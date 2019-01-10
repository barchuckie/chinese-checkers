package com.chinesecheckers.server.player;

import com.chinesecheckers.server.board.Field;

import java.util.ArrayList;
import java.util.Random;

public class BotPlayer extends Player {

    /**
     * Bot's playerID in a game
     */
    private int playerID;

    /**
     * Game board fields to observe game updates
     */
    private Field[][] fields;

    /**
     * Board field that pawns are going to.
     */
    private Field destinationField;

    /**
     * Board fields on the destination arm of the board star.
     */
    private ArrayList<Field> destinationFields;

    /**
     * The best move a bot player can make.
     */
    private Field [] bestMove;

    /**
     * Creates new bot instance.
     * @param ID player ID
     */
    public BotPlayer(int ID) {
        super.setNick("Bot#" + ID);
        playerID = ID;
        bestMove = new Field[2];
        destinationFields = new ArrayList<>();
    }

    /**
     * Sets bot's fields
     * @param fields
     */
    public void setFields(Field[][] fields) {
        this.fields = fields;
    }

    /**
     * Server reads message from bot
     * @return message for server
     */
    @Override
    public String[] read() {
        String [] message = new String[]{ "MOVE",
                Integer.toString(bestMove[0].getX()), Integer.toString(bestMove[0].getY()),
                Integer.toString(bestMove[1].getX()), Integer.toString(bestMove[1].getY()) };
        if(!canMove()) {
            message[0] = "PASS";
        }
        return message;
    }

    /**
     * Server sends message to the bot
     * @param message message to send
     */
    @Override
    public void sendMessage(String message) {
        if("YOURMOVE".equals(message)) {
            makeMove();
        }
    }

    /**
     * {@inheritDoc}
     * @return true
     */
    @Override
    public boolean isBot() {
        return true;
    }

    /**
     * Bot plays a game. Makes some moves.
     */
    public void makeMove() {
        ArrayList<Field> myFields = new ArrayList<>();
        for (Field[] fieldsRow : fields) {
            for (Field field : fieldsRow) {
                if(field != null && this.equals(field.getPlayer())) {
                    myFields.add(field);
                }
            }
        }
        bestMove[0] = myFields.get(0);
        bestMove[1] = myFields.get(0);

        for(Field destination : destinationFields) {
            if(canMove()) break;
            destinationField = destination;
            Random rand = new Random();
            for (Field origin : myFields) {
                for(int i = 0; i < origin.getNeighbours().length; ++i) {
                    Field neighbour = origin.getNeighbours()[i];
                    if(neighbour != null) {
                        if(neighbour.getPlayer() == null) {
                            if(valueOfMove(origin, neighbour) > valueOfMove(bestMove[0], bestMove[1])) {
                                bestMove[0] = origin;
                                bestMove[1] = neighbour;
                            } else if(valueOfMove(origin, neighbour) == valueOfMove(bestMove[0], bestMove[1])) {
                                if(rand.nextBoolean()) {
                                    bestMove[0] = origin;
                                    bestMove[1] = neighbour;
                                }
                            }
                        } else {
                            Field nextField = neighbour.getNeighbours()[i];
                            if(nextField != null) {
                                correctJumpPaths(origin,null, origin);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Defines whether player decided to move or not
     * @return true if player moves, false if not
     */
    private boolean canMove() {
        return !(bestMove[0].getX() == bestMove[1].getX() && bestMove[0].getY() == bestMove[1].getY());
    }

    /**
     * Search for jump moves and judge their value.
     * @param origin original field
     * @param lastField last field of the move
     * @param currentField current field in the move
     */
    private void correctJumpPaths(Field origin, Field lastField, Field currentField) {
        Field [] neighbours = currentField.getNeighbours();
        for(int i = 0; i < neighbours.length; ++i) { // jump move validation
            if((neighbours[i] != null) && (neighbours[i].getPlayer() != null)) {
                Field nextField = neighbours[i].getNeighbours()[i];
                if(nextField != null && !nextField.equals(lastField) && nextField.getPlayer() == null) {
                    if(valueOfMove(origin, nextField) >= valueOfMove(bestMove[0], bestMove[1])) {
                        bestMove[0] = origin;
                        bestMove[1] = nextField;
                    } else if(valueOfMove(origin, nextField) == valueOfMove(bestMove[0], bestMove[1])) {
                        Random random = new Random();
                        if(random.nextBoolean()) {
                            bestMove[0] = origin;
                            bestMove[1] = nextField;
                        }
                    }
                    //correctJumpPaths(origin, currentField, nextField);
                }
            }
        }
    }

    /**
     * Calculates the value of the move.
     * It is the difference in distance between origin/destination and the field that the pawn is going to.
     * @param origin original field
     * @param destination destination field
     * @return value of the move
     */
    private int valueOfMove(Field origin, Field destination) {
        return distance(origin, destinationField) - distance(destination, destinationField);
    }

    /**
     * Calculates the shortest distance in simple jumps between two fields.
     * @param origin original field
     * @param destination destination field
     * @return distance between them
     */
    private int distance(Field origin, Field destination) {
        int horizontalLength = Math.abs(destination.getY() - origin.getY());
        int verticalLength = Math.abs(destination.getX() - origin.getX());
        return (horizontalLength + verticalLength)/2;
    }

    /**
     * Sets all destination parameters according to a given arm.
     * @param destinationArm arm that the bot is going to
     */
    public void setDestinationArm(int destinationArm) {
        switch (destinationArm) {
            case 0:
                destinationFields.add(fields[0][12]);
                for(int i = 1; i < 4; ++i) {
                    for(Field field : fields[i]) {
                        if(field != null) {
                            destinationFields.add(field);
                        }
                    }
                }

            case 1:
                destinationFields.add(fields[4][24]);
                for(int i = 4; i < 9; ++i) {
                    for(int j = 1; j < 11-i; ++j) {
                        Field field = fields[i][24-j];
                        if(field != null) {
                            destinationFields.add(field);
                        }
                    }
                }

            case 2:
                destinationFields.add(fields[12][24]);
                for(int i = 4; i < 9; ++i) {
                    for(int j = 1; j < 11-i; ++j) {
                        Field field = fields[16-i][24-j];
                        if(field != null) {
                            destinationFields.add(field);
                        }
                    }
                }

            case 3:
                destinationFields.add(fields[16][12]);
                for(int i = 15; i > 12; --i) {
                    for (Field field : fields[i]) {
                        if(field != null) {
                            destinationFields.add(field);
                        }
                    }
                }

            case 4:
                destinationFields.add(fields[12][0]);
                for(int i = 4; i < 9; ++i) {
                    for(int j = 1; j < 11-i; ++j) {
                        Field field = fields[16-i][j];
                        if(field != null) {
                            destinationFields.add(field);
                        }
                    }
                }

            case 5:
                destinationFields.add(fields[4][0]);
                for(int i = 4; i < 9; ++i) {
                    for(int j = 1; j < 11-i; ++j) {
                        Field field = fields[i][j];
                        if(field != null) {
                            destinationFields.add(field);
                        }
                    }
                }
        }
        destinationField = destinationFields.get(0);
    }
}

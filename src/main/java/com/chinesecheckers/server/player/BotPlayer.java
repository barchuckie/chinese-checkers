package com.chinesecheckers.server.player;

import com.chinesecheckers.server.GameServer;
import com.chinesecheckers.server.board.Field;
import com.chinesecheckers.server.game.Game;

import java.util.ArrayList;

public class BotPlayer extends Player {
    /**
     * Message sent to a server
     */
    private String [] inputMessage;

    /**
     * Bot's playerID in a game
     */
    private int playerID;

    /**
     * Creates new bot instance.
     * @param ID player ID
     */
    public BotPlayer(int ID) {
        super.setNick("Bot#" + ID);
        playerID = ID;
        inputMessage = new String[5];
        inputMessage[0] = "MOVE";
    }

    /**
     * Server reads message from bot
     * @return message for server
     */
    @Override
    public String[] read() {
        return inputMessage;
    }

    /**
     * Server sends message to the bot
     * @param message message to send
     */
    @Override
    public void sendMessage(String message) {

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
    public Field[] makeMove(Field [][] fields) {
        ArrayList<Field> myFields = new ArrayList<>();
        for (Field[] fieldsRow : fields) {
            for (Field field : fieldsRow) {
                if(field != null && this.equals(field.getPlayer())) {
                    myFields.add(field);
                }
            }
        }
        Field [] bestMove = { myFields.get(0), myFields.get(0) };
        int i = 0;
        for(Field origin : myFields) {
            ArrayList<Field> correctPaths = correctFields(origin);
            for(Field potentialDestination : correctPaths) {
                System.out.println(i + " O: " + origin.getX() + " " + origin.getY() + " D: " +
                        potentialDestination.getX() + " " + potentialDestination.getY());
                if(distance(origin, potentialDestination) > distance(bestMove[0], bestMove[1])) {
                    bestMove[0] = origin;
                    bestMove[1] = potentialDestination;
                }
                i++;
            }
        }
        inputMessage[1] = Integer.toString(bestMove[0].getX());
        inputMessage[2] = Integer.toString(bestMove[0].getY());
        inputMessage[3] = Integer.toString(bestMove[1].getX());
        inputMessage[4] = Integer.toString(bestMove[1].getY());

        return bestMove;
    }

    private ArrayList<Field> correctFields(Field field) {
        ArrayList<Field> properFields = new ArrayList<>();
        for(Field neighbour : field.getNeighbours()) {
            if(neighbour != null && neighbour.getPlayer() == null) {
                properFields.add(neighbour);
            }
        }
        properFields.addAll(correctJumpPaths(null, field));
        return properFields;
    }

    private ArrayList<Field> correctJumpPaths(Field lastField, Field currentField) {
        ArrayList<Field> properFields = new ArrayList<>();
        Field [] neighbours = currentField.getNeighbours();
        for(int i = 0; i < neighbours.length; ++i) { // jump move validation
            if((neighbours[i] != null) && (neighbours[i].getPlayer() != null)) {
                Field nextField = neighbours[i].getNeighbours()[i];
                if(nextField != null && !nextField.equals(lastField) && nextField.getPlayer() == null) {
                    properFields.add(nextField);
                    properFields.addAll(correctJumpPaths(currentField, nextField));
                }
            }
        }
        return properFields;
    }

    private int distance(Field origin, Field destination) {
        int verticalLength = Math.abs(destination.getY() - origin.getY());
        int horizontalLength = Math.abs(destination.getX() - origin.getY());
        return verticalLength > horizontalLength ? verticalLength : horizontalLength;
    }
}

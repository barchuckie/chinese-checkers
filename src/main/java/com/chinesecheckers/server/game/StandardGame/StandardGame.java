package com.chinesecheckers.server.game.StandardGame;

import com.chinesecheckers.server.player.Player;
import com.chinesecheckers.server.board.BoardGenerator;
import com.chinesecheckers.server.board.Field;
import com.chinesecheckers.server.board.StandardBoard.StandardBoardGenerator;
import com.chinesecheckers.server.game.Game;
import com.chinesecheckers.server.game.GameData;

import java.util.Random;

/**
 * Class representing standard game in chinese checkers.
 * It takes care of the whole game logic.
 */
public class StandardGame extends Game {

    /**
     * Last turn player
     */
    private Player lastPlayer;

    /**
     * Coordinates storing original position of the player and their default values.
     */
    private int originalX, originalY, defaultX, defaultY;

    /**
     * Variable allowing player whether to move further or not.
     */
    private boolean allowFurtherMove;

    /**
     * Instantiates new standard game.
     * Builds a board, randomize a first player to move and sets all class fields.
     * @param data game data
     */
    public StandardGame(GameData data) {
        super(data);
        BoardGenerator generator = new StandardBoardGenerator();
        super.board = generator.generateBoard(data);
        Random rand = new Random();
        currentPlayer = rand.nextInt(numOfPlayers);
        defaultX = super.board.getFields().length;
        defaultY = super.board.getFields()[0].length;
        setDefaults();
    }

    /**
     * Validates whether the move is correct according to standard game rules.
     * @param player player who has made the move
     * @param oldX old field x coordinate
     * @param oldY old field y coordinate
     * @param newX new field x coordinate
     * @param newY new field y coordinate
     * @return true if approved, otherwise false
     */
    @Override
    public boolean validateMove(Player player, int oldX, int oldY, int newX, int newY) {
        System.out.println("Wszedł w validateMove z: " + player.getNick() + " " +
                oldX + " " + oldY + " " + newX + " " + newY);

        if(!player.equals(players[currentPlayer])) {
            System.out.println("Zły gracz rusza");
            return false;
        }

        Field [][] fields = board.getFields();

        if(!player.equals(fields[oldX][oldY].getPlayer())) {
            // player must play only with its own pawns
            System.out.println("Gracz rusza nie swój pionek");
            return false;
        }
        if(fields[newX][newY].getPlayer() != null) { // destination field must be empty
            System.out.println("Pole zajęte");
            return false;
        }

        Field [] neighbours = fields[oldX][oldY].getNeighbours();

        if(newX == originalX && newY == originalY) {
            System.out.println("Poprawne cofnięcie z pola sąsiadującego");
            setDefaults();
            return true;
        }

        if(allowFurtherMove) {
            if(!player.equals(lastPlayer)) {
                originalX = oldX;
                originalY = oldY;
                for (Field neighbour : neighbours) { // simple move validation
                    if (fields[newX][newY].equals(neighbour)) {
                        System.out.println("Poprawne pole sasiadujące");
                        lastPlayer = player;
                        allowFurtherMove = false;
                        return true;
                    }
                }
            }

            for(int i = 0; i < neighbours.length; ++i) { // jump move validation
                if((neighbours[i] != null) &&
                        (neighbours[i].getPlayer() != null)) {
                    Field nextField = neighbours[i].getNeighbours()[i];
                    if(nextField != null) {
                        if(board.getFields()[newX][newY].equals(nextField)) {
                            System.out.println("Poprawny skok");
                            lastPlayer = player;
                            return true;
                        }
                    }
                }
            }
        }

        System.out.println("Niepoprawny ruch");
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean checkWinner(int player) {
        if(getDestination(player) == -1) {
            return false;
        }
        if(player < 0 || player >= numOfPlayers) {
            return false;
        }
        return checkArm(getDestination(player), player);
    }

    /**
     * Returns destination arm for the given player. Returns -1 if some logic error occurs.
     * @param player player which destination vertex is returned for
     * @return destination arm ID
     */
    @Override
    public int getDestination(int player) {
        switch (numOfPlayers) {
            case 2:
                if(player == 1) {
                    return 0;
                } else if(player == 0) {
                    return 3;
                }

            case 3:
                switch (player) {
                    case 0:
                        return 3;

                    case 1:
                        return 5;

                    case 2:
                        return 1;

                }

            case 4:
                switch (player) {
                    case 0:
                        return 4;

                    case 1:
                        return 5;

                    case 2:
                        return 1;

                    case 3:
                        return 2;
                }

            case 6:
                switch (player) {
                    case 0:
                        return 3;

                    case 1:
                        return 4;

                    case 2:
                        return 5;

                    case 3:
                        return 0;

                    case 4:
                        return 1;

                    case 5:
                        return 2;
                }

            default:
                return -1;
        }
    }

    /**
     * Checks given star arm if all the pawns of the player are there.
     * If they are all in the opposite arm to the starting one, then the player wins (returns true).
     * @param armNumber arm number to be checked
     * @param player player to check
     * @return true if player has won, false if not
     */
    private boolean checkArm(int armNumber, int player) {
        Field [][] fields = board.getFields();
        switch (armNumber) {
            case 0:
                for(int i = 0; i < 4; ++i) {
                    for(Field field : fields[i]) {
                        if(field != null) {
                            if(!players[player].equals(field.getPlayer())) {
                                return false;
                            }
                        }
                    }
                }
                return true;

            case 1:
                for(int i = 4; i < 9; ++i) {
                    for(int j = 0; j < 11-i; ++j) {
                        Field field = fields[i][24-j];
                        if(field != null) {
                            if(!players[player].equals(field.getPlayer())) {
                                return false;
                            }
                        }
                    }
                }
                return true;

            case 2:
                for(int i = 4; i < 9; ++i) {
                    for(int j = 0; j < 11-i; ++j) {
                        Field field = fields[16-i][24-j];
                        if(field != null) {
                            if(!players[player].equals(field.getPlayer())) {
                                return false;
                            }
                        }
                    }
                }
                return true;

            case 3:
                for(int i = 16; i > 12; --i) {
                    for (Field field : fields[i]) {
                        if(field != null) {
                            if(!players[player].equals(field.getPlayer())) {
                                return false;
                            }
                        }
                    }
                }
                return true;

            case 4:
                for(int i = 4; i < 9; ++i) {
                    for(int j = 0; j < 11-i; ++j) {
                        Field field = fields[16-i][j];
                        if(field != null) {
                            if(!players[player].equals(field.getPlayer())) {
                                return false;
                            }
                        }
                    }
                }
                return true;

            case 5:
                for(int i = 4; i < 9; ++i) {
                    for(int j = 0; j < 11-i; ++j) {
                        Field field = fields[i][j];
                        if(field != null) {
                            if(!players[player].equals(field.getPlayer())) {
                                return false;
                            }
                        }
                    }
                }
                return true;

                default: return false;
        }
    }

    /**
     * Sets all logical variables to default values.
     * Sets original field coordinates to their default values, clears lastPlayer and allows further move.
     */
    private void setDefaults() {
        originalX = defaultX;
        originalY = defaultY;
        lastPlayer = null;
        allowFurtherMove = true;
    }

    @Override
    public void nextTurn() {
        super.nextTurn();
        setDefaults();
    }
}

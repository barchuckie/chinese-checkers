package com.chinesecheckers.server.board.StandardBoard;
import com.chinesecheckers.server.game.GameData;
import com.chinesecheckers.server.player.Player;
import com.chinesecheckers.server.board.Board;
import com.chinesecheckers.server.board.Field;

/**
 * Concrete class extending {@code Board}. Represents standard game board.
 * @see Board
 */
public class StandardBoard extends Board {

    /**
     * Number of players on a board
     */
    private int numOfPlayers;

    /**
     * Array of players on a board
     */
    private Player[] players;

    /**
     * Rows number
     */
    private final int ROWS = 17;

    /**
     * Columns number
     */
    private final int COLUMNS = 25;

    /**
     * Creates an instance with given game data.
     * @param data game data
     */
    StandardBoard(GameData data) {
        fields = new Field[ROWS][COLUMNS];
        for(int i = 0; i < 17; i++) {
            for(int j = 0; j < 25; j++) {
                fields[i][j]=null;
            }
        }
        this.numOfPlayers = data.getNumOfPlayers();
        this.players = data.getPlayers();
        setFields();
        setFieldsNeighbours();
        addPlayers();
    }

    /**
     * Sets proper fields.
     */
    private void setFields() {
        for(int i = 0; i < 13; i++) {
            for(int j = 12-i; j <= 12 + i; j = j + 2) {
                fields[i][j] = new Field(i, j);
                fields[16-i][j] = new Field(16-i, j);
            }
        }
    }

    /**
     * Sets every field neighbours. These are six fields around.
     */
    private void setFieldsNeighbours() {
        for(int i = 0; i < ROWS; ++i) {
            for(int j = 0; j < COLUMNS; ++j) {
                if(fields[i][j] != null) {
                    Field [] neighbours = { null, null, null, null, null, null };
                    if(i == 0) {
                        neighbours[1] = fields[i][j+2];
                        neighbours[2] = fields[i+1][j+1];
                        neighbours[3] = fields[i+1][j-1];
                        neighbours[4] = fields[i][j-2];
                    } else if(i == ROWS - 1) {
                        neighbours[0] = fields[i-1][j+1];
                        neighbours[1] = fields[i][j+2];
                        neighbours[4] = fields[i][j-2];
                        neighbours[5] = fields[i-1][j-1];
                    } else if(j == 0) {
                        neighbours[0] = fields[i-1][j+1];
                        neighbours[1] = fields[i][j+2];
                        neighbours[2] = fields[i+1][j+1];
                    } else if(j == 1) {
                        neighbours[0] = fields[i-1][j+1];
                        neighbours[1] = fields[i][j+2];
                        neighbours[2] = fields[i+1][j+1];
                        neighbours[3] = fields[i+1][j-1];
                        neighbours[5] = fields[i-1][j-1];
                    } else if(j == COLUMNS - 1) {
                        neighbours[3] = fields[i+1][j-1];
                        neighbours[4] = fields[i][j-2];
                        neighbours[5] = fields[i-1][j-1];
                    } else if(j == COLUMNS - 2) {
                        neighbours[0] = fields[i-1][j+1];
                        neighbours[2] = fields[i+1][j+1];
                        neighbours[3] = fields[i+1][j-1];
                        neighbours[4] = fields[i][j-2];
                        neighbours[5] = fields[i-1][j-1];
                    } else {
                        neighbours[0] = fields[i-1][j+1];
                        neighbours[1] = fields[i][j+2];
                        neighbours[2] = fields[i+1][j+1];
                        neighbours[3] = fields[i+1][j-1];
                        neighbours[4] = fields[i][j-2];
                        neighbours[5] = fields[i-1][j-1];
                    }
                    fields[i][j].setNeighbours(neighbours);
                }
            }
        }
    }

    /**
     * Adds players to the board.
     */
    private void addPlayers() {
            switch (numOfPlayers) {
                case 2:
                    for(int i = 0; i < 4; ++i) {
                        for(Field field : fields[i]) {
                            if(field != null) {
                                field.setPlayer(players[0]);
                            }
                        }
                        for (Field field : fields[16-i]) {
                            if(field != null) {
                                field.setPlayer(players[1]);
                            }
                        }
                    }
                    break;

                case 3:
                    for(int i = 0; i < 4; ++i) {
                        for(Field field : fields[i]) {
                            if(field != null) {
                                field.setPlayer(players[0]);
                            }
                        }
                    }
                    for(int i = 4; i < 9; ++i) {
                        for(int j = 0; j < 11-i; ++j) {
                            Field field = fields[16-i][j];
                            if(field != null) {
                                field.setPlayer(players[2]);
                            }
                            field = fields[16-i][24-j];
                            if(field != null) {
                                field.setPlayer(players[1]);
                            }
                        }
                    }
                    break;

                case 4:
                    for(int i = 4; i < 9; ++i) {
                        for(int j = 0; j < 11-i; ++j) {
                            Field field = fields[i][j];
                            if(field != null) {
                                field.setPlayer(players[3]);
                            }
                            field = fields[i][24-j];
                            if(field != null) {
                                field.setPlayer(players[0]);
                            }
                            field = fields[16-i][j];
                            if(field != null) {
                                field.setPlayer(players[2]);
                            }
                            field = fields[16-i][24-j];
                            if(field != null) {
                                field.setPlayer(players[1]);
                            }
                        }
                    }
                    break;
                case 6:
                    for(int i = 0; i < 4; ++i) {
                        for(Field field : fields[i]) {
                            if(field != null) {
                                field.setPlayer(players[0]);
                            }
                        }
                        for (Field field : fields[16-i]) {
                            if(field != null) {
                                field.setPlayer(players[3]);
                            }
                        }
                    }
                    for(int i = 4; i < 9; ++i) {
                        for(int j = 0; j < 11-i; ++j) {
                            Field field = fields[i][j];
                            if(field != null) {
                                field.setPlayer(players[5]);
                            }
                            field = fields[i][24-j];
                            if(field != null) {
                                field.setPlayer(players[1]);
                            }
                            field = fields[16-i][j];
                            if(field != null) {
                                field.setPlayer(players[4]);
                            }
                            field = fields[16-i][24-j];
                            if(field != null) {
                                field.setPlayer(players[2]);
                            }
                        }
                    }
                    break;

                default:
                    //Throw exc
            }
    }

}

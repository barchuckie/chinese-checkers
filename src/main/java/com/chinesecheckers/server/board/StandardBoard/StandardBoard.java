package com.chinesecheckers.server.board.StandardBoard;
import com.chinesecheckers.server.game.GameData;
import com.chinesecheckers.server.Player;
import com.chinesecheckers.server.board.Board;
import com.chinesecheckers.server.board.Field;

public class StandardBoard extends Board {

    private int numOfPlayers;
    private Player[] players;

    StandardBoard(GameData data) {
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

    private void setFields() {
        for(int i = 0; i < 13; i++) {
            for(int j = 12-i; j <= 12 + i; j = j + 2) {
                fields[i][j]=new Field();
                fields[16-i][j]=new Field();
            }
        }
    }

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


    public Field[][] getFields() {
        return fields;
    }

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

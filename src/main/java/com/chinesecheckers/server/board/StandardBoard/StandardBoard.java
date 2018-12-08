package com.chinesecheckers.server.board.StandardBoard;
import com.chinesecheckers.server.Game;
import com.chinesecheckers.server.GameData;
import com.chinesecheckers.server.Player;
import com.chinesecheckers.server.board.Board;
import com.chinesecheckers.server.board.Field;

import java.util.Arrays;

public class StandardBoard extends Board {

    private int numOfPlayers;
    private Player[] players;

    StandardBoard(GameData data) {
        /* code */
        for(int i = 0; i < 17; i++) {
            for(int j = 0; j < 25; j++) {
                fields[i][j]=null;
            }
        }
        this.numOfPlayers = data.getNumOfPlayers();
        this.players = data.getPlayers();
        setFields();
        addPlayers();
    }

    private void setFields() {
        for(int i = 0; i < 13; i++) {
            for(int j = 12-i; j <= 12 + i; j = j + 2) {
                fields[i][j]=new Field(true);
                fields[16-i][j]=new Field(true);
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

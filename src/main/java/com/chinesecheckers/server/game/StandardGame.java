package com.chinesecheckers.server.game;

import com.chinesecheckers.server.Player;
import com.chinesecheckers.server.board.BoardGenerator;
import com.chinesecheckers.server.board.Field;
import com.chinesecheckers.server.board.StandardBoard.StandardBoardGenerator;

import java.util.Random;

public class StandardGame extends Game {

    public StandardGame(GameData data) {
        super(data);
        BoardGenerator generator = new StandardBoardGenerator();
        super.board = generator.generateBoard(data);
        Random rand = new Random();
        currentPlayer = rand.nextInt(numOfPlayers);
    }

    @Override
    public boolean validateMove(Player player, int oldX, int oldY, int newX, int newY) {
        if(player.equals(players[currentPlayer])) {
            Field [][] fields = board.getFields();
            if(!fields[oldY][oldX].getPlayer().equals(player)) { // player must play only with its own pawns
                return false;
            }
            if(fields[newY][newX].getPlayer() != null) { // destination field must be empty
                return false;
            }

            Field [] neighbours = fields[oldY][oldX].getNeighbours();

            for (Field neighbour : neighbours) { // simple move validation
                if (fields[newY][newX].equals(neighbour)) {
                    return true;
                }
            }

            for(int i = 0; i < neighbours.length; ++i) { // jump move validation
                if((neighbours[i] != null) &&
                        (neighbours[i].getPlayer() != null)) {
                    Field nextField = neighbours[i].getNeighbours()[i];
                    if(nextField != null) {
                        if(board.getFields()[newY][newX].equals(nextField)) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    @Override
    public boolean checkWinner(int currentPlayer) {
        Field [][] fields = board.getFields();
        switch (numOfPlayers) {
            case 2:
                if(currentPlayer == 1) {
                    for(int i = 0; i < 4; ++i) {
                        for (Field field : fields[i]) {
                            if(!checkField(field)) {
                                return false;
                            }
                        }
                    }
                    return true;
                } else if(currentPlayer == 0) {
                    for(int i = 0; i < 4; ++i) {
                        for (Field field : fields[16-i]) {
                            if(!checkField(field)) {
                                return false;
                            }
                        }
                    }
                    return true;
                }
                return false;

            case 3:
                switch (currentPlayer) {
                    case 0:
                        for(int i = 0; i < 4; ++i) {
                            for (Field field : fields[16-i]) {
                                if(!checkField(field)) {
                                    return false;
                                }
                            }
                        }
                        return true;

                    case 1:
                        for(int i = 4; i < 9; ++i) {
                            for (int j = 0; j < 11 - i; ++j) {
                                if(!checkField(fields[i][j])) {
                                    return false;
                                }
                            }
                        }
                        return true;

                    case 2:
                        for(int i = 4; i < 9; ++i) {
                            for (int j = 0; j < 11 - i; ++j) {
                                if(!checkField(fields[i][24 - j])) {
                                    return false;
                                }
                            }
                        }
                        return true;

                }
                return false;

            case 4:
                switch (currentPlayer) {
                    case 0:
                        for(int i = 4; i < 9; ++i) {
                            for (int j = 0; j < 11 - i; ++j) {
                                if(!checkField(fields[16-i][j])) {
                                    return false;
                                }
                            }
                        }
                        return true;

                    case 1:
                        for(int i = 4; i < 9; ++i) {
                            for (int j = 0; j < 11 - i; ++j) {
                                if(!checkField(fields[i][j])) {
                                    return false;
                                }
                            }
                        }
                        return true;

                    case 2:
                        for(int i = 4; i < 9; ++i) {
                            for (int j = 0; j < 11 - i; ++j) {
                                if(!checkField(fields[i][24 - j])) {
                                    return false;
                                }
                            }
                        }
                        return true;

                    case 3:
                        for(int i = 4; i < 9; ++i) {
                            for (int j = 0; j < 11 - i; ++j) {
                                if(!checkField(fields[16 - i][24 - j])) {
                                    return false;
                                }
                            }
                        }
                        return true;
                }
                return false;

            case 6:
                switch (currentPlayer) {
                    case 0:
                        for(int i = 0; i < 4; ++i) {
                            for (Field field : fields[16-i]) {
                                if(!checkField(field)) {
                                    return false;
                                }
                            }
                        }
                        return true;

                    case 1:
                        for(int i = 4; i < 9; ++i) {
                            for (int j = 0; j < 11 - i; ++j) {
                                if(!checkField(fields[16 - i][j])) {
                                    return false;
                                }
                            }
                        }
                        return true;

                    case 2:
                        for(int i = 4; i < 9; ++i) {
                            for (int j = 0; j < 11 - i; ++j) {
                                if(!checkField(fields[i][j])) {
                                    return false;
                                }
                            }
                        }
                        return true;

                    case 3:
                        for(int i = 0; i < 4; ++i) {
                            for(Field field : fields[i]) {
                                if(!checkField(field)) {
                                    return false;
                                }
                            }
                        }
                        return true;

                    case 4:
                        for(int i = 4; i < 9; ++i) {
                            for (int j = 0; j < 11 - i; ++j) {
                                if(!checkField(fields[i][24 - j])) {
                                    return false;
                                }
                            }
                        }
                        return true;

                    case 5:
                        for(int i = 4; i < 9; ++i) {
                            for (int j = 0; j < 11 - i; ++j) {
                                if(!checkField(fields[16 - i][24 - j])) {
                                    return false;
                                }
                            }
                        }
                        return true;
                }
                return false;

                default:
                    return false;
        }
    }

    private boolean checkField(Field field) {
        if(field != null) {
            return field.getPlayer().equals(players[currentPlayer]);
        }
        return true;
    }
}

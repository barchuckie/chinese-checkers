package com.chinesecheckers.server.game.StandardGame;

import com.chinesecheckers.server.Player;
import com.chinesecheckers.server.board.BoardGenerator;
import com.chinesecheckers.server.board.Field;
import com.chinesecheckers.server.board.StandardBoard.StandardBoardGenerator;
import com.chinesecheckers.server.game.Game;
import com.chinesecheckers.server.game.GameData;

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

        for (Field neighbour : neighbours) { // simple move validation
            if (fields[newX][newY].equals(neighbour)) {
                System.out.println("Poprawne pole sasiadujące");
                return true;
            }
        }

        for(int i = 0; i < neighbours.length; ++i) { // jump move validation
            if((neighbours[i] != null) &&
                    (neighbours[i].getPlayer() != null)) {
                Field nextField = neighbours[i].getNeighbours()[i];
                if(nextField != null) {
                    if(board.getFields()[newX][newY].equals(nextField)) {
                        System.out.println("Poprawny skok");
                        return true;
                    }
                }
            }
        }

        System.out.println("Niepoprawny ruch");
        return false;
    }

    @Override
    public boolean checkWinner(int player) {
        switch (numOfPlayers) {
            case 2:
                if(player == 1) {
                    return checkArm(0, player);
                } else if(player == 0) {
                    return checkArm(3, player);
                }
                return false;

            case 3:
                switch (player) {
                    case 0:
                        return checkArm(3, player);

                    case 1:
                        return checkArm(5, player);

                    case 2:
                        return checkArm(1, player);

                }
                return false;

            case 4:
                switch (player) {
                    case 0:
                        return checkArm(4, player);

                    case 1:
                        return checkArm(5, player);

                    case 2:
                        return checkArm(1, player);

                    case 3:
                        return checkArm(2, player);
                }
                return false;

            case 6:
                switch (player) {
                    case 0:
                        return checkArm(3, player);

                    case 1:
                        return checkArm(4, player);

                    case 2:
                        return checkArm(5, player);

                    case 3:
                        return checkArm(0, player);

                    case 4:
                        return checkArm(1, player);

                    case 5:
                        return checkArm(2, player);
                }
                return false;

                default:
                    return false;
        }
    }

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
}

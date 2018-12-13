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
            if(!fields[oldY][oldX].getPlayer().equals(player)) { //error in logic
                //throw exc
            }
            if(fields[newY][newX].getPlayer() != null) { //field must be empty
                return false;
            }

            Field [] neighbours = fields[oldY][oldX].getNeighbours();

            for(int i = 0; i < neighbours.length; ++i) {
                if(fields[newY][newX].equals(neighbours[i])) {
                    makeMove(player, oldX, oldY, newX, newY);
                    return true;
                }
            }

            if(checkPaths(newX, newY, neighbours, fields[oldY][oldX])) {
                makeMove(player, oldX, oldY, newX, newY);
                return true;
            }
        }
        return false;
    }

    private boolean checkPaths(int newX, int newY, Field [] neighbours, Field origin) {
        for(int i = 0; i < neighbours.length; ++i) {
            if(neighbours[i] != null && neighbours[i].getPlayer() != null && !neighbours[i].equals(origin)) {
                Field nextField = neighbours[i].getNeighbours()[i];
                if(nextField != null) {
                    if(board.getFields()[newY][newX].equals(nextField)) {
                        return true;
                    } else {
                        if(checkPaths(newX, newY, nextField.getNeighbours(), nextField)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private void makeMove(Player player, int oldX, int oldY, int newX, int newY) {
        if(currentPlayer < numOfPlayers-1) {
            currentPlayer++;
        } else {
            currentPlayer = 0;
        }
        board.getFields()[oldY][oldX].setPlayer(null);
        board.getFields()[newY][newX].setPlayer(player);
    }
}

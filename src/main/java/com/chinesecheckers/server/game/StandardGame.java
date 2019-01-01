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

            for (Field neighbour : neighbours) {
                if (fields[newY][newX].equals(neighbour)) {
                    return true;
                }
            }

            for(int i = 0; i < neighbours.length; ++i) {
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
    public boolean checkWinner() {
        return false;
    }
}

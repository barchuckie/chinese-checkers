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
            //TODO: implement jumps over players
            Field [][] fields = board.getFields();
            if(!fields[oldY][oldX].getPlayer().equals(player)) { //error in logic
                //throw exc
            }
            if(fields[newY][newX].getPlayer() != null) { //field must be empty
                return false;
            }

            for(Field f : fields[oldY][oldX].getNeighbours()) {
                if(fields[newY][newX].equals(f)) {
                    if(currentPlayer < numOfPlayers-1) {
                        currentPlayer++;
                    } else {
                        currentPlayer = 0;
                    }
                    return true;
                }
            }
        }
        return false;
    }
}

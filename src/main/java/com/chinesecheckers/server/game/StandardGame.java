package com.chinesecheckers.server.game;

import com.chinesecheckers.server.Player;
import com.chinesecheckers.server.board.BoardGenerator;
import com.chinesecheckers.server.board.StandardBoard.StandardBoardGenerator;

public class StandardGame extends Game {

    public StandardGame(GameData data) {
        super(data);
        BoardGenerator generator = new StandardBoardGenerator();
        super.board = generator.generateBoard(data);
    }

    @Override
    public boolean validateMove(Player player, int oldX, int oldY, int newX, int newY) {
        if(player.equals(currentPlayer)) {
            //TODO
            return true;
        }
        return false;
    }
}

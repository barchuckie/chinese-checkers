package com.chinesecheckers.client.Board;

/* FACTORY METHOD PATTERN */

public abstract class BoardFactory {

    public abstract Board createBoard(int players);

    public Board getBoard(int players,String gameMode)
    {
        Board board = createBoard(players);
        board.setup(gameMode);
        return board;
    }


}


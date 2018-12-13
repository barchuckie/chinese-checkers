package com.chinesecheckers.client.Board;

/* FACTORY METHOD PATTERN */

public abstract class BoardFactory {

    public abstract Board createBoard(int players);

    public Board getBoard(int players)
    {
        Board board = createBoard(players);
        board.setup();
        return board;
    }


}

package com.chinesecheckers.client.Board;

import com.chinesecheckers.client.Circle;

public class HexBoardFourPlayers extends HexBoard{
    @Override
    public void addPlayers()
    {
        for(int i = 4; i < 9; ++i) {
            for(int j = 0; j < 11-i; ++j) {
                Circle circle = circles[i][j];
                if(circle != null) {
                    circle.setPlayer(6);
                }
                circle = circles[i][24-j];
                if(circle != null) {
                    circle.setPlayer(2);
                }
                circle = circles[16-i][j];
                if(circle != null) {
                    circle.setPlayer(5);
                }
                circle = circles[16-i][24-j];
                if(circle != null) {
                    circle.setPlayer(3);
                }
            }
        }
    }
}

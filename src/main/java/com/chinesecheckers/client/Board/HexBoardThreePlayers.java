package com.chinesecheckers.client.Board;

import com.chinesecheckers.client.Circle;

public class HexBoardThreePlayers extends HexBoard {

    @Override
    public void addPlayers()
    {
        for(int i = 0; i < 4; ++i) {
            for(Circle circle : circles[i]) {
                if(circle != null) {
                    circle.setPlayer(1);
                }
            }
        }
        for(int i = 4; i < 9; ++i) {
            for(int j = 0; j < 11-i; ++j) {
                Circle circle = circles[16-i][j];
                if(circle != null) {
                    circle.setPlayer(3);
                }
                circle = circles[16-i][24-j];
                if(circle != null) {
                    circle.setPlayer(2);
                }
            }
        }
    }
}

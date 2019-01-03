package com.chinesecheckers.client.Board;

import com.chinesecheckers.client.Circle;
import com.chinesecheckers.client.Field;

public class HexBoardThreePlayers extends HexBoard {

    @Override
    public void addPlayers()
    {
        for(int i = 0; i < 4; ++i) {
            for(Field field : fields[i]) {
                if(field != null) {
                    field.setPlayer(1);
                }
            }
        }
        for(int i = 4; i < 9; ++i) {
            for(int j = 0; j < 11-i; ++j) {
                Field field = fields[16-i][j];
                if(field != null) {
                    field.setPlayer(3);
                }
                field = fields[16-i][24-j];
                if(field != null) {
                    field.setPlayer(2);
                }
            }
        }
    }
}

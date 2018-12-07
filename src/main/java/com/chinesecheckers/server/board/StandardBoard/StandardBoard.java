package com.chinesecheckers.server.board.StandardBoard;
import com.chinesecheckers.server.board.Board;
import com.chinesecheckers.server.board.Field;

import java.util.Arrays;

public class StandardBoard extends Board {

    private int numOfPlayers;
    public int f;

    StandardBoard(int numOfPLayers) {
        /* code */
        f = 0;
        /*
        for (int i = 0; i < 17; ++i) {
            Arrays.fill(fields[i], new Field(false));
        }
        */
        for(int i=0;i<17;i++)
        {
            for(int j=0;j<25;j++)
            {
                fields[i][j]=new Field(false);
            }
        }
        this.numOfPlayers = numOfPLayers;
        setFields();
        addPlayers(numOfPLayers);
    }

    private void setFields() {
        /*for(int i = 0; i < 13; i++) {
            for(int j = 12-i; j <= 12 + i; j = j + 2) {
                fields[i][j].setIsActive(true);
                fields[16-i][j].setIsActive(true);
            }
        }*/

        int a=0;
        int b=12;
        int startingb=12;
        for(int i=0;i<13;i++)
        {
            //0-13
            for(int w=0;w<i+1;w++)
            {
                f++;
                fields[a][b].setIsActive(true);
                fields[16-a][b].setIsActive(true);
                b=b+2;
            }

            startingb--;
            b=startingb;
            a++;
        }
    }

    public Field[][] getFields() {
        return fields;
    }

    private void addPlayers(int numOfPlayers) {
        switch (numOfPlayers) {
            case 2:
                break;

            case 3:
                break;

            case 6:
                break;

            default:
                //Throw exc
        }
    }

}

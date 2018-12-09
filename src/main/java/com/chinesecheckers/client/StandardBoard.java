package com.chinesecheckers.client;

import java.awt.geom.Ellipse2D;

public class StandardBoard extends Board {

    private int numOfPlayers;

    public StandardBoard(int players)
    {
        super();
        numOfPlayers=players;
        addPlayers();
    }
    private void addPlayers() {
        switch (numOfPlayers) {
            case 2:

                for(int i = 0; i < 4; ++i) {
                    //gora
                    for(Circle circle : circles[i]) {
                        if(circle != null) {
                            circle.setPlayer(1);
                        }
                    }
                    //dol
                    for (Circle circle : circles[16-i]) {
                        if(circle != null) {
                            circle.setPlayer(2);
                        }
                    }
                }
                break;

            case 3:
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
                break;
            case 4:
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
                break;

            case 6:
                for(int i = 0; i < 4; ++i) {
                    for(Circle circle : circles[i]) {
                        if(circle != null) {
                            circle.setPlayer(1);
                        }
                    }
                    for (Circle circle : circles[16-i]) {
                        if(circle != null) {
                            circle.setPlayer(4);
                        }
                    }
                }

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
                break;

            default:
                //Throw exc
        }
    }
}

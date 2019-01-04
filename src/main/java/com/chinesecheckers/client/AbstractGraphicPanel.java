package com.chinesecheckers.client;

import com.chinesecheckers.client.Board.Board;

import javax.swing.*;
import java.awt.*;
import java.io.PrintWriter;

// pola planszy implementować Shape

public abstract class AbstractGraphicPanel extends JPanel {
    Field[][] fields;
    PrintWriter printWriter;
    Board board;
    private int playerID;
    boolean myTurn=false;

    public abstract MyMouseAdapter getMyMouseAdapter();

    public AbstractGraphicPanel(PrintWriter p,Board board)
    {
        this.board=board;
        board.addPlayers();
        printWriter=p;
        fields = board.getFields();
    }
    void sendMessage(String action, int oldX, int oldY, int newX, int newY)
    {
        printWriter.println(action + " " + oldX + " " + oldY + " " + newX + " " + newY);
        System.out.println("sentMSG " + action + " " + oldX + " " + oldY + " " + newX + " " + newY);
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.red);

        for (Field[] fieldss : fields)
        {
            for (Field field : fieldss)
            {
                if (field != null)
                {
                    g2d.draw((Shape) field);
                    g2d.setColor(PlayerColor.getColor(field.getPlayer()));
                    if (field == getMyMouseAdapter().getActive())
                    {
                        g2d.setColor(PlayerColor.getColor(field.getPlayer()).darker());
                    }
                    g2d.fill((Shape) field);
                    g2d.setColor(Color.red);
                }
            }
        }
    }

    public void setPlayerID(int id)
    {
        playerID=id;
    }

    public int getPlayerID()
    {
        return playerID;
    }

    public void setMyTurn(boolean t)
    {
        myTurn = t;
        System.out.println("Zmiana"+myTurn);
    }
}
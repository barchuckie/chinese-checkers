package com.chinesecheckers.client;

import com.chinesecheckers.client.Board.Board;

import javax.swing.*;
import java.awt.*;

// pola planszy implementować Shape

public abstract class AbstractGraphicPanel extends JPanel {
    private Field[][] fields;

    public abstract MyMouseAdapter getMyMouseAdapter();

    public AbstractGraphicPanel(Board board) {
        board.addPlayers();
        fields = board.getFields();
    }

    public void paintComponent(Graphics g) {
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
}
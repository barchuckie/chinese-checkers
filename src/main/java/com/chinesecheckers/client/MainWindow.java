package com.chinesecheckers.client;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

public class MainWindow {
    private JFrame frame;
    private JPanel mainPanel,namePanel,joinGamePanelLabel;
    private JButton joinButton;
    private JLabel chineseCheckersLabel;
    private GridLayout mainGridLayout;

    public void start()
    {
        frame = new JFrame("Gra");
        mainGridLayout = new GridLayout(3,0);

        //guzik join
        joinButton = new JButton("Dołącz do istniejącej gry");
        joinButton.addActionListener(new joinButtonListener());

        joinGamePanelLabel = new JPanel(new GridBagLayout());
        joinGamePanelLabel.add(joinButton);

        //panel na nazwe
        namePanel = new JPanel();
        chineseCheckersLabel = new JLabel("CHINESE CHECKERS");
        namePanel.setLayout(new GridBagLayout());
        namePanel.add(chineseCheckersLabel);

        mainPanel = new JPanel();
        mainPanel.setLayout(mainGridLayout);
        mainPanel.add(namePanel);
        mainPanel.add(joinGamePanelLabel);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(BorderLayout.CENTER,mainPanel);
        frame.setSize(1014,800);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    class joinButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            PlayWindow playWindow = new PlayWindow();
            playWindow.start();
            frame.dispose();
        }
    }
}

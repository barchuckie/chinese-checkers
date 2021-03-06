package com.chinesecheckers.server;

import com.chinesecheckers.server.game.StandardGame.StandardGameMode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

/**
 * Class contains server window, where game data is picked up.
 * It runs {@code GameServer}.
 */
class ServerStartWindow {
    /**
     * Window content
     */
    private JFrame frame;
    private JPanel mainPanel;
    private JPanel namePanel;
    private JPanel choosePanel;
    private JPanel choosePlayersPanel;
    private JPanel chooseBotsPanel;
    private JPanel newGamePanel;
    private JPanel newGameLabelPanel;
    private JButton startButton;
    private JLabel chineseCheckersLabel;
    private ButtonGroup howManyPlayers;
    private ButtonGroup howManyBots;
    private JRadioButton twoP;
    private JRadioButton threeP;
    private JRadioButton fourP;
    private JRadioButton sixP;
    private JRadioButton zeroB;
    private JRadioButton oneB;
    private JRadioButton twoB;
    private JRadioButton threeB;
    private JRadioButton fourB;
    private JRadioButton fiveB;
    private GridLayout mainGridLayout;

    /**
     * Starts and runs server window
     */
    void start() {
        frame = new JFrame("Gra");
        mainGridLayout = new GridLayout(3,0);

        //guzik start
        startButton = new JButton("Nowa gra");
        startButton.addActionListener(new ServerStartWindow.startButtonListener());

        //guzik join
        /*
        joinButton = new JButton("Dołącz do istniejącej gry");
        joinButton.addActionListener(new ServerStartWindow.joinButtonListener());
        */
        //wybór graczy
        twoP = new JRadioButton("2",true);
        threeP = new JRadioButton("3");
        fourP = new JRadioButton("4");
        sixP = new JRadioButton("6");
        howManyPlayers = new ButtonGroup();
        howManyPlayers.add(twoP);
        howManyPlayers.add(threeP);
        howManyPlayers.add(fourP);
        howManyPlayers.add(sixP);
        choosePlayersPanel=new JPanel();
        choosePlayersPanel.add(new JLabel("Ilość Graczy"),BorderLayout.NORTH);
        choosePlayersPanel.add(twoP);
        choosePlayersPanel.add(threeP);
        choosePlayersPanel.add(fourP);
        choosePlayersPanel.add(sixP);

        //wybór botów
        zeroB = new JRadioButton("0",true);
        oneB = new JRadioButton("1");
        twoB = new JRadioButton("2");
        threeB = new JRadioButton("3");
        fourB = new JRadioButton("4");
        fiveB = new JRadioButton("5");
        howManyBots = new ButtonGroup();
        howManyBots.add(zeroB);
        howManyBots.add(oneB);
        howManyBots.add(twoB);
        howManyBots.add(threeB);
        howManyBots.add(fourB);
        howManyBots.add(fiveB);
        chooseBotsPanel=new JPanel();
        chooseBotsPanel.add(new JLabel("Ilość Botów"),BorderLayout.NORTH);
        chooseBotsPanel.add(zeroB);
        chooseBotsPanel.add(oneB);
        chooseBotsPanel.add(twoB);
        chooseBotsPanel.add(threeB);
        chooseBotsPanel.add(fourB);
        chooseBotsPanel.add(fiveB);

        // panel na wybory(gracze/boty)
        choosePanel = new JPanel();
        newGameLabelPanel = new JPanel(new GridBagLayout());
        newGamePanel = new JPanel(new GridBagLayout());

        choosePanel.setLayout(new GridLayout(4,0));
        choosePanel.add(newGameLabelPanel);
        newGameLabelPanel.add(new JLabel("START SERVER "));
        newGamePanel.add(choosePlayersPanel);
        newGamePanel.add(chooseBotsPanel);
        newGamePanel.add(startButton);
        choosePanel.add(newGamePanel);

        //panel na nazwe
        namePanel = new JPanel();
        chineseCheckersLabel = new JLabel("CHINESE CHECKERS");
        namePanel.setLayout(new GridBagLayout());
        namePanel.add(chineseCheckersLabel);

        mainPanel = new JPanel();
        mainPanel.setLayout(mainGridLayout);
        mainPanel.add(namePanel);
        mainPanel.add(choosePanel);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(BorderLayout.CENTER,mainPanel);
        frame.setSize(1014,800);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    /**
     * Class handling start button events. It creates new instance of {@code GameServer} and runs it.
     */
    class startButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            int players = 0;
            int bots = 0;
            for (Enumeration<AbstractButton> buttons = howManyPlayers.getElements(); buttons.hasMoreElements();) {
                AbstractButton button = buttons.nextElement();

                if (button.isSelected()) {
                    System.out.println("Players: " + button.getText());
                    players = Integer.parseInt(button.getText());
                }
            }
            for (Enumeration<AbstractButton> buttons = howManyBots.getElements(); buttons.hasMoreElements();) {
                AbstractButton button = buttons.nextElement();
                if (button.isSelected()) {
                    System.out.println("Botów : " + button.getText());
                    bots = Integer.parseInt(button.getText());
                }
            }

            if(players>=bots+1) {
                System.out.println("WYBRANO GRACZY: " + players + " BOTÓW: "+ bots);
                frame.dispose();
                GameServer gameServer = new GameServer(players, bots, new StandardGameMode());
                try {
                    gameServer.start();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }
}

package com.chinesecheckers.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Player extends Thread {
    private String nick;
    private Socket socket;
    private GameServer server;
    private BufferedReader input;
    private PrintWriter output;


    public Player(Socket socket, GameServer server) {
        this.socket = socket;
        this.server = server;
    }

    public Player(String nick, Socket socket, GameServer server) {
        this.nick = nick;
        this.socket = socket;
        this.server = server;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public void run() {
        System.out.println("Player Thread started");
        String wiadomosc;

        try {
            input = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);
            output.println("WELCOME");
            output.println("MESSAGE Waiting for opponents to connect");

            sendToClient("INFO "+server.getNumOfPlayers()+" "+server.getNumOfBots(),this);

            while ((wiadomosc = input.readLine()) != null) {
                if(wiadomosc.startsWith("MOVE"))
                {
                    String[] x = wiadomosc.split(" ");
                    int x1 = Integer.parseInt(x[1]);
                    int y1 = Integer.parseInt(x[2]);

                    sendToEveryone(wiadomosc);
                }
                else if(wiadomosc.startsWith("ENDTURN"))
                {
                    System.out.println("KONIEC TURY");
                    changeTurn();
                    sendTurnMessage();
                }

                System.out.println("Odczytano: " + wiadomosc);
            }
        } catch(Exception ex) {ex.printStackTrace();}

    }

    public void sendToClient(String message,Player player)
    {
        try
        {
            PrintWriter pis = player.getOutputStream();
            pis.println(message);
            pis.flush();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public void sendTurnMessage()
    {
        for(int index = 0; index<server.getPlayers().size();index++)
        {
            Player p = server.getPlayers().get(index);
            if(index == server.getTurn())
                sendToClient("YOUR TURN",p);

            else
                sendToClient("NOT YOUR TURN",p);
        }
    }


    public void sendToEveryone(String message) {
        for(Player p: server.getPlayers())
        {
            sendToClient(message,p);
        }
    }

    public void changeTurn()
    {
        System.out.println("TURNCHANGING");
        server.setTurn((server.getTurn()+1)%(server.getPlayers().size()));
    }


    public PrintWriter getOutputStream()
    {
        return output;
    }

}

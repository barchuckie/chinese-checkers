package com.chinesecheckers.server;

import com.chinesecheckers.client.PlayWindow;

import javax.security.auth.RefreshFailedException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

import static java.lang.Thread.sleep;

public class GameServer {

    enum GameServerState {
        FREE,
        WAITING,
        INGAME
    }
    int numOfPlayers;
    int numOfBots;
    private GameServerState state;
    private ServerSocket listener;
    private ArrayList<Player> players = new ArrayList<Player>();
    int turn=0;

    public GameServer(int numOfPlayers,int numOfBots)
    {
        this.numOfPlayers=numOfPlayers;
        this.numOfBots=numOfBots;
    }

    public void start() {
        System.out.println("Chinese Checkers Server is Running");
        try{
            listener = new ServerSocket(8901);
            while (true) {
                // TODO: Server lifecycle
                if(players.size()<numOfPlayers)
                {
                    Socket s = listener.accept();
                    Player pl = new Player(s, this);
                    players.add(pl);
                    System.out.println(players.size());
                }
                else if(players.size()==numOfPlayers)
                {
                    startAllThreads();
                    chooseFirstPlayer();
                    try
                    {
                        sleep(100);
                    }catch(InterruptedException ex)
                    {
                        ex.printStackTrace();
                    }
                    players.get(turn).sendTurnMessage();
                    break;
                }else
                {
                    System.out.println("I'm sorry. Room is full");
                }

            }
        } catch(IOException ex)
        {
            ex.printStackTrace();
        }
    }

    public void startAllThreads()
    {
        for(Player p: players)
        {
            p.start();
        }
    }

    public void sendGameInfo()
    {
        for(Player p: players)
        {
            p.sendToClient("INFO "+numOfPlayers+" "+numOfBots,p);
        }
    }

    public void chooseFirstPlayer()
    {
        Random r = new Random();
        turn = r.nextInt(numOfPlayers);
        System.out.println("Gracz nr "+turn+"bedzie zaczynal");
    }

    GameServerState getState() {
        return state;
    }

    public ArrayList<Player> getPlayers()
    {
        return players;
    }

    public int getTurn()
    {
        return turn;
    }

    public void setTurn(int turn)
    {
        this.turn = turn;
    }

    public int getNumOfPlayers()
    {
        return numOfPlayers;
    }

    public int getNumOfBots()
    {
        return numOfBots;
    }
}

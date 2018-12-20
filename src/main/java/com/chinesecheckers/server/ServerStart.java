package com.chinesecheckers.server;

public class ServerStart {
    public static void main(String[] args)
    {
        try
        {
            GameServer gameServer = new GameServer();
            gameServer.start();
        }catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}

package com.chinesecheckers.server;

public class ServerStart {
    public static void main(String[] args)
    {
        try
        {
            ServerStartWindow gameServer = new ServerStartWindow();
            gameServer.start();
        }catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}

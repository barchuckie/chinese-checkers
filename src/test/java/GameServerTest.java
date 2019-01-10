import com.chinesecheckers.client.GameClient;
import com.chinesecheckers.server.GameServer;
import com.chinesecheckers.server.player.Player;
import com.chinesecheckers.server.game.StandardGame.StandardGameMode;

import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import static java.lang.Thread.sleep;
import static org.mockito.Mockito.mock;

public class GameServerTest {

    @Test
    public void testServerLifecycle() {
        Thread serverThread = new Thread(new ServerRunner());
        serverThread.start();
        GameServer server = new GameServer(2, 1, new StandardGameMode());
        try {
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private GameClient createGameClient() {
        return mock(GameClient.class);
    }
    class ServerRunner implements Runnable {

        @Override
        public void run() {
            try {
                sleep(1000);
                Socket listener = new Socket("127.0.0.1", 8901);
                InputStreamReader streamReader = new InputStreamReader(listener.getInputStream());
                BufferedReader reader = new BufferedReader(streamReader);
                PrintWriter writer = new PrintWriter(listener.getOutputStream(),true);
                writer.println("NICK dummy");
                Assert.assertEquals("YOURID 1", reader.readLine());
                Assert.assertEquals("GAME STANDARD 2", reader.readLine());
                if(!reader.readLine().startsWith("YOURMOVE")) {
                    reader.readLine();
                }
                writer.println("MOVE 0 12 0 12");
                //reader.readLine();
                Assert.assertEquals("YOURMOVE", reader.readLine());
                writer.println("CHECK 0 12 4 16");
                Assert.assertTrue(reader.readLine().startsWith("DECLINE"));
                Assert.assertTrue(reader.readLine().startsWith("YOURMOVE"));
                writer.println("CHECK 3 9 4 10");
                Assert.assertTrue(reader.readLine().startsWith("ACCEPT"));
                Assert.assertTrue(reader.readLine().startsWith("YOURMOVE"));
                writer.println("MOVE 3 9 4 10");
                Assert.assertEquals("ENDMOVE", reader.readLine());
                reader.readLine();
                writer.println("PASS 0 12 0 12");
                //Assert.assertEquals("PASS", reader.readLine());
                //reader.readLine();
                Assert.assertEquals("YOURMOVE", reader.readLine());
                listener.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}

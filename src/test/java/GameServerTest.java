import com.chinesecheckers.server.GameModeEnum;
import com.chinesecheckers.server.GameServer;
import org.junit.Test;
import org.mockito.Mock;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GameServerTest {

    @Test
    public void mockPatrykClient() {
        try {
            Socket socket = new Socket("localhost", 8901);
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            output.println("NICK patryk");

            System.out.println(input.readLine());
            System.out.println(input.readLine());
        } catch (Exception ignored) {}
    }

    @Test
    public void mockKubaClient() {
        try {
            Socket socket = new Socket("localhost", 8901);
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            output.println("NICK kuba");

            System.out.println(input.readLine());
            System.out.println(input.readLine());
            System.out.println(input.readLine());
        } catch (Exception ignored) {}
    }

    @Test
    public void testGameServer() {
        GameServer gameServer = new GameServer(2, 0, GameModeEnum.STANDARD);
        try {
            gameServer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

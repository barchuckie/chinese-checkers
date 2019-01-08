import com.chinesecheckers.client.GameClient;
import com.chinesecheckers.server.GameServer;
import com.chinesecheckers.server.Player;
import com.chinesecheckers.server.game.StandardGame.StandardGameMode;

import org.junit.Test;

import static org.mockito.Mockito.mock;

public class GameServerTest {

    @Test
    public void testServerLifecycle() {
        GameServer server = new GameServer(2, 0, new StandardGameMode());
    }

    private GameClient createGameClient() {
        return mock(GameClient.class);
    }

    private Player createPlayer() {
        return mock(Player.class);
    }

}

import com.chinesecheckers.server.Player;
import com.chinesecheckers.server.game.Game;
import com.chinesecheckers.server.game.GameData;
import com.chinesecheckers.server.game.StandardGame;
import org.junit.Test;
import org.junit.Assert;

public class GameTest extends ChineseCheckersTest {
    @Test
    public void testValidateMove() {
        String[] nicks = { "pat", "mat"};
        GameData data = createDummy2PlayerGameData(nicks);
        Player [] players = data.getPlayers();

        Game game = new StandardGame(data);
        int currentPlayer = game.getCurrentPlayer();

        if(currentPlayer == 0) {
            Assert.assertTrue(game.validateMove(players[0], 11, 3, 10, 4));
            Assert.assertFalse(game.validateMove(players[0], 11, 3, 10, 2));
        } else {
            Assert.assertTrue(game.validateMove(players[1], 11, 13, 10, 12));
        }

    }
}

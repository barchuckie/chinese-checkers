import com.chinesecheckers.server.Player;
import com.chinesecheckers.server.board.Board;
import com.chinesecheckers.server.board.Field;
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
            printBoard(game.getBoard(), nicks);

            Assert.assertTrue(game.validateMove(players[1], 11, 13, 10, 12));
            game.makeMove(players[1], 11, 13, 10, 12);
            printBoard(game.getBoard(), nicks);

            Assert.assertFalse(game.validateMove(players[0], 12, 2, 13, 1));
            Assert.assertTrue(game.validateMove(players[0], 15, 3, 16, 4));
            game.makeMove(players[0], 15, 3, 16, 4);
            printBoard(game.getBoard(), nicks);

            Assert.assertTrue(game.validateMove(players[1], 13, 13, 14, 12));
            game.makeMove(players[1], 13, 13, 14, 12);
            printBoard(game.getBoard(), nicks);

            Assert.assertFalse(game.validateMove(players[0], 14, 2, 14, 4));
            printBoard(game.getBoard(), nicks);
        } else {
            Assert.assertTrue(game.validateMove(players[1], 11, 13, 10, 12));
            game.makeMove(players[1], 11, 13, 10, 12);
        }

    }

    private void printBoard(Board board, String[] nicks) {
        Field [][] fields = board.getFields();
        for (Field[] field1 : fields) {
            for (Field field : field1) {
                if (field != null) {
                    if (field.getPlayer() == null) {
                        System.out.print('*');
                    } else if (field.getPlayer().getNick().equals(nicks[0])) {
                        System.out.print('P');
                    } else if (field.getPlayer().getNick().equals(nicks[1])) {
                        System.out.print('M');
                    }
                } else {
                    System.out.print(' ');
                }
            }
            System.out.println();
        }
    }
}

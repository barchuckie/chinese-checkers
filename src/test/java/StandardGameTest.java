import com.chinesecheckers.server.Player;
import com.chinesecheckers.server.board.Board;
import com.chinesecheckers.server.board.Field;
import com.chinesecheckers.server.game.Game;
import com.chinesecheckers.server.game.GameData;
import com.chinesecheckers.server.game.StandardGame;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;


public class StandardGameTest extends ChineseCheckersTest {
    private int currentPlayer;
    private Game game;
    private Player [] players;

    private void prepareGame() {
        String[] nicks = { "pat", "mat"};
        GameData data = createDummy2PlayerGameData(nicks);
        players = data.getPlayers();

        game = new StandardGame(data);
        currentPlayer = game.getCurrentPlayer();
    }

    @Test
    public void testValidateSimpleMove() {
        prepareGame();
        if(currentPlayer == 0) {
            Assert.assertTrue(game.validateMove(players[0], 11, 3, 10, 4));
        } else {
            Assert.assertTrue(game.validateMove(players[1], 11, 13, 10, 12));
        }
    }

    @Test
    public void testValidateJumpMove() {
        prepareGame();
        if(currentPlayer == 0) {
            Assert.assertTrue(game.validateMove(players[0], 12, 2, 10, 4));
        } else {
            Assert.assertTrue(game.validateMove(players[1], 12, 14, 10, 12));
        }
    }

    @Test
    public void testValidateMoveNotCurrentPlayer() {
        prepareGame();
        if(currentPlayer == 1) {
            Assert.assertFalse(game.validateMove(players[0], 11, 3, 10, 4));
        } else {
            Assert.assertFalse(game.validateMove(players[1], 11, 13, 10, 12));
        }
    }

    @Test
    public void testValidateMoveWrongPlayer() {
        prepareGame();
        if(currentPlayer == 0) {
            Assert.assertFalse(game.validateMove(players[0], 11, 13, 10, 12));
        } else {
            Assert.assertFalse(game.validateMove(players[1], 11, 3, 10, 4));
        }
    }

    @Test
    public void testValidateMoveOnTakenField() {
        prepareGame();
        if(currentPlayer == 0) {
            game.makeMove(players[0], 11, 3, 10, 12);
            Assert.assertFalse(game.validateMove(players[1], 11, 13, 10, 12));
        } else {
            game.makeMove(players[1], 11, 13, 10, 4);
            Assert.assertFalse(game.validateMove(players[0], 11, 3, 10, 4));
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

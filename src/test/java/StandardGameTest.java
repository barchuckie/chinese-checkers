import com.chinesecheckers.server.Player;
import com.chinesecheckers.server.game.Game;
import com.chinesecheckers.server.game.GameData;
import com.chinesecheckers.server.game.StandardGame.StandardGame;

import org.junit.Test;
import org.junit.Assert;


public class StandardGameTest extends ChineseCheckersTest {
    private int currentPlayer;
    private Game game;
    private Player [] players;

    private void prepareGame() {
        GameData data = createDummy2PlayerGameData();
        players = data.getPlayers();

        game = new StandardGame(data);
        currentPlayer = game.getCurrentPlayer();
    }

    @Test
    public void testValidateSimpleMove() {
        prepareGame();
        if(currentPlayer == 0) {
            Assert.assertTrue(game.validateMove(players[0], 3, 11, 4, 10));
        } else {
            Assert.assertTrue(game.validateMove(players[1], 13, 11, 12, 10));
        }
    }

    @Test
    public void testValidateJumpMove() {
        prepareGame();
        if(currentPlayer == 0) {
            Assert.assertTrue(game.validateMove(players[0], 2, 12, 4, 10));
        } else {
            Assert.assertTrue(game.validateMove(players[1], 14, 12, 12, 10));
        }
    }

    @Test
    public void testValidateMoveNotCurrentPlayer() {
        prepareGame();
        if(currentPlayer == 1) {
            Assert.assertFalse(game.validateMove(players[0], 3, 11, 4, 10));
        } else {
            Assert.assertFalse(game.validateMove(players[1], 13, 11, 12, 10));
        }
    }

    @Test
    public void testValidateMoveWrongPlayer() {
        prepareGame();
        if(currentPlayer == 0) {
            Assert.assertFalse(game.validateMove(players[0], 13, 11, 12, 10));
        } else {
            Assert.assertFalse(game.validateMove(players[1], 3, 11, 4, 10));
        }
    }

    @Test
    public void testValidateMoveOnTakenField() {
        prepareGame();
        if(currentPlayer == 0) {
            game.makeMove(players[0], 3, 11, 12, 10);
            game.nextTurn();
            Assert.assertFalse(game.validateMove(players[1], 13, 11, 12, 10));
        } else {
            game.makeMove(players[1], 13, 11, 4, 10);
            game.nextTurn();
            Assert.assertFalse(game.validateMove(players[0], 3, 11, 4, 10));
        }
    }

    @Test
    public void testCheckWinner() {
        prepareGame();
    }
}

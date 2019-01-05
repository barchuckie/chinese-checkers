import com.chinesecheckers.server.Player;
import com.chinesecheckers.server.board.Field;
import com.chinesecheckers.server.game.Game;
import com.chinesecheckers.server.game.GameData;
import com.chinesecheckers.server.game.GameMode;
import com.chinesecheckers.server.game.StandardGame.StandardGame;

import com.chinesecheckers.server.game.StandardGame.StandardGameMode;
import org.junit.Test;
import org.junit.Assert;


public class StandardGameTest extends ChineseCheckersTest {
    private int currentPlayer;
    private Game game;
    private Player [] players;

    private void prepareGame() {
        GameData data = createDummy2PlayerGameData();
        GameMode mode = new StandardGameMode();
        players = data.getPlayers();

        game = mode.generateGame(data);
        Assert.assertEquals("STANDARD", mode.getName());
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
    public void testValidateBackMove() {
        prepareGame();
        if(currentPlayer == 0) {
            Assert.assertTrue(game.validateMove(players[0], 3, 11, 4, 10));
            game.makeMove(players[0], 3, 11, 4, 10);
            Assert.assertTrue(game.validateMove(players[0],  4, 10, 3, 11));
        } else {
            Assert.assertTrue(game.validateMove(players[1], 13, 11, 12, 10));
            game.makeMove(players[1], 13, 11, 12, 10);
            Assert.assertTrue(game.validateMove(players[1], 12, 10, 13, 11));
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
    public void testValidateWrongMove() {
        prepareGame();
        if(currentPlayer == 0) {
            Assert.assertFalse(game.validateMove(players[0], 3, 11, 5, 9));
        } else {
            Assert.assertFalse(game.validateMove(players[1], 13, 11, 11, 9));
        }
    }

    @Test
    public void testCheckWinnerIn2PlayerGame() {
        prepareGame();
        Assert.assertFalse(game.checkWinner(0));
        Assert.assertFalse(game.checkWinner(1));
        for(int i = 0; i < 4; ++i) {
            for(Field field : game.getBoard().getFields()[i]) {
                if(field != null) {
                    field.setPlayer(players[1]);
                }
            }
        }
        Assert.assertFalse(game.checkWinner(0));
        Assert.assertTrue(game.checkWinner(1));
        for(int i = 16; i > 12; --i) {
            for(Field field : game.getBoard().getFields()[i]) {
                if(field != null) {
                    field.setPlayer(players[0]);
                }
            }
        }
        Assert.assertTrue(game.checkWinner(0));
        Assert.assertTrue(game.checkWinner(1));

        Assert.assertFalse(game.checkWinner(2));
    }

    @Test
    public void testCheckWinnerIn3PlayerGame() {
        GameData data = createDummy3PlayerGameData();
        players = data.getPlayers();

        game = new StandardGame(data);
        currentPlayer = game.getCurrentPlayer();
        Field[][] fields = game.getBoard().getFields();

        Assert.assertFalse(game.checkWinner(0));
        Assert.assertFalse(game.checkWinner(1));
        Assert.assertFalse(game.checkWinner(2));

        for(int i = 4; i < 9; ++i) {
            for(int j = 0; j < 11-i; ++j) {
                Field field = fields[i][j];
                if(field != null) {
                    field.setPlayer(players[1]);
                }
            }
        }

        Assert.assertFalse(game.checkWinner(0));
        Assert.assertTrue(game.checkWinner(1));
        Assert.assertFalse(game.checkWinner(2));

        for(int i = 16; i > 12; --i) {
            for(Field field : game.getBoard().getFields()[i]) {
                if(field != null) {
                    field.setPlayer(players[0]);
                }
            }
        }
        Assert.assertTrue(game.checkWinner(0));
        Assert.assertTrue(game.checkWinner(1));
        Assert.assertFalse(game.checkWinner(2));

        for(int i = 4; i < 9; ++i) {
            for(int j = 0; j < 11-i; ++j) {
                Field field = fields[i][24-j];
                if(field != null) {
                    field.setPlayer(players[2]);
                }
            }
        }
        Assert.assertTrue(game.checkWinner(0));
        Assert.assertTrue(game.checkWinner(1));
        Assert.assertTrue(game.checkWinner(2));

        Assert.assertFalse(game.checkWinner(3));
    }

    @Test
    public void testCheckWinnerIn4PlayerGame() {
        GameData data = createDummy4PlayerGameData();
        players = data.getPlayers();

        game = new StandardGame(data);
        currentPlayer = game.getCurrentPlayer();
        Field[][] fields = game.getBoard().getFields();

        Assert.assertFalse(game.checkWinner(0));
        Assert.assertFalse(game.checkWinner(1));
        Assert.assertFalse(game.checkWinner(2));
        Assert.assertFalse(game.checkWinner(3));

        for(int i = 4; i < 9; ++i) {
            for(int j = 0; j < 11-i; ++j) {
                Field field = fields[16-i][j];
                if(field != null) {
                    field.setPlayer(players[0]);
                }
            }
        }

        Assert.assertTrue(game.checkWinner(0));
        Assert.assertFalse(game.checkWinner(1));
        Assert.assertFalse(game.checkWinner(2));
        Assert.assertFalse(game.checkWinner(3));

        for(int i = 4; i < 9; ++i) {
            for(int j = 0; j < 11-i; ++j) {
                Field field = fields[i][j];
                if(field != null) {
                    field.setPlayer(players[1]);
                }
            }
        }

        Assert.assertTrue(game.checkWinner(0));
        Assert.assertTrue(game.checkWinner(1));
        Assert.assertFalse(game.checkWinner(2));
        Assert.assertFalse(game.checkWinner(3));

        for(int i = 4; i < 9; ++i) {
            for(int j = 0; j < 11-i; ++j) {
                Field field = fields[i][24-j];
                if(field != null) {
                    field.setPlayer(players[2]);
                }

            }
        }

        Assert.assertTrue(game.checkWinner(0));
        Assert.assertTrue(game.checkWinner(1));
        Assert.assertTrue(game.checkWinner(2));
        Assert.assertFalse(game.checkWinner(3));

        for(int i = 4; i < 9; ++i) {
            for(int j = 0; j < 11-i; ++j) {
                Field field = fields[16-i][24-j];
                if(field != null) {
                    field.setPlayer(players[3]);
                }
            }
        }

        Assert.assertTrue(game.checkWinner(0));
        Assert.assertTrue(game.checkWinner(1));
        Assert.assertTrue(game.checkWinner(2));
        Assert.assertTrue(game.checkWinner(3));

        Assert.assertFalse(game.checkWinner(4));
    }

    @Test
    public void testCheckWinnerIn6PlayerGame() {
        GameData data = createDummy6PlayerGameData();
        players = data.getPlayers();

        game = new StandardGame(data);
        currentPlayer = game.getCurrentPlayer();
        Field[][] fields = game.getBoard().getFields();

        Assert.assertFalse(game.checkWinner(0));
        Assert.assertFalse(game.checkWinner(1));
        Assert.assertFalse(game.checkWinner(2));
        Assert.assertFalse(game.checkWinner(3));
        Assert.assertFalse(game.checkWinner(4));
        Assert.assertFalse(game.checkWinner(5));

        for(int i = 16; i > 12; --i) {
            for(Field field : game.getBoard().getFields()[i]) {
                if(field != null) {
                    field.setPlayer(players[0]);
                }
            }
        }

        Assert.assertTrue(game.checkWinner(0));
        Assert.assertFalse(game.checkWinner(1));
        Assert.assertFalse(game.checkWinner(2));
        Assert.assertFalse(game.checkWinner(3));
        Assert.assertFalse(game.checkWinner(4));
        Assert.assertFalse(game.checkWinner(5));

        for(int i = 4; i < 9; ++i) {
            for(int j = 0; j < 11-i; ++j) {
                Field field = fields[16-i][j];
                if(field != null) {
                    field.setPlayer(players[1]);
                }
            }
        }

        Assert.assertTrue(game.checkWinner(0));
        Assert.assertTrue(game.checkWinner(1));
        Assert.assertFalse(game.checkWinner(2));
        Assert.assertFalse(game.checkWinner(3));
        Assert.assertFalse(game.checkWinner(4));
        Assert.assertFalse(game.checkWinner(5));

        for(int i = 4; i < 9; ++i) {
            for(int j = 0; j < 11-i; ++j) {
                Field field = fields[i][j];
                if(field != null) {
                    field.setPlayer(players[2]);
                }
            }
        }

        Assert.assertTrue(game.checkWinner(0));
        Assert.assertTrue(game.checkWinner(1));
        Assert.assertTrue(game.checkWinner(2));
        Assert.assertFalse(game.checkWinner(3));
        Assert.assertFalse(game.checkWinner(4));
        Assert.assertFalse(game.checkWinner(5));

        for(int i = 0; i < 4; ++i) {
            for(Field field : game.getBoard().getFields()[i]) {
                if(field != null) {
                    field.setPlayer(players[3]);
                }
            }
        }

        Assert.assertTrue(game.checkWinner(0));
        Assert.assertTrue(game.checkWinner(1));
        Assert.assertTrue(game.checkWinner(2));
        Assert.assertTrue(game.checkWinner(3));
        Assert.assertFalse(game.checkWinner(4));
        Assert.assertFalse(game.checkWinner(5));

        for(int i = 4; i < 9; ++i) {
            for(int j = 0; j < 11-i; ++j) {
                Field field = fields[i][24-j];
                if(field != null) {
                    field.setPlayer(players[4]);
                }
            }
        }

        Assert.assertTrue(game.checkWinner(0));
        Assert.assertTrue(game.checkWinner(1));
        Assert.assertTrue(game.checkWinner(2));
        Assert.assertTrue(game.checkWinner(3));
        Assert.assertTrue(game.checkWinner(4));
        Assert.assertFalse(game.checkWinner(5));

        for(int i = 4; i < 9; ++i) {
            for(int j = 0; j < 11-i; ++j) {
                Field field = fields[16-i][24-j];
                if(field != null) {
                    field.setPlayer(players[5]);
                }
            }
        }

        Assert.assertTrue(game.checkWinner(0));
        Assert.assertTrue(game.checkWinner(1));
        Assert.assertTrue(game.checkWinner(2));
        Assert.assertTrue(game.checkWinner(3));
        Assert.assertTrue(game.checkWinner(4));
        Assert.assertTrue(game.checkWinner(5));

        Assert.assertFalse(game.checkWinner(7));
    }
}

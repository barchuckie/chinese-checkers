import com.chinesecheckers.server.GameServer;
import com.chinesecheckers.server.board.Board;
import com.chinesecheckers.server.board.BoardGenerator;
import com.chinesecheckers.server.board.Field;
import com.chinesecheckers.server.board.StandardBoard.StandardBoard;
import com.chinesecheckers.server.board.StandardBoard.StandardBoardGenerator;
import com.chinesecheckers.server.game.Game;
import com.chinesecheckers.server.game.GameData;

import com.chinesecheckers.server.game.GameMode;
import com.chinesecheckers.server.game.StandardGame.StandardGame;
import com.chinesecheckers.server.game.StandardGame.StandardGameMode;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class StandardBoardGeneratorTest extends ChineseCheckersTest {

    @Test
    public void test2PlayerStandardBoardCreating() {
        GameData data = createDummy2PlayerGameData();
        BoardGenerator standardBoardGenerator = new StandardBoardGenerator();
        Board standardBoard = standardBoardGenerator.generateBoard(data);

        printBoard(standardBoard);
    }

    @Test
    public void test3PlayerStandardBoardCreating() {
        GameData data = createDummy3PlayerGameData();
        BoardGenerator standardBoardGenerator = new StandardBoardGenerator();
        Board standardBoard = standardBoardGenerator.generateBoard(data);

        printBoard(standardBoard);
    }

    @Test
    public void test4PlayerStandardBoardCreating() {
        GameData data = createDummy4PlayerGameData();
        BoardGenerator standardBoardGenerator = new StandardBoardGenerator();
        Board standardBoard = standardBoardGenerator.generateBoard(data);

        printBoard(standardBoard);
    }

    @Test
    public void test6PlayerStandardBoardCreating() {
        GameData data = createDummy6PlayerGameData();
        BoardGenerator standardBoardGenerator = new StandardBoardGenerator();
        Board standardBoard = standardBoardGenerator.generateBoard(data);

        printBoard(standardBoard);
    }
}

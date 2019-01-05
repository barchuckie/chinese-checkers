import com.chinesecheckers.server.board.Board;
import com.chinesecheckers.server.board.BoardGenerator;
import com.chinesecheckers.server.board.StandardBoard.StandardBoardGenerator;
import com.chinesecheckers.server.game.GameData;

import org.junit.Test;

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

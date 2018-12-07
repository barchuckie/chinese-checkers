import com.chinesecheckers.server.board.Board;
import com.chinesecheckers.server.board.BoardGenerator;
import com.chinesecheckers.server.board.Field;
import com.chinesecheckers.server.board.StandardBoard.StandardBoard;
import com.chinesecheckers.server.board.StandardBoard.StandardBoardGenerator;
import org.junit.Test;

public class StandardBoardGeneratorTest {
    @Test
    public void testStandardBoardCreating() {
        int numOfPlayers = 2;

        BoardGenerator standardBoardGenerator = new StandardBoardGenerator();
        Board standardBoard = standardBoardGenerator.generateBoard(numOfPlayers);
        Field[][] fields = standardBoard.getFields();

        for(int i  = 0; i < fields.length; i++) {
            for (Field field : fields[i]) {
                if(field.getIsActive()) {
                    System.out.print('X');
                } else {
                    System.out.print(' ');
                }
            }
            System.out.println();
        }
    }
}

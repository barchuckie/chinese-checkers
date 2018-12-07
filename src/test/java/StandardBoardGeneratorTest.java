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
        System.out.println(fields.length);
        int pola = 0;
        System.out.println(((StandardBoard) standardBoard).f);

        for(int i  = 0; i < fields.length; i++) {
            for (int j = 0; j < fields[i].length; j++) {
                if(fields[i][j].getIsActive()) {
                    System.out.print('X');
                    pola++;
                } else {
                    System.out.print('x');
                }
                System.out.println();
            }
        }
        System.out.println(pola);
    }
}

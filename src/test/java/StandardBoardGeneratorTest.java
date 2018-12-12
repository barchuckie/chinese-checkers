import com.chinesecheckers.server.*;
import com.chinesecheckers.server.board.Board;
import com.chinesecheckers.server.board.BoardGenerator;
import com.chinesecheckers.server.board.Field;
import com.chinesecheckers.server.board.StandardBoard.StandardBoardGenerator;
import com.chinesecheckers.server.game.GameData;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.net.Socket;

public class StandardBoardGeneratorTest extends ChineseCheckersTest {

    /*
    * Run server before
    */

    @Test
    public void testStandardBoardCreating() {
        String[] nicks = { "pat", "mat", "sat"};
        GameData data = createDummy3PlayerGameData(nicks);
        BoardGenerator standardBoardGenerator = new StandardBoardGenerator();
        Board standardBoard = standardBoardGenerator.generateBoard(data);
        Field[][] fields = standardBoard.getFields();

        for (int i = 0; i < fields.length; i++) {
            for (Field field : fields[i]) {
                if (field != null) {
                    if (field.getPlayer() == null) {
                        System.out.print('*');
                    } else if (field.getPlayer().getNick().equals(nicks[0])) {
                        System.out.print('P');
                    } else if (field.getPlayer().getNick().equals(nicks[1])) {
                        System.out.print('M');
                    } else if (field.getPlayer().getNick().equals(nicks[2])) {
                        System.out.print('S');
                    }
                } else {
                    System.out.print(' ');
                }
            }
            System.out.println();
        }
    }
}

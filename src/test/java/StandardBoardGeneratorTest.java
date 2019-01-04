import com.chinesecheckers.server.board.Board;
import com.chinesecheckers.server.board.BoardGenerator;
import com.chinesecheckers.server.board.Field;
import com.chinesecheckers.server.board.StandardBoard.StandardBoardGenerator;
import com.chinesecheckers.server.game.GameData;

import org.junit.Test;

public class StandardBoardGeneratorTest extends ChineseCheckersTest {

    @Test
    public void test2PlayerStandardBoardCreating() {
        String[] nicks = { "pat", "mat" };
        GameData data = createDummy2PlayerGameData(nicks);
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
                    }
                } else {
                    System.out.print(' ');
                }
            }
            System.out.println();
        }
    }

    @Test
    public void test3PlayerStandardBoardCreating() {
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

    @Test
    public void test4PlayerStandardBoardCreating() {
        String[] nicks = { "pat", "mat", "sat", "war" };
        GameData data = createDummy4PlayerGameData(nicks);
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
                    } else if (field.getPlayer().getNick().equals(nicks[3])) {
                        System.out.print('W');
                    }
                } else {
                    System.out.print(' ');
                }
            }
            System.out.println();
        }
    }

    @Test
    public void test6PlayerStandardBoardCreating() {
        String[] nicks = { "pat", "mat", "sat", "war", "jar", "fer" };
        GameData data = createDummy6PlayerGameData(nicks);
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
                    } else if (field.getPlayer().getNick().equals(nicks[3])) {
                        System.out.print('W');
                    } else if (field.getPlayer().getNick().equals(nicks[4])) {
                        System.out.print('J');
                    } else if (field.getPlayer().getNick().equals(nicks[5])) {
                        System.out.print('F');
                    }
                } else {
                    System.out.print(' ');
                }
            }
            System.out.println();
        }
    }
}

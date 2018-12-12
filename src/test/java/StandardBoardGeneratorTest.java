import com.chinesecheckers.server.*;
import com.chinesecheckers.server.board.Board;
import com.chinesecheckers.server.board.BoardGenerator;
import com.chinesecheckers.server.board.Field;
import com.chinesecheckers.server.board.StandardBoard.StandardBoardGenerator;
import com.chinesecheckers.server.game.GameData;
import org.junit.Test;

import java.io.IOException;
import java.net.Socket;

public class StandardBoardGeneratorTest {
    @Test
    public void testStandardBoardCreating() {
        try
        {
            int numOfPlayers = 3;
            Player player1 = new Player("pat", new Socket("127.0.0.1", 8901));
            Player player2 = new Player("mat", new Socket("127.0.0.1", 8901));
            Player player3 = new Player("sat", new Socket("127.0.0.1", 8901));

            Player[] players = {player1, player2, player3};

            GameData data = new GameData(numOfPlayers, players);

            BoardGenerator standardBoardGenerator = new StandardBoardGenerator();
            Board standardBoard = standardBoardGenerator.generateBoard(data);
            Field[][] fields = standardBoard.getFields();

            for (int i = 0; i < fields.length; i++)
            {
                for (Field field : fields[i])
                {
                    if (field != null)
                    {
                        if (field.getPlayer() == null)
                        {
                            System.out.print('*');
                        }
                        else if (field.getPlayer().getNick().equals("pat"))
                        {
                            System.out.print('P');
                        }
                        else if (field.getPlayer().getNick().equals("mat"))
                        {
                            System.out.print('M');
                        }
                        else if (field.getPlayer().getNick().equals("sat"))
                        {
                            System.out.print('S');
                        }
                    }
                    else
                    {
                        System.out.print(' ');
                    }
                }
                System.out.println();
            }
        }catch(IOException ex)
        {
            ex.printStackTrace();
        }
    }
}


import com.chinesecheckers.client.Board.*;
import com.chinesecheckers.client.Field;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.chinesecheckers.client.Board.*;
import com.chinesecheckers.client.Field;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class HexBoardFactoryTest extends HexBoardFactory {

    private BoardFactory hexBoardFactory;
    private Board hexBoardTwoPlayers;
    private Board hexBoardThreePlayers;
    private Board hexBoardFourPlayers;
    private Board hexBoardSixPlayers;
    private Field[][] fields;

    @Before
    public void prepareBoards()
    {
        hexBoardFactory = new HexBoardFactory();
        this.hexBoardTwoPlayers = hexBoardFactory.getBoard(2, "STANDARD");
        this.hexBoardThreePlayers = hexBoardFactory.getBoard(3, "STANDARD");
        this.hexBoardFourPlayers = hexBoardFactory.getBoard(4, "STANDARD");
        this.hexBoardSixPlayers = hexBoardFactory.getBoard(6, "STANDARD");
    }

    @Test
    public void testCreateBoard()
    {
        Assert.assertTrue(hexBoardTwoPlayers instanceof HexBoardTwoPlayers);
        Assert.assertTrue(hexBoardThreePlayers instanceof HexBoardThreePlayers);
        Assert.assertTrue(hexBoardFourPlayers instanceof HexBoardFourPlayers);
        Assert.assertTrue(hexBoardSixPlayers instanceof HexBoardSixPlayers);
        Assert.assertNull(hexBoardFactory.createBoard(-2));
    }

    @Test
    public void testAddPlayers()
    {
        //TWO PLAYERS
        fields = new Field[17][25];
        hexBoardTwoPlayers.addPlayers();
        for (int i = 0; i < 4; ++i)
        {
            //gora
            for (Field field : fields[i])
            {
                if (field != null)
                {
                    Assert.assertEquals(field.getPlayer(), 1);
                }
            }
            //dol
            for (Field field : fields[16 - i])
            {
                if (field != null)
                {
                    Assert.assertEquals(field.getPlayer(), 2);
                }
            }
        }

        //THREE PLAYERS
        fields = new Field[17][25];
        hexBoardThreePlayers.addPlayers();
        for (int i = 0; i < 4; ++i)
        {
            for (Field field : fields[i])
            {
                if (field != null)
                {
                    Assert.assertEquals(field.getPlayer(), 1);
                }
            }
        }
        for (int i = 4; i < 9; ++i)
        {
            for (int j = 0; j < 11 - i; ++j)
            {
                Field field = fields[16 - i][j];
                if (field != null)
                {
                    Assert.assertEquals(field.getPlayer(), 3);
                }
                field = fields[16 - i][24 - j];
                if (field != null)
                {
                    Assert.assertEquals(field.getPlayer(), 2);
                }
            }
        }

        //FOUR PLAYERS
        fields = new Field[17][25];
        hexBoardFourPlayers.addPlayers();
        for (int i = 4; i < 9; ++i)
        {
            for (int j = 0; j < 11 - i; ++j)
            {
                Field field = fields[i][j];
                if (field != null)
                {
                    Assert.assertEquals(field.getPlayer(), 4);
                }
                field = fields[i][24 - j];
                if (field != null)
                {
                    Assert.assertEquals(field.getPlayer(), 1);
                }
                field = fields[16 - i][j];
                if (field != null)
                {
                    Assert.assertEquals(field.getPlayer(), 3);
                }
                field = fields[16 - i][24 - j];
                if (field != null)
                {
                    Assert.assertEquals(field.getPlayer(), 2);
                }
            }
        }

        //SIXPLAYERS
        fields = new Field[17][25];
        hexBoardSixPlayers.addPlayers();
        for (int i = 0; i < 4; ++i)
        {
            for (Field field : fields[i])
            {
                if (field != null)
                {
                    Assert.assertEquals(field.getPlayer(), 1);
                }
            }
            for (Field field : fields[16 - i])
            {
                if (field != null)
                {
                    Assert.assertEquals(field.getPlayer(), 4);
                }
            }
        }

        for (int i = 4; i < 9; ++i)
        {
            for (int j = 0; j < 11 - i; ++j)
            {
                Field field = fields[i][j];
                if (field != null)
                {
                    Assert.assertEquals(field.getPlayer(), 6);
                }
                field = fields[i][24 - j];
                if (field != null)
                {
                    Assert.assertEquals(field.getPlayer(), 2);
                }
                field = fields[16 - i][j];
                if (field != null)
                {
                    Assert.assertEquals(field.getPlayer(), 5);
                }
                field = fields[16 - i][24 - j];
                if (field != null)
                {
                    Assert.assertEquals(field.getPlayer(), 3);
                }
            }
        }
    }

}
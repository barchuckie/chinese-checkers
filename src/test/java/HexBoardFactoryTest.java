
import com.chinesecheckers.client.Board.*;
import com.chinesecheckers.client.Circle;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HexBoardFactoryTest extends HexBoardFactory {

    private BoardFactory hexBoardFactory;
    private Board hexBoardTwoPlayers;
    private Board hexBoardThreePlayers;
    private Board hexBoardFourPlayers;
    private Board hexBoardSixPlayers;
    private Circle[][] circles;

    @Before
    public void prepareBoards()
    {
        hexBoardFactory= new HexBoardFactory();
        this.hexBoardTwoPlayers = hexBoardFactory.getBoard(2);
        this.hexBoardThreePlayers = hexBoardFactory.getBoard(3);
        this.hexBoardFourPlayers = hexBoardFactory.getBoard(4);
        this.hexBoardSixPlayers = hexBoardFactory.getBoard(6);
    }
    @Test
    public void testCreateBoard() {
        Assert.assertTrue(hexBoardTwoPlayers instanceof HexBoardTwoPlayers);
        Assert.assertTrue(hexBoardThreePlayers instanceof HexBoardThreePlayers);
        Assert.assertTrue(hexBoardFourPlayers instanceof HexBoardFourPlayers);
        Assert.assertTrue(hexBoardSixPlayers instanceof HexBoardSixPlayers);
    }
    @Test
    public void testAddPlayers()
    {
        //TWO PLAYERS
        circles = new Circle[17][25];
        hexBoardTwoPlayers.addPlayers();
        for(int i = 0; i < 4; ++i) {
            //gora
            for(Circle circle : circles[i]) {
                if(circle != null) {
                    Assert.assertEquals(circle.getPlayer(),1);
                }
            }
            //dol
            for (Circle circle : circles[16-i]) {
                if(circle != null) {
                    Assert.assertEquals(circle.getPlayer(),2);
                }
            }
        }

        //THREE PLAYERS
        circles = new Circle[17][25];
        hexBoardThreePlayers.addPlayers();
        for(int i = 0; i < 4; ++i) {
            for(Circle circle : circles[i]) {
                if(circle != null) {
                    Assert.assertEquals(circle.getPlayer(),1);
                }
            }
        }
        for(int i = 4; i < 9; ++i) {
            for(int j = 0; j < 11-i; ++j) {
                Circle circle = circles[16-i][j];
                if(circle != null) {
                    Assert.assertEquals(circle.getPlayer(),3);
                }
                circle = circles[16-i][24-j];
                if(circle != null) {
                    Assert.assertEquals(circle.getPlayer(),2);
                }
            }
        }

        //FOUR PLAYERS
        circles = new Circle[17][25];
        hexBoardFourPlayers.addPlayers();
        for(int i = 4; i < 9; ++i) {
            for(int j = 0; j < 11-i; ++j) {
                Circle circle = circles[i][j];
                if(circle != null) {
                    Assert.assertEquals(circle.getPlayer(),4);
                }
                circle = circles[i][24-j];
                if(circle != null) {
                    Assert.assertEquals(circle.getPlayer(),1);
                }
                circle = circles[16-i][j];
                if(circle != null) {
                    Assert.assertEquals(circle.getPlayer(),3);
                }
                circle = circles[16-i][24-j];
                if(circle != null) {
                    Assert.assertEquals(circle.getPlayer(),2);
                }
            }
        }

        //SIXPLAYERS
        circles = new Circle[17][25];
        hexBoardSixPlayers.addPlayers();
        for(int i = 0; i < 4; ++i) {
            for(Circle circle : circles[i]) {
                if(circle != null) {
                    Assert.assertEquals(circle.getPlayer(),1);
                }
            }
            for (Circle circle : circles[16-i]) {
                if(circle != null) {
                    Assert.assertEquals(circle.getPlayer(),4);
                }
            }
        }

        for(int i = 4; i < 9; ++i) {
            for(int j = 0; j < 11-i; ++j) {
                Circle circle = circles[i][j];
                if(circle != null) {
                    Assert.assertEquals(circle.getPlayer(),6);
                }
                circle = circles[i][24-j];
                if(circle != null) {
                    Assert.assertEquals(circle.getPlayer(),2);
                }
                circle = circles[16-i][j];
                if(circle != null) {
                    Assert.assertEquals(circle.getPlayer(),5);
                }
                circle = circles[16-i][24-j];
                if(circle != null) {
                    Assert.assertEquals(circle.getPlayer(),3);
                }
            }
        }
    }

}

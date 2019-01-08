import com.chinesecheckers.client.Board.Board;
import com.chinesecheckers.client.Board.BoardFactory;
import com.chinesecheckers.client.Board.HexBoardFactory;
import com.chinesecheckers.client.CircleField;
import com.chinesecheckers.client.Field;
import com.chinesecheckers.client.SquareField;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

public class FieldTest extends HexBoardFactory
{
    private Board hexOnSquares;
    private Board hexOnCircles;
    private BoardFactory boardFactory;
    private SquareField squareField;
    private CircleField circleField;
    @Before
    public void prepare()
    {
        boardFactory = new HexBoardFactory();
        hexOnSquares = boardFactory.getBoard(2,"SQUARE");
        hexOnCircles = boardFactory.getBoard(2,"STANDARD");
        squareField = new SquareField(10,10,20,2);
        circleField = new CircleField(10,10,20,2);
    }
    @Test
    public void CircleFieldTest()
    {
        Field[][] tab = hexOnCircles.getFields();
        tab[13][13].setPlayer(1);

        Assert.assertTrue(tab instanceof CircleField[][]);
        Assert.assertSame(tab[13][13].getPlayer(),1);
        Assert.assertTrue(circleField.doesContain(new Point(15,15)));


    }
    @Test
    public void SquareFieldTest()
    {
        Field[][]tab = hexOnSquares.getFields();
        tab[12][12].setPlayer(1);
        Assert.assertTrue(tab instanceof SquareField[][]);
        Assert.assertSame(tab[12][12].getPlayer(),1);
        Assert.assertTrue(squareField.doesContain(new Point(15,15)));
    }


}

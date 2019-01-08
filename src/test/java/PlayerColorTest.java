import com.chinesecheckers.client.PlayerColor;
import org.junit.Assert;
import org.junit.Test;

import java.awt.*;

public class PlayerColorTest {

    @Test
    public void getColorTest()
    {
        Assert.assertSame(PlayerColor.getColor(1), Color.GREEN);
        Assert.assertSame(PlayerColor.getColor(2), Color.RED);
        Assert.assertSame(PlayerColor.getColor(3), Color.BLUE);
        Assert.assertSame(PlayerColor.getColor(4), Color.YELLOW);
        Assert.assertSame(PlayerColor.getColor(5), Color.ORANGE);
        Assert.assertSame(PlayerColor.getColor(6), Color.MAGENTA);
        Assert.assertSame(PlayerColor.getColor(0), Color.WHITE);
    }

    @Test
    public void getPlayerTest()
    {
        Assert.assertSame(PlayerColor.getPlayer(Color.GREEN),1);
        Assert.assertSame(PlayerColor.getPlayer(Color.RED),2);
        Assert.assertSame(PlayerColor.getPlayer(Color.BLUE),3);
        Assert.assertSame(PlayerColor.getPlayer(Color.YELLOW),4);
        Assert.assertSame(PlayerColor.getPlayer(Color.ORANGE),5);
        Assert.assertSame(PlayerColor.getPlayer(Color.MAGENTA),6);
        Assert.assertSame(PlayerColor.getPlayer(Color.WHITE),0);
    }

    @Test
    public void getColorNameTest()
    {
        Assert.assertEquals(PlayerColor.getColorName(1),"GREEN");
        Assert.assertEquals(PlayerColor.getColorName(2),"RED");
        Assert.assertEquals(PlayerColor.getColorName(3),"BLUE");
        Assert.assertEquals(PlayerColor.getColorName(4),"YELLOW");
        Assert.assertEquals(PlayerColor.getColorName(5),"ORANGE");
        Assert.assertEquals(PlayerColor.getColorName(6),"MAGENTA");
        Assert.assertEquals(PlayerColor.getColorName(0),"WHITE");
    }

}

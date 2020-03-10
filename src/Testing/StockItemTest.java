package Testing;

import model.StockItem;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class StockItemTest {

    StockItem si;

    @Before
    public void setUp() throws Exception {
        si = new StockItem("1", "1", 1, 1, false, new Date(), 1, 2, "here");
    }

    @Test
    public void setQuantity() {
        Assert.assertEquals(1, si.getQuantity());
        si.setQuantity(5);
        Assert.assertEquals(5, si.getQuantity());
    }

    @Test
    public void setName() {
        Assert.assertEquals("1", si.getName());
        si.setName("banana");
        Assert.assertEquals("banana", si.getName());
    }

    @Test
    public void setPrice() {
        Assert.assertEquals(1, si.getPrice());
        si.setPrice(5);
        Assert.assertEquals(5, si.getPrice());
    }

    @Test
    public void getId() {
        Assert.assertEquals("1", si.getId());
    }

    @Test
    public void getMinStock() {
        Assert.assertEquals(1, si.getMinStock());
    }

    @Test
    public void getMaxStock() {
        Assert.assertEquals(2, si.getMaxStock());
    }

    @Test
    public void isCanExpire() {
        Assert.assertFalse(si.isCanExpire());
    }

    @Test
    public void getLocation() {
        Assert.assertEquals("here", si.getLocation());
    }

    @Test
    public void getTotalProfit() {
        Assert.assertEquals(0, si.getTotalProfit());
        si.setQuantity(5);
        si.setPrice(4);
        Assert.assertEquals(10, si.getTotalProfit());
    }
}
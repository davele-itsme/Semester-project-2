package Testing;

import model.ProductRequest;
import model.StockItem;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class ProductRequestTest {

    ProductRequest pr;
    StockItem si;

    @Before
    public void setUp(){
        si = new StockItem("1", "1", 1, 1, false, new Date(), 1, 2, "here");
        pr = new ProductRequest(si, 5);
    }

    @Test
    public void getProductId() {
        Assert.assertEquals("1", pr.getProductId());
    }

    @Test
    public void getStockItem() {
        Assert.assertEquals(si, pr.getStockItem());
    }

    @Test
    public void getName() {
        Assert.assertEquals("1", pr.getName());
    }

    @Test
    public void getID() {
        Assert.assertEquals("1", pr.getID());
    }

    @Test
    public void setQuantity() {
        Assert.assertEquals(5, pr.getQuantity());
        pr.setQuantity(10);
        Assert.assertEquals(10, pr.getQuantity());
    }

    @Test
    public void getActualQuantity() {
        Assert.assertEquals(1, pr.getActualQuantity());
    }
}
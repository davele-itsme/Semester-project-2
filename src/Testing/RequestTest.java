package Testing;

import model.Request;
import model.StockItem;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class RequestTest {

    Request r;
    StockItem si;

    @Before
    public void setUp() throws Exception {
        si = new StockItem("1", "1", 1, 1, false, new Date(), 1, 2, "here");
        r = new Request(si, 5, si.getQuantity());
    }

    @Test
    public void getRequestItem() {
        Assert.assertEquals(si, r.getRequestItem());
    }

    @Test
    public void getQuantity() {
        Assert.assertEquals(5, r.getQuantity());
    }

    @Test
    public void getCurrentQty() {
        Assert.assertEquals(1, r.getCurrentQty());
    }
}
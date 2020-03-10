package Testing;

import model.ProductRequest;
import model.ProductRequestList;
import model.StockItem;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class ProductRequestListTest {

    ProductRequest pr;
    ProductRequestList prList;

    @Before
    public void setUp() throws Exception {
        StockItem si = new StockItem("1", "1", 1, 1, false, new Date(), 1, 2, "here");
        pr = new ProductRequest(si, 5);
        prList = new ProductRequestList();
    }

    @Test
    public void addRequestToList() {
        Assert.assertEquals(0, prList.size());
        prList.addRequestToList(pr);
        Assert.assertEquals(1, prList.size());
    }

    @Test
    public void removeRequestFromList() {
        prList.addRequestToList(pr);
        Assert.assertEquals(1, prList.size());
        prList.removeRequestFromList("1");
        Assert.assertEquals(0, prList.size());
    }

    @Test
    public void getProductRequest() {
        prList.addRequestToList(pr);
        Assert.assertEquals(pr, prList.getProductRequest(0));
    }
}
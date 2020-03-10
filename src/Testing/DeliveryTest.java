package Testing;

import model.Delivery;
import model.ProductRequest;
import model.ProductRequestList;
import model.StockItem;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class DeliveryTest {

    Delivery delivery;

    @Before
    public void setUp(){
        delivery = new Delivery("1", "RT", "done", new Date());
    }

    @Test
    public void setRequestid() {
        Assert.assertEquals("1", delivery.getRequestid());
        delivery.setRequestid("2");
        Assert.assertEquals("2", delivery.getRequestid());
    }

    @Test
    public void setRequestedFrom() {
        Assert.assertEquals("RT", delivery.getRequestedFrom());
        delivery.setRequestedFrom("WH");
        Assert.assertEquals("WH", delivery.getRequestedFrom());
    }

    @Test
    public void setStatus() {
        Assert.assertEquals("done", delivery.getStatus());
        delivery.setStatus("not done");
        Assert.assertEquals("not done", delivery.getStatus());
    }

    @Test
    public void getTotalItems() {
        Assert.assertEquals(0, delivery.getTotalItems());
        StockItem si = new StockItem("1", "1", 1, 1, false, new Date(), 2, 5, "home");
        ProductRequest pr = new ProductRequest(si, 5);
        ProductRequest pr2 = new ProductRequest(si, 7);
        delivery.addToDelivery(pr); delivery.addToDelivery(pr2);
        Assert.assertEquals(12, delivery.getTotalItems());
    }

    @Test
    public void setProductList() {
        Assert.assertEquals(0, delivery.getProductList().size());
        ProductRequestList prl = new ProductRequestList();
        StockItem si = new StockItem("1", "1", 1, 1, false, new Date(), 2, 5, "home");
        prl.addRequestToList(new ProductRequest(si, 2));
        delivery.setProductList(prl);
        Assert.assertEquals(prl, delivery.getProductList());
    }

    @Test
    public void addToDelivery() {
        Assert.assertEquals(0, delivery.getProductList().size());
        StockItem si = new StockItem("1", "1", 1, 1, false, new Date(), 2, 5, "home");
        ProductRequest pr = new ProductRequest(si, 2);
        delivery.addToDelivery(pr);
        Assert.assertEquals(1, delivery.getProductList().size());
    }
}
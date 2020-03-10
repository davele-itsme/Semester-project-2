package Testing;

import model.Delivery;
import model.DeliveryList;
import model.ProductRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class DeliveryListTest {

    DeliveryList dList;

    @Before
    public void setUp() throws Exception {
        dList = new DeliveryList();
    }

    @Test
    public void addDelivery() {
        Assert.assertEquals(0, dList.size());
        dList.addDelivery(new Delivery("1", "RT", "done", new Date()));
        Assert.assertEquals(1, dList.size());
    }
}
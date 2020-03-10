package Testing;

import model.Counter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CounterTest {

    @Before
    public void setUp() throws Exception {
        Counter.setupCounter(0, 0);
    }

    @Test
    public void getIDEmployee() {
        Assert.assertEquals("000000" + 1, Counter.getIDEmployee());
        Assert.assertEquals("000000" + 2, Counter.getIDEmployee());
    }

    @Test
    public void getIDStockItem() {
        Assert.assertEquals("000000" + 1, Counter.getIDStockItem());
        Assert.assertEquals("000000" + 2, Counter.getIDStockItem());
    }
}
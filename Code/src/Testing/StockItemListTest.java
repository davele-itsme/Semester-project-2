package Testing;

import model.StockItem;
import model.StockItemList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class StockItemListTest {

    StockItemList sList;
    StockItem si;

    @Before
    public void setUp() throws Exception {
        sList = new StockItemList();
        si = new StockItem("1", "1", 1, 1, false, new Date(), 1, 2, "here");
    }

    @Test
    public void add() {
        Assert.assertEquals(0, sList.size());
        sList.add(si);
        Assert.assertEquals(1, sList.size());
    }

    @Test
    public void remove() {
        sList.add(si);
        Assert.assertEquals(1, sList.size());
        sList.remove(0);
        Assert.assertEquals(0, sList.size());
    }

    @Test
    public void remove1() {
        sList.add(si);
        Assert.assertEquals(1, sList.size());
        sList.remove(si);
        Assert.assertEquals(0, sList.size());
    }

    @Test
    public void totalPrice() {
        sList.add(si);
        Assert.assertEquals(1, sList.totalPrice());
    }

    @Test
    public void totalQuantity() {
        sList.add(si);
        Assert.assertEquals(1, sList.totalQuantity());
    }

    @Test
    public void get() {
        sList.add(si);
        Assert.assertEquals(si, sList.get(0));
    }
}
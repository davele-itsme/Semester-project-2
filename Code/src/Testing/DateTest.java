package Testing;

import model.Date;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DateTest {

    Date date;

    @Before
    public void setUp(){
        date = new Date(1, 1, 2000);
    }

    @Test
    public void setDay() {
        Assert.assertEquals(1, date.getDay());
        date.setDay(4);
        Assert.assertEquals(4, date.getDay());
    }

    @Test
    public void setMonth() {
        Assert.assertEquals(1, date.getMonth());
        date.setMonth(4);
        Assert.assertEquals(4, date.getMonth());
    }

    @Test
    public void setYear() {
        Assert.assertEquals(2000, date.getYear());
        date.setYear(2001);
        Assert.assertEquals(2001, date.getYear());
    }

    @Test
    public void daysInMonth() {
        Assert.assertEquals(31, date.daysInMonth(1));
        Assert.assertEquals(29, date.daysInMonth(2));
        date.setYear(2001);
        Assert.assertEquals(28, date.daysInMonth(2));
        Assert.assertEquals(30, date.daysInMonth(4));
    }

    @Test
    public void isLeapYear() {
        Assert.assertTrue(date.isLeapYear(2000));
        Assert.assertFalse(date.isLeapYear(2001));
    }

    @Test
    public void incDays() {
        Assert.assertEquals(1, date.getDay());
        date.incDays(20);
        Assert.assertEquals(21, date.getDay());
        date.incDays(11);
        Assert.assertEquals(2, date.getMonth());
        date = new Date(31, 12, 2001);
        date.incDays(1);
        Assert.assertEquals(2002, date.getYear());
    }

    @Test
    public void equals() {
        Assert.assertTrue(date.equals(date));
        Date otherDate = new Date(1, 1, 1);
        Assert.assertFalse(date.equals(otherDate));
    }
}
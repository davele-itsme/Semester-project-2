package Testing;

import model.Message;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;

import static org.junit.Assert.*;

public class MessageTest {

    Message m;

    @Before
    public void setUp() throws Exception {
        m = new Message("hi", new Timestamp(0), "1");
    }

    @Test
    public void setMessage() {
        Assert.assertEquals("hi", m.getMessage());
        m.setMessage("Hello");
        Assert.assertEquals("Hello", m.getMessage());
    }

    @Test
    public void setTimestamp() {
        Assert.assertEquals(new Timestamp(0), m.getTimestamp());
        m.setTimestamp(new Timestamp(2));
        Assert.assertEquals(new Timestamp(2), m.getTimestamp());
    }

    @Test
    public void setDepartmentID() {
        Assert.assertEquals("1", m.getDepartmentID());
        m.setDepartmentID("RT");
        Assert.assertEquals("RT", m.getDepartmentID());
    }
}
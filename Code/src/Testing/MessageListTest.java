package Testing;

import model.Message;
import model.MessageList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;

import static org.junit.Assert.*;

public class MessageListTest {

    MessageList mList;

    @Before
    public void setUp() throws Exception {
        mList = new MessageList();
    }

    @Test
    public void addMessage() {
        Assert.assertEquals(0, mList.size());
        mList.addMessage(new Message("Hi", new Timestamp(0), "RT"));
        Assert.assertEquals(1, mList.size());
    }
}
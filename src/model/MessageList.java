package model;

import java.util.ArrayList;

/**
 * MessageList to store {@link Message} objects.
 *
 * @author Kenneth Jensen
 * @author Floring Bordei
 * @author Jaime Lopez
 * @author Dave Joe Lê
 */
public class MessageList {
    private ArrayList<Message> messageList;

    /**
     * Creates a MessageList and instantiates the {@link ArrayList} stored.
     */
    public MessageList() {
        messageList = new ArrayList<>();
    }

    public void addMessage(Message message)
    {
        messageList.add(message);
    }
    public Message getMessage (int index)
    {
        return messageList.get(index);
    }

    public int size() {

        return messageList.size();
    }

    @Override
    public String toString() {
        return "MessageList{" +
                "messageList=" + messageList +
                '}';
    }
}

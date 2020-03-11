package viewmodel.hq.hq.messenger;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.IDataModel;
import model.Message;
import model.MessageList;
import view.hq.ViewHandler;

import java.beans.PropertyChangeEvent;
import java.sql.Timestamp;

/**
 * The main viewmodel Class for the Warehouse.
 *
 * @author Kenneth Jensen
 * @author Floring Bordei
 * @author Jaime Lopez
 * @author Dave Joe Lê
 */

public class MessengerVM {

    private IDataModel dataModel;
    private ViewHandler viewHandler;
    private ObservableList<Message> messages;
    private StringProperty txtMsg;

    /**
     * Creates a MainVM with the specified information
     *
     * @param dataModel   The {@link model.DataModel} to be used.
     * @param viewHandler The {@link ViewHandler} to be used.
     */
    public MessengerVM(IDataModel dataModel, ViewHandler viewHandler) {
        this.dataModel = dataModel;
        this.viewHandler = viewHandler;
        messages = FXCollections.observableArrayList();
        txtMsg = new SimpleStringProperty();
        dataModel.addListener("AddMessageView", this::addMessage);
        dataModel.addListener("NewMessageList", this::loadList);
    }

    private void loadList(PropertyChangeEvent evt) {
        MessageList messageList = (MessageList) evt.getNewValue();
        messages.removeAll(messages);
        for (int i = 0; i < messageList.size(); i++) {
            messages.add(messageList.getMessage(i));
        }
    }

    private void addMessage(PropertyChangeEvent evt) {
        messages.add((Message) evt.getNewValue());
    }

    public ObservableList<Message> getMessages() {
        return messages;
    }

    public StringProperty txtMsgProperty() {
        return txtMsg;
    }

    /**
     * This method opens the main Employee view.
     */
    public void openEmployeeMainHQView() {
        viewHandler.openEmployeeMainHQView();
    }

    /**
     * This method opens the main Inventory view.
     */
    public void openInventoryMainHQView() {
        viewHandler.openInventoryMainHQView();
    }

    public void openMainView() {
        viewHandler.openMainHQView();
    }

    public void sendMessage() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Message message = new Message(txtMsg.getValue(), timestamp, "HQ");
        dataModel.sendMessage(message);
        txtMsg.setValue("");
    }

    public void openMainWHView() {viewHandler.openMainWHView();
    }

    public void openMainRTView() {viewHandler.openMainRTView();
    }

    public void openMessengerView() {viewHandler.openMessengerView();
    }
}
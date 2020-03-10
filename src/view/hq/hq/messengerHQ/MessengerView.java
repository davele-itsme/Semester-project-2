package view.hq.hq.messengerHQ;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.Cell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Message;
import viewmodel.hq.hq.messenger.MessengerVM;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * The main view Class for the Headquarters.
 *
 * @author Kenneth Jensen
 * @author Floring Bordei
 * @author Jaime Lopez
 * @author Dave Joe Lê
 */
public class MessengerView {
    private MessengerVM messengerVM;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ListView<Message> chatBox;


    @FXML
    private TextArea txtMsg;

    @FXML
    void onEmployeeClicked(ActionEvent event) {
        messengerVM.openEmployeeMainHQView();
    }

    @FXML
    void onInventoryClicked(ActionEvent event) {
        messengerVM.openInventoryMainHQView();
    }

    @FXML
    void onDashboardClicked(ActionEvent event) {
        messengerVM.openMainView();
    }

    /**
     * Creates a MessengerView.
     */
    public MessengerView() {

    }

    /**
     * An init method instantiating all the required fields.
     *
     * @param messengerVM The {@link MessengerVM} viewmodel to use.
     */
    public void init(MessengerVM messengerVM) {
        this.messengerVM = messengerVM;
        chatBox.setItems(messengerVM.getMessages());
        txtMsg.textProperty().bindBidirectional(messengerVM.txtMsgProperty());
        chatBox.scrollTo(chatBox.getItems().size());
    }

    @FXML
    void onCloseClicked(MouseEvent event) {
        Platform.exit();
    }

    @FXML
    void onMinimizeClicked(MouseEvent event) {
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    void onRetailerClicked(MouseEvent event) {
        messengerVM.openMainRTView();
    }

    @FXML
    void onWarehouseClicked(MouseEvent event) {
        messengerVM.openMainWHView();
    }

    @FXML
    void onHQClicked(MouseEvent event) {
        messengerVM.openMainView();
    }

    @FXML
    void onSendMessageClicked(MouseEvent event) {
        messengerVM.sendMessage();
        chatBox.scrollTo(chatBox.getItems().size());
    }


    @FXML void onMessengerClicked(MouseEvent event)
    {
        messengerVM.openMessengerView();
    }
}

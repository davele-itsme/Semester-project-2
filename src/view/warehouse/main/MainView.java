package view.warehouse.main;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import viewmodel.warehouse.main.MainVM;

/**
 * The main view Class for the Warehouse.
 *
 * @author Kenneth Jensen
 * @author Floring Bordei
 * @author Jaime Lopez
 * @author Dave Joe Lê
 */

public class MainView {
    private MainVM mainVM;
    @FXML
    private AnchorPane anchorPane;

    /**
     * Creates a MessengerView.
     */
    public MainView() {

    }

    /**
     * An init method instantiating all the required fields.
     * @param mainVM The {@link MainVM} viewmodel to be used.
     */
    public void init(MainVM mainVM) {
        this.mainVM = mainVM;
    }

    @FXML
    void onEmployeeClicked(ActionEvent event) {
        mainVM.openEmployeeMainView();
    }

    @FXML
    void onInventoryClicked(ActionEvent event) {
        mainVM.openInventoryMainView();

    }

    @FXML
    void onProductRequestClicked(ActionEvent event) {
        mainVM.openProductRequestView();
    }

    @FXML
    void onDashboardClicked(ActionEvent event) {
        mainVM.openMainView();
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
    void onDeliveryClicked(ActionEvent event)
    {
        mainVM.openDeliveryMainView();
    }

    @FXML void onMessengerClicked(MouseEvent event) {
        mainVM.openMessengerView();
    }

}



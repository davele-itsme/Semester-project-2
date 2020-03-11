package view.hq.controlWarehouse.mainWarehouse;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import viewmodel.hq.controlWarehouse.mainWarehouse.MainWHVM;

public class MainWHView {
    private MainWHVM mainWHVM;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    void onEmployeeClicked(ActionEvent event) {
        mainWHVM.openEmployeeMainWHView();
    }

    @FXML
    void onInventoryClicked(ActionEvent event) {
        mainWHVM.openInventoryMainWHView();
    }

    @FXML
    void onDashboardClicked(ActionEvent event) {
        mainWHVM.openMainWHView();
    }

    /**
     * Creates a MessengerView.
     */
    public MainWHView()
    {

    }

    /**
     * An init method instantiating all the required fields.
     * @param mainVM The {@link MainWHVM} viewmodel to use.
     */
    public void init(MainWHVM mainVM)
    {
        this.mainWHVM = mainVM;
    }

    @FXML
    void onCloseClicked(MouseEvent event) {
        Platform.exit();
    }

    @FXML
    void onMinimizeClicked(MouseEvent event) {
        Stage stage = (Stage)anchorPane.getScene().getWindow();
        stage.setIconified(true);
    }


    @FXML
    void onRetailerClicked(MouseEvent event) {
        mainWHVM.openMainRTView();
    }

    @FXML
    void onWarehouseClicked(MouseEvent event) {
        mainWHVM.openMainWHView();
    }

    @FXML void onDeliveryClicked(ActionEvent event)
    {
        mainWHVM.openDeliveryView();
    }

    @FXML void onHQClicked(MouseEvent event)
    {
        mainWHVM.openMainView();
    }
}

package view.hq.controlRetailer.mainRetailer;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import viewmodel.hq.controlRetailer.mainRetailer.MainRTVM;

public class MainRTView {
    private MainRTVM mainRTVM;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    void onEmployeeClicked(ActionEvent event) {
        mainRTVM.openEmployeeMainRTView();
    }

    @FXML
    void onInventoryClicked(ActionEvent event) {
        mainRTVM.openInventoryMainRTView();
    }

    @FXML
    void onDashboardClicked(ActionEvent event) {
        mainRTVM.openMainRTView();
    }

    /**
     * Creates a MessengerView.
     */
    public MainRTView()
    {

    }

    /**
     * An init method instantiating all the required fields.
     * @param mainVM The {@link MainRTVM} viewmodel to use.
     */
    public void init(MainRTVM mainVM)
    {
        this.mainRTVM = mainVM;
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
        mainRTVM.openMainRTView();
    }

    @FXML
    void onWarehouseClicked(MouseEvent event) {
        mainRTVM.openMainWHView();
    }

    @FXML void onDeliveryClicked(ActionEvent event)
    {
        mainRTVM.openDeliveryView();
    }

    @FXML void onHQClicked(MouseEvent event)
    {
        mainRTVM.openMainView();
    }
}

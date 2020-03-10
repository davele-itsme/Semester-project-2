package view.hq.hq.main;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import viewmodel.hq.hq.main.MainVM;

/**
 * The main view Class for the Headquarters.
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

    @FXML
    void onEmployeeClicked(ActionEvent event) {
        mainVM.openEmployeeMainHQView();
    }

    @FXML
    void onInventoryClicked(ActionEvent event) {
        mainVM.openInventoryMainHQView();
    }

    @FXML
    void onDashboardClicked(ActionEvent event) {
        mainVM.openMainView();
    }

    /**
     * Creates a MessengerView.
     */
    public MainView()
    {

    }

    /**
     * An init method instantiating all the required fields.
     * @param mainVM The {@link MainVM} viewmodel to use.
     */
    public void init(MainVM mainVM)
    {
        this.mainVM = mainVM;
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
        mainVM.openMainRTView();
    }

    @FXML
    void onWarehouseClicked(MouseEvent event) {
        mainVM.openMainWHView();
    }

    @FXML void onHQClicked(MouseEvent event)
    {
        mainVM.openMainView();
    }

    @FXML void onMessengerClicked(MouseEvent event)
    {
        mainVM.openMessengerView();
    }

}

package view.hq.controlRetailer.employeeRetailer;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Employee;
import viewmodel.hq.controlRetailer.employeeRetailer.EmployeeRTVM;

/**
 * The view Class for the main Warehouse view.
 *
 * @author Kenneth Jensen
 * @author Floring Bordei
 * @author Jaime Lopez
 * @author Dave Joe Lê
 */

public class EmployeeMainRTView {
    @FXML
    private TableView<Employee> employeeTable;

    @FXML
    private TableColumn<String, Employee> firstNameCol;

    @FXML
    private TableColumn<String, Employee> lastNameCol;

    @FXML
    private TableColumn<String, Employee> iDCol;

    @FXML
    private AnchorPane anchorPane;

    private EmployeeRTVM employeeRTVM;

    /**
     * Creates an EmployeeMainWHView
     */
    public EmployeeMainRTView() {

    }

    /**
     * An init method, initiating all the required fields
     * @param employeeRTVM The {@link EmployeeRTVM} viewmodel to be used.
     */
    public void init(EmployeeRTVM employeeRTVM) {
        this.employeeRTVM = employeeRTVM;
        employeeTable.setItems(employeeRTVM.getEmployees());
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        iDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
    }

    @FXML
    void onCloseClicked(MouseEvent event) {
        Platform.exit();
    }

    @FXML
    void onDashboardClicked(ActionEvent event) {

    }

    @FXML void onDeliveryClicked(ActionEvent event)
    {
        employeeRTVM.openDeliveryView();
    }

    @FXML
    void onEmployeeClicked(ActionEvent event) {
        employeeRTVM.openEmployeeMainRTView();
    }

    @FXML
    void onInventoryClicked(ActionEvent event) {
        employeeRTVM.openInventoryRTView();
    }

    @FXML
    void onMinimizeClicked(MouseEvent event) {
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    void onHQClicked(MouseEvent event) {
        employeeRTVM.openMainHQView();
    }

    @FXML
    void onRetailerClicked(MouseEvent event) {
        employeeRTVM.openMainRTView();
    }

    @FXML void onWarehouseClicked(MouseEvent event)
    {
        employeeRTVM.openMainWHView();
    }
}

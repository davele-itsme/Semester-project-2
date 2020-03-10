package view.hq.controlWarehouse.employeeWarehouse;

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
import viewmodel.hq.controlWarehouse.employeeWarehouse.EmployeeWHVM;

/**
 * The view Class for the main Warehouse view.
 *
 * @author Kenneth Jensen
 * @author Floring Bordei
 * @author Jaime Lopez
 * @author Dave Joe Lê
 */

public class EmployeeMainWHView {
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

    private EmployeeWHVM employeeWHVM;

    /**
     * Creates an EmployeeMainWHView
     */
    public EmployeeMainWHView() {

    }

    /**
     * An init method, initiating all the required fields
     * @param employeeWHVM The {@link EmployeeWHVM} viewmodel to be used.
     */
    public void init(EmployeeWHVM employeeWHVM) {
        this.employeeWHVM = employeeWHVM;
        employeeTable.setItems(employeeWHVM.getEmployees());
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
        employeeWHVM.openMainWHView();
    }

    @FXML void onDeliveryClicked(ActionEvent event)
    {
        employeeWHVM.openDeliveryView();
    }

    @FXML
    void onEmployeeClicked(ActionEvent event) {
        employeeWHVM.openEmployeeMainWHView();
    }

    @FXML
    void onHQClicked(MouseEvent event) {
        employeeWHVM.openMainHQView();
    }

    @FXML
    void onInventoryClicked(ActionEvent event) {
        employeeWHVM.openInventoryWHView();
    }

    @FXML
    void onMinimizeClicked(MouseEvent event) {
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    void onRetailerClicked(MouseEvent event) {
        employeeWHVM.openMainRTView();
    }

    @FXML void onWarehouseClicked(MouseEvent event)
    {
        employeeWHVM.openMainWHView();
    }
}

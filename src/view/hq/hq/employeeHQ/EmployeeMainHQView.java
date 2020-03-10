package view.hq.hq.employeeHQ;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Employee;
import viewmodel.hq.hq.employee.EmployeeMainHQVM;

/**
 * The view Class for the Headquarter's main Employee view.
 *
 * @author Kenneth Jensen
 * @author Floring Bordei
 * @author Jaime Lopez
 * @author Dave Joe Lê
 */

public class EmployeeMainHQView {
    @FXML
    private TableView<Employee> employeeTable;

    @FXML
    private TableColumn<String, Employee> firstNameCol;

    @FXML
    private TableColumn<String, Employee> lastNameCol;

    @FXML
    private TableColumn<String, Employee> iDCol;

    @FXML
    private TableColumn<String, Employee> departmentIDCol;

    @FXML
    private AnchorPane anchorPane;

    private EmployeeMainHQVM employeeMainHQVM;
    private Employee selectedEmployee;

    /**
     * Creates an EmployeeMainHQView.
     */
    public EmployeeMainHQView() {

    }

    /**
     * An init method instantiating all the required fields.
     * @param employeeMainHQVM The {@link EmployeeMainHQVM} viewmodel to use.
     */
    public void init(EmployeeMainHQVM employeeMainHQVM)
    {
        this.employeeMainHQVM = employeeMainHQVM;
        employeeTable.setItems(employeeMainHQVM.getEmployees());
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        iDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        departmentIDCol.setCellValueFactory(new PropertyValueFactory<>("departmentID"));
    }

    @FXML
    void onAddEmployeeClicked(ActionEvent event) {
        employeeMainHQVM.openEmployeeAddView();
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
    void onDashboardClicked(ActionEvent event) {
        employeeMainHQVM.openMainView();
    }

    @FXML
    void onEditEmployeeClicked(ActionEvent event) {

    }

    @FXML
    void onEmployeeClicked(ActionEvent event) {
        employeeMainHQVM.openEmployeeMainHQView();
    }

    @FXML
    void onInventoryClicked(ActionEvent event)
    {
        employeeMainHQVM.openInventoryMainHQView();
    }

    @FXML
    void onRemoveEmployeeClicked(ActionEvent event) {
        selectedEmployee = employeeTable.getSelectionModel().getSelectedItem();
        if(selectedEmployee == null)
        {
            Alert warningAlert = new Alert(Alert.AlertType.WARNING);
            warningAlert.setTitle("Warning");
            warningAlert.setHeaderText("No employee has been selected");
            warningAlert.setContentText("Press ok to continue");
            warningAlert.showAndWait();
        }
        Alert confirmingAlert = new Alert(Alert.AlertType.CONFIRMATION, "Delete", ButtonType.YES, ButtonType.NO);
        confirmingAlert.setTitle("Confirmation");
        confirmingAlert.setHeaderText("Are you sure you want to delete the employee with ID: " + selectedEmployee.getId() + "?");
        confirmingAlert.setContentText("Press ok to continue");
        confirmingAlert.showAndWait();

        if(confirmingAlert.getResult() == ButtonType.YES) {
            Employee selectedEmployee = employeeTable.getSelectionModel().getSelectedItem();
            employeeTable.getItems().remove(selectedEmployee);
            employeeMainHQVM.removeEmployee(selectedEmployee);
        }
    }


    @FXML
    void onRetailerClicked(MouseEvent event) {
        employeeMainHQVM.openMainRTView();
    }

    @FXML
    void onWarehouseClicked(MouseEvent event) {
        employeeMainHQVM.openMainWHView();
    }

    @FXML void onHQClicked(MouseEvent event)
    {
        employeeMainHQVM.openMainView();
    }

    @FXML void onMessengerClicked(MouseEvent event)
    {
        employeeMainHQVM.openMessengerView();
    }
}

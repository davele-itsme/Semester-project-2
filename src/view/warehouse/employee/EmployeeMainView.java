package view.warehouse.employee;

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
import viewmodel.warehouse.employee.EmployeeMainVM;

/**
 * The view Class for the main Employee view.
 *
 * @author Kenneth Jensen
 * @author Floring Bordei
 * @author Jaime Lopez
 * @author Dave Joe Lê
 */

public class EmployeeMainView {

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

    private EmployeeMainVM employeeMainVM;

    private Employee selectedEmployee;

    /**
     * Creates an EmployeeMainView.
     */
    public EmployeeMainView()
    {

    }

    /**
     * An init method instantiating all the required fields.
     * @param employeeMainVM the {@link EmployeeMainVM} viewmodel to be used.
     */
    public void init(EmployeeMainVM employeeMainVM)
    {
        this.employeeMainVM = employeeMainVM;
        employeeTable.setItems(employeeMainVM.getEmployees());
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        iDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
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
        employeeMainVM.openMainView();
    }

    @FXML
    void onInventoryClicked(ActionEvent event) {
        employeeMainVM.openInventoryView();
    }

    @FXML
    void onAddEmployeeClicked(ActionEvent event) {
        employeeMainVM.openEmployeeAddView();
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
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete " + " ?", ButtonType.YES, ButtonType.NO);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Are you sure you want to delete the employee with ID: " + selectedEmployee.getId() + "?");
        alert.setContentText("Press ok to continue");
        alert.showAndWait();

        if(alert.getResult() == ButtonType.YES)
        {
            employeeTable.getItems().remove(selectedEmployee);
            employeeMainVM.removeEmployee(selectedEmployee);
        }

        //TODO: Should we make it in VM?
    }

    @FXML
    void onEmployeeClicked(ActionEvent event) {

    }

    @FXML
    void onEditEmployeeClicked(ActionEvent event) {

    }

    @FXML
    void onProductRequestClicked(ActionEvent event) {
        employeeMainVM.openProductRequestView();
    }

    @FXML void onDeliveryClicked(ActionEvent event)
    {
        employeeMainVM.openDeliveryMainView();
    }

    @FXML void onMessengerClicked(MouseEvent event) {
        employeeMainVM.openMessengerView();
    }
}

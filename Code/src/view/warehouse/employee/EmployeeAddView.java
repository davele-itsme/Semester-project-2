package view.warehouse.employee;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import viewmodel.warehouse.employee.EmployeeAddVM;

/**
 * The view Class for the add Employee view.
 *
 * @author Kenneth Jensen
 * @author Floring Bordei
 * @author Jaime Lopez
 * @author Dave Joe Lê
 */

public class EmployeeAddView {

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Label errorFirstNameLabel;

    @FXML private Label emptyFirstNameLabel;

    @FXML
    private Label errorLastNameLabel;

    @FXML
    private Label emptyLastNameLabel;

    private EmployeeAddVM employeeAddVM;

    /**
     * Creates an EmployeeAddView.
     */
    public EmployeeAddView() {

    }

    /**
     * An init method instantiating all the required fields.
     * @param employeeAddVM The {@link EmployeeAddVM} viewmodel to be used.
     */
    public void init(EmployeeAddVM employeeAddVM) {
        this.employeeAddVM = employeeAddVM;
        firstNameField.textProperty().bindBidirectional(employeeAddVM.firstNameProperty());
        lastNameField.textProperty().bindBidirectional(employeeAddVM.lastNameProperty());
    }


    @FXML
    void onAddClicked(ActionEvent event) {
        if(isEverythingValid())
        {
            employeeAddVM.addEmployee();
            employeeAddVM.confirmation();
        }
    }

    @FXML
    void onBackClicked(ActionEvent event) {
        employeeAddVM.goBack();
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
    void onDashboardClicked(ActionEvent event) {
        employeeAddVM.openMainView();
    }

    @FXML
    void onEmployeeClicked(ActionEvent event) {
        employeeAddVM.openEmployeeView();
    }

    @FXML
    void onInventoryClicked(ActionEvent event) {
        employeeAddVM.openInventoryView();
    }

    @FXML
    void onProductRequestClicked(ActionEvent event) {
        employeeAddVM.openProductRequestView();
    }

    @FXML void onDeliveryClicked(ActionEvent event)
    {
        employeeAddVM.openDeliveryMainView();
    }

    private boolean isEverythingValid() {
        boolean emptyFirstName, emptyLastName, validFirstName, validLastName = false;

        if (firstNameField.textProperty().getValue().isEmpty()) {
            emptyFirstName = true;
            emptyFirstNameLabel.setVisible(true);
        } else {
            emptyFirstName = false;
            emptyFirstNameLabel.setVisible(false);
        }

        if (lastNameField.textProperty().getValue().isEmpty()) {
            emptyLastName = true;
            emptyLastNameLabel.setVisible(true);
        } else {
            emptyLastName = false;
            emptyLastNameLabel.setVisible(false);
        }

        if (!employeeAddVM.validateFirstName()) {
            errorFirstNameLabel.setVisible(true);
            validFirstName = false;
        } else {
            errorFirstNameLabel.setVisible(false);
            validFirstName = true;
        }

        if (!employeeAddVM.validateLastName()) {
            errorLastNameLabel.setVisible(true);
            validLastName = false;
        } else {
            errorLastNameLabel.setVisible(false);
            validLastName = true;
        }

        if(!emptyFirstName && !emptyLastName && validFirstName && validLastName)
        {
            return true;
        }
        return false;
    }

    @FXML void onMessengerClicked(MouseEvent event) {
        employeeAddVM.openMessengerView();
    }
}

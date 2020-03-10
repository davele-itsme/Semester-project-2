package view.hq.hq.employeeHQ;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import viewmodel.hq.hq.employee.EmployeeAddHQVM;

/**
 * The view Class for the Headquarter's add Employee view.
 *
 * @author Kenneth Jensen
 * @author Floring Bordei
 * @author Jaime Lopez
 * @author Dave Joe Lê
 */

public class EmployeeAddHQView {
    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private Label errorFirstNameLabel;

    @FXML
    private Label emptyFirstNameLabel;

    @FXML
    private Label errorLastNameLabel;

    @FXML
    private Label emptyLastNameLabel;

    @FXML
    private AnchorPane anchorPane;

    private EmployeeAddHQVM employeeAddHQVM;

    /**
     * Creates an EmployeeAddHQView.
     */
    public EmployeeAddHQView() {

    }

    /**
     * An init method instantiating all the fields required.
     * @param employeeAddHQVM The {@link EmployeeAddHQVM} viewmodel to use.
     */
    public void init(EmployeeAddHQVM employeeAddHQVM) {
        this.employeeAddHQVM = employeeAddHQVM;
        firstNameField.textProperty().bindBidirectional(employeeAddHQVM.firstNameProperty());
        lastNameField.textProperty().bindBidirectional(employeeAddHQVM.lastNameProperty());
    }

    @FXML
    void onAddClicked(ActionEvent event) {
        if(isEverythingValid()) {
            employeeAddHQVM.addEmployee();
            employeeAddHQVM.confirmation();
        }
    }

    @FXML
    void onBackClicked(ActionEvent event) {
        employeeAddHQVM.goBack();
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
        employeeAddHQVM.openMainView();
    }

    @FXML
    void onEmployeeClicked(ActionEvent event) {
        employeeAddHQVM.openEmployeeMainHQ();
    }

    @FXML
    void onRetailerClicked(MouseEvent event) {
        employeeAddHQVM.openMainRTView();
    }

    @FXML
    void onInventoryClicked(ActionEvent event) {
        employeeAddHQVM.openInventoryMainHQView();
    }

    @FXML
    void onWarehouseClicked(MouseEvent event) {
        employeeAddHQVM.openMainWHView();
    }

    @FXML void onHQClicked(MouseEvent event)
    {
        employeeAddHQVM.openMainView();
    }

    private boolean isEverythingValid() {
        boolean emptyFirstName = false, emptyLastName = false,
                validFirstName = false, validLastName = false;

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

        if (!employeeAddHQVM.validateFirstName()) {
            errorFirstNameLabel.setText("Only letters are allowed");
            errorFirstNameLabel.setVisible(true);
            validFirstName = false;
        } else {
            errorFirstNameLabel.setVisible(false);
            validFirstName = true;
        }

        if (!employeeAddHQVM.validateLastName()) {
            errorLastNameLabel.setText("Only letters are allowed");
            errorLastNameLabel.setVisible(true);
            validLastName = false;
        } else {
            errorLastNameLabel.setVisible(false);
            validLastName = true;
        }

        if (!emptyFirstName && !emptyLastName && validFirstName && validLastName) {
            return true;
        }
        return false;
    }

    @FXML void onMessengerClicked(MouseEvent event)
    {
        employeeAddHQVM.openMessengerView();
    }

}

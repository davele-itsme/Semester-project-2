package viewmodel.hq.hq.employee;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Alert;
import model.Employee;
import model.IDataModel;
import view.hq.ViewHandler;

/**
 * The viewmodel Class for the Headquarters add Employee view.
 *
 * @author Kenneth Jensen
 * @author Floring Bordei
 * @author Jaime Lopez
 * @author Dave Joe Lê
 */

public class EmployeeAddHQVM { //This class is for adding employees to EmployeeList of HQ
    private StringProperty firstName;
    private StringProperty lastName;

    private ViewHandler viewHandler;
    private IDataModel dataModel;

    /**
     * Creates an EmployeeAddHQVM with the specified information.
     * @param dataModel The {@link model.DataModel} to be used.
     * @param viewHandler The {@link ViewHandler} to be used.
     */
    public EmployeeAddHQVM(IDataModel dataModel, ViewHandler viewHandler) {
        this.dataModel = dataModel;
        this.viewHandler = viewHandler;
        firstName = new SimpleStringProperty();
        lastName = new SimpleStringProperty();
    }

    /**
     * Adds an Employee to the {@link model.DataModel} with the current values.
     */
    public void addEmployee() {
        Employee e = new Employee(firstName.getValue(), lastName.getValue(), dataModel.getIDEmployee(), "HQ");
        dataModel.addEmployeeFromUser(e);
        System.out.println(e.getFirstName() + e.getLastName() + e.getId() + e.getDepartmentID());
        //dataModel.addEmployeeFromServer(e);
        firstName.setValue("");
        lastName.setValue("");
    }

    /**
     * This method opens the Headquarters main Employee view.
     */
    public void goBack() {
        viewHandler.openEmployeeMainHQView();
    }

    /**
     * Gets the first name {@link StringProperty}.
     * @return The first name {@link StringProperty}.
     */
    public StringProperty firstNameProperty() {
        return firstName;
    }

    /**
     * Gets the last name {@link StringProperty}.
     * @return The last name {@link StringProperty}.
     */
    public StringProperty lastNameProperty() {
        return lastName;
    }

    /**
     * This method opens the main view.
     */
    public void openMainView() {
        viewHandler.openMainHQView();
    }

    /**
     * This method opens the Headquarters main Employee view.
     */
    public void openEmployeeMainHQ() {
        viewHandler.openEmployeeMainHQView();
    }

    /**
     * This method asks the user for confirmation, using an {@link Alert}.
     */
    public void confirmation() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("Employee has been added");
        alert.setContentText("Press ok to continue");
        alert.showAndWait();

    }

    /**
     * This method validates a first name, making sure it only contains Letters.
     * @return Whether or not the first name only contained Letters.
     */
    public boolean validateFirstName() {
        return dataModel.onlyLetters(firstName.getValue());
    }

    /**
     * This method validates a last name, making sure it only contains Letters.
     * @return Whether or not the first name only contained Letters.
     */
    public boolean validateLastName() {
        return  dataModel.onlyLetters(lastName.getValue());
    }

    public void openInventoryMainHQView() { viewHandler.openInventoryMainHQView();
    }

    public void openMainRTView() {viewHandler.openMainRTView();
    }

    public void openMainWHView() {viewHandler.openMainWHView();
    }

    public void openMessengerView() {viewHandler.openMessengerView();
    }
}

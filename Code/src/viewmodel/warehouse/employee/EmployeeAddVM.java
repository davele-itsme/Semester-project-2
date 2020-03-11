package viewmodel.warehouse.employee;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Alert;
import model.Employee;
import model.IDataModel;
import view.warehouse.ViewHandler;

/**
 * This is the viewmodel Class for add Employee view.
 *
 * @author Kenneth Jensen
 * @author Floring Bordei
 * @author Jaime Lopez
 * @author Dave Joe Lê
 */

public class EmployeeAddVM {
    private StringProperty firstName;
    private StringProperty lastName;

    private IDataModel dataModel;
    private ViewHandler viewHandler;

    /**
     * Creates an EmployeeAddVM with the specified information.
     * @param dataModel The {@link model.DataModel} to be used.
     * @param viewHandler The {@link ViewHandler} to be used.
     */
    public EmployeeAddVM(IDataModel dataModel, ViewHandler viewHandler)
    {
        this.dataModel = dataModel;
        this.viewHandler = viewHandler;
        firstName = new SimpleStringProperty();
        lastName = new SimpleStringProperty();
    }

    /**
     * Adds an {@link Employee} with the current information.
     */
    public void addEmployee()
    {
        Employee e = new Employee(firstName.getValue(), lastName.getValue(), dataModel.getIDEmployee(),"WH");
        dataModel.addEmployeeFromUser(e);
        //dataModel.addEmployeeFromServer(e);
        firstName.setValue("");
        lastName.setValue("");
    }

    /**
     * This method opens the main Employee view.
     */
    public void goBack()
    {
        viewHandler.openEmployeeMainView();
    }

    /**
     * This method opens the main view.
     */
    public void openMainView()
    {
        viewHandler.openMainView();
    }

    /**
     * This method opens the main Inventory view.
     */
    public void openInventoryView()
    {
        viewHandler.openInventoryMainView();
    }

    /**
     * This method opens the main Employee view.
     */
    public void openEmployeeView()
    {
        viewHandler.openEmployeeMainView();
    }

    /**
     * Gets the first name {@link StringProperty} stored.
     * @return The first name {@link StringProperty} stored.
     */
    public StringProperty firstNameProperty() {
        return firstName;
    }

    /**
     * Gets the last name {@link StringProperty} stored.
     * @return The last name {@link StringProperty} stored.
     */
    public StringProperty lastNameProperty() {
        return lastName;
    }

    /**
     * This method asks the user for confirmation using a {@link Alert}.
     */
    public void confirmation() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("Employee has been added");
        alert.setContentText("Press ok to continue");
        alert.showAndWait();
    }

    /**
     * This method validates that the first name only contains Letters.
     * @return Whether the first name only contains Letters or not.
     */
    public boolean validateFirstName() {
        return dataModel.onlyLetters(firstName.getValue());
    }

    /**
     * This method validates that the last name only contains Letters.
     * @return Whether the last name only contains Letters or not.
     */
    public boolean validateLastName() {
        return  dataModel.onlyLetters(lastName.getValue());
    }

    public void openProductRequestView() {viewHandler.openProductRequestView();
    }

    public void openDeliveryMainView() {viewHandler.openDeliveryMainView();
    }

    public void openMessengerView() {viewHandler.openMessengerView();
    }
}

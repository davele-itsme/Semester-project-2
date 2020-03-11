package viewmodel.warehouse.employee;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Employee;
import model.EmployeeList;
import model.IDataModel;
import view.warehouse.ViewHandler;

import java.beans.PropertyChangeEvent;

/**
 * This is the viewmodel Class for the main Employye view.
 *
 * @author Kenneth Jensen
 * @author Floring Bordei
 * @author Jaime Lopez
 * @author Dave Joe Lê
 */

public class EmployeeMainVM {
    private IDataModel dataModel;
    private ObservableList<Employee> employees;
    private ViewHandler viewHandler;

    /**
     * Creates an EmployeeMainVM with the specified information and adds the needed Listeners.
     *
     * @param dataModel   The {@link model.DataModel} to be used.
     * @param viewHandler The {@link ViewHandler} to be used.
     */
    public EmployeeMainVM(IDataModel dataModel, ViewHandler viewHandler) {
        this.dataModel = dataModel;
        this.viewHandler = viewHandler;
        employees = FXCollections.observableArrayList(); // = new ObservableListWrapper<>(new ArrayList<>());
        dataModel.addListener("NewEmployeeFromUser", this::addEmployeeToClient);
        dataModel.addListener("NewEmployeeFromServer", this::addEmployeeToClient);
        dataModel.addListener("NewEmployeeList", this::loadList);
    }

    private void loadList(PropertyChangeEvent evt) {
        EmployeeList employeeList = (EmployeeList) evt.getNewValue();
        employees.removeAll(employees);
        for (int i = 0; i < employeeList.size(); i++) {
            if (employeeList.get(i).getDepartmentID().equals("WH"))
            {
                employees.add(employeeList.get(i));
            }
        }
    }

    /**
     * The method called by the Listener added at {@link this#EmployeeMainVM(IDataModel, ViewHandler)}.
     * This method adds the Employee stored in the {@link PropertyChangeEvent} data.
     *
     * @param evt The {@link PropertyChangeEvent} that caused the Listener to call this method.
     */
    private void addEmployeeToClient(PropertyChangeEvent evt) {
        Employee employee = (Employee) evt.getNewValue();
        if(employee.getDepartmentID().equals("WH"))
        {
            employees.add(employee);
        }

    }

    /**
     * Gets the {@link Employee}s stored.
     *
     * @return The {@link Employee}s stored.
     */
    public ObservableList<Employee> getEmployees() {
        return employees;
    }

    /**
     * This method opens the main view.
     */
    public void openMainView() {
        viewHandler.openMainView();
    }

    /**
     * This method opens the main Inventory view.
     */
    public void openInventoryView() {
        viewHandler.openInventoryMainView();
    }

    /**
     * This method opens the add Employee view.
     */
    public void openEmployeeAddView() {
        viewHandler.openEmployeeAddView();
    }

    /**
     * This method removes an {@link Employee} from the List.
     *
     * @param e The {@link Employee} to be removed.
     */
    public void removeEmployee(Employee e) {
        dataModel.removeEmployeeWH(e);
    }

    public void openProductRequestView() {
        viewHandler.openProductRequestView();
    }

    public void openDeliveryMainView() {
        viewHandler.openDeliveryMainView();
    }

    public void openMessengerView() {viewHandler.openMessengerView();
    }
}

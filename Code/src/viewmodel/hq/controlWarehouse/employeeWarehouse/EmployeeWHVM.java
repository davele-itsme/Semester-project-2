package viewmodel.hq.controlWarehouse.employeeWarehouse;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Employee;
import model.EmployeeList;
import model.IDataModel;
import view.hq.ViewHandler;

import java.beans.PropertyChangeEvent;

/**
 * This is the viewmodel Class for the Warehouse Employee view.
 *
 * @author Kenneth Jensen
 * @author Floring Bordei
 * @author Jaime Lopez
 * @author Dave Joe Lê
 */

public class EmployeeWHVM { //This class is to display employeeList from warehouse
    private ObservableList<Employee> employees;
    private ViewHandler viewHandler;
    private IDataModel dataModel;

    /**
     * Creates the EmployeeWHVM
     *
     * @param dataModel   The {@link model.DataModel} to be used.
     * @param viewHandler The {@link ViewHandler} to be used.
     */
    public EmployeeWHVM(IDataModel dataModel, ViewHandler viewHandler) {
        this.viewHandler = viewHandler;
        this.dataModel = dataModel;
        employees = FXCollections.observableArrayList(); // = new ObservableListWrapper<>(new ArrayList<>());
        dataModel.addListener("NewEmployeeFromUser", this::addEmployeeToClient); //TODO: Delete one of these to just initialize the table view
        dataModel.addListener("NewEmployeeFromServer", this::addEmployeeToClient);
        dataModel.addListener("NewEmployeeList", this::loadList);
    }

    private void loadList(PropertyChangeEvent evt) {
        EmployeeList employeeList = (EmployeeList) evt.getNewValue();
        employees.removeAll(employees);
        for (int i = 0; i < employeeList.size(); i++) {
            if(employeeList.get(i).getDepartmentID().equals("WH"))
            {
                employees.add(employeeList.get(i));
            }
        }
    }

    private void addEmployeeToClient(PropertyChangeEvent evt) {
        Employee employee = (Employee) evt.getNewValue();
        if(employee.getDepartmentID().equals("WH"))
        {
            employees.add(employee);
        }
    }

    /**
     * Gets the {@link Employee}s stored
     *
     * @return the {@link Employee}s stored
     */
    public ObservableList<Employee> getEmployees() {
        return employees;
    }

    /**
     * This method opens the main Headquarters view.
     */
    public void openMainHQView() {
        viewHandler.openMainHQView();
    }

    /**
     * This method opens the Warehouse inventory view.
     */
    public void openInventoryWHView() {
        viewHandler.openInventoryMainWHView();
    }

    /**
     * This method opens the main Warehouse Employee view.
     */
    public void openEmployeeMainWHView() {
        viewHandler.openEmployeeMainWHView();
    }

    public void openDeliveryView() {viewHandler.openDeliveryMainWHView();
    }

    public void openMainRTView() {
        viewHandler.openMainRTView();
    }

    public void openMainWHView() {
        viewHandler.openMainWHView();
    }
}

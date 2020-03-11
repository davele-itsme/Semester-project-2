package viewmodel.hq.controlRetailer.employeeRetailer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Employee;
import model.EmployeeList;
import model.IDataModel;
import view.hq.ViewHandler;

import java.beans.PropertyChangeEvent;

public class EmployeeRTVM {
    private ObservableList<Employee> employees;
    private ViewHandler viewHandler;
    private IDataModel dataModel;

    public EmployeeRTVM(IDataModel dataModel, ViewHandler viewHandler) {
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
            if(employeeList.get(i).getDepartmentID().equals("RT"))
            {
                employees.add(employeeList.get(i));
            }

        }
    }

    private void addEmployeeToClient(PropertyChangeEvent evt) {
        Employee employee = (Employee) evt.getNewValue();
        if(employee.getDepartmentID().equals("RT"))
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
    public void openInventoryRTView() {
        viewHandler.openInventoryMainRTView();
    }

    /**
     * This method opens the main Warehouse Employee view.
     */
    public void openEmployeeMainRTView() {
        viewHandler.openEmployeeMainRTView();
    }

    /**
     * This method removes an {@link Employee}.
     *
     * @param e The {@link Employee} to be removed.
     */
    public void removeEmployee(Employee e) {
        dataModel.removeEmployeeHQ(e);
    }

    public void openDeliveryView() {viewHandler.openDeliveryMainRTView();
    }

    public void openMainRTView() {
        viewHandler.openMainRTView();
    }

    public void openMainWHView() {
        viewHandler.openMainWHView();
    }
}

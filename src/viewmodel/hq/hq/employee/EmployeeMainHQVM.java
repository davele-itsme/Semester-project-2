package viewmodel.hq.hq.employee;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Employee;
import model.EmployeeList;
import model.IDataModel;
import view.hq.ViewHandler;

import java.beans.PropertyChangeEvent;

/**
 * The viewmodel class for the Headquarters main Employee view.
 *
 * @author Kenneth Jensen
 * @author Floring Bordei
 * @author Jaime Lopez
 * @author Dave Joe Lê
 */

public class EmployeeMainHQVM { //This class is to display employeeList of HQ
    private IDataModel dataModel;
    private ObservableList<Employee> employees;
    private ViewHandler viewHandler;

    /**
     * Creates an EmployeeMainHQVM with the specified information and adds a listener for adding {@link Employee}s.
     * @param dataModel The {@link model.DataModel} to be used.
     * @param viewHandler The {@link ViewHandler} to be used.
     */
    public EmployeeMainHQVM(IDataModel dataModel, ViewHandler viewHandler)
    {
        this.dataModel = dataModel;
        this.viewHandler = viewHandler;
        employees = FXCollections.observableArrayList();
        dataModel.addListener("NewEmployeeFromUser", this::addEmployeeToClient);
        dataModel.addListener("NewEmployeeFromServer",this::addEmployeeToClient);
        dataModel.addListener("NewEmployeeList", this::loadList);
    }

    private void loadList(PropertyChangeEvent evt) {
        EmployeeList employeeList = (EmployeeList) evt.getNewValue();
        employees.removeAll(employees);
        for (int i = 0; i < employeeList.size(); i++) {
            if(employeeList.get(i).getDepartmentID().equals("HQ"))
            {
                employees.add(employeeList.get(i));
            }
        }
    }

    /**
     * This method is called when the Listener added in {@link EmployeeMainHQVM#EmployeeMainHQVM(IDataModel, ViewHandler)} is triggered,
     * and adds an Employee to the List.
     * @param evt The {@link PropertyChangeEvent} that caused this method to be called.
     */
    private void addEmployeeToClient(PropertyChangeEvent evt) {
        Employee employee = (Employee) evt.getNewValue();
        if(employee.getDepartmentID().equals("HQ"))
        {
            employees.add(employee);
        }
    }

    /**
     * Gets the {@link Employee}s stored in the List.
     * @return The {@link Employee}s stored in the List.
     */
    public ObservableList<Employee> getEmployees() {
        return employees;
    }

    /**
     * This method opens the main view.
     */
    public void openMainView() {
        viewHandler.openMainHQView();
    }

    /**
     * This method opens the add Employee view.
     */
    public void openEmployeeAddView()
    {
        viewHandler.openEmployeeAddHQView();
    }

    /**
     * Removes an {@link Employee} from the List.
     * @param e The {@link Employee} to be removed.
     */
    public void removeEmployee(Employee e) {dataModel.removeEmployeeHQ(e);
    }

    public void openInventoryMainHQView() {viewHandler.openInventoryMainHQView();
    }

    public void openMainRTView() {viewHandler.openMainRTView();
    }

    public void openMainWHView() {viewHandler.openMainWHView();
    }

    public void openEmployeeMainHQView() {viewHandler.openEmployeeMainHQView();
    }

    public void openMessengerView() {viewHandler.openMessengerView();
    }
}

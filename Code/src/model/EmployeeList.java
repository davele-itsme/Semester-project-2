package model;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>EmployeeList class for storing a list of Employees</h1>
 * This is a list specifically for {@link Employee}'s.
 *
 * @author Kenneth Jensen
 * @author Floring Bordei
 * @author Jaime Lopez
 * @author Dave Joe Lê
 */
public class EmployeeList {

    private ArrayList<Employee> employeeList;

    /**
     * Creates an EmployeeList and instantiates the {@link ArrayList} stored.
     */
    public EmployeeList(){
        employeeList = new ArrayList<>();
    }

    /**
     * Adds an {@link Employee} to the List.
     * @param e the {@link Employee} to add.
     */
    public void add(Employee e){
        employeeList.add(e);
    }

    /**
     * Adds a {@link List} of Employees to the {@link EmployeeList}.
     * @param employees The List to add.
     */
    public void add(List<Employee> employees){
        for(Employee e : employees){
            add(e);
        }
    }

    /**
     * Removes an {@link Employee} from the List.
     * @param index The index of the {@link Employee} to remove.
     */
    public void remove(int index){
        employeeList.remove(index);
    }

    /**
     * Removes an {@link Employee} from the List.
     * @param e The {@link Employee} to remove.
     */
    public void remove(Employee e){
        for(int i = 0; i < employeeList.size(); i++){
            if(employeeList.get(i).equals(e)){
                employeeList.remove(i);
            }
        }
    }

    /**
     * Gets the {@link Employee} at the specified index.
     * @param index The index to return.
     * @return The {@link Employee} at the requested index.
     */
    public Employee get(int index){
        return employeeList.get(index);
    }

    /**
     * Gets the size of the List.
     * @return The size of the List.
     */
    public int size(){
        return employeeList.size();
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof EmployeeList)){
            return false;
        }
        EmployeeList other = (EmployeeList) obj;
        if(this.size() != other.size()){
            return false;
        }
        for(int i = 0; i < this.size(); i++){
            if(this.get(i).equals(other.get(i))){
                return false;
            }
        }
        return true;
    }
}

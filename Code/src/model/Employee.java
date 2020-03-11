package model;

/**
 * <h1>Employee Class for storing information about Employees</h1>
 * The Class stored the name, id and department the Employee works in.
 *
 * @author Kenneth Jensen
 * @author Floring Bordei
 * @author Jaime Lopez
 * @author Dave Joe Lê
 */
public class Employee {

    /**
     * The Employee's first name.
     */
    private String firstName;
    /**
     * The Employee's last name.
     */
    private String lastName;
    /**
     * The Employee's ID.
     */
    private String id;
    /**
     * The Employee's Department's ID.
     */
    private String departmentID;

    /**
     * Creates an Employee with the specified information.
     * @param firstName The first name of the Employee.
     * @param lastName The last name of the Employee.
     * @param id the ID of the Employee.
     * @param departmentID The ID of the Employee's Department.
     */
    public Employee(String firstName, String lastName, String id, String departmentID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.departmentID = departmentID;
    }

    /**
     * Gets the {@link Employee}'s first name.
     * @return The Employee's first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the {@link Employee}'s first name.
     * @param firstName The first name to be stored.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the {@link Employee}'s last name.
     * @return The Employee's last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets The {@link Employee}'s last name.
     * @param lastName The last name to be stored.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the {@link Employee} ID.
     * @return The Employee's ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the {@link Employee} ID.
     * @param id The ID to be stored.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the Deparment ID.
     * @return The Department ID.
     */
    public String getDepartmentID() {
        return departmentID;
    }

    /**
     * Checks whether this {@link Employee} is equal to the passed {@link Employee}.
     * @param obj {@link Object} to be tested.
     * @return Whether or not the Employee's were equal to each other.
     */
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Employee)){
            return false;
        }
        Employee other = (Employee) obj;
        return other.firstName.equals(this.firstName)
                && other.lastName.equals(this.lastName);
    }

    /**
     * Converts the data stored in the {@link Employee} to a {@link String}.
     * @return The {@link String} created.
     */
    @Override
    public String toString() {
        return "First name: " + firstName + ", Last name: " + lastName + ", ID: " + id + ", department ID: " + departmentID;
    }
}

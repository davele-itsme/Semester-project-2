package Testing;

import model.Employee;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EmployeeTest {

    Employee employee;

    @Before
    public void setUp() throws Exception {
        employee = new Employee("Kenneth", "Jensen", "1", "RT");
    }

    @Test
    public void setFirstName() {
        Assert.assertEquals("Kenneth", employee.getFirstName());
        employee.setFirstName("John");
        Assert.assertEquals("John", employee.getFirstName());
    }

    @Test
    public void setLastName() {
        Assert.assertEquals("Jensen", employee.getLastName());
        employee.setLastName("John");
        Assert.assertEquals("John", employee.getLastName());
    }

    @Test
    public void setId() {
        Assert.assertEquals("1", employee.getId());
        employee.setId("John");
        Assert.assertEquals("John", employee.getId());
    }

    @Test
    public void getDepartmentID() {
        Assert.assertEquals("RT", employee.getDepartmentID());
    }
}
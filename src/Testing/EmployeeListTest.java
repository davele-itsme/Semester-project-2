package Testing;

import model.Employee;
import model.EmployeeList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class EmployeeListTest {

    EmployeeList eList;

    @Before
    public void setUp() throws Exception {
        eList = new EmployeeList();
    }

    @Test
    public void add() {
        Assert.assertEquals(0, eList.size());
        eList.add(new Employee("1", "1", "1", "1"));
        Assert.assertEquals(1, eList.size());
    }

    @Test
    public void add1() {
        Assert.assertEquals(0, eList.size());
        ArrayList<Employee> eList2 = new ArrayList<>();
        eList2.add(new Employee("1", "1", "1", "1"));
        eList2.add(new Employee("1", "1", "1", "1"));
        eList.add(eList2);
        Assert.assertEquals(2, eList.size());
    }

    @Test
    public void remove() {
        eList.add(new Employee("1", "1", "1", "1"));
        Assert.assertEquals(1, eList.size());
        eList.remove(0);
        Assert.assertEquals(0, eList.size());
    }

    @Test
    public void remove1() {
        Employee e = new Employee("1", "1", "1", "1");
        eList.add(e);
        Assert.assertEquals(1, eList.size());
        eList.remove(e);
        Assert.assertEquals(0, eList.size());
    }
}
package model;

/**
 * <h1>Counter Class used for ID creation</h1>
 * The Counter Class stores an int for both employees added and Stock Items added.
 * These are used an incremented when creating a new Employee or Stock Item ID.
 *
 * @author Kenneth Jensen
 * @author Floring Bordei
 * @author Jaime Lopez
 * @author Dave Joe Lê
 */

public class Counter {
    /**
     * The counter for Stock Items
     */
    private static int counterStockItem = 0;
    /**
     * The counter for Employees
     */
    private static int counterEmployee = 0;

    public static void setupCounter(int stockItemMaxId, int employeeMaxID){
        counterStockItem = stockItemMaxId + 1;
        counterEmployee = employeeMaxID + 1;
    }

    private static synchronized void incrementEmployee()
    {
        counterEmployee++;
    }

    private static synchronized void incrementStockItem()
    {
        counterStockItem++;
    }

    /**
     * Increments the counter for the Employee and returns an ID using that value
     * @return The created Employee ID
     */
    public static String getIDEmployee()
    {
        char fill = '0';
        int id = counterEmployee;
        incrementEmployee();
        String finalID = new String(new char[6]).replace('\0', fill) + id;
        return finalID;
    }

    /**
     * Increments the counter for the Employee and returns an ID using that value
     * @return The created Stock Item ID
     */
    public static String getIDStockItem()
    {
        char fill = '0';
        int id = counterStockItem;
        incrementStockItem();
        String finalID = new String(new char[6]).replace('\0', fill) + id;
        return finalID;
    }

}

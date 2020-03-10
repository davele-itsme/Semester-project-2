package network;

import java.io.Serializable;

/**
 * <h1>Packet Class used for sending information between Server and Client</h1>
 * This Class contains a static reference to the strings to be sent as a representation
 * of the operation this Packet was created to convey.
 *
 * @author Kenneth Jensen
 * @author Floring Bordei
 * @author Jaime Lopez
 * @author Dave Joe Lê
 */

public class Packet implements Serializable {

    public static final String messageQuery = "MessageQuery";
    public static final String employeeUpdate ="EmployeeUpdate";
    public static final String itemUpdate ="ItemUpdate";
    public static final String salesRefresh ="SalesRefresh";
    private String operation, Json;
    /**
     * The Employee operation {@link String}.
     */
    public static final String EmployeeOperation = "Employees";
    /**
     * The Stock Item operation {@link String}.
     */
    public static final String StockOperation = "StockItems";
    /**
     * The Request operation {@link String}.
     */
    public static final String RequestOperation = "ProductRequest";
    /**
     * The Employee Server response operation {@link String}.
     */
    public static final String EmployeeResponseOperation = "EmployeeResponse";
    /**
     * The Stock Item Server response operation {@link String}.
     */
    public static final String StockResponseOperation = "StockResponse";
    /**
     * The Employee Query operation {@link String}.
     */
    public static final String EmployeeQuery = "EmployeeQuery";
    /**
     * The Stock Item Query {@link String}.
     */
    public static final String ItemQuery = "ItemQuery";
    /**
     * The remove Stock Item from Warehouse operation {@link String}.
     */
    public static final String DeleteItemFromWH = "DeleteItemFromWH";
    /**
     * The remove Stock Item from Headquarters operation {@link String}.
     */
    public static final String DeleteItemFromHQ = "DeleteItemFromHQ";


    public static final String DeleteEmployee = "DeleteEmployee";
    public static final String AddSale = "AddSale";
    public static final String salesQuery = "SalesQuery";
    public static final String addProductRequest = "AddProductRequest";
    public static final String requestQuery = "RequestQuery";
    public static final String requestRefresh = "RequestRefresh";
    public static final String deliveriesQuery = "DeliveriesQuery";
    public static final String message = "Message";

    /**
     * Creates a Packet with the specified information.
     * @param operation The operation to use. For available operations see {@link this#EmployeeOperation}.
     * @param json The Json {@link String} of the file to be send with this Packet.
     */
    public Packet(String operation, String json) {
        this.operation = operation;
        Json = json;
    }

    /**
     * Gets the operation stored in the Packet.
     * @return The operation stored in the Packet.
     */
    public String getOperation() {
        return operation;
    }

    /**
     * Gets the Json {@link String} stored in the Packet.
     * @return The Json {@link String} stored in the Packet.
     */
    public String getJson() {
        return Json;
    }
}

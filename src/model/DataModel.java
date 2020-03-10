package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

/**
 * <h1>The Model class from the MVVM patterns.</h1>
 * This incorporates all the methods within our
 * model and functions as the Class in which we store and access our data.
 *
 * @author Kenneth Jensen
 * @author Floring Bordei
 * @author Jaime Lopez
 * @author Dave Joe Lê
 */
public class DataModel implements IDataModel {
    /**
     * The stored list of Employees.
     */
    private EmployeeList employeeList;
    /**
     * The stored list of Stock Items.
     */
    private StockItemList stockItemList;
    private PropertyChangeSupport propertyChangeSupport;
    /**
     * The stored list of Product Requests.
     */
    private ProductRequestList productRequestList;
    private StockItemList sales;
    private DeliveryList deliveryList;
    private MessageList messages;
    private String department;

    /**
     * Creates a DataModel and instantiate all the fields.
     */
    public DataModel() {
        employeeList = new EmployeeList();
        propertyChangeSupport = new PropertyChangeSupport(this);
        stockItemList = new StockItemList();
        productRequestList = new ProductRequestList();
        sales = new StockItemList();
        deliveryList = new DeliveryList();
        messages = new MessageList();
        department="";
    }

    /**
     * Gets the {@link EmployeeList} stored in the DataModel.
     *
     * @return The {@link EmployeeList} stored in the DataModel
     */
    public EmployeeList getEmployeeList() {
        return employeeList;
    }

    /**
     * Adds an {@link Employee} sent from a user to the stored {@link EmployeeList}.
     *
     * @param e {@link Employee} to be stored.
     */

    @Override
    public void addEmployeeFromUser(Employee e) {
        employeeList.add(e);
        propertyChangeSupport.firePropertyChange("NewEmployeeFromUser", null, e);
    }

    /**
     * Adds an {@link Employee} sent from the server to the stored {@link EmployeeList}.
     *
     * @param e {@link Employee} to be stored.
     */
    @Override
    public void addEmployeeFromServer(Employee e) {
        employeeList.add(e);
        propertyChangeSupport.firePropertyChange("NewEmployeeFromServer", null, e);
    }

    @Override
    public void addListener(String evt, PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(evt, listener);
    }

    /**
     * Adds a {@link StockItem} sent from the server to the stored {@link StockItemList}.
     *
     * @param i The {@link StockItem} to be stored.
     */
    @Override
    public void addItemFromServer(StockItem i) {
        stockItemList.add(i);
        propertyChangeSupport.firePropertyChange("NewItemFromServer", null, i);
    }

    /**
     * Adds a {@link StockItem} sent from a user to the stored {@link StockItemList}.
     *
     * @param i The {@link StockItem} to be stored.
     */
    @Override
    public void addItemFromUser(StockItem i) {
        stockItemList.add(i);
        propertyChangeSupport.firePropertyChange("NewItemFromUser", null, i);

    }

    /**
     * Gets the {@link StockItemList} stored in the DataModel.
     *
     * @return The {@link StockItemList} stored in the DataModel.
     */
    public StockItemList getStockItemList() {
        return stockItemList;
    }


    /**
     * Adds a {@link ProductRequest} to the {@link ProductRequestList} stored in the DataModel.
     */
    //TODO: Pass an Item to request?
    @Override
    public void sendProductRequest(String department) {
        propertyChangeSupport.firePropertyChange("SendProductRequest", null, department);

    }

    /**
     * Deletes a {@link StockItem} from the DataModel.
     *
     * @param stockItem the {@link StockItem} to be removed.
     */
    @Override
    public void removeStockItemWH(StockItem stockItem) {
        stockItemList.remove(stockItem);
        propertyChangeSupport.firePropertyChange("DeleteItemFromWH", null, stockItem); //
    }

    /**
     * Deletes a {@link StockItem} from the DataModel.
     *
     * @param stockItem the {@link StockItem} to be removed.
     */
    @Override
    public void removeStockItemHQ(StockItem stockItem) {
        stockItemList.remove(stockItem);
        propertyChangeSupport.firePropertyChange("DeleteItemFromWH", null, stockItem);
        //todo for now we dont have stock item department so we need different methods, we can change it if we agree in something
    }

    /**
     * Deletes a {@link Employee} from the DataModel.
     *
     * @param e the {@link Employee} to be removed.
     */
    @Override
    public void removeEmployeeWH(Employee e) {
        employeeList.remove(e);
        propertyChangeSupport.firePropertyChange("DeleteEmployee", null, e);

    }

    /**
     * Deletes a {@link Employee} from the DataModel.
     *
     * @param e the {@link Employee} to be removed.
     */
    @Override
    public void removeEmployeeHQ(Employee e) {
        employeeList.remove(e);
        propertyChangeSupport.firePropertyChange("DeleteEmployee", null, e);
    }

    /**
     * Gets an Employee ID.
     *
     * @return The created Employee ID.
     */
    @Override
    public String getIDEmployee() {
        String id = Counter.getIDEmployee();
        return id;
    }

    /**
     * Gets a Stock Item ID.
     *
     * @return The created Stock Item ID.
     */
    @Override
    public String getIDStockItem() {
        String id = Counter.getIDStockItem();
        return id;
    }

    /**
     * Checks whether a {@link String} only contains Letters.
     *
     * @param word The {@link String} to be tested.
     * @return Whether or not the {@link String} contained only Letters.
     */
    @Override
    public boolean onlyLetters(String word) {
        Validation validation = new Validation();
        boolean onlyLetters = validation.onlyLetters(word);
        return onlyLetters;
    }

    /**
     * Checks whether a {@link String} only contains Numbers.
     *
     * @param word the {@link String} to be tested.
     * @return whether or not the {@link String} contained only Numbers.
     */
    @Override
    public boolean onlyNumbers(String word) {
        Validation validation = new Validation();
        boolean onlyNumbers = validation.onlyNumbers(word);
        return onlyNumbers;
    }

    /**
     * Loads the EmployeeList stored in the DataBase.
     */
    @Override
    public void loadEmployeeListFromDB(String departmentID) {
        propertyChangeSupport.firePropertyChange("EmployeeQuery", 0, department);
        System.out.println("DataModel refresh Employee list query");
    }

    /**
     * Loads the StockItemList stored in the DataBase.
     */
    @Override
    public void loadItemListFromDB() {
        propertyChangeSupport.firePropertyChange("ItemQuery", 0, department);
        System.out.println("DataModel refresh Item list query");
    }

    // TODO: JAVADOC
    public boolean controlPkEmployee(String PK) {
        for (int i = 0; i < employeeList.size(); i++) {
            if (employeeList.get(i).getId().equals(PK))
                return false;
        }
        return true;
    }

    @Override
    public void addToProductRequest(ProductRequest productRequest, boolean notifyServer) {
        productRequestList.addRequestToList(productRequest);

        if (notifyServer == true) {
            propertyChangeSupport.firePropertyChange("AddProductRequest", null, productRequest);
        }
        propertyChangeSupport.firePropertyChange("AddProductRequestView", null, productRequest);
        System.out.println("DataModel: product : " + productRequest.getProductId() + " added to model.productRequest");


    }

    @Override
    public void addToSales(StockItem selectedItem, boolean notifyServer) {
        sales.add(selectedItem.copy());
        int quantity = selectedItem.getQuantity();
        int costOfGoods = (selectedItem.getPrice()/2) * quantity;
        int operationalCost = quantity;
        int profit = (costOfGoods - operationalCost);

        if (notifyServer == true) {
            //notifies Client sender
            propertyChangeSupport.firePropertyChange("AddSale", null, selectedItem);
            propertyChangeSupport.firePropertyChange("AddSaleView", null, selectedItem);
            propertyChangeSupport.firePropertyChange("PieChartUpdate", null, new int[]{costOfGoods, profit, operationalCost});
            System.out.println(selectedItem.getName());
        } else {
            System.out.println("DataModel:" + selectedItem.getId() + " Item added to sales");
            propertyChangeSupport.firePropertyChange("AddSaleView", null, selectedItem);
            propertyChangeSupport.firePropertyChange("PieChartUpdate", null, new int[]{costOfGoods, profit, operationalCost});
        }
    }

    @Override
    public void loadSalesFromDB() {
        propertyChangeSupport.firePropertyChange("SalesQuery", 1, 2);
        System.out.println("DataModel : Sales query");

    }

    @Override
    public void removeProductRequest(ProductRequest selectedItem) {
        productRequestList.removeRequestFromList(selectedItem.getProductId()); //TODO: How do I get productID saved to product request?
        propertyChangeSupport.firePropertyChange("DeleteProductRequest", null, selectedItem); //TODO: Remove in DB
    }

    @Override
    public void editProductRequest(ProductRequest selectedItem, int quantity) {
        int oldQuantity = selectedItem.getQuantity();
        selectedItem.setQuantity(quantity);
        propertyChangeSupport.firePropertyChange("EditProductRequest", oldQuantity, quantity); //TODO: Edit in DBs
    }

    @Override
    public void loadRequestsFromDB(String departmentID) {
        propertyChangeSupport.firePropertyChange("RequestQuery", 0, departmentID);
    }

    @Override
    public void loadDeliveriesListFromDB(String departmentID) {
        propertyChangeSupport.firePropertyChange("DeliveriesQuery",0,departmentID);
    }

    @Override
    public void openDelivery(Delivery delivery)
    {
        propertyChangeSupport.firePropertyChange("OpenDelivery", null, delivery);
    }
    /**
     * Sets the stored {@link StockItemList} equal to the passed {@link StockItemList}.
     *
     * @param stockItemList The {@link StockItemList} to be stored.
     */
    public void setStockItemList(StockItemList stockItemList) {
        this.stockItemList = stockItemList;
        System.out.println("DataModel: ItemList Received from server and stored");
        propertyChangeSupport.firePropertyChange("NewStockItemList", null, stockItemList);
    }

    @Override
    public void setDeliveryList(DeliveryList deliveryList) {
        this.deliveryList = deliveryList;
        propertyChangeSupport.firePropertyChange("NewDeliveryList", null, deliveryList);
    }

    @Override
    public void setRequestList(ProductRequestList productRequestList) {
        this.productRequestList = productRequestList;
        propertyChangeSupport.firePropertyChange("NewRequestList", null, productRequestList);
    }

    @Override
    public void setSalesList(StockItemList salesList) {
        this.sales=salesList;

        for (int i=0;i<salesList.size();i++)
        {
            System.out.println("SALES: " + salesList.get(i).getName() + " QUANTITY: " + salesList.get(i).getQuantity() + " PRICE: " + salesList.get(i).getPrice());
        }

        int costOfGoods = 0, profit = 0, operationalCost = 0;
        for(int i =0; i < salesList.size(); i++)
        {
            costOfGoods += (salesList.get(i).getPrice()/2) * salesList.get(i).getQuantity();
            operationalCost += salesList.get(i).getQuantity();
            profit += (salesList.get(i).getPrice()/2) * salesList.get(i).getQuantity() - salesList.get(i).getQuantity();
        }
        propertyChangeSupport.firePropertyChange("PieChartLoad", null, new int[]{costOfGoods, profit, operationalCost});
        propertyChangeSupport.firePropertyChange("NewSalesList", null, salesList);
    }

    /**
     * Sets the stored {@link EmployeeList} equal to the passed {@link EmployeeList}.
     *
     * @param employeeList the {@link EmployeeList} to be stored.
     */
    public void setEmployeeList(EmployeeList employeeList) {
        this.employeeList = employeeList;
        for (int i = 0; i < employeeList.size(); i++) {
            System.out.println("DataModel " + employeeList.get(i).getFirstName());
        }

        propertyChangeSupport.firePropertyChange("NewEmployeeList", null, employeeList);
    }
    @Override
    public void sendMessage(Message message)
    {
        addMessage(message);
        propertyChangeSupport.firePropertyChange("SendMessage",null,message);
    }

    @Override
    public void addMessage(Message message) {
        messages.addMessage(message);
        System.out.println("DataModel:  addMessage() -> "+message.getMessage());
        propertyChangeSupport.firePropertyChange("AddMessageView",null,message);
    }

    @Override
    public void setMessageList(MessageList messages) {

        System.out.println("MESSAGELIST TRIGGERED");
        this.messages = messages;

        for (int i=0;i<messages.size();i++)
        {
            System.out.println(messages.getMessage(i).toString());
        }
        propertyChangeSupport.firePropertyChange("NewMessageList", null, messages);
    }

    @Override
    public void loadMessagesFromDB() {
        propertyChangeSupport.firePropertyChange("MessagesQuery",0,1);
    }

    @Override
    public void setDepartment(String departmentID) {
        this.department=departmentID;
    }

}
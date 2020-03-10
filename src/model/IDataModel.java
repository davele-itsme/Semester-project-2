package model;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public interface IDataModel {
    void addEmployeeFromUser(Employee e);

    void addEmployeeFromServer(Employee e);

    void addListener(String evt, PropertyChangeListener listener);

    void addItemFromServer(StockItem i);

    void addItemFromUser(StockItem i);

    EmployeeList getEmployeeList();

    StockItemList getStockItemList();

    void setEmployeeList(EmployeeList employeeList);

    void loadEmployeeListFromDB(String departmentID);

    void loadItemListFromDB();

    void openDelivery(Delivery delivery);

    void setStockItemList(StockItemList stockItemList1);

    void sendProductRequest(String department);

    void removeStockItemWH(StockItem stockItem);

    void removeStockItemHQ(StockItem stockItem);

    void removeEmployeeWH(Employee e);

    void removeEmployeeHQ(Employee e);

    String getIDEmployee();

    String getIDStockItem();

    boolean onlyLetters(String word);

    boolean onlyNumbers(String word);

    void addToProductRequest(ProductRequest productRequest,boolean notifyServer);

    void addToSales(StockItem selectedItem, boolean notifyServer);

    void loadSalesFromDB();

    void removeProductRequest(ProductRequest selectedItem);

    void editProductRequest(ProductRequest selectedItem, int quantity);

    void loadRequestsFromDB(String departmentID);

    void setRequestList (ProductRequestList productRequestList);

    void setSalesList (StockItemList salesList);
    void loadDeliveriesListFromDB (String departmentID);

    void setDeliveryList(DeliveryList deliveryList);
    void sendMessage (Message message);
    void addMessage(Message message);
    void setMessageList(MessageList messages);
    void loadMessagesFromDB ();
    void setDepartment(String departmentID);
}

package viewmodel.retailer.inventory;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.IDataModel;
import model.ProductRequest;
import model.StockItem;
import model.StockItemList;
import view.retailer.ViewHandler;

import java.beans.PropertyChangeEvent;

/**
 * The viewmodel Class for the main Inventory view.
 *
 * @author Kenneth Jensen
 * @author Floring Bordei
 * @author Jaime Lopez
 * @author Dave Joe Lê
 */

public class InventoryMainVM {
    private IDataModel dataModel;
    private ObservableList<StockItem> stockItems;
    private ViewHandler viewHandler;
    private StringProperty requestQty;
    private StringProperty sellQty;

    /**
     * Creates an InventoryMainVM with the specified information and adds the required
     * Listeners.
     * @param dataModel The {@link model.DataModel} to be used.
     * @param viewHandler The {@link ViewHandler} to be used.
     */
    public InventoryMainVM(IDataModel dataModel, ViewHandler viewHandler) {
        this.dataModel = dataModel;
        this.viewHandler = viewHandler;
        stockItems = FXCollections.observableArrayList(); // = new ObservableListWrapper<>(new ArrayList<>());
        dataModel.addListener("NewItemFromServer", this::addStockItemToClient);
        dataModel.addListener("NewItemFromUser",this::addStockItemToClient);
        dataModel.addListener("NewStockItemList",this::loadList);
        requestQty = new SimpleStringProperty();
        sellQty = new SimpleStringProperty();

    }
    private void loadList(PropertyChangeEvent evt) {
        StockItemList stockItemList = (StockItemList) evt.getNewValue();
        stockItems.removeAll(stockItems);
        for(int i = 0; i < stockItemList.size(); i++)
        {
            if(stockItemList.get(i).getLocation().equals("RT"))
            {
                stockItems.add(stockItemList.get(i));
            }
        }
    }

    /**
     * Adds a stock item to the List using the information stored in the {@link PropertyChangeEvent} passed.
     * @param evt The {@link PropertyChangeEvent} that caused this method to be called.
     */
    private void addStockItemToClient(PropertyChangeEvent evt) {
        StockItem stockItem = (StockItem) evt.getNewValue();
        if(stockItem.getLocation().equals("RT"))
        {
            stockItems.add(stockItem);
        }
    }

    /**
     * Gets the {@link StockItem}s in the List.
     * @return The {@link StockItem}s in the List.
     */
    public ObservableList<StockItem> getStockItems() {
        return stockItems;
    }

    /**
     * This method opens the main view.
     */
    public void openMainView() {
        viewHandler.openMainView();
    }

    /**
     * Gets the quantity for Request {@link StringProperty} stored.
     * @return The quantity for Request {@link StringProperty} stored.
     */
    public StringProperty requestQtyProperty() {
        return requestQty;
    }

    /**
     * Gets the quantity to sell {@link StringProperty} stored.
     * @return The quantity to sell {@link StringProperty} stored.
     */
    public StringProperty sellQtyProperty() {
        return sellQty;
    }

    /**
     * This method opens the add Inventory view.
     */
    public void openInventoryAddView() {
        viewHandler.openInventoryAddView();
    }

    /**
     * This method opens the main Employee view.
     */
    public void openEmployeeMainView() {
        viewHandler.openEmployeeMainView();
    }

    /**
     * This method opens the Product Request view.
     */
    public void openProductRequestView() { viewHandler.openProductRequestView();
    }

    /**
     * Removes a {@link StockItem} from the List.
     * @param stockItem The {@link StockItem} to be removed.
     */
    public void removeStockItem(StockItem stockItem) {
        dataModel.removeStockItemWH(stockItem);
    }

    public void addToSales(StockItem selectedItem) {
        StockItem x = selectedItem.copy();
        x.setQuantity(Integer.parseInt(sellQty.getValue()));
        x.setPrice(selectedItem.getPrice() * 2);
        dataModel.addToSales(x,true);
        sellQty.setValue("");
    }

    public void openSalesView() {
        viewHandler.openSalesView();
    }

    public void openInventoryMainView() {viewHandler.openInventoryMainView();
    }

    public void openDeliveryView() {viewHandler.openDeliveryMainView();
    }

    public void addProductRequestToList(StockItem selectedItem) {
        ProductRequest productRequest = new ProductRequest(selectedItem, Integer.parseInt(requestQty.getValue()));
        dataModel.addToProductRequest(productRequest, true);
        requestQty.setValue("");
    }

    public boolean onlyNumbersQuantity() {
        return dataModel.onlyNumbers(requestQty.getValue());
    }

    public void openMessengerView() {viewHandler.openMessengerView();
    }
}

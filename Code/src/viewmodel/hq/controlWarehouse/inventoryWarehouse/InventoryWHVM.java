package viewmodel.hq.controlWarehouse.inventoryWarehouse;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.IDataModel;
import model.StockItem;
import model.StockItemList;
import view.hq.ViewHandler;

import java.beans.PropertyChangeEvent;

/**
 * The viewmodel Class for the Warehouse Inventory view.
 *
 * @author Kenneth Jensen
 * @author Floring Bordei
 * @author Jaime Lopez
 * @author Dave Joe Lê
 */

public class InventoryWHVM { //This class is to display inventoryStockList from warehouse
    private ObservableList<StockItem> stockItems;
    private ViewHandler viewHandler;
    private IDataModel dataModel;

    /**
     * Creates an InventoryWHVM with the specified information
     * @param dataModel The {@link model.DataModel} to be used.
     * @param viewHandler The {@link view.hq.ViewHandler} to be used.
     */
    public InventoryWHVM(IDataModel dataModel, ViewHandler viewHandler) {
        this.viewHandler = viewHandler;
        this.dataModel = dataModel;
        stockItems = FXCollections.observableArrayList(); // = new ObservableListWrapper<>(new ArrayList<>());
        dataModel.addListener("NewItemFromServer", this::addStockItemToClient); //TODO: Delete one of these to just initialize the table view
        dataModel.addListener("NewItemFromUser",this::addStockItemToClient);
        dataModel.addListener("NewStockItemList", this::loadList);
    }

    private void loadList(PropertyChangeEvent evt) {
        StockItemList stockItemList = (StockItemList) evt.getNewValue();
        stockItems.removeAll(stockItems);
        for(int i = 0; i < stockItemList.size(); i++)
        {
            if(stockItemList.get(i).getLocation().equals("WH"))
            {
                stockItems.add(stockItemList.get(i));
            }
        }
    }

    private void addStockItemToClient(PropertyChangeEvent evt) {
        StockItem stockItem = (StockItem) evt.getNewValue();
        if(stockItem.getLocation().equals("WH"))
        {
            stockItems.add(stockItem);
        }
    }
    /**
     * Gets the {@link StockItem}s stored.
     * @return The {@link StockItem}s stored.
     */
    public ObservableList<StockItem> getStockItems() {
        return stockItems;
    }

    /**
     * This method opens the main view.
     */
    public void openMainView() {viewHandler.openMainWHView();

    }

    /**
     * This method opens the Warehouse Employee view.
     */
    public void openEmployeeWHView() {
        viewHandler.openEmployeeMainWHView();
    }

    /**
     * This method opens the main Headquarters view.
     */
    public void openMainHQView() {viewHandler.openMainHQView();
    }

    /**
     * This method opens the Warehouse main Inventory view,
     */
    public void openInventoryMainWHView() {
        viewHandler.openInventoryMainWHView();
    }

    public void openDeliveryView() {viewHandler.openDeliveryMainWHView();
    }

    public void openMainRTView() {viewHandler.openMainRTView();
    }

    public void openMainWHView() {viewHandler.openMainWHView();
    }
}

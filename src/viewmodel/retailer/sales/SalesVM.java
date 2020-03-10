package viewmodel.retailer.sales;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.IDataModel;
import model.ProductRequest;
import model.StockItem;
import model.StockItemList;
import view.retailer.ViewHandler;

import java.beans.PropertyChangeEvent;

/**
 * The viewmodel class for the Product Request view.
 *
 * @author Kenneth Jensen
 * @author Floring Bordei
 * @author Jaime Lopez
 * @author Dave Joe Lê
 */

public class SalesVM {
    private IDataModel dataModel;
    private ObservableList<StockItem> sales;
    private ViewHandler viewHandler;

    /**
     * Creates a SalesVM with the specified information and adds the required listeners.
     *
     * @param dataModel   The {@link model.DataModel} to be used.
     * @param viewHandler The {@link ViewHandler} to be used.
     */
    public SalesVM(IDataModel dataModel, ViewHandler viewHandler) {
        this.dataModel = dataModel;
        this.viewHandler = viewHandler;
        sales = FXCollections.observableArrayList(); // = new ObservableListWrapper<>(new ArrayList<>());
        dataModel.addListener("AddSaleView",this::addSalesToClient);
        dataModel.addListener("NewSalesList", this::loadList);
    }

    private void loadList(PropertyChangeEvent evt) {
        StockItemList stockItemList = (StockItemList) evt.getNewValue();
        sales.removeAll(sales);
        for(int i = 0; i < stockItemList.size(); i++)
        {
            sales.add(stockItemList.get(i));
        }
    }

    private void addSalesToClient(PropertyChangeEvent propertyChangeEvent) {
        sales.add((StockItem) propertyChangeEvent.getNewValue());
    }

    /**
     * Sends a Product Request to the Server with the information in the {@link PropertyChangeEvent} passed.
     * @param evt The {@link PropertyChangeEvent} that caused this method to be called.
     */

    /**
     * Gets the {@link ProductRequest}s from the List.
     * @return The {@link ProductRequest}s from the List.
     */
    public ObservableList<StockItem> getSales() {
        return sales;
    }

    /**
     * This method opens the main view.
     */
    public void openMainView() {
        viewHandler.openMainView();
    }

    /**
     * This method opens the main Employee view.
     */
    public void openEmployeeMainView() {
        viewHandler.openEmployeeMainView();
    }

    /**
     * This method opens the main Inventory view.
     */
    public void openInventoryMainView() {
        viewHandler.openInventoryMainView();
    }

    public void openProductRequest() {
        viewHandler.openProductRequestView();
    }

    public void openDeliveryView() {viewHandler.openDeliveryMainView();
    }

    public void openSalesView() {viewHandler.openSalesView();
    }

    public void openMessengerView() {viewHandler.openMessengerView();
    }
}

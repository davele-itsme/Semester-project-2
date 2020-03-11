package viewmodel.hq.controlWarehouse.deliveryWarehouse;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Delivery;
import model.DeliveryList;
import model.IDataModel;
import view.hq.ViewHandler;

import java.beans.PropertyChangeEvent;

/**
 * This is the viewmodel Class for the main Employye view.
 *
 * @author Kenneth Jensen
 * @author Floring Bordei
 * @author Jaime Lopez
 * @author Dave Joe Lê
 */

public class DeliveryMainWHVM {
    private IDataModel dataModel;
    private ObservableList<Delivery> deliveries;
    private ViewHandler viewHandler;

    /**
     * Creates an EmployeeMainVM with the specified information and adds the needed Listeners.
     *
     * @param dataModel   The {@link model.DataModel} to be used.
     * @param viewHandler The {@link ViewHandler} to be used.
     */
    public DeliveryMainWHVM(IDataModel dataModel, ViewHandler viewHandler) {
        this.dataModel = dataModel;
        this.viewHandler = viewHandler;
        deliveries = FXCollections.observableArrayList(); // = new ObservableListWrapper<>(new ArrayList<>());
        dataModel.addListener("", this::addDeliveryToClient);
        dataModel.addListener("NewDeliveryList",this::loadList);

    }

    private void loadList(PropertyChangeEvent evt) {
        DeliveryList deliveryList = (DeliveryList) evt.getNewValue();
        deliveries.removeAll(deliveries);
        for(int i = 0; i < deliveryList.size(); i++)
        {
            if(deliveryList.get(i).getRequestedFrom().equals("RT") || deliveryList.get(i).getRequestedFrom().equals("WH"))
            deliveries.add(deliveryList.get(i));
        }
    }

    private void addDeliveryToClient(PropertyChangeEvent evt) {
    }

    /**
     * The method called by the Listener added at {@link this#DeliveryMainWHVM(IDataModel, ViewHandler)} (IDataModel, ViewHandler)}.
     * This method adds the Employee stored in the {@link PropertyChangeEvent} data.
     *
     * @param {@link PropertyChangeEvent} that caused the Listener to call this method.
     *            <p>
     *            /**
     *            Gets the {@link Delivery}s stored.
     * @return The {@link Delivery}s stored.
     */
    public ObservableList<Delivery> getDeliveries() {
        return deliveries;
    }

    /**
     * This method opens the main view.
     */
    public void openMainView() {
        viewHandler.openMainWHView();
    }

    /**
     * This method opens the main Inventory view.
     */
    public void openInventoryView() {
        viewHandler.openInventoryMainWHView();
    }

    /**
     * This method removes an {@link Delivery} from the List.
     *
     * @param {@link DeliveryList} to be removed.
     */

    public void openDeliveryClicked() {
        viewHandler.openDeliveryMainWHView();
    }


    public void openEmployeeMainView() {
        viewHandler.openEmployeeMainWHView();
    }

    public void openDeliveryItemsView(Delivery delivery) {
        viewHandler.openDeliveryItemsWHView();
        dataModel.openDelivery(delivery);
    }

    public void openMainHQView() {viewHandler.openMainHQView();
    }

    public void openMainRTView() {viewHandler.openMainRTView();
    }

    public void openMainWHView() {viewHandler.openMainWHView();
    }
}

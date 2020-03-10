package viewmodel.warehouse.delivery;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Delivery;
import model.IDataModel;
import model.ProductRequest;
import view.warehouse.ViewHandler;

import java.beans.PropertyChangeEvent;

/**
 * This is the viewmodel Class for the main Employye view.
 *
 * @author Kenneth Jensen
 * @author Floring Bordei
 * @author Jaime Lopez
 * @author Dave Joe Lê
 */

public class DeliveryItemsVM {
    private IDataModel dataModel;
    private ObservableList<ProductRequest> productRequests;
    private ViewHandler viewHandler;

    /**
     * Creates an EmployeeMainVM with the specified information and adds the needed Listeners.
     * @param dataModel The {@link model.DataModel} to be used.
     * @param viewHandler The {@link ViewHandler} to be used.
     */
    public DeliveryItemsVM(IDataModel dataModel, ViewHandler viewHandler)
    {
        this.dataModel = dataModel;
        this.viewHandler = viewHandler;
        productRequests = FXCollections.observableArrayList(); // = new ObservableListWrapper<>(new ArrayList<>());
        dataModel.addListener("OpenDelivery", this::openDelivery);

    }

    private void openDelivery(PropertyChangeEvent evt) {
        Delivery delivery = (Delivery)evt.getNewValue();
        productRequests.removeAll(productRequests);
        for(int i = 0; i < delivery.getProductList().size(); i++)
        {
            productRequests.add(delivery.getProductList().getProductRequest(i));
        }
    }

    /**
     * The method called by the Listener added at {@link this#DeliveryItemsVM(IDataModel, ViewHandler)} (IDataModel, ViewHandler)}.
     * This method adds the Employee stored in the {@link PropertyChangeEvent} data.
     * @param {@link PropertyChangeEvent} that caused the Listener to call this method.

    /**
     * Gets the {@link ProductRequest}s stored.
     * @return The {@link ProductRequest}s stored.
     */
    public ObservableList<ProductRequest> getProductRequests()
    {
        return productRequests;
    }

    /**
     * This method opens the main view.
     */
    public void openMainView()
    {
        viewHandler.openMainView();
    }

    /**
     * This method opens the main Inventory view.
     */
    public void openInventoryView()
    {
        viewHandler.openInventoryMainView();
    }

    /**
     * This method removes an {@link ProductRequest} from the List.
     * @param {@link Delivery} to be removed.
     */

    public void openProductRequestView() {viewHandler.openProductRequestView();
    }

    public void openDeliveryClicked() {
        viewHandler.openDeliveryMainView();
    }

    public void openEmployeeMainView() {viewHandler.openEmployeeMainView();
    }

    public void goBack() {viewHandler.openDeliveryMainView();
    }

    public void openMessengerView() {
        viewHandler.openMessengerView();
    }
}

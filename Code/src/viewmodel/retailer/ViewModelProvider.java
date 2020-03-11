package viewmodel.retailer;

import javafx.stage.Stage;
import model.IDataModel;
import view.retailer.ViewHandler;
import viewmodel.retailer.delivery.DeliveryItemsVM;
import viewmodel.retailer.delivery.DeliveryMainVM;
import viewmodel.retailer.employee.EmployeeAddVM;
import viewmodel.retailer.employee.EmployeeMainVM;
import viewmodel.retailer.inventory.InventoryAddVM;
import viewmodel.retailer.inventory.InventoryMainVM;
import viewmodel.retailer.messenger.MessengerVM;
import viewmodel.retailer.request.ProductRequestVM;
import viewmodel.retailer.main.MainVM;
import viewmodel.retailer.sales.SalesVM;


/**
 * The viewmodel Provider for the Warehouse
 *
 * @author Kenneth Jensen
 * @author Floring Bordei
 * @author Jaime Lopez
 * @author Dave Joe Lê
 */

public class ViewModelProvider {
    private IDataModel dataModel;
    private MainVM mainVM;
    private InventoryMainVM inventoryMainVM;
    private InventoryAddVM inventoryAddVM;
    private EmployeeMainVM employeeMainVM;
    private EmployeeAddVM employeeAddVM;
    private ProductRequestVM productRequestVM;
    private SalesVM salesVM;
    private DeliveryMainVM deliveryMainVM;
    private DeliveryItemsVM deliveryItemsVM;
    private MessengerVM messengerVM;

    /**
     * Creates a ViewModelProvider with the specified information and instantiates all the required fields.
     *
     * @param dataModel The {@link model.DataModel} to be used.
     */
    public ViewModelProvider(IDataModel dataModel) {
        this.dataModel = dataModel;
    }

    /**
     * Gets the main viewmodel.
     *
     * @return The main viewmodel.
     */
    public MainVM getMainVM() {
        return mainVM;
    }

    /**
     * Gets the main Inventory viewmodel.
     *
     * @return The main Inventory viewmodel.
     */
    public InventoryMainVM getInventoryMainVM() {
        return inventoryMainVM;
    }

    /**
     * Gets the main Employee viewmodel
     *
     * @return The main Employee viewmodel
     */
    public EmployeeMainVM getEmployeeMainVM() {
        return employeeMainVM;
    }

    /**
     * Gets the add Employee viewmodel.
     *
     * @return The add Employee viewmodel.
     */
    public EmployeeAddVM getEmployeeAddVM() {
        return employeeAddVM;
    }

    /**
     * Gets the add Inventory viewmodel.
     *
     * @return The add Inventory viewmodel.
     */
    public InventoryAddVM getInventoryAddVM() {
        return inventoryAddVM;
    }

    /**
     * Gets the Product Request viewmodel.
     *
     * @return
     */
    public ProductRequestVM getProductRequestVM() {
        return productRequestVM;
    }

    /**
     * Instantiates all the viewmodels, to make sure they aren't null.
     *
     * @param viewHandler The {@link ViewHandler} to be used.
     */
    public void instantiateViewModels(ViewHandler viewHandler) {
        employeeMainVM = new EmployeeMainVM(dataModel, viewHandler);
        employeeAddVM = new EmployeeAddVM(dataModel, viewHandler);
        mainVM = new MainVM(dataModel, viewHandler);
        inventoryMainVM = new InventoryMainVM(dataModel, viewHandler);
        inventoryAddVM = new InventoryAddVM(dataModel, viewHandler);
        productRequestVM = new ProductRequestVM(dataModel, viewHandler);
        salesVM = new SalesVM(dataModel, viewHandler);
        deliveryMainVM = new DeliveryMainVM(dataModel, viewHandler);
        deliveryItemsVM = new DeliveryItemsVM(dataModel, viewHandler);
        messengerVM = new MessengerVM(dataModel, viewHandler);
    }


    public SalesVM getSalesVM() {
        return salesVM;
    }

    public DeliveryMainVM getDeliveryMainVM() {return deliveryMainVM;
    }

    public DeliveryItemsVM getDeliveryItemsVM()
    {
        return deliveryItemsVM;
    }

    public MessengerVM getMessengerVM() {
        return messengerVM;
    }
}

package viewmodel.hq;

import model.IDataModel;
import view.hq.ViewHandler;
import viewmodel.hq.controlRetailer.deliveryRetailer.DeliveryItemsRTVM;
import viewmodel.hq.controlRetailer.deliveryRetailer.DeliveryMainRTVM;
import viewmodel.hq.controlRetailer.employeeRetailer.EmployeeRTVM;
import viewmodel.hq.controlRetailer.inventoryRetailer.InventoryRTVM;
import viewmodel.hq.controlRetailer.mainRetailer.MainRTVM;
import viewmodel.hq.controlWarehouse.deliveryWarehouse.DeliveryItemsWHVM;
import viewmodel.hq.controlWarehouse.deliveryWarehouse.DeliveryMainWHVM;
import viewmodel.hq.controlWarehouse.employeeWarehouse.EmployeeWHVM;
import viewmodel.hq.controlWarehouse.inventoryWarehouse.InventoryWHVM;
import viewmodel.hq.controlWarehouse.mainWarehouse.MainWHVM;
import viewmodel.hq.hq.employee.EmployeeAddHQVM;
import viewmodel.hq.hq.employee.EmployeeMainHQVM;
import viewmodel.hq.hq.inventory.InventoryAddHQVM;
import viewmodel.hq.hq.inventory.InventoryMainHQVM;
import viewmodel.hq.hq.main.MainVM;
import viewmodel.hq.hq.messenger.MessengerVM;

/**
 * The viewmodel provider for the Headquarters and it is responsible for passing the requested
 * viewmodel to any Class requesting a viewmodel.
 *
 * @author Kenneth Jensen
 * @author Floring Bordei
 * @author Jaime Lopez
 * @author Dave Joe Lê
 */

public class ViewModelProvider {

    private IDataModel dataModel;
    private MainVM mainVM;
    private EmployeeMainHQVM employeeMainHQVM;
    private EmployeeAddHQVM employeeAddHQVM;
    private InventoryWHVM inventoryWHVM;
    private EmployeeWHVM employeeWHVM;
    private InventoryMainHQVM inventoryMainHQVM;
    private InventoryAddHQVM inventoryAddHQVM;
    private EmployeeRTVM employeeRTVM;
    private InventoryRTVM inventoryRTVM;
    private MainWHVM mainWHVM;
    private MainRTVM mainRTVM;
    private DeliveryItemsWHVM deliveryItemsWHVM;
    private DeliveryMainWHVM deliveryMainWHVM;
    private DeliveryMainRTVM deliveryMainRTVM;
    private DeliveryItemsRTVM deliveryItemsRTVM;
    private MessengerVM messengerVM;

    /**
     * Creates a ViewModelProvider with the specified information
     *
     * @param dataModel The {@link model.DataModel} to be used.
     */
    public ViewModelProvider(IDataModel dataModel) {
        this.dataModel = dataModel;
    }

    /**
     * This method gets the main viewmodel.
     *
     * @return The main viewmodel.
     */
    public MainVM getMainVM() {
        return mainVM;
    }

    /**
     * This method gets the Headquarters main Employee viewmodel.
     *
     * @return The Headquarters main Employee viewmodel.
     */
    public EmployeeMainHQVM getEmployeeMainHQVM() {
        return employeeMainHQVM;
    }

    /**
     * This method gets the Headquarters add Inventory viewmodel.
     *
     * @return The Headquarters add Inventory viewmodel.
     */
    public InventoryAddHQVM getInventoryAddHQVM() {
        return inventoryAddHQVM;
    }

    /**
     * This method gets the Headquarters main Inventory viewmodel.
     *
     * @return The Headquarters main Inventory viewmodel.
     */
    public InventoryMainHQVM getInventoryMainHQVM() {
        return inventoryMainHQVM;
    }

    /**
     * This method gets the Headquarters add Employee viewmodel.
     *
     * @return The Headquarters add Employee viewmodel.
     */
    public EmployeeAddHQVM getEmployeeAddHQVM() {
        return employeeAddHQVM;
    }

    /**
     * This method gets the Warehouse Inventory viewmodel.
     *
     * @return The Warehouse Inventory viewmodel.
     */
    public InventoryWHVM getInventoryWHVM() {
        return inventoryWHVM;
    }

    /**
     * This method gets the Warehouse Employee viewmodel.
     *
     * @return The Warehouse Employee viewmodel.
     */
    public EmployeeWHVM getEmployeeWHVM() {
        return employeeWHVM;
    }

    /**
     * This method gets the Retailer Employee viewmodel.
     *
     * @return The Retailer Employee viewmodel.
     */
    public EmployeeRTVM getEmployeeRTVM() {
        return employeeRTVM;
    }

    /**
     * This method gets the Retailer Inventory viewmodel.
     *
     * @return The Retailer Inventory viewmodel.
     */
    public InventoryRTVM getInventoryRTVM() {
        return inventoryRTVM;
    }

    /**
     * This method gets the Warehouse Dashboard viewmodel.
     *
     * @return The Warehouse Dashboard viewmodel.
     */
    public MainWHVM getMainWHVM() {
        return mainWHVM;
    }

    /**
     * This method gets the Retailer Dashboard viewmodel.
     *
     * @return The Retailer Dashboard viewmodel.
     */
    public MainRTVM getMainRTVM() {
        return mainRTVM;
    }

    /**
     * This method instantiates all the viewmode.
     *
     * @param viewHandler The {@link ViewHandler} to be used.
     */
    public void instantiateViewModels(ViewHandler viewHandler) {
        employeeAddHQVM = new EmployeeAddHQVM(dataModel, viewHandler);
        employeeMainHQVM = new EmployeeMainHQVM(dataModel, viewHandler);
        mainVM = new MainVM(dataModel, viewHandler);
        inventoryWHVM = new InventoryWHVM(dataModel, viewHandler);
        employeeWHVM = new EmployeeWHVM(dataModel, viewHandler);
        inventoryAddHQVM = new InventoryAddHQVM(dataModel, viewHandler);
        inventoryMainHQVM = new InventoryMainHQVM(dataModel, viewHandler);
        employeeRTVM = new EmployeeRTVM(dataModel, viewHandler);
        inventoryRTVM = new InventoryRTVM(dataModel, viewHandler);
        mainRTVM = new MainRTVM(dataModel, viewHandler);
        mainWHVM = new MainWHVM(dataModel, viewHandler);
        deliveryItemsWHVM = new DeliveryItemsWHVM(dataModel, viewHandler);
        deliveryMainWHVM = new DeliveryMainWHVM(dataModel, viewHandler);
        deliveryItemsRTVM = new DeliveryItemsRTVM(dataModel, viewHandler);
        deliveryMainRTVM = new DeliveryMainRTVM(dataModel, viewHandler);
        messengerVM = new MessengerVM(dataModel, viewHandler);
    }

    public DeliveryItemsWHVM getDeliveryItemsWHVM() {
        return deliveryItemsWHVM;
    }

    public DeliveryMainWHVM getDeliveryMainWHVM() {
        return deliveryMainWHVM;
    }

    public DeliveryMainRTVM getDeliveryMainRTVM() {
        return deliveryMainRTVM;
    }

    public DeliveryItemsRTVM getDeliveryItemsRTVM() {
        return deliveryItemsRTVM;
    }

    public MessengerVM getMessengerVM() {
        return messengerVM;
    }
}

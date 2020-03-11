package viewmodel.warehouse.main;

import model.IDataModel;
import view.warehouse.ViewHandler;

/**
 * The main viewmodel Class for the Warehouse.
 *
 * @author Kenneth Jensen
 * @author Floring Bordei
 * @author Jaime Lopez
 * @author Dave Joe Lê
 */

public class MainVM {

    private IDataModel dataModel;
    private ViewHandler viewHandler;

    /**
     * Creates a MainVM with the specified information
     * @param dataModel The {@link model.DataModel} to be used.
     * @param viewHandler The {@link ViewHandler} to be used.
     */
    public MainVM(IDataModel dataModel, ViewHandler viewHandler) {
        this.dataModel = dataModel;
        this.viewHandler = viewHandler;
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
    public void openInventoryMainView()
    {
        viewHandler.openInventoryMainView();
    }

    /**
     * This method opens the Product Request view.
     */
    public void openProductRequestView()
    {
        viewHandler.openProductRequestView();
    }

    public void openMainView() {viewHandler.openMainView();
    }

    public void openDeliveryMainView() {viewHandler.openDeliveryMainView();
    }

    public void openMessengerView() {viewHandler.openMessengerView();
    }
}

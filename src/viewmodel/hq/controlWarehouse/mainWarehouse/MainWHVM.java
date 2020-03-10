package viewmodel.hq.controlWarehouse.mainWarehouse;

import model.IDataModel;
import view.hq.ViewHandler;

public class MainWHVM {
    private IDataModel dataModel;
    private ViewHandler viewHandler;

    public MainWHVM(IDataModel dataModel, ViewHandler viewHandler) {
        this.viewHandler = viewHandler;
        this.dataModel = dataModel;
    }

    public void openEmployeeMainWHView() {
        viewHandler.openEmployeeMainWHView();
    }

    public void openMainView() {viewHandler.openMainHQView();
    }

    public void openDeliveryView() {viewHandler.openDeliveryMainWHView();
    }

    public void openMainWHView() {
        viewHandler.openMainWHView();
    }

    public void openMainRTView() {viewHandler.openMainRTView();
    }

    public void openInventoryMainWHView() {viewHandler.openInventoryMainWHView();
    }
}

package viewmodel.hq.controlRetailer.mainRetailer;

import model.IDataModel;
import view.hq.ViewHandler;

public class MainRTVM {
    private IDataModel dataModel;
    private ViewHandler viewHandler;

    public MainRTVM(IDataModel dataModel, ViewHandler viewHandler) {
        this.viewHandler = viewHandler;
        this.dataModel = dataModel;
    }

    public void openMainView() {viewHandler.openMainHQView();
    }

    public void openDeliveryView() {viewHandler.openDeliveryMainRTView();
    }

    public void openMainWHView() {
        viewHandler.openMainWHView();
    }

    public void openMainRTView() {viewHandler.openMainRTView();
    }


    public void openEmployeeMainRTView() {viewHandler.openEmployeeMainRTView();
    }

    public void openInventoryMainRTView() {viewHandler.openInventoryMainRTView();
    }
}

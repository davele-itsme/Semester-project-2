package view.hq.controlRetailer.deliveryRetailer;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.ProductRequest;
import viewmodel.hq.controlRetailer.deliveryRetailer.DeliveryItemsRTVM;


/**
 * The view Class for the main Employee view.
 *
 * @author Kenneth Jensen
 * @author Floring Bordei
 * @author Jaime Lopez
 * @author Dave Joe Lê
 */

public class DeliveryItemsRTView {

    @FXML
    private TableView<ProductRequest> deliveryTableView;

    @FXML
    private TableColumn<ProductRequest, String> nameCol;

    @FXML private TableColumn<ProductRequest, String> iDCol;

    @FXML private TableColumn<Integer, ProductRequest> quantityCol;

    @FXML
    private AnchorPane anchorPane;

    private DeliveryItemsRTVM deliveryItemsRTVM;

    /**
     * Creates an DeliveryMainRTView.
     */
    public DeliveryItemsRTView() {

    }

    /**
     * An init method instantiating all the required fields.
     *
     * @param deliveryItemsRTVM the {@link DeliveryItemsRTVM} viewmodel to be used.
     */
    public void init(DeliveryItemsRTVM deliveryItemsRTVM) {
        this.deliveryItemsRTVM = deliveryItemsRTVM;
        deliveryTableView.setItems(deliveryItemsRTVM.getProductRequests());
        nameCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ProductRequest, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ProductRequest, String> param) {
                return new SimpleStringProperty(param.getValue().getName());
            }
        });
        iDCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ProductRequest, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ProductRequest, String> param) {
                return new SimpleStringProperty(param.getValue().getID());
            }
        });
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    }

    @FXML
    void onCloseClicked(MouseEvent event) {
        Platform.exit();
    }

    @FXML
    void onMinimizeClicked(MouseEvent event) {
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    void onDashboardClicked(ActionEvent event) {
        deliveryItemsRTVM.openMainView();
    }

    @FXML
    void onInventoryClicked(ActionEvent event) {
        deliveryItemsRTVM.openInventoryView();
    }

    @FXML
    void onEmployeeClicked(ActionEvent event) {
        deliveryItemsRTVM.openEmployeeMainView();
    }

    @FXML
    void onDeliveryClicked(ActionEvent event) {
        deliveryItemsRTVM.openDeliveryClicked();
    }

    @FXML void onBackClicked(ActionEvent event)
    {
        deliveryItemsRTVM.goBack();
    }

    @FXML
    void onHQClicked(MouseEvent event) {
        deliveryItemsRTVM.openMainHQView();
    }

    @FXML
    void onRetailerClicked(MouseEvent event) {
        deliveryItemsRTVM.openMainRTView();
    }

    @FXML void onWarehouseClicked(MouseEvent event)
    {
        deliveryItemsRTVM.openMainWHView();
    }
}

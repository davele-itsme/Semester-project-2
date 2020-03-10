package view.hq.controlWarehouse.deliveryWarehouse;

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
import viewmodel.hq.controlWarehouse.deliveryWarehouse.DeliveryItemsWHVM;


/**
 * The view Class for the main Employee view.
 *
 * @author Kenneth Jensen
 * @author Floring Bordei
 * @author Jaime Lopez
 * @author Dave Joe Lê
 */

public class DeliveryItemsWHView {

    @FXML
    private TableView<ProductRequest> deliveryTableView;

    @FXML
    private TableColumn<ProductRequest, String> nameCol;

    @FXML private TableColumn<ProductRequest, String> iDCol;

    @FXML private TableColumn<Integer, ProductRequest> quantityCol;

    @FXML
    private AnchorPane anchorPane;

    private DeliveryItemsWHVM deliveryItemsWHVM;

    /**
     * Creates an DeliveryMainRTView.
     */
    public DeliveryItemsWHView() {

    }

    /**
     * An init method instantiating all the required fields.
     *
     * @param deliveryItemsWHVM the {@link DeliveryItemsWHVM} viewmodel to be used.
     */
    public void init(DeliveryItemsWHVM deliveryItemsWHVM) {
        this.deliveryItemsWHVM = deliveryItemsWHVM;
        deliveryTableView.setItems(deliveryItemsWHVM.getProductRequests());
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
        deliveryItemsWHVM.openMainView();
    }

    @FXML
    void onInventoryClicked(ActionEvent event) {
        deliveryItemsWHVM.openInventoryView();
    }

    @FXML
    void onEmployeeClicked(ActionEvent event) {
        deliveryItemsWHVM.openEmployeeMainView();
    }

    @FXML
    void onDeliveryClicked(ActionEvent event) {
        deliveryItemsWHVM.openDeliveryClicked();
    }

    @FXML void onBackClicked(ActionEvent event)
    {
        deliveryItemsWHVM.goBack();
    }

    @FXML
    void onHQClicked(MouseEvent event) {
        deliveryItemsWHVM.openMainHQView();
    }

    @FXML
    void onRetailerClicked(MouseEvent event) {
        deliveryItemsWHVM.openMainRTView();
    }

    @FXML void onWarehouseClicked(MouseEvent event)
    {
        deliveryItemsWHVM.openMainWHView();
    }
}

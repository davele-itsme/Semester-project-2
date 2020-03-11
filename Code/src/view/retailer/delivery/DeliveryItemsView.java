package view.retailer.delivery;

import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
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
import model.Delivery;
import model.ProductRequest;
import viewmodel.retailer.delivery.DeliveryItemsVM;

/**
 * The view Class for the main Employee view.
 *
 * @author Kenneth Jensen
 * @author Floring Bordei
 * @author Jaime Lopez
 * @author Dave Joe Lê
 */

public class DeliveryItemsView {

    @FXML
    private TableView<ProductRequest> deliveryTableView;

    @FXML
    private TableColumn<ProductRequest, String> nameCol;

    @FXML private TableColumn<ProductRequest, String> iDCol;

    @FXML private TableColumn<Integer, ProductRequest> quantityCol;

    @FXML
    private AnchorPane anchorPane;

    private DeliveryItemsVM deliveryItemsVM;

    /**
     * Creates an DeliveryMainRTView.
     */
    public DeliveryItemsView() {

    }

    /**
     * An init method instantiating all the required fields.
     *
     * @param deliveryItemsVM the {@link DeliveryItemsVM} viewmodel to be used.
     */
    public void init(DeliveryItemsVM deliveryItemsVM) {
        this.deliveryItemsVM = deliveryItemsVM;
        deliveryTableView.setItems(deliveryItemsVM.getProductRequests());
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
        deliveryItemsVM.openMainView();
    }

    @FXML
    void onInventoryClicked(ActionEvent event) {
        deliveryItemsVM.openInventoryView();
    }

    @FXML
    void onEmployeeClicked(ActionEvent event) {
        deliveryItemsVM.openEmployeeMainView();
    }


    @FXML
    void onProductRequestClicked(ActionEvent event) {
        deliveryItemsVM.openProductRequestView();
    }

    @FXML
    void onDeliveryClicked(ActionEvent event) {
        deliveryItemsVM.openDeliveryClicked();
    }

    @FXML
    void onSalesClicked(ActionEvent event) {
        deliveryItemsVM.openSalesView();
    }

    @FXML void onBackClicked(ActionEvent event)
    {
        deliveryItemsVM.goBack();
    }

    @FXML void onMessengerClicked(MouseEvent event)
    {
        deliveryItemsVM.openMessengerView();
    }
}

package view.warehouse.request;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.ProductRequest;
import viewmodel.warehouse.request.ProductRequestVM;

/**
 * The view Class for the Product Request view.
 *
 * @author Kenneth Jensen
 * @author Floring Bordei
 * @author Jaime Lopez
 * @author Dave Joe Lê
 */

public class ProductRequestView {

    @FXML
    private TableView<ProductRequest> productRequestTable;

    @FXML
    private TableColumn<ProductRequest, String> nameCol;

    @FXML
    private TableColumn<ProductRequest, String> iDCol;

    @FXML
    private TableColumn<Integer, ProductRequest> quantityCol;

    @FXML
    private Label errorQuantityLabel;

    @FXML
    private Label emptyQuantity;

    @FXML
    private AnchorPane anchorPane;

    private ProductRequestVM productRequestVM;
    private ProductRequest selectedItem;

    /**
     * Creates a SalesView.
     */
    public ProductRequestView()
    {

    }

    /**
     * An init method instantiating all the required fields.
     * @param productRequestVM The {@link ProductRequestVM} viewmodel to be used.
     */
    public void init(ProductRequestVM productRequestVM) {
        this.productRequestVM = productRequestVM;
        productRequestTable.setItems(productRequestVM.getProductRequests());
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
    void onDashboardClicked(ActionEvent event) {
        productRequestVM.openMainView();
    }

    @FXML
    void onEmployeeClicked(ActionEvent event) {
        productRequestVM.openEmployeeMainView();
    }

    @FXML
    void onInventoryClicked(ActionEvent event) {
        productRequestVM.openInventoryMainView();
    }

    @FXML
    void onSendRequestClicked(ActionEvent event) {
        productRequestVM.sendProductRequest();

    }

    @FXML
    void onCloseClicked(MouseEvent event) {
        Platform.exit();
    }

    @FXML
    void onMinimizeClicked(MouseEvent event) {
        Stage stage = (Stage)anchorPane.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML void onProductRequestClicked(ActionEvent event)
    {
        productRequestVM.openProductRequestView();
    }

    @FXML void onDeliveryClicked(ActionEvent event)
    {
        productRequestVM.openDeliveryMainView();
    }

    @FXML
    void onRemoveProductRequestClicked(ActionEvent event)
    {
        selectedItem = productRequestTable.getSelectionModel().getSelectedItem();
        if(selectedItem == null)
        {
            Alert warningAlert = new Alert(Alert.AlertType.WARNING);
            warningAlert.setTitle("Warning");
            warningAlert.setHeaderText("No item has been selected");
            warningAlert.setContentText("Press ok to continue");
            warningAlert.showAndWait();
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete " + " ?", ButtonType.YES, ButtonType.NO);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Are you sure you want to delete the item with ID: " + selectedItem.getID() + "?");
        alert.setContentText("Press ok to continue");
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            productRequestTable.getItems().remove(selectedItem);
            productRequestVM.removeProductRequest(selectedItem);
        }
    }

    @FXML void onMessengerClicked(MouseEvent event) {
        productRequestVM.openMessengerView();
    }
}

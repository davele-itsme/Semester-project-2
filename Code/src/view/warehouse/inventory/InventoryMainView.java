package view.warehouse.inventory;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.StockItem;
import viewmodel.warehouse.inventory.InventoryMainVM;

import java.time.LocalDate;

/**
 * The view Class for the main Inventory view.
 *
 * @author Kenneth Jensen
 * @author Floring Bordei
 * @author Jaime Lopez
 * @author Dave Joe Lê
 */

public class InventoryMainView {

    @FXML
    private TableView<StockItem> stockItemTable;

    @FXML
    private TableColumn<String, StockItem> nameCol;

    @FXML
    private TableColumn<Integer, StockItem> quantityCol;

    @FXML
    private TableColumn<Integer, StockItem> priceCol;

    @FXML
    private TableColumn<String, StockItem> iDCol;

    @FXML
    private TableColumn<Boolean, StockItem> canExpireCol;

    @FXML
    private TableColumn<LocalDate, StockItem> expiryDateCol;

    @FXML
    private TableColumn<Integer, StockItem> minStockCol;

    @FXML
    private TableColumn<Integer, StockItem> maxStockCol;

    @FXML
    private TextField requestQtyField;

    @FXML
    private Label errorQuantityLabel;

    @FXML
    private Label emptyQuantity;

    @FXML
    private AnchorPane anchorPane;

    private InventoryMainVM inventoryMainVM;

    private StockItem selectedItem;

    /**
     * Creates an InventoryMainView.
     */
    public InventoryMainView() {

    }

    /**
     * An init method instantiating all the required fields.
     *
     * @param inventoryMainVM The {@link InventoryMainVM} viewmodel to be used.
     */
    public void init(InventoryMainVM inventoryMainVM) {
        this.inventoryMainVM = inventoryMainVM;
        stockItemTable.setItems(inventoryMainVM.getStockItems());
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        iDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        canExpireCol.setCellValueFactory(new PropertyValueFactory<>("canExpire"));
        expiryDateCol.setCellValueFactory(new PropertyValueFactory<>("expiryDate"));
        minStockCol.setCellValueFactory(new PropertyValueFactory<>("minStock"));
        maxStockCol.setCellValueFactory(new PropertyValueFactory<>("maxStock"));
        requestQtyField.textProperty().bindBidirectional(inventoryMainVM.requestQtyProperty());
    }


    @FXML
    void onAddItemStockClicked(ActionEvent event) {
        inventoryMainVM.openInventoryAddView();
    }

    @FXML
    void onEmployeeClicked(ActionEvent event) {
        inventoryMainVM.openEmployeeMainView();
    }

    @FXML
    void onDashboardClicked(ActionEvent event) {
        inventoryMainVM.openMainView();
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
    void onRemoveItemStockClicked(ActionEvent event) {
        selectedItem = stockItemTable.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            Alert warningAlert = new Alert(Alert.AlertType.WARNING);
            warningAlert.setTitle("Warning");
            warningAlert.setHeaderText("No stock item has been selected");
            warningAlert.setContentText("Press ok to continue");
            warningAlert.showAndWait();
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete " + " ?", ButtonType.YES, ButtonType.NO);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Are you sure you want to delete the stock item with ID: " + selectedItem.getId() + "?");
        alert.setContentText("Press ok to continue");
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            stockItemTable.getItems().remove(selectedItem);
            inventoryMainVM.removeStockItem(selectedItem);
        }
    }

    @FXML
    void onEditItemStockClicked(ActionEvent event) {

    }

    @FXML
    void onInventoryClicked(ActionEvent event) {
        inventoryMainVM.openInventoryMainView();
    }

    @FXML
    void onProductRequestClicked(ActionEvent event) {
        inventoryMainVM.openProductRequestView();
    }

    @FXML
    void onAddProductRequestClicked(ActionEvent event) {
        selectedItem = stockItemTable.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            Alert warningAlert = new Alert(Alert.AlertType.WARNING);
            warningAlert.setTitle("Warning");
            warningAlert.setHeaderText("No stock item has been selected");
            warningAlert.setContentText("Press ok to continue");
            warningAlert.showAndWait();
        }
        if (isValid()) {
            inventoryMainVM.addProductRequestToList(selectedItem);
        }

    }

    @FXML
    void onDeliveryClicked(ActionEvent event) {
        inventoryMainVM.openDeliveryView();
    }

    private boolean isValid() {
        boolean validQty, emptyQty = false;
        if (requestQtyField.textProperty().getValue().isEmpty()) {
            emptyQty = true;
            emptyQuantity.setVisible(true);
        } else {
            emptyQty = false;
            emptyQuantity.setVisible(false);
        }

        if (!inventoryMainVM.onlyNumbersQuantity() && !emptyQty) {
            validQty = false;
            errorQuantityLabel.setVisible(true);
        } else {
            validQty = true;
            errorQuantityLabel.setVisible(false);
        }

        if (validQty && !emptyQty) {
            return true;
        }
        return false;
    }

    @FXML void onMessengerClicked(MouseEvent event) {
        inventoryMainVM.openMessengerView();
    }

}

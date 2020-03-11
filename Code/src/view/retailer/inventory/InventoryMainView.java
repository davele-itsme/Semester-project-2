package view.retailer.inventory;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.StockItem;
import viewmodel.retailer.inventory.InventoryMainVM;

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
    private AnchorPane anchorPane;

    @FXML
    private TextField requestQtyField;

    @FXML
    private TextField sellQuantityField;

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
        sellQuantityField.textProperty().bindBidirectional(inventoryMainVM.sellQtyProperty());
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
        if (selectedItem == null || requestQtyField.textProperty().getValue().isEmpty()) {
            Alert warningAlert = new Alert(Alert.AlertType.WARNING);
            warningAlert.setTitle("Warning");
            String headerText = "";
            if (selectedItem == null) {
                headerText += "No stock item has been selected.";
            }
            if (requestQtyField.textProperty().getValue().isEmpty()) {
                if(headerText.equals(""))
                {
                    headerText += "Field cannot be empty.";
                }
                else
                {
                    headerText += "\n" + "Field cannot be empty.";
                }
            }
            warningAlert.setHeaderText(headerText);
            warningAlert.setContentText("Press ok to continue");
            warningAlert.showAndWait();

        }
        else{
            inventoryMainVM.addProductRequestToList(selectedItem);
        }

    }

    @FXML
    void onSalesClicked(ActionEvent event) {
        inventoryMainVM.openSalesView();
    }

    @FXML
    void onDeliveryClicked(ActionEvent event) {
        inventoryMainVM.openDeliveryView();
    }

    @FXML
    void onSellClicked(ActionEvent event) {
        selectedItem = stockItemTable.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            Alert warningAlert = new Alert(Alert.AlertType.WARNING);
            warningAlert.setTitle("Warning");
            warningAlert.setHeaderText("No stock item has been selected");
            warningAlert.setContentText("Press ok to continue");
            warningAlert.showAndWait();
        }
        inventoryMainVM.addToSales(selectedItem);
    }

    @FXML void onMessengerClicked(MouseEvent event)
    {
        inventoryMainVM.openMessengerView();
    }
}

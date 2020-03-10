package view.hq.hq.inventoryHQ;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.StockItem;
import viewmodel.hq.hq.inventory.InventoryMainHQVM;

import java.time.LocalDate;

/**
 * The view Class for the main Inventory view.
 *
 * @author Kenneth Jensen
 * @author Floring Bordei
 * @author Jaime Lopez
 * @author Dave Joe Lê
 */

public class InventoryMainHQView {

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

    private InventoryMainHQVM inventoryMainHQVM;

    private StockItem selectedItem;

    /**
     * Creates an InventoryMainView.
     */
    public InventoryMainHQView() {

    }

    /**
     * An init method instantiating all the required fields.
     * @param inventoryMainHQVM The {@link InventoryMainHQVM} viewmodel to be used.
     */
    public void init(InventoryMainHQVM inventoryMainHQVM) {
        this.inventoryMainHQVM = inventoryMainHQVM;
        stockItemTable.setItems(inventoryMainHQVM.getStockItems());
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        iDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        canExpireCol.setCellValueFactory(new PropertyValueFactory<>("canExpire"));
        expiryDateCol.setCellValueFactory(new PropertyValueFactory<>("expiryDate"));
        minStockCol.setCellValueFactory(new PropertyValueFactory<>("minStock"));
        maxStockCol.setCellValueFactory(new PropertyValueFactory<>("maxStock"));
    }


    @FXML
    void onAddItemStockClicked(ActionEvent event) {
        inventoryMainHQVM.openInventoryAddHQView();
    }

    @FXML
    void onEmployeeClicked(ActionEvent event) {
        inventoryMainHQVM.openEmployeeMainHQView();
    }

    @FXML
    void onDashboardClicked(ActionEvent event) {
        inventoryMainHQVM.openMainView();
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

    @FXML
    void onRemoveItemStockClicked(ActionEvent event) {
        selectedItem = stockItemTable.getSelectionModel().getSelectedItem();
        if(selectedItem == null)
        {
            Alert warningAlert = new Alert(Alert.AlertType.WARNING);
            warningAlert.setTitle("Warning");
            warningAlert.setHeaderText("No stock item has been selected");
            warningAlert.setContentText("Press ok to continue");
            warningAlert.showAndWait();
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete", ButtonType.YES, ButtonType.NO);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Are you sure you want to delete the stock item with ID: " + selectedItem.getId() + "?");
        alert.setContentText("Press ok to continue");
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            stockItemTable.getItems().remove(selectedItem);
            inventoryMainHQVM.removeStockItem(selectedItem);
        }
    }

    @FXML
    void onEditItemStockClicked(ActionEvent event) {

    }

    @FXML
    void onInventoryClicked(ActionEvent event) {
        inventoryMainHQVM.openInventoryMainHQView();
    }

    @FXML
    void onRetailerClicked(MouseEvent event) {
        inventoryMainHQVM.openMainRTView();
    }

    @FXML
    void onWarehouseClicked(MouseEvent event) {
        inventoryMainHQVM.openMainWHView();
    }

    @FXML void onHQClicked(MouseEvent event)
    {
        inventoryMainHQVM.openMainView();
    }

    @FXML void onMessengerClicked(MouseEvent event)
    {
        inventoryMainHQVM.openMessengerView();
    }
}

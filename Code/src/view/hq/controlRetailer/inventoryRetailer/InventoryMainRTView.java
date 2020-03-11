package view.hq.controlRetailer.inventoryRetailer;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.StockItem;
import viewmodel.hq.controlRetailer.inventoryRetailer.InventoryRTVM;

import java.time.LocalDate;

/**
        * The view Class for the main Warehouse view.
        *
        * @author Kenneth Jensen
        * @author Floring Bordei
        * @author Jaime Lopez
        * @author Dave Joe Lê
        */

public class InventoryMainRTView {
    @FXML
    private TableView<StockItem> stockItemTable;

    @FXML
    private TableColumn<String, StockItem> nameCol;

    @FXML
    private TableColumn<String, StockItem> iDCol;

    @FXML
    private TableColumn<Integer, StockItem> quantityCol;

    @FXML
    private TableColumn<Integer, StockItem> priceCol;

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

    private InventoryRTVM inventoryRTVM;

    /**
     * Creates an InventoryMainWHView.
     */
    public InventoryMainRTView()
    {

    }

    /**
     * An init method, instantiating all the fields required.
     * @param inventoryRTVM The {@link InventoryRTVM} viewmodel to be used.
     */
    public void init(InventoryRTVM inventoryRTVM)
    {
        this.inventoryRTVM = inventoryRTVM;
        stockItemTable.setItems(inventoryRTVM.getStockItems());
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
    void onCloseClicked(MouseEvent event) {
        Platform.exit();
    }

    @FXML
    void onDashboardClicked(ActionEvent event) {
        inventoryRTVM.openMainView();
    }

    @FXML
    void onEmployeeClicked(ActionEvent event) {
        inventoryRTVM.openEmployeeRTView();
    }

    @FXML
    void onHQClicked(MouseEvent event) {
        inventoryRTVM.openMainHQView();
    }

    @FXML
    void onInventoryClicked(ActionEvent event) {
        inventoryRTVM.openInventoryMainRTView();
    }

    @FXML
    void onMinimizeClicked(MouseEvent event) {
        Stage stage = (Stage)anchorPane.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    void onRetailerClicked(MouseEvent event) {
        inventoryRTVM.openMainRTView();
    }

    @FXML void onDeliveryClicked(ActionEvent event)
    {
        inventoryRTVM.openDeliveryView();
    }

    @FXML void onWarehouseClicked(MouseEvent event) {
        inventoryRTVM.openMainWHView();
    }
}

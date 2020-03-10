package view.retailer.inventory;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import viewmodel.retailer.inventory.InventoryAddVM;

/**
 * The view Class for the add Inventory view.
 *
 * @author Kenneth Jensen
 * @author Floring Bordei
 * @author Jaime Lopez
 * @author Dave Joe Lê
 */

public class InventoryAddView {
    @FXML
    private TextField nameField;

    @FXML
    private TextField iDField;

    @FXML
    private TextField quantityField;

    @FXML
    private TextField priceField;

    @FXML
    private CheckBox canExpireCheckBox;

    @FXML
    private DatePicker expiryDatePicker;

    @FXML
    private TextField minStockField;

    @FXML
    private TextField maxStockField;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Label emptyNameLabel;

    @FXML
    private Label errorPriceLabel;

    @FXML
    private Label errorQuantityLabel;

    @FXML
    private Label errorMinStockLabel;

    @FXML
    private Label errorMaxStockLabel;

    @FXML
    private Label errorExpiryDateLabel;

    private InventoryAddVM inventoryAddVM;

    /**
     * Creates an InventoryAddView.
     */
    public InventoryAddView() {

    }

    /**
     * An init method instantiating all the required fields.
     *
     * @param inventoryAddVM the {@link InventoryAddVM} viewmodel to be used.
     */
    public void init(InventoryAddVM inventoryAddVM) {
        this.inventoryAddVM = inventoryAddVM;
        nameField.textProperty().bindBidirectional(inventoryAddVM.nameProperty());
        iDField.textProperty().bindBidirectional(inventoryAddVM.idProperty());
        quantityField.textProperty().bindBidirectional(inventoryAddVM.quantityProperty());
        priceField.textProperty().bindBidirectional(inventoryAddVM.priceProperty());
        canExpireCheckBox.selectedProperty().bindBidirectional(inventoryAddVM.canExpireProperty());
        expiryDatePicker.valueProperty().bindBidirectional(inventoryAddVM.getExpiryDate());
        minStockField.textProperty().bindBidirectional(inventoryAddVM.minStockProperty());
        maxStockField.textProperty().bindBidirectional(inventoryAddVM.maxStockProperty());

    }

    @FXML
    void onAddClicked(ActionEvent event) {
        if (isEverythingValid()) {
            inventoryAddVM.addStockItem();
            inventoryAddVM.confirmation();
        }
    }

    @FXML
    void onBackClicked(ActionEvent event) {
        inventoryAddVM.goBack();
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
    void onEmployeeClicked(ActionEvent event) {
        inventoryAddVM.openEmployeeView();
    }

    @FXML
    void onDashboardClicked(ActionEvent event) {
        inventoryAddVM.openMainView();
    }

    @FXML
    void onInventoryClicked(ActionEvent event) {
        inventoryAddVM.openInventoryView();
    }

    @FXML
    void canExpireClicked(ActionEvent event) {
        if (canExpireCheckBox.isSelected()) {
            expiryDatePicker.setDisable(false);
        } else {
            expiryDatePicker.setDisable(true);
            expiryDatePicker.setValue(null);
        } //TODO: Violating MVVM pattern?
    }

    private boolean isEverythingValid() {
        boolean emptyName, validExpiryDate, validPrice, validQuantity, validMinStock, validMaxStock = false;

        inventoryAddVM.executeEmpty();
        validExpiryDate = inventoryAddVM.validDate();

        if (nameField.textProperty().getValue().isEmpty()) {
            emptyName = true;
            emptyNameLabel.setVisible(true);
        } else {
            emptyName = false;
            emptyNameLabel.setVisible(false);
        }

        if (validExpiryDate) {
            errorExpiryDateLabel.setVisible(false);
        } else {
            errorExpiryDateLabel.setVisible(true);
        }

        if (!inventoryAddVM.onlyNumbersPrice()) {
            errorPriceLabel.setVisible(true);
            validPrice = false;
        } else {
            errorPriceLabel.setVisible(false);
            validPrice = true;
        }

        if (!inventoryAddVM.onlyNumbersQuantity()) {
            errorQuantityLabel.setVisible(true);
            validQuantity = false;
        } else {
            errorQuantityLabel.setVisible(false);
            validQuantity = true;
        }

        if (!inventoryAddVM.onlyNumbersMaxStock()) {
            errorMaxStockLabel.setVisible(true);
            validMaxStock = false;
        } else {
            errorMaxStockLabel.setVisible(false);
            validMaxStock = true;
        }

        if (!inventoryAddVM.onlyNumbersMinStock()) {
            errorMinStockLabel.setVisible(true);
            validMinStock = false;
        } else {
            errorMinStockLabel.setVisible(false);
            validMinStock = true;
        }

        if (validPrice && validQuantity && validMaxStock && validMinStock && !emptyName && validExpiryDate) {
            return true;
        }
        return false;
    }

    @FXML
    void onProductRequestClicked(ActionEvent event) {
        inventoryAddVM.openProductRequestView();
    }

    @FXML
    void onDeliveryClicked(ActionEvent event) {
        inventoryAddVM.openDeliveryView();
    }

    @FXML
    void onSalesClicked(ActionEvent event) {
        inventoryAddVM.openSalesView();
    }

    @FXML void onMessengerClicked(MouseEvent event)
    {
        inventoryAddVM.openMessengerView();
    }
}

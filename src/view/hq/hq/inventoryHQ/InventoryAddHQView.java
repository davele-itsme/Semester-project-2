package view.hq.hq.inventoryHQ;

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
import viewmodel.hq.hq.inventory.InventoryAddHQVM;

/**
 * The view Class for the add Inventory view.
 *
 * @author Kenneth Jensen
 * @author Floring Bordei
 * @author Jaime Lopez
 * @author Dave Joe Lê
 */

public class InventoryAddHQView {
    @FXML
    private TextField nameField;

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

    private InventoryAddHQVM inventoryAddHQVM;

    /**
     * Creates an InventoryAddView.
     */
    public InventoryAddHQView() {

    }

    /**
     * An init method instantiating all the required fields.
     * @param inventoryAddHQVM the {@link InventoryAddHQVM} viewmodel to be used.
     */
    public void init(InventoryAddHQVM inventoryAddHQVM) {
        this.inventoryAddHQVM = inventoryAddHQVM;
        nameField.textProperty().bindBidirectional(inventoryAddHQVM.nameProperty());
        quantityField.textProperty().bindBidirectional(inventoryAddHQVM.quantityProperty());
        priceField.textProperty().bindBidirectional(inventoryAddHQVM.priceProperty());
        canExpireCheckBox.selectedProperty().bindBidirectional(inventoryAddHQVM.canExpireProperty());
        expiryDatePicker.valueProperty().bindBidirectional(inventoryAddHQVM.getExpiryDate());
        minStockField.textProperty().bindBidirectional(inventoryAddHQVM.minStockProperty());
        maxStockField.textProperty().bindBidirectional(inventoryAddHQVM.maxStockProperty());

    }

    @FXML
    void onAddClicked(ActionEvent event) {
        if(isEverythingValid())
        {
            inventoryAddHQVM.addStockItem();
            inventoryAddHQVM.confirmation();
        }
    }

    @FXML
    void onBackClicked(ActionEvent event) {
        inventoryAddHQVM.goBack();
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
        inventoryAddHQVM.openEmployeeView();
    }

    @FXML
    void onDashboardClicked(ActionEvent event) {
        inventoryAddHQVM.openMainView();
    }

    @FXML
    void onInventoryClicked(ActionEvent event) {
        inventoryAddHQVM.openInventoryView();
    }

    @FXML
    void onRetailerClicked(MouseEvent event) {
        inventoryAddHQVM.openMainRTView();
    }

    @FXML
    void onWarehouseClicked(MouseEvent event) {
        inventoryAddHQVM.openMainWHView();
    }

    @FXML void onHQClicked(MouseEvent event)
    {
        inventoryAddHQVM.openMainView();
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

        inventoryAddHQVM.executeEmpty();
        validExpiryDate = inventoryAddHQVM.validDate();

        if (nameField.textProperty().getValue().isEmpty()) {
            emptyName = true;
            emptyNameLabel.setVisible(true);
        } else {
            emptyName = false;
            emptyNameLabel.setVisible(false);
        }

        if(validExpiryDate)
        {
            errorExpiryDateLabel.setVisible(false);
        }
        else
        {
            errorExpiryDateLabel.setVisible(true);
        }

        if (!inventoryAddHQVM.onlyNumbersPrice()) {
            errorPriceLabel.setVisible(true);
            validPrice = false;
        } else {
            errorPriceLabel.setVisible(false);
            validPrice = true;
        }

        if (!inventoryAddHQVM.onlyNumbersQuantity()) {
            errorQuantityLabel.setVisible(true);
            validQuantity = false;
        } else {
            errorQuantityLabel.setVisible(false);
            validQuantity = true;
        }

        if (!inventoryAddHQVM.onlyNumbersMaxStock()) {
            errorMaxStockLabel.setVisible(true);
            validMaxStock = false;
        } else {
            errorMaxStockLabel.setVisible(false);
            validMaxStock = true;
        }

        if (!inventoryAddHQVM.onlyNumbersMinStock()) {
            errorMinStockLabel.setVisible(true);
            validMinStock = false;
        } else {
            errorMinStockLabel.setVisible(false);
            validMinStock = true;
        }

        if (validPrice && validQuantity && validMaxStock && validMinStock && !emptyName && validExpiryDate)
        {
            return true;
        }
        return false;
    }

    @FXML void onMessengerClicked(MouseEvent event)
    {
        inventoryAddHQVM.openMessengerView();
    }
}
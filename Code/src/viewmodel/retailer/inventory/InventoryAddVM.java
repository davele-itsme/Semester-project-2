package viewmodel.retailer.inventory;

import javafx.beans.property.*;
import javafx.scene.control.Alert;
import model.IDataModel;
import model.StockItem;
import view.retailer.ViewHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

/**
 * The viewmodel Class for the add Inventory view.
 *
 * @author Kenneth Jensen
 * @author Floring Bordei
 * @author Jaime Lopez
 * @author Dave Joe Lê
 */

public class InventoryAddVM {
    private StringProperty name;
    private StringProperty id;
    private StringProperty quantity;
    private StringProperty price;
    private BooleanProperty canExpire;
    private ObjectProperty<LocalDate> expiryDate;
    private Date date;
    private StringProperty minStock;
    private StringProperty maxStock;
    private IDataModel dataModel;
    private ViewHandler viewHandler;

    /**
     * Creates an InventoryAddVM with the specified information and instantiated the required fields.
     * @param dataModel The {@link model.DataModel} to be used.
     * @param viewHandler The {@link ViewHandler} to be used.
     */
    public InventoryAddVM(IDataModel dataModel, ViewHandler viewHandler) {
        this.dataModel = dataModel;
        this.viewHandler = viewHandler;
        name = new SimpleStringProperty();
        id = new SimpleStringProperty();
        quantity = new SimpleStringProperty();
        price = new SimpleStringProperty();
        canExpire = new SimpleBooleanProperty();
        expiryDate = new SimpleObjectProperty<>();
        minStock = new SimpleStringProperty();
        maxStock = new SimpleStringProperty();

        date = new Date(0, 0, 0);
    }

    /**
     * Converts a {@link LocalDate} Object to a {@link Date} Object.
     * @throws ParseException
     */
    public void dateConverter() throws ParseException
    {
        if (expiryDate.get() != null) {
            LocalDate localDate = expiryDate.get();
            int day = localDate.getDayOfMonth();
            int month = localDate.getMonthValue();
            int year = localDate.getYear();
            date = new SimpleDateFormat("MM/dd/yyyy").parse("" + month + "/" + day + "/" + year);
        } else {
            date = null;
        }

    }

    /**
     * Adds a Stock Item to the {@link model.DataModel} with the current information.
     */
    public void addStockItem() {
        try {
            dateConverter();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        StockItem si = new StockItem(name.getValue(), id.getValue(), Integer.parseInt(quantity.getValue()), Integer.parseInt(price.getValue()), canExpire.getValue(), date, Integer.parseInt(minStock.getValue()), Integer.parseInt(maxStock.getValue()), "RT");
        dataModel.addItemFromUser(si);

        //Resetting fields in view
        name.setValue("");
        id.setValue("");
        quantity.setValue("");
        price.setValue("");
        canExpire.set(false);
        expiryDate.setValue(null);
        minStock.setValue("");
        maxStock.setValue("");
    }

    /**
     * This method opens the main Inventory view.
     */
    public void goBack() {
        viewHandler.openInventoryMainView();
    }

    /**
     * Gets the name {@link StringProperty}.
     * @return The name {@link StringProperty}.
     */
    public StringProperty nameProperty() {
        return name;
    }

    /**
     * Gets the ID {@link StringProperty}.
     * @return The ID {@link StringProperty}.
     */
    public StringProperty idProperty() {
        return id;
    }


    /**
     * Gets the quantity {@link StringProperty}.
     * @return The quantity {@link StringProperty}.
     */
    public StringProperty quantityProperty() {
        return quantity;
    }

    /**
     * Gets the price {@link StringProperty}.
     * @return The price {@link StringProperty}.
     */
    public StringProperty priceProperty() {
        return price;
    }

    /**
     * Get the can expire {@link BooleanProperty}.
     * @return The can expire {@link BooleanProperty}.
     */
    public BooleanProperty canExpireProperty() {
        return canExpire;
    }

    /**
     * Gets the expiry date {@link ObjectProperty}.
     * @return The expiry date {@link ObjectProperty}.
     */
    public ObjectProperty<LocalDate> getExpiryDate() {
        return expiryDate;
    }

    /**
     * This method opens the main view.
     */
    public void openMainView() {
        viewHandler.openMainView();
    }

    /**
     * This method opens the main Inventory view.
     */
    public void openInventoryView() {
        viewHandler.openInventoryMainView();
    }

    /**
     * This method opens the main Employee view.
     */
    public void openEmployeeView() {
        viewHandler.openEmployeeMainView();
    }

    /**
     * Gets the minimum stock {@link StringProperty}.
     * @return The minimum stock {@link StringProperty}.
     */
    public StringProperty minStockProperty() {
        return minStock;
    }

    /**
     * Gets the maximum stock {@link StringProperty}.
     * @return The maximum stock {@link StringProperty}.
     */
    public StringProperty maxStockProperty() {
        return maxStock;
    }

    /**
     * This method asks the user for confirmation using an {@link Alert}.
     */
    public void confirmation() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("Stock item has been added");
        alert.setContentText("Press ok to continue");
        alert.showAndWait();
    }

    /**
     * Verifies that the quantity only contains Numbers.
     * @return Whether the quantity only contains Numbers or not.
     */
    public boolean onlyNumbersQuantity() {
        return dataModel.onlyNumbers(quantity.getValue());
    }

    /**
     * Verifies that the price only contains Numbers.
     * @return Whether the price only contains Numbers or not.
     */
    public boolean onlyNumbersPrice()
    {
        return dataModel.onlyNumbers(price.getValue());
    }

    /**
     * Verifies that the minimum stock only contains Numbers.
     * @return Whether the minimum stock only contains Numbers or not.
     */
    public boolean onlyNumbersMinStock()
    {
        return dataModel.onlyNumbers(minStock.getValue());
    }

    /**
     * Verifies that the maximum stock only contains Numbers.
     * @return Whether the maximum stock only contains Numbers or not.
     */
    public boolean onlyNumbersMaxStock()
    {
        return dataModel.onlyNumbers(maxStock.getValue());
    }

    /**
     * Populates the fields with default values if they are empty.
     */
    public void executeEmpty()
    {
        if(quantity.getValue().isEmpty() || quantity.getValue() == null)
        {
            quantity.setValue("0");
        }

        if(price.getValue().isEmpty() || price.getValue() == null)
        {
            price.setValue("0");
        }

        if(minStock.getValue().isEmpty() || minStock.getValue() == null)
        {
            minStock.setValue("0");
        }

        if(maxStock.getValue().isEmpty() || maxStock.getValue() == null)
        {
            maxStock.setValue("0");
        }
    }

    /**
     * Verifies that the expiry date is a valid date.
     * @return Whether the expiry date is a valid date or not.
     */
    public boolean validDate()
    {
        if(canExpire.getValue() && expiryDate.get() == null)
        {
            return false;
        }
        return true;
    }

    public void openProductRequestView() {viewHandler.openProductRequestView();
    }

    public void openDeliveryView() {viewHandler.openDeliveryMainView();
    }

    public void openSalesView() {viewHandler.openSalesView();
    }

    public void openMessengerView() {viewHandler.openMessengerView();
    }
}

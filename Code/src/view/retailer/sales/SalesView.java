package view.retailer.sales;

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
import viewmodel.retailer.sales.SalesVM;

/**
 * The view Class for the Product Request view.
 *
 * @author Kenneth Jensen
 * @author Floring Bordei
 * @author Jaime Lopez
 * @author Dave Joe Lê
 */

public class SalesView {

    @FXML
    private TableView<StockItem> salesTable;

    @FXML
    private TableColumn<String, StockItem> nameCol;

    @FXML
    private TableColumn<Integer, StockItem> quantitySoldCol;

    @FXML
    private TableColumn<Integer, StockItem> pricePerUnitCol;

    @FXML
    private TableColumn<Integer, StockItem > totalProfitCol;

    @FXML
    private AnchorPane anchorPane;

    private SalesVM salesVM;

    /**
     * Creates a SalesView.
     */
    public SalesView() {

    }

    /**
     * An init method instantiating all the required fields.
     *
     * @param salesVM The {@link SalesVM} viewmodel to be used.
     */
    public void init(SalesVM salesVM) {
        this.salesVM = salesVM;
        salesTable.setItems(salesVM.getSales());
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        quantitySoldCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        pricePerUnitCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        totalProfitCol.setCellValueFactory(new PropertyValueFactory<>("totalProfit"));
    }

    @FXML
    void onDashboardClicked(ActionEvent event) {
        salesVM.openMainView();
    }

    @FXML
    void onEmployeeClicked(ActionEvent event) {
        salesVM.openEmployeeMainView();
    }

    @FXML
    void onInventoryClicked(ActionEvent event) {
        salesVM.openInventoryMainView();
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
    void onProductRequestClicked(ActionEvent event) {
        salesVM.openProductRequest();

    }

    @FXML void onDeliveryClicked(ActionEvent event)
    {
        salesVM.openDeliveryView();
    }

    @FXML void onSalesClicked(ActionEvent event)
    {
        salesVM.openSalesView();
    }

    @FXML void onMessengerClicked(MouseEvent event)
    {
        salesVM.openMessengerView();
    }
}

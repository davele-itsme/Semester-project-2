package view.retailer.main;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import viewmodel.retailer.main.MainVM;

/**
 * The main view Class for the Warehouse.
 *
 * @author Kenneth Jensen
 * @author Floring Bordei
 * @author Jaime Lopez
 * @author Dave Joe Lê
 */

public class MainView {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    PieChart pieChart;

    private PieChart.Data costOfGoods = new PieChart.Data("costOfGoods", 0);
    private PieChart.Data profit = new PieChart.Data("profit", 0);
    private PieChart.Data operationalCost = new PieChart.Data("operationalCost", 0);
    private MainVM mainVM;

    /**
     * Creates a MessengerView.
     */
    public MainView() {

    }

    /**
     * An init method instantiating all the required fields.
     *
     * @param mainVM The {@link MainVM} viewmodel to be used.
     */
    public void init(MainVM mainVM) {
        this.mainVM = mainVM;
        costOfGoods.pieValueProperty().bind(mainVM.costOfGoodsProperty());
        profit.pieValueProperty().bind(mainVM.profitProperty());
        operationalCost.pieValueProperty().bind(mainVM.operationalCostProperty());
        costOfGoods.setName("Cost of goods");
        profit.setName("Profit");
        operationalCost.setName("Operational costs");
        ObservableList<PieChart.Data> datas = FXCollections.observableArrayList(costOfGoods, profit, operationalCost);
        pieChart.setData(datas);
        datas.forEach(data -> data.nameProperty().bind(Bindings.concat(data.getName(), " ", data.pieValueProperty())));

    }

    @FXML
    void onEmployeeClicked(ActionEvent event) {
        mainVM.openEmployeeMainView();
    }

    @FXML
    void onInventoryClicked(ActionEvent event) {
        mainVM.openInventoryMainView();

    }

    @FXML
    void onProductRequestClicked(ActionEvent event) {
        mainVM.openRequestMainView();
    }

    @FXML
    void onDashboardClicked(ActionEvent event) {
        mainVM.openMainView();
    }

    @FXML void onDeliveryClicked(ActionEvent event)
    {
        mainVM.openDeliveryView();
    }

    @FXML
    void onSalesClicked(ActionEvent event) {
        mainVM.openSalesMainView();
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

    @FXML void onMessengerClicked(MouseEvent event)
    {
        mainVM.openMessengerView();
    }

}



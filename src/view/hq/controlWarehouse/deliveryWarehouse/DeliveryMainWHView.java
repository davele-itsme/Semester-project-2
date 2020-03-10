package view.hq.controlWarehouse.deliveryWarehouse;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Delivery;
import viewmodel.hq.controlWarehouse.deliveryWarehouse.DeliveryMainWHVM;


import java.util.Date;

/**
 * The view Class for the main Employee view.
 *
 * @author Kenneth Jensen
 * @author Floring Bordei
 * @author Jaime Lopez
 * @author Dave Joe Lê
 */

public class DeliveryMainWHView {

    @FXML
    private TableView<Delivery> deliveryTableView;

    @FXML
    private TableColumn<String, Delivery> iDCol;

    @FXML private TableColumn<String, Delivery> requestFromCol;

    @FXML private TableColumn<String, Delivery> statusCol;

    @FXML private TableColumn<Date, Delivery> dateCol;

    @FXML private TableColumn<Integer, Delivery> totalItemsCol;

    @FXML
    private AnchorPane anchorPane;

    private DeliveryMainWHVM deliveryMainWHVM;

    private Delivery selectedDelivery;

    /**
     * Creates an DeliveryMainRTView.
     */
    public DeliveryMainWHView() {

    }

    /**
     * An init method instantiating all the required fields.
     *
     * @param deliveryMainWHVM the {@link DeliveryMainWHVM} viewmodel to be used.
     */
    public void init(DeliveryMainWHVM deliveryMainWHVM) {
        this.deliveryMainWHVM = deliveryMainWHVM;
        deliveryTableView.setItems(deliveryMainWHVM.getDeliveries());
        iDCol.setCellValueFactory(new PropertyValueFactory<>("requestid"));
        requestFromCol.setCellValueFactory(new PropertyValueFactory<>("requestedFrom"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        totalItemsCol.setCellValueFactory(new PropertyValueFactory<>("totalItems"));

        deliveryTableView.setRowFactory( tv -> {
            TableRow<Delivery> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Delivery rowData = row.getItem();
                    deliveryMainWHVM.openDeliveryItemsView(rowData);
                }
            });
            return row ;
        });
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
    void onDashboardClicked(ActionEvent event) {
        deliveryMainWHVM.openMainView();
    }

    @FXML
    void onInventoryClicked(ActionEvent event) {
        deliveryMainWHVM.openInventoryView();
    }

    @FXML
    void onEmployeeClicked(ActionEvent event) {
        deliveryMainWHVM.openEmployeeMainView();
    }

    @FXML
    void onDeliveryClicked(ActionEvent event) {
        deliveryMainWHVM.openDeliveryClicked();
    }

    @FXML void onOpenDeliveryItemClicked(ActionEvent event)
    {
        selectedDelivery = deliveryTableView.getSelectionModel().getSelectedItem();
        deliveryMainWHVM.openDeliveryItemsView(selectedDelivery);
    }

    @FXML
    void onHQClicked(MouseEvent event) {
        deliveryMainWHVM.openMainHQView();
    }

    @FXML
    void onRetailerClicked(MouseEvent event) {
        deliveryMainWHVM.openMainRTView();
    }

    @FXML void onWarehouseClicked(MouseEvent event)
    {
        deliveryMainWHVM.openMainWHView();
    }
}
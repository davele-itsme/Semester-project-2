package view.hq.controlRetailer.deliveryRetailer;

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
import viewmodel.hq.controlRetailer.deliveryRetailer.DeliveryMainRTVM;

import java.util.Date;

/**
 * The view Class for the main Employee view.
 *
 * @author Kenneth Jensen
 * @author Floring Bordei
 * @author Jaime Lopez
 * @author Dave Joe Lê
 */

public class DeliveryMainRTView {

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

    private DeliveryMainRTVM deliveryMainRTVM;

    private Delivery selectedDelivery;

    /**
     * Creates an DeliveryMainRTView.
     */
    public DeliveryMainRTView() {

    }

    /**
     * An init method instantiating all the required fields.
     *
     * @param deliveryMainRTVM the {@link DeliveryMainRTVM} viewmodel to be used.
     */
    public void init(DeliveryMainRTVM deliveryMainRTVM) {
        this.deliveryMainRTVM = deliveryMainRTVM;
        deliveryTableView.setItems(deliveryMainRTVM.getDeliveries());
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
                    deliveryMainRTVM.openDeliveryItemsView(rowData);
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
        deliveryMainRTVM.openMainView();
    }

    @FXML
    void onInventoryClicked(ActionEvent event) {
        deliveryMainRTVM.openInventoryView();
    }

    @FXML
    void onEmployeeClicked(ActionEvent event) {
        deliveryMainRTVM.openEmployeeMainView();
    }

    @FXML
    void onDeliveryClicked(ActionEvent event) {
        deliveryMainRTVM.openDeliveryClicked();
    }

    @FXML void onOpenDeliveryItemClicked(ActionEvent event)
    {
        selectedDelivery = deliveryTableView.getSelectionModel().getSelectedItem();
        deliveryMainRTVM.openDeliveryItemsView(selectedDelivery);
    }

    @FXML
    void onHQClicked(MouseEvent event) {
        deliveryMainRTVM.openMainHQView();
    }

    @FXML
    void onRetailerClicked(MouseEvent event) {
        deliveryMainRTVM.openMainRTView();
    }

    @FXML void onWarehouseClicked(MouseEvent event)
    {
        deliveryMainRTVM.openMainWHView();
    }
}
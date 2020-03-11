package view.warehouse.delivery;

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
import viewmodel.warehouse.delivery.DeliveryMainVM;

import java.util.Date;

/**
 * The view Class for the main Employee view.
 *
 * @author Kenneth Jensen
 * @author Floring Bordei
 * @author Jaime Lopez
 * @author Dave Joe Lê
 */

public class DeliveryMainView {

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

    private DeliveryMainVM deliveryMainVM;

    private Delivery selectedDelivery;

    /**
     * Creates an DeliveryMainRTView.
     */
    public DeliveryMainView() {

    }

    /**
     * An init method instantiating all the required fields.
     *
     * @param deliveryMainVM the {@link DeliveryMainVM} viewmodel to be used.
     */
    public void init(DeliveryMainVM deliveryMainVM) {
        this.deliveryMainVM = deliveryMainVM;
        deliveryTableView.setItems(deliveryMainVM.getDeliveries());
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
                    deliveryMainVM.openDeliveryItemsView(rowData);
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
        deliveryMainVM.openMainView();
    }

    @FXML
    void onInventoryClicked(ActionEvent event) {
        deliveryMainVM.openInventoryView();
    }

    @FXML
    void onEmployeeClicked(ActionEvent event) {
        deliveryMainVM.openEmployeeMainView();
    }

    @FXML
    void onProductRequestClicked(ActionEvent event) {
        deliveryMainVM.openProductRequestView();
    }

    @FXML
    void onDeliveryClicked(ActionEvent event) {
        deliveryMainVM.openDeliveryClicked();
    }

    @FXML void onOpenDeliveryItemClicked(ActionEvent event)
    {
        selectedDelivery = deliveryTableView.getSelectionModel().getSelectedItem();
        deliveryMainVM.openDeliveryItemsView(selectedDelivery);
    }

    @FXML void onMessengerClicked(MouseEvent event) {
        deliveryMainVM.openMessengerView();
    }
}
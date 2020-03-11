package view.warehouse;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import view.warehouse.delivery.DeliveryItemsView;
import view.warehouse.delivery.DeliveryMainView;
import view.warehouse.employee.EmployeeAddView;
import view.warehouse.employee.EmployeeMainView;
import view.warehouse.inventory.InventoryAddView;
import view.warehouse.inventory.InventoryMainView;
import view.warehouse.messenger.MessengerView;
import view.warehouse.request.ProductRequestView;
import view.warehouse.main.MainView;
import viewmodel.warehouse.ViewModelProvider;

import java.io.IOException;

/**
 * <h1>The ViewHandler Class for the Warehouse</h1>
 * This Class is responsible for opening the different views the Warehouse has access to.
 *
 * @author Kenneth Jensen
 * @author Floring Bordei
 * @author Jaime Lopez
 * @author Dave Joe Lê
 */

public class ViewHandler {
    private Stage primaryStage;
    private ViewModelProvider viewModelProvider;
    private Rectangle2D screenSize;

    /**
     * Creates a ViewHandler with the specified information
     * @param stage The {@link Stage} to be used.
     * @param vmp The {@link ViewModelProvider} to be used.
     */
    public ViewHandler(Stage stage, ViewModelProvider vmp)
    {
        primaryStage = stage;
        primaryStage.setMaximized(true);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        screenSize = Screen.getPrimary().getVisualBounds();
        viewModelProvider = vmp;
        viewModelProvider.instantiateViewModels(this);
    }

    /**
     * The start method, to be called upon the start of our program, which will open the default view.
     */
    public void start() {
        openMainView();
    }

    /**
     * This method opens the main view.
     */
    public void openMainView()
    {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("main/Main.fxml"));
        Parent root = null;
        try{
            root = loader.load();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        MainView view = loader.getController();
        view.init(viewModelProvider.getMainVM());
        primaryStage.setTitle("Dashboard");

        Scene scene = new Scene(root, screenSize.getWidth(), screenSize.getHeight());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * This method opens the main Employee view.
     */
    public void openEmployeeMainView()
    {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("employee/EmployeeMain.fxml"));
        Parent root = null;
        try{
            root = loader.load();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        EmployeeMainView view = loader.getController();
        view.init(viewModelProvider.getEmployeeMainVM());
        primaryStage.setTitle("List of employees");

        Scene scene = new Scene(root, screenSize.getWidth(), screenSize.getHeight());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * This method opens the main Inventory view,
     */
    public void openInventoryMainView()
    {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("inventory/InventoryMain.fxml"));
        Parent root = null;
        try{
            root = loader.load();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        InventoryMainView view = loader.getController();
        view.init(viewModelProvider.getInventoryMainVM());
        primaryStage.setTitle("Stock inventory");

        Scene scene = new Scene(root, screenSize.getWidth(), screenSize.getHeight());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * This method opens the add Employee view.
     */
    public void openEmployeeAddView() {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("employee/EmployeeAdd.fxml"));
        Parent root = null;
        try{
            root = loader.load();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        EmployeeAddView view = loader.getController();
        view.init(viewModelProvider.getEmployeeAddVM());
        primaryStage.setTitle("Add employee");

        Scene scene = new Scene(root, screenSize.getWidth(), screenSize.getHeight());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * This method opens the add Inventory view.
     */
    public void openInventoryAddView() {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("inventory/InventoryAdd.fxml"));
        Parent root = null;
        try{
            root = loader.load();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        InventoryAddView view = loader.getController();
        view.init(viewModelProvider.getInventoryAddVM());
        primaryStage.setTitle("Add stock item");

        Scene scene = new Scene(root, screenSize.getWidth(), screenSize.getHeight());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * This method opens the Product Request view.
     */
    public void openProductRequestView() {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("request/ProductRequest.fxml"));
        Parent root = null;
        try{
            root = loader.load();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        ProductRequestView view = loader.getController();
        view.init(viewModelProvider.getProductRequestVM());
        primaryStage.setTitle("Product request");

        Scene scene = new Scene(root, screenSize.getWidth(), screenSize.getHeight());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void openDeliveryMainView() {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("delivery/DeliveryMain.fxml"));
        Parent root = null;
        try{
            root = loader.load();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        DeliveryMainView view = loader.getController();
        view.init(viewModelProvider.getDeliveryMainVM());
        primaryStage.setTitle("Deliveries");

        Scene scene = new Scene(root, screenSize.getWidth(), screenSize.getHeight());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void openDeliveryItemsView() {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("delivery/DeliveryItems.fxml"));
        Parent root = null;
        try{
            root = loader.load();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        DeliveryItemsView view = loader.getController();
        view.init(viewModelProvider.getDeliveryItemsVM());
        primaryStage.setTitle("Delivery item");

        Scene scene = new Scene(root, screenSize.getWidth(), screenSize.getHeight());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void openMessengerView()
    {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("messenger/Messenger.fxml"));
        Parent root = null;
        try{
            root = loader.load();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        MessengerView view = loader.getController();
        view.init(viewModelProvider.getMessengerVM());
        primaryStage.setTitle("Messenger");

        Scene scene = new Scene(root, screenSize.getWidth(), screenSize.getHeight());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

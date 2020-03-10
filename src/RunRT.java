import javafx.application.Application;
import javafx.stage.Stage;
import model.DataModel;
import model.IDataModel;
import network.client.Client;
import view.retailer.ViewHandler;
import viewmodel.retailer.ViewModelProvider;

public class RunRT extends Application {


    public static void main(String[] args) {
        Application.launch(Run.class);
    }

    @Override
    public void start(Stage stage) throws Exception {
        IDataModel dataModel = new DataModel();
        viewmodel.retailer.ViewModelProvider vmp = new ViewModelProvider(dataModel);
        ViewHandler vh = new ViewHandler(stage, vmp);
        vh.start();
        // Give a argument to the client to reference which data to load
        Client client = new Client("localhost", dataModel,"RT");
        client.run();

    }
}
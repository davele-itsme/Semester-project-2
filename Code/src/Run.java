import javafx.application.Application;
import javafx.stage.Stage;
import model.DataModel;
import model.IDataModel;
import network.client.Client;
import view.warehouse.ViewHandler;
import viewmodel.warehouse.ViewModelProvider;

public class Run extends Application {


    public static void main(String[] args) {
        Application.launch(Run.class);
    }

    @Override
    public void start(Stage stage) throws Exception {
        IDataModel dataModel = new DataModel();
        ViewModelProvider vmp = new ViewModelProvider(dataModel);
        ViewHandler vh = new ViewHandler(stage, vmp);
        vh.start();
        // Give a argument to the client to reference which data to load
        Client client = new Client("192.168.43.206", dataModel,"WH");
        client.run();

    }
}


package network.client;

import model.Counter;
import model.IDataModel;
import model.Message;

import java.io.IOException;
import java.net.Socket;
import java.sql.Timestamp;
import java.util.Date;

/**
 * The Client class of our Client/Server infrastructure.
 * This Class is responsible for setting up the connection and starting 2 Threads, one running a
 * {@link ClientSender} and the other running a {@link ClientReceiver}.
 *
 * @author Kenneth Jensen
 * @author Floring Bordei
 * @author Jaime Lopez
 * @author Dave Joe Lê
 */
public class Client implements Runnable {

    private final int PORT = 5678;
    private String HOST, departmentID;
    private IDataModel dataModel;


    /**
     * Creates a Client connection to the given host and using the given {@link model.DataModel}.
     *
     * @param HOST      The IP Adress of the server the Client needs to connect to
     * @param dataModel The DataModel for the Client to use.
     */
    public Client(String HOST, IDataModel dataModel, String departmentID) {
        this.HOST = HOST;
        this.dataModel = dataModel;
        this.departmentID = departmentID;
    }

    /**
     * The Run method inherited from the {@link Runnable} interface.
     * This method creates a {@link ClientSender} and {@link ClientReceiver} and start a a Thread for each.
     */
    @Override
    public void run() {


        Socket socket = null;
        try {
            socket = new Socket(HOST, PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ClientSender clientSender = new ClientSender(socket, dataModel);
        Thread t1 = new Thread(clientSender);
        t1.start();
        dataModel.setDepartment(departmentID);
        dataModel.loadEmployeeListFromDB(departmentID);
        dataModel.loadItemListFromDB();
        dataModel.loadRequestsFromDB(departmentID);
        dataModel.loadMessagesFromDB();

        if (departmentID.equals("RT")) {
            dataModel.loadSalesFromDB();
        }

        Counter.setupCounter(dataModel.getStockItemList().size(), dataModel.getEmployeeList().size());

        System.out.println("Client Refresh employee list");
        ClientReceiver clientReceiver = new ClientReceiver(socket, dataModel, departmentID);
        Thread t2 = new Thread(clientReceiver);
        t2.start();
    }
}

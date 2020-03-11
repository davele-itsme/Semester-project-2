package Testing;

import com.google.gson.Gson;
import jdbc.DataBaseModel;
import jdbc.ReadWrite;
import model.Employee;
import model.EmployeeList;
import network.Packet;
import network.Server.ServerReceiver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerReceiverTest {

    // Variables needed for the test

    Socket socket;
    ServerReceiver receiver;
    Thread t1;


    @Before
    public void setUp() throws Exception {
        // We set up a Server and connect it with a Client, then setup a DataBaseModel to instantiate the ServerReceiver
        // We then start the receiver in a new Thread to be able to test it

        ServerSocket welcomeSocket = new ServerSocket(1111);
        socket = new Socket("localhost", 1111);
        DataBaseModel dataBaseModel = new DataBaseModel();
        ReadWrite readWrite = new ReadWrite(dataBaseModel);
        receiver = new ServerReceiver(welcomeSocket.accept(), readWrite,1);
        t1 = new Thread(receiver);
        t1.start();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void run() throws IOException, InterruptedException {
        // We set up an ObjectOutputStream using the Socket of the Server, to be able to send information to the Server.
        // Then we Create a Packet with the JSON of an EmployeeList. The test succeeds if the Server output
        // The information of the EmployeeList to the Console

        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        Gson gson = new Gson();
        Employee e = new Employee("kenneth", "jensen", "1", "HQ");
        EmployeeList employeeList = new EmployeeList();
        employeeList.add(e);
        String json = gson.toJson(employeeList);
        Packet packet = new Packet(Packet.EmployeeOperation, json);
        out.writeObject(packet);
        Thread.sleep(500);
    }
}
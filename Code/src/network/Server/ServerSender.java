package network.Server;

import com.google.gson.Gson;
import jdbc.DataBaseModel;
import model.*;
import network.Packet;

import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * <h1>ServerSender Class responsible for the {@link Server}s outgoing traffic</h1>
 * The ServerSocket implements a modified Producer/Consumer pattern with the {@link network.client.ClientReceiver}
 * as the Consumer.
 * It stores a {@link Queue} and methods to add to it, and then sends the {@link Packet}s contained in the {@link Queue}
 * to the {@link network.client.ClientReceiver}.
 *
 * @author Kenneth Jensen
 * @author Floring Bordei
 * @author Jaime Lopez
 * @author Dave Joe Lê
 */

public class ServerSender implements Runnable {

    private Socket socket;
    private Queue<Packet> queue;
    private DataBaseModel dataBaseModel;
    private int clientNo;

    /**
     * Creates ServerSender with the specified information
     *
     * @param socket        The {@link Socket} to be used.
     * @param dataBaseModel The {@link DataBaseModel} to  be used.
     */
    public ServerSender(Socket socket, DataBaseModel dataBaseModel, int clientNo) {
        this.socket = socket;
        this.dataBaseModel = dataBaseModel;
        queue = new LinkedList<>();
        this.clientNo = clientNo;
        dataBaseModel.addListener("EmployeeQuery", this::sendEmployeeList);
        dataBaseModel.addListener("ItemQuery", this::sendItemList);
        dataBaseModel.addListener("SalesQuery", this::sendSalesList);
        dataBaseModel.addListener("NewEmployee",this::newEmployee);
        dataBaseModel.addListener("RequestQuery",this::sendRequestList);
        dataBaseModel.addListener("RequestRefresh",this::requestRefresh);
        dataBaseModel.addListener("DeliveriesQuery",this::sendDeliveriesList);
        dataBaseModel.addListener("NewMessage",this::sendMessage);
        dataBaseModel.addListener("MessageQuery",this::sendMesageList);
        dataBaseModel.addListener("NewItem",this::newItem);
        dataBaseModel.addListener("UpdateEmployee",this::notifyEmployeQuery);
        dataBaseModel.addListener("ItemRefresh",this::itemRefresh);
        dataBaseModel.addListener("SalesRefresh",this::salesRefresh);
        System.out.println("newemployeelistener");

    }

    private void salesRefresh(PropertyChangeEvent propertyChangeEvent) {
        Packet packet = new Packet(Packet.salesRefresh,"");
        addToQueue(packet);
    }

    private void itemRefresh(PropertyChangeEvent propertyChangeEvent) {

            Packet packet = new Packet(Packet.itemUpdate,(String)propertyChangeEvent.getNewValue());
            addToQueue(packet);

    }


    private void notifyEmployeQuery(PropertyChangeEvent propertyChangeEvent) {
        if (clientNo!=(int)propertyChangeEvent.getOldValue())
        {
            Packet packet = new Packet(Packet.employeeUpdate,(String)propertyChangeEvent.getNewValue());
            addToQueue(packet);

        }
    }

    private void newItem(PropertyChangeEvent propertyChangeEvent) {
        if (clientNo != (int) propertyChangeEvent.getOldValue()) {
            Gson gson = new Gson();
            StockItem stockItem = ((StockItem) propertyChangeEvent.getNewValue());
            String json = gson.toJson(stockItem);
            Packet packet = new Packet(Packet.StockOperation, json);
            addToQueue(packet);
            System.out.println("ServerSender: Employee  sent");
        }
        System.out.println("ServerSender: "+clientNo+ " = " +(int)propertyChangeEvent.getOldValue());
    }

    private void sendMesageList(PropertyChangeEvent propertyChangeEvent) {
        if (clientNo==(int)propertyChangeEvent.getOldValue())
        {
            MessageList messages = (MessageList)propertyChangeEvent.getNewValue();
            Gson gson = new Gson();
            String json =gson.toJson(messages);
            Packet packet = new Packet(Packet.messageQuery,json);
            addToQueue(packet);
            System.out.println("ServerSender: sendMessageList");
        }

    }

    private void sendMessage(PropertyChangeEvent propertyChangeEvent) {
        if (clientNo!= (int) propertyChangeEvent.getOldValue())
        {
            Message message =(Message) propertyChangeEvent.getNewValue();
            Gson gson = new Gson();
            String json = gson.toJson(message);
            Packet packet = new Packet(Packet.message,json);
            addToQueue(packet);

            System.out.println("ServerSender : sendMessage");
        }


    }

    private void sendDeliveriesList(PropertyChangeEvent propertyChangeEvent) {
        if (clientNo==(int) propertyChangeEvent.getOldValue())
        {
            DeliveryList deliveryList = (DeliveryList) propertyChangeEvent.getNewValue();
            Gson gson = new Gson();
            String json = gson.toJson(deliveryList);
            Packet packet = new Packet(Packet.deliveriesQuery,json);
            addToQueue(packet);
            System.out.println("ServerSender: sendDeliveriesList");
        }


    }

    private void requestRefresh(PropertyChangeEvent propertyChangeEvent) {
        Gson gson = new Gson();
        String json = "";
        Packet packet = new Packet(Packet.requestRefresh,json);
        addToQueue(packet);
        System.out.println("ServerSender: requestRefresh sent");
    }

    private void sendRequestList(PropertyChangeEvent propertyChangeEvent) {
        ProductRequestList requestList = (ProductRequestList) propertyChangeEvent.getNewValue();
        Gson gson = new Gson();
        String json = gson.toJson(requestList);
        Packet packet = new Packet(Packet.requestQuery,json);
        addToQueue(packet);
        System.out.println("ServerSender: requestList sent");
    }

    private void newEmployee(PropertyChangeEvent propertyChangeEvent) {
        if (clientNo != (int) propertyChangeEvent.getOldValue()) {
            Gson gson = new Gson();
            Employee employee = ((Employee) propertyChangeEvent.getNewValue());
            String json = gson.toJson(employee);
            Packet packet = new Packet(Packet.EmployeeOperation, json);
            addToQueue(packet);
            System.out.println("ServerSender: Employee  sent");
        }
        System.out.println("ServerSender: "+clientNo+ " = " +(int)propertyChangeEvent.getOldValue());

    }

    private void sendSalesList(PropertyChangeEvent propertyChangeEvent) {
        if (clientNo == (int) propertyChangeEvent.getOldValue()) {
            Gson gson = new Gson();
            StockItemList stockItemList = ((StockItemList) propertyChangeEvent.getNewValue());
            String json = gson.toJson(stockItemList);
            Packet packet = new Packet(Packet.salesQuery, json);
            addToQueue(packet);
            System.out.println("ServerSender: Sales List sent");
        }


    }

    /**
     * Sends a {@link StockItemList} to the client.
     *
     * @param propertyChangeEvent The {@link PropertyChangeEvent} that caused this method to be called.
     */
    private void sendItemList(PropertyChangeEvent propertyChangeEvent) {
        if (clientNo == (int) propertyChangeEvent.getOldValue()) {
            Gson gson = new Gson();
            StockItemList stockItemList = ((StockItemList) propertyChangeEvent.getNewValue());
            String json = gson.toJson(stockItemList);
            Packet packet = new Packet(Packet.ItemQuery, json);
            addToQueue(packet);
            System.out.println("ServerSender: StockItemListPacket sent");
        }
    }

    /**
     * Sends a {@link EmployeeList} to the client.
     *
     * @param propertyChangeEvent The {@link PropertyChangeEvent} that caused this method to be called.
     */
    private void sendEmployeeList(PropertyChangeEvent propertyChangeEvent) {
        if (clientNo == (int) propertyChangeEvent.getOldValue()) {
            Gson gson = new Gson();
            EmployeeList employeeList = ((EmployeeList) propertyChangeEvent.getNewValue());
            String json = gson.toJson(employeeList);
            Packet packet = new Packet(Packet.EmployeeQuery, json);
            addToQueue(packet);
            System.out.println("ServerSender: EmployeListPacket sent");
        }
    }

    public int getClientNo() {
        return clientNo;
    }

    /**
     * The run method inherited from {@link Runnable}.
     * The method loops running {@link Thread#sleep(long)} and checking if something is in the {@link Queue}.
     * If there is something in the {@link Queue}, it will send it to the {@link network.client.ClientReceiver}.
     */
    @Override
    public void run() {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            if (queue.isEmpty()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                out.writeObject(queue.poll());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void addToQueue(Packet packet) {
        queue.add(packet);
    }
}

package network.Server;

import com.google.gson.Gson;
import jdbc.DataBaseModel;
import jdbc.ReadWrite;
import model.*;
import network.Packet;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * <h1>ServerReceiver Class responsible for the incoming traffic of the {@link Server}</h1>
 * This class implements a modified Producer/Consumer pattern, with the {@link network.client.ClientSender} as the Producer.
 *
 * @author Kenneth Jensen
 * @author Floring Bordei
 * @author Jaime Lopez
 * @author Dave Joe Lê
 */

public class ServerReceiver implements Runnable {

    private Socket socket;
    private ReadWrite readWrite;
    private int clientNo;

    /**
     * Creates a ServerReceiver with the specified {@link Socket} and {@link DataBaseModel}.
     *
     * @param socket The {@link Socket} for the ServerReceiver to use.
     */
    public ServerReceiver(Socket socket, ReadWrite readWrite, int clientNo) {
        this.socket = socket;
        this.readWrite = readWrite;
        this.clientNo = clientNo;
    }

    public int getClientNo() {
        return clientNo;
    }

    /**
     * The run method inherited from the {@link Runnable}.
     * This method creates an {@link java.io.ObjectOutputStream} with the input stream of the {@link Socket}.
     * It then loops running {@link Thread#sleep(long)} and checking whether something has been sent to it.
     * If something has been sent, it decrypts the {@link Packet} and performs the logic associated with the
     * request contained.
     */
    @Override
    public void run() {
        ObjectInputStream in = null;
        Gson gson = null;
        try {
            in = new ObjectInputStream(socket.getInputStream());
            gson = new Gson();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                Object incoming = in.readObject();
                if (incoming == null) {
                    Thread.sleep(1000);
                    continue;
                }
                Packet packet = (Packet) incoming;
                String json = packet.getJson();
                switch (packet.getOperation()) {
                    case Packet.deliveriesQuery:
                    case Packet.EmployeeQuery:
                    case Packet.ItemQuery:
                    case Packet.messageQuery:
                    case Packet.requestQuery:
                    case Packet.salesQuery:
                        readWrite.acquireRead();
                        readWrite.read(packet,clientNo);
                        readWrite.releaseRead();
                        break;
                    default:
                        readWrite.adquireWrite();
                        readWrite.write(packet,clientNo);
                        readWrite.releaseRead();
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


}

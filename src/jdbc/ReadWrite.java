package jdbc;

import com.google.gson.Gson;
import model.Employee;
import model.Message;
import model.ProductRequest;
import model.StockItem;
import network.Packet;

public class ReadWrite {


    private int activeReaders, activeWriters, waitingWriters;
    private DataBaseModel dataBaseModel;

    public ReadWrite(DataBaseModel dataBaseModel) {
        activeReaders = activeWriters = waitingWriters = 0;
        this.dataBaseModel = dataBaseModel;
    }

    public synchronized void acquireRead() {
        while (activeWriters >= 1 || waitingWriters > 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            activeReaders++;

        }
    }

    public synchronized void releaseRead() {
        activeReaders--;
        if (activeReaders == 0) {
            notify();
        }
    }

    public synchronized void adquireWrite() {
        waitingWriters++;
        while (activeReaders > 0 || activeWriters > 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        activeWriters++;

    }

    public synchronized void releaseWrite() {
        activeWriters--;
        notifyAll();
    }

    public synchronized void write(Packet packet, int clientNo) {
        Gson gson = new Gson();
        String json = packet.getJson();
        switch (packet.getOperation()) {
            case Packet.EmployeeOperation:
                Employee employee = (Employee) gson.fromJson(json, Employee.class);
                System.out.println("ServerReceiver: employee stored to database = " + dataBaseModel.addEmployeeToDataBase(employee, clientNo));
                break;
            case Packet.StockOperation:
                StockItem stockItem = (StockItem) gson.fromJson(json, StockItem.class);
                System.out.println("ServerReceiver: item stored to database = " +
                        dataBaseModel.addItemToDataBase(stockItem, stockItem.getLocation(), clientNo));


                break;
            case Packet.RequestOperation:
                String departmentID1 = json;
                String requestID = "" + dataBaseModel.requestProductMaxID(departmentID1);
                dataBaseModel.setRequestStatus(departmentID1, requestID, clientNo, "Complete");
                break;
            case Packet.DeleteItemFromWH:
                StockItem stockItem1 = gson.fromJson(json, StockItem.class);
                System.out.println("Server Receiver: Stock item deleted from database = " +
                        dataBaseModel.deleteItemByIdAndDepartment(stockItem1.getId(), stockItem1.getLocation(), clientNo));
                break;
            case Packet.DeleteItemFromHQ:
                StockItem stockItem2 = gson.fromJson(json, StockItem.class);
                dataBaseModel.deleteItemByIdAndDepartment(stockItem2.getId(), stockItem2.getLocation(), clientNo);
                break;
            case Packet.DeleteEmployee:
                employee = gson.fromJson(json, Employee.class);
                System.out.println("Employee: " + employee.getId() + " deleted = " + dataBaseModel.deleteEmployee(employee, clientNo));
                break;
            case Packet.AddSale:
                StockItem stockItem3 = gson.fromJson(json, StockItem.class);
                System.out.println("ServerReceiver: sale added to database = " + dataBaseModel.addSaleToDataBase(stockItem3, clientNo));
                break;
            case Packet.addProductRequest:
                ProductRequest productRequest = gson.fromJson(json, ProductRequest.class);
                String department = productRequest.getStockItem().getLocation();

                int departmentid = dataBaseModel.requestProductMaxID(department);

                if (departmentid == 0) {
                    departmentid = dataBaseModel.requestCountQuery();

                    dataBaseModel.addRequestToDataBase(productRequest.getStockItem().getLocation(), clientNo);
                    departmentid++;
                }


                System.out.println(dataBaseModel.addRequestItemToDatabase(productRequest, departmentid, clientNo));
                break;
            case Packet.message:

                Message message = gson.fromJson(json, Message.class);
                dataBaseModel.addMessage(message, clientNo);
                break;

            case Packet.messageQuery:
                dataBaseModel.messageQuery(clientNo);
                break;

        }
    }

    public void read(Packet packet, int clientNo) {
        String json = packet.getJson();
        switch (packet.getOperation()) {


            case Packet.EmployeeQuery:
                String departmentID = json;
                dataBaseModel.employeeQuery(departmentID, clientNo);

                break;
            case Packet.ItemQuery:
                String depID = json;
                dataBaseModel.itemQuery(depID, clientNo);
                System.out.println("ServerReceiver: ItemQuery()called in DB");
                break;

            case Packet.salesQuery:
                dataBaseModel.salesQuery(clientNo);
                System.out.println("ServerReceiver: SalesQuery");
                break;

            case Packet.requestQuery:
                dataBaseModel.requestQuery(clientNo, json);
            case Packet.deliveriesQuery:
                dataBaseModel.deliveriesQuery(json, clientNo);
                break;

            case Packet.messageQuery:
                dataBaseModel.messageQuery(clientNo);
                break;

        }
    }
}

package jdbc;

import model.*;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DataBaseModel {
    private Connection connection;
    private PreparedStatement departmentInsertStatement;
    private PreparedStatement employeeInsertStatement;
    private PreparedStatement employeeQuery;
    private PreparedStatement stockItemInsertStatement;
    private PreparedStatement stockItemQuery;
    private PreparedStatement requestInsertStatement;
    private PreparedStatement itemListInsertStatement;
    private PreparedStatement deleteItemByIDandDep;
    private PreparedStatement deleteEmployee;
    private PreparedStatement addSale;
    private PreparedStatement addMessage;
    private PreparedStatement deleteSale;
    private PropertyChangeSupport changeSupport;

    public DataBaseModel() {
        setConnection();
        departmentInsertStatement = prepareDepartmentStatement();
        employeeInsertStatement = prepareEmployeeStatement();
        stockItemQuery = prepareWHItemQuery();
        stockItemInsertStatement = prepareStockItemStatement();
        requestInsertStatement = prepareInsertRequest();
        itemListInsertStatement = prepareInsertItemListRequest();
        changeSupport = new PropertyChangeSupport(this);
        deleteItemByIDandDep = prepareDeleteItemFromWH();
        deleteEmployee = prepareDeleteEmployee();
        employeeQuery = prepareEmployeeQuery();
        addSale = prepareAddSale();
        addMessage = prepareAddMessage();
        deleteSale = prepareDeleteSale();


    }


    private PreparedStatement prepareAddMessage() {
        String preparedSql = "INSERT INTO \"Sep2\".chatlog (message,time,senderID) " +
                "SELECT * FROM (SELECT ?,?::TIMESTAMP,?) AS TMP;";
        addMessage = null;

        try {
            addMessage = connection.prepareStatement(preparedSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return addMessage;
    }

    //ServerSender Listens to DataBase Model
    public void addListener(String evt, PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(evt, listener);
    }

    //Conection to postgres
    public void setConnection() {
        //Settings for Database
        String driver = "org.postgresql.Driver";
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String pw = "123321";
        connection = null;
        //magic. Something about loading the JDBC driver.
        try {
            Class.forName(driver);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(url, user, pw);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Creates a empty employee table
    public void createEmployeeTable() {
        String sql = "CREATE TABLE IF NOT EXISTS\"Sep2\".Employee (" +
                "   EmployeeID varchar(30) NOT NULL PRIMARY KEY," +
                "departmentID varchar(30) NOT NULL ," +
                " firstName varchar(30) NOT NULL," +
                "lastName varchar(30) NOT NULL," +
                " FOREIGN KEY (departmentID) REFERENCES \"Sep2\".Department(departmentID)" +
                ");";
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //Creates a empty department table;
    public void createDepartmentTable() {
        String sql = "CREATE TABLE IF NOT EXISTS\"Sep2\".Department (" +
                "departmentID varchar(30) NOT NULL PRIMARY KEY ," +
                " departmentName varchar(30) NOT NULL" +
                ");";
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

    }

    public void createSep2Schema() {
        String sql = "CREATE SCHEMA IF NOT EXISTS \"Sep2\";";
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Creates empty StockItem table
    public void createStockItemTable() {
        String sql = "CREATE TABLE IF NOT EXISTS\"Sep2\".StockItem (" +
                "   id varchar(30) NOT NULL," +
                "name varchar(30) NOT NULL ," +
                " quantity int NOT NULL," +
                "price int NOT NULL," +
                "expiryDate Date, " +
                "location varchar(30) NOT NULL REFERENCES \"Sep2\".department(departmentid)," +
                "PRIMARY KEY (id, location)" +
                ");";
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    //Creates empty item request table
    public void createItemRequestTable() {
        String sql = "CREATE TABLE IF NOT EXISTS\"Sep2\".itemRequest (" +
                " RequestID varchar(6) NOT NULL ," +
                "itemID varchar(10) NOT NULL," +
                " quantity int NOT NULL," +
                " CONSTRAINT ItemRequest_CK PRIMARY KEY (requestID, itemID)," +
                " FOREIGN KEY (requestID) REFERENCES \"Sep2\".request(requestID)" +
                ");";

        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    //Creates empty  request table
    public void createRequestTable() {
        String sql = "CREATE TABLE IF NOT EXISTS\"Sep2\".Request (" +
                " RequestID varchar(6) NOT NULL PRIMARY KEY ," +
                "requestedFrom varchar(10) NOT NULL," +
                " status varchar(30) NOT NULL, " +
                " time timestamp" +

                ");";
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    //Prepares a employee statement to improve DB performance for simple queries that are used multiple times
    public PreparedStatement prepareEmployeeStatement() {
        String preparedSql = "INSERT INTO \"Sep2\".employee (employeeID,departmentID,firstName,lastName) " +
                "SELECT * FROM (SELECT ?,?,?,?) AS tmp " +
                "WHERE NOT EXISTS (SELECT employeeID FROM \"Sep2\".employee " +
                "WHERE employeeID = ?) LIMIT 1;";
        PreparedStatement employeeStatement = null;

        try {
            employeeStatement = connection.prepareStatement(preparedSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeeStatement;
    }

    //Prepares a employee statement to improve DB performance for simple queries that are used multiple times
    public PreparedStatement prepareDepartmentStatement() {
        String preparedSql = "INSERT INTO \"Sep2\".department (departmentID,departmentName) " +
                "SELECT * FROM (SELECT ?,?) AS tmp " +
                "WHERE NOT EXISTS (SELECT departmentID FROM \"Sep2\".department " +
                "WHERE departmentID = ?) LIMIT 1;";
        PreparedStatement departmentStatement = null;

        try {
            departmentStatement = connection.prepareStatement(preparedSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departmentStatement;
    }

    //Uses a prepared statement and 2 String to add 1 row to the department table
    public void addDepartmentToDataBase(String departmentID, String departmentName) {
        try {
            departmentInsertStatement.setString(1, departmentID);
            departmentInsertStatement.setString(2, departmentName);
            departmentInsertStatement.setString(3, departmentID);
            departmentInsertStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //Uses a prepared statement and 2 String to add 1 row to the department table
    public boolean addEmployeeToDataBase(Employee employee, int clientNo) {
        try {
            employeeInsertStatement.setString(1, employee.getId());
            employeeInsertStatement.setString(2, employee.getDepartmentID());
            employeeInsertStatement.setString(3, employee.getFirstName());
            employeeInsertStatement.setString(4, employee.getLastName());
            employeeInsertStatement.setString(5, employee.getId());
            employeeInsertStatement.executeUpdate();
            changeSupport.firePropertyChange("NewEmployee", clientNo, employee);
            changeSupport.getPropertyChangeListeners().toString();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;


        }

    }

    //Departament query prints out all rows in the department table
    public void departmentQuery(int clientNo) {
        ArrayList<Object[]> results = new ArrayList<>();
        try {
            String departmentQuery = "SELECT * FROM \"Sep2\".department;";
            PreparedStatement departmentQueryStatement = connection.prepareStatement(departmentQuery);
            ResultSet resultSet = departmentQueryStatement.executeQuery();


            while (resultSet.next()) {
                Object[] row = new Object[resultSet.getMetaData().getColumnCount()];
                for (int i = 0; i < row.length; i++) {
                    row[i] = resultSet.getObject(i + 1);
                }
                results.add(row);


            }
            for (int i = 0; i < results.size(); i++) {
                Object[] row = results.get(i);
                String departmentID = row[0].toString();
                String departmentName = row[1].toString();

                System.out.println("DepartmentID: " + departmentID + " Department Name: " + departmentName);
                //todo create department object instead sysout
            }
            resultSet.close();
            departmentQueryStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void employeeQuery(String departmentID, int clientNo) {
        ArrayList<Object[]> results = new ArrayList<>();
        EmployeeList employeeList = new EmployeeList();
        try {
            ResultSet resultSet = null;
            if (!departmentID.equals("HQ")) {
                employeeQuery.setString(1, departmentID);
                resultSet = employeeQuery.executeQuery();
            } else {
                String sql = "SELECT * FROM \"Sep2\".employee;";
                PreparedStatement employee = connection.prepareStatement(sql);
                resultSet = employee.executeQuery();

            }

            while (resultSet.next()) {
                Object[] row = new Object[resultSet.getMetaData().getColumnCount()];
                for (int i = 0; i < row.length; i++) {
                    row[i] = resultSet.getObject(i + 1);
                }
                results.add(row);

            }

            for (int i = 0; i < results.size(); i++) {
                Object[] row = results.get(i);
                String employeeID = row[0].toString();
                String depID = row[1].toString();
                String firstName = row[2].toString();
                String lastName = row[3].toString();

                Employee e = new Employee(firstName, lastName, employeeID, depID);
                employeeList.add(e);
            }

            resultSet.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }
        changeSupport.firePropertyChange("EmployeeQuery", clientNo, employeeList);
        System.out.println("DataBaseModel: Employee query fired");


    }

    public void itemQuery(String departmentID, int clientNo) {

        ArrayList<Object[]> results = new ArrayList<>();
        StockItemList stockItemList = new StockItemList();
        try {
            ResultSet resultSet = null;
            if (!departmentID.equals("HQ")) {
                stockItemQuery.setString(1, departmentID);
                resultSet = stockItemQuery.executeQuery();
            } else {
                String sql = "SELECT * FROM \"Sep2\".stockitem;";
                PreparedStatement itemQuery = connection.prepareStatement(sql);
                resultSet = itemQuery.executeQuery();
            }

            while (resultSet.next()) {
                Object[] row = new Object[resultSet.getMetaData().getColumnCount()];
                for (int i = 0; i < row.length; i++) {
                    row[i] = resultSet.getObject(i + 1);
                }
                results.add(row);

            }

            for (int i = 0; i < results.size(); i++) {
                Object[] row = results.get(i);
                String itemID = row[0].toString();
                String itemName = row[1].toString();
                int qty = (int) row[2];
                int price = (int) row[3];
                java.util.Date sqlDate = (java.sql.Date) row[4];
                String location = row[5].toString();
                java.util.Date date = new java.util.Date(sqlDate.getDay(), sqlDate.getMonth(), sqlDate.getYear());

                StockItem stockItem = new StockItem(itemName, itemID, qty, price, true, date, 10, 100, location);
                //todo max stock is not set
                stockItemList.add(stockItem);
            }
            resultSet.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }
        changeSupport.firePropertyChange("ItemQuery", clientNo, stockItemList);
        System.out.println("DataBaseModel: ItemQueryFired query fired");


    }

    public PreparedStatement prepareEmployeeQuery() {
        String preparedStatement = "SELECT * FROM \"Sep2\".employee where departmentID = ? ;";


        try {
            employeeQuery = connection.prepareStatement(preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeeQuery;
    }

    private static java.sql.Date getCurrentDate() {
        java.util.Date today = new java.util.Date();
        return new java.sql.Date(today.getTime());
    }


    //Uses a prepared statement and 2 String to add 1 row to the department table
    public boolean addItemToDataBase(StockItem stockItem, String department, int clientNo) {
        try {
            java.sql.Date sqlDate = null;
            if (stockItem.getExpiryDate() != null) {
                sqlDate = new java.sql.Date(stockItem.getExpiryDate().getDay(), stockItem.getExpiryDate().getMonth(), stockItem.getExpiryDate().getYear());
            } else {
                sqlDate = new java.sql.Date(2100, 1, 1);
            }
            stockItemInsertStatement.setString(1, stockItem.getId());
            stockItemInsertStatement.setString(2, stockItem.getName());
            stockItemInsertStatement.setInt(3, stockItem.getQuantity());
            stockItemInsertStatement.setInt(4, stockItem.getPrice());
            stockItemInsertStatement.setDate(5, sqlDate);
            stockItemInsertStatement.setString(6, department);
            stockItemInsertStatement.setString(7, stockItem.getId());
            stockItemInsertStatement.setString(8, department);

            stockItemInsertStatement.executeUpdate();
            changeSupport.firePropertyChange("NewItem", clientNo, stockItem);
            return true;


        } catch (SQLException e) {
            e.printStackTrace();
            return false;


        }

    }

    public PreparedStatement prepareStockItemStatement() {
        String preparedSql = "INSERT INTO \"Sep2\".stockitem (id,name,quantity,price,expiryDate,location) " +
                "SELECT * FROM (SELECT ?,?,?,?,? :: DATE,?) AS tmp " +
                "WHERE NOT EXISTS (SELECT id,location FROM \"Sep2\".stockitem " +
                "WHERE id = ? and location = ?) LIMIT 1;";
        PreparedStatement stockItemStatement = null;

        try {
            stockItemStatement = connection.prepareStatement(preparedSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stockItemStatement;
    }


    public PreparedStatement prepareWHItemQuery() {
        String preparedStatement = "SELECT * FROM \"Sep2\".stockitem where location = ? ;";


        try {
            stockItemQuery = connection.prepareStatement(preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stockItemQuery;
    }


    public PreparedStatement prepareInsertRequest() {
        String preparedSql = "INSERT INTO \"Sep2\".request (requestID,requestedFrom,status,time) " +
                "SELECT * FROM (SELECT ?,?,?,?::timestamp) AS tmp " +
                "WHERE NOT EXISTS (SELECT requestID FROM \"Sep2\".request " +
                "WHERE requestID = ?) LIMIT 1;";
        requestInsertStatement = null;


        try {
            requestInsertStatement = connection.prepareStatement(preparedSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requestInsertStatement;
    }

    public int addRequestToDataBase(String requestedFrom, int clientNo) {
        try {
            Date date = new Date();
            Timestamp timestamp = new java.sql.Timestamp(date.getTime());
            int count = requestCountQuery() + 1;
            requestInsertStatement.setString(1, "" + count);
            requestInsertStatement.setString(2, requestedFrom);
            requestInsertStatement.setString(3, "Null");
            requestInsertStatement.setTimestamp(4, timestamp);
            requestInsertStatement.setString(5, "" + count);
            requestInsertStatement.executeUpdate();
            return count;

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;


        }

    }

    public int requestCountQuery() {

        int count = 0;
        try {
            String requestCount = "SELECT * FROM \"Sep2\".request;";
            PreparedStatement requestCountStatement = connection.prepareStatement(requestCount);
            ResultSet resultSet = requestCountStatement.executeQuery();
            while (resultSet.next()) {
                count++;

            }
            resultSet.close();
            requestCountStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(count);
        return count;
    }

    public int requestProductMaxID(String department) {

        String maxID = "0";
        try {
            String requestCount = "select max(requestid) from \"Sep2\".request where requestedfrom ='" + department + "';";
            PreparedStatement requestCountStatement = connection.prepareStatement(requestCount);
            ResultSet resultSet = requestCountStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getObject(1) != null)
                    maxID = resultSet.getObject(1).toString();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Integer.parseInt(maxID);
    }

    public boolean setRequestStatus(String department, String requestID, int clientNo, String status) {
        try {
            String sql = "UPDATE \"Sep2\".request " +
                    "SET status = '" + status + "' " +
                    "WHERE requestid ='" + requestID + "';";
            PreparedStatement setRequestStatus = connection.prepareStatement(sql);
            setRequestStatus.executeUpdate();
            requestTransaction(clientNo, requestID, department);
            changeSupport.firePropertyChange("CompleteRequest", null, null);
            addRequestToDataBase(department, clientNo);

            //todo fix manual input
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }


    }

    public boolean addRequestItemToDatabase(ProductRequest productRequest, int requestID, int clientNo) {
        try {
            itemListInsertStatement.setString(1, "" + requestID);
            itemListInsertStatement.setString(2, productRequest.getProductId());
            itemListInsertStatement.setInt(3, productRequest.getQuantity());
            System.out.println(productRequest.getQuantity() + "--------------------");
            itemListInsertStatement.executeUpdate();

            return true;


        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }


    }

    public PreparedStatement prepareInsertItemListRequest() {
        String preparedSql = "INSERT INTO \"Sep2\".itemRequest (requestID,itemID,quantity) " +
                "SELECT * FROM (SELECT ?,?,?) AS tmp ;";

        itemListInsertStatement = null;

        try {
            itemListInsertStatement = connection.prepareStatement(preparedSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return itemListInsertStatement;
    }

    public boolean deleteItemByIdAndDepartment(String id, String department, int clientNo) {
        try {
            deleteItemByIDandDep.setString(1, id);
            deleteItemByIDandDep.setString(2, department);
            deleteItemByIDandDep.executeUpdate();
            changeSupport.firePropertyChange("ItemRefresh", clientNo, department);
            return true;


        } catch (SQLException e) {
            e.printStackTrace();
            return false;


        }

    }


    public PreparedStatement prepareDeleteItemFromWH() {
        String preparedSql = "DELETE FROM \"Sep2\".stockitem " +
                "where id = ? and location = ? ;";

        deleteItemByIDandDep = null;

        try {
            deleteItemByIDandDep = connection.prepareStatement(preparedSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deleteItemByIDandDep;
    }

    public PreparedStatement prepareDeleteEmployee() {
        String preparedSql = "DELETE FROM \"Sep2\".employee " +
                "where employeeID = ? ;";

        deleteEmployee = null;

        try {
            deleteEmployee = connection.prepareStatement(preparedSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deleteEmployee;
    }

    public boolean deleteEmployee(Employee employee, int clientNo) {
        try {


            deleteEmployee.setString(1, employee.getId());
            deleteEmployee.executeUpdate();

            changeSupport.firePropertyChange("UpdateEmployee", clientNo, employee.getDepartmentID());
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }


    }

    public void createSalesTable() {
        String sql = "CREATE TABLE IF NOT EXISTS \"Sep2\".sales (" +
                "productID varchar(30) NOT NULL , " +
                "quantitySold int," +
                "CONSTRAINT salesPK PRIMARY KEY (productID)" +
                ");";
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void createSalesListTable() {
        String sql = "CREATE TABLE IF NOT EXISTS\"Sep2\".salesList (" +
                " saleID varchar(10) NOT NULL PRIMARY KEY ," +
                "DateOfSale DATE, " +
                "status varchar(30) " +
                ");";
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public PreparedStatement prepareAddSale() {
        String preparedSql = "INSERT INTO \"Sep2\".sales (productID, quantitySold) " +
                "SELECT * FROM (SELECT ?,?) AS tmp " +
                "WHERE NOT EXISTS (SELECT productID FROM \"Sep2\".sales " +
                "WHERE  productID = ?) LIMIT 1;";
        addSale = null;

        try {
            addSale = connection.prepareStatement(preparedSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return addSale;
    }

    public PreparedStatement prepareDeleteSale() {
        String preparedSql = "delete from \"Sep2\".stockitem where id = ? and location = ? " +
                "SELECT * FROM (SELECT ?,?) AS tmp ;";

        deleteSale = null;

        try {
            deleteSale = connection.prepareStatement(preparedSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deleteSale;
    }

    public boolean addSaleToDataBase(StockItem stockItem, int clientNo) {
        try {
            addSale.setString(1, stockItem.getId());
            addSale.setInt(2, stockItem.getQuantity());
            addSale.setString(3, stockItem.getId());
            addSale.executeUpdate();
            changeSupport.firePropertyChange("SalesRefresh", 0, clientNo);

            String sql = "update \"Sep2\".stockitem set quantity = quantity-" + stockItem.getQuantity() + " where id ='" + stockItem.getId() + "' and location ='" + stockItem.getLocation() + "';";
            addSale = connection.prepareStatement(sql);
            addSale.executeUpdate();

            changeSupport.firePropertyChange("ItemRefresh", clientNo, "RT");


            return true;

        } catch (SQLException e) {
            e.printStackTrace();

            System.out.println(new Exception("Item already exists update has been performed"));
            String sql = "update \"Sep2\".sales set quantitysold = quantitysold +" +
                    stockItem.getQuantity() + " where productid ='" +
                    stockItem.getId() + "'; ";
            try {
                PreparedStatement update = connection.prepareStatement(sql);
                update.executeUpdate();
                changeSupport.firePropertyChange("SalesRefresh", 0, clientNo);
                String SQL = "update \"Sep2\".stockitem set quantity = quantity-" + stockItem.getQuantity() + " where id ='" + stockItem.getId() + "' and location ='" + stockItem.getLocation() + "';";
                addSale = connection.prepareStatement(SQL);
                addSale.executeUpdate();
                changeSupport.firePropertyChange("ItemRefresh", clientNo, "RT");
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

            return false;
        }

    }


    public void salesQuery(int clientNo) {
        ArrayList<Object[]> results = new ArrayList<>();
        StockItemList salesList = new StockItemList();
        try {
            String salesSQLQuery =
                    "SELECT  quantitySold,productID,name,price FROM \"Sep2\".sales " +
                            " LEFT JOIN \"Sep2\".stockItem on stockItem.id = sales.productID and stockitem.location = 'RT';";
            PreparedStatement salesQuery = connection.prepareStatement(salesSQLQuery);
            ResultSet resultSet = salesQuery.executeQuery();


            while (resultSet.next()) {
                Object[] row = new Object[resultSet.getMetaData().getColumnCount()];
                for (int i = 0; i < row.length; i++) {
                    row[i] = resultSet.getObject(i + 1);
                }
                results.add(row);


            }
            for (int i = 0; i < results.size(); i++) {
                Object[] row = results.get(i);
                int quantitySold = (int) row[0];
                String productID = row[1].toString();
                String name = row[2].toString();
                int price = (int) row[3];

                StockItem stockItem = new StockItem(name, productID, quantitySold, price, false, null, 0, 0, null);
                salesList.add(stockItem);

            }
            changeSupport.firePropertyChange("SalesQuery", clientNo, salesList);
            resultSet.close();
            salesQuery.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void requestQuery(int clientNo, String departmentID) {
        ArrayList<Object[]> results = new ArrayList<>();
        ProductRequestList requestList = new ProductRequestList();
        try {
            String SQLQuery =
                    //todo query last id from request join itemlist on requestID
                    "select stockitem.name,itemrequest.itemid,itemrequest.quantity " +
                            "from \"Sep2\".request " +
                            "join \"Sep2\".itemrequest on request.requestid = itemrequest.requestid " +
                            "join \"Sep2\".stockitem on itemrequest.itemid = stockitem.id " +
                            "and itemrequest.requestid = (select max(requestid) from \"Sep2\".request)::VARCHAR(10)" +
                            "and request.status = 'Null'   and stockitem.location = '" + departmentID + "';";
            PreparedStatement requestQuery = connection.prepareStatement(SQLQuery);
            ResultSet resultSet = requestQuery.executeQuery();


            while (resultSet.next()) {
                Object[] row = new Object[resultSet.getMetaData().getColumnCount()];
                for (int i = 0; i < row.length; i++) {
                    row[i] = resultSet.getObject(i + 1);
                }
                results.add(row);


            }
            for (int i = 0; i < results.size(); i++) {
                Object[] row = results.get(i);
                String name = row[0].toString();
                String itemID = row[1].toString();
                int quantity = (int) row[2];

                StockItem stockItem = new StockItem(name, itemID, quantity, 0, false, null, 0, 0, null);
                ProductRequest productRequest = new ProductRequest(stockItem, quantity);
                requestList.addRequestToList(productRequest);

            }
            changeSupport.firePropertyChange("RequestQuery", clientNo, requestList);
            resultSet.close();
            requestQuery.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void requestTransaction(int clientNo, String requestID, String requestedfrom) {
        ArrayList<Object[]> results = new ArrayList<>();
        StockItemList salesList = new StockItemList();
        try {
            String sql =
                    "select itemid,quantity from \"Sep2\".request join \"Sep2\".itemrequest " +
                            "on request.requestid = itemrequest.requestid " +
                            "and requestedfrom ='" + requestedfrom + "' and request.requestid='" + requestID + "';";
            PreparedStatement transaction = connection.prepareStatement(sql);
            ResultSet resultSet = transaction.executeQuery();
            String sendFrom = "";
            if (requestedfrom.equals("WH")) {
                sendFrom = "HQ";
            } else sendFrom = "WH";


            while (resultSet.next()) {
                Object[] row = new Object[resultSet.getMetaData().getColumnCount()];
                for (int i = 0; i < row.length; i++) {
                    row[i] = resultSet.getObject(i + 1);
                }
                results.add(row);


            }
            for (int i = 0; i < results.size(); i++) {
                Object[] row = results.get(i);
                String itemID = row[0].toString();
                int quantityRequested = (int) row[1];

                sql = "update \"Sep2\".stockitem set quantity = quantity-" + quantityRequested + " where id ='" + itemID + "' and location ='" + sendFrom + "';";
                transaction = connection.prepareStatement(sql);
                transaction.executeUpdate();
                sql = "update \"Sep2\".stockitem set quantity = quantity+" + quantityRequested + " where id ='" + itemID + "' and location ='" + requestedfrom + "';";
                transaction = connection.prepareStatement(sql);
                transaction.executeUpdate();

            }
            //todo update all clients new data
            changeSupport.firePropertyChange("RequestRefresh", 0, clientNo);
            resultSet.close();
            transaction.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void deliveriesQuery(String departmentID, int clientNo) {
        ArrayList<Object[]> results = new ArrayList<>();
        DeliveryList deliveryList = new DeliveryList();
        ProductRequestList productRequestList = new ProductRequestList();
        try {
            String sql = "";
            if (departmentID.equals("RT")) {
                sql =
                        "SELECT * FROM \"Sep2\".request where requestedFrom ='" + departmentID + "';";
            } else {
                sql = "SELECT * FROM \"Sep2\".request;";
            }

            PreparedStatement deliveriesQuery = connection.prepareStatement(sql);
            ResultSet resultSet = deliveriesQuery.executeQuery();


            while (resultSet.next()) {
                Object[] row = new Object[resultSet.getMetaData().getColumnCount()];
                for (int i = 0; i < row.length; i++) {
                    row[i] = resultSet.getObject(i + 1);
                }
                results.add(row);


            }
            for (int i = 0; i < results.size(); i++) {
                Object[] row = results.get(i);
                String requestID = row[0].toString();
                String requestedFrom = row[1].toString();
                String status = row[2].toString();
                Timestamp timestamp = (Timestamp) row[3];

                Delivery delivery = new Delivery(requestID, requestedFrom, status, timestamp);
                deliveryList.addDelivery(delivery);
            }


            sql = "select request.requestid, itemid, name ,itemrequest.quantity from \"Sep2\".request join \"Sep2\".itemrequest" +
                    " on request.requestid = itemrequest.requestid join \"Sep2\".stockitem" +
                    " on stockitem.id =itemrequest.itemid and stockitem.location ='HQ'";
            deliveriesQuery = connection.prepareStatement(sql);
            resultSet = deliveriesQuery.executeQuery();

            results = new ArrayList<>();
            while (resultSet.next()) {
                Object[] row = new Object[resultSet.getMetaData().getColumnCount()];
                for (int i = 0; i < row.length; i++) {
                    row[i] = resultSet.getObject(i + 1);
                }
                results.add(row);


            }
            for (int i = 0; i < results.size(); i++) {
                Object[] row = results.get(i);
                String requestID = row[0].toString();
                String itemID = row[1].toString();
                String name = row[2].toString();
                int quantity = (int) row[3];
                StockItem stockItem = new StockItem(name, itemID, 0, 0, false, null, 0, 0, "DataBaseModel");
                ProductRequest productRequest = new ProductRequest(stockItem, quantity);
                productRequestList.addRequestToList(productRequest);
                deliveryList.addRequestToDelivery(productRequest, requestID);
            }

            changeSupport.firePropertyChange("DeliveriesQuery", clientNo, deliveryList);
            resultSet.close();
            deliveriesQuery.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean addMessage(Message message, int clientNo) {
        try {
            addMessage.setString(1, message.getMessage());
            addMessage.setTimestamp(2, message.getTimestamp());
            addMessage.setString(3, message.getDepartmentID());
            addMessage.executeUpdate();
            changeSupport.firePropertyChange("NewMessage", clientNo, message);
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }


    }

    public void messageQuery(int clientNo) {
        ArrayList<Object[]> results = new ArrayList<>();
        MessageList messages = new MessageList();
        try {
            String sql =
                    "select * from \"Sep2\".chatlog;";
            PreparedStatement messageQuery = connection.prepareStatement(sql);
            ResultSet resultSet = messageQuery.executeQuery();
            while (resultSet.next()) {
                Object[] row = new Object[resultSet.getMetaData().getColumnCount()];
                for (int i = 0; i < row.length; i++) {
                    row[i] = resultSet.getObject(i + 1);
                }
                results.add(row);


            }
            for (int i = 0; i < results.size(); i++) {
                Object[] row = results.get(i);
                String message = row[0].toString();
                Timestamp timestamp = (Timestamp) row[1];
                String senderID = row[2].toString();
                Message message1 = new Message(message, timestamp, senderID);
                messages.addMessage(message1);
            }


            changeSupport.firePropertyChange("MessageQuery", clientNo, messages);
            resultSet.close();
            messageQuery.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createChatLogTable() {
        String sql = "CREATE TABLE IF NOT EXISTS\"Sep2\".chatlog (" +
                "   Message varchar(99999)," +
                "Time TimeStamp NOT NULL PRIMARY KEY," +
                " SenderID varchar(30) NOT NULL);";
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
    }

    public void createDummyData() {
        try {
            String sql = "insert into \"Sep2\".stockitem values " +
                    "('0000000', 'Banana', 5000, 4, '2019-10-01', 'HQ')," +
                    "('0000000', 'Banana', 1000, 4, '2019-10-01', 'WH')," +
                    "('0000000', 'Banana', 150, 4, '2019-10-01', 'RT')," +
                    "('0000001', 'Cheese', 5000, 3, '2019-11-01', 'HQ')," +
                    "('0000001', 'Cheese', 1000, 3, '2019-11-01', 'WH')," +
                    "('0000001', 'Cheese', 150, 3, '2019-11-01', 'RT')," +
                    "('0000002', 'Wine', 5000, 10, '2020-01-01', 'HQ')," +
                    "('0000002', 'Wine', 1000, 10, '2020-01-01', 'WH')," +
                    "('0000002', 'Wine', 150, 10, '2020-01-01', 'RT')," +
                    "('0000003', 'Sausages', 5000, 12, '2022-01-01', 'HQ')," +
                    "('0000003', 'Sausages', 1000, 12, '2022-01-01', 'WH')," +
                    "('0000003', 'Sausages', 150, 12, '2022-01-01', 'RT');";

            Statement statement = connection.createStatement();
            statement.execute(sql);

            sql = "INSERT INTO \"Sep2\".employee values ('0000000','HQ','Jaume','Lopez'),\n" +
                    " ('0000001','HQ','Keneth','Jensen'),\n" +
                    "  ('0000002','HQ','Florin','Bordei'),\n" +
                    "   ('0000003','HQ','Dave','Le'),\n" +
                    "   ('0000004','WH','KenethWH','Jensen'),\n" +
                    "  ('0000005','WH','FlorinWH','Bordei'),\n" +
                    "   ('0000006','WH','DaveWH','Le'),\n" +
                    "    ('0000007','WH','JaumeWH','Lopez'),\n" +
                    "   ('0000008','RT','KenethRT','Jensen'),\n" +
                    "  ('0000009','RT','FlorinRT','Bordei'),\n" +
                    "   ('00000010','RT','DaveRT','Le'),\n" +
                    "   ('0000011','RT','JaumeRT','Lopez');";
             statement = connection.createStatement();
            statement.execute(sql);
            sql = "insert into \"Sep2\".sales values ('0000000',100),\n" +
                    "('0000001',25),\n" +
                    "('0000002',35),\n" +
                    "('0000003',45);";
            statement = connection.createStatement();
            statement.execute(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

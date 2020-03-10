package jdbc;

public class Setup {

    public static void main(String[] args) {

        DataBaseModel dataBaseModel = new DataBaseModel();
        dataBaseModel.createSep2Schema();
        dataBaseModel.createDepartmentTable();
        dataBaseModel.addDepartmentToDataBase("WH", "Warehouse");
        dataBaseModel.addDepartmentToDataBase("HQ", "Headquarters");
        dataBaseModel.addDepartmentToDataBase("RT", "Retailer");
        dataBaseModel.createStockItemTable();
        dataBaseModel.createEmployeeTable();
        dataBaseModel.createRequestTable();
        dataBaseModel.createItemRequestTable();
        dataBaseModel.createSalesTable();
        dataBaseModel.createChatLogTable();
        dataBaseModel.createDummyData();



    }
}
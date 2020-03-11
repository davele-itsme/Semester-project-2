package jdbc;

import java.sql.Date;

public class DataBase {

    public static void main(String[] args) {

        DataBaseModel dataBaseModel = new DataBaseModel();
        Date date = new Date(1,1,1);
//        StockItem stockItem = new StockItem("Bonano","eeee",3,3,true,date,3,10);
//        System.out.println(dataBaseModel.addItemToDataBase(stockItem,"WH"));
//        dataBaseModel.departmentQuery(1);
//        dataBaseModel.addRequestToDataBase("WH",1);
//        ProductRequestList productRequestList = new ProductRequestList();
//        StockItem stockItem1= new StockItem("banana","rrrr",3,3,true,date,3,10);
//        ProductRequest p1 = new ProductRequest(stockItem,5);
//        ProductRequest p2 = new ProductRequest(stockItem1,10);
//        productRequestList.addRequestToList(p1);
//        productRequestList.addRequestToList(p2);
//        System.out.println(dataBaseModel.setRequestStatus(productRequestList,1));
//        System.out.println(productRequestList.Size());
//        System.out.println(dataBaseModel.deleteItemByIdAndDepartment("1234","WH",1));
        dataBaseModel.deliveriesQuery("RT",1);
    }
}
package model;

public class Request {

    StockItem requestItem;
    int quantity, currentQty;

    public Request(StockItem requestItem, int quantity, int currentQty){
        this.requestItem = requestItem;
        this.quantity = quantity;
        this.currentQty = currentQty;
    }

    public StockItem getRequestItem() {
        return requestItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getCurrentQty() {
        return currentQty;
    }
}

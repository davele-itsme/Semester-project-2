package model;

import java.util.Date;

public class Delivery {
    private String requestid,requestedFrom,status;
    private java.util.Date date;
    private int totalItems;
    private ProductRequestList productList;

    public Delivery(String requestid, String requestedFrom, String status, java.util.Date date) {
        this.requestid = requestid;
        this.requestedFrom = requestedFrom;
        this.status = status;
        this.date = date; //TODO: Dave: I created date, should save this to database
        productList = new ProductRequestList();
        calculateTotalItems();
    }


    public String getRequestid() {
        return requestid;
    }

    public void setRequestid(String requestid) {
        this.requestid = requestid;
    }

    public String getRequestedFrom() {
        return requestedFrom;
    }

    public void setRequestedFrom(String requestedFrom) {
        this.requestedFrom = requestedFrom;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ProductRequestList getProductList() {
        return productList;
    }

    public Date getDate()
    {
        return date;
    }

    public int getTotalItems()
    {
        return totalItems;
    }

    public void setProductList(ProductRequestList productList) {
        this.productList = productList;
        calculateTotalItems();
    }
    public void addToDelivery(ProductRequest productRequest)
    {
        productList.addRequestToList(productRequest);
        calculateTotalItems();
    }

    private void calculateTotalItems()
    {
        totalItems = 0;
        for(int i = 0; i < productList.size(); i++)
        {
            totalItems += productList.getProductRequest(i).getQuantity();
        }
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "requestid='" + requestid + '\'' +
                ", requestedFrom='" + requestedFrom + '\'' +
                ", status='" + status + '\'' +
                ", productList=" + productList +
                '}';
    }
}

package model;

import java.util.ArrayList;

public class DeliveryList{

    private ArrayList<Delivery> deliveries;

    public DeliveryList() {
        deliveries = new ArrayList<>();
    }


    public void addRequestToDelivery (ProductRequest productRequest,String requestID)
    {
        for (int i=0;i<deliveries.size();i++)
        {
            if (requestID.equals(deliveries.get(i).getRequestid()))
            {
                deliveries.get(i).addToDelivery(productRequest);
                break;
            }
        }
    }

    @Override
    public String toString() {
        return "DeliveryList{" +
                "deliveries=" + deliveries +
                '}';
    }

    public void addDelivery(Delivery delivery)
    {
        deliveries.add(delivery);
    }

    public int size() {
        return deliveries.size();
    }

    public Delivery get(int i) {
        return deliveries.get(i);
    }
}

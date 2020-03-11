package model;

import java.util.ArrayList;

/**
 * ProductRequestList to store {@link ProductRequest} objects.
 *
 * @author Kenneth Jensen
 * @author Floring Bordei
 * @author Jaime Lopez
 * @author Dave Joe Lê
 */
public class ProductRequestList {
    private ArrayList<ProductRequest> requestList;

    /**
     * Creates a ProductRequestList and instantiates the {@link ArrayList} stored.
     */
    public ProductRequestList() {
        requestList = new ArrayList<>();
    }

    /**
     * Add a {@link ProductRequest} to the List.
     * @param productRequest the {@link ProductRequest} to add.
     */
    public void addRequestToList(ProductRequest productRequest) {
        requestList.add(productRequest);
    }

    /**
     * Removes a {@link ProductRequest} from the List.
     * @param productId the {@link ProductRequest} to remove.
     */
    public void removeRequestFromList(String productId) {
        for (int i = 0; i < requestList.size(); i++) {
            if (requestList.get(i).getProductId().equals(productId)) ;
            {
                requestList.remove(i);
                break;
            }

        }
    }

    /**
     * Gets the size of the List.
     * @return The size of the List.
     */
    public int size()
    {
        return requestList.size();
    }

    /**
     * Gets the {@link ProductRequest} at the requested index from the List.
     * @param i The index to return.
     * @return The {@link ProductRequest} the the requested index.
     */
    public ProductRequest getProductRequest( int i)
    {
        return requestList.get(i);
    }

    @Override
    public String toString() {
        return "ProductRequestList{" +
                "requestList=" + requestList +
                '}';
    }
}

package model;

/**
 * <h1>Product Request Class for storing information about requests</h1>
 * This stores the {@link StockItem} requested, the quantity requested
 * and the product ID.
 *
 * @author Kenneth Jensen
 * @author Floring Bordei
 * @author Jaime Lopez
 * @author Dave Joe Lê
 */

public class ProductRequest {
    private int quantity;
    private String productId;
    private StockItem stockItem;

    /**
     * Creates a ProductRequest with the specified information.
     * @param stockItem The {@link StockItem} requested.
     * @param quantity The quantity requested.
     */
    public ProductRequest(StockItem stockItem,int quantity) {
        this.productId=stockItem.getId();
        this.quantity = quantity;
        this.stockItem=stockItem;

    }

    @Override
    public String toString() {
        return "ProductRequest{" +
                "quantity=" + quantity +
                ", productId='" + productId + '\'' +
                ", stockItem=" + stockItem.getName() +
                '}';
    }

    /**
     * Gets the quantity requested.
     * @return The quantity requested.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Gets the product ID.
     * @return The product ID.
     */
    public String getProductId() {
        return productId;
    }

    /**
     * Gets the {@link StockItem} requested.
     * @return The {@link StockItem} requested.
     */
    public StockItem getStockItem ()
    {
        return stockItem;
    }

    public String getName()
    {
        return stockItem.getName();
    }

    public String getID()
    {
        return stockItem.getId();
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    public int getActualQuantity()
    {
        return stockItem.getQuantity();
    }
}

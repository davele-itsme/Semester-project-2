package model;

import java.util.Date;

/**
 * <h1>StockItem Class to store information about the item</h1>
 * Stores the name, ID, quantity, price, minimum stock, maximum stock,
 * whether it can Expire, and if so the {@link Date} at which it will.
 *
 * @author Kenneth Jensen
 * @author Floring Bordei
 * @author Jaime Lopez
 * @author Dave Joe Lê
 */
public class StockItem {

    private String name, id,location;
    private int quantity, price, minStock, maxStock, totalProfit;
    private boolean canExpire;
    private Date expiryDate;


    /**
     * Creates a StockItem with the specified information.
     *
     * @param name       The name of the Item.
     * @param id         The ID of the Item.
     * @param quantity   The quantity of the Item.
     * @param price      The price of the Item.
     * @param canExpire  Whether the Item can expire.
     * @param expiryDate The expiry date of the Item.
     * @param minStock   The minimum stock of the Item.
     * @param maxStock   The maximum stock of the Item.
     * @param location   The entity location of the item.
     */
    public StockItem(String name, String id, int quantity, int price, boolean canExpire, Date expiryDate, int minStock, int maxStock, String location) {
        this.name = name;
        this.id = id;
        this.quantity = quantity;
        this.price = price;
        this.canExpire = canExpire;
        this.expiryDate = expiryDate;
        this.minStock = minStock;
        this.maxStock = maxStock;
        this.location=location;
        this.totalProfit = (price * quantity) / 2;
    }

    /**
     * Gets the quantity of the Item.
     *
     * @return The quantity of the Item.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the Item.
     *
     * @param quantity The quantity to be stored.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.totalProfit = (price * quantity) / 2;
        //TODO: Update Java doc
    }

    /**
     * Gets the name of the Item.
     *
     * @return The name of the Item.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the Item.
     *
     * @param name The name to be stored.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the price of the Item.
     *
     * @return The price of the Item.
     */
    public int getPrice() {
        return price;
    }

    /**
     * Sets the price of the Item.
     *
     * @param price The price to be stored.
     */
    public void setPrice(int price) {
        this.price = price;
        this.totalProfit = (price * quantity) / 2;
        //TODO: Update Java doc
    }

    /**
     * Gets the ID of the Item.
     *
     * @return The Id of the Item.
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the minimum stock of the Item.
     *
     * @return The minimum stock of the Item.
     */
    public int getMinStock() {
        return minStock;
    }

    /**
     * Gets the maximum stock of the Item.
     *
     * @return The maximum stock of the Item.
     */
    public int getMaxStock() {
        return maxStock;
    }

    /**
     * Gets whether the Item can expire or not.
     *
     * @return Whether the Item can expire or not.
     */
    public boolean isCanExpire() {
        return canExpire;
    }

    /**
     * Gets the expiry date of the Item.
     *
     * @return The expiry date of the Item.
     */
    public Date getExpiryDate() {
        return expiryDate;
    }


    /**
     * Gets the location of the Item.
     *
     * @return The location of the Item.
     */
    public String getLocation()
    {
        return location;
    }

    /**
     * Gets the total profit of the Item.
     *
     * @return The total profit of the Item.
     */
    public int getTotalProfit() {
        return totalProfit;
    }

    public StockItem copy()
    {
        StockItem stockItem = new StockItem(name, id, quantity, price, canExpire, expiryDate, minStock, maxStock, location);
        return stockItem;
    }
}

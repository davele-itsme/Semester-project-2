package model;

import java.util.ArrayList;

/**
 * <h1>StockItemList to store a List of StockItems</h1>
 * This stores {@link StockItem}s, and contains methods to interact with them like
 * {@link StockItemList#totalPrice()} and {@link StockItemList#totalQuantity()}.
 *
 * @author Kenneth Jensen
 * @author Floring Bordei
 * @author Jaime Lopez
 * @author Dave Joe Lê
 */
public class StockItemList {

    private ArrayList<StockItem> stockItemList;

    /**
     * Creates the StockItemList and instantiates the {@link ArrayList} stored.
     */
    public StockItemList() {
        this.stockItemList = new ArrayList<>();
    }

    /**
     * Adds a {@link StockItem} to the List.
     * @param item The {@link StockItem} to be added.
     */
    public void add(StockItem item){
        stockItemList.add(item);
    }

    /**
     * Removes a {@link StockItem} at the specified index from the List.
     * @param index The index of the {@link StockItem} to be removed.
     */
    public void remove(int index){
        stockItemList.remove(index);
    }

    /**
     * Removes a {@link StockItem} from the List.
     * @param item The {@link StockItem} to be removed.
     */
    public void remove(StockItem item){
        stockItemList.remove(item);
    }

    /**
     * Gets the total price of {@link StockItem}s stored in the List.
     * @return The total price of {@link StockItem}s stored in the List.
     */
    public int totalPrice(){
        int count = 0;

        for(StockItem item : stockItemList){
            count += item.getPrice();
        }

        return count;
    }

    /**
     * Gets the total quantity of {@link StockItem}s stored in the List.
     * @return The total quantity of {@link StockItem}s stored in the List.
     */
    public int totalQuantity(){
        int count = 0;

        for(StockItem item : stockItemList){
            count += item.getQuantity();
        }

        return count;
    }

    /**
     * Gets a {@link StockItem} at the specified index from the List.
     * @param index The index to retrieve.
     * @return The {@link StockItem} retrieved at the index.
     */
    public StockItem get(int index){
        return stockItemList.get(index);
    }

    /**
     * Gets the size of the List.
     * @return The size of the List.
     */
    public int size(){
        return stockItemList.size();
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof StockItemList)){
            return false;
        }
        StockItemList other = (StockItemList) obj;
        if(size() != other.size()){
            return false;
        }
        for(int i = 0; i < size(); i++){
            if(!(get(i).equals(other.get(i)))){
                return false;
            }
        }
        return true;
    }
}

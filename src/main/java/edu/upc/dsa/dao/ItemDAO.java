package edu.upc.dsa.dao;

import edu.upc.dsa.models.Item;

import java.util.List;

public interface ItemDAO {
    public List<Item> getItems();
    public Item addItem(Item i);
    public void deleteItem(String ItemId);
    public Item getItemByName(String ItemName);

}

package edu.upc.dsa.dao;

import edu.upc.dsa.models.Inventory;
import edu.upc.dsa.models.Item;
import java.beans.IntrospectionException;

import java.util.List;

public interface InventoryDAO {
    public List<Inventory> getInventory();

    public List<Item> getUserInventory(String UserId);

    public Inventory addInventory(String UserId, String ItemId);

    public Inventory PurchaseItem(String userName, String itemId) throws IntrospectionException;

    public boolean Repeated(String UserName, String ItemName);

}

package edu.upc.dsa.models;

public class Inventory {
    private String id;
    private String UserId;
    private String ItemId;

    public Inventory(){} // Constructor vacío

    public Inventory(String UserId, String ItemId){
        this.UserId = UserId;
        this.ItemId = ItemId;
    }
    // Getters


    public String getId() {
        return id;
    }

    public String getUserId() {
        return UserId;
    }

    public String getItemId() {
        return ItemId;
    }

    // Setters


    public void setId(String id) {
        this.id = id;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public void setItemId(String itemId) {
        ItemId = itemId;
    }
}


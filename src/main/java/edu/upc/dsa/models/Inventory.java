package edu.upc.dsa.models;

import edu.upc.dsa.util.RandomUtils;

public class Inventory {
    private String id;
    private String idUser;
    private String idItem;

    public Inventory(){} // Constructor vacío

    public Inventory(String UserId, String ItemId){
        this.id = RandomUtils.getId();
        this.idUser = UserId;
        this.idItem = ItemId;
    }
    // Getters


    public String getId() {
        return id;
    }

    public String getIdUser() {
        return idUser;
    }

    public String getIdItem() {
        return idItem;
    }

    // Setters


    public void setId(String id) {
        this.id = id;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public void setIdItem(String idItem) {
        this.idItem = idItem;
    }
}


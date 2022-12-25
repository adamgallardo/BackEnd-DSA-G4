package edu.upc.dsa.models;

public class Items {
    private String id;
    private String description;
    private int price;

    public Items(){}
    public Items(String id, String description, int price){
        this.description = description;
        this.id = id;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public void setId(String id) {

        this.id = id;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
package edu.upc.dsa.models;

public class Item {
    private String id;
    private String name;
    private String description;
    private int damage;
    private int heal;
    private int price;

    private String image;

    public Item(){} //constructor vacío

    public Item(String id, String name, String description, int price, int damage, int heal, String image){
        this.description = description;
        this.name = name;
        this.damage = damage;
        this.heal = heal;
        this.id = id;
        this.price = price;
        this.image = image;
    }

    //Getters
    public String getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getName(){return name;}

    public String getImage() {return image;}

    public int getDamage(){return damage;}

    public int getHeal() {return heal;}

    //Setters

    public void setId(String id) {this.id = id;}

    public void setDamage(int damage) {this.damage = damage;}

    public void setHeal(int heal) {this.heal = heal;}

    public void setName(String name) {this.name = name;}

    public void setImage(String image) {this.image = image;}

    public void setPrice(int price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString(){
        return "Item{"+
                "id: " + id + '\'' +
                "name:" + name + '\'' +
                "description:" + description + '\'' +
                "price:" + price + '\'' +
                "damage"+ damage + '\'' +
                "heal" + heal + '\'' +
                "image" + image + "}";
    }
}
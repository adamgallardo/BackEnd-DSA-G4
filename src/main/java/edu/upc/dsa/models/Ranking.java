package edu.upc.dsa.models;

public class Ranking {
    String username;
    int maxPoints;
    //Constructor vacío
    public Ranking() {
    }
    //constructor para inicializar las variables.
    public Ranking(String UserName, int points) {
        this.username = UserName;
        this.maxPoints = points;
    }

    //Getters
    public String getUsername() {
        return username;
    }

    public int getMaxPoints() {
        return maxPoints;
    }

    //Setters

    public void setUsername(String userName) {
        this.username = userName;
    }

    public void setMaxPoints(int points) {
        this.maxPoints = points;
    }

}

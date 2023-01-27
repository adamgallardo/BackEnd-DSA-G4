package edu.upc.dsa.models;

public class Match {
    String UserName;
    int points;
    //Constructor vacío
    public Match() {
    }
    //constructor para inicializar las variables.
    public Match(String UserName, int points) {
        this.UserName = UserName;
        this.points = points;
    }

    //Getters
    public String getUserName() {
        return UserName;
    }

    public int getPoints() {
        return points;
    }

    //Setters

    public void setUserName(String userName) {
        this.UserName = userName;
    }

    public void setPoints(int points) {
        this.points = points;
    }

}

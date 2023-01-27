package edu.upc.dsa.models;

public class Issue {
    private String date;
    private String informer;
    private String message;


    public Issue(){}

    public Issue(String date, String informer, String message){

        this.informer = informer;
        this.date = date;
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getInformer() {
        return informer;
    }

    public void setInformer(String informer) {
        this.informer = informer;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
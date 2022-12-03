package edu.upc.dsa.models;

public class LogIn {
    String username;
    String password;

    public LogIn(String nombre, String contra) {
        this.setUsername(nombre);
        this.setPassword(contra);
    }
    public LogIn(){}
    public String getUsername(){
        return this.username;
    }
    public String getPassword(){
        return this.password;
    }
    public void setUsername(String nombre) {
        this.username = nombre;
    }
    public void setPassword(String contraseña) {
        this.password = contraseña;
    }
}

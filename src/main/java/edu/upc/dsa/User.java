package edu.upc.dsa;

import edu.upc.dsa.util.RandomUtils;

public class User {
    String nombre;
    String apellidos;
    String email;
    String password;
    String username;
    String id;

    //Constructor para inicializar las variables
    public User(String nombre, String apellidos, String email, String username, String password){
        this.nombre=nombre;
        this.apellidos=apellidos;
        this.email=email;
        this.username=username;
        this.password=password;
        this.id=RandomUtils.getId();
    }

    //Constructor vacío para la API REST
    public User(){
    }

    //Setters & getters
    public String getNombre(){return this.nombre;}
    public void setNombre(String nombre){this.nombre=nombre;}
    public String getApellidos(){return this.apellidos;}
    public void setApellidos(String apellidos){this.apellidos=apellidos;}
    public String getEmail(){return this.email;}
    public void setEmail(String email){this.email=email;}
    public String getUsername(){return this.username;}
    public void setUsername(String username){this.username=username;}
    public String getPassword(){return this.password;}
    public void setPassword(String password){this.password=password;}
    public String getId(){return this.id;}
    public void setId(String id){this.id=id;}

}

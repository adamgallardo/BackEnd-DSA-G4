package edu.upc.dsa;

import edu.upc.dsa.util.RandomUtils;

public class User {
    private String name;
    private String surname;
    private String email;
    private String password;
    private String username;
    private String id;
    private Integer coins;

    //Constructor para inicializar las variables
    public User(String name, String surname, String email, String username, String password){
        this.name=name;
        this.surname=surname;
        this.email=email;
        this.username=username;
        this.password=password;
        this.id=RandomUtils.getId();
        this.coins=0;
    }

    //Constructor vacío para la API REST
    public User(){
    }

    //Setters & getters
    public String getName(){return this.name;}
    public void setName(String name){this.name=name;}
    public String getSurname(){return this.surname;}
    public void setSurname(String surname){this.surname=surname;}
    public String getEmail(){return this.email;}
    public void setEmail(String email){this.email=email;}
    public String getUsername(){return this.username;}
    public void setUsername(String username){this.username=username;}
    public String getPassword(){return this.password;}
    public void setPassword(String password){this.password=password;}
    public String getId(){return this.id;}
    public void setId(String id){this.id=id;}

    public Integer getCoins() {
        return this.coins;
    }

    public void setCoins(Integer coins) {
        this.coins = coins;
    }
}

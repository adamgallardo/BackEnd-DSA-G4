package edu.upc.dsa;

import edu.upc.dsa.util.RandomUtils;

public class User {

    private String id;
    private String username;
    private String password;
    private String email;
    private Integer coins;

    //Constructor para inicializar las variables
    public User( String username, String password, String email){
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

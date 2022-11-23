package edu.upc.dsa;

public class SignUp {
    private String username;
    private String password;
    private String email;
    private String name;
    private String surname;

    public SignUp(String username, String password){}

    public SignUp(String name, String surname, String email, String username, String password){
        this.name=name;
        this.surname=surname;
        this.email=email;
        this.username=username;
        this.password=password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}

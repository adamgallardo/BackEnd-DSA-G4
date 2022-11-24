package edu.upc.dsa;

public class SignUp {
    private String username;
    private String password;
    private String email;


    public SignUp(String username, String password){}

    public SignUp(String username, String password, String email){

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


}

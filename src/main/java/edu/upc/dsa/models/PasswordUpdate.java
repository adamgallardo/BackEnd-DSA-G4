package edu.upc.dsa.models;

public class PasswordUpdate {

    String id;
    String oldPassword;
    String newPassword;

    public PasswordUpdate(){}

    public PasswordUpdate(String id, String oldPassword, String newPassword){
        this.id = id;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOldPassword(){return oldPassword;}

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }
    public String getNewPassword(){return newPassword;}

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}

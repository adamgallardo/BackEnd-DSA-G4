package edu.upc.dsa.dao;

import edu.upc.dsa.models.User;

public interface IUserDAO {
    User addUser(String username, String password, String email);
    User getUserByName(String name);
    User getUserByEmail(String email);
    User getUserById(String id);
    void deleteUser(String id);
    User updateUsername(String oldUsername, String newUsername);
    User updateCoins(String username, Integer coins);
    User updatePassword(String id, String newPassword);
    User updateImage(String id, Integer newImage);
}

package edu.upc.dsa;

public interface GameManager {
    User addUser( String username, String password, String email);
    User addUser(User u);
    int size();
    User getUserByName(String name);
    User getUserByEmail(String email);
}

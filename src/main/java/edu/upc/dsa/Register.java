package edu.upc.dsa;

public interface Register {
    String addUser(String nombre, String apellidos, String email, String username, String password);
    void clearResources();
}

package edu.upc.dsa.dao.implementations;

import edu.upc.dsa.dao.IUserDAO;

import edu.upc.dsa.models.User;
import org.apache.log4j.Logger;

import java.util.LinkedList;
import java.util.List;

public class UserDAOImpl implements IUserDAO {

    private static UserDAOImpl instance;
    final static Logger logger = Logger.getLogger(UserDAOImpl.class);
    private List<User> users;
    private UserDAOImpl(){
        this.users=new LinkedList<>();//Users list
    }
    public static IUserDAO getInstance(){
        if(instance == null){
            instance = new UserDAOImpl();
        }
        return instance;
    }
    @Override
    public User addUser(String username, String password, String email){
        User u = new User(username,password,email);
        this.users.add(u);
        logger.info("Add new user: "+u.toString());
        return u;
    }
    @Override
    public User getUserByName(String username){
        for (User u: this.users){
            if(u.getUsername().equals(username)){return u;}
        }
        return null;
    }
    @Override
    public User getUserByEmail(String email) {
        for (User u: this.users){
            if(u.getEmail().equals(email)){return u;}
        }
        return null;
    }
    @Override
    public int size() {
        return this.users.size();
    }
}

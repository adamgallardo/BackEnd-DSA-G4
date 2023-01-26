package edu.upc.dsa.dao.implementations;

import edu.upc.dsa.dao.IUserDAO;

import edu.upc.dsa.models.User;
import org.apache.log4j.Logger;

import java.util.LinkedList;
import java.util.List;

public class UserDAOImpl implements IUserDAO {

    private static UserDAOImpl instance;
    final static Logger logger = Logger.getLogger(UserDAOImpl.class);
    private SessionImpl session;

    private UserDAOImpl(){
        this.session = SessionImpl.getInstance();
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
        session.save(u);
        logger.info("Add new user: " + u);
        return u;
    }
    @Override
    public User getUserByName(String username){
        User u = (User) this.session.getByName(User.class, username);
        if (u.getUsername() == null){
            return null;
        }
        logger.info("Get user by name: " + username);
        return u;
    }
    @Override
    public User getUserByEmail(String email) {
        List<User> users = new LinkedList<>();
        session.findAll(User.class).forEach(user -> users.add((User) user));
        for (User u: users){
            if(u.getEmail().equals(email)){return u;}
        }
        return null;
    }

    @Override
    public User getUserById(String id) {
        User u = (User) this.session.getById(User.class, id);
        if (u.getUsername() == null){
            return null;
        }
        logger.info("Get user by id");
        return u;
    }

    @Override
    public void deleteUser(String id) {
        User user = (User) this.session.getById(User.class, id);
        logger.info("Deleting the following user: " + user);
        session.delete(user);
    }

    @Override
    public User updateUsername(String oldUsername, String newUsername) {
        User user = (User) this.session.getByName(User.class, oldUsername);
        logger.info("Changing the following username: "+oldUsername+" for: "+newUsername);
        user.setUsername(newUsername);
        this.session.update(user);
        return user;
    }

    @Override
    public User updateCoins(String username, Integer coins) {
        User user = (User) this.session.getByName(User.class, username);
        logger.info("Updating coins");
        user.setCoins(coins);
        this.session.update(user);
        return user;
    }

    @Override
    public User updatePassword(String id, String newPassword) {
        User user = (User) this.session.getById(User.class, id);
        logger.info("Changing password");
        user.setPassword(newPassword);
        this.session.update(user);
        return user;
    }

    @Override
    public User updateImage(String id, Integer newImage) {
        User user = (User) this.session.getById(User.class, id);
        logger.info("Changing profile image");
        user.setImage(newImage);
        this.session.update(user);
        return user;
    }
}

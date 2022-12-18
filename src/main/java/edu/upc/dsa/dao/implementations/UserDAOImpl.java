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
        User u = (User) this.session.getByUsername(User.class, username);
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
        session.deleteUser(user);
    }
}

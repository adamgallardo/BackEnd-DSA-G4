package edu.upc.dsa;

import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class GameManagerImpl implements GameManager {

    private static GameManager instance;
    final static Logger logger = Logger.getLogger(GameManagerImpl.class);
    private HashMap<String,String> userMap;
    private List<User> users;

    private GameManagerImpl(){
        this.userMap=new HashMap<String,String>(); //Key = idUser, Value = email
        this.users=new LinkedList<>();// Users list
    }

    //Singleton
    public static GameManager getInstance(){
        if(instance==null){
            instance=new GameManagerImpl();
        }
        return instance;
    }

    @Override
    public User addUser(String username, String password, String email) {

        return this.addUser(new User(username,password,email));
    }

    @Override
    public User addUser(User u) {
        this.userMap.put(u.getId(),u.getEmail());
        this.users.add(u);
        return u;
    }

    @Override
    public int size() {
        return this.users.size();
    }

    @Override
    public User getUserByName(String username) {
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
}

package edu.upc.dsa;

import org.apache.log4j.Logger;

import java.util.HashMap;

public class GameManagerImpl implements GameManager {

    private static GameManager instance;
    final static Logger logger = Logger.getLogger(GameManagerImpl.class);
    private HashMap<String,String> userMap;

    private GameManagerImpl(){
        this.userMap=new HashMap<String,String>(); //Key = idUser, Value = email
    }

    //Singleton
    public static GameManager getInstance(){
        if(instance==null){
            instance=new GameManagerImpl();
        }
        return instance;
    }

    @Override
    public String addUser(String name, String surname, String email, String username, String password) {
        //Primero comprobamos si ya hay alguna cuenta vinculada al correo electrónico
        boolean encontrado = userMap.containsValue(email);
        if(!encontrado){
            logger.info("No exite ningun usuario vinculado a este correo: "+email+". Se puede hacer el registro.");
            SignUp su =new SignUp(name,surname,email,username,password);
            User user =new User(name, surname, email,username,password);
            userMap.put(user.getId(), email);
            return "OK";

        }
        else return "No";
    }
}

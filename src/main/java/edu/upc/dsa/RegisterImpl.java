package edu.upc.dsa;

import org.apache.log4j.Logger;

import java.util.HashMap;

public class RegisterImpl implements Register{

    private static Register instance;
    final static Logger logger = Logger.getLogger(RegisterImpl.class);
    private HashMap<String,String> userMap;

    private RegisterImpl(){
        this.userMap=new HashMap<String,String>(); //Key = idUser, Value = email
    }

    //Singleton
    public static Register getInstance(){
        if(instance==null){
            instance=new RegisterImpl();
        }
        return instance;
    }

    @Override
    public String addUser(String nombre, String apellidos, String email, String username, String password) {
        //Primero comprobamos si ya hay alguna cuenta vinculada al correo electrónico
        boolean encontrado = userMap.containsValue(email);
        if(!encontrado){

        }
        return null;
    }
}

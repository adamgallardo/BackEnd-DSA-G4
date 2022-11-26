package edu.upc.dsa.services;

import edu.upc.dsa.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api(value = "/user", description = "Endpoint to User Service")
@Path("/user")
public class UserService {
    private GameManager manager;

    public UserService(){
        this.manager = GameManagerImpl.getInstance();
        if(manager.size()==0){
            this.manager.addUser("Legyonaryus","1234","agallardo@dsa.com");
        }
    }

    // Sign Up
    @POST
    @ApiOperation(value = "User sign up", notes = "username + password + email")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class),
            @ApiResponse(code = 405, message = "Username already in use"),
            @ApiResponse(code = 406, message = "email already in use"),
            @ApiResponse(code = 500, message = "Validation Error")
    })
    @Path("/signUp")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response userSignUp(SignUp userCred) {

        User user = new User(userCred.getUsername(), userCred.getPassword(), userCred.getEmail());
        if (user.getEmail().isEmpty() || userCred.getUsername().isEmpty() || user.getPassword().isEmpty())
            return Response.status(500).build();

        User namecheck = this.manager.getUserByName(userCred.getUsername());
        User emailcheck = this.manager.getUserByEmail(userCred.getEmail());
        if (namecheck != null )
            return Response.status(405).build();
        else if (emailcheck != null )
            return Response.status(406).build();
        else {
            this.manager.addUser(user.getUsername(), user.getPassword(), user.getEmail());
            return Response.status(201).entity(user).build();
        }
    }


    @POST
    @ApiOperation(value = "LogIn User", notes = "Name and Password")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = User.class),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 405, message = "Wrong password"),
            @ApiResponse(code = 500, message = "Invalid credentials")
    })
    @Path("/logIn")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response logInUser(LogIn logInCred) {
        User user = this.manager.getUserByName(logInCred.getUsername());
        if ((logInCred.getUsername().isEmpty()) || (logInCred.getPassword().isEmpty()))
            return Response.status(500).build();
        else if (user == null)
            return Response.status(404).build();
        else {
            if (user.getPassword().equals(logInCred.getPassword()))
                return Response.status(200).entity(user).build();
            else
                return Response.status(405).build();
        }
    }
}
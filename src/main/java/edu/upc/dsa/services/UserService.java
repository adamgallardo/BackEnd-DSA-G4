package edu.upc.dsa.services;

import edu.upc.dsa.dao.IUserDAO;
import edu.upc.dsa.dao.implementations.UserDAOImpl;
import edu.upc.dsa.models.LogIn;
import edu.upc.dsa.models.PasswordUpdate;
import edu.upc.dsa.models.SignUp;
import edu.upc.dsa.models.User;
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
    private IUserDAO manager;

    public UserService(){
        this.manager = UserDAOImpl.getInstance();
    }

    // Sign Up
    @POST
    @ApiOperation(value = "User sign up", notes = "username + password + email")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class),
            @ApiResponse(code = 405, message = "Username already in use"),
            @ApiResponse(code = 406, message = "Email already in use"),
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

    //Log In
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

    // Get one user in particular
    @GET
    @ApiOperation(value = "Get a particuler user", notes = "username")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("username") String username) {

        User user = this.manager.getUserByName(username);
        if (user == null) {
            return Response.status(404).build();
        } else {
            GenericEntity<User> entity = new GenericEntity<User>(user) {};
            return Response.status(201).entity(entity).build();
        }
    }

    //Delete a user
    @DELETE
    @ApiOperation(value = "Delete a user", notes = "Id")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/delete/{id}")
    public Response deleteUser(@PathParam("id") String id){

        User user = manager.getUserById(id);
        if (user == null) {
            return Response.status(404).build();
        }
        else {
            manager.deleteUser(id);
            return Response.status(201).entity(user).build();
        }
    }

    //Change username
    @PUT
    @ApiOperation(value = "Change the username", notes = "old username + new username")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 405, message = "Username already in use"),
            @ApiResponse(code = 500, message = "Invalid credentials")
    })
    @Path("/updateUser/{oldUsername}/{newUsername}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUsername(@PathParam("oldUsername") String oldUsername, @PathParam("newUsername") String newUsername){
        User user = manager.getUserByName(oldUsername);
        if( oldUsername.isEmpty() || newUsername.isEmpty()){
            return Response.status(500).build();
        }
        else if (user == null) {
            return Response.status(404).build();
        }
        User namecheck = this.manager.getUserByName(newUsername);
        if (namecheck != null){
            return Response.status(405).build();
        }
        else {
            manager.updateUsername(oldUsername,newUsername);
            return Response.status(201).entity(user).build();
        }
    }

    //Change password
    @PUT
    @ApiOperation(value = "Change the password", notes = "id + new password")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 407, message = "Wrong password"),
            @ApiResponse(code = 500, message = "Invalid credentials")
    })
    @Path("/updatePassword")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePassword(PasswordUpdate passwordUpdateCred){
        User user = manager.getUserById(passwordUpdateCred.getId());
        if( passwordUpdateCred.getOldPassword().isEmpty() || passwordUpdateCred.getNewPassword().isEmpty()){
            return Response.status(500).build();
        }
        else if (user == null) {
            return Response.status(404).build();
        }
        else if (user.getPassword().equals(passwordUpdateCred.getOldPassword())){
            manager.updatePassword(passwordUpdateCred.getId(), passwordUpdateCred.getNewPassword());
            return Response.status(201).entity(user).build();
        }
        else {
            return Response.status(407).build();
        }
    }

    //Change profile image
    @PUT
    @ApiOperation(value = "Change the password", notes = "id + new password")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 500, message = "Invalid credentials")
    })
    @Path("/updateImage/{id}/{newImage}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateImage(@PathParam("id") String id, @PathParam("newImage") Integer newImage){
        User user = manager.getUserById(id);
        if(id.isEmpty()|| newImage == null){
            return Response.status(500).build();
        }
        else if (user == null) {
            return Response.status(404).build();
        }
        else {
            manager.updateImage(id, newImage);
            return Response.status(201).entity(user).build();
        }
    }
}


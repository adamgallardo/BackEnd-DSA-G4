package edu.upc.dsa.services;
import edu.upc.dsa.*;
import edu.upc.dsa.dao.IUserDAO;
import edu.upc.dsa.dao.InventoryDAO;
import edu.upc.dsa.dao.ItemDAO;
import edu.upc.dsa.dao.implementations.InventoryDAOImpl;
import edu.upc.dsa.dao.implementations.ItemDAOImpl;
import edu.upc.dsa.dao.implementations.UserDAOImpl;
import edu.upc.dsa.models.Item;
import edu.upc.dsa.models.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.beans.IntrospectionException;
import java.util.List;


@Api(value = "/item", description = "Endpoint to Item Service")
@Path("/item")

public class StoreService {

        private ItemDAO itemManager;
        private IUserDAO userManager;
        private InventoryDAO inventoryManager;

        public StoreService() {
            this.itemManager = ItemDAOImpl.getInstance();
            this.userManager = UserDAOImpl.getInstance();
            this.inventoryManager = InventoryDAOImpl.getInstance();
        }

        @Path("basic")
        @GET
        @Produces(MediaType.TEXT_PLAIN)
        public String getIt() {
            return "Got it!";
        }

        // get de todos los items de la tienda.
        @GET
        @ApiOperation(value = "Get all items in store", notes = " ")
        @ApiResponses(value = {
                @ApiResponse(code = 200, message = "Successful", response = Item.class, responseContainer="List"),
        })
        @Path("/itemsList")
        @Produces(MediaType.APPLICATION_JSON)
        public Response getStoreItems() {

            List<Item> storeList = this.itemManager.getItems();
            GenericEntity<List<Item>> entity = new GenericEntity<List<Item>>(storeList) {};
            return Response.status(200).entity(entity).build();
        } //añadir respuesta en caso de que vaya mal.

        //get de todos los items del inventario de un usuario
        @GET
        @ApiOperation(value = "Get a particular User's inventory", notes = " ")
        @ApiResponses(value = {
                @ApiResponse(code = 200, message = "Successful", response = Item.class),
                @ApiResponse(code = 404, message = "User not found")
        })
        @Path("inventory/{UserName}")
        @Produces(MediaType.APPLICATION_JSON)
        public Response getInventoryList(@PathParam("UserName") String UserName) {

            User user = userManager.getUserByName(UserName);
            if (user == null) {
                return Response.status(404).build();
            } else {
                List<Item> inventory = this.inventoryManager.getUserInventory(user.getId());
                GenericEntity<List<Item>> entity = new GenericEntity<List<Item>>(inventory) {};
                return Response.status(200).entity(entity).build();
            }
        }

        // compra un item de la tienda.
        @PUT
        @ApiOperation(value = "Purchase an Item", notes = " ")
        @ApiResponses(value = {
                @ApiResponse(code = 200, message = "Successful"),
                @ApiResponse(code = 402, message = "Not enough coins"),
                @ApiResponse(code = 404, message = "User not found"),
                @ApiResponse(code = 405, message = "Item not found"),
                @ApiResponse(code = 409, message = "Item is already in possession")
        })
        @Path("/PurchaseItem/{ItemName}/{UserName}")
        @Produces(MediaType.APPLICATION_JSON)
        public Response PurchaseItem(@PathParam("ItemName") String ItemName, @PathParam("UserName") String UserName) throws IntrospectionException, IntrospectionException {

            User user = userManager.getUserByName(UserName);
            Item item = itemManager.getItemByName(ItemName);
            String UserId = user.getId();
            String ItemId = item.getId();

            if (user == null) {
                return Response.status(404).build();
            } else if (item == null) {
                return Response.status(405).build();
            } else if (user.getCoins() < item.getPrice()) {
                return Response.status(402).build();
            } else if (inventoryManager.Repeated(UserName, ItemName)){
                return Response.status(409).build();
            } else {
                this.inventoryManager.PurchaseItem(UserId,ItemId);
                return Response.status(200).build();
            }
        }
    }


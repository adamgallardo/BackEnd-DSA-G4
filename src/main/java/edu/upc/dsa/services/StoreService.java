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
import java.util.ArrayList;
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

            List<Item> storeList = this.itemManager.getItems();  // new ArrayList<>(); //this.itemManager.getItems();
           /* storeList.add(new Item( "I1", "1111", "DESC1", 100, 10, 0, "candycanes.png"));
            storeList.add(new Item( "I2", "2222", "DESC2", 200, 20, 5, "gift.png"));
            storeList.add(new Item( "I1", "3331", "DESC3", 500, 10, 0, "coal"));
            storeList.add(new Item( "I1", "4444", "DESC4", 600, 0, 0, "cookie"));
            storeList.add(new Item( "I1", "juan", "DESC1", 100, 10, 0, "candycanes"));
            storeList.add(new Item( "I1", "m1gu3l", "DESC1", 150, 10, 4, "candycanes"));*/
            GenericEntity<List<Item>> entity = new GenericEntity<List<Item>>(storeList) {};
            return Response.status(200).entity(entity).build();
        } //añadir respuesta en caso de que vaya mal.

        //get de todos los items del inventario de un usuario
        @GET
        @ApiOperation(value = "Get a particular User's inventory", notes = " ")
        @ApiResponses(value = {
                @ApiResponse(code = 200, message = "Successful", response = Item.class, responseContainer = "List"),
                @ApiResponse(code = 404, message = "User not found")
        })
        @Path("inventory/{UserId}")
        @Produces(MediaType.APPLICATION_JSON)
        public Response getInventoryList(@PathParam("UserId") String UserId) {

            User user = userManager.getUserById(UserId);
            if (user == null) {
                return Response.status(404).build();
            } else {
                List<Item> userInventory = this.inventoryManager.getUserInventory(UserId);
                GenericEntity<List<Item>> entity = new GenericEntity<List<Item>>(userInventory) {};
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


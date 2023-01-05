package edu.upc.dsa.dao.implementations;

import com.sun.jna.platform.win32.Netapi32Util;
import edu.upc.dsa.dao.IUserDAO;
import edu.upc.dsa.dao.InventoryDAO;
import edu.upc.dsa.dao.ItemDAO;
import edu.upc.dsa.models.Inventory;
import edu.upc.dsa.models.Item;
import edu.upc.dsa.models.User;
import org.apache.log4j.Logger;

import java.beans.IntrospectionException;
import java.util.LinkedList;
import java.util.List;

public class InventoryDAOImpl implements InventoryDAO {

    private SessionImpl session;

    final static Logger logger = Logger.getLogger(ItemDAOImpl.class);

    private static InventoryDAOImpl instance;

    private InventoryDAOImpl(){
        this.session = SessionImpl.getInstance();
    }

    public static InventoryDAO getInstance(){
        if(instance == null){
            instance = new InventoryDAOImpl();
        }
        return instance;
    }

    private static IUserDAO UserManager = UserDAOImpl.getInstance();
    private static ItemDAO ItemManager = ItemDAOImpl.getInstance();



    @Override
    //Nos da la lista de todos los inventarios que existen.

    public List<Inventory> getInventory() {
        List<Inventory> Inventorium = this.session.findAll(Inventory.class);
        return Inventorium;
    }



    @Override
    //Nos da el inventario de un usuario en particular.
    public List<Item> getUserInventory(String UserId) {
        List<Inventory> Inventorium = this.getInventory();
        List<Item> items = new LinkedList<>();

        for(Inventory inv: Inventorium){
            if(inv.getUserId().equals(UserId)){
                Item i = (Item) this.session.getById(Item.class, inv.getItemId());
                items.add(i);
            }
        }
        return items;
    }

    @Override
    //nos permite añadir un inventario.
    public Inventory addInventory(String UserId, String ItemId) {
        User u = (User) this.session.getById(User.class, UserId);
        Item i = (Item) this.session.getById(Item.class, ItemId);
        Inventory inv = new Inventory(u.getId(), i.getId());
        this.session.save(inv);
        return inv;
    }

    @Override
    // Permite al usuario comprar un Item para su inventario.
    public Inventory PurchaseItem(String UserId, String ItemId) throws IntrospectionException {
        Inventory inventory = null;
        User u = (User) this.session.getById(User.class, UserId);
        Item i = (Item) this.session.getById(Item.class, ItemId);
        if(u.getCoins() < i.getPrice() || !this.Repeated(UserId, ItemId)){
            inventory = this.addInventory(UserId, ItemId);
            int coins = u.getCoins() - i.getPrice();
            u.setCoins(coins);
            logger.info("El usuario"+ u.getUsername()+ "ha comprado el item" + i.getName());
        }
        return inventory;

    }

    @Override
    //La siguiente función determina si el usuario ya tiene el objeto en cuestión en su inventario.
    public boolean Repeated(String UserName, String ItemName) {
        List<Inventory> FullInventory = this.getInventory();
        User u = UserManager.getUserByName(UserName);
        Item i = ItemManager.getItemByName(ItemName);
        for (Inventory inv : FullInventory){
            if((inv.getUserId().equals(u.getId())) && (inv.getItemId().equals(i.getId()))){
                return true;
            }
        }
        return false;

    }
}

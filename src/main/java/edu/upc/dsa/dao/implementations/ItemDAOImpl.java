package edu.upc.dsa.dao.implementations;

import edu.upc.dsa.dao.IUserDAO;
import edu.upc.dsa.dao.ItemDAO;
import edu.upc.dsa.models.Item;
import org.apache.log4j.Logger;

import java.util.List;

public class ItemDAOImpl implements ItemDAO {
    private SessionImpl session;

    final static Logger logger = Logger.getLogger(ItemDAOImpl.class);

    private static ItemDAOImpl instance;

    private ItemDAOImpl(){
        this.session = SessionImpl.getInstance();
    }

    public static ItemDAO getInstance(){
        if(instance == null){
            instance = new ItemDAOImpl();
        }
        return instance;
    }

    @Override
    public List<Item> getItems() {
        List<Item> Items = this.session.findAll(Item.class);
        return Items;
    }

    @Override
    public Item addItem(Item i) {
        this.session.save(i);
        logger.info("Item saved:" + i.toString());
        return i;
    }

    @Override
    public void deleteItem(String ItemId) {
        Item i = (Item) this.session.getById(Item.class, ItemId);
        logger.info("Deleting the following item: " + i.toString());
        session.delete(i);
    }

    @Override
    public Item getItemByName(String ItemName) {
        Item i = (Item) this.session.getById(Item.class, ItemName);
        if (i.getName() == null) {
            return null;
        }
        logger.info("Get item by name " + ItemName);
        return i;

    }
}


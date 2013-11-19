package basegame.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Lisa Helm on 9/11/2013
 * Represents the player in the game. Stored details about health and other attributes, plus items. Maybe also position?
 */
public class Player {
    
    private Set<Item> items;
    private Weapon equippedWeapon;
    private Armour equippedArmour;
    
    
    
    public Player()
    {
        items = new HashSet<>();
    }
    
    /**
     * Added 11/11/13 by Lisa
     * Checks if picking up the given item is a possibility
     * @param item
     * @return 
     */
    public boolean canPickUpItem(Item item) {
        return true;
    }

    /**
     * Adds the item to the player's item set
     * @param item 
     */
    public void pickUpItem(Item item) {
        items.add(item);
    }

    boolean hasItem(Item item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void dropItem(Item item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}

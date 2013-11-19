package basegame.model;

/**
 * Created by Lisa Helm on 9/11/2013
 * Basic information for items in the game.
 */
public class Item implements Occupant{

    protected String name;
    
    public Item(String name)
    {
        this.name=name;
    }
}

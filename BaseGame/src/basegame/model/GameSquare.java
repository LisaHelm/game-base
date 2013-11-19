package basegame.model;

import java.awt.Point;

/**
 * Created by Lisa Helm on 9/11/2013
 * Holds location, type and occupants of a single square in the game
 */
public class GameSquare 
{
    private Point location;
    private Terrain terrain;
    private Item item;
    
    public GameSquare(Point location)
    {
        this.location=location;
        terrain=Terrain.FLOOR;
    }
    
    /**
     * Getters and Setters 
     */
    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public Terrain getTerrain() {
        return terrain;
    }

    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }
    
    
}

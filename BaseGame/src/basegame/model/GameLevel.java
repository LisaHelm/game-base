package basegame.model;

import java.awt.Point;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Lisa Helm on 9/11/2013
 * Holds the details about a single level of the game.
 */
public class GameLevel {

    private Set<GameSquare> squares;
    private int width, height;
    private HashMap<Point,Occupant> occupants;

    
    
    /**
     * Constructs level of given size.
     * @param width
     * @param height 
     */
    public GameLevel(int width, int height)
    {
        squares = new HashSet<>();
        occupants = new HashMap<>();
        this.height=height;
        this.width=width;
        setUpLevel();
        
    }
    /**     
     * Sets up level. Will eventually randomly generate.  
     */
    private void setUpLevel()
    {
        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                GameSquare s = new GameSquare(new Point(w,h));
                if(h==0 || h==height-1)
                {
                    s.setTerrain(Terrain.WALL);
                }
                else if(w==0 || w==width-1)
                {
                    s.setTerrain(Terrain.WALL);
                }
                else
                {
                    s.setTerrain(Terrain.FLOOR);
                }                
                squares.add(s);
            }
        }
        
        occupants.put(new Point(23,24), new Item("test"));
    }
    
    /**
     * Finds the game square at the given point
     * Added by Lisa 10/11/13
     * @param p
     * @return 
     */
    public GameSquare getSquareAtPoint(Point p)
    {
        GameSquare square = new GameSquare(new Point(-1,-1));
        
        Iterator<GameSquare> iter=squares.iterator();
        
        while(iter.hasNext())
        {
            GameSquare s=iter.next();
            if(s.getLocation().equals(p))
            {
                square = s;
            }
        }        
        return square;
    }
    
    /**
     * Getters and Setters 
     */

    public Set<GameSquare> getSquares() {
        return squares;
    }

    public void setSquares(Set<GameSquare> squares) {
        this.squares = squares;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }    
    
    public HashMap<Point, Occupant> getOccupants() {
        return occupants;
    }

    public void setOccupants(HashMap<Point, Occupant> occupants) {
        this.occupants = occupants;
    }

    void addOccupant(Point point, Item item) {
        if(occupants.containsKey(point))
        {
            addOccupant(new Point(point.x,point.y+1), item);
        }
        else
        {
            occupants.put(point, item);
        }
    }
}

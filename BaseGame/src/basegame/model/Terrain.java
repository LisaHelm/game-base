package basegame.model;

import java.awt.Color;

/**
 * Created by Lisa Helm on 9/11/2013
 * Defines all possible types of terrain that a GameSquare may have.
 */
public enum Terrain 
{
    WALL(new Color(0.5f, 0.5f, 0.5f)),
    FLOOR(new Color(0.9f, 0.9f, 0.9f));
    
    private Color colour;
    
    Terrain(Color colour)
    {
        this.colour=colour;
    }
    
    public Color getColour()
    {
        return colour;
    }
}

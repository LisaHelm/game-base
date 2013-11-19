package basegame.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by Lisa Helm on 9/11/2013
 * The main class of the model of the game.
 */
public class Game {
    
    private static Point BADPOINT = new Point(-1,-1);
    private Player player;
    private ArrayList<GameLevel> gameLevels;
    private GameLevel currentLevel;
    private Point playerLocation;
    
    
    
    public Game()
    {
        player = new Player();
        gameLevels=new ArrayList<>();
        
        testGame();
    }
    
    public void testGame()
    {
        currentLevel = new GameLevel(30,30);
        gameLevels.add(currentLevel);
        playerLocation = new Point(12,12);
        
    }
    
    /**
     * Moves the player in the specified direction. Prevents move if new location is invalid. Checks if possible to pick up any items. Picks them up.
     * Added by Lisa 10/11/13
     * @param dir 
     */
    public void movePlayer(MoveDirection dir)
    {
        Point point;
        if(dir==MoveDirection.LEFT)
        {
            point=new Point(playerLocation.x-1, playerLocation.y);
        }
        else if(dir==MoveDirection.RIGHT)
        {
            point=new Point(playerLocation.x+1, playerLocation.y);
        }
        else if(dir==MoveDirection.UP)
        {
            point=new Point(playerLocation.x, playerLocation.y+1);
        }
        else if(dir==MoveDirection.DOWN)
        {
            point=new Point(playerLocation.x, playerLocation.y-1);
        }
        else
        {
            point=new Point(playerLocation.x, playerLocation.y);
        }
        
        GameSquare s = currentLevel.getSquareAtPoint(point);
        if(point==BADPOINT)
        {
            point=new Point(playerLocation.x, playerLocation.y);
        }
        else if(s.getTerrain()==Terrain.WALL)
        {
            point=new Point(playerLocation.x, playerLocation.y);
        }
        playerLocation=point;
        
        
        if(currentLevel.getOccupants().containsKey(point))
        {
            if(currentLevel.getOccupants().get(point) instanceof Item)
            {
                grabItemForPlayer((Item)currentLevel.getOccupants().get(point));
            }
        }
    }
    
    /**
     * Checks if player can get item, gives item to player and removes it from gamelevel
     * @param item 
     */
    private void grabItemForPlayer(Item item) {
        if(player.canPickUpItem(item))
        {
            player.pickUpItem(item);
            currentLevel.getOccupants().remove(playerLocation);
        }
    }
    
    public void dropItemForPlayer(Item item)
    {
        if(player.hasItem(item))
        {
            player.dropItem(item);            
            currentLevel.addOccupant(playerLocation, item);
        }
    }
    
    

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public ArrayList<GameLevel> getGameLevels() {
        return gameLevels;
    }

    public void setGameLevels(ArrayList<GameLevel> gameLevels) {
        this.gameLevels = gameLevels;
    }

    public GameLevel getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(GameLevel currentLevel) {
        this.currentLevel = currentLevel;
    }

    public Point getPlayerLocation() {
        return playerLocation;
    }

    public void setPlayerLocation(Point playerLocation) {
        this.playerLocation = playerLocation;
    }

}

package basegame;

import basegame.model.Game;
import basegame.view.GameScreen;

/**
 * Created by Lisa Helm on 09/11/2013
 * Main class of game, just creates game and gives it to a screen.
 */
public class BaseGame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Game game = new Game();
        GameScreen screen = new GameScreen(game);
    }
}

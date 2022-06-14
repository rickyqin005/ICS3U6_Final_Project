package screens;
import java.util.ArrayList;

import components.GameComponent;
import core.Game;


public abstract class GameScreen {
    protected Game game;
    /**
     * A list of screens to be rendered.
     */
    protected ArrayList<GameComponent> screens = new ArrayList<GameComponent>();
    public void update() {
        for(GameComponent screen: screens) {
            screen.update();
        }
    }
}

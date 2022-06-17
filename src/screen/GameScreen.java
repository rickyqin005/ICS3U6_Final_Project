package screen;

import component.Updatable;
import core.Game;

public abstract class GameScreen {
    protected Game game;
    public GameScreen(Game game) {
        this.game = game;
    }
    /**
     * Finds a GameComponent with the specified name, or returns and adds a new GameComponent if it is not found
     * @param name The name of the component
     * @param ifNotFound The value to return if the requested GameComponent is not found
     */
    public Updatable getGameComponentByName(String name, Updatable ifNotFound) {
        Updatable component = game.getGameComponentByName(name);
        if(component == null) {
            component = ifNotFound;
            game.addGameComponent(component);
        }
        return component;
    }
    public abstract void update();
    public abstract void exitScreen();
}

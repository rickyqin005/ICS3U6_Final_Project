package screen;

import component.GameComponent;
import core.Game;

public abstract class GameScreen {
    protected Game game;
    public GameScreen(Game game) {
        this.game = game;
    }
    /**
     * Finds a GameComponent with the specified name, or returns a new GameComponent if it is not found
     * @param name The name of the component
     * @param ifNotFound The value to return if the requested GameComponent is not found
     */
    public GameComponent getGameComponentByName(String name, GameComponent ifNotFound) {
        GameComponent component = game.getGameComponentByName(name);
        if(component == null) {
            component = ifNotFound;
            game.addGameComponent(component);
        }
        return component;
    }
    public void update() {
        for(GameComponent component: game.getGameComponents()) {
            component.update();
        }
    }
}

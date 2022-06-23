package screen;

import core.Game;
import core.Updatable;

public abstract class GameScreen implements Updatable {
    protected Game game;

    public GameScreen(Game game) {
        this.game = game;
    }

    public abstract int getState();
    
    public abstract void exitScreen(int newState);
}

package screen;

import core.Game;

public abstract class GameScreen {
    protected Game game;

    public GameScreen(Game game) {
        this.game = game;
    }
    
    public abstract int getState();
    /**
     * Updates the contents of the screen and all its subcomponents.
     */
    public abstract void updateState();
    /**
     * Repaints the screen and all its subcomponents.
     */
    public abstract void updateGraphics();
    public abstract void exitScreen(int newState);
}

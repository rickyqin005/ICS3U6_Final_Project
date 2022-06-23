package core;

public interface Updatable {
    /**
     * This method is called every frame to update the contents and repaint itself and all its subcomponents.
     * Note that the grid should always be repainted first.
     */
    public void update();
}

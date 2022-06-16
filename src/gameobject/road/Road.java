package gameobject.road;

import java.awt.Color;

import utility.Line;


public class Road {
    /**
     * The standard colour of a road
     */
    public static final Color COLOR = Color.GRAY;
    /**
     * The standard width of a road in cells
     */
    public static final int THICKNESS = 1;
    private Line path;
    private int numLanes;

    public Road(Line path, int numLanes) {
        this.path = path;
        this.numLanes = numLanes;
    }

    public Line getPath() {
        return this.path;
    }
    public int numLanes() {
        return this.numLanes;
    }
}

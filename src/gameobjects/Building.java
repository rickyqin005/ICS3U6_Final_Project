package gameobjects;
import java.awt.Dimension;
import java.awt.Point;

import components.Grid;
import utility.Sprite;

public abstract class Building {
    private static int nextId = 1;
    private static int getNextId() {
        return nextId++;
    }

    private int id;
    private Grid grid;
    private Dimension dimension;
    private int cost;
    /**
     * The coordinates of the top left corner of the building.
     */
    private Point location;
    private String picture;

    public Building(Grid grid, Dimension dimension, int cost, Point location, String picture) {
        this.id = Building.getNextId();
        this.grid = grid;
        this.dimension = dimension;
        this.cost = cost;
        this.location = location;
        this.picture = picture;
    }

    public int getId() {
        return id;
    }
    public Grid getGrid() {
        return grid;
    }
    public Dimension getDimension() {
        return dimension;
    }
    public int getCost() {
        return cost;
    }
    public Point getLocation() {
        return location;
    }
    public Sprite getPicture(int scale) {
        return new Sprite(0, 0, picture + "_scale" + scale + ".png");
    }
}

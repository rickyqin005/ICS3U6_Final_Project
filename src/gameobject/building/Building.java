package gameobject.building;

import java.awt.Color;
import java.awt.Rectangle;

import component.Grid;
import images.ImagePath;
import utility.Sprite;

public abstract class Building {
    private static int nextId = 1;
    private static int getNextId() {
        return nextId++;
    }

    private int id;
    private Grid grid;
    private Rectangle plot;
    private int cost;
    private Color background;
    private Sprite[] pictures = new Sprite[Grid.Viewport.SCALES.length];

    private void loadPictures(String pictureName) {
        for(int i = 0; i < this.getPictures().length; i++) {
            pictures[i] = new Sprite(0, 0, ImagePath.getImagePath(pictureName, Grid.Viewport.SCALES[i]));
        }
    }
    public Building(Grid grid, Rectangle plot, int cost, Color background) {
        this.id = Building.getNextId();
        this.grid = grid;
        this.plot = plot;
        this.cost = cost;
        this.background = background;
        this.pictures = new Sprite[Grid.Viewport.SCALES.length];
    }
    public Building(Grid grid, Rectangle plot, int cost, Color background, String pictureName) {
        this.id = Building.getNextId();
        this.grid = grid;
        this.plot = plot;
        this.cost = cost;
        this.background = background;
        loadPictures(pictureName);
    }

    public int getId() {
        return id;
    }
    public Grid getGrid() {
        return grid;
    }
    public Rectangle getPlot() {
        return plot;
    }
    public int getCost() {
        return cost;
    }
    public Color getBackGround() {
        return background;
    }
    public Sprite[] getPictures() {
        return pictures;
    }
    public Sprite getPicture(int scaleLevel) {
        return pictures[scaleLevel];
    }
}

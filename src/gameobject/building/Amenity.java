package gameobject.building;

import java.awt.Color;
import java.awt.Rectangle;

import component.Grid;

public class Amenity extends Building {
    public Amenity(Grid grid, Rectangle plot, int cost, Color background, String pictureName) {
        super(grid, plot, cost, background, pictureName);
    }
}

package gameobject.building;

import java.awt.Color;
import java.awt.Rectangle;

import component.Grid;

public class ServiceBuilding extends Building {
    public ServiceBuilding(Grid grid, Rectangle plot, int cost, Color background, String pictureName) {
        super(grid, plot, cost, background, pictureName);
    }
}

package gameobject.building;

import java.awt.Color;
import java.awt.Rectangle;

import component.Grid;
import images.ImagePath;
import utility.Sprite;

public class ResidentialBuilding extends Building {
    // single family -> townhouse -> multi-family -> apartment -> condominium -> skycraper
    private int level;
    private int population;
    private int basePopulation;

    public ResidentialBuilding(Grid grid, Rectangle plot, int cost, Color background, String pictureName, int level) {
        super(grid, plot, cost, background);
        
        for(int i = 0; i < this.getPictures().length; i++) {
            this.getPictures()[i] = new Sprite(0, 0, ImagePath.getImagePath(pictureName, Grid.Viewport.SCALES[i], level));
        }
    }
    /**
     * @return The level of the building
     */
    public int getLevel() {
        return level;
    }
    /**
     * @return The population of the building
     */
    public int getPopulation() {
        return population;
    }
    /**
     * @return The population of the building, excluding any boosts
     */
    public int getBasePopulation() {
        return basePopulation;
    }
    /**
     * @return The tax rate, expressed as an integer percentage
     */
    public void getTaxRate() {
        
    }
}

package gameobject.building;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import component.Grid;
import images.ImagePath;
import utility.Random;
import utility.Sprite;

public class ResidentialBuilding extends Building {
    private static final String[] NAME = {"Single Family Home", "Townhouse", "Multi-family Unit", "Apartment", "Condominium"};
    private static final int MAX_LEVEL = NAME.length-1;
    private static final int[][] BASE_POPULATION_RANGE = {{50, 60}, {200, 250}, {800, 1000}, {3000, 3600}, {15000, 18000}};
    private static final int DEFAULT_WIDTH = 3;
    private static final int DEFAULT_HEIGHT = 3;
    /**
     * The cost of constructing a residential building.
     */
    private static final int DEFAULT_COST = 0;
    private static final Color DEFAULT_BACKGROUND = Color.WHITE;
    private static final String DEFAULT_PICTURE_NAME = "house";
    public static final int TAX_RATE_TIME_INVERVAL = 60*60*1000;
    public static final String TAX_RATE_TIME_UNIT = "hour";
    public static int getTotalPopulation(ArrayList<Building> buildings) {
        int population = 0;
        for(Building building: buildings) {
            if(building instanceof ResidentialBuilding) {
                population += ((ResidentialBuilding)building).getPopulation();
            }
        }
        return population;
    }
    /**
     * Calculates the total tax rate in the unit: SimCoins/hour
     * @param buildings The list of buildings to calculate the tax rate on
     * @return The total tax rate
     */
    public static int getTaxRate(ArrayList<Building> buildings) {
        int population = getTotalPopulation(buildings);
        return (int)(600*Math.pow(population, 1.0/2.0));
    }
    private int basePopulation;
    private int populationBoost;
    private int level;
    private void loadPictures() {
        for(int i = 0; i < this.getPictures().length; i++) {
            this.getPictures()[i] = new Sprite(0, 0, ImagePath.getImagePath(DEFAULT_PICTURE_NAME, Grid.Viewport.SCALES[i], level));
        }
    }
    private void setLevel(int newLevel) {
        level = newLevel;
        basePopulation = Random.randInt(BASE_POPULATION_RANGE[level][0], BASE_POPULATION_RANGE[level][1]);
        loadPictures();
    }
    /**
     * @param location The top left corner of the building.
     * @param cost The cost of building the building.
     * @param level The level of the residential building (0-indexed).
     */
    public ResidentialBuilding(Grid grid, Point location, int level) {
        super(grid, new Rectangle(location.x, location.y, DEFAULT_WIDTH, DEFAULT_HEIGHT), DEFAULT_COST, DEFAULT_BACKGROUND);

        populationBoost = 0;
        setLevel(level);
        grid.getUser().spendSimCoins(DEFAULT_COST);
    }
    /**
     * Returns the name of the building, which varies based on its level
     * @return The name of the building
     */
    public String getName() {
        return NAME[level];
    }
    /**
     * @return The level of the building
     */
    public int getLevel() {
        return level;
    }
    /**
     * Returns the true population of the building. Since the true population can change frequently, it is calculated when this method is called.
     * @return The true population of the building
     */
    public int getPopulation() {
        return (int)(basePopulation * (1.0 + (double)populationBoost/100));
    }
    /**
     * @return The population of the building, excluding any boosts
     */
    public int getBasePopulation() {
        return basePopulation;
    }
    
    public void upgradeBuilding() {
        if(level < MAX_LEVEL) {
            setLevel(level+1);
        }
    }
}

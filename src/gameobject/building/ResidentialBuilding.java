package gameobject.building;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JLabel;

import component.ComponentList;
import component.Grid;
import utility.Direction;
import utility.Images;
import utility.Random;
import utility.Rectangles;
import utility.Text;

public class ResidentialBuilding extends Building {
    public static final String[] NAME = {"Single Family Home", "Townhouse", "Multi-family Unit", "Apartment", "Condominium"};
    public static final int[] DEFAULT_UPGRADE_COST = {0, 2000, 6000, 15000, 30000};
    private static final int MAX_LEVEL = NAME.length-1;
    private static final int[][] BASE_POPULATION_RANGE = {{50, 60}, {200, 250}, {800, 1000}, {3000, 3600}, {15000, 18000}};
    public static final Dimension DEFAULT_DIMENSIONS = new Dimension(3, 3);
    public static final String DEFAULT_PICTURE_NAME = "house";
    public static final int TAX_RATE_TIME_INVERVAL = 60*60*1000;
    public static final String TAX_RATE_TIME_UNIT = "hour";

    /**
     * Calculates the total population of a list of buildings.
     * @param buildings The buildings to count.
     * @return The total population.
     */
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
     * Calculates the total tax rate in terms of SimCoins/hour
     * @param buildings The list of buildings to calculate the tax rate on
     * @return The total tax rate
     */
    public static int getTaxRate(ArrayList<Building> buildings) {
        int population = getTotalPopulation(buildings);
        return (int)(600*Math.pow(population, 1.0/2.0));
    }

    private int level;
    private int basePopulation;
    private int populationBoost;
    
    @Override
    protected void loadPictures() {
        for(int i = 0; i < this.pictures.length; i++) {
            this.pictures[i] = Images.getSprite(Images.getImagePath(DEFAULT_PICTURE_NAME, Grid.Viewport.SCALES[i], level));
        }
    }
    
    private void setLevel(int newLevel) {
        level = newLevel;
        basePopulation = Random.randInt(BASE_POPULATION_RANGE[level][0], BASE_POPULATION_RANGE[level][1]);
        loadPictures();
    }

    private void calculatePopulationBoost() {
        populationBoost = 0;
        for(Building amenity: grid.getBuildings()) {
            if(amenity instanceof Amenity) {
                if(Rectangles.overlap(this.plot, amenity.plot)) {
                    populationBoost += ((Amenity)amenity).getBoost();
                }
            }
        }
    }

    /**
     * @param grid The grid the building is in.
     * @param location The top left corner of the building.
     */
    public ResidentialBuilding(Grid grid, Point location) {
        super(grid, "", new Rectangle(location, DEFAULT_DIMENSIONS), 0, DEFAULT_BACKGROUND_COLOR, DEFAULT_PICTURE_NAME);

        setLevel(0);
        calculatePopulationBoost();
    }

    /**
     * @param grid The grid the building is in.
     * @param location The top left corner of the building.
     * @param level The level of the building.
     */
    public ResidentialBuilding(Grid grid, Point location, int level) {
        super(grid, "", new Rectangle(location, DEFAULT_DIMENSIONS), 0, DEFAULT_BACKGROUND_COLOR, DEFAULT_PICTURE_NAME);

        setLevel(level);
        calculatePopulationBoost();
    }

    /**
     * @param template The template for the building.
     * @param grid The grid the building is in.
     * @param location The top left corner of the building.
     */
    public ResidentialBuilding(TemplateResidentialBuilding template, Grid grid, Point location) {
        super(grid, "", new Rectangle(location, DEFAULT_DIMENSIONS), 0, DEFAULT_BACKGROUND_COLOR, DEFAULT_PICTURE_NAME);

        setLevel(0);
        calculatePopulationBoost();
    }

    /**
     * @param args {Building class, plot.x, plot.y, level, basePopulation}
     * @param grid The grid the building is in.
     */
    public ResidentialBuilding(String[] args, Grid grid) {
        super(grid, "", 
                new Rectangle(new Point(Integer.parseInt(args[1]), Integer.parseInt(args[2])), DEFAULT_DIMENSIONS), 
                0, DEFAULT_BACKGROUND_COLOR, DEFAULT_PICTURE_NAME);
        
        setLevel(Integer.parseInt(args[3]));
        setBasePopulation(Integer.parseInt(args[4]));
        calculatePopulationBoost();
    }

    @Override
    public JComponent[] toInfoComponents(Grid grid) {
        JComponent[] superComponentList = super.toInfoComponents(grid);
        ((JLabel)superComponentList[0]).setText(getName());

        JLabel buildingPopulation = new JLabel("Population: " + Integer.toString(getPopulation()));
        Text.formatJLabel(buildingPopulation);

        return new JComponent[]{superComponentList[0], superComponentList[1], buildingPopulation};
    }

    @Override
    public ComponentList toInfoComponentList(Grid grid) {
        return new ComponentList("residentialBuildingInfoPanel", toInfoComponents(grid), Direction.DOWN, 
        new int[] {1, 5, 1}, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
    }

    @Override
    public String toString() {
        return "ResidentialBuilding" + "\t" + plot.x + "\t" + plot.y + "\t" + level + "\t" + basePopulation;
    }

    /**
     * Returns the name of the building, which varies based on its level
     * @return The name of the building
     */
    @Override
    public String getName() {
        return NAME[level];
    }

    /**
     * Returns the current level of the building
     */
    public int getLevel() {
        return level;
    }

    /**
     * Returns the true population of the building. Since the true population can change frequently, it is calculated when this method is called.
     */
    public int getPopulation() {
        return (int)(basePopulation * (1.0 + (double)populationBoost/100));
    }

    /**
     * Returns the population of the building, excluding any boosts.
     */
    public int getBasePopulation() {
        return basePopulation;
    }

    /**
     * Sets the base population of the building to the specified value.
     * @param newBasePopulation The new population.
     */
    public void setBasePopulation(int newBasePopulation) {
        basePopulation = newBasePopulation;
    }

    @Override
    public int getCost() {
        return DEFAULT_UPGRADE_COST[level];
    }

    /**
     * Returns the cost of upgrading the residential building to the next level.
     */
    public int getUpgradeCost() {
        return DEFAULT_UPGRADE_COST[level+1];
    }

    /**
     * Upgrades the current building, causing the level to increase by 1.
     */
    public void upgradeBuilding() {
        if(level < MAX_LEVEL) {
            setLevel(level+1);
        }
    }

    @Override
    public void moveTo(Point newLocation) {
        super.moveTo(newLocation);
        calculatePopulationBoost();
    }

}

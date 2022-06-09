import java.awt.Dimension;
import java.awt.Point;

public class ResidentialBuilding extends Building {
    // single family -> townhouse -> multi-family -> apartment -> condominium -> skycraper
    public static final String[] pictures = {"src/images/house0", "src/images/house1", "src/images/house2", "src/images/house3"};
    private int population;
    private int basePopulation;

    public ResidentialBuilding(Grid grid, Dimension dimension, int cost, Point location, String picture) {
        super(grid, dimension, cost, location, picture);
        
    }
    /**
     * @return the population of the building
     */
    public int getPopulation() {
        return this.population;
    }
    /**
     * @return the population of the building, excluding any boosts
     */
    public int getBasePopulation() {
        return this.basePopulation;
    }
    /**
     * @return The tax rate, expressed as an integer percentage
     */
    public void getTaxRate() {
        
    }
}

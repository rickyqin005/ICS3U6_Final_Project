package component.label;

import component.Grid;
import gameobject.building.ResidentialBuilding;

public class PopulationLabel extends GameLabel {
    private static final String NAME = "populationLabel";
    private Grid grid;

    public PopulationLabel(Grid grid) {
        super();
        this.grid = grid;
        update();
    }

    public String populationToString() {
        return "Population: " + String.format("%,d", ResidentialBuilding.getTotalPopulation(grid.getBuildings()));
    }
    
    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void update() {
        setText(populationToString());
    }
}

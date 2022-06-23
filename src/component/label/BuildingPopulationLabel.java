package component.label;

import gameobject.building.ResidentialBuilding;

public class BuildingPopulationLabel extends GameLabel {
    private static final String NAME = "buildingPopulationLabel";

    private ResidentialBuilding building;
    
    public BuildingPopulationLabel(ResidentialBuilding building) {
        super();
        this.building = building;
        update();
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void update() {
        setText("Population: " + Integer.toString(building.getPopulation()));
    }
}

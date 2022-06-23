package component.label;

import gameobject.building.TemplateAmenity;

public class BuildingBoostLabel extends GameLabel {
    private static final String NAME = "buildingBoostLabel";

    private TemplateAmenity building;
    
    public BuildingBoostLabel(TemplateAmenity building) {
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
        setText(building.boostToString() + ", " + building.boostDimensionsToString());
    }
}

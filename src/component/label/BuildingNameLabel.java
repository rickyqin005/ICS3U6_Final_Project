package component.label;

import gameobject.building.TemplateBuilding;

public class BuildingNameLabel extends GameLabel {
    private static final String NAME = "buildingNameLabel";

    private TemplateBuilding building;
    public BuildingNameLabel(TemplateBuilding building) {
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
        setText(building.getName() + ", " + building.dimensionsToString());
    }
}

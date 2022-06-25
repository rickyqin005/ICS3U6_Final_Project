package component.label;

import gameobject.building.TemplateBuilding;

public class BuildingPictureLabel extends PictureLabel {
    private static final String NAME = "buildingPictureLabel";
    
    private TemplateBuilding building;

    public BuildingPictureLabel(TemplateBuilding building) {
        super(NAME);
        this.building = building;
        update();
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void update() {
        setIcon(building.getPictureIcon());
    }
}
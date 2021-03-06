package gameobject.building;

import java.awt.GridBagConstraints;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;

import component.ComponentList;
import component.Grid;
import core.Updatable;
import utility.Direction;

public class TemplateResidentialBuilding extends TemplateBuilding {
    public static final ArrayList<TemplateResidentialBuilding> TEMPLATES = new ArrayList<TemplateResidentialBuilding>(Arrays.asList(
        new TemplateResidentialBuilding()
    ));

    public TemplateResidentialBuilding() {
        super(ResidentialBuilding.NAME[ResidentialBuilding.STARTING_LEVEL], 
                new Rectangle(ResidentialBuilding.DEFAULT_DIMENSIONS), 
                ResidentialBuilding.DEFAULT_UPGRADE_COST[ResidentialBuilding.STARTING_LEVEL], 
                ResidentialBuilding.DEFAULT_PICTURE_NAME_PREFIX + ResidentialBuilding.STARTING_LEVEL);
    }

    @Override
    protected Updatable[] toInfoComponents(Grid grid) {
        return super.toInfoComponents(grid);
    }

    @Override
    public ComponentList toInfoComponentList(Grid grid) {
        return new ComponentList("templateResidentialBuildingInfoPanel", toInfoComponents(grid), 
        Direction.DOWN, new int[] {1, 5, 1}, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
    }
}

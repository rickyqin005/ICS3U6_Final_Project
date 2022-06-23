package gameobject.building;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;

import component.ComponentList;
import component.Grid;
import component.label.BuildingBoostLabel;
import core.Updatable;
import utility.Direction;

public class TemplateAmenity extends TemplateBuilding {
    public static final ArrayList<TemplateAmenity> TEMPLATES = new ArrayList<TemplateAmenity>(Arrays.asList(
        new TemplateAmenity("Public School", new Rectangle(new Dimension(6, 4)), new Dimension(24, 18), 12000, "school", 25),
        new TemplateAmenity("Parkette", new Rectangle(new Dimension(2, 3)), new Dimension(12, 15), 5000, "parkette", 10),
        new TemplateAmenity("Park With Pond", new Rectangle(new Dimension(4, 3)), new Dimension(20, 12), 10000, "parkwithpond", 15),
        new TemplateAmenity("Police Station", new Rectangle(new Dimension(8, 6)), new Dimension(30, 30), 20000, "policestation", 20),
        new TemplateAmenity("Fire Station", new Rectangle(new Dimension(9, 5)), new Dimension(27, 21), 20000, "firestation", 20),
        new TemplateAmenity("Hospital", new Rectangle(new Dimension(8, 6)), new Dimension(30, 30), 25000, "hospital", 25),
        new TemplateAmenity("University", new Rectangle(new Dimension(9, 8)), new Dimension(45, 40), 40000, "university", 30)
    ));

    public static TemplateAmenity getTemplate(String name) {
        for(TemplateAmenity template: TEMPLATES) {
            if(template.getName().equals(name)) {
                return template;
            }
        }
        return null;
    }

    private int boost;
    private Dimension boostDimensions;

    public TemplateAmenity(String name, Rectangle plot, Dimension boostDimensions, int cost, String pictureName, int boost) {
        super(name, plot, cost, pictureName);
        this.boost = boost;
        this.boostDimensions = boostDimensions;
    }

    public int getBoost() {
        return boost;
    }

    public String boostToString() {
        return "+" + boost + "%";
    }

    public Dimension getBoostDimensions() {
        return boostDimensions;
    }

    public String boostDimensionsToString() {
        return boostDimensions.width + " x " + boostDimensions.height;
    }

    @Override
    protected Updatable[] toInfoComponents(Grid grid) {
        Updatable[] superComponentList = super.toInfoComponents(grid);

        BuildingBoostLabel buildingBoost = new BuildingBoostLabel(this);

        return new Updatable[] {
                superComponentList[0], superComponentList[1], buildingBoost, superComponentList[2]};
    }

    @Override
    public ComponentList toInfoComponentList(Grid grid) {
        return new ComponentList("templateAmenityInfoPanel", toInfoComponents(grid), Direction.DOWN, 
        new int[] {1, 5, 1, 1}, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
    }
}

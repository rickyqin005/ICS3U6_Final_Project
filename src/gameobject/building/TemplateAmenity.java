package gameobject.building;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JComponent;
import javax.swing.JLabel;

import component.ComponentList;
import component.Grid;
import utility.Direction;
import utility.Text;

public class TemplateAmenity extends TemplateBuilding {
    public static final ArrayList<TemplateAmenity> TEMPLATES = new ArrayList<TemplateAmenity>(Arrays.asList(
        new TemplateAmenity("Public School", new Rectangle(new Dimension(6, 4)), new Dimension(16, 12), 12000, "school", 25),
        new TemplateAmenity("Parkette", new Rectangle(new Dimension(2, 3)), new Dimension(10, 15), 5000, "parkette", 10),
        new TemplateAmenity("Police Station", new Rectangle(new Dimension(8,6)), new Dimension(20, 20), 20000, "policestationlarge", 20)
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
    protected JComponent[] toInfoComponents(Grid grid) {
        JComponent[] superComponentList = super.toInfoComponents(grid);

        JLabel buildingBoost = new JLabel(boostToString() + ", " + boostDimensionsToString());
        Text.formatJLabel(buildingBoost);

        return new JComponent[] {
                superComponentList[0], superComponentList[1], buildingBoost, superComponentList[2]};
    }

    @Override
    public ComponentList toInfoComponentList(Grid grid) {
        return new ComponentList("templateAmenityInfoPanel", toInfoComponents(grid), Direction.DOWN, 
        new int[] {1, 5, 1, 1}, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
    }
}

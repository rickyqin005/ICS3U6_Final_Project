package gameobject.building;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.JComponent;
import javax.swing.JLabel;

import component.ComponentList;
import component.Grid;
import utility.Direction;
import utility.Text;

public class Amenity extends Building {
    private TemplateAmenity template;
    private int boost;
    private Dimension boostDimensions;

    public Amenity(TemplateAmenity template, Grid grid, Point location) {
        super(grid, template.getName(), new Rectangle(location, template.getDimensions()), template.getCost(), 
                DEFAULT_BACKGROUND_COLOR, template.getPictureName());
        this.template = template;
        boost = template.getBoost();
        boostDimensions = template.getBoostDimensions();
    }

    /**
     * @param args {Building class, templateName, plot.x, plot.y}
     * @param grid The grid the building is in.
     */
    public Amenity(String[] args, Grid grid) {
        super(grid, TemplateAmenity.getTemplate(args[1]).name, 
                new Rectangle(new Point(Integer.parseInt(args[2]), Integer.parseInt(args[3])), TemplateAmenity.getTemplate(args[1]).getDimensions()), 
                TemplateAmenity.getTemplate(args[1]).cost, DEFAULT_BACKGROUND_COLOR, 
                TemplateAmenity.getTemplate(args[1]).pictureName);
        this.template = TemplateAmenity.getTemplate(args[1]);
        boost = template.getBoost();
        boostDimensions = TemplateAmenity.getTemplate(args[1]).getBoostDimensions();
    }

    @Override
    public JComponent[] toInfoComponents(Grid grid) {
        JComponent[] superComponentList = super.toInfoComponents(grid);

        JLabel buildingBoost = new JLabel(boostToString() + ", " + boostDimensionsToString());
        Text.formatJLabel(buildingBoost);

        return new JComponent[] {superComponentList[0], superComponentList[1], buildingBoost};
    }

    @Override
    public ComponentList toInfoComponentList(Grid grid) {
        return new ComponentList("amenityInfoPanel", toInfoComponents(grid), Direction.DOWN, 
        new int[] {1, 5, 1}, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
    }
    
    @Override
    public String toString() {
        return "Amenity" + "\t" + template.getName() + "\t" + plot.x + "\t" + plot.y;
    }

    public int getBoost() {
        return boost;
    }

    public Dimension getBoostDimensions() {
        return boostDimensions;
    }

    public String boostToString() {
        return "+" + boost + "%";
    }

    public String boostDimensionsToString() {
        return boostDimensions.width + " x " + boostDimensions.height;
    }

    /**
     * Sets the color of the current road according to the validity of its location.
     */
    @Override
    public void setValidityBackgroundColor() {
        if(isValidLocation()) {
            setBackgroundColor(Amenity.VALID_LOCATION_BACKGROUND_COLOR);
        } else {
            setBackgroundColor(Amenity.INVALID_LOCATION_BACKGROUND_COLOR);
        }
    }
}

package gameobject.building;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Rectangle;

import javax.swing.JComponent;
import javax.swing.JLabel;

import component.ComponentList;
import component.Grid;
import component.PicturePanel;
import component.button.AddBuildingButton;
import utility.Direction;
import utility.Images;
import utility.Text;
import utility.providedtemplates.Sprite;

public abstract class TemplateBuilding {
    protected String name;
    protected Rectangle plot;
    protected int cost;
    protected String pictureName;
    protected Sprite picture;

    public TemplateBuilding(String name, Rectangle plot, int cost, String pictureName) {
        this.name = name;
        this.plot = plot;
        this.cost = cost;
        this.pictureName = pictureName;
        this.picture = Images.getSprite(Images.getImagePath(pictureName));
    }

    public String getName() {
        return name;
    }

    public Rectangle getPlot() {
        return plot;
    }

    public Dimension getDimensions() {
        return plot.getSize();
    }

    public String dimensionsToString() {
        return plot.width + " x " + plot.height;

    }

    public int getCost() {
        return cost;
    }

    public String getPictureName() {
        return pictureName;
    }

    public Sprite getPicture() {
        return picture;
    }

    protected JComponent[] toInfoComponents(Grid grid) {
        JLabel buildingName = new JLabel(getName() + ", " + dimensionsToString());
        Text.formatJLabel(buildingName);

        PicturePanel buildingPicture = new PicturePanel(name, getPicture());

        AddBuildingButton buildingCost = new AddBuildingButton(grid, this);
        
        return new JComponent[] {buildingName, buildingPicture, buildingCost};
    }

    public ComponentList toInfoComponentList(Grid grid) {
        return new ComponentList("templateBuildingInfoPanel", toInfoComponents(grid), Direction.DOWN, 
        new int[] {1, 5, 1}, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
    }
}

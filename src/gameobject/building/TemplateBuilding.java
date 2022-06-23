package gameobject.building;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import component.ComponentList;
import component.Grid;
import component.button.AddBuildingButton;
import component.label.BuildingNameLabel;
import component.label.BuildingPictureLabel;
import core.Updatable;
import utility.Direction;
import utility.Images;

public abstract class TemplateBuilding {
    public static final int DEFAULT_PICTURE_SCALE_LEVEL = 1;
    protected String name;
    protected Rectangle plot;
    protected int cost;
    protected String pictureName;
    protected ImageIcon pictureIcon;

    protected void loadIcon() {
        pictureIcon = Images.getImageIcon(Images.getImagePath(pictureName));
    }
    
    public TemplateBuilding(String name, Rectangle plot, int cost, String pictureName) {
        this.name = name;
        this.plot = plot;
        this.cost = cost;
        this.pictureName = pictureName;
        loadIcon();
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

    public ImageIcon getPictureIcon() {
        return pictureIcon;
    }

    protected Updatable[] toInfoComponents(Grid grid) {
        BuildingNameLabel buildingName = new BuildingNameLabel(this);

        BuildingPictureLabel buildingPicture = new BuildingPictureLabel(this);

        AddBuildingButton buildingCost = new AddBuildingButton(grid, this);
        
        return new Updatable[] {buildingName, buildingPicture, buildingCost};
    }

    public ComponentList toInfoComponentList(Grid grid) {
        return new ComponentList("templateBuildingInfoPanel", toInfoComponents(grid), Direction.DOWN, 
        new int[] {1, 5, 1}, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
    }
}

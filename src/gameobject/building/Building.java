package gameobject.building;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.JComponent;
import javax.swing.JLabel;

import component.ComponentList;
import component.Grid;
import component.PicturePanel;
import component.Grid.Viewport;
import component.button.DemolishBuildingButton;
import component.button.MoveBuildingButton;
import gameobject.road.Road;
import gameobject.road.RoadNetwork;
import utility.Direction;
import utility.Images;
import utility.Line;
import utility.Rectangles;
import utility.Text;
import utility.providedtemplates.Sprite;

public abstract class Building extends TemplateBuilding {
    public static final Color DEFAULT_BORDER_COLOR = Color.BLACK;
    public static final Color DEFAULT_BACKGROUND_COLOR = Color.WHITE;
    public static final Color VALID_LOCATION_BACKGROUND_COLOR = new Color(153, 255, 153);
    public static final Color INVALID_LOCATION_BACKGROUND_COLOR = new Color(255, 153, 153);
    public static final Color SELECTED_BACKGROUND_COLOR = new Color(255, 255, 153);
    
    protected Grid grid;
    protected Color backgroundColor;
    protected Sprite[] pictures = new Sprite[Grid.Viewport.SCALES.length];
    protected boolean isSelected;

    protected void loadPictures() {
        for(int i = 0; i < this.getPictures().length; i++) {
            pictures[i] = Images.getSprite(Images.getImagePath(pictureName, Grid.Viewport.SCALES[i]));
        }
    }
    
    public Building(Grid grid, String name, Rectangle plot, int cost, Color backgroundColor, String pictureName) {
        super(name, plot, cost, pictureName);
        this.grid = grid;
        this.backgroundColor = backgroundColor;
        loadPictures();
        isSelected = false;
    }

    public Building(TemplateBuilding template, Grid grid, Point location, Color backgroundColor) {
        super(template.name, new Rectangle(location, template.getDimensions()), template.cost, template.pictureName);
        this.grid = grid;
        this.backgroundColor = backgroundColor;
        loadPictures();
        isSelected = false;
    }

    public Grid getGrid() {
        return grid;
    }

    public Point getLocation() {
        return new Point(plot.getLocation());
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color color) {
        backgroundColor = color;
    }
    
    /**
     * Sets the color of the current road according to the validity of its location.
     */
    public void setValidityBackgroundColor() {
        if(isValidLocation()) {
            backgroundColor = Building.VALID_LOCATION_BACKGROUND_COLOR;
        } else {
            backgroundColor = Building.INVALID_LOCATION_BACKGROUND_COLOR;
        }
    }

    public Sprite[] getPictures() {
        return pictures;
    }

    public Sprite getPicture(int scaleLevel) {
        return pictures[scaleLevel];
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean newVal) {
        isSelected = newVal;
    }

    public void draw(Graphics g, Viewport viewport) {
        Rectangle plotScreen = viewport.mapToViewport(plot);
        if(viewport.isInViewport(plotScreen)) {
            g.setColor(this.backgroundColor);
            g.fillRect(plotScreen.x, plotScreen.y, plotScreen.width, plotScreen.height);
            g.setColor(DEFAULT_BORDER_COLOR);
            g.drawRect(plotScreen.x, plotScreen.y, plotScreen.width, plotScreen.height);

            Sprite picture = this.getPicture(viewport.getScaleLevel());
            picture.setX(plotScreen.x + (plotScreen.width - picture.getWidth()) / 2);
            picture.setY(plotScreen.y + (plotScreen.height - picture.getHeight()) / 2);
            picture.draw(g);
        }
    }

    public void draw(Graphics g, Viewport viewport, Color backgroundColor) {
        Color regularBackground = this.backgroundColor;
        this.backgroundColor = backgroundColor;
        draw(g, viewport);
        this.backgroundColor = regularBackground;
    }

    @Override
    public JComponent[] toInfoComponents(Grid grid) {
        JLabel buildingName = new JLabel(getName());
        Text.formatJLabel(buildingName);

        PicturePanel buildingPicture = new PicturePanel("buildingPicture", Images.getImagePath(pictureName));

        return new JComponent[] {buildingName, buildingPicture};
    }

    @Override
    public ComponentList toInfoComponentList(Grid grid) {
        return new ComponentList("buildingInfoPanel", toInfoComponents(grid), Direction.DOWN, 
        new int[] {1, 5}, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
    }

    public JComponent[] toActionComponents() {
        MoveBuildingButton buildingMove = new MoveBuildingButton(grid, this);
        Text.formatJButton(buildingMove);

        DemolishBuildingButton buildingDemolish = new DemolishBuildingButton(grid, this);
        Text.formatJButton(buildingDemolish);

        return new JComponent[] {buildingMove, buildingDemolish};
    }

    public ComponentList toActionComponentList(Grid grid) {
        return new ComponentList("buildingActionComponentList", toActionComponents(), Direction.DOWN, 
        1, GridBagConstraints.CENTER, GridBagConstraints.NONE);
    }

    /**
     * Determines if the specified location is valid.
     * A location is valid if all of the following conditions are true:
     * 
     * <ul>
     *  <li>The building doesn't go out of bounds.</li>
     *  <li>The building doesn't overlap with any other buildings or roads.</li>
     *  <li>The building is adjacent to at least one other road.</li>
     * </ul>
     */
    public boolean isValidLocation(Point location) {
        if(!grid.toRectangle().contains(plot)) {
            return false;
        }
        for(Building building: grid.getBuildings()) {
            if(building != this) {
                if(this.overlapsWithBuilding(building)) {
                    return false;
                }
            }
        }
        for(Road road: grid.getRoadNetwork().getRoads()) {
            if(this.overlapsWithRoad(road)) {
                return false;
            }
        }
        if(!this.isAdjacentToRoad(grid.getRoadNetwork())) {
            return false;
        }
        return true;
    }

    /**
     * Determines if the specified location is valid.
     * A location is valid if all of the following conditions are true:
     * 
     * <ul>
     *  <li>The building doesn't go out of bounds.</li>
     *  <li>The building doesn't overlap with any other buildings or roads.</li>
     *  <li>The building is adjacent to at least one other road.</li>
     * </ul>
     */
    public boolean isValidLocation() {
        return isValidLocation(getLocation());
    }

    public void select() {
        setBackgroundColor(SELECTED_BACKGROUND_COLOR);
        setSelected(true);
    }

    public void deselect() {
        setBackgroundColor(DEFAULT_BACKGROUND_COLOR);
        setSelected(false);
    }
    
    /**
     * Moves the building to the specified location (top left corner).
     * @param newLocation The new location.
     */
    public void moveTo(Point newLocation) {
        plot.setLocation(newLocation);
    }

    /**
     * Determines if the current building overlaps with the specified building.
     * @param building The other building.
     * @return True if the intersection of the two buildings is non-zero.
     */
    public boolean overlapsWithBuilding(Building building) {
        return Rectangles.overlap(plot, building.plot);
    }

    /**
     * Determines if the current building overlaps with the specified road.
     * @param road The other road.
     * @return True if the intersection of the two objects is non-zero.
     */
    public boolean overlapsWithRoad(Road road) {
        return Rectangles.overlap(plot, road.toRectangle());
    }

    /**
     * Determines if the current building contains the specified cell.
     * @param point The cell expressed with its top-left corner.
    */
    public boolean hasCell(Point point) {
        return plot.contains(new Rectangle(point, new Dimension(1, 1)));
    }

    /**
     * Determines if the current building is adjacent to a road.
     * @param road The other road.
     * @return True if the intersection of the two objects is non-zero.
     */
    public boolean isAdjacentToRoad(RoadNetwork roadNetwork) {
        Line[] buildingEdges = Rectangles.getEdges(plot);
        for(Line buildingEdge: buildingEdges) {
            for(Road road: roadNetwork.getRoads()) {
                Line[] roadEdges = Rectangles.getEdges(road.toRectangle());
                for(Line roadEdge: roadEdges) {
                    if(buildingEdge.isCoincident(roadEdge)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}

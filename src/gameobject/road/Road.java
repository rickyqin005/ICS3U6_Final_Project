package gameobject.road;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import component.Grid;
import component.Grid.Viewport;
import gameobject.building.Building;
import utility.Line;
import utility.Rectangles;

public class Road {
    public static final Color DEFAULT_BORDER_COLOR = Color.BLACK;
    public static final Color DEFAULT_BACKGROUND_COLOR = Color.GRAY;
    public static final Color ROAD_MARKING_COLOR = Color.WHITE;
    public static final Color VALID_LOCATION_BACKGROUND_COLOR = new Color(153, 255, 153);
    public static final Color INVALID_LOCATION_BACKGROUND_COLOR = new Color(255, 153, 153);
    public static final Color SELECTED_BACKGROUND_COLOR = new Color(255, 255, 153);
    public static final int DEFAULT_THICKNESS = 1;
    public static final int DEFAULT_NUMLANES = 2;
    public static final int COST_PER_UNIT_DISTANCE = 200;

    private RoadNetwork roadNetwork;
    private Line path;
    private int numLanes;
    private Color backgroundColor;

    /**
     * Constructs a Road object.
     * @param path The path of the road. The direction must be either Direction.DOWN or Direction.RIGHT.
     */
    public Road(RoadNetwork roadNetwork, Line path) {
        this.roadNetwork = roadNetwork;
        this.path = path;
        this.numLanes = DEFAULT_NUMLANES;
        this.backgroundColor = DEFAULT_BACKGROUND_COLOR;
    }

    public Road(RoadNetwork roadNetwork, Line path, int numLanes) {
        this.roadNetwork = roadNetwork;
        this.path = path;
        this.numLanes = numLanes;
        this.backgroundColor = DEFAULT_BACKGROUND_COLOR;
    }

    public Road(RoadNetwork roadNetwork, Rectangle rect, int numLanes) {
        this.roadNetwork = roadNetwork;
        this.path = new Line(new Point(rect.x, rect.y), 
                new Point(rect.x+rect.width-DEFAULT_THICKNESS, rect.y+rect.height-DEFAULT_THICKNESS));
        this.numLanes = numLanes;
        this.backgroundColor = DEFAULT_BACKGROUND_COLOR;
    }

    /**
     * 
     * @param roadNetwork The road network the road is in.
     * @param args {start.x, start.y, end.x, end.y, numLanes}
     */
    public Road(RoadNetwork roadNetwork, String[] args) {
        this.roadNetwork = roadNetwork;
        this.path = new Line(new Point(Integer.parseInt(args[0]), Integer.parseInt(args[1])), 
                new Point(Integer.parseInt(args[2]), Integer.parseInt(args[3])));
        this.numLanes = Integer.parseInt(args[4]);
        this.backgroundColor = DEFAULT_BACKGROUND_COLOR;
    }

    @Override
    public String toString() {
        Point end = path.getEndPoint();
        return path.start.x + "\t" + path.start.y + "\t" + end.x + "\t" + end.y + "\t" + numLanes;
    }

    public Line getPath() {
        return this.path;
    }
    public int getNumLanes() {
        return this.numLanes;
    }
    public void setBackgroundColor(Color newBackgroundColor) {
        backgroundColor = newBackgroundColor;
    }

    /**
     * Sets the color of the current road according to the validity of its location.
     */
    public void setValidityBackgroundColor() {
        if(isValidLocation()) {
            backgroundColor = Road.VALID_LOCATION_BACKGROUND_COLOR;
        } else {
            backgroundColor = Road.INVALID_LOCATION_BACKGROUND_COLOR;
        }
    }
    public int getCost() {
        return COST_PER_UNIT_DISTANCE*path.distance;
    }

    public void drawArea(Graphics g, Viewport viewport) {
        Rectangle roadRect = viewport.mapToViewport(toRectangle());
        g.setColor(backgroundColor);
        g.fillRect(roadRect.x, roadRect.y, roadRect.width, roadRect.height);
        
        
    }

    public void drawOutline(Graphics g, Viewport viewport) {
        Rectangle roadRect = viewport.mapToViewport(toRectangle());
        g.setColor(DEFAULT_BORDER_COLOR);
        g.drawRect(roadRect.x, roadRect.y, roadRect.width, roadRect.height);
    }

    /**
     * Returns the path of the road expressed as a Rectangle.
     * @return The Rectangular representation of the road.
     */
    public Rectangle toRectangle() {
        return new Rectangle(path.start.x, path.start.y, path.direction[0]*path.distance+DEFAULT_THICKNESS, path.direction[1]*path.distance+DEFAULT_THICKNESS);
    }

    /**
     * Determines if the specified location is valid.
     * A location is valid if the road doesn't overlap with any other buildings, goes out of bounds, and has a length of greater than or equal to 2.
     */
    public boolean isValidLocation(Point location) {
        Grid grid = roadNetwork.getGrid();

        if(path.getLength() < 2) {
            return false;
        }

        Rectangle roadRect = toRectangle();
        if(!grid.toRectangle().contains(roadRect)) {
            return false;
        }

        for(Building building: grid.getBuildings()) {
            if(this.overlapsWithBuilding(building)) {
                return false;
            }
        }

        for(Road road: roadNetwork.getRoads()) {
            if(this.coincidesWithRoad(road)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Determines if the specified location is valid.
     * A location is valid if the road doesn't overlap with any other buildings or go out of bounds.
     */
    public boolean isValidLocation() {
        return isValidLocation(path.start);
    }

    /**
     * Determines if the current road overlaps with the specified building.
     * @param building The other building.
     * @return True if their intersection is non-zero.
     */
    public boolean overlapsWithBuilding(Building building) {
        return Rectangles.overlap(this.toRectangle(), building.getPlot());
    }

    /**
     * Determines the intersection point between the current road and the specified road.
     * Two road are considered to intersect each other if they meet at a single cell.
     * Consequently, coincident roads do not intersect each other.
     * @param road The specified road.
     * @return The intersection point, or null if there is no intersection point.
     */
    public Point getIntersectionPoint(Road road) {
        return this.path.getIntersectionPoint(road.path);
    }

    /**
     * Determines if the current road overlaps with the specified road.
     * @param road The other road.
     * @return True if the intersection of the two roads is non-zero.
     */
    public boolean intersectsWithRoad(Road road) {
        return (this.getIntersectionPoint(road) != null);
    }

    /**
     * Determines if the current road coincides with the specified road.
     * @param road The other road.
     */
    public boolean coincidesWithRoad(Road road) {
        return this.path.isCoincident(road.path);
    }
}

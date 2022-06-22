package gameobject.road;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import component.Grid;
import component.Grid.Viewport;
import utility.Rectangles;

public class RoadNetwork {
    private Grid grid;
    private ArrayList<Road> roads;
    private ArrayList<Point> intersectionPoints;

    public RoadNetwork(Grid grid) {
        this.grid = grid;
        this.roads = new ArrayList<Road>();
        this.intersectionPoints = new ArrayList<Point>();
    }

    private void calculateIntersectionPoints() {
        intersectionPoints.clear();
        for(int i = 0; i < roads.size(); i++) {
            Road road1 = roads.get(i);
            for(int j = i+1; j < roads.size(); j++) {
                Road road2 = roads.get(j);
                Point point = road1.getIntersectionPoint(road2);
                if(point != null) {
                    intersectionPoints.add(point);
                }
            }
        }
    }

    public Grid getGrid() {
        return grid;
    }
    
    /**
     * Adds the specified road to the road network.
     * @param road A reference to a road segment.
     */
    public void addRoad(Road road) {

        // merge the new road to an existing road if they are extensions of each other.
        for(int idx = 0; idx < roads.size(); idx++) {
            Road road2 = roads.get(idx);
            if(road.getPath().isParallel(road2.getPath())) {
                if(Rectangles.overlap(road.toRectangle(), road2.toRectangle()) && 
                        road.getNumLanes() == road2.getNumLanes()) {
                    road = new Road(this, road.toRectangle().union(road2.toRectangle()), road.getNumLanes());
                    
                    roads.remove(road2);
                    idx--;
                }
            }
        }
        roads.add(road);
        calculateIntersectionPoints();
    }

    /**
     * Removes a road from the road network.
     * @param road A reference to a road segment.
     */
    public void removeRoad(Road road) {
        roads.remove(road);
    }
    
    /**
     * Returns a list of all the road segments in this road network.
     */
    public ArrayList<Road> getRoads() {
        return roads;
    }

    public void draw(Graphics g, Viewport viewport) {
        for(Road road: roads) {
            road.drawArea(g, viewport);
        }

        for(Road road: roads) {
            road.drawOutline(g, viewport);
        }
    }
}

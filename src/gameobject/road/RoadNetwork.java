package gameobject.road;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import component.Grid;
import component.Grid.Viewport;

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
        for(Road road1: roads) {
            for(Road road2: roads) {
                if(road1 != road2) {
                    Point point = road1.getIntersectionPoint(road2);
                    if(point != null) {
                        intersectionPoints.add(point);
                    }
                }
            }
        }
    }

    public Grid getGrid() {
        return grid;
    }
    
    public void addRoad(Road road) {
        roads.add(road);
        calculateIntersectionPoints();
    }

    /**
     * Removes a road from the road network.
     * @param road A reference to a road segment.
     */
    public void removeRoad(Road road) {
        for(int i = 0; i < roads.size(); i++) {
            if(roads.get(i) == road) {
                roads.remove(i);
                return;
            }
        }
    }
    
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

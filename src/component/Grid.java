package component;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import gameobject.building.*;
import gameobject.road.*;
import utility.Const;
import utility.Line;
import utility.Sprite;

public class Grid extends GameComponent {
    public static final Dimension DEFAULT_DIMENSIONS = new Dimension(200, 200);
    private String name;
    private Dimension dimension;
    private Viewport viewport;
    private ArrayList<Building> buildings;
    private ArrayList<Road> roads;
    MouseState mouseState;
    GridMouseListener mouseListener;
    GridMouseMotionListener mouseMotionListener;

    public Grid(String name) {
        this.name = name;
        dimension = Grid.DEFAULT_DIMENSIONS;
        viewport = new Viewport(this);
        buildings = new ArrayList<Building>();
        roads = new ArrayList<Road>();
        mouseState = new MouseState();
        mouseListener = new GridMouseListener();
        addMouseListener(mouseListener);
        mouseMotionListener = new GridMouseMotionListener();
        addMouseMotionListener(mouseMotionListener);

        buildings.add(new ResidentialBuilding(this, new Rectangle(0, 4, 3, 3), 5000, Color.WHITE, "house", 3));
        buildings.add(new ResidentialBuilding(this, new Rectangle(4, 0, 3, 3), 5000, Color.WHITE, "house", 2));
        buildings.add(new ResidentialBuilding(this, new Rectangle(0, 0, 3, 3), 5000, Color.WHITE, "house", 1));
        buildings.add(new Amenity(this, new Rectangle(4, 4, 6, 4), 20000, Color.WHITE, "school"));
        buildings.add(new Amenity(this, new Rectangle(10, 12, 8, 6), 50000, Color.WHITE, "policestationlarge"));
        roads.add(new Road(new Line(new Point(0, 3), Const.RIGHT, 15), 2));
        roads.add(new Road(new Line(new Point(3, 0), Const.DOWN, 80), 2));
        roads.add(new Road(new Line(new Point(10, 0), Const.DOWN, 80), 2));

        /*for(int x = 1; x < dimension.width; x += 4) {
            for(int y = 1; y < dimension.height; y += 4) {
                double rand = Math.random();
                if(rand < 0.1) {
                    buildings.add(new ResidentialBuilding(this, new Rectangle(x, y, 3, 3), 5000, "house", 3));
                } else if(rand < 0.2) {
                    buildings.add(new ResidentialBuilding(this, new Rectangle(x, y, 3, 3), 5000, "house", 2));
                } else if(rand < 0.5) {
                    buildings.add(new ResidentialBuilding(this, new Rectangle(x, y, 3, 3), 5000, "house", 1));
                } else {
                    buildings.add(new ResidentialBuilding(this, new Rectangle(x, y, 3, 3), 5000, "house", 0));
                }
            }
        }

        for(int x = 0; x < dimension.width; x += 4) {
            roads.add(new Road(new Line(new Point(x, 0), Const.DOWN, dimension.height), 2));
        }
        for(int y = 0; y < dimension.height; y += 4) {
            roads.add(new Road(new Line(new Point(0, y), Const.RIGHT, dimension.width), 2));
        }*/
    }

    @Override
    public String getName() {
        return name;
    }
    public Dimension getDimensions() {
        return this.dimension;
    }
    public Viewport getViewport() {
        return viewport;
    }
    public ArrayList<Building> getBuildings() {
        return this.buildings;
    }
    public void getBuilding(int id) {
    }
    public void addBuilding(Building building) {
        this.buildings.add(building);
    }
    
    @Override
    public void update() {
        repaint();
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); //required
        Dimension screenSize = super.getSize();

        // draw the background
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, screenSize.width, screenSize.height);


        // draw the gridlines
        g.setColor(Color.LIGHT_GRAY);
        Rectangle mappedGrid = viewport.mapToViewport(new Rectangle(0, 0, dimension.width, dimension.height));
        for(int currX = mappedGrid.x; currX <= mappedGrid.x + mappedGrid.width; currX += viewport.getScale()) {
            g.drawLine(currX, mappedGrid.y, currX, mappedGrid.y + mappedGrid.height);
        }
        for(int currY = mappedGrid.y; currY <= mappedGrid.y + mappedGrid.height; currY += viewport.getScale()) {
            g.drawLine(mappedGrid.x, currY, mappedGrid.x + mappedGrid.width, currY);
        }


        // draw the roads
        g.setColor(Road.COLOR);
        for(Road road: roads) {
            Line path = road.getPath();
            Rectangle rectPath = new Rectangle(path.startPoint.x, path.startPoint.y, path.direction[0]*path.length+1, path.direction[1]*path.length+1);
            rectPath = viewport.mapToViewport(rectPath);
            //System.out.println("(" + rectPath.x + ", " + rectPath.y + ") " + rectPath.width + " " + rectPath.height);
            g.fillRect(rectPath.x, rectPath.y, rectPath.width, rectPath.height);
        }
        

        // draw the buildings
        for(Building building: buildings) {
            Rectangle plot = viewport.mapToViewport(building.getPlot());
            //System.out.println(plot);
            if(viewport.isInViewport(plot)) {
                g.setColor(building.getBackGround());
                g.fillRect(plot.x, plot.y, plot.width, plot.height);
                g.setColor(Color.BLACK);
                g.drawRect(plot.x, plot.y, plot.width, plot.height);

                Sprite picture = building.getPicture(viewport.getScaleLevel());
                picture.setX(plot.x + (plot.width - picture.getWidth()) / 2);
                picture.setY(plot.y + (plot.height - picture.getHeight()) / 2);
                picture.draw(g);
            }
        }
    }

    public class GridMouseListener implements MouseListener {
        public void mouseClicked(MouseEvent e) {
            System.out.println("Mouse was clicked at (" + e.getX() + ", " + e.getY() + ")");
        }
        public void mousePressed(MouseEvent e) {     
            System.out.println("Mouse was pressed at (" + e.getX() + ", " + e.getY() + ")");
            Point mouseLocation = new Point(e.getX(), e.getY());
            mouseState.lastMouseDown = mouseLocation;
        }
        public void mouseReleased(MouseEvent e) {
            System.out.println("Mouse was released at (" + e.getX() + ", " + e.getY() + ")");
        }
        public void mouseEntered(MouseEvent e) {
            System.out.println("Mouse entered at (" + e.getX() + ", " + e.getY() + ")");
        }
        public void mouseExited(MouseEvent e) {
            System.out.println("Mouse exited at (" + e.getX() + ", " + e.getY() + ")");
        }
    }

    public class GridMouseMotionListener implements MouseMotionListener {
        public void mouseMoved(MouseEvent e) {
            //System.out.println("Mouse was moved to (" + e.getX() + ", " + e.getY() + ")");
        }
        public void mouseDragged(MouseEvent e) {
            Point mouseLoc = new Point(e.getX(), e.getY());
            //System.out.println("Mouse was dragged to (" + e.getX() + ", " + e.getY() + ")");
            if(!mouseLoc.equals(mouseState.lastMouseDown)) {
                viewport.translate(-(mouseLoc.x-mouseState.lastMouseDown.x), -(mouseLoc.y-mouseState.lastMouseDown.y));
                mouseState.lastMouseDown = mouseLoc;
            }
        }
    }

    public class MouseState {
        public Point lastMouseDown;
    }

    public static class Viewport {
        public static final int[] SCALES = {15, 25, 40, 60, 90};
        public static final int DEFAULT_SCALE_LEVEL = 2;
        private Grid grid;
        /**
         * The top left corner of the viewport
         */
        private int x;
        private int y;
        
        private int scaleLevel;
        /**
         * The width and height of each cell in pixels
         */
        private int scale;
        public Viewport(Grid grid) {
            this.grid = grid;
            x = 0;
            y = 0;
            setScaleLevel(DEFAULT_SCALE_LEVEL);
        }
        private void restrictViewportBounds() {
            x = Math.max(x, 0);
            y = Math.max(y, 0);
            Point bottomRightCorner = scaleToViewport(new Point(grid.dimension.width, grid.dimension.height));
            x = Math.min(x, bottomRightCorner.x - grid.getWidth());
            y = Math.min(y, bottomRightCorner.y - grid.getHeight());
        }
        public int getScale() {
            return scale;
        }
        public int getScaleLevel() {
            return scaleLevel;
        }
        private void setScaleLevel(int newLevel) {
            if(newLevel < 0) {
                newLevel = 0;
            } else if(newLevel > SCALES.length-1) {
                newLevel = SCALES.length-1;
            }
            int newScale = SCALES[newLevel];
            x = (int)((double)x/scale*newScale);
            y = (int)((double)y/scale*newScale);
            scaleLevel = newLevel;
            scale = newScale;
            restrictViewportBounds();
        }
        /**
         * Translates the viewport by the specified coordinates
         */
        public void translate(int dx, int dy) {
            x += dx;
            y += dy;
            restrictViewportBounds();
        }
        /**
         * Moves the top left corner of the viewport to the specified coordinates
         */
        public void moveTo(int newX, int newY) {
            x = newX;
            y = newY;
            restrictViewportBounds();
        }
        /**
         * Increases the scale level by 1, causing the viewport to zoom in
         */
        public void zoomIn() {
            setScaleLevel(scaleLevel+1);
        }
        /**
         * Decreases the scale level by 1, causing the viewport to zoom out
         */
        public void zoomOut() {
            setScaleLevel(scaleLevel-1);
        }
        /**
         * Resizes and translates a rectangle to map it with the viewport
         * @param rect The rectangle to be mapped
         * @return The mapped rectangle
         */
        public Rectangle mapToViewport(Rectangle rect) {
            return new Rectangle(rect.x*scale - x, rect.y*scale - y, rect.width*scale, rect.height*scale);
        }
        /**
         * Maps a point to the viewport
         * @param point The point to be mapped
         * @return The mapped point
         */
        public Point mapToViewport(Point point) {
            return new Point(point.x*scale - x, point.y*scale - y);
        }
        /**
         * Maps a line to the viewport
         * @param line The line to be mapped
         * @return The mapped line
         */
        public Line mapToViewport(Line line) {
            return new Line(mapToViewport(line.startPoint), line.direction, line.length*scale);
        }
        /**
         * Maps a distance to the viewport
         * @param distance The provided distance
         * @return The mapped distance
         */
        public int mapToViewport(int distance) {
            return distance*scale;
        }
        /**
         * Checks whether or not a rectangle is visible in the viewport
         * @param rect The rectangle to check
         */
        public boolean isInViewport(Rectangle rect) {
            Rectangle bounds = new Rectangle(grid.getWidth(), grid.getHeight());
            return (rect.intersects(bounds) || rect.contains(bounds));
        }
        /**
         * Scales a point to the viewport, without translating it
         * @param point The point to scale
         * @return The scaled point
         */
        public Point scaleToViewport(Point point) {
            return new Point(point.x*scale, point.y*scale);
        }
    }
}

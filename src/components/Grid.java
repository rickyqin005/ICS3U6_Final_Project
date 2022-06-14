package components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import gameobjects.Building;
import gameobjects.ResidentialBuilding;
import utility.Sprite;

public class Grid extends GameComponent {
    public static final Dimension DEFAULT_DIMENSIONS = new Dimension(60, 60);

    private Dimension dimension;
    private Viewport viewport;
    private ArrayList<Building> buildings = new ArrayList<Building>();
    MouseState mouseState;
    GridMouseListener mouseListener;
    GridMouseMotionListener mouseMotionListener;
    public Grid() {
        dimension = Grid.DEFAULT_DIMENSIONS;
        viewport = new Viewport();
        mouseState = new MouseState();
        mouseListener = new GridMouseListener();
        addMouseListener(mouseListener);
        mouseMotionListener = new GridMouseMotionListener();
        addMouseMotionListener(mouseMotionListener);

        //buildings.add(new ResidentialBuilding(this, new Dimension(3, 3), 5000, new Point(0, 0), ResidentialBuilding.pictures[0]));
        buildings.add(new ResidentialBuilding(this, new Dimension(3, 3), 5000, new Point(3, 0), ResidentialBuilding.pictures[1]));
        //buildings.add(new ResidentialBuilding(this, new Dimension(3, 3), 5000, new Point(9, 2), ResidentialBuilding.pictures[2]));
        //buildings.add(new ResidentialBuilding(this, new Dimension(3, 3), 5000, new Point(5, 2), ResidentialBuilding.pictures[3]));

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
        //System.out.println(mappedGrid);
        //Rectangle visibleGrid = mappedGrid.intersection(new Rectangle(0, 0, screenSize.width, screenSize.height));
        //System.out.println(visibleGrid);

        for(int currX = mappedGrid.x; currX <= mappedGrid.x + mappedGrid.width; currX += viewport.scale) {
            g.drawLine(currX, mappedGrid.y, currX, mappedGrid.y + mappedGrid.height);
        }
        //System.out.println("scale: " + viewport.scale);
        for(int currY = mappedGrid.y; currY <= mappedGrid.y + mappedGrid.height; currY += viewport.scale) {
            g.drawLine(mappedGrid.x, currY, mappedGrid.x + mappedGrid.width, currY);
        }
        //System.out.println("(" + viewport.x + ", " + viewport.y + ")");
        // draw the buildings
        for(Building building: buildings) {
            try {
            Point buildingCoordinate = viewport.mapToViewport(building.getLocation());
            //System.out.println(buildingCoordinate);
            Sprite buildingPicture = building.getPicture(viewport.scale);
            buildingPicture.setX(buildingCoordinate.x);
            buildingPicture.setY(buildingCoordinate.y);
            buildingPicture.draw(g);
            } catch (Exception ex) {}
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
        public void mouseMoved(MouseEvent e){
            //System.out.println("Mouse was moved to (" + e.getX() + ", " + e.getY() + ")");
        }
        public void mouseDragged(MouseEvent e){
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

    public class Viewport {
        private final int[] SCALES = {25, 40, 65, 100};
        private static final int DEFAULT_SCALE_LEVEL = 1;
        /**
         * The top left corner of the viewport
         */
        public int x;
        public int y;
        private int scaleLevel;
        /**
         * The width and height of each cell in pixels
         */
        public int scale;
        public Viewport() {
            x = 0;
            y = 0;
            setScaleLevel(DEFAULT_SCALE_LEVEL);
        }
        private void setScaleLevel(int newLevel) {
            if(newLevel < 0) {
                newLevel = 0;
            } else if(newLevel > SCALES.length-1) {
                newLevel = SCALES.length-1;
            }
            scaleLevel = newLevel;
            scale = SCALES[newLevel];
        }
        public void translate(int dx, int dy) {
            x += dx;
            y += dy;
        }
        public void moveTo(int newX, int newY) {
            x = newX;
            y = newY;
        }
        public void zoomIn() {
            setScaleLevel(scaleLevel+1);
        }
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
    }
}

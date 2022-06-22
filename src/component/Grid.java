package component;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JPanel;

import core.Game;
import core.Game.GameState;
import exception.NotEnoughCurrencyException;
import gameobject.building.*;
import gameobject.road.*;
import utility.Direction;
import utility.Line;

public class Grid extends JPanel {
    private static final Dimension DEFAULT_DIMENSIONS = new Dimension(200, 200);
    private static final Color DEFAULT_BACKGROUND_COLOR = Color.WHITE;
    public static final String NAME = "grid";
    private Game game;
    private Dimension dimension;
    private Viewport viewport;
    private User user;
    private ArrayList<Building> buildings;
    private RoadNetwork roadNetwork;
    private File savedFile;
    private GridMouseListener gridMouseListener;

    public Grid(Game game) {
        this.game = game;
        dimension = Grid.DEFAULT_DIMENSIONS;
        viewport = new Viewport(this);
        user = new User(this);
        buildings = new ArrayList<Building>();
        roadNetwork = new RoadNetwork(this);
        savedFile = null;
    }

    public Grid(Game game, File file) {
        this.game = game;
        dimension = Grid.DEFAULT_DIMENSIONS;
        viewport = new Viewport(this);
        user = new User(this);
        buildings = new ArrayList<Building>();
        roadNetwork = new RoadNetwork(this);
        savedFile = file;
        new GridFileReader(this);
    }

    public void setState(int newState) {
        if(gridMouseListener instanceof BuildingsListener) {
            ((BuildingsListener)gridMouseListener).setSelected(null);
        } else if(gridMouseListener instanceof RoadsListener) {
            ((RoadsListener)gridMouseListener).setPending(null);
        }

        removeMouseListener(gridMouseListener);
        removeMouseMotionListener(gridMouseListener);
        removeMouseWheelListener(gridMouseListener);

        if(newState == GameState.BUILDINGS) {
            gridMouseListener = new BuildingsListener(this);
        } else if(newState == GameState.ROADS) {
            gridMouseListener = new RoadsListener();
        } else {
            gridMouseListener = new GamePlayListener();
        }
        
        addMouseListener(gridMouseListener);
        addMouseMotionListener(gridMouseListener);
        addMouseWheelListener(gridMouseListener);
    }

    public Rectangle toRectangle() {
        return new Rectangle(new Point(0, 0), dimension);
    }

    public Point getCenter() {
        return new Point(dimension.width/2, dimension.height/2);
    }

    @Override
    public String getName() {
        return NAME;
    }

    public Dimension getDimensions() {
        return this.dimension;
    }

    public Viewport getViewport() {
        return viewport;
    }

    public User getUser() {
        return user;
    }

    public ArrayList<Building> getBuildings() {
        return this.buildings;
    }

    /**
     * Returns the building that occupies a specified cell.
     * @param point The cell to check, expressed using its top left corner.
     * @param buildings The list of buildings.
     * @return The building that occupies that cell, or null if no buildings occupy that cell.
     */
    public Building getBuilding(Point point) {
        Rectangle pointRect = new Rectangle(point.x, point.y, 1, 1);
        for(Building building: buildings) {
            if(building.getPlot().contains(pointRect)) {
                return building;
            }
        }
        return null;
    }

    public boolean hasBuilding(Point point) {
        return (getBuilding(point) != null);
    }

    public void addBuilding(Building building) {
        try {
            user.spendCurrency(building.getCost());
            buildings.add(building);
        } catch(NotEnoughCurrencyException e) {
            e.showErrorMessage();
        }
    }

    public void demolishBuilding(Building building) {
        buildings.remove(building);
    }

    public RoadNetwork getRoadNetwork() {
        return this.roadNetwork;
    }

    public void addRoad(Road road) {
        try {
            user.spendCurrency(road.getCost());
            roadNetwork.addRoad(road);
        } catch(NotEnoughCurrencyException e) {
            e.showErrorMessage();
        }
    }

    public GridMouseListener getGridMouseListener() {
        return gridMouseListener;
    }

    /**
     * Returns the file that the game is saved to.
     * @return The file the game is saved to, or null if there is no file.
     */
    public File getSavedFile() {
        return savedFile;
    }

    @Override
    public void paintComponent(Graphics g) {
        user.update();
        
        super.paintComponent(g); //required

        // draw the background
        g.setColor(DEFAULT_BACKGROUND_COLOR);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        // draw the gridlines
        g.setColor(Color.LIGHT_GRAY);
        Rectangle mappedGrid = viewport.mapToViewport(new Rectangle(0, 0, dimension.width, dimension.height));
        for(int currX = mappedGrid.x; currX <= mappedGrid.x + mappedGrid.width; currX += viewport.getScale()) {
            g.drawLine(currX, mappedGrid.y, currX, mappedGrid.y + mappedGrid.height);
        }
        for(int currY = mappedGrid.y; currY <= mappedGrid.y + mappedGrid.height; currY += viewport.getScale()) {
            g.drawLine(mappedGrid.x, currY, mappedGrid.x + mappedGrid.width, currY);
        }

        roadNetwork.draw(g, viewport);

        for(Building building: buildings) {
            if(!building.isSelected()) {
                building.draw(g, viewport);
            }
        }

        if(gridMouseListener != null) {
            if(gridMouseListener instanceof BuildingsListener) {
                Building pendingBuilding = ((BuildingsListener)gridMouseListener).getPending();
                Building selectedBuilding = ((BuildingsListener)gridMouseListener).getSelected();
                if(pendingBuilding != null) {
                    pendingBuilding.draw(g, viewport);
                }
                if(selectedBuilding != null) {
                    selectedBuilding.draw(g, viewport);
                }
            } else if(gridMouseListener instanceof RoadsListener) {
                Road pendingRoad = ((RoadsListener)gridMouseListener).getPending();
                if(pendingRoad != null) {
                    pendingRoad.drawArea(g, viewport);
                    pendingRoad.drawOutline(g, viewport);
                }
            }
        }
        
        
    }

    public abstract class GridMouseListener implements MouseListener, MouseMotionListener, MouseWheelListener {}
    public class GamePlayListener extends GridMouseListener {
        public Point lastMouseDown;
        public void mouseClicked(MouseEvent e) {
            Point point = viewport.mapToGrid(e.getPoint());
            if(hasBuilding(point)) {
                game.setGameState(GameState.BUILDINGS, null);
                ((BuildingsListener)gridMouseListener).setSelected(getBuilding(point));
            }
        }
        public void mousePressed(MouseEvent e) {
            Point mouseLocation = new Point(e.getX(), e.getY());
            lastMouseDown = mouseLocation;
        }
        public void mouseReleased(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
        public void mouseMoved(MouseEvent e) {}
        public void mouseDragged(MouseEvent e) {
            Point mouseLoc = new Point(e.getX(), e.getY());
            if(!mouseLoc.equals(lastMouseDown)) {
                viewport.translate(-(mouseLoc.x-lastMouseDown.x), -(mouseLoc.y-lastMouseDown.y));
                lastMouseDown = mouseLoc;
            }
        }
        public void mouseWheelMoved(MouseWheelEvent e) {
            if(e.getPreciseWheelRotation() > 0) {
                viewport.zoomOut(e.getPoint());
            } else if(e.getPreciseWheelRotation() < 0) {
                viewport.zoomIn(e.getPoint());
            }
        }
    }

    public class BuildingsListener extends GamePlayListener {
        private Grid grid;
        private Building pending;
        private Building selected;
        private boolean selectedMovable;

        public BuildingsListener(Grid grid) {
            this.grid = grid;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            Point point = viewport.mapToGrid(e.getPoint());
            if(pending != null) {
                confirmPending();
                setPending(null);
            } else {
                if(selectedMovable) {
                    if(selected.isValidLocation()) {
                        selected.deselect();
                        selected.moveTo(point);
                    }
                    setSelectedMovable(false);
                }
                setSelected(getBuilding(point));
            }
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            Point point = viewport.mapToGrid(e.getPoint());
            if(pending != null) {
                pending.moveTo(point);
                pending.setValidityBackgroundColor();
            } else {
                if(selected != null && getSelectedMovable()) {
                    selected.moveTo(point);
                    selected.setValidityBackgroundColor();
                }
            }
        }

        /**
         * Tries to turn the pending building into an actual building.
         * @return Whether or not the action was successful.
         */
        public boolean confirmPending() {
            if(pending.isValidLocation()) {
                pending.deselect();
                addBuilding(pending);
                return true;
            }
            return false;
        }

        /**
         * Returns the current pending building, or null if there is no pending building.
         */
        public Building getPending() {
            return pending;
        }

        /**
         * Sets the pending building to the specified building.
         * If the specified building is null, the pending building is set to null.
         * Note that only one of pending and selected can be non-null at any moment.
         * @param template
         */
        public void setPending(TemplateBuilding template) {
            if(pending != null) {
                pending.deselect();
            }
            if(template == null) {
                pending = null;
            } else {
                setSelected(null);
                Point initialLocation = viewport.mapToGrid(new Point(Integer.MAX_VALUE,Integer.MAX_VALUE));
                if(template instanceof TemplateResidentialBuilding) {
                    pending = new ResidentialBuilding((TemplateResidentialBuilding)template, grid, initialLocation);
                } else if(template instanceof TemplateAmenity) {
                    pending = new Amenity((TemplateAmenity)template, grid, initialLocation);
                }
            }
        }
    
        public Building getSelected() {
            return selected;
        }
    
        /**
         * Sets the currently selected building to the specified building.
         * If the specified building is null, then no building will be selected.
         * @param newBuilding The specified building.
         */
        public void setSelected(Building newBuilding) {
            if(selected != null) {
                selected.deselect();
            }
            setSelectedMovable(false);
            if(newBuilding == null) {
                selected = null;
            } else {
                setPending(null);
                selected = newBuilding;
                selected.select();
            }
        }

        public boolean getSelectedMovable() {
            return selectedMovable;
        }
    
        public void setSelectedMovable(boolean newVal) {
            selectedMovable = newVal;
        }
    }

    public class RoadsListener extends GamePlayListener {
        private Road pending;
        private Point pendingStart;

        @Override
        public void mouseClicked(MouseEvent e) {
            if(pending == null) {
                pendingStart = viewport.mapToGrid(e.getPoint());
                pending = new Road(roadNetwork, new Line(pendingStart, Direction.RIGHT, 1));
                pending.setValidityBackgroundColor();
            } else {
                if(pending.isValidLocation()) {
                    pending.setBackgroundColor(Road.DEFAULT_BACKGROUND_COLOR);
                    addRoad(pending);
                }
                pending = null;
                pendingStart = null;
            }
        }
        
        @Override
        public void mouseMoved(MouseEvent e) {
            if(pending != null && pendingStart != null) {
                Point startPointScreen = viewport.mapToViewport(pendingStart);
                Point currPointScreen = e.getPoint();
                Point endPoint;
                if(Math.abs(startPointScreen.x-currPointScreen.x) <= Math.abs(startPointScreen.y-currPointScreen.y)) {
                    endPoint = viewport.mapToGrid(new Point(startPointScreen.x, currPointScreen.y));
                } else {
                    endPoint = viewport.mapToGrid(new Point(currPointScreen.x, startPointScreen.y));
                }
                pending = new Road(roadNetwork, new Line(pendingStart, endPoint));
                pending.setValidityBackgroundColor();
            }
        }

        public Road getPending() {
            return pending;
        }

        public void setPending(Road road) {
            pending = road;
        }
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
            setScaleLevel(DEFAULT_SCALE_LEVEL, new Point(0,0));
            this.moveTo(mapToViewport(grid.getCenter()));
        }
        private void restrictViewportBounds() {
            x = Math.max(x, 0);
            y = Math.max(y, 0);
            Point bottomRightCorner = scaleToViewport(new Point(grid.dimension.width, grid.dimension.height));
            x = Math.min(x, bottomRightCorner.x - grid.getWidth());
            y = Math.min(y, bottomRightCorner.y - grid.getHeight());
        }
        public Point getCenter() {
            return new Point(grid.getWidth()/2, grid.getHeight()/2);
        }
        public int getScale() {
            return scale;
        }
        public int getScaleLevel() {
            return scaleLevel;
        }
        private void setScaleLevel(int newLevel, Point focalPoint) {
            if(newLevel < 0 || newLevel > SCALES.length-1) {
                return;
            }
            int newScale = SCALES[newLevel];
            focalPoint = translateToViewport(focalPoint);
            Point newFocalPoint = new Point();
            newFocalPoint.x = (int)((double)focalPoint.x/scale*newScale);
            newFocalPoint.y = (int)((double)focalPoint.y/scale*newScale);
            x += newFocalPoint.x - focalPoint.x;
            y += newFocalPoint.y - focalPoint.y;
            scaleLevel = newLevel;
            scale = newScale;
            restrictViewportBounds();
        }
        /**
         * Translates the viewport by the specified coordinates
         * @param dx The amount to translate in the X direction
         * @param dy The amount to translate in the Y direction
         */
        public void translate(int dx, int dy) {
            x += dx;
            y += dy;
            restrictViewportBounds();
        }
        /**
         * Moves the top left corner of the viewport to the specified coordinates
         * @param newX The new X coordinate
         * @param newY The new Y coordinate
         */
        public void moveTo(int newX, int newY) {
            x = newX;
            y = newY;
            restrictViewportBounds();
        }

        /**
         * Moves the top left corner of the viewport to the specified coordinates.
         * @param point The point to move the top left corner to.
         */
        public void moveTo(Point newPoint) {
            moveTo(newPoint.x, newPoint.y);
        }

        /**
         * Increases the scale level by 1, causing the viewport to zoom in
         * @param focalPoint The point that the zoom is centered on (ie the point that doesn't move).
         */
        public void zoomIn(Point focalPoint) {
            setScaleLevel(scaleLevel+1, focalPoint);
        }
        /**
         * Decreases the scale level by 1, causing the viewport to zoom out
         * @param focalPoint The point that the zoom is centered on (ie the point that doesn't move).
         */
        public void zoomOut(Point focalPoint) {
            setScaleLevel(scaleLevel-1, focalPoint);
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
         * Maps a point to the viewport.
         * @param point The point to be mapped.
         */
        public Point mapToViewport(Point point) {
            return new Point(point.x*scale - x, point.y*scale - y);
        }

        /**
         * Maps a line to the viewport
         * @param line The line to be mapped
         */
        public Line mapToViewport(Line line) {
            return new Line(mapToViewport(line.start), line.direction, line.distance*scale);
        }

        /**
         * Maps a distance to the viewport
         * @param distance The distance to be mapped
         */
        public int mapToViewport(int distance) {
            return distance*scale;
        }

        public Point translateToViewport(Point point) {
            return new Point(point.x + x, point.y + y);
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
        /**
         * Maps a coordinate on the viewport to the coordinates of the grid.
         * @param point The point to map
         * @return The mapped point
         */
        public Point mapToGrid(Point point) {
            return new Point((x+point.x) / scale, (y+point.y) / scale);
        }
    }
    
    public static class User {
        private static final int DEFAULT_STARTING_BALANCE = 100000;
        private Grid grid;
        private int balance;
        private long lastTaxAccumulation;
        private double accumulatedTax;

        public User(Grid grid) {
            this.grid = grid;
            balance = DEFAULT_STARTING_BALANCE;
            lastTaxAccumulation = System.currentTimeMillis();
            accumulatedTax = 0;
        }

        public User(Grid grid, int startingBalance, long lastTaxAccumulation, double accumulatedTax) {
            this.grid = grid;
            balance = startingBalance;
            lastTaxAccumulation = System.currentTimeMillis();
            accumulatedTax = 0;
        }

        /**
         * @param grid The grid the user is playing in.
         * @param args {startingBalance, lastTaxAccumulation, accumulatedTax}
         */
        public User(Grid grid, String[] args) {
            this.grid = grid;
            balance = Integer.parseInt(args[0]);
            lastTaxAccumulation = Long.parseLong(args[1]);
            accumulatedTax = Double.parseDouble(args[2]);
        }

        @Override
        public String toString() {
            return balance + "\t" + lastTaxAccumulation + "\t" + accumulatedTax;
        }

        /**
         * Returns the balance of the user account.
         */
        public int getCurrency() {
            return balance;
        }

        /**
         * Adds currency to the balance.
         */
        public void addCurrency(int amount) {
            balance += amount;
            System.out.println("balance was increased to " + balance);
        }

        /**
         * Spends currency, causing the balance to decrease.
         * @param amount The amount to spend.
         * @throws NotEnoughCurrencyException If the amount spent exceeds the balance and cancels the transaction.
         */
        public void spendCurrency(int amount) throws NotEnoughCurrencyException {
            if(amount > balance) {
                throw new NotEnoughCurrencyException();
            } else {
                balance -= amount;
            }
        }

        /**
         * Updates the amount of tax that can be collected.
         */
        public void accumulateTax() {
            long currentTime = System.currentTimeMillis();
            long timeElapsed = currentTime - lastTaxAccumulation;
            accumulatedTax += (double)timeElapsed*ResidentialBuilding.getTaxRate(grid.buildings)/ResidentialBuilding.TAX_RATE_TIME_INVERVAL;
            lastTaxAccumulation = currentTime;
        }

        /**
         * Checks the amount of currency that can immediately be collected as tax.
         * @return The amount of currency ready to be collected as tax.
         */
        public int checkCollectableTax() {
            return (int)accumulatedTax;
        }

        /**
         * Collects the currency that can be collected as tax.
         * @return The amount of currency to collect.
         */
        public int getCollectableTax() {
            int tax = (int)accumulatedTax;
            accumulatedTax -= tax;
            return tax;
        }

        public void update() {
            accumulateTax();
        }
    }

    public static class GridFileConstants {
        public static final String ARGS_DELIMITER = "\t";
        public static final String END_OF_BUILDINGS = "........";
        public static final String END_OF_ROADS = "............";
    }
    public class GridFileReader {
        private Scanner fileReader;
        public GridFileReader(Grid grid) {
            try {
                fileReader = new Scanner(savedFile);
                boolean reachedEndOfBuildings = false;
                boolean reachedEndOfRoads = false;

                // read building data
                while(!reachedEndOfBuildings) {
                    String line = fileReader.nextLine();
                    if(line.equals(GridFileConstants.END_OF_BUILDINGS)) {
                        reachedEndOfBuildings = true;
                    } else {
                        String[] args = line.split(GridFileConstants.ARGS_DELIMITER);
                        if(args[0].equals("ResidentialBuilding")) {
                            buildings.add(new ResidentialBuilding(args, grid));
                        } else if(args[0].equals("Amenity")) {
                            buildings.add(new Amenity(args, grid));
                        }
                    }
                }

                // read road data
                while(!reachedEndOfRoads) {
                    String line = fileReader.nextLine();
                    if(line.equals(GridFileConstants.END_OF_ROADS)) {
                        reachedEndOfRoads = true;
                    } else {
                        String[] args = line.split(GridFileConstants.ARGS_DELIMITER);
                        roadNetwork.addRoad(new Road(roadNetwork, args));
                    }
                }

                // read user info
                String[] args = fileReader.nextLine().split(GridFileConstants.ARGS_DELIMITER);
                user = new User(grid, args);

                fileReader.close();
            } catch(Exception e) {}
        }
    }
    
    public class GridFileWriter {
        private FileWriter fileWriter;
        public GridFileWriter(File file) {
            savedFile = file;
            try {
                fileWriter = new FileWriter(savedFile);

                // write building data
                for(Building building: buildings) {
                    fileWriter.write(building.toString());
                    fileWriter.write("\n");
                }
                fileWriter.write(GridFileConstants.END_OF_BUILDINGS);
                fileWriter.write("\n");

                // write road data
                for(Road road: roadNetwork.getRoads()) {
                    fileWriter.write(road.toString());
                    fileWriter.write("\n");
                }
                fileWriter.write(GridFileConstants.END_OF_ROADS);
                fileWriter.write("\n");

                fileWriter.write(user.toString());
                fileWriter.write("\n");

                fileWriter.close();
            } catch(Exception e) {}
        }
    }
}

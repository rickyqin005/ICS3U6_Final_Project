package core;

import java.io.File;

import javax.swing.JComponent;
import javax.swing.JFrame;

import component.Grid;
import screen.*;

public class Game extends JFrame {
    private static final String NAME = "SimCity";
    public static final String FILE_EXTENSION = "simcity";
    private static final int FRAMES_PER_SECOND = 40;
    private Grid grid;
    private GameScreen currScreen;

    public Game(int width, int height) {
        super(NAME);
        super.setSize(width, height);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setGameState(GameState.MAIN_MENU, null);

        this.setVisible(true);
        this.startLoop();
    }

    public class GameState {
        public static final int MAIN_MENU = 0;
        public static final int GAMEPLAY = 1;
        public static final int SETTINGS = 2;
        public static final int ROADS = 3;
        public static final int BUILDINGS = 4;
        public static final int ANALYTICS = 5;
    }

    private void startLoop() {
        while(true) {
            if(currScreen != null) {
                currScreen.updateState();
            }
            validate();
            if(currScreen != null) {
                currScreen.updateGraphics();
            }
            // Delay to match the desired FPS
            try {
                Thread.sleep(1000 / Game.FRAMES_PER_SECOND);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Adds the specified component to the screen.
     * @param component The component to add.
     * @param constraints The layout constraints.
     */
    public void addGameComponent(JComponent component, Object constraints) {
        add(component, constraints);
        System.out.println("Added " + component.getName() + " @" + component.hashCode());
    }

    /**
     * Removes the specified component from the screen.
     * @param component The component to remove.
     */
    public void removeGameComponent(JComponent component) {
        getContentPane().remove(component);
        System.out.println("Removed " + component.getName() + " @" + component.hashCode());
    }

    public Grid getGrid() {
        return grid;
    }

    public void setGrid(Grid newGrid) {
        grid = newGrid;
    }

    public int getGameState() {
        return currScreen.getState();
    }
    
    public void setGameState(int newState, File savedGameFile) {
        if(currScreen != null) {
            currScreen.exitScreen(newState);
        }

        if(newState == GameState.MAIN_MENU) {
            currScreen = new MainMenuScreen(this);
        } else if(newState == GameState.GAMEPLAY) {
            if(savedGameFile != null) {
                currScreen = new GameplayScreen(this, savedGameFile);
            } else {
                currScreen = new GameplayScreen(this);
            }
        } else if(newState == GameState.SETTINGS) {
            currScreen = new SettingsScreen(this, currScreen.getState());
        } else if(newState == GameState.ROADS) {
            currScreen = new AddRoadsScreen(this);
        } else if(newState == GameState.BUILDINGS) {
            currScreen = new BuildingsScreen(this);
        } else if(newState == GameState.ANALYTICS) {
            currScreen = new AnalyticsScreen(this);
        }
        validate();
        repaint();
    }
}
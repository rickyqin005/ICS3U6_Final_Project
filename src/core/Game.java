package core;

import java.util.ArrayList;
import javax.swing.JFrame;

import component.Updatable;
import screen.*;

public class Game extends JFrame {
    private static final int FRAMES_PER_SECOND = 40;
    private ArrayList<Updatable> gameComponents;
    private GameScreen currScreen;
    public Game(String title, int width, int height) {
        super(title);
        super.setSize(width, height);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        gameComponents = new ArrayList<Updatable>();
        setGameState(GameState.MAIN_MENU);
        // setGameState(GameState.GAMEPLAY);

        this.setVisible(true);
        this.startLoop();
    }

    public class GameState {
        public static final int MAIN_MENU = 0;
        public static final int GAMEPLAY = 1;
        public static final int SETTINGS = 2;
        public static final int ROADS = 3;
        public static final int BUILDINGS = 4;
        public static final int AMENITIES = 5;
        public static final int ANALYTICS = 6;
    }

    private void startLoop() {
        while(true) {
            currScreen.update();
            // Delay to match the desired FPS
            try {
                Thread.sleep(1000 / Game.FRAMES_PER_SECOND);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public ArrayList<Updatable> getGameComponents() {
        return gameComponents;
    }
    public Updatable getGameComponentByName(String name) {
        for(Updatable component: gameComponents) {
            if(component.getName().equals(name)) {
                return component;
            }
        }
        return null;
    }
    public void addGameComponent(Updatable component) {
        gameComponents.add(component);
    }
    public void removeGameComponent(Updatable component) {
        for(int i = 0; i < gameComponents.size(); i++) {
            if(gameComponents.get(i) == component) {
                gameComponents.remove(i);
                return;
            }
        }
    }
    public void setGameState(int newState) {
        if(currScreen != null) {
            this.getContentPane().removeAll();
            currScreen.exitScreen();
        }

        if(newState == GameState.MAIN_MENU) {
            currScreen = new MainMenuScreen(this);
        } else if(newState == GameState.GAMEPLAY) {
            currScreen = new GameplayScreen(this);
        } else if(newState == GameState.BUILDINGS) {
            currScreen = new BuildingsScreen(this);
        } else if(newState == GameState.ANALYTICS) {
            currScreen = new AnalyticsScreen(this);
        }
        this.validate();
        this.repaint();
    }
}
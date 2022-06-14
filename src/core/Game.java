package core;
import javax.swing.JFrame;

import screens.GameScreen;
import screens.GameplayScreen;

public class Game extends JFrame {
    private static final int FRAMES_PER_SECOND = 50;
    private GameScreen currScreen;

    public Game(String title, int width, int height) {
        super(title);
        super.setSize(width, height);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setGameState(GameState.GAMEPLAY);

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
    }
    private void startLoop() {
        while (true) {
            currScreen.update();

            // Delay to match the desired FPS
            try {
                Thread.sleep(1000 / Game.FRAMES_PER_SECOND);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    /*public void addToWindow(Screen screen) {
        super.add(screen);
        super.setVisible(true);
    }*/

    public void setGameState(int newState) {
        if(newState == GameState.GAMEPLAY) {
            currScreen = new GameplayScreen(this);
        } else if(newState == GameState.SETTINGS) {

        }
    }
}
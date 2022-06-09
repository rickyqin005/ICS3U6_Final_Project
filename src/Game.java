import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Game extends JFrame {
    private static final int FRAMES_PER_SECOND = 50;
    private User user;
    /**
     * A list of screens to be rendered. Items that are earlier in the list are rendered first.
     */
    private ArrayList<Screen> screens = new ArrayList<Screen>();

    public Game(String title, int width, int height) {
        super(title);
        super.setSize(width, height);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        user = new User();

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JButton addBuildingButton = new JButton("Add Building");
        gbc.weightx = 0.5;
        gbc.weighty = 0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(addBuildingButton, gbc);

        JButton settingsButton = new JButton("Settings");
        gbc.weightx = 0.5;
        gbc.weighty = 0;
        gbc.gridx = 1;
        gbc.gridy = 0;
        this.add(settingsButton, gbc);
        
        Grid grid = new Grid();
        screens.add(grid);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(grid, gbc);


        this.setVisible(true);
        this.startLoop();
    }

    private void startLoop() {
        while (true) {
            for(Screen screen: screens) {
                screen.update();
            }

            // Delay to match the desired FPS
            try {
                Thread.sleep(1000 / Game.FRAMES_PER_SECOND);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void addToWindow(Screen screen) {
        super.add(screen);
        super.setVisible(true);
    }
}
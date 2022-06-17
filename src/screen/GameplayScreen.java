package screen;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;


import component.ComponentList;
import component.Grid;
import component.UserPanel;
import component.button.*;
import core.Game;
import utility.Direction;

public class GameplayScreen extends GameScreen {
    private Grid grid;
    private UserPanel userPanel;
    private ComponentList sideBar;

    public GameplayScreen(Game game) {
        super(game);

        // retrieve the JPanels
        grid = (Grid)getGameComponentByName("grid", new Grid("grid"));
        userPanel = (UserPanel)getGameComponentByName("userPanel", new UserPanel("userPanel", grid));

        final JButton[] buttons = {new SettingsButton(), new ZoomInButton(grid), new ZoomOutButton(grid), new RoadButton(), 
            new BuildingButton(game), new AmenityButton(), new AnalyticsButton(game), new CollectTaxButton(grid)};
        sideBar = (ComponentList)getGameComponentByName("sideBar", new ComponentList("sideBar", buttons, Direction.DOWN, 0, 0));

        // add the JPanels to the screen
        game.setLayout(new GridBagLayout());
        GridBagConstraints gbc;
        
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 0;
        gbc.weighty = 0;
        game.add(userPanel, gbc);

        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        gbc.weighty = 1;
        game.add(sideBar, gbc);
        
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        game.add(grid, gbc);
    }
    public void update() {
        grid.update();
        userPanel.repaint();
        sideBar.update();
        System.out.println("updated gameplay screen");
    }
}

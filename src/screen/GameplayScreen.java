package screen;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;


import component.ButtonList;
import component.Grid;
import component.UserPanel;
import component.button.*;
import core.Game;
import utility.Const;

public class GameplayScreen extends GameScreen {
    private UserPanel userPanel;
    private Grid grid;
    private ButtonList sidebar;

    public GameplayScreen(Game game) {
        super(game);

        // retrieve the JPanels
        this.userPanel = (UserPanel)getGameComponentByName("userpanel", new UserPanel("userpanel"));
        this.grid = (Grid)getGameComponentByName("grid", new Grid("grid"));

        final JButton[] buttons = {new SettingsButton(), new ZoomInButton(grid), new ZoomOutButton(grid), new RoadButton(), 
            new BuildingButton(game), new AmenityButton(), new AnalyticsButton(game), new CollectTaxButton(grid)};
        this.sidebar = (ButtonList)getGameComponentByName("sidebar", new ButtonList("sidebar", buttons, Const.DOWN));

        // add the JPanels to the screen
        game.setLayout(new GridBagLayout());
        GridBagConstraints gbc;
        
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 1;
        game.add(userPanel, gbc);

        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        gbc.weighty = 1;
        game.add(this.sidebar, gbc);
        
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        game.add(grid, gbc);
    }
    
}

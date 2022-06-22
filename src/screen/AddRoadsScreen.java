package screen;

import java.awt.GridBagLayout;

import javax.swing.JComponent;

import java.awt.GridBagConstraints;

import component.ComponentList;
import component.UserPanel;
import component.button.*;
import core.Game;
import core.Game.GameState;
import utility.Direction;

public class AddRoadsScreen extends GameScreen {
    public static final int STATE = GameState.ROADS;
    private UserPanel userPanel;
    private ComponentList sideBar;

    public AddRoadsScreen(Game currGame) {
        super(currGame);

        game.getGrid().setState(STATE);
        userPanel = new UserPanel(game.getGrid());

        final JComponent[] buttons = {new SettingsButton(game), new ExitButton(game, GameState.GAMEPLAY, false), 
                new ZoomInButton(game.getGrid()), new ZoomOutButton(game.getGrid())};
        sideBar = new ComponentList("sideBar", buttons, Direction.DOWN, 0, GridBagConstraints.NORTH, GridBagConstraints.NONE);
        
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
        game.addGameComponent(userPanel, gbc);

        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        game.addGameComponent(sideBar, gbc);

        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        game.addGameComponent(game.getGrid(), gbc);
    }

    @Override
    public int getState() {
        return STATE;
    }
    
    @Override
    public void updateState() {

    }
    
    @Override
    public void updateGraphics() {
        userPanel.repaint();
        sideBar.repaint();
        game.getGrid().repaint();
    }
    
    @Override
    public void exitScreen(int newState) {
        game.removeGameComponent(userPanel);
        game.removeGameComponent(sideBar);
        game.removeGameComponent(game.getGrid());
    }
}

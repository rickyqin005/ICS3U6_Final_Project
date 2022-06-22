package screen;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.File;

import javax.swing.JComponent;

import component.ComponentList;
import component.Grid;
import component.UserPanel;
import component.button.*;
import core.Game;
import core.Game.GameState;
import utility.Direction;

public class GameplayScreen extends GameScreen {
    public static final int STATE = GameState.GAMEPLAY;
    private UserPanel userPanel;
    private ComponentList sideBar;

    private JComponent[] getButtons(Game game, Grid grid) {
        return new JComponent[]{new SettingsButton(game), new ExitButton(game, GameState.MAIN_MENU, true), 
            new ZoomInButton(grid), new ZoomOutButton(grid), new RoadButton(game), new BuildingButton(game), 
            new AnalyticsButton(game), new CollectTaxButton(grid)};
    }
    
    private ComponentList getSideBar(Game game, Grid grid) {
        return new ComponentList("sideBar", getButtons(game, grid), Direction.DOWN, 0, GridBagConstraints.NORTH, GridBagConstraints.NONE);
    }
    public GameplayScreen(Game currGame) {
        super(currGame);

        if(game.getGrid() == null) {
            game.setGrid(new Grid(game));
        }
        game.getGrid().setState(STATE);
        userPanel = new UserPanel(game.getGrid());
        sideBar = getSideBar(game, game.getGrid());

        layoutComponents();
    }

    public GameplayScreen(Game currGame, File savedGameFile) {
        super(currGame);

        game.setGrid(new Grid(game, savedGameFile));
        game.getGrid().setState(STATE);
        userPanel = new UserPanel(game.getGrid());
        sideBar = getSideBar(game, game.getGrid());

        layoutComponents();
    }

    /**
     * Adds the JComponents to the screen
     */
    private void layoutComponents() {
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
        if(game.getGrid() != null) {
            game.getGrid().repaint();
        }
        userPanel.repaint();
        sideBar.repaint();
    }
    
    @Override
    public void exitScreen(int newState) {
        game.removeGameComponent(userPanel);
        game.removeGameComponent(sideBar);
        game.removeGameComponent(game.getGrid());
        
        if(newState == GameState.MAIN_MENU) {
            game.setGrid(null);
        }
    }

    
}

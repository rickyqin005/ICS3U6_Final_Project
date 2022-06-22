package screen;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import javax.swing.JComponent;

import component.ComponentList;
import component.ModifyBuildingsPanel;
import component.UserPanel;
import component.button.*;
import core.Game;
import core.Game.GameState;
import gameobject.building.TemplateAmenity;
import gameobject.building.TemplateBuilding;
import gameobject.building.TemplateResidentialBuilding;
import utility.Direction;

public class BuildingsScreen extends GameScreen {
    public static final int STATE = GameState.BUILDINGS;
    private UserPanel userPanel;
    private ComponentList sideBar;
    private ModifyBuildingsPanel buildingSelection;

    public BuildingsScreen(Game currGame) {
        super(currGame);

        game.getGrid().setState(STATE);
        userPanel = new UserPanel(game.getGrid());

        final JComponent[] buttons = {new SettingsButton(game), new ExitButton(game, GameState.GAMEPLAY, false), 
                new ZoomInButton(game.getGrid()), new ZoomOutButton(game.getGrid())};
        sideBar = new ComponentList("sideBar", buttons, Direction.DOWN, 0, GridBagConstraints.NORTH, GridBagConstraints.NONE);
        
        final ArrayList<TemplateBuilding> buildingList = new ArrayList<TemplateBuilding>();
        buildingList.addAll(TemplateResidentialBuilding.TEMPLATES);
        buildingList.addAll(TemplateAmenity.TEMPLATES);
        buildingSelection = new ModifyBuildingsPanel("buildingSelection", buildingList, game.getGrid());
        
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
        gbc.gridwidth = 1;
        gbc.weightx = 1;
        gbc.weighty = 2;
        game.addGameComponent(game.getGrid(), gbc);

        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.weightx = 1;
        gbc.weighty = 1;
        game.addGameComponent(buildingSelection, gbc);
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
        game.getGrid().repaint();
        userPanel.repaint();
        sideBar.repaint();
        buildingSelection.repaint();
    }
    
    @Override
    public void exitScreen(int newState) {
        game.removeGameComponent(userPanel);
        game.removeGameComponent(sideBar);
        game.removeGameComponent(game.getGrid());
        game.removeGameComponent(buildingSelection);
    }
}

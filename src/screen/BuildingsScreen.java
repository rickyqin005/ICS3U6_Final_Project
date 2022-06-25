package screen;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import component.ComponentList;
import component.ModifyBuildingsPanel;
import component.UserPanel;
import component.button.*;
import core.Game;
import core.Updatable;
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

    private Updatable[] getButtons(Game game) {
        final Updatable[] buttons = {new ExitButton(game, GameState.GAMEPLAY, false), new ZoomInButton(game.getGrid()), 
                new ZoomOutButton(game.getGrid()), new RoadButton(game), new BuildingButton(game), new CollectTaxButton(game.getGrid())};
        ((BuildingButton)buttons[4]).setEnabled(false);
        return buttons;
    }

    public BuildingsScreen(Game currGame) {
        super(currGame);

        game.getGrid().setState(STATE);
        userPanel = new UserPanel(game.getGrid());

        sideBar = new ComponentList("sideBar", getButtons(game), Direction.DOWN, 0, GridBagConstraints.NORTH, GridBagConstraints.NONE);
        
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
        game.addToJFrame(userPanel, gbc);

        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        game.addToJFrame(sideBar, gbc);
        
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 1;
        gbc.weighty = 6;
        game.addToJFrame(game.getGrid(), gbc);

        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.weightx = 1;
        gbc.weighty = 1;
        game.addToJFrame(buildingSelection, gbc);
    }

    @Override
    public int getState() {
        return STATE;
    }

    @Override
    public void update() {
        game.getGrid().update();
        userPanel.update();
        sideBar.update();
        buildingSelection.update();
    }
    
    @Override
    public void exitScreen(int newState) {
        game.removeFromJFrame(userPanel);
        game.removeFromJFrame(sideBar);
        game.removeFromJFrame(game.getGrid());
        game.removeFromJFrame(buildingSelection);
    }
}

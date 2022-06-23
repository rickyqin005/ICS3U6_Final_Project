package component;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import component.label.NoBuildingSelectedLabel;
import gameobject.building.Building;
import component.Grid.BuildingsListener;

public class SelectedBuildingPanel extends GamePanel {
    private static final String NAME = "selectedBuildingPanel";
    private static final NoBuildingSelectedLabel NO_BUILDING_SELECTED_LABEL = new NoBuildingSelectedLabel();

    private Building selectedBuilding;
    private ComponentList infoComponentList;
    private ComponentList actionComponentList;
    private Grid grid;

    public SelectedBuildingPanel(Grid grid) {
        this.grid = grid;
        this.setLayout(new GridBagLayout());
    }

    public void setSelectedBuilding(Building newSelectedBuilding) {
        if(selectedBuilding == null) {
            this.remove(NO_BUILDING_SELECTED_LABEL);
        } else {
            this.remove(infoComponentList);
            this.remove(actionComponentList);
            infoComponentList = null;
            actionComponentList = null;
        }
        
        if(newSelectedBuilding == null) {
            GridBagConstraints gbc = new GridBagConstraints();
            this.add(NO_BUILDING_SELECTED_LABEL, gbc);
        } else {
            infoComponentList = newSelectedBuilding.toInfoComponentList(grid);
            actionComponentList = newSelectedBuilding.toActionComponentList(grid);

            GridBagConstraints gbc;
            gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.BOTH;
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 1;
            gbc.weighty = 1;
            this.add(infoComponentList, gbc);

            gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.BOTH;
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.gridx = 1;
            gbc.gridy = 0;
            gbc.weightx = 1;
            gbc.weighty = 1;
            this.add(actionComponentList, gbc);

        }
        validate();
        selectedBuilding = newSelectedBuilding;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void update() {
        Building newSelectedBuilding = ((BuildingsListener)grid.getGridMouseListener()).getSelected();
        if(newSelectedBuilding != selectedBuilding) {
            setSelectedBuilding(newSelectedBuilding);
        }

        if(selectedBuilding == null) {
            NO_BUILDING_SELECTED_LABEL.update();
        } else {
            infoComponentList.update();
            actionComponentList.update();
        }
    }
}

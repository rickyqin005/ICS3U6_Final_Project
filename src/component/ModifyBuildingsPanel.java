package component;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import component.button.SlideLeftButton;
import component.button.SlideRightButton;
import gameobject.building.TemplateBuilding;

public class ModifyBuildingsPanel extends GamePanel {
    private String name;
    private Grid grid;
    private ArrayList<TemplateBuilding> items;
    private int itemsIndex;
    private SlideLeftButton slideLeftButton;
    private ComponentList buildingInfoPanel;
    private SlideRightButton slideRightButton;
    private SelectedBuildingPanel selectedBuildingPanel;

    public ModifyBuildingsPanel(String name, ArrayList<TemplateBuilding> items, Grid grid) {
        this.name = name;
        this.grid = grid;
        this.items = items;
        this.itemsIndex = 0;
        this.slideLeftButton = new SlideLeftButton(this);
        this.buildingInfoPanel = new ComponentList("slidingSelectionBuildingInfo");
        this.slideRightButton = new SlideRightButton(this);
        this.selectedBuildingPanel = new SelectedBuildingPanel(grid);

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.weighty = 1;
        this.add(slideLeftButton, gbc);

        updateSlidingSelectionBuilding();

        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.weighty = 1;
        this.add(slideRightButton, gbc);

        selectedBuildingPanel.setSelectedBuilding(null);
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        this.add(selectedBuildingPanel, gbc);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void update() {
        slideLeftButton.update();
        buildingInfoPanel.update();
        slideRightButton.update();
        selectedBuildingPanel.update();
    }

    private void updateSlidingSelectionBuilding() {
        if(buildingInfoPanel != null) {
            this.remove(buildingInfoPanel);
        }
        buildingInfoPanel = items.get(itemsIndex).toInfoComponentList(grid);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        this.add(buildingInfoPanel, gbc);

        this.validate();
    }

    public void slideLeft() {
        itemsIndex--;
        if(itemsIndex < 0) {
            itemsIndex += items.size();
        }
        updateSlidingSelectionBuilding();
        System.out.println(name + " was moved left to " + itemsIndex);
    }

    public void slideRight() {
        itemsIndex++;
        if(itemsIndex > items.size()-1) {
            itemsIndex -= items.size();
        }
        updateSlidingSelectionBuilding();
        System.out.println(name + " was moved right to " + itemsIndex);
    }
}
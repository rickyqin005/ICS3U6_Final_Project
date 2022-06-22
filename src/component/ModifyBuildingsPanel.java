package component;

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import component.button.SlideLeftButton;
import component.button.SlideRightButton;
import component.Grid.BuildingsListener;
import gameobject.building.Building;
import gameobject.building.TemplateBuilding;
import utility.Direction;
import utility.Text;

public class ModifyBuildingsPanel extends JPanel {
    private String name;
    private Grid grid;
    private ArrayList<TemplateBuilding> items;
    private int itemsIndex;
    private SlideLeftButton slideLeftButton;
    private ComponentList buildingInfoPanel;
    private SlideRightButton slideRightButton;
    private Building currSelectedBuilding;
    private JComponent selectedBuildingPanel;

    public ModifyBuildingsPanel(String name, ArrayList<TemplateBuilding> items, Grid grid) {
        this.name = name;
        this.grid = grid;
        this.items = items;
        this.itemsIndex = 0;
        this.slideLeftButton = new SlideLeftButton(this);
        this.buildingInfoPanel = new ComponentList("slidingSelectionBuildingInfo");
        this.slideRightButton = new SlideRightButton(this);
        currSelectedBuilding = null;

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

        setSelectedBuildingPanel(noSelectedBuildingPanel());
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); //required
        updateSelectedBuilding();

        slideLeftButton.repaint();
        buildingInfoPanel.repaint();
        slideRightButton.repaint();
        selectedBuildingPanel.repaint();
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
        
    }

    private void updateSelectedBuilding() {
        Building newSelectedBuilding = ((BuildingsListener)grid.getGridMouseListener()).getSelected();
        if(newSelectedBuilding != currSelectedBuilding) {
            currSelectedBuilding = newSelectedBuilding;

            if(newSelectedBuilding == null) {
                setSelectedBuildingPanel(noSelectedBuildingPanel());
            } else {
                setSelectedBuildingPanel(selectedBuildingPanel());
            }
        }
    }

    private void setSelectedBuildingPanel(JComponent component) {
        if(selectedBuildingPanel != null) {
            this.remove(selectedBuildingPanel);
        }
        selectedBuildingPanel = component;

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        this.add(selectedBuildingPanel, gbc);
    }

    private JComponent noSelectedBuildingPanel() {
        JLabel label = new JLabel("No building selected");
        label.setName("noSelectedBuildingPanel");
        Text.formatJLabel(label);
        return label;
    }

    private JComponent selectedBuildingPanel() {
        ComponentList infoComponentList = currSelectedBuilding.toInfoComponentList(grid);
        ComponentList actionComponentList = currSelectedBuilding.toActionComponentList(grid);
        return new ComponentList("selectedBuildingPanel", new JComponent[] {infoComponentList, actionComponentList}, 
        Direction.RIGHT, new int[] {2, 1}, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
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
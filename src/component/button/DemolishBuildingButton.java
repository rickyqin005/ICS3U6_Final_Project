package component.button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import component.Grid;
import component.Grid.BuildingsListener;
import component.optionpane.ConfirmDemolishBuilding;
import gameobject.building.Building;
import utility.Images;

public class DemolishBuildingButton extends GameButton {
    private static final ImageIcon ICON = new ImageIcon(Images.getIconPath("demolish"));
    private static final String NAME = "demolishBuildingButton";
    
    public DemolishBuildingButton(Grid grid, Building building) {
        super(ICON);
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int response = new ConfirmDemolishBuilding().getResponse();
                if(response == JOptionPane.YES_OPTION) {
                    grid.demolishBuilding(building);
                    ((BuildingsListener)grid.getGridMouseListener()).setSelected(null);
                }
            }
        });
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void update() {
        
    }
}

package component.button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

import exception.NotEnoughCurrencyException;
import gameobject.building.ResidentialBuilding;
import utility.Images;

public class UpgradeResidentialBuildingButton extends GameButton {
    private static final ImageIcon ICON = new ImageIcon(Images.getIconPath("upgrade"));
    private static final String NAME = "upgradeResidentialBuildingButton";

    private ResidentialBuilding building;

    public UpgradeResidentialBuildingButton(ResidentialBuilding building) {
        super(ICON);
        setBuilding(building);
        update();
    }

    public void setBuilding(ResidentialBuilding building) {
        this.building = building;
    }

    public void updateButtonText() {
        if(building.getLevel() == ResidentialBuilding.MAX_LEVEL) {
            setText("Max Level Reached");
            setEnabled(false);
        } else {
            setText(building.getUpgradeCostToString());
            setEnabled(true);
        }
    }
    
    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void update() {
        updateButtonText();

        if(this.getActionListeners().length > 0) {
            this.removeActionListener(this.getActionListeners()[0]);
        }
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    building.upgrade();
                    updateButtonText();
                } catch (NotEnoughCurrencyException exception) {
                    exception.showErrorMessage();
                }
            }
        });
    }
    
}

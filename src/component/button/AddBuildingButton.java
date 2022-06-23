package component.button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

import component.Grid;
import component.Grid.BuildingsListener;
import gameobject.building.TemplateBuilding;
import utility.Currency;
import utility.Images;

public class AddBuildingButton extends GameButton {
    private static final ImageIcon ICON = new ImageIcon(Images.getIconPath("build"));
    private static final String NAME = "addBuildingButton";
    private Grid grid;
    private TemplateBuilding building;
    public AddBuildingButton(Grid grid, TemplateBuilding building) {
        super();
        this.grid = grid;
        setBuilding(building);
        update();
    }

    public void setBuilding(TemplateBuilding building) {
        this.building = building;
    }
    
    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void update() {
        this.setText(Currency.formatCurrencyAmount(building.getCost()));
        this.setIcon(ICON);

        if(this.getActionListeners().length > 0) {
            this.removeActionListener(this.getActionListeners()[0]);
        }
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((BuildingsListener)grid.getGridMouseListener()).setPending(building);
            }
        });
    }
}

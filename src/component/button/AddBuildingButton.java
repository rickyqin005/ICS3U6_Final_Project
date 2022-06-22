package component.button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import component.Grid;
import component.Grid.BuildingsListener;
import gameobject.building.TemplateBuilding;
import utility.Currency;
import utility.Text;

public class AddBuildingButton extends JButton {
    private static final String NAME = "addBuildingButton";
    private Grid grid;
    public AddBuildingButton(Grid grid, TemplateBuilding building) {
        super();
        Text.formatJButton(this);
        this.grid = grid;
        setBuilding(building);
    }
    
    public void setBuilding(TemplateBuilding building) {
        this.setText(Currency.formatCurrencyAmount(building.getCost()));
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
    
    @Override
    public String getName() {
        return NAME;
    }
}

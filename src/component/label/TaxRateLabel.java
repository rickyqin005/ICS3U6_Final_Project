package component.label;

import java.awt.Font;
import javax.swing.JLabel;

import component.Grid;
import gameobject.building.ResidentialBuilding;

public class TaxRateLabel extends JLabel {
    private Grid grid;
    private Font font;
    public TaxRateLabel(Grid grid, Font font) {
        super();
        this.grid = grid;
        this.font = font;
        this.setText(taxRateToString());
        this.setFont(this.font);
    }
    public String taxRateToString() {
        return "Tax Rate: " + ResidentialBuilding.getTaxRate(grid.getBuildings()) + " per " + ResidentialBuilding.TAX_RATE_TIME_UNIT;
    }
}

package component.label;

import java.awt.Font;
import javax.swing.JLabel;

import component.Grid;
import component.Updatable;
import gameobject.building.ResidentialBuilding;

public class TaxRateLabel extends JLabel implements Updatable {
    private static final String NAME = "taxRateLabel";
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
    @Override
    public String getName() {
        return NAME;
    }
    @Override
    public void update() {
        this.setText(taxRateToString());
        repaint();
    }
}

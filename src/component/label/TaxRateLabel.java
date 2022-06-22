package component.label;

import java.awt.Graphics;
import javax.swing.JLabel;

import component.Grid;
import gameobject.building.ResidentialBuilding;
import utility.Currency;
import utility.Text;

public class TaxRateLabel extends JLabel {
    private static final String NAME = "taxRateLabel";
    private Grid grid;
    public TaxRateLabel(Grid grid) {
        super();
        this.grid = grid;
        this.setText(taxRateToString());
        Text.formatJLabel(this);
    }
    public String taxRateToString() {
        return "Tax Rate: " + Currency.formatCurrencyAmount(ResidentialBuilding.getTaxRate(grid.getBuildings())) + " per " + ResidentialBuilding.TAX_RATE_TIME_UNIT;
    }

    @Override
    public String getName() {
        return NAME;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        setText(taxRateToString());

        super.paintComponent(g);
    }
}

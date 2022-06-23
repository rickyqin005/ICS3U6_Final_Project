package component.label;

import component.Grid;
import gameobject.building.ResidentialBuilding;
import utility.Currency;

public class TaxRateLabel extends GameLabel {
    private static final String NAME = "taxRateLabel";
    private Grid grid;

    public TaxRateLabel(Grid grid) {
        super();
        this.grid = grid;
        update();
    }

    public String taxRateToString() {
        return "Tax Rate: " + Currency.formatCurrencyAmount(ResidentialBuilding.getTaxRate(grid.getBuildings())) + " per " + ResidentialBuilding.TAX_RATE_TIME_UNIT;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void update() {
        setText(taxRateToString());        
    }
}

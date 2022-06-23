package component.label;

import component.Grid;
import utility.Currency;

public class CurrencyLabel extends GameLabel {
    private static final String NAME = "currencyLabel";
    private Grid grid;

    public CurrencyLabel(Grid grid) {
        super();
        this.grid = grid;
        update();
    }

    public String currencyToString() {
        return Currency.CURRENCY_NAME + ": " + Currency.formatCurrencyAmount(grid.getUser().getCurrency());
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void update() {
        setText(currencyToString());
    }
}

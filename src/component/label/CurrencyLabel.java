package component.label;

import java.awt.Graphics;
import javax.swing.JLabel;

import component.Grid;
import utility.Currency;
import utility.Text;

public class CurrencyLabel extends JLabel {
    private static final String NAME = "currencyLabel";
    private Grid grid;
    public CurrencyLabel(Grid grid) {
        super();
        this.grid = grid;
        this.setText(currencyToString());
        Text.formatJLabel(this);
    }
    public String currencyToString() {
        return Currency.CURRENCY_NAME + ": " + Currency.formatCurrencyAmount(grid.getUser().getCurrency());
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void paintComponent(Graphics g) {
        setText(currencyToString());

        super.paintComponent(g);
    }
}

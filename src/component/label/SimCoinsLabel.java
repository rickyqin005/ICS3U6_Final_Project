package component.label;

import java.awt.Font;
import javax.swing.JLabel;

import component.Grid;
import component.Updatable;

public class SimCoinsLabel extends JLabel implements Updatable {
    private static final String NAME = "simCoinsLabel";
    private Grid grid;
    private Font font;
    public SimCoinsLabel(Grid grid, Font font) {
        super();
        this.grid = grid;
        this.font = font;
        this.setText(simCoinsToString());
        this.setFont(this.font);
    }
    public String simCoinsToString() {
        return "Simcoins: " + grid.getUser().getSimCoins();
    }
    @Override
    public String getName() {
        return NAME;
    }
    @Override
    public void update() {
        this.setText(simCoinsToString());
        repaint();
    }
}

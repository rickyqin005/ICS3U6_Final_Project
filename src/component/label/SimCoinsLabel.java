package component.label;

import java.awt.Font;
import javax.swing.JLabel;

import component.Grid;

public class SimCoinsLabel extends JLabel {
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
}

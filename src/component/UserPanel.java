package component;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JPanel;

import component.label.PopulationLabel;
import component.label.CurrencyLabel;
import component.label.TaxRateLabel;
import utility.Direction;

public class UserPanel extends JPanel {
    public static final String NAME = "userPanel";
    private ComponentList labelList;

    public UserPanel(Grid grid) {
        super();
        
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JComponent[] labels = {new PopulationLabel(grid), new CurrencyLabel(grid), new TaxRateLabel(grid)};
        labelList = new ComponentList("info", labels, Direction.RIGHT, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        this.add(labelList, gbc);
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); //required
        labelList.repaint();
    }
}

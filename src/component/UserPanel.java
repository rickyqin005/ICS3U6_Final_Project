package component;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JLabel;

import component.label.PopulationLabel;
import component.label.SimCoinsLabel;
import component.label.TaxRateLabel;
import utility.Direction;

public class UserPanel extends GameComponent {
    private static final Font REGULAR_FONT = new Font("Arial", Font.PLAIN, 16);
    private String name;

    public UserPanel(String name, Grid grid) {
        super();
        this.name = name;

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel[] labels = {new PopulationLabel(grid, REGULAR_FONT), new SimCoinsLabel(grid, REGULAR_FONT), 
            new TaxRateLabel(grid, REGULAR_FONT)};
        ComponentList labelList = new ComponentList("info", labels, Direction.RIGHT, 1, 1);
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
        return name;
    }

    @Override
    public void update() {
        repaint();
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); //required
        System.out.println("painted userpanel");
    }
}

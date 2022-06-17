package component;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Graphics;
import javax.swing.JComponent;

public class ComponentList extends GameComponent {
    private String name;
    
    public ComponentList(String name, JComponent[] components, int[] direction, int weightx, int weighty) {
        this.name = name;

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = weightx;
        gbc.weighty = weighty;
        for(JComponent component: components) {
            this.add(component, gbc);
            gbc.gridx += direction[0];
            gbc.gridy += direction[1];
        }
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
        System.out.println("painted componentlist " + name);
    }
}

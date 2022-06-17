package component;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class ComponentList extends JPanel implements Updatable {
    private String name;
    private Updatable[] components;
    
    public ComponentList(String name, Updatable[] components, int[] direction, int weightx, int weighty) {
        this.name = name;
        this.components = components;

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = weightx;
        gbc.weighty = weighty;
        for(Updatable component: this.components) {
            this.add((JComponent)component, gbc);
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
        for(Updatable component: components) {
            component.update();
        }
        //System.out.println("updated componentlist " + name);
    }
}

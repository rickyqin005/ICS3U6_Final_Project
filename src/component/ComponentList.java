package component;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Arrays;
import javax.swing.JComponent;

import core.Updatable;
import utility.Direction;

public class ComponentList extends GamePanel {
    private String name;
    private Updatable[] components;
    
    public ComponentList(String name) {
        this.name = name;
        this.components = new Updatable[0];
    }

    public ComponentList(String name, Updatable[] components, int[] direction, int weight, int anchor, int fill) {
        this.name = name;
        final int[] weights = new int[components.length];
        Arrays.fill(weights, weight);
        setComponents(components, direction, weights, anchor, fill);
    }

    public ComponentList(String name, Updatable[] components, int[] direction, int[] weights, int anchor, int fill) {
        this.name = name;
        setComponents(components, direction, weights, anchor, fill);
    }

    @Override
    public String getName() {
        return name;
    }

    public void setComponents(Updatable[] components, int[] direction, int[] weights, int anchor, int fill) {
        this.removeAll();
        this.components = components;

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = anchor;
        gbc.fill = fill;
        gbc.gridx = 0;
        gbc.gridy = 0;
        int weightIdx = 0;
        for(Updatable component: this.components) {
            if(direction == Direction.DOWN) {
                gbc.weighty = weights[weightIdx];
            } else {
                gbc.weightx = weights[weightIdx];
            }
            this.add((JComponent)component, gbc);
            gbc.gridx += direction[0];
            gbc.gridy += direction[1];
            weightIdx++;
        }
    }

    @Override
    public void update() {
        for(Updatable component: components) {
            component.update();
        }
    }
}

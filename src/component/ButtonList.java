package component;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Graphics;
import javax.swing.JButton;

public class ButtonList extends GameComponent {
    private String name;
    
    public ButtonList(String name, JButton[] buttons, int[] direction) {
        this.name = name;

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        for(JButton button: buttons) {
            this.add(button, gbc);
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
    }
}

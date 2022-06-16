package component;

import java.awt.Graphics;

public class SlidingSelectionPanel extends GameComponent {
    private String name;
    public SlidingSelectionPanel(String name) {
        this.name = name;
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

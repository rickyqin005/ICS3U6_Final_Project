package component;

import java.awt.Graphics;
import javax.swing.JPanel;

public class SlidingSelectionPanel extends JPanel implements Updatable {
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

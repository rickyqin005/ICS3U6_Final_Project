package component;
import javax.swing.JPanel;

public abstract class GameComponent extends JPanel {
    public abstract String getName();
    public abstract void update();
}

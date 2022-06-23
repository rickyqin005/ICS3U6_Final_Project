package component.button;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import core.Updatable;
import utility.Text;

public abstract class GameButton extends JButton implements Updatable {
    public GameButton() {
        super();
        Text.formatJButton(this);
    }

    public GameButton(String label) {
        super(label);
        Text.formatJButton(this);
    }

    public GameButton(ImageIcon icon) {
        super(icon);
        Text.formatJButton(this);
    }

    public GameButton(ImageIcon icon, String label) {
        super(label, icon);
        Text.formatJButton(this);
    }
}

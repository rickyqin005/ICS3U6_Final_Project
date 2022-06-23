package component.label;

import javax.swing.Icon;
import javax.swing.JLabel;

import core.Updatable;
import utility.Text;

public abstract class GameLabel extends JLabel implements Updatable {
    public GameLabel() {
        super();
        Text.formatJLabel(this);
    }

    public GameLabel(String text) {
        super(text);
        Text.formatJLabel(this);
    }

    public GameLabel(Icon picture) {
        super(picture);
    }
}

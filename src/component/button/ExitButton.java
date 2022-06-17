package component.button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import component.Updatable;
import core.Game;
import images.ImagePath;

public class ExitButton extends JButton implements Updatable {
    private static final ImageIcon ICON = new ImageIcon(ImagePath.getIconPath("exit"));
    private static final String NAME = "exitButton";
    public ExitButton(Game game, int newState) {
        super(ICON);
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.setGameState(newState);
            }
        });
    }
    @Override
    public String getName() {
        return NAME;
    }
    @Override
    public void update() {
    }
}

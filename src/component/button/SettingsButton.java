package component.button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import component.Updatable;
import core.Game;
import images.ImagePath;

public class SettingsButton extends JButton implements Updatable {
    private static final ImageIcon ICON = new ImageIcon(ImagePath.getIconPath("settings"));
    private static final String NAME = "settingsButton";
    public SettingsButton(Game game) {
        super(ICON);
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.setGameState(Game.GameState.SETTINGS);
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

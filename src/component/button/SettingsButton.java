package component.button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import core.Game;
import utility.Images;
import utility.Text;

public class SettingsButton extends JButton {
    private static final ImageIcon ICON = new ImageIcon(Images.getIconPath("settings"));
    private static final String NAME = "settingsButton";
    public SettingsButton(Game game) {
        super(ICON);
        Text.formatJButton(this);
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.setGameState(Game.GameState.SETTINGS, null);
            }
        });
    }
    @Override
    public String getName() {
        return NAME;
    }
}

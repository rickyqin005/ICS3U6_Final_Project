package component.button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import images.ImagePath;

public class SettingsButton extends JButton {
    private static final ImageIcon ICON = new ImageIcon(ImagePath.getIconPath("settings"));
    public SettingsButton() {
        super(ICON);
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //game.setGameState(Game.GameState.SETTINGS);
            }
        });
    }
}

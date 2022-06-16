package component.button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import core.Game;
import images.ImagePath;

public class BuildingButton extends JButton {
    private static final ImageIcon ICON = new ImageIcon(ImagePath.getIconPath("building"));
    public BuildingButton(Game game) {
        super(ICON);
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.setGameState(Game.GameState.BUILDINGS);
            }
        });
    }
}

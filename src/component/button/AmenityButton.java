package component.button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import images.ImagePath;

public class AmenityButton extends JButton {
    private static final ImageIcon ICON = new ImageIcon(ImagePath.getIconPath("amenity"));
    public AmenityButton() {
        super(ICON);
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //game.setGameState(Game.GameState.AMENITIES);
            }
        });
    }
}

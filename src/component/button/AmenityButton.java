package component.button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import component.Updatable;
import core.Game;
import images.ImagePath;

public class AmenityButton extends JButton implements Updatable {
    private static final String NAME = "amenityButton";
    private static final ImageIcon ICON = new ImageIcon(ImagePath.getIconPath("amenity"));
    public AmenityButton(Game game) {
        super(ICON);
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.setGameState(Game.GameState.AMENITIES);
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

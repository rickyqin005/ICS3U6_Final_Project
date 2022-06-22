package component.button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import core.Game;
import utility.Images;
import utility.Text;

public class BuildingButton extends JButton {
    private static final ImageIcon ICON = new ImageIcon(Images.getIconPath("building"));
    private static final String NAME = "buildingButton";
    public BuildingButton(Game game) {
        super(ICON);
        Text.formatJButton(this);
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.setGameState(Game.GameState.BUILDINGS, null);
            }
        });
    }
    @Override
    public String getName() {
        return NAME;
    }
}

package component.button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;

import core.Game;
import utility.Images;

public class BuildingButton extends GameButton {
    private static final ImageIcon ICON = new ImageIcon(Images.getIconPath("building"));
    private static final String NAME = "buildingButton";

    public BuildingButton(Game game) {
        super(ICON);
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

    @Override
    public void update() {
        
    }
}

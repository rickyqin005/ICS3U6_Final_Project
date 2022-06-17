package component.button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

import component.Updatable;
import core.Game;

public class NewGameButton extends JButton implements Updatable {
    private static final String NAME = "newGameButton";
    private static final String BUTTON_LABEL = "New Game";
    public NewGameButton(Game game) {
        super(BUTTON_LABEL);
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.setGameState(Game.GameState.GAMEPLAY);
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

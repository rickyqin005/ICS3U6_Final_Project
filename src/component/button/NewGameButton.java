package component.button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

import core.Game;
import utility.Text;

public class NewGameButton extends JButton {
    private static final String NAME = "newGameButton";
    private static final String BUTTON_LABEL = "New Game";
    public NewGameButton(Game game) {
        super(BUTTON_LABEL);
        Text.formatJButton(this);
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.setGameState(Game.GameState.GAMEPLAY, null);
            }
        });
    }
    
    @Override
    public String getName() {
        return NAME;
    }
}

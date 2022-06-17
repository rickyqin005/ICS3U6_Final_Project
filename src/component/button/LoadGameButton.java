package component.button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

import component.Updatable;
import core.Game;

public class LoadGameButton extends JButton implements Updatable {
    private static final String NAME = "loadGameButton";
    private static final String BUTTON_LABEL = "Load Game";
    public LoadGameButton(Game game) {
        super(BUTTON_LABEL);
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
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

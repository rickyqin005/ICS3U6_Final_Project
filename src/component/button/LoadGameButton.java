package component.button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import core.Game;
import core.Game.GameState;

public class LoadGameButton extends GameButton {
    private static final String NAME = "loadGameButton";
    private static final String BUTTON_LABEL = "Load Game";
    private JFileChooser fileChooser;

    public LoadGameButton(Game game) {
        super(BUTTON_LABEL);
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new FileNameExtensionFilter(null, Game.FILE_EXTENSION));
                fileChooser.showOpenDialog(null);
                if(fileChooser.getSelectedFile() != null) {
                    game.setGameState(GameState.GAMEPLAY, fileChooser.getSelectedFile());
                }
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
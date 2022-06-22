package component.button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import component.optionpane.SaveGameOption;
import core.Game;
import utility.Images;
import utility.Text;

public class ExitButton extends JButton {
    private static final ImageIcon ICON = new ImageIcon(Images.getIconPath("exit"));
    private static final String NAME = "exitButton";
    public ExitButton(Game game, int newState, boolean saveGame) {
        super(ICON);
        Text.formatJButton(this);
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(saveGame) {
                    int response = new SaveGameOption().getResponse();
                    if(response == JOptionPane.CLOSED_OPTION) {
                        return;
                    } else if(response == JOptionPane.YES_OPTION) {
                        saveGame(game);
                    } else if(response == JOptionPane.NO_OPTION) {
                        
                    }
                }

                game.setGameState(newState, null);
            }
        });
    }
    
    public void saveGame(Game game) {
        File destinationFile = game.getGrid().getSavedFile();
        System.out.println(destinationFile);
        if(destinationFile == null) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new FileNameExtensionFilter(null, Game.FILE_EXTENSION));
            fileChooser.showOpenDialog(null);
            destinationFile = fileChooser.getSelectedFile();
        }
        game.getGrid().new GridFileWriter(destinationFile);
    }
    
    @Override
    public String getName() {
        return NAME;
    }
}

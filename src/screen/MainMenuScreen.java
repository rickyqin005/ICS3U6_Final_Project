package screen;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JComponent;

import component.ComponentList;
import component.PicturePanel;
import component.button.LoadGameButton;
import component.button.NewGameButton;
import component.button.SettingsButton;
import core.Game;
import core.Game.GameState;
import utility.Direction;
import utility.Images;
import utility.Sounds;
import utility.providedtemplates.Music;

public class MainMenuScreen extends GameScreen {
    public static final int STATE = GameState.MAIN_MENU;
    private final PicturePanel logoPanel = new PicturePanel("logoPanel", Images.getImagePath("gamelogo"));
    private ComponentList mainMenuButtonList;
    private Music backgroundMusic;

    public MainMenuScreen(Game currGame) {
        super(currGame);

        final JComponent[] buttonList = {new SettingsButton(game), new NewGameButton(game), new LoadGameButton(game)};
        mainMenuButtonList = new ComponentList("mainMenuButtonList", buttonList, Direction.RIGHT, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);
        
        game.setLayout(new GridBagLayout());
        GridBagConstraints gbc;

        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 3;
        game.addGameComponent(logoPanel, gbc);

        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 2;
        game.addGameComponent(mainMenuButtonList, gbc);

        backgroundMusic = Sounds.getMusic(Sounds.getAudioPath("backgroundmusic"));
        backgroundMusic.start();
        backgroundMusic.loop();
    }

    @Override
    public int getState() {
        return STATE;
    }

    @Override
    public void updateState() {

    }

    @Override
    public void updateGraphics() {
        logoPanel.repaint();
        mainMenuButtonList.repaint();
    }

    @Override
    public void exitScreen(int newState) {
        game.removeGameComponent(logoPanel);
        game.removeGameComponent(mainMenuButtonList);
    }
}

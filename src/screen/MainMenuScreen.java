package screen;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;

import component.ComponentList;
import component.button.LoadGameButton;
import component.button.NewGameButton;
import component.label.PictureLabel;
import core.Game;
import core.Updatable;
import core.Game.GameState;
import utility.Direction;
import utility.Images;
import utility.Sounds;
import utility.providedtemplates.Music;

public class MainMenuScreen extends GameScreen {
    public static final int STATE = GameState.MAIN_MENU;
    private static final ImageIcon GAME_LOGO = Images.getImageIcon(Images.getImagePath("gamelogo"));
    private final PictureLabel logoPanel = new PictureLabel("logoPanel", GAME_LOGO);
    private ComponentList mainMenuButtonList;
    private Music backgroundMusic;

    public MainMenuScreen(Game currGame) {
        super(currGame);

        final Updatable[] buttonList = {new NewGameButton(game), new LoadGameButton(game)};
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
        game.addToJFrame(logoPanel, gbc);

        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 2;
        game.addToJFrame(mainMenuButtonList, gbc);

        backgroundMusic = Sounds.getMusic(Sounds.getAudioPath("backgroundmusic"));
        backgroundMusic.start();
        backgroundMusic.loop();
    }

    @Override
    public int getState() {
        return STATE;
    }

    @Override
    public void update() {
        logoPanel.update();
        mainMenuButtonList.update();
    }

    @Override
    public void exitScreen(int newState) {
        game.removeFromJFrame(logoPanel);
        game.removeFromJFrame(mainMenuButtonList);
    }
}

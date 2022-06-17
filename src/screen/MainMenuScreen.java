package screen;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import component.ComponentList;
import component.PicturePanel;
import component.Updatable;
import component.button.LoadGameButton;
import component.button.NewGameButton;
import core.Game;
import utility.Direction;

public class MainMenuScreen extends GameScreen {
    private PicturePanel logoPanel;
    private ComponentList mainMenuButtonList;
    public MainMenuScreen(Game game) {
        super(game);

        // Retrieve the JPanels
        logoPanel = (PicturePanel)getGameComponentByName("logoPanel", new PicturePanel("logoPanel", "gamelogo"));
        Updatable[] buttonList = {new NewGameButton(game), new LoadGameButton(game)};
        mainMenuButtonList = (ComponentList)getGameComponentByName("mainMenuButtonList", 
            new ComponentList("mainMenuButtonList", buttonList, Direction.RIGHT, 1, 1));
        // add the JPanels to the screen
        game.setLayout(new GridBagLayout());
        GridBagConstraints gbc;

        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        game.add(logoPanel, gbc);

        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 0.5;
        game.add(mainMenuButtonList, gbc);
    }
    public void update() {
        logoPanel.update();
        mainMenuButtonList.update();
    }
    @Override
    public void exitScreen() {
        game.removeGameComponent(logoPanel);
    }
}

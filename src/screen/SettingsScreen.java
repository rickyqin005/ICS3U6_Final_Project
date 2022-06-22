package screen;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JComponent;
import javax.swing.JSlider;

import component.ComponentList;
import component.PicturePanel;
import component.button.ExitButton;
import core.Game;
import core.Game.GameState;
import utility.Direction;
import utility.Images;

public class SettingsScreen extends GameScreen {
    public static final int STATE = GameState.SETTINGS;
    ComponentList sideBar;
    ComponentList settingsIconList;
    ComponentList settingsSliderList;
    
    public SettingsScreen(Game currGame, int prevState) {
        super(currGame);

        // Retrieve the JPanels
        final JComponent[] buttons = {new ExitButton(game, prevState, false)};
        sideBar = new ComponentList("sideBar", buttons, Direction.DOWN, 0, GridBagConstraints.NORTH, GridBagConstraints.NONE);

        JComponent[] icons = {
                new PicturePanel("musicIcon", Images.getIconPath("music")),
                new PicturePanel("soundIcon", Images.getIconPath("sound"))
        };
        JComponent[] sliders = {new JSlider(0, 100, 50), new JSlider(0, 100, 50)};
        settingsIconList = new ComponentList("settingsIconList", icons, Direction.DOWN, 1, GridBagConstraints.EAST, GridBagConstraints.BOTH);
        settingsSliderList = new ComponentList("settingsSliderList", sliders, Direction.DOWN, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);

        // add the JPanels to the screen
        game.setLayout(new GridBagLayout());
        GridBagConstraints gbc;

        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.weighty = 0;
        game.addGameComponent(sideBar, gbc);

        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        game.addGameComponent(settingsIconList, gbc);

        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        game.addGameComponent(settingsSliderList, gbc);
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
        sideBar.repaint();
        settingsIconList.repaint();
        settingsSliderList.repaint();
    }

    @Override
    public void exitScreen(int newState) {
        game.removeGameComponent(sideBar);
        game.removeGameComponent(settingsIconList);
        game.removeGameComponent(settingsSliderList);
    }
    
}

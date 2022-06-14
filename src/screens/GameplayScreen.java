package screens;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import components.Grid;
import components.UserPanel;
import core.Game;

public class GameplayScreen extends GameScreen {
    private UserPanel userPanel;
    private Grid grid;

    private JButton settingsButton() {
        ImageIcon buttonIcon = new ImageIcon("src/images/icons/settings.png");
        JButton button = new JButton(buttonIcon);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //game.setGameState(Game.GameState.SETTINGS);
            }
        });
        return button;
    }
    private JButton zoomInButton() {
        ImageIcon buttonIcon = new ImageIcon("src/images/icons/zoomin.png");
        JButton button = new JButton(buttonIcon);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                grid.getViewport().zoomIn();
            }
        });
        return button;
    }
    private JButton zoomOutButton() {
        ImageIcon buttonIcon = new ImageIcon("src/images/icons/zoomout.png");
        JButton button = new JButton(buttonIcon);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                grid.getViewport().zoomOut();
            }
        });
        return button;
    }
    private JButton roadButton() {
        ImageIcon buttonIcon = new ImageIcon("src/images/icons/road.png");
        JButton button = new JButton(buttonIcon);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        return button;
    }
    private JButton buildingButton() {
        ImageIcon buttonIcon = new ImageIcon("src/images/icons/building.png");
        JButton button = new JButton(buttonIcon);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //game.setGameState(Game.GameState.BUILDINGS);
            }
        });
        return button;
    }
    private JButton amenityButton() {
        ImageIcon buttonIcon = new ImageIcon("src/images/icons/amenity.png");
        JButton button = new JButton(buttonIcon);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //game.setGameState(Game.GameState.AMENITIES);
            }
        });
        return button;
    }
    private JButton analyticsButton() {
        ImageIcon buttonIcon = new ImageIcon("src/images/icons/analytics.png");
        JButton button = new JButton(buttonIcon);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //game.setGameState(Game.GameState.ANALYTICS);
            }
        });
        return button;
    }

    public GameplayScreen(Game game) {
        this.userPanel = new UserPanel();
        this.grid = new Grid();

        game.setLayout(new GridBagLayout());
        GridBagConstraints gbc;

        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.weightx = 0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        screens.add(userPanel);
        game.add(userPanel, gbc);

        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.weighty = 0;
        gbc.gridx = 0;
        gbc.gridy = 1;
        game.add(settingsButton(), gbc);

        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.weighty = 0;
        gbc.gridx = 0;
        gbc.gridy = 2;
        game.add(zoomInButton(), gbc);

        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.weighty = 0;
        gbc.gridx = 0;
        gbc.gridy = 3;
        game.add(zoomOutButton(), gbc);

        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.weighty = 0;
        gbc.gridx = 0;
        gbc.gridy = 4;
        game.add(roadButton(), gbc);

        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.weighty = 0;
        gbc.gridx = 0;
        gbc.gridy = 5;
        game.add(buildingButton(), gbc);

        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.weighty = 0;
        gbc.gridx = 0;
        gbc.gridy = 6;
        game.add(amenityButton(), gbc);

        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.weighty = 0;
        gbc.gridx = 0;
        gbc.gridy = 7;
        game.add(analyticsButton(), gbc);
        
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridheight = 7;
        gbc.gridx = 1;
        gbc.gridy = 1;
        screens.add(grid);
        game.add(grid, gbc);
    }
    
}

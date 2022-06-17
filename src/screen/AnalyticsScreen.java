package screen;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JButton;

import component.Grid;
import component.UserPanel;
import component.button.*;
import component.ComponentList;
import core.Game;
import utility.Direction;

public class AnalyticsScreen extends GameScreen {
    private Grid grid;
    private UserPanel userPanel;
    private ComponentList sidebar;
    
    public AnalyticsScreen(Game game) {
        super(game);
        
    }
    public void update() {

    }
    @Override
    public void exitScreen() {

    }
}

package screen;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import component.Grid;
import component.SlidingSelectionPanel;
import component.UserPanel;
import component.button.*;
import core.Game;

public class BuildingsScreen extends GameScreen {
    private UserPanel userPanel;
    private Grid grid;
    private SlidingSelectionPanel buildingPanel;

    public BuildingsScreen(Game game) {
        super(game);

    }
}

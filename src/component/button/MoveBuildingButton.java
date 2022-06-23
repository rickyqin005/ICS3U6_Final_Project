package component.button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

import component.Grid;
import component.Grid.BuildingsListener;
import gameobject.building.Building;
import utility.Images;

public class MoveBuildingButton extends GameButton {
    private static final ImageIcon ICON = new ImageIcon(Images.getIconPath("move"));
    private static final String NAME = "moveBuildingButton";

    public MoveBuildingButton(Grid grid, Building building) {
        super(ICON);
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((BuildingsListener)grid.getGridMouseListener()).setSelectedMovable(true);
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

package component.button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import component.Grid;
import component.Grid.BuildingsListener;
import gameobject.building.Building;
import utility.Images;
import utility.Text;

public class MoveBuildingButton extends JButton {
    private static final ImageIcon ICON = new ImageIcon(Images.getIconPath("move"));
    private static final String NAME = "moveBuildingButton";
    public MoveBuildingButton(Grid grid, Building building) {
        super(ICON);
        Text.formatJButton(this);
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
}

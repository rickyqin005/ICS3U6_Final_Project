package component.button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Point;
import javax.swing.ImageIcon;

import component.Grid;
import utility.Images;

public class ZoomInButton extends GameButton {
    private static final ImageIcon ICON = new ImageIcon(Images.getIconPath("zoomin"));
    private static final String NAME = "zoomInButton";

    public ZoomInButton(Grid grid) {
        super(ICON);
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Point screenCenter = grid.getViewport().getCenter();
                grid.getViewport().zoomIn(screenCenter);
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

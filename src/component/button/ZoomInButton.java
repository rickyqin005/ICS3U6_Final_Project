package component.button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Point;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import component.Grid;
import images.ImagePath;

public class ZoomInButton extends JButton {
    private static final ImageIcon ICON = new ImageIcon(ImagePath.getIconPath("zoomin"));
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
}

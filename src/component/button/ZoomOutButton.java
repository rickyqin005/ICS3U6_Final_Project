package component.button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Point;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import component.Grid;
import images.ImagePath;

public class ZoomOutButton extends JButton {
    private static final ImageIcon ICON = new ImageIcon(ImagePath.getIconPath("zoomout"));
    public ZoomOutButton(Grid grid) {
        super(ICON);
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Point screenCenter = grid.getViewport().getCenter();
                grid.getViewport().zoomOut(screenCenter);
            }
        });
    }
}

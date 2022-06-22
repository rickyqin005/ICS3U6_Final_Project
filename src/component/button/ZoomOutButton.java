package component.button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Point;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import component.Grid;
import utility.Images;
import utility.Text;

public class ZoomOutButton extends JButton {
    private static final ImageIcon ICON = new ImageIcon(Images.getIconPath("zoomout"));
    private static final String NAME = "zoomOutButton";
    public ZoomOutButton(Grid grid) {
        super(ICON);
        Text.formatJButton(this);
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Point screenCenter = grid.getViewport().getCenter();
                grid.getViewport().zoomOut(screenCenter);
            }
        });
    }
    @Override
    public String getName() {
        return NAME;
    }
}

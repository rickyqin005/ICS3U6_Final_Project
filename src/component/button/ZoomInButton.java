package component.button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Point;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import component.Grid;
import utility.Images;
import utility.Text;

public class ZoomInButton extends JButton {
    private static final ImageIcon ICON = new ImageIcon(Images.getIconPath("zoomin"));
    private static final String NAME = "zoomInButton";
    public ZoomInButton(Grid grid) {
        super(ICON);
        Text.formatJButton(this);
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
}

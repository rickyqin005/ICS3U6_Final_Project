package component.button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
                //System.out.println(grid);
                grid.getViewport().zoomIn();
            }
        });
    }
}

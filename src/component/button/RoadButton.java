package component.button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import images.ImagePath;

public class RoadButton extends JButton {
    private static final ImageIcon ICON = new ImageIcon(ImagePath.getIconPath("road"));
    public RoadButton() {
        super(ICON);
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
    }
}

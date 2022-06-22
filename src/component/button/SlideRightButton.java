package component.button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import component.ModifyBuildingsPanel;
import utility.Images;
import utility.Text;

public class SlideRightButton extends JButton {
    private static final ImageIcon ICON = new ImageIcon(Images.getIconPath("slideright"));
    private static final String NAME = "slideRightButton";
    public SlideRightButton(ModifyBuildingsPanel panel) {
        super(ICON);
        Text.formatJButton(this);
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.slideRight();
            }
        });
    }
    @Override
    public String getName() {
        return NAME;
    }
}

package component.button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import component.ModifyBuildingsPanel;
import utility.Images;
import utility.Text;

public class SlideLeftButton extends JButton{
    private static final ImageIcon ICON = new ImageIcon(Images.getIconPath("slideleft"));
    private static final String NAME = "slideLeftButton";
    public SlideLeftButton(ModifyBuildingsPanel panel) {
        super(ICON);
        Text.formatJButton(this);
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.slideLeft();
            }
        });
    }
    @Override
    public String getName() {
        return NAME;
    }
}

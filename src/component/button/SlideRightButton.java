package component.button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;

import component.ModifyBuildingsPanel;
import utility.Images;

public class SlideRightButton extends GameButton {
    private static final ImageIcon ICON = new ImageIcon(Images.getIconPath("slideright"));
    private static final String NAME = "slideRightButton";

    public SlideRightButton(ModifyBuildingsPanel panel) {
        super(ICON);
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

    @Override
    public void update() {
    }
}

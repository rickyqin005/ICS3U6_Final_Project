package component.button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import component.Grid;
import component.Updatable;
import images.ImagePath;

public class CollectTaxButton extends JButton implements Updatable {
    private static final ImageIcon ICON = new ImageIcon(ImagePath.getIconPath("collecttax"));
    private static final String NAME = "collectTaxButton";
    public CollectTaxButton(Grid grid) {
        super(ICON);
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                grid.getUser().addSimCoins(grid.getUser().getCollectableTax());
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

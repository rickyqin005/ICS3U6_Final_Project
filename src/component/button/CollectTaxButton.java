package component.button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;

import component.Grid;
import utility.Images;

public class CollectTaxButton extends GameButton {
    private static final ImageIcon ICON = new ImageIcon(Images.getIconPath("collecttax"));
    private static final String NAME = "collectTaxButton";

    public CollectTaxButton(Grid grid) {
        super(ICON);
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                grid.getUser().addCurrency(grid.getUser().getCollectableTax());
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

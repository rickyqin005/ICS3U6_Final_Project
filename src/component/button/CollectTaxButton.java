package component.button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import component.Grid;
import utility.Images;
import utility.Text;

public class CollectTaxButton extends JButton {
    private static final ImageIcon ICON = new ImageIcon(Images.getIconPath("collecttax"));
    private static final String NAME = "collectTaxButton";

    public CollectTaxButton(Grid grid) {
        super(ICON);
        Text.formatJButton(this);
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
}

package component.label;

import javax.swing.ImageIcon;

public class PictureLabel extends GameLabel {
    private String name;

    public PictureLabel(String name) {
        super();
        this.name = name;
    }
    
    public PictureLabel(String name, ImageIcon icon) {
        super(icon);
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void update() {
    }
}

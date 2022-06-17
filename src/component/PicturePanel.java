package component;

import java.awt.Graphics;
import javax.swing.JPanel;

import images.ImagePath;
import utility.Sprite;

public class PicturePanel extends JPanel implements Updatable {
    private String name;
    private Sprite picture;
    public PicturePanel(String name, String pictureName) {
        this.name = name;
        this.picture = new Sprite(0, 0, ImagePath.getImagePath(pictureName));
    }
    @Override
    public String getName() {
        return name;
    }
    @Override
    public void update() {
        repaint();
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); //required
        picture.setX((this.getWidth() - picture.getWidth()) / 2);
        picture.setY((this.getHeight() - picture.getHeight()) / 2);
        picture.draw(g);
    }
}

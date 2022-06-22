package component;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

import utility.Images;
import utility.providedtemplates.Sprite;

public class PicturePanel extends JPanel {
    private String name;
    private Sprite picture;

    public PicturePanel(String name) {
        this.name = name;
    }
    
    public PicturePanel(String name, String picturePath) {
        this.name = name;
        setPicture(picturePath);
    }

    public PicturePanel(String name, Sprite picture) {
        this.name = name;
        this.picture = picture;
        this.setMinimumSize(new Dimension(this.picture.getWidth(), this.picture.getHeight()));
    }

    @Override
    public String getName() {
        return name;
    }

    public void setPicture(String newPicturePath) {
        this.picture = Images.getSprite(newPicturePath);
        this.setMinimumSize(new Dimension(this.picture.getWidth(), this.picture.getHeight()));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); //required
        picture.setX((this.getWidth() - picture.getWidth()) / 2);
        picture.setY((this.getHeight() - picture.getHeight()) / 2);
        picture.draw(g);
    }

    
}

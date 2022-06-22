package utility;

import java.util.HashMap;

import utility.providedtemplates.Sprite;

public class Images {
    private static HashMap<String, Sprite> loadedImages = new HashMap<String, Sprite>();
    /**
     * Finds and returns a reference to the Sprite with the specified path, or creates and returns a new one if it doesn't exist.
     * Since ImageIO.read() is very slow, it is optimal to instantiate each distinct Sprite only once.
     * @param path The file path of the image for the Sprite.
     * @return The found Sprite or a new Sprite.
     */
    public static Sprite getSprite(String path) {
        Sprite result = loadedImages.get(path);
        if(result == null) {
            result = new Sprite(0, 0, path);
            loadedImages.put(path, result);
            System.out.println("Loaded " + path);
        }
        return result;
    }
    public static String getImagePath(String name) {
        return "src/media/images/" + name + ".png";
    }
    public static String getImagePath(String name, int scale) {
        return "src/media/images/" + name + "_scale" + scale + ".png";
    }
    public static String getImagePath(String name, int scale, int level) {
        return "src/media/images/" + name + "_scale" + scale + "_level" + level + ".png";
    }
    public static String getIconPath(String name) {
        return "src/media/images/icons/" + name + ".png";
    }
}

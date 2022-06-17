package images;

public class ImagePath {
    public static String getImagePath(String name) {
        return "src/images/" + name + ".png";
    }
    public static String getImagePath(String name, int scale) {
        return "src/images/" + name + "_scale" + scale + ".png";
    }
    public static String getImagePath(String name, int scale, int level) {
        return "src/images/" + name + "_scale" + scale + "_level" + level + ".png";
    }
    public static String getIconPath(String name) {
        return "src/images/icons/" + name + ".png";
    }
}

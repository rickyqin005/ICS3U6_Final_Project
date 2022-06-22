package utility;

import java.awt.Point;
import java.awt.Rectangle;

public class Rectangles {
    /**
     * Determines if two Rectangles overlap with each other.
     * Two Rectangles are considered to overlap if their intersection is non-zero.
     * @param rect1 The first rectangle.
     * @param rect2 The second rectangle.
     */
    public static boolean overlap(Rectangle rect1, Rectangle rect2) {
        return (rect1.intersects(rect2) || rect1.contains(rect2));
    }

    public static final int TOP_EDGE = 0;
    public static final int LEFT_EDGE = 1;
    public static final int BOTTOM_EDGE = 2;
    public static final int RIGHT_EDGE = 3;
    public static Line getEdge(Rectangle rect, int edge) {
        if(edge == TOP_EDGE) {
            return new Line(new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y));
        } else if(edge == LEFT_EDGE) {
            return new Line(new Point(rect.x, rect.y), new Point(rect.x, rect.y + rect.height));
        } else if(edge == BOTTOM_EDGE) {
            return new Line(new Point(rect.x, rect.y + rect.height), new Point(rect.x + rect.width, rect.y + rect.height));
        } else if(edge == RIGHT_EDGE) {
            return new Line(new Point(rect.x + rect.width, rect.y), new Point(rect.x + rect.width, rect.y + rect.height));
        } else {
            return null;
        }
    }

    public static Line[] getEdges(Rectangle rect) {
        return new Line[]{getEdge(rect, TOP_EDGE), getEdge(rect, LEFT_EDGE), 
                getEdge(rect, BOTTOM_EDGE), getEdge(rect, RIGHT_EDGE)};
    }
}

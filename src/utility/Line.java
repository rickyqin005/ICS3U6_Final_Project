package utility;

import java.awt.Point;

public class Line {
    public Point startPoint;
    public int[] direction;
    public int length;

    public Line(Point startPoint, int[] direction, int length) {
        this.startPoint = startPoint;
        this.direction = direction;
        this.length = length-1;
    }

    public void translate(int dx, int dy) {
        this.startPoint.translate(dx, dy);
    }
}

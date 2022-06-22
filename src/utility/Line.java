package utility;

import java.awt.Point;

public class Line {
    public Point start;
    public int[] direction;
    public int distance;

    public Line(Point startPoint, int[] direction, int length) {
        this.start = startPoint;
        this.direction = direction;
        this.distance = length-1;
    }

    public Line(Point startPoint, Point endpoint) {
        int dx = endpoint.x-startPoint.x;
        int dy = endpoint.y-startPoint.y;
        if(dx == 0) {
            this.start = new Point(startPoint.x, Math.min(endpoint.y, startPoint.y));
            this.direction = Direction.DOWN;
            this.distance = Math.abs(dy);
        } else if(dy == 0) {
            this.start = new Point(Math.min(endpoint.x, startPoint.x), startPoint.y);
            this.direction = Direction.RIGHT;
            this.distance = Math.abs(dx);
        }
    }

    /**
     * Returns the length of the current line.
     */
    public int getLength() {
        return distance+1;
    }

    /**
     * Returns the endpoint of the current line.
     * @return The endpoint.
     */
    public Point getEndPoint() {
        return new Point(start.x + distance*direction[0], start.y + distance*direction[1]);
    }

    /**
     * Determines if the current line is parallel to the specified line.
     * @param line The specified line.
     */
    public boolean isParallel(Line line) {
        return (direction == line.direction);
    }

    /**
     * Determines if the current line is perpendicular to the specified line.
     * @param line The specified line.
     */
    public boolean isPerpendicular(Line line) {
        return (direction != line.direction);
    }
    
    /**
     * Determines if the current line coincides with the specified line.
     * @param line The specified line.
     */
    public boolean isCoincident(Line line) {
        if(this.isParallel(line)) {
            if(this.direction == Direction.RIGHT) {
                if(this.start.y == line.start.y) {
                    int maxMinX = Math.max(this.start.x, line.start.x);
                    int minMaxX = Math.min(this.getEndPoint().x, line.getEndPoint().x);
                    if(maxMinX < minMaxX) {
                        return true;
                    }
                }
            } else {
                if(this.start.x == line.start.x) {
                    int maxMinY = Math.max(this.start.y, line.start.y);
                    int minMaxY = Math.min(this.getEndPoint().y, line.getEndPoint().y);
                    if(maxMinY < minMaxY) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Determines the intersection point between the current line and the specified line.
     * Two lines are considered to intersect each other if they meet at a single point.
     * Consequently, coincident lines do not intersect each other.
     * @param line The specified line.
     * @return The intersection point, or null if there is no intersection point.
     */
    public Point getIntersectionPoint(Line line) {
        if(this.isPerpendicular(line)) {
            if(this.direction == Direction.RIGHT) {
                if(this.start.x <= line.start.x && line.start.x <= this.getEndPoint().x && 
                        line.start.y <= this.start.y && this.start.y <= line.getEndPoint().y) {
                    return new Point(line.start.x, this.start.y);
                }
            } else {
                if(line.start.x <= this.start.x && this.start.x <= line.getEndPoint().x && 
                        this.start.y <= line.start.y && line.start.y <= this.getEndPoint().y) {
                    return new Point(this.start.x, line.start.y);
                }
            }
        } else {
            if(this.start.equals(line.getEndPoint())) {
                return new Point(this.start);
            } else if(this.getEndPoint().equals(line.start)) {
                return new Point(this.getEndPoint());
            }
        }
        return null;
    }

    /**
     * Determines if the current line intersects with the specified line.
     * Two lines are considered to intersect each other if they meet at a single point.
     * Consequently, coincident lines do not intersect each other.
     * @param line The specified line.
     */
    public boolean intersects(Line line) {
        return (this.getIntersectionPoint(line) != null);
    }

    /**
     * Translates the current line by the specified amount.
     * @param dx The amount to translate in the X direction.
     * @param dy The amount to translate in the Y direction.
     */
    public void translate(int dx, int dy) {
        this.start.translate(dx, dy);
    }
}

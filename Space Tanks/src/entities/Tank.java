package entities;

import utiliies.Point;
import utiliies.Vector;

public class Tank {
    private Point point;
    private Vector vector;
    private boolean isAI;

    public Tank(double x, double y, double angle, double power, boolean isAI) {
        this.point = new Point(x, y);
        this.vector = new Vector(angle, power);
        this.isAI = isAI;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public Vector getVector() {
        return vector;
    }

    public void setVector(Vector vector) {
        this.vector = vector;
    }
}

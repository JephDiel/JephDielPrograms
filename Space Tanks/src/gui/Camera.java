package gui;

import utiliies.Point;

public class Camera {
    private Point point;
    private double zoom;

    public Camera(double x, double y, double zoom) {
        this.point = new Point(x, y);
        this.zoom = zoom;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public double getZoom() {
        return zoom;
    }

    public void setZoom(double zoom) {
        this.zoom = zoom;
    }
}

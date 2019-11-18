package world;

import utiliies.Point;

public class Planet {
    private Point point;
    private double mass;
    private double radius;

    public Planet(double x, double y, double mass, double radius) {
        this.point = new Point(x, y);
        this.mass = mass;
        this.radius = radius;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
}

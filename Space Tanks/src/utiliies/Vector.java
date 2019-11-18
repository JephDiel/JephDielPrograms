package utiliies;

public class Vector {
    private double angle;
    private double power;

    public Vector(double angle, double power) {
        this.angle = angle;
        this.power = power;
    }

    public Vector(Point point1, Point point2) {
        double x = point1.getX() - point2.getX();
        double y = point1.getY() - point2.getY();
        this.setAngle(Math.atan2(y, x));
        this.setPower(Math.sqrt((x * x) + (y * y)));
    }

    public double getXOffset() {
        return power * Math.cos(angle);
    }

    public double getyOffset() {
        return power * Math.sin(angle);
    }

    public void pull(Vector vector) {
        double x = this.getXOffset() + vector.getXOffset();
        double y = this.getyOffset() + vector.getyOffset();
        this.setAngle(Math.atan2(y, x));
        this.setPower(Math.sqrt((x * x) + (y * y)));
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        this.power = power;
    }
}

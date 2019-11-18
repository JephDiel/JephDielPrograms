package gui;

public class Bounds {
    private double minimumX;
    private double maximumX;
    private double minimumY;
    private double maximumY;

    public Bounds(double minimumX, double maximumX, double minimumY, double maximumY) {
        this.minimumX = minimumX;
        this.maximumX = maximumX;
        this.minimumY = minimumY;
        this.maximumY = maximumY;
    }


    public double getMinimumX() {
        return minimumX;
    }

    public void setMinimumX(double minimumX) {
        this.minimumX = minimumX;
    }

    public double getMaximumX() {
        return maximumX;
    }

    public void setMaximumX(double maximumX) {
        this.maximumX = maximumX;
    }

    public double getMinimumY() {
        return minimumY;
    }

    public void setMinimumY(double minimumY) {
        this.minimumY = minimumY;
    }

    public double getMaximumY() {
        return maximumY;
    }

    public void setMaximumY(double maximumY) {
        this.maximumY = maximumY;
    }
}

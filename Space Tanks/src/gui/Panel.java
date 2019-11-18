package gui;

import world.Planet;
import world.Universe;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {
    private Universe universe;
    private Camera camera;

    public Panel(Universe universe, Camera camera) {
        this.universe = universe;
        this.camera = camera;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (universe != null) {
            for (Planet planet: universe.getPlanets()){
                g.setColor(Color.YELLOW);
                //g.

            }
        }
    }

    private void fillRect(Graphics g, double x, double y, double width, double height) {
        int drawX = scaleX(x);
        int drawY = scaleY(y);
        int drawWidth = scaleWidth(width);
        //int drawHeight = sc
        //TODO Complete
        //How should scaling happen and what values does camera need?
    }

    private int scaleX(double positon) {
        return (int)(positon - camera.getPoint().getX() * camera.getZoom()) + getWidth() / 2;
    }
    private int scaleY(double positon) {
        return (int)(positon - camera.getPoint().getY() * camera.getZoom()) + getHeight() / 2;
    }

    private int scaleWidth(double width) {
        return (int)(width * camera.getZoom());
    }
}

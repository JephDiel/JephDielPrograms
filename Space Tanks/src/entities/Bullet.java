package entities;

import utiliies.Point;
import utiliies.Vector;
import world.Planet;
import world.Universe;

public class Bullet {
    private Point point;
    private Vector vector;

    public Bullet(Point point, Vector vector) {
        this.point = point;
        this.vector = vector;
    }

    public void move(Universe universe) {
        for (Planet planet: universe.getPlanets()) {
            Vector gravity = new Vector(point, planet.getPoint());
            gravity.setPower(1 / gravity.getPower());
            vector.pull(gravity);
        }
    }
}

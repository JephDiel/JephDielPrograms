package world;

import entities.Tank;
import utiliies.Point;
import utiliies.Vector;

import java.util.LinkedList;
import java.util.List;

public class Universe {

    private List<Planet> planets;
    private List<Tank> tanks;

    private static Universe instance;

    private Universe() {
        planets = new LinkedList<>();
        tanks = new LinkedList<>();
        addPlanets();
        addTanks();
    }

    public Universe getInstance() {
        if (instance == null) {
            instance = new Universe();
        }
        return instance;
    }

    private void addPlanets() {
        planets.add(new Planet(100, 100, 50, 50));
    }

    private void addTanks() {
        tanks.add(new Tank(100, 45, 0, 0, false));
    }

    public List<Planet> getPlanets() {
        return planets;
    }

    public void setPlanets(List<Planet> planets) {
        this.planets = planets;
    }

    public List<Tank> getTanks() {
        return tanks;
    }

    public void setTanks(List<Tank> tanks) {
        this.tanks = tanks;
    }
}

package AirTrafficSimulator;

import java.awt.*;

public class Airport extends Figure {
    private String name;
    private double x, y;

    Airport(String name, double x, double y) {
        setName(name);
        setX(x);
        setY(y);
    }

    public String getName() {
        return name;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setX(double x) {
        if (x > 90.0 || x < -90.0) {
            throw new IllegalArgumentException();
        }
        this.x = x;
    }

    private void setY(double y) {
        if (y > 90.0 || y < -90.0) {
            throw new IllegalArgumentException();
        }
        this.y = y;
    }

    @Override
    public boolean equals (Object other) {
        if (other == null)
            return false;
        if (other.getClass() != this.getClass())
            return  false;
        if (other == this)
            return true;
        Airport airport = (Airport) other;
        return airport.getName().equals(this.getName());
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    void draw(Graphics g) {

    }
}

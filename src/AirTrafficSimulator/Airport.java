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

    private String getName() {
        return name;
    }

    private double getX() {
        return x;
    }

    private double getY() {
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
    void draw(Graphics g) {

    }
}

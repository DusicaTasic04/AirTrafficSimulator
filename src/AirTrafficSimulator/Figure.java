package AirTrafficSimulator;

import java.awt.*;

public abstract class Figure {
    protected double x, y;
    int size = 15;
    boolean visible = true;

    Figure(double x, double y, boolean visible) {
        this.x = x;
        this.y = y;
        this.visible = visible;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getSize() {
        return size;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    abstract void paint(Graphics g, int px, int py, int w, int h);
}

package AirTrafficSimulator;

import java.awt.*;

public class Airport extends Figure {
    private String name, code;
    private double x, y;
    private boolean highlighted = false;
    private Scheduler  scheduler = new Scheduler();

    Airport(String name, String code, double x, double y) {
        super(x, y, true);
        setName(name);
        setCode(code);
        setX(x);
        setY(y);
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Scheduler getScheduler() {
        return scheduler;
    }

    public boolean isHighlighted() {
        return highlighted;
    }

    private void setCode(String code) {
        if (code.length() != 3 || !code.matches("[A-Z]{3}")) {
            throw new IllegalArgumentException("Invalid code");
        }
        this.code = code;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name is null or empty");
        }
        this.name = name;
    }

    @Override
    public void setX(double x) {
        if (x > 90.0 || x < -90.0) {
            throw new IllegalArgumentException("Coordinate x out of range");
        }
        this.x = x;
    }

    @Override
    public void setY(double y) {
        if (y > 90.0 || y < -90.0) {
            throw new IllegalArgumentException("Coordinate y out of range");
        }
        this.y = y;
    }

    public void setHighlighted(boolean highlighted) {
        this.highlighted = highlighted;
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
        return airport.getCode().equals(this.getCode());
    }

    @Override
    void paint(Graphics g, int px, int py) {
        Color prevColor = g.getColor();

        if (!highlighted) g.setColor(Color.DARK_GRAY);
        else g.setColor(Color.RED);
        g.drawString(code, px + 8, py - 8);
        g.fillRect(px - size / 2, py - size / 2, size, size);

        g.setColor(prevColor);
    }
}

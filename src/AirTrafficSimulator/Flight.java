package AirTrafficSimulator;

import java.awt.*;

public class Flight extends Figure {
    private Airport from, to;
    private Time departureTime, departureTimeDelayed;
    private int duration;

    public Flight(Airport from, Airport to, Time departureTime, int duration) {
        super(from.getX(), from.getY(), false);
        setFrom(from);
        setTo(to);
        setDepartureTime(departureTime);
        setDepartureTimeDelayed(new Time(departureTime));
        setDuration(duration);
    }

    public Airport getFrom() {
        return from;
    }

    public Airport getTo() {
        return to;
    }

    public Time getDepartureTime() {
        return departureTime;
    }

    public Time  getDepartureTimeDelayed() {
        return departureTimeDelayed;
    }

    public int getDuration() {
        return duration;
    }

    public void setFrom(Airport from) {
        if  (from == null) {
            throw new IllegalArgumentException();
        }
        this.from = from;
    }

    public void setTo(Airport to) {
        if (to == null) {
            throw new IllegalArgumentException();
        }
        this.to = to;
    }

    public void setDepartureTime(Time departureTime) {
        if (departureTime == null) {
            throw new IllegalArgumentException();
        }
        this.departureTime = departureTime;
    }

    public void setDepartureTimeDelayed(Time departureTimeDelayed) {
        this.departureTimeDelayed = departureTimeDelayed;
    }

    public void setDuration(int duration) {
        if (duration <= 0) {
            throw new IllegalArgumentException();
        }
        this.duration = duration;
    }

    @Override
    public String toString() {
        return from.getName() + " -> " + to.getName() + " | " + departureTime.toString() + " | " + duration + " min ";
    }

    //@Override
    void paint(Graphics g, int px, int py, int w, int h) {
        g.translate(w, h);
        Color prevColor = g.getColor();
        g.setColor(new Color(0, 60, 125));
        g.fillOval(px, py, size, size);
        g.setColor(prevColor);
    }
}

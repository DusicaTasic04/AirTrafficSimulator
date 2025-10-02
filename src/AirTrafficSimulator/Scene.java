package AirTrafficSimulator;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Scene extends Canvas {
    private MainFrame parentFrame;
    private final int rows = 6;
    private int cols = 8;
    AirportHighlighter airportHighlighter = new AirportHighlighter(this);

    public Scene(MainFrame parentFrame) {
        this.parentFrame = parentFrame;
        setBackground(new Color(205, 240, 255));
        new Thread(airportHighlighter).start();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                airportFlicker(e);
            }
        });
    }

    public int getRows() {
        return rows;
    }

    public MainFrame getParentFrame() {
        return parentFrame;
    }

    public void paint(Graphics g) {
        drawLines(g);
        drawFigures(g);
    }

    private void drawFigures(Graphics g) {
        for (Airport airport : Simulation.getInstance().airports.values()) {
            drawAirport(g, airport);
        }
        for (Airport airport : Simulation.getInstance().airports.values()) {
            for (Flight flight : airport.getScheduler()) {
                drawFlight(g, flight);
            }
        }
    }

    private void drawAirport(Graphics g, Airport a) {
        if (a.isVisible()) {
            int px = (int) ((a.getX() / 90.0) * (getWidth() / 2.0) + getWidth() / 2.0);
            int py = (int) (getHeight() / 2.0 - (a.getY() / 90.0) * (getHeight() / 2.0));
            a.paint(g, px, py, 0, 0);
        }
    }

    private void drawFlight(Graphics g, Flight f) {
        if (!f.isVisible()) return;

        double x0 = ((f.getFrom().getX() / 90.0) * (getWidth() / 2.0) + getWidth() / 2.0);
        double y0 = (getHeight() / 2.0 - (f.getFrom().getY() / 90.0) * (getHeight() / 2.0));

        double x1 = ((f.getTo().getX() / 90.0) * (getWidth() / 2.0) + getWidth() / 2.0);
        double y1 = (getHeight() / 2.0 - (f.getTo().getY() / 90.0) * (getHeight() / 2.0));

        double progress = (double)(
                Simulation.getInstance().clock.getSimulationTime().minutesDifference(f.getDepartureTimeDelayed())
        ) / f.getDuration();

        int px = (int)(x0 + progress * (x1 - x0));
        int py = (int)(y0 + progress * (y1 - y0));

        f.paint(g, px, py, getWidth() / 2, getHeight() / 2);
    }

    private void drawLines(Graphics g) {
        int w = getWidth();
        int h = getHeight();

        double stepX = (double) w / cols;
        double stepY = (double) h / rows;

        g.setColor(new Color(160, 160, 160));

        for (int i = 0; i <= rows; i++) {
            int y = (int) Math.round(i * stepY);
            g.drawLine(0, y, w - 1, y);
        }

        for (int i = 0; i <= cols; i++) {
            int x = (int) Math.round(i * stepX);
            g.drawLine(x, 0, x, h - 1);
        }
        g.drawLine(0, h - 1, w, h - 1);
        g.drawLine(w - 1, 0, w -1, h -1);
    }

    private void airportFlicker(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();

        for (Airport airport : Simulation.getInstance().airports.values()) {
            int px = (int) ((airport.getX() / 90.0) * (getWidth() / 2.0) + getWidth() / 2.0);
            int py = (int) (getHeight( )/ 2.0 - (airport.getY() / 90.0) * (getHeight() / 2.0));

            if (mouseX <= px + airport.getSize() / 2 && mouseX >= px - airport.getSize() / 2 &&
            mouseY <= py + airport.getSize() / 2 && mouseY >= py - airport.getSize() / 2) {
                if (airport.isVisible()) {
                    airportHighlighter.toggle(airport);
                }
                break;
            }
        }
    }
}

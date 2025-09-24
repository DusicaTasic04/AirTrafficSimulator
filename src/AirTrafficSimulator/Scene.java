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
            drawFigure(g, airport);
        }
    }

    private void drawFigure(Graphics g, Figure f) {
        if (f.isVisible()) {
            int px = (int) ((f.getX() / 90.0) * (getWidth() / 2.0) + getWidth() / 2.0);
            int py = (int) (getHeight() / 2.0 - (f.getY() / 90.0) * (getHeight() / 2.0));
            f.paint(g, px, py);
        }
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
                if (airport.isVisible()) { System.out.println(airport.getName());}
                if (airport.isVisible()) {
                    airportHighlighter.toggle(airport);
                }
                break;
            }
        }
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }

}

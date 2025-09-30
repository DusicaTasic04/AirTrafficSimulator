package AirTrafficSimulator;

import java.awt.*;

import static AirTrafficSimulator.Time.addMinutes;

public class Clock implements Runnable {
    private Time simulationTime;
    private MainFrame parentFrame;
    private volatile boolean running = true;
    private boolean isOn = false;

    Clock(MainFrame parentFrame) {
        simulationTime = new Time(0, 0, 0);
        this.parentFrame = parentFrame;
    }

    public Time getSimulationTime() {
        return simulationTime;
    }

    public void resetSimulationTime() {
        simulationTime.reset();
    }

    @Override
    public void run() {
        try {
            while (running) {
                if (isOn) {
                    simulationTime.addMinutes(2);
                    parentFrame.revalidateTimeLabel(simulationTime);
                    for (Airport a : Simulation.getInstance().airports.values())
                        for (Flight f : a.getScheduler()) {
                            paintFlight(f);
                        }

                    //noinspection BusyWait
                    Thread.sleep(200);
                }
            }
        }
        catch (InterruptedException ignored) { }
    }

    public void go() {
        isOn = true;
    }

    public void reset() {
        simulationTime.reset();
    }

    public void pause() {
        isOn = false;
    }

    public boolean isOn() {
        return isOn;
    }

    public void turnOff() {
        running = false;
    }

    private void paintFlight(Flight f) {
        if (f.getDepartureTimeDelayed().minutesDifference(simulationTime) <= 0 &&
                addMinutes(f.getDepartureTimeDelayed(), f.getDuration()).minutesDifference(simulationTime) >= 0) {
            f.setVisible(true);

            double x0 = ((f.getFrom().getX() / 90.0) * (parentFrame.scene.getWidth() - f.getSize()) / 2 - (double) f.getSize() / 2);
            double y0 = (-(f.getFrom().getY() / 90.0) * (parentFrame.scene.getHeight() - f.getSize()) / 2 - (double) f.getSize() / 2);

            double x1 = ((f.getTo().getX() / 90.0) * (parentFrame.scene.getWidth() - f.getSize()) / 2 - (double) f.getSize() / 2);
            double y1 = (-(f.getTo().getY() / 90.0) * (parentFrame.scene.getHeight() - f.getSize()) / 2 - (double) f.getSize() / 2);

            double progress = (double)(
                    simulationTime.minutesDifference(f.getDepartureTimeDelayed())
            ) / f.getDuration();

            int px = (int)(x0 + progress * (x1 - x0));
            int py = (int)(y0 + progress * (y1 - y0));
            f.paint(parentFrame.scene.getGraphics(), px, py, parentFrame.scene.getWidth() / 2, parentFrame.scene.getHeight() / 2);
            parentFrame.scene.repaint();
        }
        if (addMinutes(f.getDepartureTimeDelayed(), f.getDuration()).minutesDifference(simulationTime) <= 0) {
            if (f.isVisible()) {
                f.setVisible(false);
                parentFrame.scene.repaint();
            }
        }
    }

}

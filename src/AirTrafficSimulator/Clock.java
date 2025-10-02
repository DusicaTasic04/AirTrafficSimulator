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
                    parentFrame.scene.repaint();
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
        parentFrame.revalidateTimeLabel(simulationTime);
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
        }
        if (addMinutes(f.getDepartureTimeDelayed(), f.getDuration()).minutesDifference(simulationTime) <= 0) {
            if (f.isVisible()) {
                f.setVisible(false);
            }
        }
    }

}

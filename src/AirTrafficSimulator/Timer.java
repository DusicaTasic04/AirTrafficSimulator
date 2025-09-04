package AirTrafficSimulator;

import java.awt.*;

public class Timer extends Thread {
    private Time time;
    private boolean running;
    private final MainFrame mainFrame;

    Timer(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        time = new Time(0, 0, 0);
    }

    public void run() {
        try {
            while (!isInterrupted()) {
                synchronized (this) {
                    while (!running) {
                        wait();
                    }
                }
                //noinspection BusyWait
                sleep(1000);
                time.addSecond();
                if (time.second == 5) {
                    CountdownDialog cd = new CountdownDialog(mainFrame);
                    cd.setVisible(true);
                    if (cd.isConfirmed()) {
                        time.reset();
                    } else {
                        EventQueue.invokeLater(() -> {
                            if (mainFrame != null) {
                                mainFrame.dispose();
                            }
                        });
                        break;
                    }
                }
            }
        } catch (InterruptedException | IllegalArgumentException ignored) { }
    }

    public synchronized void go() {
        running = true;
        notify();
    }

    public synchronized void pause() {
        running = false;
    }

    public synchronized void reset() {
        time.reset();
        notify();
    }

    public boolean isRunning() {
        return running;
    }

    public String toString() {
        return String.format("%02d:%02d", time.minute, time.second);
    }
}

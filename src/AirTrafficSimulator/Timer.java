package AirTrafficSimulator;

import java.awt.*;

public class Timer extends Thread {
    private final Time time;
    private boolean running;
    private final MainFrame mainFrame;
    private final static int total = 10;

    Timer(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        time = new Time(0, 0, 0);
    }

    public void run() {
        try {
            CountdownDialog cd = null;
            while (!isInterrupted()) {
                synchronized (this) {
                    while (!running) {
                        wait();
                    }
                }
                //noinspection BusyWait
                sleep(1000);
                time.addSecond();
                int remaining = total - time.getSeconds();
                if (remaining == 5) {
                    cd = new CountdownDialog(mainFrame);
                    cd.setVisible(true);
                }
                if (remaining <= 5 && cd != null) {
                    cd.setWarningLabel("Application will close in " + remaining + " seconds");
                    if (remaining == 0) {
                        if (!cd.isConfirmed()) {
                            EventQueue.invokeLater(() -> {
                                if (mainFrame != null) {
                                    mainFrame.controller.quit();
                                }
                            });
                            break;
                        } else {
                            time.reset();
                            cd.dispose();
                            cd = null;
                        }
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
        return String.format("%02d:%02d", time.getMinutes(), time.getSeconds());
    }
}

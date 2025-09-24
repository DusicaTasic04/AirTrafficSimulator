package AirTrafficSimulator;

public class AirportHighlighter implements Runnable {
    private Airport selectedAirport;
    private Scene scene;
    private volatile boolean running = true;

    public AirportHighlighter(Scene scene) {
        this.scene = scene;
    }

    public void toggle(Airport airport) {
        if (airport == selectedAirport) {
            airport.setHighlighted(false);
            scene.repaint();
            selectedAirport = null;
            scene.getParentFrame().controller.userTimer.go();
            return;
        }

        if (selectedAirport != null) {
            selectedAirport.setHighlighted(false);
        }

        scene.getParentFrame().controller.userTimer.pause();
        selectedAirport = airport;
        running = true;
    }

    @Override
    public void run() {
        try {
            while (running) {
                if (selectedAirport != null) {
                    selectedAirport.setHighlighted(!selectedAirport.isHighlighted());
                    scene.repaint();
                }
                //noinspection BusyWait
                Thread.sleep(500);
            }
        }
        catch (InterruptedException ignored) { }
    }

    public void stopFlicker() {
        running = false;
        if (selectedAirport != null) this.selectedAirport.setHighlighted(false);
    }
}

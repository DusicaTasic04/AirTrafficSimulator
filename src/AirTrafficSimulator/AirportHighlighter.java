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
            paintAirport(airport);
            selectedAirport = null;
            scene.getParentFrame().controller.userTimer.go();
            return;
        }

        if (selectedAirport != null) {
            selectedAirport.setHighlighted(false);
            paintAirport(selectedAirport);
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
                    paintAirport(selectedAirport);
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

    private void paintAirport(Airport a) {
        int px = (int) ((a.getX() / 90.0) * (scene.getWidth() / 2.0) + scene.getWidth() / 2.0);
        int py = (int) (scene.getHeight() / 2.0 - (a.getY() / 90.0) * (scene.getHeight() / 2.0));
        a.paint(scene.getGraphics(), px, py);
    }
}

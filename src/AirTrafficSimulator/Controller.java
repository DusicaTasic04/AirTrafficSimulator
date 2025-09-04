package AirTrafficSimulator;

public class Controller {
    private MainFrame mainFrame;
    Timer userTimer;

    public Controller(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        userTimer = new Timer(mainFrame);
        userTimer.start();
        userTimer.go();
    }

    public void switchTimer() {
        if (userTimer.isRunning()) {
            userTimer.pause();
            return;
        }
        userTimer.go();
    }

    public void resetTimer() {
        userTimer.reset();
    }

    public void quit() {
        mainFrame.dispose();
        userTimer.interrupt();
    }

}

package AirTrafficSimulator;

import java.awt.*;
import java.io.File;
import java.util.logging.FileHandler;

public class Controller {
    private MainFrame mainFrame;
    Timer userTimer;

    public Controller(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        userTimer = new Timer(mainFrame);
        userTimer.start();
        userTimer.go();
    }

    public void importFlights() {
        userTimer.pause();
        try {
            ImportFileHandler importFile = new ImportFileHandler(mainFrame);
            importFile.showDialog();
        }
        catch (Exception e) {
            new ExceptionDialog(mainFrame, "<Cancel> pressed or file not found. ");
        }
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

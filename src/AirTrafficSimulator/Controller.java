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
            File file = new ImportFileHandler(mainFrame).showDialog();
            new FlightFileReader(mainFrame).readFile(file);
        }
        catch (Exception e) {
            new ExceptionDialog(mainFrame, "<Cancel> pressed or file not found. ");
        }
        userTimer.go();
    }

    public void importAirports() {
        userTimer.pause();
        try {
            File file = new ImportFileHandler(mainFrame).showDialog();
            new AirportFileReader(mainFrame).readFile(file);
        }
        catch (Exception e) {
            new ExceptionDialog(mainFrame, "<Cancel> pressed or file not found. ");
        }
        mainFrame.scene.repaint();
        userTimer.go();
    }

    public void exportFlights() {
        userTimer.pause();
        new ExportFileHandler(mainFrame).exportFlights();
        userTimer.go();
    }

    public void exportAirports() {
        userTimer.pause();
        new  ExportFileHandler(mainFrame).exportAirports();
        userTimer.go();
    }

    public void addFlight() {
        userTimer.pause();
        new NewFlightDialog(mainFrame);
        userTimer.go();
    }

    public void showFlights() {
        userTimer.pause();
        new ShowFlightDialog(mainFrame);
        userTimer.go();
    }

    public void addAirport() {
        userTimer.pause();
        new NewAirportDialog(mainFrame);
        mainFrame.scene.repaint();
        userTimer.go();
    }

    public void switchAirportVisibility(Airport a) {
        a.setVisible(!a.isVisible());
        mainFrame.scene.repaint();
    }

    public void startStopSimulation() {
        Clock c = Simulation.getInstance().clock;
        if (c.isOn()) {
            c.pause();
            userTimer.go();
            mainFrame.enableMenuBar();
        }
        else {
            c.go();
            userTimer.pause();
            mainFrame.disableMenuBar();
        }

    }

    public void resetSimulation() {
        Simulation.getInstance().clock.reset();
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
        mainFrame.scene.airportHighlighter.stopFlicker();
        Simulation.getInstance().clock.turnOff();
    }
}

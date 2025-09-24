package AirTrafficSimulator;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.FileHandler;

public class ExportFileHandler extends FileDialogHandler {
    private File file;

    public ExportFileHandler(Frame parentFrame) {
        super(parentFrame, "Save", FileDialog.SAVE);
    }

    private void saveFile() {
        try {
            this.fd.setFile("*.csv");
            this.showDialog();
            String directory = fd.getDirectory();
            String fileName = fd.getFile();
            if (directory == null || fileName == null) {
                throw new NullPointerException();
            }
            file = new File(directory + fileName);
        }
        catch (Exception e) {
            new ExceptionDialog(parentFrame, "<Cancel> pressed or cannot save file. ");
        }
    }

    public void exportAirports() {
        saveFile();
        if (file == null) {
            new ExceptionDialog(parentFrame, "<Cancel> pressed or cannot save file. ");
            return;
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Airport airport : Simulation.getInstance().airports.values()) {
                String line = airport.getName() + "," + airport.getCode() + "," + airport.getX() + "," + airport.getY();
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            new ExceptionDialog(parentFrame, "Cannot write to file. ");
        }
    }

    public void exportFlights() {
        saveFile();
        if (file == null) {
            new ExceptionDialog(parentFrame, "<Cancel> pressed or cannot save file. ");
            return;
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Airport airport : Simulation.getInstance().airports.values()) {
                for (Flight flight : airport.getScheduler()) {
                    String line = flight.getFrom().getCode() + "," + flight.getTo().getCode() + "," + flight.getDepartureTime() + "," + flight.getDuration();
                    writer.write(line);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            new ExceptionDialog(parentFrame, "Cannot write to file. ");
        }
    }
}

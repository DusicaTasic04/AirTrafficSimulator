package AirTrafficSimulator;

import java.awt.*;

public class AirportFileReader extends FileReader {

    @Override
    public void processLine(String line) {
        if (!line.matches("^[A-Z]{3},-?\\d+(\\.\\d+)?,-?\\d+(\\.\\d+)?$"))
            throw new IllegalArgumentException();

        String[] tokens = line.split(";");

        String name = tokens[0];
        double x =  Double.parseDouble(tokens[1]);
        double y = Double.parseDouble(tokens[2]);

        Simulation.getInstance().airports.put(name, new Airport(name, x, y));
    }
}

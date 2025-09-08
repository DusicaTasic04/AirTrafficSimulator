package AirTrafficSimulator;

import java.awt.*;

public class AirportFileReader extends FileReader {

    protected AirportFileReader(MainFrame parentFrame) {
        super(parentFrame);
    }

    @Override
    public void processLine(String line) {
        if (!line.matches("^[\\p{L}0-9 _.-]+,[A-Z]{3},-?\\d+(\\.\\d+)?,-?\\d+(\\.\\d+)?$"))
            throw new IllegalArgumentException("Wrong line format");

        String[] tokens = line.split(",");

        String name = tokens[0];
        String code = tokens[1];
        double x =  Double.parseDouble(tokens[2]);
        double y = Double.parseDouble(tokens[3]);

        Simulation.getInstance().addAirport(new Airport(name, code, x, y));
    }
}

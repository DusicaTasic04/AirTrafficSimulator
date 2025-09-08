package AirTrafficSimulator;

public class FlightFileReader extends FileReader {

    protected FlightFileReader(MainFrame parentFrame) {
        super(parentFrame);
    }

    @Override
    public void processLine(String line) {
        if (!line.matches("^[A-Z]{3},[A-Z]{3},[0-9]{2}:[0-9]{2},[0-9]+$"))
            throw new IllegalArgumentException();

        String[] tokens = line.split(",");

        String from =  tokens[0];
        String to =  tokens[1];
        int h =  Integer.parseInt(tokens[2].split(":")[0]);
        int m =  Integer.parseInt(tokens[2].split(":")[1]);
        Time departure = new Time(h, m, 0);
        int duration = Integer.parseInt(tokens[3]);

        Airport a1 = Simulation.getInstance().airports.get(from);
        Airport a2 = Simulation.getInstance().airports.get(to);

        Simulation.getInstance().addFlight(new Flight(a1, a2, departure, duration));
    }
}

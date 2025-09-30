package AirTrafficSimulator;

import java.util.HashMap;

public class Simulation {
    private static final Simulation instance = new Simulation();
    MainFrame frame;
    HashMap<String, Airport> airports;
    Clock clock;

    private Simulation() {
        airports = new HashMap<>();
    }

    public void setFrame(MainFrame frame) {
        this.frame = frame;
        clock = new Clock(frame);
        new Thread(clock).start();
    }

    public void addAirport(Airport airport) {
        if (airports.containsKey(airport.getCode())) {
            throw new IllegalArgumentException("Airport code already exists");
        }
        if (frame != null) frame.addCheckBox(airport);
        airports.put(airport.getCode(), airport);
    }

    public void addFlight(Flight flight) {
        if (flight.getFrom() == null || flight.getTo() == null) {
            throw new IllegalArgumentException("Flight from and to cannot be null");
        }
        if (!airports.containsKey(flight.getFrom().getCode()) || !airports.containsKey(flight.getTo().getCode())) {
            throw new IllegalArgumentException("Airport code does not exist");
        }
        if (flight.getFrom().getCode().equals(flight.getTo().getCode())) {
            throw new IllegalArgumentException("Airport From and To cannot be same");
        }
        flight.getFrom().getScheduler().schedule(flight);
    }

    public static Simulation getInstance() {
        return instance;
    }
}

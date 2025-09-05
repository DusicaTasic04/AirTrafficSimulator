package AirTrafficSimulator;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Vector;

public class Simulation {
    private static final Simulation instance = new Simulation();
    Vector<Flight> flights;
    HashMap<String, Airport> airports;

    private Simulation() {
        flights = new Vector<>();
        airports = new HashMap<>();
    }

    public void addAirport(Airport airport) {
        if (!airports.containsKey(airport.getName())) {
            throw new IllegalArgumentException();
        }
        airports.put(airport.getName(), airport);
    }

    public void addFlight(Flight flight) {
        flights.add(flight);
    }

    public static Simulation getInstance() {
        return instance;
    }
}

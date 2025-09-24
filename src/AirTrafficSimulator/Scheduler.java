package AirTrafficSimulator;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * The Scheduler class manages the order and departure times of flights at an airport.
 * It ensures a minimum gap between consecutive flights by shifting them forward in time if needed,
 * while preserving their initially planned order.
 */

public class Scheduler implements Iterable<Flight>{
    private LinkedList<Flight> flights = new LinkedList<>();
    private int minGap = 10; // in minutes

    Scheduler() {}

    public void setMinGap(int minGap) {
        this.minGap = minGap;
    }

    public int getMinGap() {
        return minGap;
    }

    public void schedule(Flight newFlight) {
        int insertIndex = 0;
        while (insertIndex < flights.size() && timeDifference(flights.get(insertIndex), newFlight) < 0) {
            insertIndex++;
        }
        flights.add(insertIndex, newFlight);

        for (int i = 1; i < flights.size(); i++) {
            Flight prev = flights.get(i - 1);
            Flight curr = flights.get(i);

            int diff = timeDifferenceDelayed(curr, prev);
            if (diff < minGap) {
                curr.getDepartureTimeDelayed().addMinutes(minGap - diff);
            }
        }
    }

    private static int timeDifference(Flight f1, Flight f2) {
        return f1.getDepartureTime().minutesDifference(f2.getDepartureTime());
    }

    private static int timeDifferenceDelayed(Flight f1, Flight f2) {
        return f1.getDepartureTimeDelayed().minutesDifference(f2.getDepartureTimeDelayed());
    }

    public Iterator<Flight> iterator() {
        return flights.iterator();
    }
}

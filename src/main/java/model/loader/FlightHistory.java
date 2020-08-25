package model.loader;

import model.data.Flight;

import java.util.ArrayList;
import java.util.List;

public class FlightHistory {
    public static List<Flight> flights = new ArrayList<>();

    public List<Flight> getFlights() {return flights;}

    public void addFlight(List<Flight> flight) {
        flights.add((Flight) flight);
    }
}

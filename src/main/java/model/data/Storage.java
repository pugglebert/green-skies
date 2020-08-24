package model.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Class to keep track of all the data currently open files
 * @author Ella Johnson
 * @since 17/08/2020
 * @version 1.0
 */
public class Storage {

    private List<Airline> airlines = new ArrayList<>();
    private List<Airport> airports = new ArrayList<>();
    private List<Route> routes = new ArrayList();

    /**
     * @return a set of Airline objects from the currently open file cast as Datatype objects.
     */
    public List<Airline> getAirlines() {return airlines;}

    /**
     * @return a set of Airport objects from the currently open file cast as Datatype objects.
     */
    public List<Airport> getAirports() {return airports;}

    /**
     * @return a set of Route object from the currently open file cast as Datatype objects.
     */
    public List<Route> getRoutes() {return routes;}

    public void setData(List<DataType> data, String type) {

        if (type.matches("Airline")) {

            for (DataType entry : data) {
                Airline airline = (Airline) entry;
                airlines.add(airline);
            }
        } else if (type.matches("Airport")) {
            for (DataType entry : data) {
                Airport airport = (Airport) entry;
                airports.add(airport);
            }
        } else if (type.matches("Route")) {
            for (DataType entry : data) {
                Route route = (Route) entry;
                routes.add(route);
            }
        } else {
            throw new IllegalArgumentException("Type must be airline, airport or route");
        }
    }
 }

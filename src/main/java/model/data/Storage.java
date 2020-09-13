package model.data;

import model.database.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * Class to keep track of all the data currently open files
 * @author Ella Johnson
 * @since 17/08/2020
 * @version 1.0
 */
public class Storage {

    private List<Airline> airlines = new ArrayList<>();
    private List<Airport> airports = new ArrayList<>();
    private List<Route> routes = new ArrayList<>();

    public List<Route> history = new ArrayList<>();

    private TreeSet<String> airportCountries = new TreeSet<>();
    private TreeSet<String> airlineCountries = new TreeSet<>();
    private TreeSet<String> airportCities = new TreeSet<>();
    private TreeSet<String> routeAirlines = new TreeSet<>();
    private TreeSet<String> routeSources = new TreeSet<>();
    private TreeSet<String> routeDestinations = new TreeSet<>();

    private SQLiteDatabase database = new SQLiteDatabase();
    /**
     * @return a list of Airline objects from the currently open file cast as Datatype objects.
     */
    public List<Airline> getAirlines() {return airlines;}

    /**
     * @return a list of Airport objects from the currently open file cast as Datatype objects.
     */
    public List<Airport> getAirports() {return airports;}

    /**
     * @return a list of Route object from the currently open file cast as Datatype objects.
     */
    public List<Route> getRoutes() {return routes;}

    /**
     * @return a list of routes in the user's history.
     */
    public List<Route> getHistory() {return history;}

    /**
     * @return a list all unique counties from the airline file.
     */
    public List<String> getAirlineCounties() {
        return new ArrayList<>(airlineCountries);
    }

    /**
     * @return a list of all unique countries from the aiport file.
     */
    public List<String> getAirportCountries() {
        return new ArrayList<>(airportCountries);
    }

    /**
     * @return a list of all unique cities from the airport file.
     */
    public List<String> getAirportCities() {
        return new ArrayList<>(airportCities);
    }

    /**
     * @return a list of all unique airline codes from the route file.
     */
    public List<String> getRouteAirlines() {
        return new ArrayList<>(routeAirlines);
    }

    /**
     * @return a list of all unique source airports from the route file.
     */
    public List<String> getRouteSources() {
        return new ArrayList<>(routeSources);
    }

    /**
     * @return a list of all unique destination airports from the route file.
     */
    public List<String> getRouteDestinations() {
        return new ArrayList<>(routeDestinations);
    }

    /**
     * Add an arrayList of routes to the history list.
     * @param routes an arrayList of routes.
     */
    public void addToHistory(ArrayList<Route> routes) {
        history.addAll(routes);
    }

    /**
     * Add a list of data from a file to storage.
     * @param data the list of data.
     * @param type type of data to be stored.
     */
    public void setData(List<DataType> data, String type) throws SQLException, ClassNotFoundException {

        if (type.matches("Airline")) {
            database.dropTable("airlines");
            database.closeAutoCommite();
            for (DataType entry : data) {
                Airline airline = (Airline) entry;
                if (airline != null) {
                    airlines.add(airline);
                    airlineCountries.add(airline.getCountry());
                    database.addAirlines(airline);

                }
            }
            database.startCommite();
        } else if (type.matches("Airport")) {
            database.dropTable("airports");
            database.closeAutoCommite();
            for (DataType entry : data) {
                Airport airport = (Airport) entry;
                airports.add(airport);
                database.addAirports(airport);
                if (airport != null) {
                    airportCountries.add(airport.getCountry());
                    airportCities.add(airport.getCity());
                }
            }
            database.startCommite();
        } else if (type.matches("Route")) {
            database.dropTable("routes");
            database.closeAutoCommite();
            for (DataType entry : data) {
                Route route = (Route) entry;
                routes.add(route);
                database.addRoutes(route);
                routeAirlines.add(route.getAirlineName());
                routeSources.add(route.getSourceAirport());
                routeDestinations.add(route.getDestinationAirport());
            }
            database.startCommite();
        } else {
            throw new IllegalArgumentException("Type must be airline, airport or route");
        }
    }
 }

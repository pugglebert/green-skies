package model.data;

import io.cucumber.java.hu.Ha;
import model.database.SQLiteDatabase;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Class to keep track of all the data that has been uploaded to the application, also interact with
 * database.
 *
 * @author Ella Johnson, Lambert, Hayley Krippner
 * @since 2020-09-15
 * @version 1.3
 */
public class Storage {

  /** A list of all the airlines that have been uploaded to the application. */
  private HashMap<String, List<Airline>> airlineFiles = new HashMap<>();

  /** The name of the airline files which is currently in use. */
  private String currentAirlineFile;

  /** A list of all the airports that have been uploaded to the application. */
  private HashMap<String, List<Airport>> airportFiles = new HashMap<>();

  /** The name of the airport file which is2 currently in use. */
  private String currentAirportFile;

  /** A list of all the routes that have been uploaded to the application. */
  private HashMap<String, List<Route>> routeFiles = new HashMap<>();

  /** The name of the route file which is currently in use. */
  private String currentRouteFile;

  /** Temporary list of routes used when adding routes to history. */
  private List<Route> tempRoutes = new ArrayList<>();

  /** A list of all the values calculated for the distances of the routes in the history. */
  private List<Double> analyseDistanceResult = new ArrayList<>();

  /** A list of all the values calculated for the emissions of the routes in the history. */
  private List<Double> analyseEmissionResult = new ArrayList<>();

  /** A list of all the routes that have been added to the user's personal history. */
  private List<Route> history = new ArrayList<>();

  /**
   * A HashMap of all the source airports the user has visited and the number of times they have
   * visited them.
   */
  private HashMap<String, Integer> historySrcAirports = new HashMap<>();

  /**
   * A HashMap of all the destination airports the user has visited and the number of times they
   * have visited them.
   */
  private HashMap<String, Integer> historyDestAirports = new HashMap<>();

  /** The database in which data added to the application is stored. */
  private SQLiteDatabase database = new SQLiteDatabase();

  /** @return a HashMap with filename as key and List of Airline datatypes as values. */
  public HashMap<String, List<Airline>> getAirlineFiles() {
    return airlineFiles;
  }

  /** @return the name of the currently open Airline file. */
  public String getCurrentAirlineFile() {return currentAirlineFile;}

  public List<Airline> getAirlines() {
    return airlineFiles.get(currentAirlineFile);
  }

//  /** This method reset airlines list. */
//  public void resetAirlinesList() {
//    airlines = new ArrayList<>();
//  }

  /** @return a HashMap with filename as key and List of Airport datatypes as values. */
  public HashMap<String, List<Airport>> getAirportFiles() {
    return airportFiles;
  }

  /** @return the name of the currently open Airport file. */
  public String getCurrentAirportFile() {
    return currentAirportFile;
  }

  public List<Airport> getAirports() {
    return airportFiles.get(currentAirportFile);
  }
//  /** This method reset airports list. */
//  public void resetAirportsList() {
//    airports = new ArrayList<>();
//  }

  /** @return a HashMap with filename as key and List of Route datatypes as values. */
  public HashMap<String, List<Route>> getRouteFiles() {
    return routeFiles;
  }

  /** @return the name of the currently open Route file. */
  public String getCurrentRouteFile() {
    return currentRouteFile;
  }

  public List<Route> getRoutes() {
    return routeFiles.get(currentRouteFile);
  }

//  /** This method reset routes list. */
//  public void resetRoutesList() {
//    routes = new ArrayList<>();
//  }

  /** @return a list of Route object from route to add to history. */
  public List<Route> getTempRoutes() {
    return tempRoutes;
  }

  /** @return a list of routes in the user's history. */
  public List<Route> getHistory() {
    return history;
  }

  /** @return a list of distance has been analysed . */
  public List<Double> getAnalyseDistanceResult() {
    return analyseDistanceResult;
  }

  /** @ADD distance has been analysed into a list . */
  public void setAnalyseDistanceResult(double distance) {
    analyseDistanceResult.add(distance);
  }

  /** @return a list of emissions has been analysed . */
  public List<Double> getAnalyseEmissionResult() {
    return analyseEmissionResult;
  }

  /** @ADD emissions has been analysed into a list . */
  public void setAnalyseEmissionResult(double emission) {
    analyseEmissionResult.add(emission);
  }

  /**
   * This method adds an arrayList of routes to the history list.
   *
   * @param routes An arrayList of routes.
   */
  public void addToHistory(Route routes) {
    history.add(routes);
  }

  /**
   * This method adds a list of data from a file to storage.
   *
   * @param data The list of data.
   * @param type Type of data to be stored.
   */
  public void setData(List<DataType> data, String type, String filename) {

    if (type.matches("Airline")) {
//      database.initialiseTable("airlines");
//      database.closeAutoCommite();
      List<Airline> airlines = new ArrayList<>();
      if (filename == null) {
        filename = currentAirlineFile;
      }
      for (DataType entry : data) {
        Airline airline = (Airline) entry;
        if (airline != null) {
          airlines.add(airline);
//          database.addAirlines(airline);
        }
      }
      airlineFiles.put(filename, airlines);
//      database.startCommite();
    } else if (type.matches("Airport")) {
      List<Airport> airports = new ArrayList<>();
      if (filename == null) {
        filename = currentAirportFile;
      }
//      database.initialiseTable("airports");
//      database.closeAutoCommite();
      for (DataType entry : data) {
        Airport airport = (Airport) entry;
        airports.add(airport);
//        database.addAirports(airport);
      }
//      database.startCommite();
      airportFiles.put(filename, airports);
    } else if (type.matches("Route")) {
      List<Route> routes = new ArrayList<>();
      if (filename == null) {
        filename = currentRouteFile;
      }
//      database.initialiseTable("routes");
//      database.closeAutoCommite();
      for (DataType entry : data) {
        Route route = (Route) entry;
        routes.add(route);
//        database.addRoutes(route);
      }
      routeFiles.put(filename, routes);
//      database.startCommite();
    } else {
      throw new IllegalArgumentException("Type must be airline, airport or route");
    }
  }

  /**
   * This method initilises storage with data from database after user start the application
   *
   * @throws SQLException This throws an SQLException.
   */
  public void initialiseStorage() throws SQLException {
    database.initialiseStorage(this);
  }

  /**
   * This method updates the count of source aiport visits in the flight history.
   *
   * @param airportName The name of the source airport of the route being added to history.
   */
  public void addToHistorySrcAirports(String airportName) {
    if (historySrcAirports.containsKey(airportName)) {
      historySrcAirports.put(airportName, historySrcAirports.get(airportName) + 1);
    } else {
      historySrcAirports.put(airportName, 1);
    }
  }

  /**
   * This method gets the historySrcAirports.
   *
   * @return A HashMap with the names of source airports as keys and the number of times they have
   *     been visited as the value.
   */
  public HashMap<String, Integer> getHistorySrcAirports() {
    return historySrcAirports;
  }

  /**
   * This method updates the count of destination aiport visits in the flight history.
   *
   * @param airportName The name of the destination airport of the route being added to history.
   */
  public void addToHistoryDestAirports(String airportName) {
    if (historyDestAirports.containsKey(airportName)) {
      historyDestAirports.put(airportName, historyDestAirports.get(airportName) + 1);
    } else {
      historyDestAirports.put(airportName, 1);
    }
  }

  /**
   * This method gets the historyDestAirports.
   *
   * @return A HashMap of the names of destination airports as key and the number of times they have
   *     been added to history as value.
   */
  public HashMap<String, Integer> getHistoryDestAirports() {
    return historyDestAirports;
  }
}

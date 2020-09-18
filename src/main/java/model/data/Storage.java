package model.data;

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
  private List<Airline> airlines = new ArrayList<>();

  /** A list of all the airports that have been uploaded to the application. */
  private List<Airport> airports = new ArrayList<>();

  /** A list of all the routes that have been uploaded to the application. */
  private List<Route> routes = new ArrayList<>();

  /** Temporary list of routes used when adding routes to history. */
  private List<Route> tempRoutes = new ArrayList<>();

  /** A list of all the values calculated for the distances of the routes in the history. */
  private List<Double> analyseDistanceResult = new ArrayList<Double>();

  /** A list of all the values calculated for the emissions of the routes in the history. */
  private List<Double> analyseEmissionResult = new ArrayList<Double>();

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

  /** @return a list of Airline objects from the currently open file cast as Datatype objects. */
  public List<Airline> getAirlines() {
    return airlines;
  }

  /**This method reset airlines list. */
  public void resetAirlinesList(){
    airlines = new ArrayList<Airline>();
  }

  /** @return a list of Airport objects from the currently open file cast as Datatype objects. */
  public List<Airport> getAirports() {
    return airports;
  }

  /**This method reset airports list. */
  public void resetAirportsList(){
    airports = new ArrayList<Airport>();
  }

  /** @return a list of Route object from the currently open file cast as Datatype objects. */
  public List<Route> getRoutes() {
    return routes;
  }

  /**This method reset routes list. */
  public void resetRoutesList(){
    routes = new ArrayList<Route>();
  }

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
  public void setData(List<DataType> data, String type) {

    if (type.matches("Airline")) {
//      airlines = new ArrayList<Airline>();
      database.initialiseTable("airlines");
      database.closeAutoCommite();
      for (DataType entry : data) {
        Airline airline = (Airline) entry;
        if (airline != null) {
          airlines.add(airline);
          database.addAirlines(airline);
        }
      }
      database.startCommite();
    } else if (type.matches("Airport")) {
//      airports = new ArrayList<Airport>();
      database.initialiseTable("airports");
      database.closeAutoCommite();
      for (DataType entry : data) {
        Airport airport = (Airport) entry;
        airports.add(airport);
        database.addAirports(airport);
        if (airport != null) {}
      }
      database.startCommite();
    } else if (type.matches("Route")) {
      database.initialiseTable("routes");
      database.closeAutoCommite();
      for (DataType entry : data) {
        Route route = (Route) entry;
        routes.add(route);
        database.addRoutes(route);
      }
      database.startCommite();
    } else {
      throw new IllegalArgumentException("Type must be airline, airport or route");
    }
  }

  /**
   * This method initilises storage with data from database after user start the application
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  public void initialiseStorage() throws SQLException, ClassNotFoundException {
    database.initialiseStorage(this);
  }

  /**
   * This method updates the count of source aiport visits in the flight history.
   *
   * @param airportName , the name of the source airport of the route being added to history.
   * @param counter , the number of times the route needs to be added to history.
   */
  public void addToHistorySrcAirports(String airportName, int counter) {
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
   * @param airportName , the name of the destination airport of the route being added to history.
   * @param counter , the number of times the route needs to be added to history.
   */
  public void addToHistoryDestAirports(String airportName, int counter) {
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

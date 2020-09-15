package model.data;

import model.database.SQLiteDatabase;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Class to keep track of all the data currently open files, also interact with database.
 *
 * @author Ella Johnson, Lambert, Hayley Krippner
 * @since 15/09/2020
 * @version 1.3
 */
public class Storage {

  private List<Airline> airlines = new ArrayList<>();
  private List<Airport> airports = new ArrayList<>();
  private List<Route> routes = new ArrayList<>();
  private List<Route> tempRoute = new ArrayList<>();
  public List<Double> analyseDistanceResult = new ArrayList<Double>();
  public List<Double> analyseEmissionResult = new ArrayList<Double>();
  public List<Route> history = new ArrayList<>();
  private HashMap<String, Integer> historySrcAirports = new HashMap<>();
  private HashMap<String, Integer> historyDestAirports = new HashMap<>();

  private SQLiteDatabase database = new SQLiteDatabase();
  /** @return a list of Airline objects from the currently open file cast as Datatype objects. */
  public List<Airline> getAirlines() {
    return airlines;
  }

  /** @return a list of Airport objects from the currently open file cast as Datatype objects. */
  public List<Airport> getAirports() {
    return airports;
  }

  /** @return a list of Route object from the currently open file cast as Datatype objects. */
  public List<Route> getRoutes() {
    return routes;
  }

  /** @return a list of Route object from the currently open file cast as Datatype objects. */
  public List<Route> getTempRoutes() {
    return tempRoute;
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
   * Add an arrayList of routes to the history list.
   *
   * @param routes an arrayList of routes.
   */
  public void addToHistory(Route routes) {
    history.add(routes);
  }

  /**
   * Add a list of data from a file to storage.
   *
   * @param data the list of data.
   * @param type type of data to be stored.
   */
  public void setData(List<DataType> data, String type)
      throws SQLException, ClassNotFoundException {

    if (type.matches("Airline")) {
      airlines = new ArrayList<Airline>();
      //            database.dropTable("airlines");
      //            database.closeAutoCommite();
      for (DataType entry : data) {
        Airline airline = (Airline) entry;
        if (airline != null) {
          airlines.add(airline);
          //                    database.addAirlines(airline);
        }
      }
      //            database.startCommite();
    } else if (type.matches("Airport")) {
      airports = new ArrayList<Airport>();
      //            database.dropTable("airports");
      //            database.closeAutoCommite();
      for (DataType entry : data) {
        Airport airport = (Airport) entry;
        airports.add(airport);
        //                database.addAirports(airport);
        if (airport != null) {}
      }
      //            database.startCommite();
    } else if (type.matches("Route")) {
      routes = new ArrayList<Route>();
      //            database.dropTable("routes");
      //            database.closeAutoCommite();
      for (DataType entry : data) {
        Route route = (Route) entry;
        routes.add(route);
        //                database.addRoutes(route);

      }
      //            database.startCommite();
    } else {
      throw new IllegalArgumentException("Type must be airline, airport or route");
    }
  }

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
   * @return
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
   * @return
   */
  public HashMap<String, Integer> getHistoryDestAirports() {
    return historyDestAirports;
  }
}

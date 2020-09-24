package model.loader;

import model.data.Route;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to process route data which has been extracted from a file by Loader class. Each entry in
 * each line is checked for errors. If the line has no errors than a Route object is created with
 * attributes determined by the data in the line. If the line contains an error this is added to the
 * ErrorCounter and the parser moves onto the next line without creating a route object.
 *
 * @author Ella Johnson
 * @version 1.0
 * @since 11/08/2020
 */
public class RouteParser extends Parser {

  /**
   * * Codes corresponding to each Route to check for duplicates. The external ArrayList indexed by
   * airline ID and the internal ArrayList contains {sourceID, destinationID} arrays for all Routes
   * with that airline ID.
   */
  private final ArrayList<ArrayList<int[]>> routeCodes = new ArrayList<>();

  /** Variable name to represent line index */
  private final int airline = 0,
      airlineID = 1,
      sourceAirport = 2,
      sourceAirportID = 3,
      destinationAirport = 4,
      destinationAirportID = 5,
      codeshare = 6,
      stops = 7,
      equipment = 8;

  /**
   * This method initializes error collection and calls dataParser method to begin processing data.
   *
   * @param dataFile ArrayList of a string for each line in the file.
   */
  public RouteParser(ArrayList<String> dataFile, List<Route> existingRoutes) {
    super(dataFile, 12);
    for (Route route : existingRoutes) {
      addToRouteCodes(route);
    }
    dataParser();
  }

  /**
   * This method adds a route to an array containing the routes source and destination airport IDs
   * to the internal ArrayList which is found at an index of the external ArrayList which
   * corresponds to the Route's airline ID.
   *
   * @param route The Route from which to get the code to add to RouteCodes.
   */
  private void addToRouteCodes(Route route) {
    int[] routeCode = new int[2];
    routeCode[0] = route.getSourceAirportID();
    routeCode[1] = route.getDestinationAirportID();
    int index = route.getAirlineID();
    while (routeCodes.size() < index + 1) {
      routeCodes.add(new ArrayList<>());
    }
    routeCodes.get(index).add(routeCode);
  }

  /** This method initializes the error lookup array with message for each error code. */
  @Override
  protected void initErrorLookup() {
    errorLookup[0] = "Wrong number of parameters";
    errorLookup[1] = "Invalid airline code";
    errorLookup[2] = "Invalid airline ID";
    errorLookup[3] = "Invalid source airport code";
    errorLookup[4] = "Invalid source airport ID";
    errorLookup[5] = "Invalid destination airport code";
    errorLookup[6] = "Invalid destination airport ID";
    errorLookup[7] = "Invalid value for codeshare";
    errorLookup[8] = "Invalid value for number of stops";
    errorLookup[9] = "Invalid equipment code";
    errorLookup[10] = "Duplicate route";
    errorLookup[11] = "Unknown error";
  }

  /**
   * This method is called when RouteParser is initialized. Calls validate method to check each
   * line. If line is valid, creates Route object with attributes from line and adds route to routes
   * set.
   */
  @Override
  protected void dataParser() {
    for (String dataLine : dataFile) {
      if (totalErrors > 200) {
        totalErrors = 0;
        throw new RuntimeException("File rejected: more than 200 lines contain errors");
      }
      parseLine(dataLine);
    }
  }

  /**
   * This method calls the validator method to check a single line and then add that line to
   * parserData if it is valid.
   *
   * @param dataLine Line to be checked and added to parserData.
   */
  protected void parseLine(String dataLine) {
    String[] line = dataLine.split(",");
    if (validater(line)) {
      try {
        Route route =
            new Route(
                line[airline],
                Integer.parseInt(line[airlineID]),
                line[sourceAirport],
                Integer.parseInt(line[sourceAirportID]),
                line[destinationAirport],
                Integer.parseInt(line[destinationAirportID]),
                line[codeshare],
                Integer.parseInt(line[stops]),
                line[equipment].split(" "));
        addRoute(route);
      } catch (Exception e) {
        errorCounter(11);
      }
    }
  }

  /**
   * This method checks that line has expected number of entries and calls isValid method to check
   * that each token on the line matches the expected pattern.
   *
   * @param line A string made up of comma-seperated tokens representing data about a route
   * @return True if all tokens are valid, false otherwise
   */
  @Override
  protected boolean validater(String[] line) {
    if (line.length != 9) {
      errorCounter(0);
      return false;
    }

    changeNulls(line);

    if (!Validator.isAirlineValid(line[airline])) {
      errorCounter(1);
      return false;
    }

    if (!Validator.isAirlineIDValid(line[airlineID])) {
      errorCounter(2);
      return false;
    }

    if (!Validator.isAirportValid(line[sourceAirport])) {
      errorCounter(3);
      return false;
    }

    if (!Validator.isAirportIDValid(line[sourceAirportID])) {
      errorCounter(4);
      return false;
    }

    if (!Validator.isAirportValid(line[destinationAirport])) {
      errorCounter(5);
      return false;
    }

    if (!Validator.isAirportIDValid(line[destinationAirportID])) {
      errorCounter(6);
      return false;
    }

    if (!Validator.isCodeshareValid(line[codeshare])) {
      errorCounter(7);
      return false;
    }

    if (!Validator.isStopsValid(line[stops])) {
      errorCounter(8);
      return false;
    }

    if (!Validator.isEquipmentValid(line[equipment])) {
      errorCounter(9);
      return false;
    }

    return true;
  }

  /**
   * This method replaces '\N' in string with the value 0.
   *
   * @param line Array to replace nulls in.
   */
  protected void changeNulls(String[] line) {
    if (line[airlineID].equals("\\N")) {
      line[airlineID] = "0";
    }

    if (line[sourceAirportID].equals("\\N")) {
      line[sourceAirportID] = "0";
    }

    if (line[destinationAirportID].equals("\\N")) {
      line[destinationAirportID] = "0";
    }
  }

  /**
   * This method checks for duplicates in data. If there are no duplicates, addes route to data.
   *
   * @param newRoute Route to be added.
   */
  private void addRoute(Route newRoute) {
    boolean inData = false;
    int[] newCode = new int[2];
    newCode[0] = newRoute.getSourceAirportID();
    newCode[1] = newRoute.getDestinationAirportID();
    if (routeCodes.size() > newRoute.getAirlineID()) {
      ArrayList<int[]> searchList = routeCodes.get(newRoute.getAirlineID());
      for (int[] existingCode : searchList) {
        if (existingCode[0] == newCode[0] && existingCode[1] == newCode[1]) {
          inData = true;
          errorCounter(10);
          break;
        }
      }
    }
    if (!inData) {
      parserData.add(newRoute);
      addToRouteCodes(newRoute);
    }
  }
}

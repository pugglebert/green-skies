package model.loader;

import model.data.Route;
import java.util.ArrayList;
import java.util.List;

// TODO: check all method comments start with "This method ..."

/**
 * Class to process route data which has been extracted from a file by Loader class. Each entry in
 * each line is checked for errors. If the line has no errors than a Route object is created with
 * attributes determined by the data in the line. If the line contains an error this is added to the
 * ErrorCounter and the parser moves onto the next line without creating a route object.
 *
 * @author Ella Johnson
 * @since 11/08/22
 */
public class RouteParser extends Parser {

    /***
     * Codes corresponding to each Route to check for duplicates. The external ArrayList indexed by airline
     * ID and the internal ArrayList contains {sourceID, destinationID} arrays for all
     * Routes with that airline ID.
     */
    private ArrayList<ArrayList<int[]>> routeCodes = new ArrayList<ArrayList<int[]>>();

    /** Alphabetical name to represent line index */
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
     * Initializes error collection and calls dataParser method to begin processing data.
     * @param dataFile ArrayList of a string for each line in the file.
     */
    public RouteParser(ArrayList<String> dataFile, List<Route> existingRoutes) {
        super(dataFile, 12);
        for (Route route : existingRoutes) {
            addToRouteCodes(route);
        }
        try {
            dataParser();
        } catch (RuntimeException e) {
            throw e;
        }
    }

    /**
     * Add an array containing the routes source and destination airport IDs to the internal
     * ArrayList which is found at an index of the external ArrayList which corresponds to the
     * Route's airline ID.
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

  @Override
  /** Initializes error lookup array with message for each error code. */
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
     * Is called when RouteParser is initialized. Calls validate method to check each line. If line is valid, creates
     * Route object with attributes from line and adds route to routes set.
     */
    @Override
    protected void dataParser() {
        for (String dataLine : dataFile) {
            if (totalErrors > 200) {
                throw new RuntimeException("File rejected: more than 100 lines contain errors");
            }
            parseLine(dataLine);
        }
    }

    /**
     * Call the validator method to check a single line and then add that line to parserData if it is valid.
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
   * Checks that line has expected number of entries and calls isValid method to check that each
   * token on the line matches the expected pattern.
   *
   * @param line a string made up of comma-seperated tokens representing data about a route
   * @return returns true if all tokens are valid, false otherwise
   */
  @Override
  public boolean validater(String[] line) {
    if (line.length != 9) {
      errorCounter(0);
      return false;
    }

    changeNulls(line);

    if (!isAirlineValid(line[airline])) {
      errorCounter(1);
      return false;
    }

    if (!isAirlineIDValid(line[airlineID])) {
      errorCounter(2);
      return false;
    }

    if (!isAirportValid(line[sourceAirport])) {
      errorCounter(3);
      return false;
    }

    if (!isAirportIDValid(line[sourceAirportID])) {
      errorCounter(4);
      return false;
    }

    if (!isAirportValid(line[destinationAirport])) {
      errorCounter(5);
      return false;
    }

    if (!isAirportIDValid(line[destinationAirportID])) {
      errorCounter(6);
      return false;
    }

    if (!isCodeshareValid(line[codeshare])) {
      errorCounter(7);
      return false;
    }

    if (!isStopsValid(line[stops])) {
      errorCounter(8);
      return false;
    }

    if (!isEquipmentValid(line[equipment])) {
      errorCounter(9);
      return false;
    }

    return true;
  }

    /**
     * Replace '\N' in string with the value 0.
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
   * Verify whether string is a valid ICAO or IATA airport code
   *
   * @param airport the string to be verified
   * @return true if string matches ICAO or IATA format, false otherwise
   */
  protected boolean isAirportValid(String airport) {
    return (airport.length() == 3 || airport.length() == 4) && airport.matches("[A-Z]+");
  }

  /**
   * Verify whether string is a valid openflight airport id code *
   *
   * @param airportID the string to be verified *
   * @return true if string matches openflights format, false otherwise
   */
  protected boolean isAirportIDValid(String airportID) {
    return (airportID.length() <= 5 && airportID.matches("[0-9]+"));
  }

  /**
   * Verify whether string is a valid ICAO or IATA airlien code
   *
   * @param airline the string to be verified
   * @return true if string matches ICAO or IATA format, false otherwise
   */
  protected boolean isAirlineValid(String airline) {
    return (airline.length() == 2 || airline.length() == 3) && airline.matches("[0-9A-Z]+");
  }

  /**
   * Verify whether string is a valid openflights airline id code
   *
   * @param airlineID the string to be verified
   * @return true if string matches openflights format, false otherwise
   */
  protected boolean isAirlineIDValid(String airlineID) {
    return airlineID.length() <= 5 && airlineID.matches("[0-9]+");
  }

  /**
   * Verify whether sting is "Y" (if route is a codeshare) or empty (if it is not)
   *
   * @param codeshare the string to be tested
   * @return true if string is "Y" or empty, false otherwise
   */
  protected boolean isCodeshareValid(String codeshare) {
    return codeshare.isEmpty() || codeshare.equals("Y");
  }

  /**
   * Verify whether string stops represents a number less than 10
   *
   * @param stops the string to be verified
   * @return true if string is a number less than 10, false otherwise
   */
  protected boolean isStopsValid(String stops) {
    return stops.matches("[0-9]");
  }

  /**
   * Verify whether equipment is a sting of whitespace separated 3 character plane codes
   *
   * @param equipment the string to be tested
   * @return returns true if string matches expected format, false otherwise
   */
  protected boolean isEquipmentValid(String equipment) {
    String[] equipArray = equipment.split(" ");
    for (String plane : equipArray) {
      if (!(plane.length() == 3 && plane.matches("[0-9A-Z]+"))) {
        return false;
      }
    }
    return true;
  }

    /**
     * Checks for duplicates in data. If there are no duplicates, addes route to data.
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

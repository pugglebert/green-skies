package model.loader;

import model.data.Airline;
import model.data.DataType;

import java.util.List;

/**
 * Class to process airline data which has been extracted from a file by Loader class. Each entry in
 * each line is checked for errors. If the line has no errors than a Airline object is created with
 * attributes determined by the data in the line. If the line contains an error this is added to the
 * ErrorCounter and the parser moves onto the next line without creating a route object.
 *
 * @author Nathan Huynh
 * @version 1.0
 * @since 11/08/2020
 */
public class AirlineParser extends Parser {

  /** Variable name to represent line index */
  private final int airlineID = 0,
      name = 1,
      alias = 2,
      IATA = 3,
      ICAO = 4,
      callsign = 5,
      country = 6,
      activeStatus = 7;

  /**
   * This method initializes error collection and calls dataParser method to begin processing data.
   *
   * @param dataFile ArrayList of a string for each line in the file.
   */
  public AirlineParser(List<String> dataFile, List<Airline> existingAirlines) {
    super(dataFile, 11);
    for (Airline airline : existingAirlines) {
      addAirLine(airline.getAirlineID(), airline);
    }
    dataParser();
  }

  /** This method initializes error lookup array with message for each error code. */
  @Override
  protected void initErrorLookup() {
    errorLookup[0] = "Not enough parameters";
    errorLookup[1] = "Duplicate airline";
    errorLookup[2] = "Invalid airline ID";
    errorLookup[3] = "Invalid airport name";
    errorLookup[4] = "Invalid ailias";
    errorLookup[5] = "Invalid IATA code";
    errorLookup[6] = "Invalid ICAO code";
    errorLookup[7] = "Invalid callsign";
    errorLookup[8] = "Invalid country";
    errorLookup[9] = "Invalid status";
    errorLookup[10] = "Unknown error";
  }

  /**
   * This method iterate throught each line of input file and calls parseData if there are less than 200 erors
   * in the file. If there are more than 200 errors raises exception.
   */
  @Override
  public void dataParser() {
    for (String dataLine : dataFile) {
      if (totalErrors > 200) {
        totalErrors = 0;
        throw new RuntimeException("File rejected: more than 200 lines contain errors");
      }
      parseLine(dataLine);
    }
  }

  /**
   * This method splits the line into data segments, calls the validator method to check each segment,
   * and then adds that line to paserData if it is valid.
   * @param dataLine line from file to split into segments.
   */
  protected void parseLine(String dataLine) {
    dataLine = dataLine.replaceAll("[\"]", "");
    String[] line = dataLine.split(",");

    if (validater(line)) {
      try {
        boolean active = false;
        if (line[activeStatus].matches("Y")) {
          active = true;
        }
        Airline airline =
            new Airline(
                Integer.parseInt(line[airlineID]),
                line[name],
                line[alias],
                line[IATA],
                line[ICAO],
                line[callsign],
                line[country],
                active);
        addAirLine(airline.getAirlineID(), airline);
      } catch (Exception e) {
        errorCounter(10);
      }
    }
  }

  /**
   * This method add airline to index matches with airLineID. First check if there are any airline
   * currently sit at index. If it is null then replace with airline param. If parserset size is too
   * small then init it with null value. If there is an airline at index then check if the airline
   * is the same with the one we want to add. If it is the same then treat as duplicate (do nothing)
   * If is is not then add to error.
   *
   * @param airlineID airline ID we want to add
   * @param airline Airline Object we wanted to add
   */
  private void addAirLine(int airlineID, Airline airline) {
    if (airlineID >= parserData.size()) {
      int i = parserData.size();
      while (i < airlineID + 2) {
        parserData.add(null);
        i++;
      }
    }
    if (parserData.get(airlineID) == null) {
      parserData.set(airlineID, airline);
    } else if (parserData.get(airlineID).equals(airline)) {
      errorCounter(1); // Have the same airline
    } else {
      errorCounter(11); // Airline exist with same ID
    }
  }

  /**
   * This method checks that line has expected number of entries and calls isValid method to check
   * that each token on the line matches the expected pattern.
   *
   * @param line A string made up of comma-seperated tokens representing data about a route
   * @return True if all tokens are valid, false otherwise
   */
  protected boolean validater(String[] line) {

    boolean isValid = true;
    if (line.length != 8) {
      errorCounter(0);
      return false;
    }

    if (!Validator.isAirlineIDValid(line[airlineID])) {
      errorCounter(2);
      return false;
    }

    if (!Validator.isAirlineNameValid(line[name])) {
      errorCounter(3);
      return false;
    }

    if (!Validator.isAliasValid(line[alias])) {
      errorCounter(4);
      return false;
    }

    if (!Validator.isAirlineIATAValid(line[IATA])) {
      errorCounter(5);
      return false;
    }

    if (!Validator.isAirlineICAOValid(line[ICAO])) {
      errorCounter(6);
      return false;
    }

    if (!Validator.isCallsignValid(line[callsign])) {
      errorCounter(7);
      return false;
    }

    if (!Validator.isCountryValid(line[country])) {
      errorCounter(8);
      return false;
    }

    if (!Validator.isActiveStatusValid(line[activeStatus])) {
      errorCounter(9);
      return false;
    }
    return true;
  }

  /**
   * This method check if id is valid.
   *
   * @param airlineID airline id as a string.
   * @return true if valid, false if invalid.
   */
  private boolean isIdValid(String airlineID) {

    // airline ID Duplication and Negative check
    try {
      if (Integer.parseInt(airlineID) <= 0) {
        errorCounter(1);
        return false;
      }
      for (DataType data : parserData) {
        if (data == null) {
          continue;
        }
        Airline airline = (Airline) data;
        if (airline.getAirlineID() == Integer.parseInt(airlineID)) {
          errorCounter(1);
          return false;
        }
      }
    } catch (Exception e) {
      errorCounter(2);
      return false;
    }
    return true;
  }

  /**
   * This method checks if the name is valid.
   *
   * @param name airline name as a string.
   * @return true if valid, false if invalid.
   */
  private boolean isNameValid(String name) {
    if (!name.matches("[a-zA-Z0-9 -.]+")) {
      errorCounter(3);
      return false;
    }
    return true;
  }

  /**
   * This method checks if the city is valid.
   *
   * @param alias airline city as a string.
   * @return true if valid, false if invalid.
   */
  private boolean isAliasValid(String alias) {
    if (!alias.matches("(\\\\N)|([\\w ]+)|(^$)")) {
      errorCounter(4);
      return false;
    }
    return true;
  }

  /**
   * This method checks if the IATA is valid.
   *
   * @param IATA airline IATA as a string.
   * @return true if valid, false if invalid.
   */
  private boolean isIATAValid(String IATA) {

    // airline IATA check
    if (!IATA.matches("-|([A-Z0-9]{2})|(^$)")) {
      errorCounter(5);
      return false;
    }

    return true;
  }

  /**
   * This method checks if the ICAO is valid.
   *
   * @param ICAO airline ICAO as a string.
   * @return true if valid, false if invalid.
   */
  private boolean isICAOValid(String ICAO) {

    if (!ICAO.matches("(\\\\N)|(N/A)|([A-Z0-9]{3})|(^$)")) {
      errorCounter(6);
      return false;
    }

    return true;
  }

  /**
   * This method checks if the callsign is valid.
   *
   * @param callsign airline latitude as a string.
   * @return true if valid, false if invalid.
   */
  private boolean isCallsignValid(String callsign) {
    // airline callsign check
    if (!callsign.matches("([A-Za-z -]+)|(^$)")) {
      errorCounter(7);
      return false;
    }
    return true;
  }

  /**
   * This method checks if the country is valid.
   *
   * @param country airline country as a string.
   * @return true if valid, false if invalid.
   */
  private boolean isCountryValid(String country) {
    // ISO 3166-1 codes not implemented
    if (!country.matches("(\\\\N)|([a-zA-Z ]+)|(^$)")) {
      errorCounter(8);
      return false;
    }
    return true;
  }

  /**
   * This method checks if the activeStatus is valid.
   *
   * @param activeStatus as a string.
   * @return true if valid, false if invalid.
   */
  private boolean isActiveStatusValid(String activeStatus) {

    if (!activeStatus.matches("[YN]")) {
      errorCounter(9);
      return false;
    }
    return true;
  }
}

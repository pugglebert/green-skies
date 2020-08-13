package model.loader;

import model.data.Airline;

import java.util.List;
import java.util.HashSet;
import java.util.Set;

public class AirlineParser extends Parser {
  // Processed airlines data
  private final Set<Airline> airlines = new HashSet<>();
  // Alphabetical name to represent line index
  private final int airlineID=0, name=1, alias = 2, IATA=3, ICAO=4, callsign=5, country=6, activeStatus=7;
  public AirlineParser(List<String> dataFile) {
    super(dataFile);
    /**
     * AirportParser Error code:
     * 100: not enough parameters
     * 101: airport id exists
     * 102: invalid id number
     * 103: invalid airport name
     * 104: invalid airport city
     * 105: invalid airport country
     * 106: invalid airport IATA code
     * 107: invalid airport ICAO code
     * 108: invalid latitude
     * 109: invalid lontitude
     * 110: invalid altitude
     * 111: invalid timezone
     * 112: invalid DST
     * 113: invalid database timezone
     * 114: invalid unknown error
     * 115: number of failed insertions
     *
     * 116: invalid alias
     * 117: invalid callsign
     * 118: invalid activestatus
     */
    errorCollectionInitializer(117);
    dataParser();
  }

  public void dataParser() {
    for (String dataLine : dataFile) {
      String[] line = dataLine.split(",");
      // need if(validate(line))
      if (validater(line)) {
        try {
          Airline airline = new Airline(
                  Integer.parseInt(line[airlineID]), line[name], line[alias], line[IATA], line[ICAO],
                  line[callsign], line[country], Boolean.parseBoolean(line[activeStatus]));
          airlines.add(airline);
        } catch (Exception e) {
          errorCounter(114);
        }
      } else {
        errorCounter(115);
      }
    }
  }

  protected boolean validater(String[] line) {
    boolean isValid = true;
    if (line.length != 8) {
      errorCounter(100);
    }
    
    if (!isIdValid(line[airlineID])){
      isValid = false;
    }

    if (!isNameValid(line[name])){
      isValid = false;
    }

    if (!isAliasValid(line[alias])){
      isValid = false;
    }

    if (!isIATAValid(line[IATA])){
      isValid = false;
    }

    if (!isICAOValid(line[ICAO])){
      isValid = false;
    }

    if (!isCallsignValid(line[callsign])){
      isValid = false;
    }

    if (!isCountryValid(line[country])){
      isValid = false;
    }
    
    if (!isActiveStatusValid(line[activeStatus])){
      isValid = false;
    }
    return isValid;
  }
  /**
   * Check if id is valid.
   *
   * @param airlineID airline id as a string.
   * @return true if valid, false if invalid.
   */
  private boolean isIdValid(String airlineID) {
    // airline ID Duplication check
    for (Airline airline : airlines) {
      try {
        if (airline.getAirlineID() == Integer.parseInt(airlineID)) {
          errorCounter(101);
          return false;
        }
      } catch (Exception e) {
        errorCounter(102);
        return false;
      }
    }
    return true;
  }

  /**
   * Check if name is valid.
   *
   * @param name airline name as a string.
   * @return true if valid, false if invalid.
   */
  private boolean isNameValid(String name) {
    if (!name.matches("[a-zA-Z ]+")) {
      errorCounter(103);
      return false;
    }
    return true;
  }

  /**
   * Check if city is valid.
   *
   * @param alias airline city as a string.
   * @return true if valid, false if invalid.
   */
  private boolean isAliasValid(String alias) {
    if (!alias.matches("[a-zA-Z ]+")) {
      errorCounter(116);
      return false;
    }
    return true;
  }

  /**
   * Check if IATA is valid.
   *
   * @param IATA airline IATA as a string.
   * @return true if valid, false if invalid.
   */
  private boolean isIATAValid(String IATA) {
    // airline IATA check
    if (!IATA.equalsIgnoreCase("null") && !IATA.equalsIgnoreCase("unknown")) {
      if (!IATA.matches("[a-zA-Z]+") || IATA.length() != 2) {
        errorCounter(106);
        return false;
      }
    }
    return true;
  }

  /**
   * Check if ICAO is valid.
   *
   * @param ICAO airline ICAO as a string.
   * @return true if valid, false if invalid.
   */
  private boolean isICAOValid(String ICAO) {
    // airline ICAO check
    if (!ICAO.equalsIgnoreCase("null") && !ICAO.equalsIgnoreCase("unknown")) {
      if (!ICAO.matches("[a-zA-Z]+") || ICAO.length() != 3) {
        errorCounter(107);
        return false;
      }
    }
    return true;
  }

  /**
   * Check if callsign is valid.
   *
   * @param callsign airline latitude as a string.
   * @return true if valid, false if invalid.
   */
  private boolean isCallsignValid(String callsign) {
    // airline callsign check
    if (!callsign.matches("[a-zA-Z]+")) {
      errorCounter(117);
      return false;
    }
    return true;
  }

  /**
   * Check if country is valid.
   *
   * @param country airline country as a string.
   * @return true if valid, false if invalid.
   */
  private boolean isCountryValid(String country) {
    // ISO 3166-1 codes not implemented
    if (!country.matches("[a-zA-Z ]+")) {
      errorCounter(105);
      return false;
    }
    return true;
    }

    /**
     * Check if activeStatus is valid.
     *
     * @param activeStatus as a string.
     * @return true if valid, false if invalid.
     */
  private boolean isActiveStatusValid(String activeStatus) {
    try {
      Boolean.parseBoolean(activeStatus.toLowerCase());
      return true;
    } catch (Exception e) {
      errorCounter(118);
      return false;
    }
  }

  /**
   * Getter for airlines
   *
   * @return A hashset contains all airline objects.
   */
  public Set<Airline> getAirlines() {
    return airlines;
  }
}

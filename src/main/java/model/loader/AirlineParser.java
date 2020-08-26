package model.loader;

import model.data.Airline;
import model.data.DataType;

import java.util.List;

public class AirlineParser extends Parser {
  // Processed airlines data
  //  private final Set<DataType> airlines = new HashSet<>();
  // Alphabetical name to represent line index
  private final int airlineID = 0,
      name = 1,
      alias = 2,
      IATA = 3,
      ICAO = 4,
      callsign = 5,
      country = 6,
      activeStatus = 7;

  // todo add error count
  public AirlineParser(List<String> dataFile) {
    super(dataFile, 12);

    /**
     * AirportParser Error code: 100: not enough parameters 101: airport id exists 102: invalid id
     * number 103: invalid airport name 104: invalid airport city 105: invalid airport country 106:
     * invalid airport IATA code 107: invalid airport ICAO code 108: invalid latitude 109: invalid
     * lontitude 110: invalid altitude 111: invalid timezone 112: invalid DST 113: invalid database
     * timezone 114: invalid unknown error 115: number of failed insertions
     *
     * <p>116: invalid alias 117: invalid callsign 118: invalid activestatus
     */
    dataParser();
  }

  @Override
  /** Initializes error lookup array with message for each error code. */
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
    errorLookup[11] = "Failed insertion";
  }

  public void dataParser() {

    for (String dataLine : dataFile) {
      dataLine = dataLine.replaceAll("[\"]", ""); // remove double quote
      String[] line = dataLine.split(",");
      //System.out.println(Arrays.toString(line));

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
          parserData.add(airline);

        } catch (Exception e) {
          errorCounter(10);
        }
      } else {
        errorCounter(11);
      }
    }
  }

  protected boolean validater(String[] line) {
    boolean isValid = true;
    if (line.length != 8) {
      errorCounter(0);
    }

    if (!isIdValid(line[airlineID])) {
      System.out.println("ID " + line[airlineID]);
      isValid = false;
    }

    if (!isNameValid(line[name])) {
      System.out.println("name " + line[name]);
      isValid = false;
    }

    if (!isAliasValid(line[alias])) {
      System.out.println("alias " + line[alias]);
      isValid = false;
    }

    if (!isIATAValid(line[IATA])) {
      System.out.println("IATA " + line[IATA]);
      isValid = false;
    }

    if (!isICAOValid(line[ICAO])) {
      System.out.println("ICAO " + line[ICAO]);
      isValid = false;
    }

    if (!isCallsignValid(line[callsign])) {
      System.out.println("callsign " + line[callsign]);
      isValid = false;
    }

    if (!isCountryValid(line[country])) {
      System.out.println("country " + line[country]);
      isValid = false;
    }

    if (!isActiveStatusValid(line[activeStatus])) {
      System.out.println("activeStatus " + line[activeStatus]);
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
    for (DataType data : parserData) {

      try {
        Airline airline = (Airline) data;
        if (airline.getAirlineID() == Integer.parseInt(airlineID) && airline.getAirlineID() >= 0) {
          errorCounter(1);
          return false;
        }
      } catch (Exception e) {
        errorCounter(2);
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
    if (!name.matches("[a-zA-Z0-9 -.]+")) {
      errorCounter(3);
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
    if (!alias.matches("(\\\\N)|([\\w ]+)|(^$)")) {
      errorCounter(4);
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
    if (!IATA.matches("-|([A-Z0-9]{2})|(^$)")) {
      errorCounter(5);
      return false;
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

    if (!ICAO.matches("(\\\\N)|(N/A)|([A-Z0-9]{3})|(^$)")) {
      errorCounter(6);
      return false;
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
    if (!callsign.matches("(\\\\N)|([A-Za-z -]+)|(^$)")) {
      errorCounter(7);
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
    if (!country.matches("(\\\\N)|([a-zA-Z ]+)|(^$)")) {
      errorCounter(8);
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

    if (!activeStatus.matches("[YN]")) {
      errorCounter(9);
      return false;
    }
    return true;
  }

  //  /**
  //   * Getter for airlines
  //   *
  //   * @return A hashset contains all airline objects.
  //   */
  //  public Set<DataType> getData() {
  //    return airlines;
  //  }

  //  public static void main(String[] args) throws Exception {
  //    ArrayList<String> testLines;
  //    testLines = new ArrayList<String>();
  //
  //    BufferedReader br = new BufferedReader(new
  // FileReader("src/test/java/TestFiles/airlines.csv"));
  //    int count = 0;
  //    String line = "";
  //    br.readLine(); // header
  //    while ((line = br.readLine()) != null && count < 1000) {
  //      testLines.add(line);
  //      //count++;
  //    }
  //
  //    AirlineParser parser = new AirlineParser(testLines);
  //  }
}

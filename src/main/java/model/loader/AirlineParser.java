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
//      System.out.println(Arrays.toString(line));

      if (validater(line)) {
        // System.out.println(Arrays.toString(line));

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

          // parserData.add(airline);
          addAirLine(parserData, airline.getAirlineID(), airline);

        } catch (Exception e) {
          errorCounter(10);
        }
      } else {
        errorCounter(11);
      }
    }
    System.out.println(parserData);
  }

  /**
   * add airline to index matches with airLineID.
   * First check if there are any airline currently sit at index. If it is null then replace with airline param.
   * If parserset size is too small then init it with null value.
   * If there is an airline at index then check if the airline is the same with the one we want to add.
   * If it is the same then treat as duplicate (do nothing)
   * If is is not then add to error
   *
   * @param parserData the superclass list
   * @param airlineID  airline ID we want to add
   * @param airline    Airline Object we wanted to add
   */
  private void addAirLine(List parserData, int airlineID, Airline airline) {
    int attemp = 3; // maximum attemp
    while (attemp > 0) {
      attemp--;
      try {
        Airline arrayItem = (Airline) parserData.get(airlineID);
        if (arrayItem == null) {
          parserData.set(airlineID, airline);
        } else if (arrayItem.equals(airline)) {
          errorCounter(1); // Have the same airline
        } else {
          errorCounter(11); // Airline exist with same ID
        }

      } catch (
              IndexOutOfBoundsException e) { // size is smaller than ID then init array with null value
        for (int i = 0; i < airlineID - parserData.size() + 1; i++) {
          parserData.add(null);
        }
      }
    }
  }

  protected boolean validater(String[] line) {

    boolean isValid = true;
    if (line.length != 8) {
      errorCounter(0);
    }

    if (!isIdValid(line[airlineID])) {
      //      System.out.println("ID " + line[airlineID]);
      isValid = false;
    }

    if (!isNameValid(line[name])) {
      //      System.out.println("name " + line[name]);
      isValid = false;
    }

    if (!isAliasValid(line[alias])) {
      //      System.out.println("alias " + line[alias]);
      isValid = false;
    }

    if (!isIATAValid(line[IATA])) {
      //      System.out.println("IATA " + line[IATA]);
      isValid = false;
    }

    if (!isICAOValid(line[ICAO])) {
      //      System.out.println("ICAO " + line[ICAO]);
      isValid = false;
    }

    if (!isCallsignValid(line[callsign])) {
      //      System.out.println("callsign " + line[callsign]);
      isValid = false;
    }

    if (!isCountryValid(line[country])) {
      //      System.out.println("country " + line[country]);
      isValid = false;
    }

    if (!isActiveStatusValid(line[activeStatus])) {
      //      System.out.println("activeStatus " + line[activeStatus]);
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

//  public static void main(String[] args) throws Exception {
//    ArrayList<String> testLines;
//    testLines = new ArrayList<String>();
//
//    BufferedReader br = new BufferedReader(new FileReader("src/test/java/TestFiles/airlines.csv"));
//    int count = 0;
//    String line = "";
//    br.readLine(); // header
//    while ((line = br.readLine()) != null && count < 2) {
//      testLines.add(line);
//      count++;
//    }
//    AirlineParser parser = new AirlineParser(testLines);
//  }
}

package model.data;

/**
 * The Airline class which contains all data for one unique airline.
 *
 * @author Nathan
 * @version 1.3
 * @since 2020-08-11
 */
public class Airline implements DataType {
  /** The ID of an airline (unique). */
  private final int airlineID;

  /** The name of the airline. */
  private final String name;

  /** The alias of an airline. */
  private final String alias;

  /** The IATA code of an airline. */
  private final String IATA;

  /** The ICAO code of an airline. */
  private final String ICAO;

  /** The callsign of an airline. */
  private final String callsign;

  /** The country or territory where airport is located. */
  private final String country;

  /** "Y" if the airline is or has until recently been operational, "N" if it is defunct. */
  private final Boolean activeStatus;

  /** The Airline constructor. */
  public Airline(
      int airlineID,
      String name,
      String alias,
      String IATA,
      String ICAO,
      String callsign,
      String country,
      Boolean activeStatus) {
    this.airlineID = airlineID;
    this.name = name;
    this.alias = alias;
    this.IATA = IATA;
    this.ICAO = ICAO;
    this.callsign = callsign;
    this.country = country;
    this.activeStatus = activeStatus;
  }

  /**
   * This method returns the Airline's ID.
   *
   * @return airlineID.
   */
  public int getAirlineID() {
    return airlineID;
  }

  /**
   * This method returns the name of the Airline.
   *
   * @return airlineName.
   */
  public String getName() {
    return name;
  }

  /**
   * This method returns the alias of the Airline.
   *
   * @return airlineName.
   */
  public String getAirlineAlias() {
    return alias;
  }

  /**
   * This method returns the IATA of the Airline.
   *
   * @return IATA.
   */
  public String getIATA() {
    return IATA;
  }

  /**
   * This method returns the ICAO of the Airline.
   *
   * @return ICAO.
   */
  public String getICAO() {
    return ICAO;
  }

  /**
   * This method returns the callsign of the Airline.
   *
   * @return airlineCallsign.
   */
  public String getCallsign() {
    return callsign;
  }

  /**
   * This method returns the country of origin of the Airline.
   *
   * @return country.
   */
  public String getCountry() {
    return country;
  }

  /**
   * This method returns the whether the Airline is active or not.
   *
   * @return activeStatus.
   */
  public boolean getActiveStatus() {
    return activeStatus;
  }

  /**
   * Returns true if all another is instance of airline and has all the same attributes as this
   * airline, false otherwise..
   *
   * @param o any object to check equality against.
   * @return true if objects are equal, false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (o instanceof Airline) {
      Airline another = (Airline) o;
      return (this.airlineID == another.getAirlineID())
          && this.name.equals(another.getName())
          && this.alias.equals(another.getAirlineAlias())
          && this.IATA.equals(another.getIATA())
          && this.ICAO.equals(another.getICAO())
          && this.callsign.equals(another.getCallsign())
          && this.country.equals(another.getCountry())
          && this.activeStatus == another.getActiveStatus();
    } else {
      return false;
    }
  }
}

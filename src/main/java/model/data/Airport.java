package model.data;

/**
 * The Airport class which contains all data for one unique airport.
 *
 * @author Enyang Zhang, Nathan Huynh
 * @version 1.3
 * @since 2020-08-21
 */
public class Airport implements DataType {

  /** Unique OpenFlights identifier for this airport. */
  private final int airportID;

  /** Name of airport. May or may not contain the City name. */
  private final String name;

  /** Main city served by airport. May be spelled differently from Name. */
  private final String city;

  /** Country or territory where airport is located. */
  private final String country;

  /** 3-letter IATA code. Null if not assigned/unknown.*/
  private final String IATA;

  /** 4-letter ICAO code*/
  private final String ICAO;

  /** Decimal degrees, usually to six significant digits. Negative is South, positive is North. */
  private final double latitude;

  /** Decimal degrees, usually to six significant digits. Negative is West, positive is East.*/
  private final double longitude;

  /** Unit of alltitude is in feet*/
  private final int altitude;

  /** Hours offset from UTC. Fractional hours are expressed as decimals, eg. India is 5.5.*/
  private final float timezone;

  /** Daylight savings time. One of E (Europe), A (US/Canada), S (South America), O (Australia), Z (New Zealand),
   * N (None) or U (Unknown).*/
  private final String DST;

  /** Timezone in "tz" (Olson) format, eg. "America/Los_Angeles".*/
  private final String dataBaseTimeZone;

  /** The Airport constructor. */
  public Airport(
      int airportID,
      String name,
      String city,
      String country,
      String IATA,
      String ICAO,
      double latitude,
      double longitude,
      int altitude,
      float timezone,
      String DST,
      String dataBaseTimeZone) {
    this.airportID = airportID;
    this.name = name;
    this.city = city;
    this.country = country;
    this.IATA = IATA;
    this.ICAO = ICAO;
    this.latitude = latitude;
    this.longitude = longitude;
    this.altitude = altitude;
    this.timezone = timezone;
    this.DST = DST;
    this.dataBaseTimeZone = dataBaseTimeZone;
  }

  /**
   * This method returns the Airport's ID.
   *
   * @return airportID.
   */
  public int getAirportID() {
    return airportID;
  }

  /**
   * This method returns the name of the Airport.
   *
   * @return name.
   */
  public String getName() {
    return name;
  }

  /**
   * This method returns the city the Airport is in.
   *
   * @return city.
   */
  public String getCity() {
    return city;
  }

  /**
   * This method returns the country the Airport is in.
   *
   * @return country.
   */
  public String getCountry() {
    return country;
  }

  /**
   * This method returns the IATA of the Airport.
   *
   * @return IATA.
   */
  public String getIATA() {
    return IATA;
  }

  /**
   * This method returns the ICAO of the Airport.
   *
   * @return ICAO.
   */
  public String getICAO() {
    return ICAO;
  }

  /**
   * This method returns latitude of the Airport.
   *
   * @return latitude.
   */
  public double getLatitude() {
    return latitude;
  }

  /**
   * This method returns the longitude of the airport.
   *
   * @return longitude.
   */
  public double getLongitude() {
    return longitude;
  }

  /**
   * This method returns the altitude of the airport.
   *
   * @return altitude.
   */
  public int getAltitude() {
    return altitude;
  }

  /**
   * This method returns the timezone of the Airport.
   *
   * @return timezone.
   */
  public float getTimezone() {
    return timezone;
  }

  /**
   * This method returns the DST of the airport.
   *
   * @return DST.
   */
  public String getDST() {
    return DST;
  }

  /**
   * This method returns the dataBaseTimeZone of the Airport.
   *
   * @return dataBaseTimeZone.
   */
  public String getDataBaseTimeZone() {
    return dataBaseTimeZone;
  }

  /**
   * This method returns true if object has the same attributes as the airport from which the method is called,
   * false otherwise.
   *
   * @param o object to be compared to the airport calling the method.
   * @return true if the two objects attributes are the same, false if there are any differences.
   */
  @Override
  public boolean equals(Object o) {
    if (o instanceof Airport) {
      Airport another = (Airport) o;
      return (this.airportID == another.getAirportID())
          && this.name.equals(another.getName())
          && this.city.equals(another.getCity())
          && this.country.equals(another.getCountry())
          && this.IATA.equals(another.getIATA())
          && this.ICAO.equals(another.getICAO())
          && Math.abs(this.latitude - another.getLatitude()) < 0.0000001
          && Math.abs(this.longitude - another.getLongitude()) < 0.0000001
          && this.altitude == another.getAltitude()
          && Math.abs(this.timezone - another.getTimezone()) < 0.0000001
          && this.DST.equals(another.getDST())
          && this.dataBaseTimeZone.equals(another.getDataBaseTimeZone());
    } else {
      return false;
    }
  }
}

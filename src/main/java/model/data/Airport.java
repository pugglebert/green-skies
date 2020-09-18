package model.data;

/**
 * The Airport class which contains all data for one unique airport.
 *
 * @author Enyang Zhang
 * @version 1.3
 * @since 2020-08-21
 */
public class Airport implements DataType {

  /**All Airport information according to datafile and official explainations. */
  private final int airportID;
  private final String name;
  private final String city;
  private final String country;
  private final String IATA;
  private final String ICAO;
  private final double latitude;
  private final double longitude;
  private final int altitude;
  private final float timezone;
  private final String DST;
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
   * Getter for the Airport's ID.
   *
   * @return airportID.
   */
  public int getAirportID() {
    return airportID;
  }

  /**
   * Getter for the name of the Airport.
   *
   * @return name.
   */
  public String getName() {
    return name;
  }

  /**
   * Getter for the city the Airport is in.
   *
   * @return city.
   */
  public String getCity() {
    return city;
  }

  /**
   * Getter for the country the Airport is in.
   *
   * @return country.
   */
  public String getCountry() {
    return country;
  }

  /**
   * Getter for the IATA of the Airport.
   *
   * @return IATA.
   */
  public String getIATA() {
    return IATA;
  }

  /**
   * Getter for the ICAO of the Airport.
   *
   * @return ICAO.
   */
  public String getICAO() {
    return ICAO;
  }

  /**
   * Getter for latitude of the Airport.
   *
   * @return latitude.
   */
  public double getLatitude() {
    return latitude;
  }

  /**
   * Getter for the longitude of the airport.
   *
   * @return longitude.
   */
  public double getLongitude() {
    return longitude;
  }

  /**
   * Getter for the altitude of the airport.
   *
   * @return altitude.
   */
  public int getAltitude() {
    return altitude;
  }

  /**
   * Getter for the timezone of the Airport.
   *
   * @return timezone.
   */
  public float getTimezone() {
    return timezone;
  }

  /**
   * Getter for the DST of the airport.
   *
   * @return DST.
   */
  public String getDST() {
    return DST;
  }

  /**
   * Getter for the dataBaseTimeZone of the Airport.
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

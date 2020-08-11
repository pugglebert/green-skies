package model.data;

/**
 * The Airport class which contains all data for one unique airport.
 * @author Hayley Krippner
 * @version 1.2
 * @since 2020-08-11
 */
public class Airport {
    private final int airportID;
    private final String name;
    private final String city;
    private final String country;
    private final String IATA;
    private final String ICAO;
    private final float latitude;
    private final float longitude;
    private final int altitude;
    private final float timezone;
    private final String DST;
    private final String dataBaseTimeZone;

    /**
     * The Airport constructor.
     */
    public Airport(int airportID, String name, String city, String country, String IATA, String ICAO, float latitude,
                   float longitude, int altitude, float timezone, String DST, String dataBaseTimeZone){
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
     * @return airportID.
     */
    public int getAirportID(){
        return airportID;
    }

    /**
     * Getter for the name of the Airport.
     * @return name.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for the city the Airport is in.
     * @return city.
     */
    public String getCity() {
        return city;
    }

    /**
     * Getter for the country the Airport is in.
     * @return country.
     */
    public String getCountry() {
        return country;
    }

    /**
     * Getter for the IATA of the Airport.
     * @return IATA.
     */
    public String getIATA() {
        return IATA;
    }

    /**
     * Getter for the ICAO of the Airport.
     * @return ICAO.
     */
    public String getICAO() {
        return ICAO;
    }

    /**
     * Getter for latitude of the Airport.
     * @return latitude.
     */
    public float getLatitude() {
        return latitude;
    }

    /**
     * Getter for the longitude of the airport.
     * @return longitude.
     */
    public float getLongitude() {
        return longitude;
    }

    /**
     * Getter for the altitude of the airport.
     * @return altitude.
     */
    public int getAltitude() {
        return altitude;
    }

    /**
     * Getter for the timezone of the Airport.
     * @return timezone.
     */
    public float getTimezone() {
        return timezone;
    }

    /**
     * Getter for the DST of the airport.
     * @return DST.
     */
    public String getDST() {
        return DST;
    }

    /**
     * Getter for the dataBaseTimeZone of the Airport.
     * @return dataBaseTimeZone.
     */
    public String getDataBaseTimeZone() {
        return dataBaseTimeZone;
    }
}

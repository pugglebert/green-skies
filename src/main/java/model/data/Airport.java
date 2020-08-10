package model.data;

/**
 * The Airport class for containing all data for one specific airport.
 * @author Enyang Zhang(Lambert)
 * @version 1.1
 * @since 2020-08-09
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
     * constructor for airport. Passes current airport data to attributes
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
     * Getter for airport ID
     * @return airport ID
     */
    public int getAirportID(){
        return airportID;
    }

    /**
     * Getter for airport name
     * @return airport name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for airport city
     * @return airport city
     */
    public String getCity() {
        return city;
    }

    /**
     * Getter for airport country
     * @return airport country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Getter for airport IATA
     * @return airport IATA
     */
    public String getIATA() {
        return IATA;
    }

    /**
     * Getter for airport ICAO
     * @return airport ICAO
     */
    public String getICAO() {
        return ICAO;
    }

    /**
     * Getter for airport latitude
     * @return airport latitude
     */
    public float getLatitude() {
        return latitude;
    }

    /**
     * Getter for airport longitude
     * @return airport longitude
     */
    public float getLongitude() {
        return longitude;
    }

    /**
     * Getter for airport altitude
     * @return airport altitude
     */
    public int getAltitude() {
        return altitude;
    }

    /**
     * Getter for airport timezone
     * @return airport timezone
     */
    public float getTimezone() {
        return timezone;
    }

    /**
     * Getter for airport DST
     * @return airport DST
     */
    public String getDST() {
        return DST;
    }

    /**
     * Getter for airport dataBaseTimeZone
     * @return airport dataBaseTimeZone
     */
    public String getDataBaseTimeZone() {
        return dataBaseTimeZone;
    }
}

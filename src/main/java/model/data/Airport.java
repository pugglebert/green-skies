package model.data;

// TODO: 9/10/2020 getter for all attributes
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
     * @return airportID
     */
    public int getAirportID(){
        return airportID;
    }
}

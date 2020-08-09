package model.data;

public class Airport {
    public int airportID;
    public String name;
    public String city;
    public String country;
    public String IATA;
    public String ICAO;
    public float latitude;
    public float longitude;
    public int altitude;
    public float timezone;
    public String DST;
    public String dataBaseTimeZone;

    public Airport(int airportID, String name, String city, String country, String IATA, String ICAO, float latitude, float longitude, int altitude, float timezone, String DST, String dataBaseTimeZone){
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
}

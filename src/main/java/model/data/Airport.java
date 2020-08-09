package model.data;

public class Airport {
    int airportID;
    String name;
    String city;
    String country;
    String IATA;
    String ICAO;
    float latitude;
    float longitude;
    int altitude;
    float timezone;
    String DST;
    String dataBaseTimeZone;

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

package model.data;

public class Airline {
    private int airlineID;
    private String airlineName;
    private String airportAlias;
    private String IATA;
    private String ICAO;
    private String airlineCallsign;
    private String country;
    private Boolean activeStatus;

    public Airline(int airlineID, String airlineName, String airportAlias, String IATA, String ICAO,
                   String airlineCallsign, String country, Boolean activeStatus) {
        this.airportID = airportID;
        this.airlineName = airlineName;
        this.airportAlias = airportAlias;
        this.IATA = IATA;
        this.ICAO = ICAO;
        this.airlineCallsign = airlineCallsign;
        this.country = country;
        this.codeShare = activeStatus;
    }
}

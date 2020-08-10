package model.data;
/**
 * The Airline class for containing all data for one specific airline.
 * @author Nathan Huynh
 * @version 1.1
 * @since 2020-08-09
 */
public class Airline {

    private final int airlineID;
    private final String airlineName;
    private final String airlineAlias;
    private final String IATA;
    private final String ICAO;
    private final String airlineCallsign;
    private final String country;
    private final Boolean activeStatus;

    /**
     * Constructor of Airline class
     */
    public Airline(int airlineID, String airlineName, String airlineAlias, String IATA, String ICAO,
                   String airlineCallsign, String country, Boolean activeStatus) {
        this.airlineID = airlineID;
        this.airlineName = airlineName;
        this.airlineAlias = airlineAlias;
        this.IATA = IATA;
        this.ICAO = ICAO;
        this.airlineCallsign = airlineCallsign;
        this.country = country;
        this.activeStatus = activeStatus;
    }

    /**
     * @return airline ID
     */
    public int getAirlineID() {
        return airlineID;
    }

    /**
     * @return airline name
     */
    public String getAirlineName() {
        return airlineName;
    }

    /**
     * @return airpline alias
     */
    public String airlineAlias() {
        return airlineAlias;
    }

    /**
     * @return airline IATA (2 chars)
     */
    public String getIATA() {
        return IATA;
    }

    /**
     * @return airline ICAO (3 chars)
     */
    public String getICAO() {
        return ICAO;
    }

    /**
     * @return airline Callsign
     */
    public String getAirlineCallsign() {
        return airlineCallsign;
    }

    /**
     * @return country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @return active status of the airline
     */
    public Boolean getActiveStatus() {
        return activeStatus;
    }
}


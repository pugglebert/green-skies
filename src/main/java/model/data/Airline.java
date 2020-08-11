package model.data;

/**
 * The Airline class which contains all data for one unique airline.
 * @author Hayley Krippner
 * @version 1.2
 * @since 2020-08-11
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
     * The Airline constructor.
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
     * Getter for the Airline's ID.
     * @return airlineID.
     */
    public int getAirlineID(){
        return airlineID;
    }

    /**
     * Getter for the name of the Airline.
     * @return airlineName.
     */
    public int getAirlineName(){
        return airlineName;
    }

    /**
     * Getter for the alias of the Airline.
     * @return airlineName.
     */
    public int getAirlineName(){
        return airlineName;
    }

    /**
     * Getter for the IATA of the Airline.
     * @return IATA.
     */
    public int getIATA(){
        return IATA;
    }

    /**
     * Getter for the ICAO of the Airline.
     * @return ICAO.
     */
    public int getICAO(){
        return ICAO;
    }

    /**
     * Getter for the callsign of the Airline.
     * @return airlineCallsign.
     */
    public int getAirlineCallsign(){
        return airlineCallsign;
    }

    /**
     * Getter for the country of origin of the Airline.
     * @return country.
     */
    public int getCountry(){
        return country;
    }

    /**
     * Getter for the whether the Airline is active or not.
     * @return activeStatus.
     */
    public int getActiveStatus(){
        return activeStatus;
    }
}

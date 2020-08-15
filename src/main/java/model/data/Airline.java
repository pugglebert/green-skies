package model.data;

/**
 * The Airline class which contains all data for one unique airline.
 * @author Hayley Krippner
 * @version 1.2
 * @since 2020-08-11
 */
public class Airline {
    private final int airlineID;
    private final String name;
    private final String alias;
    private final String IATA;
    private final String ICAO;
    private final String callsign;
    private final String country;
    private final Boolean activeStatus;

    /**
     * The Airline constructor.
     */
    public Airline(int airlineID, String name, String alias, String IATA, String ICAO,
                   String callsign, String country, Boolean activeStatus) {
        this.airlineID = airlineID;
        this.name = name;
        this.alias = alias;
        this.IATA = IATA;
        this.ICAO = ICAO;
        this.callsign = callsign;
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
    public String getName(){
        return name;
    }

    /**
     * Getter for the alias of the Airline.
     * @return airlineName.
     */
    public String getairlineAlias(){
        return name;
    }

    /**
     * Getter for the IATA of the Airline.
     * @return IATA.
     */
    public String getIATA(){
        return IATA;
    }

    /**
     * Getter for the ICAO of the Airline.
     * @return ICAO.
     */
    public String getICAO(){
        return ICAO;
    }

    /**
     * Getter for the callsign of the Airline.
     * @return airlineCallsign.
     */
    public String getCallsign(){
        return callsign;
    }

    /**
     * Getter for the country of origin of the Airline.
     * @return country.
     */
    public String getCountry(){
        return country;
    }

    /**
     * Getter for the whether the Airline is active or not.
     * @return activeStatus.
     */
    public boolean getActiveStatus(){
        return activeStatus;
    }
}
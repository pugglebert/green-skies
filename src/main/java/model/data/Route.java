package model.data;

/**
 * The Route class for containing all data for one unique flight route.
 * @author Hayley Krippner
 * @version 1.1
 * @since 2020-08-11
 */
public class Route {
    private final String airlineName;
    private final int airlineID;
    private final String sourceAirport;
    private final int sourceAirportID;
    private final String destinationAirport;
    private final int destinationAirportID;
    private final String codeShare;
    private final int numOfStops;
    private final String equipment;

    /**
     * The Route constructor.
     */
    public Route(String airlineName, int airlineID, String sourceAirport, int sourceAirportID, String destinationAirport,
                   int destinationAirportID, String codeShare, int numOfStops, String equipment) {
        this.airlineName = airlineName;
        this.airlineID = airlineID;
        this.sourceAirport = sourceAirport;
        this.sourceAirportID = sourceAirportID;
        this.destinationAirport = destinationAirport;
        this.destinationAirportID = destinationAirportID;
        this.codeShare = codeShare;
        this.numOfStops = numOfStops;
        this.equipment = equipment;
    }

    /**
     * Getter for the name of the airline of that is used during the Flight.
     * @return airlineID.
     */
    public String getAirlineName(){
        return airlineName;
    }

    /**
     * Getter for the ID of the airline of that is used during the Flight.
     * @return airlineID.
     */
    public int getAirlineID(){
        return airlineID;
    }

    /**
     * Getter for the name of the source airport of the Flight.
     * @return sourceAirport.
     */
    public String getSourceAirport(){
        return sourceAirport;
    }

    /**
     * Getter for the ID of the source airport of the Flight.
     * @return sourceAirportID.
     */
    public int getSourceAirportID(){
        return sourceAirportID;
    }

    /**
     * Getter for the name of the destination airport of the Flight.
     * @return destinationAirport.
     */
    public String getDestinationAirport(){
        return destinationAirport;
    }

    /**
     * Getter for the ID of the destination airport of the Flight.
     * @return destinationAirportID.
     */
    public int getDestinationAirportID(){
        return destinationAirportID;
    }

    /**
     * Getter for the codeShare for the Flight.
     * @return codeShare.
     */
    public String getCodeShare(){
        return codeShare;
    }

    /**
     * Getter for the number of stops the Flight has.
     * @return numOfStops.
     */
    public int getNumOfStops(){
        return numOfStops;
    }

    /**
     * Getter for the equipment used during the Flight.
     * @return equipment.
     */
    public String getEquipment(){
        return equipment;
    }
}

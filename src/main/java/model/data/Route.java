package model.data;

public class Route {
    private String airlineName;
    private int airlineID;
    private String sourceAirport;
    private int sourceAirportID;
    private String destinationAirport;
    private int destinationAirportID;
    private String codeShare;
    private int numOfStops;
    private int equipment;

    public Route(String airlineName, int airlineID, String sourceAirport, int sourceAirportID, String destinationAirport,
                   int destinationAirportID, String codeShare, int numOfStops, int equipment) {
        this.airlineID = airlineID;
        this.sourceAirport = sourceAirport;
        this.sourceAirportID = sourceAirportID;
        this.destinationAirport = destinationAirport;
        this.destinationAirportID = destinationAirportID;
        this.codeShare = codeShare;
        this.numOfStops = numOfStops;
        this.equipment = equipment;
    }
}

package model.data;

/**
 * The Flight class for containing all data for one specific flight.
 * @author Hayley Krippner
 * @version 1.1
 * @since 2020-08-09
 */
public class Flight {
    private String flightID;
    private String type;
    private String airportsVia;
    private int altitude;
    private int position;
    private int distance;
    private String flightName;

    /**
     * Constructor for Flight. Passes current flight data to attributes.
     */
    public Flight(String flightID, String type, String airportsVia, int altitude, int position, int distance,
                  String flightName) {
        this.flightID = flightID;
        this.type = type;
        this.airportsVia = airportsVia;
        this.altitude = altitude;
        this.position = position;
        this.distance = distance;
        this.flightName = flightName;
    }
}

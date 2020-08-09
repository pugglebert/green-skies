package model.data;

public class Flight {
    private String flightID;
    private String type;
    private String airportsVia;
    private int altitude;
    private int position;
    private int distance;
    private String flightName;

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

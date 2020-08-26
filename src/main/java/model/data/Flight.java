package model.data;

/**
 * The Flight class for containing all data for one unique flight.
 * @author Hayley Krippner
 * @version 1.1
 * @since 2020-08-09
 */
public class Flight {
    private final String flightID;
    private final String type;
    private final String airportsVia;
    private final int altitude;
    private final int position;
    private final int distance;
    private final String flightName;

    /**
     * The Flight constructor.
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

    /**
     * Getter for the Flight's ID.
     * @return flightID.
     */
    public String getFlightID(){
        return flightID;
    }

    /**
     * Getter for the type of the Fight.
     * @return type.
     */
    public String getType() {
        return type;
    }

    /**
     * Getter for the airports the Fight goes via.
     * @return airportsVia.
     */
    public String getAirportsVia() {
        return airportsVia;
    }

    /**
     * Getter for the altitude of the Fight.
     * @return altitude.
     */
    public int getAltitude() {
        return altitude;
    }

    /**
     * Getter for the position of the Fight.
     * @return position.
     */
    public int getPosition() {
        return position;
    }


    /**
     * Getter for the distance travelled during the Fight.
     * @return distance.
     */
    public int getDistance() {
        return distance;
    }

    /**
     * Getter for the name of the Fight.
     *
     * @return flightName.
     */
    public String getFlightName() {
        return flightName;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "flightID='" + flightID + '\'' +
                ", type='" + type + '\'' +
                ", airportsVia='" + airportsVia + '\'' +
                ", altitude=" + altitude +
                ", position=" + position +
                ", distance=" + distance +
                ", flightName='" + flightName + '\'' +
                '}';
    }
}

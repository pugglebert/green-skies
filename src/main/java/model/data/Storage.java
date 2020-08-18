package model.data;

import java.util.Set;

/**
 * Class to keep track of all the data currently open files
 * @author Ella Johnson
 * @since 17/08/2020
 * @version 1.0
 */
public class Storage {

    private Set<DataType> airlines;
    private Set<DataType> airports;
    private Set<DataType> routes;

    /**
     * @return a set of Airline objects from the currently open file cast as Datatype objects.
     */
    public Set<DataType> getAirlines() {return airlines;}

    /**
     * @return a set of Airport objects from the currently open file cast as Datatype objects.
     */
    public Set<DataType> getAirports() {return airports;}

    /**
     * @return a set of Route object from the currently open file cast as Datatype objects.
     */
    public Set<DataType> getRoutes() {return routes;}

    public void setData(Set<DataType> data, String type) {
        if (type.matches("airline")) {
            airlines = data;
        } else if (type.matches("airport")) {
            airports = data;
        } else if (type.matches("route")) {
            routes = data;
        } else {
            throw new IllegalArgumentException("Type must be airline, airport or route");
        }
    }
 }

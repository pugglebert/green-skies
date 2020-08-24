package model.data;

import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Set;

/**
 * Class to keep track of all the data currently open files
 * @author Ella Johnson
 * @since 17/08/2020
 * @version 1.0
 */
public class Storage {

    private static ObservableList<DataType> airlines;
    private static ObservableList<DataType> airports;
    private static ObservableList<DataType> routes;

    /**
     * @return a set of Airline objects from the currently open file cast as Datatype objects.
     */
    public static ObservableList<DataType> getAirlines() {return airlines;}

    /**
     * @return a set of Airport objects from the currently open file cast as Datatype objects.
     */
    public static ObservableList<DataType> getAirports() {return airports;}

    /**
     * @return a set of Route object from the currently open file cast as Datatype objects.
     */
    public static ObservableList<DataType> getRoutes() {return routes;}

    public void setData(ObservableList<DataType> data, String type) {
        if (type.matches("Airline")) {
            airlines = data;
        } else if (type.matches("Airport")) {
            airports = data;
        } else if (type.matches("Route")) {
            routes = data;
        } else {
            throw new IllegalArgumentException("Type must be airline, airport or route");
        }
    }
 }

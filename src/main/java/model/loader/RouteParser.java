package model.loader;

import model.data.Airport;
import model.data.Route;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class RouteParser extends Parser {

    //Processed route data
    private final Set<Route> routes = new HashSet<>();
    //Alphabetical name to represent line index
    private final int airline = 0, airlineID = 1, sourceAirport = 2, sourceAirportID = 3, destinationAirport = 4,
            destinationAirportID = 5, codeshare = 6,  stops= 7, equipment = 8;
    public RouteParser(ArrayList<String> dataFile) {
        super(dataFile);

        /**
         * AirportParser Error code:
         * 100: not enough parameters
         * 101: invalide airline code
         * 102: invalid airline id number
         * 103: invalid source airport code
         * 104: invalid source airport id
         * 105: invalid destination airport code
         * 106: invalid destination airport id
         * 107: invalid codeshare value
         * 108: invalid number of stops
         * 109: invalid equipment code
         * 110: invalid unknown error
         * 111: number of failed insertions
         */
        errorCollectionInitializer(16);
        dataParser();
    }

    @Override
    protected void dataParser() {
        for (String dataLine: dataFile){
            String[] line= dataLine.replaceAll("\"","").split(",");
            if (validater(line)){
                try{
                    Route route = new Route(line[airline], Integer.parseInt(line[airlineID]), line[sourceAirport], Integer.parseInt(line[sourceAirportID]),
                            line[destinationAirport], Integer.parseInt(line[destinationAirportID]), line[codeshare], Integer.parseInt(line[stops]),
                            line[equipment]);
                    routes.add(route);
                } catch(Exception e) {
                    errorCounter(110);
                }
            } else {
                errorCounter(111);
            }
        }

    }

    @Override
    public boolean validater(String[] line) {
        boolean isValid = true;
        if (line.length != 9){
            errorCounter(100);
            isValid = false;
        }

        if (!isAirlineValid(line[airline])){
            isValid = false;
        }

        if(!isAirlineIDValid(line[airlineID])){
            isValid = false;
        }

        if(!isAirportValid(line[sourceAirport])){
            isValid = false;
        }

        if(!isAirportIDValid(line[sourceAirportID])){
            isValid = false;
        }

        if(!isAirportValid(line[destinationAirport])){
            isValid = false;
        }

        if(!isAirportIDValid(line[destinationAirportID])){
            isValid = false;
        }

        if(!isCodeshareValid(line[codeshare])){
            isValid = false;
        }

        if(!isStopsValid(line[stops])){
            isValid = false;
        }

        if(!isEquipmentValid(line[equipment])){
            isValid = false;
        }

        return isValid;
    }

    private boolean isAirportValid(String airport) {
        return false;
    }

    private boolean isAirportIDValid(String airportID) {
        return false;
    }

    private boolean isAirlineValid(String airline) {
        return false;
    }

    private boolean isAirlineIDValid(String airlineID) {
        return false;
    }

    private boolean isCodeshareValid(String codeshare) {
        return false;
    }

    private boolean isStopsValid(String stops) {
        return false;
    }

    private boolean isEquipmentValid(String equipment) {
        return false;
    }
}

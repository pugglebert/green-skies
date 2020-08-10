package model.loader;

import model.data.Airport;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The sub-class Parser for airport. AirportParser class receives a list of airport data, validates
 * each of the data line and create airport object. Then put the object into a hashSet for return
 * value.
 *
 * @author Enyang Zhang(Lambert)
 * @version 1.0
 * @since 2020-08-09
 */
public class AirportParser extends Parser {
    //Processed airport data
    private final Set<Airport> airports = new HashSet<>();
    //Alphabetical name to represent line index
    private final int airportID = 0, name = 1, city = 2, country = 3, IATA = 4, ICAO = 5, latitude = 6, longtitude = 7,
            altitude = 8, timezone = 9, DST = 10, dataBaseTimeZone = 11;

    /**
     * Constructor of AirportParser, it will start dataParse method as well.
     * @param dataFile is the list contains one line of datafile per element.
     */
    public AirportParser(List<String> dataFile) {
        super(dataFile);
        /**
         * AirportParser Error code:
         * 100: not enough parameters
         * 101: airport id exists
         * 102: invalid id number
         * 103: invalid airport name
         * 104: invalid airport city
         * 105: invalid airport country
         * 106: invalid airport IATA code
         * 107: invalid airport ICAO code
         * 108: invalid latitude
         * 109: invalid lontitude
         * 110: invalid altitude
         * 111: invalid timezone
         * 112: invalid DST
         * 113: invalid database timezone
         */
        errorCollectionInitializer(13);
        dataParse();
    }

    /**
     * Data parser to convert airport data from list into airport objects and add to HashSet. will also call validater
     * to verify each airport data.
     */
    @Override
    protected void dataParse(){

        for (String dataLine: dataFile){
            String[] line= dataLine.replaceAll("\"","").split(",");
            if (validater(line)){
                try{
                    Airport airport = new Airport(Integer.parseInt(line[0]), line[1], line[2], line[3], line[4],
                            line[5], Float.parseFloat(line[6]), Float.parseFloat(line[7]), Integer.parseInt(line[8]),
                            Float.parseFloat(line[9]), line[10], line[11]);
                    airports.add(airport);
                } catch(Exception e) {
                    System.out.println("Unknown Error."); // all possible known errors will be caught in validater
                }

            } else {
                System.out.println("Unable to insert data.");
            }
        }
    }

    @Override
    protected boolean validater(String[] line) {
        boolean isValid = true;
        if (line.length != 12){
            errorCounter(100);
            isValid = false;
        }

        if (!isIdValid(line[airportID])){
            isValid = false;
        }

        if(!isNameValid(line[name])){
            isValid = false;
        }

        if(!isCityValid(line[city])){
            isValid = false;
        }

        if(!isCountryValid(line[country])){
            isValid = false;
        }

        if(!isIATAValid(line[IATA])){
            isValid = false;
        }

        if(!isICAOValid(line[ICAO])){
            isValid = false;
        }

        if(!isLatValid(line[latitude])){
            isValid = false;
        }

        if(!isLonValid(line[longtitude])){
            isValid = false;
        }

        if(!isAltValid(line[altitude])){
            isValid = false;
        }

        if(!isTZValid(line[timezone])){
            isValid = false;
        }

        if(!isDSTValid(line[DST])){
            isValid = false;
        }

        if(!isDBTZValid(line[dataBaseTimeZone])){
            isValid = false;
        }

        return isValid;
    }

    private boolean isIdValid(String id){
        // airport ID Duplication check
        for(Airport airport: airports){
            try{
                if(airport.getAirportID() == Integer.parseInt(id)){
                    errorCounter(101);
                    return false;
                }
            } catch (Exception e){
                    errorCounter(102);
                    return false;
            }
        }
        return true;
    }

    private boolean isNameValid(String name){
        if(!name.matches("[a-zA-Z ]+")){
            errorCounter(103);
            return false;
        }
        return true;
    }

    private boolean isCityValid(String city){
        if(!city.matches("[a-zA-Z ]+")){
            errorCounter(104);
            return false;
        }
        return true;
    }

    private boolean isCountryValid(String country){
        //ISO 3166-1 codes not implemented
        if(!country.matches("[a-zA-Z ]+")){
            errorCounter(105);
            return false;
        }
        return true;
    }

    private boolean isIATAValid(String IATA){
        //airport IATA check
        if(!IATA.equalsIgnoreCase("null") || !IATA.equalsIgnoreCase("unknown")){
            if(!IATA.matches("[a-zA-Z]+" ) || IATA.length() != 3 ){
                errorCounter(106);
                return false;
                }
        }
        return true;
    }

    private boolean isICAOValid(String ICAO){
        //airport ICAO check
        if(ICAO.toLowerCase().equals("null") || ICAO.toLowerCase().equals("unknown")){
            if(!ICAO.matches("[a-zA-Z]+" ) || ICAO.length() != 4 ){
                errorCounter(107);
                return false;
            }
        }
        return true;
    }

    private boolean isLatValid(String lat){
        //airport Latitude check
        try{
            Float.parseFloat(lat);
            return true;
        } catch (Exception e){
            errorCounter(108);
            return false;
        }
    }

    private boolean isLonValid(String lon){
        try{
            Float.parseFloat(lon);
            return true;
        } catch (Exception e){
            errorCounter(109);
            return false;
        }
    }

    private boolean isAltValid(String alt){
        try{
            Integer.parseInt(alt);
            return true;
        } catch (Exception e){
            errorCounter(110);
            return false;
        }
    }

    private boolean isTZValid(String timeZone){
        try{
            Float.parseFloat(timeZone);
            return true;
        } catch (Exception e){
            errorCounter(111);
            return false;
        }
    }

    private boolean isDSTValid(String DST){
        if(!DST.matches("[EASOZNU]+" ) || DST.length() != 1 ){
            errorCounter(112);
            return false;
        }
        return true;
    }

    private boolean isDBTZValid(String DBTZ){
        if(!DBTZ.matches("[a-zA-Z/a-zA-Z_]+" )){
            errorCounter(113);
            return false;
        }
        return true;
    }

    /**
     * Getter for airports
     * @return An hashset contains all airport objects
     */
    public Set<Airport> getAirports() {
        return airports;
    }

}

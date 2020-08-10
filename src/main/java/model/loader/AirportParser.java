package model.loader;

import model.data.Airport;

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
        errorCollectionInitializer(15);
        /**
         * AirportParser Error code:
         * 100: not enough parameters
         * 101: airport id exists
         * 102: invalid id number
         */
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


//        // airport name check
//        if(!line[1].matches("[a-zA-Z ]+")){
//            System.out.println("Unknown type of airport name.");
//            return false;
//        }
//
//        //airport city check
//        if(!line[2].matches("[a-zA-Z ]+")){
//            System.out.println("Unknown type of airport city.");
//            return false;
//        }
//
//        //airport country check
//        //ISO 3166-1 codes not implemented
//        if(!line[3].matches("[a-zA-Z ]+")){
//            System.out.println("Unknown type of airport country.");
//            return false;
//        }
//
//        //airport IATA check
//        if(!(line[4].toLowerCase().equals("null") || line[4].toLowerCase().equals("unknown"))){
//        if(!line[4].matches("[a-zA-Z]+" ) || line[4].length() != 3 ){
//            System.out.println("Unknown type of airport IATA.");
//            return false;
//            }
//        }
//
//        //airport ICAO check
//        if(!(line[5].toLowerCase().equals("null") || line[5].toLowerCase().equals("unknown"))){
//            if(!line[5].matches("[a-zA-Z]+" ) || line[5].length() != 4 ){
//                System.out.println("Unknown type of airport ICAO.");
//                return false;
//            }
//        }
//
//        //airport Latitude check
//        try{
//            Float.parseFloat(line[6]);
//        } catch (Exception e){
//            System.out.println("Unknown type of airport latitude.");
//        }
//
//        //airport longitude check
//        try{
//            Float.parseFloat(line[7]);
//        } catch (Exception e){
//            System.out.println("Unknown type of airport longitude.");
//        }
//
//        //airport altitude check
//        try{
//            Integer.parseInt(line[8]);
//        } catch (Exception e){
//            System.out.println("Unknown type of airport altitude.");
//        }
//
//        //airport time zone check
//        try{
//            Float.parseFloat(line[9]);
//        } catch (Exception e){
//            System.out.println("Unknown type of airport time zone.");
//        }
//
//        //airport DST check
//        if(!line[10].matches("[EASOZNU]+" ) || line[10].length() != 1 ){
//            System.out.println("Unknown type of airport DST.");
//            return false;
//        }
//
//        //airport database time zone check
//        if(!line[11].matches("[a-zA-Z/a-zA-Z_]+" )){
//            System.out.println("Unknown type of airport database time zone.");
//            return false;
//        }
//
//        //--------------------------------------
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
        return false;
    }



    /**
     * Getter for airports
     * @return An hashset contains all airport objects
     */
    public Set<Airport> getAirports() {
        return airports;
    }

}

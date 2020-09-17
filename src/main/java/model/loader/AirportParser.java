package model.loader;

import model.data.Airport;
import model.data.DataType;
import java.util.ArrayList;
import java.util.List;
//TODO: check all method comments start with "This method ..."

/**
 * The sub-class Parser for airport. AirportParser class receives a list of airport data, validates
 * each of the data line and create airport object. Then put the object into a hashSet for return
 * value.
 *
 * @author Enyang Zhang(Lambert)
 * @version 2.3
 * @since 2020-08-22
 */
public class AirportParser extends Parser {
    //Processed airport data
//    private final Set<DataType> airports = new HashSet<>();

    //Alphabetical name to represent line index
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
     * 114: invalid unknown error
     * 115: number of failed insertions
     * 116: invalid alias
     * 117: invalid callsign
     * 118: invalid activestatus
     */

    //TODO: write comments for these attributes

    private final int airportID = 0, name = 1, city = 2, country = 3, IATA = 4, ICAO = 5, latitude = 6, longtitude = 7,
            altitude = 8, timezone = 9, DST = 10, dataBaseTimeZone = 11;

    /**
     * Constructor of AirportParser, it will start dataParse method as well.
     * @param dataFile is the list contains one line of datafile per element.
     */
    public AirportParser(ArrayList<String> dataFile, List<Airport> existingAirports) {
        super(dataFile, 16);
        for (Airport airport : existingAirports) {
            parserData.add(airport);
        }
        try {
            dataParser();
        } catch(RuntimeException e){
            throw e;
        }
    }

    @Override
    /** Initialize the error messages for each error code */
    protected void initErrorLookup() {
        errorLookup[0] = "Not enough parameters";
        errorLookup[1] = "Duplicate airport ID";
        errorLookup[2] = "Invalid airport ID";
        errorLookup[3] = "Invalid airport name";
        errorLookup[4] = "Invalid airport city";
        errorLookup[5] = "Invalid airport country";
        errorLookup[6] = "Invalid IATA code";
        errorLookup[7] = "Invalid ICAO code";
        errorLookup[8] = "Invalid lattitude";
        errorLookup[9] = "Invalid longitude";
        errorLookup[10] = "Invalid altitude";
        errorLookup[11] = "Invalid timezone";
        errorLookup[12] = "Invalid DST";
        errorLookup[13] = "Invalid database timezone";
        errorLookup[14] = "Unknown error";
        errorLookup[15] = "Failed insertion";
    }

    /**
     * Is called when airportParser is initialized. Calls validate method to check each line. If line is valid, creates
     * airport object with attributes from line and adds route to routes set.
     */
    @Override
    protected void dataParser() {
        for (String dataLine : dataFile) {
            if (totalErrors > 200) {
                totalErrors = 0;
                throw new RuntimeException("File rejected: more than 100 lines contain errors");
            }
            parseLine(dataLine);
        }
    }

    /**
     * Data parser to convert airport data from list into airport objects and add to HashSet. will also call validater
     * to verify each airport data.
     */
    protected void parseLine(String dataLine){
         String[] line = dataLine.replaceAll("\"", "").split(",");
            if (validater(line)){
                try{
                    Airport airport = new Airport(Integer.parseInt(line[airportID]),
                            line[name],
                            line[city],
                            line[country],
                            line[IATA],
                            line[ICAO],
                            Double.parseDouble(line[latitude]),
                            Double.parseDouble(line[longtitude]),
                            Integer.parseInt(line[altitude]),
                            Float.parseFloat(line[timezone]),
                            line[DST],
                            line[dataBaseTimeZone]);
                    parserData.add(airport);
                } catch(Exception e) {
                    errorCounter(14);
                }
//            } else {
//                errorCounter(15);
            }

    }



    /**
     * Validates the data in one line is valid or not.
     * @param line String list contains 11 data for airport attributes.
     * @return true if the data line is valid, false if the data line is not expected.
     */
    @Override
    protected boolean validater(String[] line) {
        boolean isValid = true;
        if (line.length != 12){
            errorCounter(0);
            isValid = false;
//            System.out.println(0);
            System.out.println("airport length invaid" + line.length);

        }

        if (!isIdValid(line[airportID])){
            isValid = false;
//            System.out.println(1);
            System.out.println("airport id invaid"  + line[airportID]);

        }

        if(!isNameValid(line[name])){
            isValid = false;
//            System.out.println(2);
            System.out.println("airport name invaid"+ line[name]);

        }

        if(!isCityValid(line[city])){
            isValid = false;
//            System.out.println(3);
            System.out.println(line[city]);

        }

        if(!isCountryValid(line[country])){
            isValid = false;
//            System.out.println(4);
            System.out.println(line[country]);

        }

        if(!isIATAValid(line[IATA])){
            isValid = false;
//            System.out.println(5);
            System.out.println(line[IATA]);

        }

        if(!isICAOValid(line[ICAO])){
            isValid = false;
//            System.out.println(6);
            System.out.println(line[ICAO]);


        }

        if(!isLatValid(line[latitude])){
            isValid = false;
//            System.out.println(7);
            System.out.println(line[latitude]);

        }

        if(!isLonValid(line[longtitude])){
            isValid = false;
//            System.out.println(8);
            System.out.println(line[longtitude]);

        }

        if(!isAltValid(line[altitude])){
            isValid = false;
//            System.out.println(9);
            System.out.println(line[altitude]);

        }

        if(!isTZValid(line[timezone])){
            isValid = false;
//            System.out.println(10);
            System.out.println(line[timezone]);

        }

        if(!isDSTValid(line[DST])){
            isValid = false;
//            System.out.println(11);
            System.out.println(line[DST]);

        }

        if(!isDBTZValid(line[dataBaseTimeZone])){
            isValid = false;
//            System.out.println(12);
            System.out.println(line[dataBaseTimeZone]);

        }

        return isValid;
    }

    /**
     * Check if id is valid.
     * @param id airport id as a string.
     * @return true if valid, false if invalid.
     */
    protected boolean isIdValid(String id){
        // airport ID Duplication check
        for(DataType data: parserData){
            try{
                Airport airport = (Airport) data;
                if(airport.getAirportID() == Integer.parseInt(id)){
                    errorCounter(1);
                    return false;
                }
            } catch (Exception e){
                    errorCounter(2);
                    return false;
            }
        }
        return true;
    }

    /**
     * Check if name is valid.
     * @param name airport name as a string.
     * @return true if valid, false if invalid.
     */
    protected boolean isNameValid(String name){
        if(!name.matches("[a-zA-Z0-9 .'()/-]+")){
            errorCounter(3);
            return false;
        }
        return true;
    }

    /**
     * Check if city is valid.
     * @param city airport city as a string.
     * @return true if valid, false if invalid.
     */
    protected boolean isCityValid(String city){
        if(!city.matches("[a-zA-Z0-9 .'()/-]+")){
            errorCounter(4);
            return false;
        }
        return true;
    }

    /**
     * Check if country is valid.
     * @param country airport country as a string.
     * @return true if valid, false if invalid.
     */
    protected boolean isCountryValid(String country){
        //ISO 3166-1 codes not implemented
        if(!country.matches("[a-zA-Z .'()/-]+")){
            errorCounter(5);
            return false;
        }
        return true;
    }

    /**
     * Check if IATA is valid.
     * @param IATA airport IATA as a string.
     * @return true if valid, false if invalid.
     */
    protected boolean isIATAValid(String IATA){
        //airport IATA check
        if(!IATA.equalsIgnoreCase("null") && !IATA.equalsIgnoreCase("unknown") &&
                !IATA.equalsIgnoreCase("")){
            if(!IATA.matches("[a-zA-Z0-9]+" ) || IATA.length() != 3 ){
                errorCounter(6);
                return false;
                }
        }
        return true;
    }

    /**
     * Check if ICAO is valid.
     * @param ICAO airport ICAO as a string.
     * @return true if valid, false if invalid.
     */
    protected boolean isICAOValid(String ICAO){
        //airport ICAO check
        if(!ICAO.equalsIgnoreCase("null") && !ICAO.equalsIgnoreCase("unknown") &&
                !ICAO.equalsIgnoreCase("\\N") && !ICAO.equalsIgnoreCase("")){
            if(!ICAO.matches("[a-zA-Z0-9]+" ) || ICAO.length() != 4 ){
                errorCounter(7);
                return false;
            }
        }
        return true;
    }

    /**
     * Check if latitude is valid.
     * @param lat airport latitude as a string.
     * @return true if valid, false if invalid.
     */
    protected boolean isLatValid(String lat){
        //airport Latitude check
        try{
            Float.parseFloat(lat);
            return true;
        } catch (Exception e){
            errorCounter(8);
            return false;
        }
    }

    /**
     * Check if longitude is valid.
     * @param lon airport longitude as a string.
     * @return true if valid, false if invalid.
     */
    protected boolean isLonValid(String lon){
        try{
            Float.parseFloat(lon);
            return true;
        } catch (Exception e){
            errorCounter(9);
            return false;
        }
    }

    /**
     * Check if altitude is valid.
     * @param alt airport altitude as a string.
     * @return true if valid, false if invalid.
     */
    protected boolean isAltValid(String alt){
        try{
            Integer.parseInt(alt);
            return true;
        } catch (Exception e){
            errorCounter(10);
            return false;
        }
    }

    /**
     * Check if timeZone is valid.
     * @param timeZone airport timeZone as a string.
     * @return true if valid, false if invalid.
     */
    protected boolean isTZValid(String timeZone){
        try{

            if(-12 <= Float.parseFloat(timeZone) && Float.parseFloat(timeZone) <= 12){
                return true;
            } else {
                return false;
            }
        } catch (Exception e){
            errorCounter(11);
            return false;
        }
    }

    /**
     * Check if DST is valid.
     * @param DST airport DST as a string.
     * @return true if valid, false if invalid.
     */
    protected boolean isDSTValid(String DST){
        if(!DST.matches("[EASOZNU]+" ) || DST.length() != 1 ){
            return false;
        }
        return true;
    }

    /**
     * Check if DBTZ is valid.
     * @param DBTZ airport DBTZ as a string.
     * @return true if valid, false if invalid.
     */
    protected boolean isDBTZValid(String DBTZ){
        if (!DBTZ.equalsIgnoreCase("\\N")) {
            if(!DBTZ.matches("[a-zA-Z-/a-zA-Z-_]+")){
                errorCounter(13);
                return false;
            }
        }
        return true;
    }

//TODO remove?

//    /**
//     * Getter for airports
//     * @return A hashset contains all airport objects.
//     */
//    @Override
//    public Set<DataType> getData() {
//        return parserData;
//    }
}

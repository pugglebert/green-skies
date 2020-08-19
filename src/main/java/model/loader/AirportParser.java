package model.loader;

import model.data.Airport;
import model.data.DataType;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The sub-class Parser for airport. AirportParser class receives a list of airport data, validates
 * each of the data line and create airport object. Then put the object into a hashSet for return
 * value.
 *
 * @author Hayley Krippner
 * @version 2.1
 * @since 2020-08-11
 */
public class AirportParser extends Parser {
    //Processed airport data
    private final Set<DataType> airports = new HashSet<>();
    //Alphabetical name to represent line index
    private final int airportID = 0, name = 1, city = 2, country = 3, IATA = 4, ICAO = 5, latitude = 6, longtitude = 7,
            altitude = 8, timezone = 9, DST = 10, dataBaseTimeZone = 11;

    /**
     * Constructor of AirportParser, it will start dataParse method as well.
     * @param dataFile is the list contains one line of datafile per element.
     */
    public AirportParser(List<String> dataFile) {
        super(dataFile, 16);

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
         *
         * 116: invalid alias
         * 117: invalid callsign
         * 118: invalid activestatus
         */
        dataParser();
    }

    /**
     * Data parser to convert airport data from list into airport objects and add to HashSet. will also call validater
     * to verify each airport data.
     */
    @Override
    protected void dataParser(){

        for (String dataLine: dataFile){
            String[] line= dataLine.replaceAll("\"","").split(",");
            if (validater(line)){
                try{
                    Airport airport = new Airport(Integer.parseInt(line[airportID]), line[name], line[city], line[country], line[IATA],
                            line[ICAO], Float.parseFloat(line[latitude]), Float.parseFloat(line[longtitude]), Integer.parseInt(line[altitude]),
                            Float.parseFloat(line[timezone]), line[DST], line[dataBaseTimeZone]);
                    airports.add(airport);
                } catch(Exception e) {
                    errorCounter(14);
                }
            } else {
                errorCounter(15);
            }
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

    /**
     * Check if id is valid.
     * @param id airport id as a string.
     * @return true if valid, false if invalid.
     */
    private boolean isIdValid(String id){
        // airport ID Duplication check
        for(DataType data: airports){
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
    private boolean isNameValid(String name){
        if(!name.matches("[a-zA-Z ]+")){
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
    private boolean isCityValid(String city){
        if(!city.matches("[a-zA-Z ]+")){
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
    private boolean isCountryValid(String country){
        //ISO 3166-1 codes not implemented
        if(!country.matches("[a-zA-Z ]+")){
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
    private boolean isIATAValid(String IATA){
        //airport IATA check
        if(!IATA.equalsIgnoreCase("null") && !IATA.equalsIgnoreCase("unknown")){
            if(!IATA.matches("[a-zA-Z]+" ) || IATA.length() != 3 ){
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
    private boolean isICAOValid(String ICAO){
        //airport ICAO check
        if(!ICAO.equalsIgnoreCase("null") && !ICAO.equalsIgnoreCase("unknown")){
            if(!ICAO.matches("[a-zA-Z]+" ) || ICAO.length() != 4 ){
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
    private boolean isLatValid(String lat){
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
    private boolean isLonValid(String lon){
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
    private boolean isAltValid(String alt){
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
    private boolean isTZValid(String timeZone){
        try{

            if(-12 < Float.parseFloat(timeZone) && Float.parseFloat(timeZone) < 12){
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
    private boolean isDSTValid(String DST){
        if(!DST.matches("[EASOZNU]+" ) || DST.length() != 1 ){
            errorCounter(12);
            return false;
        }
        return true;
    }

    /**
     * Check if DBTZ is valid.
     * @param DBTZ airport DBTZ as a string.
     * @return true if valid, false if invalid.
     */
    private boolean isDBTZValid(String DBTZ){
        if(!DBTZ.matches("[a-zA-Z/a-zA-Z_]+" )){
            errorCounter(13);
            return false;
        }
        return true;
    }

    /**
     * Getter for airports
     * @return A hashset contains all airport objects.
     */
    @Override
    public Set<DataType> getData() {
        return airports;
    }

}

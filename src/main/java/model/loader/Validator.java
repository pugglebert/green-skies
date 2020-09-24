package model.loader;

import model.data.Airline;
import model.data.DataType;

public class Validator {

    /**
     * This method verifies whether string is a valid openflights airline id code.
     *
     * @param airlineID The string to be verified.
     * @return True if string matches openflights format, false otherwise.
     */
    protected static boolean isAirlineIDValid(String airlineID) {
//        errorCounter(2);
        return airlineID.length() <= 5 && airlineID.matches("[0-9]+");
    }

    /**
     * This method checks if the name is valid.
     *
     * @param name airline name as a string.
     * @return true if valid, false if invalid.
     */
    protected static boolean isAirlineNameValid(String name) {
        //            errorCounter(3);
        return name.matches("[a-zA-Z0-9 -.]+");
    }

    /**
     * This method checks if the city is valid.
     *
     * @param alias airline city as a string.
     * @return true if valid, false if invalid.
     */
    protected static boolean isAliasValid(String alias) {
        //            errorCounter(4);
        return alias.matches("(\\\\N)|([\\w ]+)|(^$)");
    }

    /**
     * This method checks if the IATA is valid.
     *
     * @param IATA airline IATA as a string.
     * @return true if valid, false if invalid.
     */
    protected static boolean isAirlineIATAValid(String IATA) {

        // airline IATA check
        //            errorCounter(5);
        return IATA.matches("-|([A-Z0-9]{2})|(^$)");
    }

    /**
     * This method checks if the ICAO is valid.
     *
     * @param ICAO airline ICAO as a string.
     * @return true if valid, false if invalid.
     */
    protected static boolean isAirlineICAOValid(String ICAO) {

        //            errorCounter(6);
        return ICAO.matches("(\\\\N)|(N/A)|([A-Z0-9]{3})|(^$)");
    }

    /**
     * This method checks if the callsign is valid.
     *
     * @param callsign airline latitude as a string.
     * @return true if valid, false if invalid.
     */
    protected static boolean isCallsignValid(String callsign) {
        // airline callsign check
        //            errorCounter(7);
        return callsign.matches("([A-Za-z -]+)|(^$)");
    }

    /**
     * This method checks if the country is valid.
     *
     * @param country airline country as a string.
     * @return true if valid, false if invalid.
     */
    protected static boolean isCountryValid(String country) {
        // ISO 3166-1 codes not implemented
        //            errorCounter(8);
        return country.matches("(\\\\N)|([a-zA-Z ]+)|(^$)");
    }

    /**
     * This method checks if the activeStatus is valid.
     *
     * @param activeStatus as a string.
     * @return true if valid, false if invalid.
     */
    protected static boolean isActiveStatusValid(String activeStatus) {

        //            errorCounter(9);
        return activeStatus.matches("[YN]");
    }

    /**
     * This method verifies whether string is a valid ICAO or IATA airport code.
     *
     * @param airport The string to be verified.
     * @return True if string matches ICAO or IATA format, false otherwise.
     */
    protected static boolean isAirportValid(String airport) {
        return (airport.length() == 3 || airport.length() == 4) && airport.matches("[A-Z]+");
    }

    /**
     * This method verifies whether string is a valid openflight airport id code.
     *
     * @param airportID The string to be verified.
     * @return True if string matches openflights format, false otherwise.
     */
    protected static boolean isAirportIDValid(String airportID) {
        return (airportID.length() <= 5 && airportID.matches("[0-9]+"));
    }

    /**
     * This method verifies whether string is a valid ICAO or IATA airline code.
     *
     * @param airline The string to be verified.
     * @return True if string matches ICAO or IATA format, false otherwise.
     */
    protected static boolean isAirlineValid(String airline) {
        return (airline.length() == 2 || airline.length() == 3) && airline.matches("[0-9A-Z]+");
    }

    /**
     * This method verifies whether string is "Y" (if route is a codeshare) or empty (if it is not).
     *
     * @param codeshare the string to be tested.
     * @return true if string is "Y" or empty, false otherwise.
     */
    protected static boolean isCodeshareValid(String codeshare) {
        return codeshare.isEmpty() || codeshare.equals("Y");
    }

    /**
     * This method verifies whether string stops represents a number less than 10.
     *
     * @param stops The string to be verified.
     * @return True if string is a number less than 10, false otherwise.
     */
    protected static boolean isStopsValid(String stops) {
        return stops.matches("[0-9]");
    }

    /**
     * This method verifies whether equipment is a sting of whitespace separated 3 character plane
     * codes.
     *
     * @param equipment The string to be tested.
     * @return True if string matches expected format, false otherwise.
     */
    protected static boolean isEquipmentValid(String equipment) {
        String[] equipArray = equipment.split(" ");
        for (String plane : equipArray) {
            if (!(plane.length() == 3 && plane.matches("[0-9A-Z]+"))) {
                return false;
            }
        }
        return true;
    }
}

package model.loader;

import model.data.Airport;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AirportParser extends Parser {
    private final Set<Airport> airports = new HashSet<>();

    public AirportParser(List<String> dataFile) {
        super(dataFile);
        dataParse();
    }

    private void dataParse(){
        for (String dataLine: dataFile){
            String[] line= dataLine.split(",");

            if (validater(line)){
                try{
                    Airport airport = new Airport(Integer.parseInt(line[0]), line[1], line[2], line[3], line[4], line[5], Float.parseFloat(line[6]), Float.parseFloat(line[7]), Integer.parseInt(line[8]), Float.parseFloat(line[9]), line[10], line[11]);
                    airports.add(airport);
                } catch(Exception e) {
                    System.out.println("Unknown Error.");
                }

            } else {
                System.out.println("Unable to insert data.");
            }
        }
    }

    private Boolean validater(String[] line) {
        // airport ID Duplication check
        for(Airport airport: airports){
            try{
                if(airport.airportID == Integer.parseInt(line[0])){
                    System.out.println("Duplicated airport ID.");
                    return false;
                    }
            } catch (Exception e){
                System.out.println("Unknown type of airport ID.");
            }
        }

        // airport name check
        if(!line[1].matches("[a-zA-Z ]+")){
            System.out.println("Unknown type of airport name.");
            return false;
        }

        //airport city check
        if(!line[2].matches("[a-zA-Z ]+")){
            System.out.println("Unknown type of airport city.");
            return false;
        }

        //airport country check
        //ISO 3166-1 codes not implemented
        if(!line[3].matches("[a-zA-Z ]+")){
            System.out.println("Unknown type of airport country.");
            return false;
        }

        //airport IATA check
        if(!(line[4].toLowerCase().equals("null") || line[4].toLowerCase().equals("unknown"))){
        if(!line[4].matches("[a-zA-Z]+" ) || line[4].length() != 3 ){
            System.out.println("Unknown type of airport IATA.");
            return false;
            }
        }

        //airport ICAO check
        if(!(line[5].toLowerCase().equals("null") || line[5].toLowerCase().equals("unknown"))){
            if(!line[5].matches("[a-zA-Z]+" ) || line[5].length() != 4 ){
                System.out.println("Unknown type of airport ICAO.");
                return false;
            }
        }

        //airport Latitude check
        try{
            Float.parseFloat(line[6]);
        } catch (Exception e){
            System.out.println("Unknown type of airport latitude.");
        }

        //airport longitude check
        try{
            Float.parseFloat(line[7]);
        } catch (Exception e){
            System.out.println("Unknown type of airport longitude.");
        }

        //airport altitude check
        try{
            Integer.parseInt(line[8]);
        } catch (Exception e){
            System.out.println("Unknown type of airport altitude.");
        }

        //airport time zone check
        try{
            Float.parseFloat(line[9]);
        } catch (Exception e){
            System.out.println("Unknown type of airport time zone.");
        }

        //airport DST check
        if(!line[10].matches("[EASOZNU]+" ) || line[10].length() != 1 ){
            System.out.println("Unknown type of airport DST.");
            return false;
        }

        //airport database time zone check
        if(!line[11].matches("[a-zA-Z/a-zA-Z_]+" )){
            System.out.println("Unknown type of airport database time zone.");
            return false;
        }

        //--------------------------------------
        System.out.println("Data validated.");
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

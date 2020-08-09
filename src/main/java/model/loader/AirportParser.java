package model.loader;

import model.data.Airline;
import model.data.Airport;
import java.util.ArrayList;
import java.util.HashSet;

public class AirportParser extends Parser {
    private HashSet<Airport> airports;

    public AirportParser(ArrayList<String> dataFile) {
        super(dataFile);
    }

    private void dataParse(){
//        for (String dataLine: dataFile){
//            String[] line= dataLine.split(",");
        String dataline = "4,Nadzab,Nadzab,Papua New Guinea,LAE,AYNZ,-6.569828,146.726242,239,10,U,Pacific/Port_Moresby";
        String[] line = dataline.split(",");
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
//    }

    private Boolean validater(String[] line) {

        for(Airport airport: airports){
            // airport ID Duplication check
            try{
                if(airport.airportID == Integer.parseInt(line[0])){
                    System.out.println("Duplicated airport ID.");
                    return false;
                    }
            } catch (Exception e){
                System.out.println("Unknown type of airport ID.");
            }

            // airport name check
            if(!airport.name.matches("[a-zA-Z ]+")){
                System.out.println("Unknown type of airport name.");
                return false;
            }

            //airport city check
            if(!airport.city.matches("[a-zA-Z ]+")){
                System.out.println("Unknown type of airport city.");
                return false;
            }

            //airport country check
            //ISO 3166-1 codes not implemented
            if(!airport.country.matches("[a-zA-Z ]+")){
                System.out.println("Unknown type of airport country.");
                return false;
            }

            //airport IATA check
            if(!airport.IATA.matches("[a-zA-Z]+" || )){
                System.out.println("Unknown type of airport IATA.");
                return false;
            }
        }
        System.out.println("Data validated.");
        return true;
    }

    public HashSet<Airport> getAirports() {
        return airports;
    }

}

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
            Airport airport = new Airport(Integer.parseInt(line[0]), line[1], line[2], line[3], line[4], line[5], Float.parseFloat(line[6]), Float.parseFloat(line[7]), Integer.parseInt(line[8]), Float.parseFloat(line[9]), line[10], line[11]);
            airports.add(airport);
        } else {
            System.out.println("Invalid data format");
        }
        }
//    }

    private Boolean validater(String[] line) {
//        Boolean isValiate = true;
//        try{
//            line[0] = Integer.parseInt(line[0]);
//        }
        return true;
    }

    public HashSet<Airport> getAirports() {
        return airports;
    }

}

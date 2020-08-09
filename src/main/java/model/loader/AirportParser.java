package model.loader;

import model.data.Airport;

import java.util.ArrayList;
import java.util.HashSet;

public class AirportParser extends Parser {
    private HashSet<Airport> AirportLine;

    public AirportParser(ArrayList<String> dataFile) {
        super(dataFile);
    }

    private void dataParse(){
        for (String dataLine: dataFile){
            String[] line= dataLine.split(",");

        }
    }

//    private Boolean validater(String[] line) {
//
//    }
//
//    public static void main(String[] args){
//        String[] test =
//
//    }
}

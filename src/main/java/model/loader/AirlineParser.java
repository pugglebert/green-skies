package model.loader;


import model.data.Airline;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class AirlineParser extends Parser{
    private Set<Airline> airlines = new HashSet<>();

    public AirlineParser(ArrayList<String> dataFile) {
        super(dataFile);
        dataParser();
    }

    public void dataParser(){
        for (String dataLine: dataFile){
            String[] line= dataLine.split(",");
// need if(validate(line))
            if(validater(line)){
                try{
                    Airline airline = new Airline(Integer.parseInt(line[0]), line[1], line[2], line[3], line[4], line[5], line[6], Boolean.parseBoolean(line[7]));

                }
                catch (Exception e){

                }
            }
        }
    }


    public boolean validater(String[] line) {
        return false;
    }









}

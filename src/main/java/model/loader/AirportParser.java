package model.loader;

import java.util.ArrayList;

public class AirportParser extends Parser {
    private ArrayList<String> AirportLine;

    public AirportParser(ArrayList<String> dataFile, String dataType) {
        super(dataFile, dataType);
    }
}

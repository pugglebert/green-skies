package model.loader;

import java.util.ArrayList;

public class FlightParser extends Parser {
    public FlightParser(ArrayList<String> dataFile) {
        super(dataFile);
    }

    @Override
    protected void dataParser() {

    }

    @Override
    public boolean validater(String[] line) {
        return false;
    }
}
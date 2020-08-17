package model.loader;

import model.data.DataType;

import java.util.ArrayList;
import java.util.Set;

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

    @Override
    public Set<DataType> getData() {
        return null;
    }
}

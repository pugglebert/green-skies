package model.loader;

import java.util.ArrayList;

public class RouteParser extends Parser {
    public RouteParser(ArrayList<String> dataFile) {
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

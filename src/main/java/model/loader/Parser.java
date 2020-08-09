package model.loader;

import java.util.ArrayList;

public abstract class Parser {
    private ArrayList<String> dataFile;


    public Parser (ArrayList<String> dataFile){
        this.dataFile = dataFile;

    }

    private void validateEntry(){};

    private void ValidateLine(){};

    private void parseLine(){};

    private void parseEntry(){};
}

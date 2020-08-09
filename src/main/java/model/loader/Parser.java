package model.loader;

import java.util.ArrayList;

public abstract class Parser {
    public ArrayList<String> dataFile;


    public Parser (ArrayList<String> dataFile){
        this.dataFile = dataFile;

    }


    private void Validater(){};

    private void dataParse(){};

}
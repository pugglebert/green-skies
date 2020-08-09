package model.loader;

import java.util.ArrayList;

public abstract class Parser {
    private ArrayList<String> dataFile;
    private String dataType;

    public Parser (ArrayList<String> dataFile, String dataType){
        this.dataFile = dataFile;
        this.dataType = dataType;
    }



    private void dataValidater(){};

}

package model.loader;

import java.util.List;

public abstract class Parser {
    public final List<String> dataFile;

    public Parser(List<String> dataFile) {
        this.dataFile = dataFile;
    }

    protected void dataParse(){}

    protected void validater(){}


}
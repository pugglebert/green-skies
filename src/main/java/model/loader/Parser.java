package model.loader;

import java.util.List;

public abstract class Parser {
    public final List<String> dataFile;

    public Parser(List<String> dataFile) {
        this.dataFile = dataFile;
    }

    protected abstract void dataParse();

    protected abstract boolean validater(String[] line);
}



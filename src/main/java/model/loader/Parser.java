package model.loader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Parser {
    protected final List<String> dataFile;
  /**
   * Stores int key for error code and int value for error quantity.
   * */
  protected Map<Integer, Integer> errorCollection = new HashMap<>();

    public Parser(List<String> dataFile) {
        this.dataFile = dataFile;
    }

    protected abstract void dataParse();

    protected abstract boolean validater(String[] line);

    protected Map<Integer, Integer> getErrorCollection(){
        return errorCollection;
    };

    protected void errorCounter(int key){
        try{
            errorCollection.put(key, errorCollection.get(key)+1);
        } catch (Exception e){
            System.out.println("key not found");
        }
    }

    protected void errorCollectionInitializer(int errorCodeNum){
        for (int i = 100; i < errorCodeNum + 100; i++){
            errorCollection.put(i, 0);
        }
    }
}



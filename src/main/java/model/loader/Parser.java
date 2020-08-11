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

    /**
     * Constructor of Paser class.
     * @param dataFile passed from loader, contains all data from datafile, one line per element in the list.
     */
    public Parser(List<String> dataFile) {
        this.dataFile = dataFile;
    }

    /**Abstract class of dataPasrser.*/
    protected abstract void dataParser();

    /**Abstract class of validater.*/
    protected abstract boolean validater(String[] line);

    /**
     * Getter for error collection.
     * @return the HashMap errorCollection.
     */
    protected Map<Integer, Integer> getErrorCollection(){
        return errorCollection;
    }

    /**
     * Initialize error code key in errorCollection.
     * @param errorCodeNum number of error code that are expected to be generated in hashmap
     */
    protected void errorCollectionInitializer(int errorCodeNum){
        for (int i = 100; i < errorCodeNum + 100; i++){
            errorCollection.put(i, 0);
        }
    }

    /**
     * Count quantity of occurrences for same error. Will plus one on value to given error code.
     * @param key error code initialized before use.
     */
    protected void errorCounter(int key){
        try{
            errorCollection.put(key, errorCollection.get(key)+1);
        } catch (Exception e){
            System.out.println(key + " key not found");
        }
    }

}



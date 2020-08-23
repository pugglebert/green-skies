package model.loader;

import model.data.DataType;

import java.util.*;

public abstract class Parser {
    protected final List<String> dataFile;
  /**
   * Stores int key for error code and int value for error quantity.
   * */
  protected Map<Integer, Integer> errorCollection = new HashMap<>();

  /** The number of error codes for each parser type. */
  protected int numCodes;

  /** Arraylist of meaning of error codes for Airport Parser, where each index corresponds to an error code. */
  protected String[] errorLookup;

//  /** Arraylist of meaning of error codes for Airline Parser, where each index corresponds to an error code. */
//  protected String[] errorLookupAirLine;
//
//  /** Arraylist of meaning of error codes for Route Parser, where each index corresponds to an error code. */
//  protected String[] errorLookupRoute;

  /** The total number of errors found while parsing the file. */
  protected int totalErrors = 0;

  /** The set contains airport, Airline, route for each sub-parser.*/
  protected final Set<DataType> parserData = new HashSet<>();

    /**
     * Constructor of Paser class.
     * @param dataFile passed from loader, contains all data from datafile, one line per element in the list.
     */
    public Parser(List<String> dataFile, int numCodes) {
        this.dataFile = dataFile;
        this.numCodes = numCodes;
        errorCollectionInitializer(numCodes);
        errorLookup = new String[numCodes];
//        errorLookupRoute = new String[numCodes];
//        errorLookupAirLine = new String[numCodes];
        initErrorLookup();
    }

    /** Initialize errorLookup with message for each error code */
    protected abstract void initErrorLookup();

    /**Abstract class of dataPasrser.*/
    protected abstract void dataParser();

    /**Abstract class of validater.*/
    protected abstract boolean validater(String[] line);

    /**Getter returning processed data result for all sub-parsers. */
    public Set<DataType> getData(){
        return parserData;
    };

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
    private void errorCollectionInitializer(int errorCodeNum){
        for (int i = 0; i < errorCodeNum; i++){
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
            totalErrors++;
        } catch (Exception e){
            System.out.println(key + " key not found");
        }
    }

    /**
     * Create and return a message detailing the errors found in the file
     * @return String with information about error types in fiile
     */
    public String getErrorMessage() {
        String errorMessage = String.format("File uploaded with %d invalid lines rejected\n", totalErrors);
        String template  = "Error [%d] %s: %d occurances\n";
        for (int i = 0; i < numCodes; i++) {
            if (errorCollection.get(i) > 0) {
                errorMessage += String.format(template, i, errorLookup[i], errorCollection.get(i));
            }
        }
        return errorMessage;
    }

}



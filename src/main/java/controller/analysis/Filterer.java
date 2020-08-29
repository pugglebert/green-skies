package controller.analysis;

import model.data.Airport;
import java.util.*;

/**
 * Class for filtering data based on one or more filter options.
 *
 * @author Hayley Krippner
 * @since 29/08/20
 * @version 1.0
 */
public class Filterer {

    private Searcher searcher;

    /**
     * Constructer for Filterer.
     * @param searcher, the Searcher used to search for specific data records that match a given criteria.
     */
    public Filterer(Searcher searcher) {
        this.searcher = searcher;
    }

    /**
   * Filters airport data to get the airport records that contain the desired filter entries.
   *
   * @param filterTerms an HashMap of Strings of filter terms selected by the user for airport records they want
   *                    information on and their associated attribute type as a String.
   * @param airports a list of airports to filter.
   * @return an ArrayList of the airports that are filtered based on the filter terms.
   */
  public ArrayList<Airport> filterAirports(HashMap<String, String> filterTerms, List<Airport> airports) {

    ArrayList<ArrayList<Airport>> individualLists = new ArrayList<>();
    //Iterate through and perform a search for each term and add these lists to an overall list.
    for (Map.Entry<String, String> entry : filterTerms.entrySet()) {
        String filterTermType = entry.getKey();
        String filterTerm = entry.getValue();
        individualLists.add(searcher.searchAirports(filterTerm, filterTermType, airports));
        }

    ArrayList<Airport> filteredAirports = individualLists.get(0); //set up with first group of airport records.
    //Iterate through the indivual lists and take the intersection of the Airport records.
    int filteredAirportsSize = individualLists.size() - 1;
    for (int index = 1; index < filteredAirportsSize; index++) {
        // removes the elements from the current search return list that are not contained filteredAirports
        filteredAirports.retainAll(individualLists.get(index));
        }

    return filteredAirports;
    }

}
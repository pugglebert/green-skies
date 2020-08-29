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

      //    if (filterTerms.size() == 1) {
      //set up with first group of airport records.
      ArrayList<Airport> filteredAirports = individualLists.get(0);
//
//    } else if (filterTerms.size() > 1) {
//        filteredAirports = individualLists.get(0); //set up with first group of airport records.

        // Iterate through the indivual lists and take the intersection of the Airport records.
      int filteredAirportsSize = individualLists.size();
      for (int index = 1; index < filteredAirportsSize; index++) {
        // removes the elements from the current search return list that are not contained
          ArrayList<Airport> priorIntersectionArray = filteredAirports;
          System.out.println(filteredAirports);
          filteredAirports.retainAll(individualLists.get(index));
//          if (priorIntersectionArray == filteredAirports){ //no change so no entries in common.
//            ArrayList<Airport> emptyArray = new ArrayList<>();
//              filteredAirports = emptyArray;
//          }


        //filteredAirports.retainAll(individualLists.get(index));
      }
      // }


    if (filteredAirports.isEmpty()) {
        throw new RuntimeException("No entries match your filter term(s).");
      }
    return filteredAirports;
    }

}

//        for (int index = 0; index < individualLists.size(); index++){
//
//        System.out.println(individualLists.get(index).toString());
//        }
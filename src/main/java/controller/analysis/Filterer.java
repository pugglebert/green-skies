package controller.analysis;

import model.data.Airline;
import model.data.Airport;
import model.data.Route;
import java.util.*;

/**
 * Class for filtering data based on one or more filter options.
 *
 * @author Hayley Krippner
 * @since 30/08/20
 * @version 1.0
 */
public class Filterer {

    private ArrayList<Airport> filteredAirports;
    private ArrayList<Airline> filteredAirlines;
    private ArrayList<Route> filteredRoutes;
    private boolean filterSuccess;

    /**
   * Filters airport data to get the Airport records that contain the desired filter entries.
   *
   * @param filterTerms an HashMap of Strings of filter terms selected by the user for Airport records they want
   *                    information on and their associated attribute type as a String.
   * @param airports a List of Airports to filter.
   * @return an ArrayList of the Airports that are filtered based on the filter terms.
   */
  public void filterAirports(HashMap<String, String> filterTerms, List<Airport> airports) {

    ArrayList<ArrayList<Airport>> individualLists = new ArrayList<>();
    //Iterate through and perform a search for each term and add these lists to an overall list.
    for (Map.Entry<String, String> entry : filterTerms.entrySet()) {
        String filterTermType = entry.getKey();
        String filterTerm = entry.getValue();
        individualLists.add(Searcher.searchAirports(filterTerm, filterTermType, airports));
    }

    ArrayList<Airport> filteredAirports = individualLists.get(0);
    // Iterate through the indivual lists and take the intersection of the Airport records.
    int filteredAirportsSize = individualLists.size();
    for (int index = 1; index < filteredAirportsSize; index++) {
        // removes the elements from the current search return list that are not contained in the current list.
        filteredAirports.retainAll(individualLists.get(index));
      }

    if (filteredAirports.isEmpty()) {
        throw new RuntimeException("No entries match your filter term(s).");
      }

    this.filteredAirports = filteredAirports;

    }

    /**
     * Filters airline data to get the airport records that contain the desired filter entries.
     *
     * @param filterTerms an HashMap of Strings of filter terms selected by the user for airline records they want
     *                    information on and their associated attribute type as a String.
     * @param airlines a List of Airlines to filter.
     * @return an ArrayList of the Airlines that are filtered based on the filter terms.
     */
    public void filterAirlines(HashMap<String, String> filterTerms, List<Airline> airlines) {

        ArrayList<ArrayList<Airline>> individualLists = new ArrayList<>();
        //Iterate through and perform a search for each term and add these lists to an overall list.
        for (Map.Entry<String, String> entry : filterTerms.entrySet()) {
            String filterTermType = entry.getKey();
            String filterTerm = entry.getValue();
            individualLists.add(Searcher.searchAirlines(filterTerm, filterTermType, airlines));
        }

        ArrayList<Airline> filteredAirlines = individualLists.get(0);
        // Iterate through the indivual lists and take the intersection of the Airline records.
        int filteredAirlinesSize = individualLists.size();
        for (int index = 1; index < filteredAirlinesSize; index++) {
            // removes the elements from the current search return list that are not contained in the current list.
            filteredAirlines.retainAll(individualLists.get(index));
        }

        if (filteredAirlines.isEmpty()) {
            throw new RuntimeException("No entries match your filter term(s).");
        }

        this.filteredAirlines = filteredAirlines;
    }

    /**
     * Filters airport data to get the airport records that contain the desired filter entries.
     *
     * @param filterTerms an HashMap of Strings of filter terms selected by the user for airport records they want
     *                    information on and their associated attribute type as a String.
     * @param routes a List of Routes to filter.
     * @return an ArrayList of the Routes that are filtered based on the filter terms.
     */
    public void filterRoutes(HashMap<String, String> filterTerms, List<Route> routes) {

        ArrayList<ArrayList<Route>> individualLists = new ArrayList<>();
        //Iterate through and perform a search for each term and add these lists to an overall list.
        for (Map.Entry<String, String> entry : filterTerms.entrySet()) {
            String filterTermType = entry.getKey();
            String filterTerm = entry.getValue();
            individualLists.add(Searcher.searchRoutes(filterTerm, filterTermType, routes));
        }

        ArrayList<Route> filteredRoutes = individualLists.get(0);
        // Iterate through the indivual lists and take the intersection of the Airline records.
        int filteredRoutesSize = individualLists.size();
        for (int index = 1; index < filteredRoutesSize; index++) {
            // removes the elements from the current search return list that are not contained
            filteredRoutes.retainAll(individualLists.get(index));
        }

        if (filteredRoutes.isEmpty()) {
            throw new RuntimeException("No entries match your filter term(s).");
        }

        this.filteredRoutes = filteredRoutes;
    }

    public void setFilterSuccess(boolean bool) {
        filterSuccess = bool;
    }

    public boolean getFilterSuccess() {
        return filterSuccess;
    }

    public List<Airport> getFilteredAirports() {
        return filteredAirports;
    }

    public List<Airline> getFilteredAirlines() {return filteredAirlines;}

    public List<Route> getFilteredRoutes() {return filteredRoutes;}

}

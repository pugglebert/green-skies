package controller.analysis;

import model.data.Airline;
import model.data.Airport;
import model.data.Route;
import java.util.*;


/**
 * Class for filtering data based on one or more filter options.
 *
 * @author Hayley Krippner, Ella Johnson
 * @since 30/08/2020
 * @version 1.0
 */
public class Filterer {

  /** A list of airports after a filter has been applied to them. */
  private ArrayList<Airport> filteredAirports;
  /** A list of airlines after a filter has been applied to them. */
  private ArrayList<Airline> filteredAirlines;
  /** A list of routes after a filter has been applied to them. */
  private ArrayList<Route> filteredRoutes;
  /** Records whether the latest filter was successfully executed. */
  private boolean filterSuccess;

  /**
   * This method filters airport data to get the Airport records that contain the desired filter entries.
   *
   * @param filterTerms an HashMap of Strings of filter terms selected by the user for Airport
   *     records they want information on and their associated attribute type as a String.
   * @param airports a List of Airports to filter.
   */
  public void filterAirports(HashMap<String, String> filterTerms, List<Airport> airports) {

    ArrayList<ArrayList<Airport>> individualLists = new ArrayList<>();
    // Iterate through and perform a search for each term and add these lists to an overall list.
    for (Map.Entry<String, String> entry : filterTerms.entrySet()) {
      String filterTermType = entry.getKey();
      String filterTerm = entry.getValue();
      individualLists.add(Searcher.searchAirports(filterTerm, filterTermType, airports));
    }

    ArrayList<Airport> filteredAirports = individualLists.get(0);
    // Iterate through the indivual lists and take the intersection of the Airport records.
    int filteredAirportsSize = individualLists.size();
    for (int index = 1; index < filteredAirportsSize; index++) {
      // removes the elements from the current search return list that are not contained in the
      // current list.
      filteredAirports.retainAll(individualLists.get(index));
    }

    if (filteredAirports.isEmpty()) {
      throw new RuntimeException("No entries match your filter term(s).");
    }

    this.filteredAirports = filteredAirports;
  }

  /**
   * This method filters airline data to get the airport records that contain the desired filter entries.
   *
   * @param filterTerms an HashMap of Strings of filter terms selected by the user for airline
   *     records they want information on and their associated attribute type as a String.
   * @param airlines a List of Airlines to filter.
   */
  public void filterAirlines(HashMap<String, String> filterTerms, List<Airline> airlines) {

    ArrayList<ArrayList<Airline>> individualLists = new ArrayList<>();
    // Iterate through and perform a search for each term and add these lists to an overall list.
    for (Map.Entry<String, String> entry : filterTerms.entrySet()) {
      String filterTermType = entry.getKey();
      String filterTerm = entry.getValue();
      individualLists.add(Searcher.searchAirlines(filterTerm, filterTermType, airlines));
    }

    ArrayList<Airline> filteredAirlines = individualLists.get(0);
    // Iterate through the indivual lists and take the intersection of the Airline records.
    int filteredAirlinesSize = individualLists.size();
    for (int index = 1; index < filteredAirlinesSize; index++) {
      // removes the elements from the current search return list that are not contained in the
      // current list.
      filteredAirlines.retainAll(individualLists.get(index));
    }

    if (filteredAirlines.isEmpty()) {
      throw new RuntimeException("No entries match your filter term(s).");
    }

    this.filteredAirlines = filteredAirlines;
  }

  /**
   * This method filters airport data to get the airport records that contain the desired filter entries.
   *
   * @param filterTerms an HashMap of Strings of filter terms selected by the user for airport
   *     records they want information on and their associated attribute type as a String.
   * @param routes a List of Routes to filter.
   */
  public void filterRoutes(HashMap<String, String> filterTerms, List<Route> routes) {

    ArrayList<ArrayList<Route>> individualLists = new ArrayList<>();
    // Iterate through and perform a search for each term and add these lists to an overall list.
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

  /**
   * This method allows class calling filter datatype methods to set filter success to true if there were no
   * errors or false if errors occured.
   *
   * @param success Value to set filter success attribute to.
   */
  public void setFilterSuccess(boolean success) {
    filterSuccess = success;
  }

  /**
   * This method returns the filterSuccess attribute, which should be set to true if filtering was executed with
   *  no errors and false otherwise.
   *
   * @return filterSuccess.
   */
  public boolean getFilterSuccess() {
    return filterSuccess;
  }

  /**
   * This method returns a list of airports after latest filterAirports call has been carried out.
   *
   * @return filteredAirports.
   */
  public List<Airport> getFilteredAirports() {
    return filteredAirports;
  }

  /**
   * This method returns a list of airlines after latest filterAirlines call has been carried out.
   *
   * @return filteredAirlines.
   */
  public List<Airline> getFilteredAirlines() {
    return filteredAirlines;
  }

  /**
   * This method returns a list of routes after latest filterRoutes call has been carried out.
   *
   * @return filteredRoutes.
   */
  public List<Route> getFilteredRoutes() {
    return filteredRoutes;
  }
}

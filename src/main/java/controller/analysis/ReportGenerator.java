package controller.analysis;

import model.data.Route;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class for which contains the methods for calcuting and updating the analysis data for the user's
 * carbon emissions report.
 *
 * @author Hayley Krippner
 * @since 13/09/20
 * @version 1.0
 */
public class ReportGenerator {

  private double totalDistanceTravelled = 0.0; // km

  private double totalCarbonEmissions = 0.0; // grams

  private ArrayList<Route> mostEmissionsRoutes = new ArrayList<>();

  private ArrayList<Route> leastEmissionsRoutes = new ArrayList<>();

  private ArrayList<Route> mostDistanceRoutes = new ArrayList<>();

  private ArrayList<Route> leastDistanceRoutes = new ArrayList<>();

  private ArrayList<String> mostVisitedSrcAirports = new ArrayList<>();

  private ArrayList<String> mostVisitedDestAirports = new ArrayList<>();

  private ArrayList<Route> mostTravelledRoutes = new ArrayList<>();

  private ArrayList<Route> leastTravelledRoutes = new ArrayList<>();

  private double carbonEmissionGoal = 0.0; // grams

  private double howMuchToReduceCO2By = 0.0;

  private double analysisPeriod = 0.0;

  private double treesToGrow = 0.0;

  /**
   * This method updates the total carbon emissions from flight travel.
   *
   * @param currentRouteRecord, the current route record that is being added to user's flight
   *     history.
   */
  public void updateTotalEmissions(Route currentRouteRecord) {
    totalCarbonEmissions += (currentRouteRecord.getEmissions() * currentRouteRecord.getTimesTake());
  }

  /**
   * This method updates the total distance travelled via flight travel.
   *
   * @param currentRouteRecord, the current route record that is being added to user's flight
   *     history.
   */
  public void updateTotalDistance(Route currentRouteRecord) {
    totalDistanceTravelled += (currentRouteRecord.getDistance());
  }

  /**
   * This method updates the current most travelled flight route.
   *
   * @param routeHistoryEntries, the current route record that is being added to user's flight
   *     history.
   */
  public void updateMostTravelledRoute(List<Route> routeHistoryEntries) {
    // TODO: remove once the quicksort and binary sort have been thoughroughly tested.
    //        int currMaxRouteUsed = 0;
    //        for (Route route : routeHistoryEntries) {
    //            if (route.getTimesTake() > currMaxRouteUsed) {
    //                mostTravelledRoutes.clear();
    //                mostTravelledRoutes.add(route);
    //            } else if (route.getTimesTake() == currMaxRouteUsed) {
    //                mostTravelledRoutes.add(route);
    //            }
    //        }

    quickSort(routeHistoryEntries, 0, routeHistoryEntries.size() - 1);
    int maxRouteCounter = routeHistoryEntries.get(routeHistoryEntries.size() - 1).getTimesTake();
    int firstOccuranceIndex = binarySearch(routeHistoryEntries, maxRouteCounter);
    for (int i = firstOccuranceIndex; i < routeHistoryEntries.size() - 1; i++) {
      mostTravelledRoutes.add(routeHistoryEntries.get(i));
    }
  }

  /**
   * This method updates the current least travelled flight route, provided the route is already in
   * the flight history.
   *
   * @param routeHistoryEntries, the current route record that is being added to user's flight
   *     history.
   */
  public void updateLeastTravelledRoute(List<Route> routeHistoryEntries) {
    // TODO: remove once the quicksort and binary sort have been thoughroughly tested.

    //        int currMaxRouteUsed = 0;
    //        for (Route route : routeHistoryEntries) {
    //            if (route.getTimesTake() > currMaxRouteUsed) {
    //                leastTravelledRoutes.clear();
    //                leastTravelledRoutes.add(route);
    //            } else if (route.getTimesTake() == currMaxRouteUsed) {
    //                leastTravelledRoutes.add(route);
    //            }
    //        }

    quickSort(routeHistoryEntries, 0, routeHistoryEntries.size() - 1);
    int minRouteCounter = routeHistoryEntries.get(0).getTimesTake();
    int firstOccuranceIndex = binarySearch(routeHistoryEntries, minRouteCounter);
    for (int i = 0; i < firstOccuranceIndex + 1; i++) {
      mostTravelledRoutes.add(routeHistoryEntries.get(i));
    }
  }

  /**
   * This method updates the which route produces the most emissions.
   *
   * @param currentRouteRecord
   */
  public void updateMostEmissionsRoute(Route currentRouteRecord) {
    if (this.mostEmissionsRoutes.size() == 0) {
      mostEmissionsRoutes.add(currentRouteRecord);
    } else if (currentRouteRecord.getEmissions() > mostEmissionsRoutes.get(0).getEmissions()) {
        mostEmissionsRoutes.clear();
        mostEmissionsRoutes.add(currentRouteRecord);
      } else if (currentRouteRecord.getEmissions() == mostEmissionsRoutes.get(0).getEmissions()) {
        mostEmissionsRoutes.add(currentRouteRecord);
      }
    }


  /**
   * This method updates the which route produces the least emissions. The currentRouteRecord is
   * checked against the current route with the least emissions so far.
   *
   * @param currentRouteRecord
   */
  public void updateLeastEmissionsRoute(Route currentRouteRecord) {
    if (leastEmissionsRoutes.isEmpty() == true) {
      leastEmissionsRoutes.add(currentRouteRecord);
    } else if (currentRouteRecord.getEmissions() < leastEmissionsRoutes.get(0).getEmissions()) {
        leastEmissionsRoutes.clear();
        leastEmissionsRoutes.add(currentRouteRecord);
      } else if (currentRouteRecord.getEmissions() == leastEmissionsRoutes.get(0).getEmissions()) {
        leastEmissionsRoutes.add(currentRouteRecord);
      }
    }


  /**
   * This method updates which route is of the greatest distance. The currentRouteRecord is checked
   * against the current route with the most emissions so far.
   *
   * @param currentRouteRecord
   */
  public void updateMostDistanceRoute(Route currentRouteRecord) {
    if (mostDistanceRoutes.isEmpty() == true) {
      mostDistanceRoutes.add(currentRouteRecord);
    } else if (currentRouteRecord.getDistance() > mostDistanceRoutes.get(0).getDistance()) {
        mostDistanceRoutes.clear();
        mostDistanceRoutes.add(currentRouteRecord);
      } else if (currentRouteRecord.getDistance() == mostDistanceRoutes.get(0).getDistance()) {
        mostDistanceRoutes.add(currentRouteRecord);
      }
    }


  /**
   * This method updates which route is of the least distance. The currentRouteRecord is checked
   * against the current route with the least emissions so far.
   *
   * @param currentRouteRecord
   */
  public void updateLeastDistanceRoute(Route currentRouteRecord) {
    if (leastDistanceRoutes.isEmpty() == true) {
      leastDistanceRoutes.add(currentRouteRecord);
    } else if (currentRouteRecord.getDistance() < leastDistanceRoutes.get(0).getDistance()) {
        leastDistanceRoutes.clear();
        leastDistanceRoutes.add(currentRouteRecord);
      } else if (currentRouteRecord.getDistance() == leastDistanceRoutes.get(0).getDistance()) {
        leastDistanceRoutes.add(currentRouteRecord);
      }
    }

  // TODO: write a single method for MostVisitedSrcAirports and MostVisitedDestAirports

  /**
   * Calculates the airport(s) that was the most visited, based on the user's flight history
   * entries.
   *
   * @return mostVisitedSrcAirports, an ArrayList containing the String of the name of the airports
   *     that were most visited, based on the user's flight history entries.
   */
  public void updateMostVisitedSrcAirports(HashMap<String, Integer> srcAirportCounts) {
    int currSrcAirportMax = 0;
    for (Map.Entry<String, Integer> entry : srcAirportCounts.entrySet()) {
      if (entry.getValue() > currSrcAirportMax) {
        currSrcAirportMax = entry.getValue();
        mostVisitedSrcAirports.clear();
        mostVisitedSrcAirports.add(entry.getKey());
      } else if (entry.getValue() == currSrcAirportMax) {
        currSrcAirportMax = entry.getValue();
        mostVisitedSrcAirports.add(entry.getKey());
      }
    }
  }

  /**
   * Calculates the airport(s) that was the least visited, based on the user's flight history
   * entries.
   *
   * @return mostVisitedSrcAirports, an ArrayList containing the String of the name of the airports
   *     that were most visited, based on the user's flight history entries.
   */
  public void updateMostVisitedDestAirports(HashMap<String, Integer> destAirportCounts) {
    int currDestAirportMax = 0;
    for (Map.Entry<String, Integer> entry : destAirportCounts.entrySet()) {
      if (entry.getValue() > currDestAirportMax) {
        currDestAirportMax = entry.getValue();
        mostVisitedDestAirports.clear();
        mostVisitedDestAirports.add(entry.getKey());
      } else if (entry.getValue() == currDestAirportMax) {
        currDestAirportMax = entry.getValue();
        mostVisitedDestAirports.add(entry.getKey());
      }
    }
  }

  /**
   * This method calcuates the amount of CO2 that the user can produce whilst meeting their maximum
   * yearly CO2 production goal.
   */
  public void calculateCO2ReductionNeeded() {
    this.howMuchToReduceCO2By = this.carbonEmissionGoal - this.totalCarbonEmissions;
    if (howMuchToReduceCO2By < 0) {
      throw new IllegalArgumentException();
    }
  }

  // TODO: write this method.
  public void calculateOffsetTrees(double totalCarbonEmissions) {}

  public void setCarbonEmissionsGoal(double carbonEmissionGoal) {
    this.carbonEmissionGoal = carbonEmissionGoal;
  }

  public void setAnalysisPeriod(double analysisPeriod) {
    this.analysisPeriod = analysisPeriod;
  }

  public double getTotalDistanceTravelled() {
    return totalDistanceTravelled;
  }

  public double getTotalCarbonEmissions() {
    return totalCarbonEmissions;
  }

  public ArrayList<Route> getMostEmissionsRoutes() {
    return mostEmissionsRoutes;
  }

  public ArrayList<Route> getLeastEmissionsRoutes() {
    return leastEmissionsRoutes;
  }

  public ArrayList<Route> getMostDistanceRoutes() {
    return mostDistanceRoutes;
  }

  public ArrayList<Route> getLeastDistanceRoutes() {
    return leastDistanceRoutes;
  }

  public ArrayList<String> getMostVisitedSrcAirports() {
    return mostVisitedSrcAirports;
  }

  public ArrayList<String> getMostVisitedDestAirports() {
    return mostVisitedDestAirports;
  }

  public double getCarbonEmissionGoal() {
    return carbonEmissionGoal;
  }

  public double getHowMuchToReduceCO2By() {
    return howMuchToReduceCO2By;
  }

  public double getAnalysisPeriod() {
    return analysisPeriod;
  }

  // TODO: test the implemention the sort and search in terms of updateMostTravelledRoute and
  // updateLeastTravelledRoute. 14/09/2020 HK

  /**
   * This function implements the binary search algorithm.
   *
   * @param arraryToSearch, the array which is being searched.
   * @param searchElement, the element that is being search for.
   * @return
   */
  public static int binarySearch(List<Route> arraryToSearch, int searchElement) {
    int firstIndex = 0;
    int lastIndex = arraryToSearch.size() - 1;
    while (firstIndex <= lastIndex) {
      int middleIndex = (firstIndex + lastIndex) / 2;
      if (arraryToSearch.get(middleIndex).getTimesTake() == searchElement) {
        return middleIndex;
      } else if (arraryToSearch.get(middleIndex).getTimesTake() < searchElement)
        firstIndex = middleIndex + 1;
      else if (arraryToSearch.get(middleIndex).getTimesTake() > searchElement)
        lastIndex = middleIndex - 1;
    }
    return -1;
  }

  /**
   * This method sets up the quick sort algorithm.
   *
   * @param arraytoSort , the array which needs to be sorted.
   * @param start , the starting index of the arrayToSort.
   * @param end , the ending index of the arrayToSort.
   */
  public static void quickSort(List<Route> arraytoSort, int start, int end) {
    if (end <= start) return;
    int pivot = quickSortPartition(arraytoSort, start, end);
    quickSort(arraytoSort, start, pivot - 1);
    quickSort(arraytoSort, pivot + 1, end);
  }

  /**
   * This function implements the main logic of the quick sort algoirthm.
   *
   * @param arraytoSort , the array which needs to be sorted.
   * @param start , the starting index of the arrayToSort.
   * @param end , the ending index of the arrayToSort.
   * @return
   */
  static int quickSortPartition(List<Route> arraytoSort, int start, int end) {
    int pivot = end;
    int counter = start;
    for (int i = start; i < end; i++) {
      if (arraytoSort.get(i).getTimesTake() < arraytoSort.get(pivot).getTimesTake()) {
        Route temp = arraytoSort.get(counter);
        arraytoSort.set(counter, arraytoSort.get(i));
        arraytoSort.set(i, temp);
        counter++;
      }
    }
    Route temp = arraytoSort.get(pivot);
    arraytoSort.set(pivot, arraytoSort.get(counter));
    arraytoSort.set(counter, temp);
    return counter;
  }

  /**
   * This method sets the total carbon emissions to the provided totalCarbonEmissions double.
   *
   * @param totalCarbonEmissions , the total distance travelled by flight travel.
   */
  public void setTotalCarbonEmissions(double totalCarbonEmissions) {
    this.totalCarbonEmissions = totalCarbonEmissions;
  }

  /**
   * This method sets the total carbon emissions to the provided totalDistanceTravelled double.
   *
   * @param totalDistanceTravelled , the total distance travelled by flight travel.
   */
  public void setTotalDistanceTravelled(double totalDistanceTravelled) {
    this.totalDistanceTravelled = totalDistanceTravelled;
  }
}

package controller.analysis;

import model.data.Route;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Class for which contains the methods for calcuting and updating the analysis data for the user's carbon emissions
 * report.
 * @author Hayley Krippner
 * @since 13/09/20
 * @version 1.0
 */
public class ReportGenerator {

    private double totalDistanceTravelled;   // km

    private double totalCarbonEmissions;  // grams

    private ArrayList<Route> mostEmissionsRoutes;

    private ArrayList<Route> leastEmissionsRoutes;

    private ArrayList<Route> mostDistanceRoutes;

    private ArrayList<Route> leastDistanceRoutes;

    private ArrayList<String> mostVisitedSrcAirports;

    private ArrayList<String> mostVisitedDestAirports;

    private ArrayList<Route>  mostTravelledRoutes;

    private ArrayList<Route>  leastTravelledRoutes;

    private double carbonEmissionGoal; // grams

    private double howMuchToReduceCO2By;

    private double analysisPeriod;

    private double treesToGrow;

    /**
     * This method updates the total carbon emissions from flight travel.
     * @param currentRouteRecord, the current route record that is being added to user's flight history.
     */
    public void updateTotalEmissions(Route currentRouteRecord) {
        totalCarbonEmissions += (currentRouteRecord.getEmissions() * currentRouteRecord.getTimesTake());
    }

    /**
     * This method updates the total distance travelled via flight travel.
     * @param currentRouteRecord, the current route record that is being added to user's flight history.
     */
    public void updateTotalDistance(Route currentRouteRecord) {
        totalDistanceTravelled += (currentRouteRecord.getDistance());
    }

    //TODO: write this method.
    /**
     * This method updates the current most travelled flight route.
     * @param routeHistoryEntries, the current route record that is being added to user's flight history.
     */
    public void updateMostTravelledRoute(ArrayList<Route> routeHistoryEntries) {
        int currMaxRouteUsed = 0;
        for (Route route : routeHistoryEntries) {
            if (route.getTimesTake() > currMaxRouteUsed) {
                mostTravelledRoutes.clear();
                mostTravelledRoutes.add(route);
            } else if (route.getTimesTake() == currMaxRouteUsed) {
                mostTravelledRoutes.add(route);
            }
        }
    }

    //TODO: write this method.
    /**
     * This method updates the current least travelled flight route, provided the route is already in the flight history.
     * @param routeHistoryEntries, the current route record that is being added to user's flight history.
     */
    public void updateLeastTravelledRoute(ArrayList<Route> routeHistoryEntries) {
        int currMaxRouteUsed = 0;
        for (Route route : routeHistoryEntries) {
            if (route.getTimesTake() > currMaxRouteUsed) {
                leastTravelledRoutes.clear();
                leastTravelledRoutes.add(route);
            } else if (route.getTimesTake() == currMaxRouteUsed) {
                leastTravelledRoutes.add(route);
            }
        }
    }

    /**
     * This method updates the which route produces the most emissions.
     * @param currentRouteRecord
     */
    public void updateMostEmissionsRoute(Route currentRouteRecord) {
        if (mostDistanceRoutes.isEmpty() == true) {
            mostDistanceRoutes.add(currentRouteRecord);
        } else if (currentRouteRecord.getEmissions() > mostDistanceRoutes.get(0).getEmissions()) {
            mostDistanceRoutes.clear();
            mostDistanceRoutes.add(currentRouteRecord);
        } else if (currentRouteRecord.getEmissions() == mostDistanceRoutes.get(0).getEmissions()) {
            mostDistanceRoutes.add(currentRouteRecord);
        }
    }

    /**
     * This method updates the which route produces the least emissions.
     * The currentRouteRecord is checked against the current route with the least emissions so far.
     * @param currentRouteRecord
     */
    public void updateLeastEmissionsRoute(Route currentRouteRecord) {
        if (mostDistanceRoutes.isEmpty() == true) {
            mostDistanceRoutes.add(currentRouteRecord);
        } else if (currentRouteRecord.getEmissions() < mostDistanceRoutes.get(0).getEmissions()) {
            mostDistanceRoutes.clear();
            mostDistanceRoutes.add(currentRouteRecord);
        } else if (currentRouteRecord.getEmissions() == mostDistanceRoutes.get(0).getEmissions()) {
            mostDistanceRoutes.add(currentRouteRecord);
        }
    }

    /**
     * This method updates which route is of the greatest distance.
     * The currentRouteRecord is checked against the current route with the most emissions so far.
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
     * This method updates which route is of the least distance.
     * The currentRouteRecord is checked against the current route with the least emissions so far.
     * @param currentRouteRecord
     */
    public void updateLeastDistanceRoute(Route currentRouteRecord) {
        if (mostDistanceRoutes.isEmpty() == true) {
            mostDistanceRoutes.add(currentRouteRecord);
        } else if (currentRouteRecord.getDistance() < mostDistanceRoutes.get(0).getDistance()) {
            mostDistanceRoutes.clear();
            mostDistanceRoutes.add(currentRouteRecord);
        } else if (currentRouteRecord.getDistance() == mostDistanceRoutes.get(0).getDistance()) {
            mostDistanceRoutes.add(currentRouteRecord);
        }
    }

  // TODO: write a single method for MostVisitedSrcAirports and MostVisitedDestAirports

  /**
   * Calculates the airport(s) that was the most visited, based on the user's flight history entries.
   * @return mostVisitedSrcAirports, an ArrayList containing the String of the name of the airports
   *     that were most visited, based on the user's flight history entries.
   */
  public void MostVisitedSrcAirports(HashMap<String, Integer> srcAirportCounts) {
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
 * Calculates the airport(s) that was the least visited, based on the user's flight history entries.
 * @return mostVisitedSrcAirports, an ArrayList containing the String of the name of the airports
 *     that were most visited, based on the user's flight history entries.
 */
  public void MostVisitedDestAirports(HashMap<String, Integer> destAirportCounts) {
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
     * This method calcuates the amount of CO2 that the user can produce whilst meeting their maximum yearly CO2
     * production goal.
     */
    public void calculateCO2ReductionNeeded() {
        this.howMuchToReduceCO2By = this.carbonEmissionGoal - this.totalCarbonEmissions;
        if(howMuchToReduceCO2By < 0) {
            throw new IllegalArgumentException();
        }
    }

    //TODO: write this method.
    public void calculateOffsetTrees(double totalCarbonEmissions) {


    }

    public void setCarbonEmissionsGoal(double carbonEmissionGoal) {
        this.carbonEmissionGoal = carbonEmissionGoal;
    }

    public void setAnalysisPeriod(double analysisPeriod) {
        this.analysisPeriod = analysisPeriod;
    }

    public double getTotalDistanceTravelled () {
        return totalDistanceTravelled;
    }

    public double getTotalCarbonEmissions() {
        return totalCarbonEmissions;
    }

    public ArrayList<Route> getMostEmissionsRoute() {
        return mostEmissionsRoutes;
    }

    public ArrayList<Route> getLeastEmissionsRoute() {
        return leastEmissionsRoutes;
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

    /**
     * This function implements the binary search algorithm.
     * @param arraryToSearch, the array which is being searched.
     * @param searchElement, the element that is being search for.
     * @return
     */
    public static int binarySearch(int arraryToSearch[], int searchElement) {
        int firstIndex = 0;
        int lastIndex = arraryToSearch.length - 1;
        while(firstIndex <= lastIndex) {
            int middleIndex = (firstIndex + lastIndex) / 2;
            if (arraryToSearch[middleIndex] == searchElement) {
                return middleIndex;
            }
            else if (arraryToSearch[middleIndex] < searchElement)
                firstIndex = middleIndex + 1;
            else if (arraryToSearch[middleIndex] > searchElement)
                lastIndex = middleIndex - 1;
        }
        return -1;
    }

    /**
     * This method sets up the quick sort algorithm.
     * @param arraytoSort , the array which needs to be sorted.
     * @param start , the starting index of the arrayToSort.
     * @param end , the ending index of the arrayToSort.
     */
    public static void quickSort(int[] arraytoSort, int start, int end) {
        if (end <= start) return;
        int pivot = quickSortPartition(arraytoSort, start, end);
        quickSort(arraytoSort, start, pivot-1);
        quickSort(arraytoSort, pivot+1, end);
    }

    /**
     * This function implements the main logic of the quick sort algoirthm.
     * @param arraytoSort , the array which needs to be sorted.
     * @param start , the starting index of the arrayToSort.
     * @param end , the ending index of the arrayToSort.
     * @return
     */
    static int quickSortPartition(int[] arraytoSort, int start, int end) {
        int pivot = end;
        int counter = start;
        for (int i = start; i < end; i++) {
            if (arraytoSort[i] < arraytoSort[pivot]) {
                int temp = arraytoSort[counter];
                arraytoSort[counter] = arraytoSort[i];
                arraytoSort[i] = temp;
                counter++;
            }
        }
        int temp = arraytoSort[pivot];
        arraytoSort[pivot] = arraytoSort[counter];
        arraytoSort[counter] = temp;
        return counter;
    }

    /**
     * This method sets the total carbon emissions to the provided totalCarbonEmissions double.
     * @param totalCarbonEmissions , the total distance travelled by flight travel.
     */
    public void setTotalCarbonEmissions(double totalCarbonEmissions) {
        this.totalCarbonEmissions = totalCarbonEmissions;
    }

    /**
     * This method sets the total carbon emissions to the provided totalDistanceTravelled double.
     * @param totalDistanceTravelled , the total distance travelled by flight travel.
     */
    public void setTotalDistanceTravelled(double totalDistanceTravelled) {
        this.totalDistanceTravelled = totalDistanceTravelled;
    }

}
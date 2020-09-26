package controller.analysis;

import controller.main.Main;
import model.data.Route;
import model.data.Storage;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Class for which contains the methods for calcuting and updating the analysis data for the user's
 * carbon emissions report.
 *
 * @author Hayley Krippner
 * @since 25/09/20
 * @version 1.0
 */
public class ReportGenerator {
  /** This total distance the user has travelled via flying in km. */
  private double totalDistanceTravelled = 0.0;
  /** The total carbon emissions produced in g from the user's flight travel. */
  private double totalCarbonEmissions = 0.0;
  /** * The routes which produce the most emissions. */
  private ArrayList<Route> mostEmissionsRoutes = new ArrayList<>();
  /** * The routes which produce the least emissions. */
  private ArrayList<Route> leastEmissionsRoutes = new ArrayList<>();
  /** * The routes which are of the most distance. */
  private ArrayList<Route> mostDistanceRoutes = new ArrayList<>();
  /** * The routes which are of the least distance. */
  private ArrayList<Route> leastDistanceRoutes = new ArrayList<>();
  /** * The source airports which were the most visited. */
  private ArrayList<String> mostVisitedSrcAirports = new ArrayList<>();
  /** * The destination airports which were the most visited. */
  private ArrayList<String> mostVisitedDestAirports = new ArrayList<>();
  /** * The routes which were most travelled. */
  private ArrayList<String> leastVisitedSrcAirports = new ArrayList<>();
  /** * The destination airports which were the most visited. */
  private ArrayList<String> leastVisitedDestAirports = new ArrayList<>();
  /** * The routes which were most travelled. */
  private ArrayList<Route> mostTravelledRoutes = new ArrayList<>();
  /** The routes which were the least travelled. */
  private ArrayList<Route> leastTravelledRoutes = new ArrayList<>();
  /**
   * The user's carbon emission goal which is the amount of carbon emissions they want to remain
   * below within the current year, in grams.
   */
  private double carbonEmissionGoal = 0.0;
  /**
   * The amount the user needs to reduce their carbon emission production by via flight travel to
   * ensure their goal is met.
   */
  private double howMuchToReduceCO2By = 0.0;
  /** The analysis period for their current carbon emissions goal. */
  private double analysisPeriod = 0.0;
  /** The number of trees the user would need to plant to counter their current carbon emissions. */
  private double treesToGrow = 0.0;
  /**
   * The rate of emissions produced so far in the current year.
   */
  private double emissionsPerDayBaseOnCurrDate;
  /**
   * The rate of emissions produced at the year in total if the user continues at their current rate.
   */
  private double emissionsPerYear;
  /**
   * The amount of carbon emissions the user can produce whilst still achieving their carbon emissions goal.
   */
  private double remainingCO2InYear;
  /**
   *
   */
  public double reductionPercentage;
  /**
   * This method creates the comment of the user's carbon emission status in terms of their goal.
   */
  public String carbonEmissionsComment;

  /**
   * This method updates the total carbon emissions from flight travel.
   *
   * @param currentRouteRecord , the current route record that is being added to user's flight
   *     history.
   */
  public void updateTotalEmissions(Route currentRouteRecord) {
    totalCarbonEmissions += (currentRouteRecord.getEmissions() * currentRouteRecord.getTimesTaken());

  }

  /**
   * This method updates the total distance travelled via flight travel.
   *
   * @param currentRouteRecord , the current route record that is being added to user's flight
   *     history.
   */
  public void updateTotalDistance(Route currentRouteRecord) {
    //TODO remove later!
    System.out.println(currentRouteRecord.getTimesTaken());
    totalDistanceTravelled += (currentRouteRecord.getDistance() * currentRouteRecord.getTimesTaken());

  }

  /**
   * This method updates the current most travelled flight route.
   *
   * @param routeHistoryEntries , the current route record that is being added to user's flight
   *     history.
   */
  public void updateMostTravelledRoute(List<Route> routeHistoryEntries) {
    if (routeHistoryEntries.size() >= 1) {
      if (routeHistoryEntries.size() == 1) {
        mostTravelledRoutes.add(routeHistoryEntries.get(0));
      } else {
        quickSort(routeHistoryEntries, 0, routeHistoryEntries.size() - 1);
        int maxRouteCounter =
            routeHistoryEntries.get(routeHistoryEntries.size() - 1).getTimesTaken();
        int firstOccuranceIndex = binarySearch(routeHistoryEntries, maxRouteCounter);
        for (int i = firstOccuranceIndex; i < routeHistoryEntries.size(); i++) {
          mostTravelledRoutes.add(routeHistoryEntries.get(i));
        }
      }
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
    if (routeHistoryEntries.size() >= 1) {
      if (routeHistoryEntries.size() == 1) {
        leastTravelledRoutes.add(routeHistoryEntries.get(0));
      } else {
        quickSort(routeHistoryEntries, 0, routeHistoryEntries.size() - 1);
        int minRouteCounter = routeHistoryEntries.get(0).getTimesTaken();
        int firstOccuranceIndex = binarySearch(routeHistoryEntries, minRouteCounter);
        for (int i = 0; i < firstOccuranceIndex + 1; i++) {
          leastTravelledRoutes.add(routeHistoryEntries.get(i));
        }
      }
    }
  }

  /**
   * This method updates the which route produces the most emissions.
   *
   * @param currentRouteRecord
   */
  public void updateMostEmissionsRoute(Route currentRouteRecord) {
    if (currentRouteRecord.getEmissions() > 0.0) {
      if (this.mostEmissionsRoutes.size() == 0) {
        mostEmissionsRoutes.add(currentRouteRecord);
      } else if (currentRouteRecord.getEmissions() > mostEmissionsRoutes.get(0).getEmissions()) {
        mostEmissionsRoutes.clear();
        mostEmissionsRoutes.add(currentRouteRecord);
      } else if (currentRouteRecord.getEmissions() == mostEmissionsRoutes.get(0).getEmissions()) {
        Boolean found = false;
        for (Route route : mostEmissionsRoutes) {
          if (route.getAirlineID() == currentRouteRecord.getAirlineID()) {
            found = true;
            break;
          }
        }
        if (!found) {
          mostEmissionsRoutes.add(currentRouteRecord);
        }
      }
    }
  }

  /**
   * This method updates the which route produces the least emissions. The currentRouteRecord is
   * checked against the current route with the least emissions so far.
   *
   * @param currentRouteRecord
   */
  public void updateLeastEmissionsRoute(Route currentRouteRecord) {
    if (currentRouteRecord.getEmissions() > 0.0) {
      if (leastEmissionsRoutes.size() == 0) {
        leastEmissionsRoutes.add(currentRouteRecord);
      } else if (currentRouteRecord.getEmissions() < leastEmissionsRoutes.get(0).getEmissions()) {
        leastEmissionsRoutes.clear();
        leastEmissionsRoutes.add(currentRouteRecord);
      } else if (currentRouteRecord.getEmissions() == leastEmissionsRoutes.get(0).getEmissions()) {
        Boolean found = false;
        for (Route route : leastEmissionsRoutes) {
          if (route.getAirlineID() == currentRouteRecord.getAirlineID()) {
            found = true;
            break;
          }
        }
        if (!found) {
          leastEmissionsRoutes.add(currentRouteRecord);
        }
      }
    }
  }

  /**
   * This method updates which route is of the greatest distance. The currentRouteRecord is checked
   * against the current route with the most emissions so far.
   *
   * @param currentRouteRecord
   */
  public void updateMostDistanceRoute(Route currentRouteRecord) {
    if (currentRouteRecord.getDistance() > 0.0) {
      if (mostDistanceRoutes.isEmpty() == true) {
        mostDistanceRoutes.add(currentRouteRecord);
      } else if (currentRouteRecord.getDistance() > mostDistanceRoutes.get(0).getDistance()) {
        mostDistanceRoutes.clear();
        mostDistanceRoutes.add(currentRouteRecord);
      } else if (currentRouteRecord.getDistance() == mostDistanceRoutes.get(0).getDistance()) {
        Boolean found = false;
        for (Route route : mostDistanceRoutes) {
          if (route.getAirlineID() == currentRouteRecord.getAirlineID()) {
            found = true;
            break;
          }
        }
        if (!found) {
          mostDistanceRoutes.add(currentRouteRecord);
        }
      }
    }
  }

  /**
   * This method updates which route is of the least distance. The currentRouteRecord is checked
   * against the current route with the least emissions so far.
   *
   * @param currentRouteRecord
   */
  public void updateLeastDistanceRoute(Route currentRouteRecord) {
    if (currentRouteRecord.getDistance() > 0.0) {

      if (leastDistanceRoutes.isEmpty() == true) {
        leastDistanceRoutes.add(currentRouteRecord);
      } else if (currentRouteRecord.getDistance() < leastDistanceRoutes.get(0).getDistance()) {
        leastDistanceRoutes.clear();
        leastDistanceRoutes.add(currentRouteRecord);
      } else if (currentRouteRecord.getDistance() == leastDistanceRoutes.get(0).getDistance()) {
        Boolean found = false;
        for (Route route : leastDistanceRoutes) {
          if (route.getAirlineID() == currentRouteRecord.getAirlineID()) {
            found = true;
            break;
          }
        }
        if (!found) {
          leastDistanceRoutes.add(currentRouteRecord);
        }
      }
    }
  }

  /**
   * Calculates the source airport(s) that was the most visited, based on the user's flight history
   * entries.
   * @param srcAirportCounts A count of the number of times each source airport has been visited with the
   *                         name of the aiport as the key.
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
   * Calculates the destionation airport(s) that was the most visited, based on the user's flight
   * history entries.
   *
   * @param destAirportCounts A count of how many times each destination airport has been visited
   *                          with the names as the key.
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
   * Calculates the source airport(s) that was the least visited, based on the user's flight history
   * entries.
   *
   * @param srcAirportCounts A count of how many times each airport has been visited with the name of the
   *                         airport as the key.
   */
  public void updateLeastVisitedSrcAirports(HashMap<String, Integer> srcAirportCounts) {
    int currSrcAirportMax = 1;
    for (Map.Entry<String, Integer> entry : srcAirportCounts.entrySet()) {
      if (entry.getValue() < currSrcAirportMax) {
        currSrcAirportMax = entry.getValue();
        leastVisitedSrcAirports.clear();
        leastVisitedSrcAirports.add(entry.getKey());
      } else if (entry.getValue() == currSrcAirportMax) {
        currSrcAirportMax = entry.getValue();
        leastVisitedSrcAirports.add(entry.getKey());
      }
    }
  }

  /**
   * Calculates the destination airport(s) that was the least visited, based on the user's flight
   * history entries.
   *
   * @param destAirportCounts A count of how many times each destination airport has been visited with the
   *                          name of the airport as the key.
   */
  public void updateLeastVisitedDestAirports(HashMap<String, Integer> destAirportCounts) {
    int currDestAirportMax = 1;
    for (Map.Entry<String, Integer> entry : destAirportCounts.entrySet()) {
      if (entry.getValue() < currDestAirportMax) {
        currDestAirportMax = entry.getValue();
        leastVisitedDestAirports.clear();
        leastVisitedDestAirports.add(entry.getKey());
      } else if (entry.getValue() == currDestAirportMax) {
        currDestAirportMax = entry.getValue();
        leastVisitedDestAirports.add(entry.getKey());
      }
    }
  }

  /**
   * This method determines the amount of emissions per year based on the current rate of carbon
   * emissions produced at the current time of the year.
   */
  public void calculateEmissionsPerYear() {
    Date currDayinCurrYear = new Date();
    SimpleDateFormat dateForm = new SimpleDateFormat("D");
    String dayAsString = dateForm.format(currDayinCurrYear);
    Integer dayAsInt = Integer.valueOf(dayAsString);
    this.remainingCO2InYear = 365 - dayAsInt;
    this.emissionsPerDayBaseOnCurrDate = getTotalCarbonEmissions()/dayAsInt;
    this.emissionsPerYear = emissionsPerDayBaseOnCurrDate * 365;
  }

  //TODO implement this!
  /**
   * This method calculates the carbon emissions production reduction percentage required to meet the user's goal
   * by the end of the year.
   */
  public void calculateReductionPercentage() {

  }

  /**
   * This method creates the comment of the user's carbon emission status in terms of their goal.
   */
  public void createCarbonEmissionsComment() {
    calculateRemainingCO2InYear();
    this.carbonEmissionsComment = "Currently, in " + getCurrentYear() + ", you are producing " + getEmissionsPerDayBaseOnCurrDate() + " kg of carbon emissions per day." +
            "If you continue at this rate, you will produce " + getEmissionsPerYear() + "by the end of this year. This means you can only produce " +
            getRemainingCO2InYear() + " this year. To do so, you will need to reduce your flight travel by " + getReductionPercentage() + ".";
  //TODO remove later!
    System.out.println(this.carbonEmissionsComment);

  }

  /**
   * This method gets the current year.
   * @return The current year as an integer.
   */
  public int getCurrentYear() {
    Date currDayinCurrYear = new Date();
    SimpleDateFormat dateForm = new SimpleDateFormat("Y");
    String yearAsString = dateForm.format(currDayinCurrYear);
    Integer yearAsInt = Integer.valueOf(yearAsString);
    return yearAsInt;
  }

  /**
   * This method calcuates the amount of CO2 that the user can produce whilst meeting their maximum
   * yearly CO2 production goal.
   */
  public void calculateRemainingCO2InYear() {
    this.howMuchToReduceCO2By = this.carbonEmissionGoal - this.totalCarbonEmissions;
    if (howMuchToReduceCO2By < 0) {
      throw new IllegalArgumentException();
    }
  }

  /**
   * This method calculates how many trees need to be planted to counter the carbon emissions
   * produced. It assumes that the trees planted have an approximate age of standing of at least 20 years and
   * and that the age of stand when measured is also at least 20 years. Note that trees do not sequester much carbon in
   * the first few years after planting so the minimum standing age must be no less than 20.
   */
  public void calculateOffsetTrees() {
    double CO2Tonnes = this.totalCarbonEmissions / 1000; //convert carbon emissions from grams to tonnes
    this.treesToGrow = (CO2Tonnes / 144.64) * 2500; //determine number of trees to offset emissions
  }

  /**
   * This methods updates all the most and least travelled route(s) arrays and visited airport(s) arrays at once.
   */
  public void updateTravelledAndVisited() {
    Storage storage = Main.getStorage();
    this.updateLeastTravelledRoute(storage.getHistory());
    this.updateMostTravelledRoute(storage.getHistory());
    this.updateMostVisitedSrcAirports(storage.getHistorySrcAirports());
    this.updateLeastVisitedSrcAirports(storage.getHistorySrcAirports());
    this.updateMostVisitedDestAirports(storage.getHistoryDestAirports());
    this.updateLeastVisitedDestAirports(storage.getHistoryDestAirports());
  }

  /**
   * This function implements the binary search algorithm.
   *
   * @param arraryToSearch The array which is being searched.
   * @param searchElement The element that is being search for.
   * @return
   */
  public static int binarySearch(List<Route> arraryToSearch, int searchElement) {
    int firstIndex = 0;
    int lastIndex = arraryToSearch.size() - 1;
    while (firstIndex <= lastIndex) {
      int middleIndex = (firstIndex + lastIndex) / 2;
      if (arraryToSearch.get(middleIndex).getTimesTaken() == searchElement) {
        return middleIndex;
      } else if (arraryToSearch.get(middleIndex).getTimesTaken() < searchElement)
        firstIndex = middleIndex + 1;
      else if (arraryToSearch.get(middleIndex).getTimesTaken() > searchElement)
        lastIndex = middleIndex - 1;
    }
    return -1;
  }

  /**
   * This method sets up the quick sort algorithm.
   *
   * @param arraytoSort The array which needs to be sorted.
   * @param start The starting index of the arrayToSort.
   * @param end The ending index of the arrayToSort.
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
   * @param arraytoSort The array which needs to be sorted.
   * @param start The starting index of the arrayToSort.
   * @param end The ending index of the arrayToSort.
   * @return
   */
  static int quickSortPartition(List<Route> arraytoSort, int start, int end) {
    int pivot = end;
    int counter = start;
    for (int i = start; i < end; i++) {
      if (arraytoSort.get(i).getTimesTaken() < arraytoSort.get(pivot).getTimesTaken()) {
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

  // TODO: possibly remove HK 25/09/2020

  public void resetReportGenerator() {
    mostTravelledRoutes.clear();
    leastTravelledRoutes.clear();
    mostEmissionsRoutes.clear();
    leastEmissionsRoutes.clear();
    mostDistanceRoutes.clear();
    leastDistanceRoutes.clear();
    mostVisitedDestAirports.clear();
    mostVisitedSrcAirports.clear();
    leastVisitedSrcAirports.clear();
    leastVisitedDestAirports.clear();
  }

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

  public ArrayList<String> getLeastVisitedSrcAirports() {
    return leastVisitedSrcAirports;
  }

  public ArrayList<String> getLeastVisitedDestAirports() {
    return leastVisitedDestAirports;
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

  public double getTreesToGrow() {
    return this.treesToGrow;
  }

  public double getEmissionsPerDayBaseOnCurrDate() {
    return this.emissionsPerDayBaseOnCurrDate;
  }

  public double getEmissionsPerYear() {
    return this.emissionsPerYear;
  }

  public double getRemainingCO2InYear() {
    return this.remainingCO2InYear;
  }

  public double getReductionPercentage() {
    return this.reductionPercentage;
  }

  public void setTotalCarbonEmissions(double totalCarbonEmissions) {
    this.totalCarbonEmissions = totalCarbonEmissions;
  }

  public void setTotalDistanceTravelled(double totalDistanceTravelled) {
    this.totalDistanceTravelled = totalDistanceTravelled;
  }

  public ArrayList<Route> getMostTravelledRoute() {
    return mostTravelledRoutes;
  }

  public ArrayList<Route> getLeastTravelledRoute() {
    return leastTravelledRoutes;
  }

  public String getCarbonEmissionsComment() {
    return carbonEmissionsComment;
  }

}
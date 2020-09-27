package controller.analysis;

import model.data.Route;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * This class contains the methods for calcuting and updating the analysis data for the user's
 * carbon emissions report.
 *
 * @author Hayley Krippner
 * @since 26/09/20
 * @version 1.0
 */
public class GeneralStatsCalculator {
  /** This total distance the user has travelled via flying in km. */
  private double totalDistanceTravelled = 0.0;
  /** The total carbon emissions produced in g from the user's flight travel. */
  private double totalCarbonEmissions = 0.0;

  /**
   * The user's carbon emission goal which is the amount of carbon emissions they want to remain
   * below within the current year, in grams.
   */
  private double carbonEmissionGoal = 0.0;
  /** The rate of emissions produced so far in the current year. */
  private double emissionsPerDayBaseOnCurrDate;
  /**
   * The rate of emissions produced at the year in total if the user continues at their current
   * rate.
   */
  private double emissionsPerYear;
  /**
   * The amount of carbon emissions the user can produce whilst still achieving their carbon
   * emissions goal.
   */
  private double remainingCO2InYear;
  /** The percentage that the user needs to reduce their flight travel by to meet their goal. */
  public double reductionPercentage;
  /**
   * The amount the user needs to reduce their carbon emission production by via flight travel to
   * ensure their goal is met.
   */
  private double howMuchToReduceCO2By = 0.0;
  /** The number of trees the user would need to plant to counter their current carbon emissions. */
  private double treesToGrow = 0.0;
  /**
   * This method creates the comment of the user's carbon emission status in terms of their goal.
   */
  private String carbonEmissionsComment;
  /** The remaining days in the year. */
  private int remainingDaysInYear;
  /**
   * The current day in the year.
   */
  private Integer dayInYear;

  /**
   * This method updates the total carbon emissions from flight travel.
   *
   * @param currentRouteRecord The current route record that is being added to user's flight
   *     history.
   */
  public void updateTotalEmissions(Route currentRouteRecord) {
    totalCarbonEmissions +=
        (currentRouteRecord.getEmissions() * currentRouteRecord.getTimesTaken());
  }

  /**
   * This method updates the total distance travelled via flight travel.
   *
   * @param currentRouteRecord The current route record that is being added to user's flight
   *     history.
   */
  public void updateTotalDistance(Route currentRouteRecord) {
    totalDistanceTravelled +=
        (currentRouteRecord.getDistance() * currentRouteRecord.getTimesTaken());
  }

  /** This method calculates the current day of the year and returns the integer of it. */
  public void calculateDateAsInt() {
    Date currDayinCurrYear = new Date();
    SimpleDateFormat dateForm = new SimpleDateFormat("D");
    String dayAsString = dateForm.format(currDayinCurrYear);
    Integer dayAsInt = Integer.valueOf(dayAsString);
    this.dayInYear = dayAsInt;
  }

  /** This method determines how many remaining days in the year there are. */
  public void calculateRemainingDaysInYear() {
    try {
      this.remainingDaysInYear = 365 - this.dayInYear;
      if (this.remainingDaysInYear < 0) {
        throw new Exception("It is not possible to have a negative amount of days in the year.");
        }
    } catch (Exception e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  /**
   * This method determines the amount of emissions per year based on the current rate of carbon
   * emissions produced at the current time of the year.
   */
  public void calculateEmissionsPerYear() {
    try {
      this.emissionsPerDayBaseOnCurrDate = getTotalCarbonEmissions() / this.dayInYear;
    } catch (ArithmeticException e) {
      System.out.println("Cannot divide the total amount of carbon emissions by zero days " + e);
    }
    this.emissionsPerYear = emissionsPerDayBaseOnCurrDate * 365;
  }

  // TODO implement this!
  /**
   * This method calculates the carbon emissions production reduction percentage required to meet
   * the user's goal by the end of the year.
   */
  public void calculateReductionPercentage() {}

  /**
   * This method gets the current year.
   *
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
    this.remainingCO2InYear = this.carbonEmissionGoal - this.totalCarbonEmissions;
    if (remainingCO2InYear < 0) {
      this.remainingCO2InYear = 0;
    }
  }

  /**
   * This method calculates how many trees need to be planted to counter the carbon emissions
   * produced. It assumes that the trees planted have an approximate age of standing of at least 20
   * years and and that the age of stand when measured is also at least 20 years. Note that trees do
   * not sequester much carbon in the first few years after planting so the minimum standing age
   * must be no less than 20.
   */
  public void calculateOffsetTrees() {
    double CO2Tonnes =
        this.totalCarbonEmissions / 1000; // convert carbon emissions from grams to tonnes
    this.treesToGrow = (CO2Tonnes / 144.64) * 2500; // determine number of trees to offset emissions
  }

  /**
   * This method creates the comment of the user's carbon emission status in terms of their goal.
   */
  public void createCarbonEmissionsComment() {
    calculateRemainingCO2InYear();
    this.carbonEmissionsComment =
        "Currently, in "
            + getCurrentYear()
            + ", you are producing "
            + getEmissionsPerDayBaseOnCurrDate()
            + " kg of carbon emissions per day."
            + "If you continue at this rate, you will produce "
            + getEmissionsPerYear()
            + "by the end of this year. This means you can only produce "
            + getRemainingCO2InYear()
            + " this year. To do so, you will need to reduce your flight travel by "
            + getReductionPercentage()
            + ".";
    // TODO remove later!
    System.out.println(this.carbonEmissionsComment);
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

  public void setCarbonEmissionsGoal(double carbonEmissionGoal) {
    this.carbonEmissionGoal = carbonEmissionGoal;
  }

  public double getCarbonEmissionGoal() {
    return carbonEmissionGoal;
  }

  public double getTotalDistanceTravelled() {
    return totalDistanceTravelled;
  }

  public double getTotalCarbonEmissions() {
    return totalCarbonEmissions;
  }

  public void setTotalCarbonEmissions(double totalCarbonEmissions) {
    this.totalCarbonEmissions = totalCarbonEmissions;
  }

  public void setTotalDistanceTravelled(double totalDistanceTravelled) {
    this.totalDistanceTravelled = totalDistanceTravelled;
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

  // TODO write method for calculating this!
  public double getHowMuchToReduceCO2By() {
    return howMuchToReduceCO2By;
  }

  public int getRemainingDaysInYear() {
    return remainingDaysInYear;
  }

  public double getTreesToGrow() {
    return this.treesToGrow;
  }

  public String getCarbonEmissionsComment() {
    return carbonEmissionsComment;
  }

  public void setRemainingDaysInYear(int days) {
    this.remainingDaysInYear = days;
  }

  public void setDayInYear(int day) {
    this.dayInYear = day;
  }

  public int getDayInYear() {
    return dayInYear;
  }

  public void setEmissionsPerDayBaseOnCurrDate(int rate) {
    this.emissionsPerDayBaseOnCurrDate = rate;
  }
}

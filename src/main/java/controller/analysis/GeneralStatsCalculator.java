package controller.analysis;

import model.data.Route;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * This class contains the methods for calculating and updating the analysis data for the user's
 * carbon emissions report.
 *
 * @author Hayley Krippner
 * @since 28/09/20
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
  private String carbonEmissionsComment = "";
  /** The remaining days in the year. */
  private int remainingDaysInYear;
  /** The current day in the year. */
  private Integer dayInYear;
  /**
   * The maximum rate that carbon emissions can be produced by per day within the year whilst still
   * meeting the goal.
   */
  private double emissionsPerDayGoal;
  /** The current year; */
  private int currentYear;

  /**
   * This method adds the added route's carbon emissions to the total carbon emissions from flight travel.
   *
   * @param currentRouteRecord The current route record that is being added to the user's flight
   *     history.
   */
  public void updateTotalEmissions(Route currentRouteRecord) {
    if (!Double.isNaN(currentRouteRecord.getEmissions())) {
      totalCarbonEmissions +=
          (currentRouteRecord.getEmissions() * currentRouteRecord.getTimesTaken());
    }
  }

  //TODO test this method.
  /**
   * This method adds the removes route's carbon emissions from the total carbon emissions from flight travel.
   *
   * @param currentRouteRecord The current route record that is being removed for the user's flight
   *     history.
   */
  public void updateTotalEmissionsRemoval(Route currentRouteRecord) {
    if (!Double.isNaN(currentRouteRecord.getEmissions())) {
      totalCarbonEmissions -=
              (currentRouteRecord.getEmissions() * currentRouteRecord.getTimesTaken());
      if (totalCarbonEmissions < 0.00) {
        totalCarbonEmissions = 0.00;
      }
    }
  }

  /**
   * This method adds the removes route's distance the total distance travelled via flight travel.
   *
   * @param currentRouteRecord The current route record that is being removed from the user's flight
   *     history.
   */
  public void updateTotalDistance(Route currentRouteRecord) {
    totalDistanceTravelled +=
            (currentRouteRecord.getDistance() * currentRouteRecord.getTimesTaken());
  }

  //TODO test this method.
  /**
   * This method adds the added route's distance the total distance travelled via flight travel.
   *
   * @param currentRouteRecord The current route record that is being added to the user's flight
   *     history.
   */
  public void updateTotalDistanceRemoval(Route currentRouteRecord) {
    totalDistanceTravelled -=
            (currentRouteRecord.getDistance() * currentRouteRecord.getTimesTaken());
    if (totalDistanceTravelled < 0.00) {
      totalDistanceTravelled = 0.00;
    }
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
      if (this.remainingDaysInYear <= 0) {
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
  public void calculateEmissionsPerYearCurrentRate() {
    try {
      this.emissionsPerDayBaseOnCurrDate = getTotalCarbonEmissions() / this.dayInYear;
    } catch (ArithmeticException e) {
      System.out.println("Cannot divide the total amount of carbon emissions by zero days " + e);
    }
    this.emissionsPerYear = emissionsPerDayBaseOnCurrDate * 365;
  }

  /**
   * This method calculates the maximum rate that carbon emissions can be produced by per day within
   * the year whilst still meeting the goal.
   */
  public void calculateEmissionsPerYearGoalRate() {
    this.emissionsPerDayGoal = getCarbonEmissionGoal() / 365;
  }

  // TODO tests this! HK 28/09/2020 --> test exception is caught
  /**
   * This method calculates the carbon emissions production reduction percentage required to meet
   * the user's goal by the end of the year.
   */
  public void calculateReductionPercentage() {
    if (emissionsPerDayGoal > emissionsPerDayBaseOnCurrDate) {
      this.reductionPercentage = 0;
    } else {
      try {
        double negReductionDec = 1 - (emissionsPerDayBaseOnCurrDate / emissionsPerDayGoal);
        double posReductionPercentage = 100 - (negReductionDec * -100);
        this.reductionPercentage = posReductionPercentage;
      } catch (ArithmeticException e) {
        System.out.println(
            "Cannot divide the emissionsPerDayBaseOnCurrDate by a rate of zero carbon emissions per day "
                + e);
      }
    }
  }

  /**
   * This method gets the current year.
   *
   * @return The current year as an integer.
   */
  public int getCurrentYear() {
    Date currDayInCurrYear = new Date();
    SimpleDateFormat dateForm = new SimpleDateFormat("Y");
    String yearAsString = dateForm.format(currDayInCurrYear);
    Integer yearAsInt = Integer.valueOf(yearAsString);
    return yearAsInt;
  }

  /**
   * This method calculates the amount of CO2 that the user can produce whilst meeting their maximum
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
    calculateDateAsInt();
    calculateRemainingDaysInYear();
    calculateEmissionsPerYearCurrentRate();
    calculateEmissionsPerYearGoalRate();
    calculateReductionPercentage();
    calculateRemainingCO2InYear();
    calculateOffsetTrees();

    String carbonEmissionsComment =
        "Currently, in "
            + getCurrentYear()
            + ", you are producing "
            + String.format("%.2f", getEmissionsPerDayBaseOnCurrDate())
            + " kg of carbon emissions per day from your \nflight travel."
            + " If you continue at this rate, you will produce "
            + String.format("%.2f", getEmissionsPerYear())
            + " kg by the end of this year \nfrom flight travel. ";

    if (getReductionPercentage() == 0.00) {
      carbonEmissionsComment += "This means that you will be below your carbon emissions goal.";
    } else if (getRemainingCO2InYear() == 0.00) {
      carbonEmissionsComment +=
          "This means you have breached your goal and should not produce any \nmore carbon emissions"
              + " in the remaining part of this year. To ensure you stay under\n your goal in "
              + (getCurrentYear() + 1)
              + ", you will need to reduce your flight travel by "
              + String.format("%.2f", (-1 * getReductionPercentage()))
              + " percent.";

    } else {
      carbonEmissionsComment +=
          "This means you can only produce "
              + String.format("%.2f", getRemainingCO2InYear())
              + " kg in the remaining part of this year.\n To ensure you stay under your goal, you will need to reduce your flight travel by "
              + String.format("%.2f", getReductionPercentage())
              + " percent.";
    }
    setCarbonEmissionsComment(carbonEmissionsComment);
  }

  /**
   * This function implements the binary search algorithm.
   *
   * @param arrayToSearch The array which is being searched.
   * @param searchElement The element that is being search for.
   * @return
   */
  public static int binarySearch(List<Route> arrayToSearch, int searchElement) {
    int firstIndex = 0;
    int lastIndex = arrayToSearch.size() - 1;
    while (firstIndex <= lastIndex) {
      int middleIndex = (firstIndex + lastIndex) / 2;
      if (arrayToSearch.get(middleIndex).getTimesTaken() == searchElement) {
        return middleIndex;
      } else if (arrayToSearch.get(middleIndex).getTimesTaken() < searchElement)
        firstIndex = middleIndex + 1;
      else if (arrayToSearch.get(middleIndex).getTimesTaken() > searchElement)
        lastIndex = middleIndex - 1;
    }
    return -1;
  }

  /**
   * This method sets up the quick sort algorithm.
   *
   * @param arrayToSort The array which needs to be sorted.
   * @param start The starting index of the arrayToSort.
   * @param end The ending index of the arrayToSort.
   */
  public static void quickSort(List<Route> arrayToSort, int start, int end) {
    if (end <= start) return;
    int pivot = quickSortPartition(arrayToSort, start, end);
    quickSort(arrayToSort, start, pivot - 1);
    quickSort(arrayToSort, pivot + 1, end);
  }

  /**
   * This function implements the main logic of the quick sort algoirthm.
   *
   * @param arrayToSort The array which needs to be sorted.
   * @param start The starting index of the arrayToSort.
   * @param end The ending index of the arrayToSort.
   * @return
   */
  public static int quickSortPartition(List<Route> arrayToSort, int start, int end) {
    int pivot = end;
    int counter = start;
    for (int i = start; i < end; i++) {
      if (arrayToSort.get(i).getTimesTaken() < arrayToSort.get(pivot).getTimesTaken()) {
        Route temp = arrayToSort.get(counter);
        arrayToSort.set(counter, arrayToSort.get(i));
        arrayToSort.set(i, temp);
        counter++;
      }
    }
    Route temp = arrayToSort.get(pivot);
    arrayToSort.set(pivot, arrayToSort.get(counter));
    arrayToSort.set(counter, temp);
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

  public int getRemainingDaysInYear() {
    return remainingDaysInYear;
  }

  public double getTreesToGrow() {
    return this.treesToGrow;
  }

  public String getCarbonEmissionsComment() {
    return carbonEmissionsComment;
  }

  public void setCarbonEmissionsComment(String comment) {
    this.carbonEmissionsComment = comment;
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

  public double getEmissionsPerDayGoal() {
    return this.emissionsPerDayGoal;
  }

  public void setEmissionsPerDayGoal(int rate) {
    this.emissionsPerDayGoal = rate;
  }

  public void setCurrentYear(int year) {
    this.currentYear = year;
  }

  public void setEmissionsPerYear(double rate) {
    this.emissionsPerYear = rate;
  }

  public void setRemainingCO2InYear(int amount) {
    this.remainingCO2InYear = amount;
  }

  public void setReductionPercentage(int percentage) {
    this.reductionPercentage = percentage;
  }

}

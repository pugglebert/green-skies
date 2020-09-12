package controller.analysis;

import model.data.Airport;
import model.data.Route;

import java.util.ArrayList;

/**
 * Class for which contains the methods for calcuting and updating the analysis data for the user's carbon emissions report.
 * @author Hayley Krippner
 * @since 12/09/20
 * @version 1.0
 */
public class ReportGenerator {

    private double totalDistanceTravelled;   // km

    private double totalCarbonEmissions;  // grams

    private ArrayList<Route> mostEmissionsRoutes;

    private ArrayList<Route> leastEmissionsRoutes;

    private ArrayList<Route> mostDistanceRoutes;

    private ArrayList<Route> leastDistanceRoutes;

    private ArrayList<Airport> MostVisitedSrcAirports;

    private ArrayList<Airport> MostVisitedDestAirports;

    private double carbonEmissionGoal; // grams

    private double howMuchToReduceCO2By;

    private double analysisPeriod;

    private double treesToGrow;

    /**
     * This method updates the total carbon emissions from flight travel.
     * @param currentRouteRecord, the current route record that is being added to user's flight history.
     * @param timesRouteTaken, the times the route has been taken.
     */
    public void updateTotalEmissions(Route currentRouteRecord, int timesRouteTaken) {
        totalCarbonEmissions += (currentRouteRecord.getEmissions() * timesRouteTaken);
    }

    /**
     * This method updates the total distance travelled via flight travel.
     * @param currentRouteRecord, the current route record that is being added to user's flight history.
     * @param timesRouteTaken, the times the route has been taken.
     */
    public void updateTotalDistance(Route currentRouteRecord, int timesRouteTaken) {
        totalDistanceTravelled += (currentRouteRecord.getDistance() * timesRouteTaken);
    }

    //TODO: write this method.
    /**
     * This method updates the current most travelled flight route.
     * @param currentRouteRecord, the current route record that is being added to user's flight history.
     * @param timesRouteTaken, the times the route has been taken.
     */
    public void updateMostTravelledRoute(Route currentRouteRecord, int timesRouteTaken) {

    }

    //TODO: write this method.
    /**
     * This method updates the current least travelled flight route, provided the route is already in the flight history.
     * @param currentRouteRecord, the current route record that is being added to user's flight history.
     */
    public void updateLeastTravelledRoute(Route currentRouteRecord) {
//        for (Route entry : flightRouteHistory) {
//
//        }
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

    //TODO: write this method.
    public void MostVisitedSrcAirports(ArrayList<Route> flightRouteHistory) {

        }


    //TODO: write this method.
    public void MostVisitedDestAirports(ArrayList<Route> flightRouteHistory) {

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

    public ArrayList<Airport> getMostVisitedSrcAirports() {
        return MostVisitedSrcAirports;
    }

    public ArrayList<Airport> getMostVisitedDestAirports() {
        return MostVisitedDestAirports;
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

}
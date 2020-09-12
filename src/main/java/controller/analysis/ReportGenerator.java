package controller.analysis;

import model.data.Airport;
import model.data.Route;

/**
 * Class for which contains the methods for calcuting and updating the analysis data for the user's carbon emissions report.
 * @author Hayley Krippner
 * @since 12/09/20
 * @version 1.0
 */
public class ReportGenerator {

    private double totalDistanceTravelled;   // km

    private double totalCarbonEmissions;  // grams

    private Route mostEmissionsRoute;

    private Route leastEmissionsRoute;

    private Airport mostTravelledAirport;

    private double carbonEmissionGoal; // grams

    private double howMuchToReduceCO2By;

    private double analysisPeriod;

    private Route initalRoute;

    private Airport initialAirport;


    public void updateTotalEmissions() {

    }

    public void updateTotalDistance() {

    }

    public void updateMostTravelledRoute() {

    }

    public void updateLeastTravelledRoute() {


    }

    public void updateMostEmissionsRoute() {

    }

    public void updateLeastEmissionsRoute() {


    }

    public void MostVisitedAirport() {

    }

    public void MostVisitedCountry() {

    }

    public void calculateCO2ReductionNeeded() {

    }

    public void setCarbonEmissionsGoal(double carbonEmissionGoal) {
        this.carbonEmissionGoal = carbonEmissionGoal;
    }

    public void setAnalysisPeriod(double analysisPeriod) {
        this.analysisPeriod = analysisPeriod;
    }

    public void calculateOffsetTrees() {

    }

    public double getTotalDistanceTravelled () {
        return totalDistanceTravelled;
    }

    public double getTotalCarbonEmissions() {
        return totalCarbonEmissions;
    }

    public Route getMostEmissionsRoute() {
        return mostEmissionsRoute;
    }

    public Route getLeastEmissionsRoute() {
        return leastEmissionsRoute;
    }

    public Airport getMostTravelledAirport() {
        return mostTravelledAirport;
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

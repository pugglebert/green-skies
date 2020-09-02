package controller.analysis;

import model.data.Airport;
import model.data.Storage;
import model.loader.Loader;
import java.awt.*;
import java.io.FileNotFoundException;
import java.nio.file.FileSystemException;
import java.util.ArrayList;
import java.util.List;

/**
 * The FlightAnalyser class which calculate two paths' carbon emission, total distance of the path, and comparison of two
 * paths' distance and emission.
 * @author HeZhengJingRui  and  Enyang Zhang
 * @version 1.2
 * @since 2020-08-24
 */
public class FlightAnalyser {

        private final double radius = 6371e3;   //radius of earth;

        private double distance;   //must use in KM

        private double FuelUsed;

        private final int seatsOccupancy = 333;   //number of passengers;

        private final double Co2OfOneGramFuel = 3.15;  //in gram

        private final int CruisingSpeed = 910;   //km per hour

        private  ArrayList<String> path1 = new ArrayList<String>();

        private  ArrayList<String> path2 = new ArrayList<String>();

        private List<Airport> airports = new ArrayList<Airport>();

        private ArrayList<ArrayList<Double>> path1Coords = new ArrayList<>();

        private ArrayList<ArrayList<Double>> path2Coords = new ArrayList<>();

        private double totalDistancePath1 = 0;

        private double totalDistancePath2 = 0;

        private double totalEmissionPath1 = 0;

        private double totalEmissionPath2 = 0;
    // TODO: 1/09/2020 add checking route validation 
    /**
     * Constructor of FlightAnalyser which starts processing and calculation.
     * @param path1 An arraylist contains IATA or ICAO code for each airport which the flight may pass for path1.
     * @param path2 An arraylist contains IATA or ICAO code for each airport which the flight may pass for path2.
     * @param storage Storage contains all information about airports, routes, and airlines.
     */
    public FlightAnalyser(ArrayList<String> path1, ArrayList<String> path2, Storage storage) {
        this.path1 = path1;
        this.path2 = path2;
        this.airports = storage.getAirports();
        processsPath();
        calculateTotalDistance();
        calculatePathsEmission();
    }

    /**
     * Process two arraylist path1 and path2, loop through the airports data and put coordinates of each airport which
     * is contained in the path into arraylist path1coords and path2coords.
    */
    private void processsPath(){
            for(String airportCode: path1){
                for(Airport airport: airports){
                    if(airport.getIATA().equals(airportCode)){
                        ArrayList<Double> coord = new ArrayList<>();
                        coord.add(airport.getLatitude());
                        coord.add(airport.getLongitude());
                        path1Coords.add(coord);
                    }
                }
            }
        for(String airportCode: path2){
            for(Airport airport: airports){
                if(airport.getIATA().equals(airportCode)){
                    ArrayList<Double> coord = new ArrayList<>();
                    coord.add(airport.getLatitude());
                    coord.add(airport.getLongitude());
                    path2Coords.add(coord);
                }
            }
        }
    }

    /**
     * Calculate totalDistance of path1 and path2.
     */
    private void calculateTotalDistance(){
        for (int i = 0; i < path1Coords.size()-1;i++) {
            double airport1lat = path1Coords.get(i).get(0); //lat
            double airport1lon = path1Coords.get(i).get(1);
            double airport2lat = path1Coords.get(i+1).get(0); //lat
            double airport2lon = path1Coords.get(i+1).get(1);
            totalDistancePath1 += calculatedistance(airport1lat, airport1lon, airport2lat, airport2lon);
        }

        for (int i = 0; i < path2Coords.size()-1;i++) {
            double airport1lat = path2Coords.get(i).get(0); //lat
            double airport1lon = path2Coords.get(i).get(1);
            double airport2lat = path2Coords.get(i+1).get(0); //lat
            double airport2lon = path2Coords.get(i+1).get(1);
            totalDistancePath2 += calculatedistance(airport1lat, airport1lon, airport2lat, airport2lon);
        }
    }

    /**
     * Calculate distance between two airports.
     * @param Lati1 Latitude of the airport in path1.
     * @param Long1 longitude of the airport in path1.
     * @param Lati2 Latitude of the airport in path1.
     * @param Long2 longitude of the airport in path1.
     * @return returns distance between two airports in kilometres.
     */
    private double calculatedistance(double Lati1, double Long1, double Lati2, double Long2) {
        double φ1 = Lati1*Math.PI/180;
        double φ2 = Lati2*Math.PI/180;
        double Δφ = (Lati2-Lati1)*Math.PI/180;
        double Δλ = (Long2-Long1)*Math.PI/180;

        double a = Math.sin(Δφ/2) * Math.sin(Δφ/2) + Math.cos(φ1) * Math.cos(φ2) * Math.sin(Δλ/2) * Math.sin(Δλ/2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        double distance = radius * c;

        return distance/1000;   //distance in kilometers
    }

    /**
     * start calculate two paths' total carbon emission.
     */
    private void calculatePathsEmission(){
            this.totalEmissionPath1 = calculateCarbonEmission(totalDistancePath1);
            this.totalEmissionPath2 = calculateCarbonEmission(totalDistancePath2);
        }

    /**
     * Calculate carbon emission between two airports.
     * @param distance distance between two airports.
     * @return carbon emission in kilograms.
     */
    private double calculateCarbonEmission(double distance) {
        FuelUsed = distance * 12 / 1250  ;//fuel in tonns

        double FuelPerPassenger = (FuelUsed / (distance*seatsOccupancy))*1000000;   //fuel use per passenger per km

        double Co2PerPassengerPerKm = FuelPerPassenger*Co2OfOneGramFuel;  //co2 emissions per passenger km in gram

        double Co2Hour = (Co2PerPassengerPerKm*CruisingSpeed)/1000;   //how much Co2 genate per hour in kg

        double flytime = distance / CruisingSpeed;  // in hour

        double finalCo2 =  flytime*Co2Hour;

        return finalCo2;  // in kg
    }
  // TODO: 29/08/20 comfirm what need to do with compare distance and compare emission.

    /**
     * Compare distance between two paths by getting absolute number of their difference.
     * @return difference of two distances.
     */
    public double compareDistance() {
        return Math.abs(totalDistancePath1-totalDistancePath2);
    }

    /**
     * Compare emission between two paths by getting absolute number of their difference.
     * @return difference of two emissions.
     */
    public double compareEmission() {
        return Math.abs(totalEmissionPath1-totalEmissionPath2);
    }

    /**
     * Getter for path1 emission.
     * @return Total emission of path1.
     */
    public double getPath1Emission(){
        return totalEmissionPath1;
    }

    /**
     * getter for path2 emissions.
     * @return Total mission of path2.
     */
    public double getPath2Emission(){
        return totalEmissionPath2;
    }

    /**
     * Getter for path1 distance.
     * @return Total distance of path1.
     */
    public double getTotalDistancePath1(){
        return totalDistancePath1;
    }

    /**
     * Getter for path2 distance.
     * @return Total distance of path2.
     */
    public double getTotalDistancePath2(){
        return totalDistancePath2;
    }


}

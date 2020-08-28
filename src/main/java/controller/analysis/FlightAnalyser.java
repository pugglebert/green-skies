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
 * The FlightAnalyser class which has bunch of methods.
 * @author HeZhengJingRui  and  Enyang Zhang
 * @version 1.0
 * @since 2020-08-24
 */

public class FlightAnalyser {

        private double LatitudeExample1;

        private double LongitudeExample1;

        private double LatitudeExample2;

        private double LongitudeExample2;

        private final double radius = 6371e3;   //radius of earth;

        private double distance;   //must use in KM

        private double FuelUsed;

        private final int seatsOccupancy = 333;   //number of passengers;

        private final double Co2OfOneGramFuel = 3.15;  //in gram

        private final int CruisingSpeed = 910;   //km per hour

        private  ArrayList<String> path1 = new ArrayList<String>();

        private  ArrayList<String> path2 = new ArrayList<String>();

//        private Storage storage = new Storage();

        private List<Airport> airports = new ArrayList<Airport>();

        private ArrayList<ArrayList<Double>> path1Coords = new ArrayList<>();

        private ArrayList<ArrayList<Double>> path2Coords = new ArrayList<>();

        public double totalDistancePath1 = 0;

        public double totalDistancePath2 = 0;

    public FlightAnalyser(ArrayList<String> path1, ArrayList<String> path2, Storage storage) {
//            this.LatitudeExample1 = Latitude1;
//            this.LongitudeExample1 = Longitude1;
//            this.LatitudeExample2 = Latitude2;
//            this.LongitudeExample2 = Longitude2;
            this.path1 = path1;
            this.path2 = path2;
//            this.storage = storage;
            this.airports = storage.getAirports();
            proccesssPath();
            calculateTotalDistance();
        }

//        private void proccessPaths(){
//            proccesssPath(path1);
//            proccesssPath(path2);
//        }

        private void proccesssPath(){
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

//        private void startCalculateDistance(){
//            calculateTotalDistance(path1Coords);
//            calculateTotalDistance(path2Coords);
//        }


        public void calculateTotalDistance(){
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

        public double calculatedistance(double Lati1, double Long1, double Lati2, double Long2) {
            double φ1 = Lati1*Math.PI/180;
            double φ2 = Lati2*Math.PI/180;
            double Δφ = (Lati2-Lati1)*Math.PI/180;
            double Δλ = (Long2-Long1)*Math.PI/180;

            double a = Math.sin(Δφ/2) * Math.sin(Δφ/2) + Math.cos(φ1) * Math.cos(φ2) * Math.sin(Δλ/2) * Math.sin(Δλ/2);

            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

            double distance = radius * c;

            return distance/1000;   //distance in kilometers
        }

        public double campareDisdance(){
            return Math.abs(totalDistancePath1-totalDistancePath2);
       }
        //------------------------------------------------------------
        public double calculateCarbonEmission(double distance){
            FuelUsed = distance * 12 / 1250  ;//fuel in tonns

            double FuelPerPassenger = (FuelUsed / (distance*seatsOccupancy))*1000000;   //fuel use per passenger per km

            double Co2PerPassengerPerKm = FuelPerPassenger*Co2OfOneGramFuel;  //co2 emissions per passenger km in gram

            double Co2Hour = (Co2PerPassengerPerKm*CruisingSpeed)/1000;   //how much Co2 genate per hour in kg

            double flytime = distance / CruisingSpeed;  // in hour

            double finalCo2 =  flytime*Co2Hour;

            return finalCo2;  // in kg

        }



        public static void main(String[] args) throws FileNotFoundException, FileSystemException {
//            double Lati1 =40.689202777778;
//            double Long1 =-74.044219444444;
//            double Lati2 = 38.889069444444;
//            double Long2 = -77.034502777778;
//            int disatnce = 5556;
//            FlightAnalyser f1 = new FlightAnalyser(Lati1, Long1, Lati2, Long2);
//            System.out.println(f1.calculateCarbonEmission(disatnce));
            Storage storage = new Storage();
            Loader loader = new Loader(storage);
            loader.loadFile("../seng202_project/src/test/java/TestFiles/airports.csv", "Airport");
            System.out.println(storage.getAirports());
            ArrayList<String> path1 = new ArrayList<>();
            ArrayList<String> path2 = new ArrayList<>();

            path1.add("AER");
            path1.add("KZN");
            path1.add("ASF");
            path2.add("AER");
            path2.add("KZN");
            path2.add("CEK");
//            path2.add("KZN");
            FlightAnalyser fa = new FlightAnalyser(path1, path2, storage);
            System.out.println(fa.totalDistancePath1);
            System.out.println(fa.totalDistancePath2);
        }











}

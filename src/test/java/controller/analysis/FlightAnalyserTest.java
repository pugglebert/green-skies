package controller.analysis;

import model.data.Airport;
import model.data.Route;
import model.data.Storage;
import model.loader.Loader;
import org.junit.Before;
import org.junit.Test;
import java.io.FileNotFoundException;
import java.nio.file.FileSystemException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FlightAnalyserTest {


    Storage storage ;
    Loader loader ;
    ArrayList<String> path1;
    ArrayList<String> path2;
    String[] rubbish1;
    String[] rubbish2;

    Route route1 = new Route("Air Inter Gabon", 219, "AER", 2965, "KZN" , 2990, "dont know", 0,  rubbish1);
    Route route2 = new Route("Air Cess", 55, "ASF", 2966, "SVX", 2975, "dont know", 0, rubbish2);
    FlightAnalyser analyser;
    List<Airport> airport;
    private final double radius = 6371e3;
    double distance1;
    double distance2;

    /**
     *
     */
    public void findCoordinate() {

        airport = new ArrayList<>() ;
        airport = storage.getAirports();
        ArrayList<Airport> listOfAirportPath1 = new ArrayList<Airport>();
        ArrayList<Airport> listOfAirportPath2 = new ArrayList<Airport>();
        for(String k: path1) {
            for(Airport i: airport){
                if (i.getIATA().equals(k) || i.getICAO().equals(k)) {
                    listOfAirportPath1.add(i);

                }
            }
        }
        int i = 0;

        while(i < path2.size()) {
            int j = 0;
            while(j < airport.size()) {
                if(path2.get(i).equals(airport.get(j).getIATA()) || path2.get(i).equals(airport.get(j).getICAO())){
                    listOfAirportPath2.add(airport.get(j));

                }
                j++;
            }
            i++;
        }

        System.out.println(listOfAirportPath1);

        System.out.println(listOfAirportPath2);
        double path1Distance1 = calculatedistance(listOfAirportPath1.get(0).getLatitude(), listOfAirportPath1.get(0).getLongitude(), listOfAirportPath1.get(1).getLatitude(), listOfAirportPath1.get(1).getLongitude());

        double path2Distance1 = calculatedistance(listOfAirportPath2.get(0).getLatitude(), listOfAirportPath2.get(0).getLongitude(), listOfAirportPath2.get(1).getLatitude(), listOfAirportPath2.get(1).getLongitude());
        distance1 = path1Distance1;
        distance2 = path2Distance1;
        System.out.println(distance1);
        System.out.println(distance2);

    }

    /**
     *
     * @param distance
     * @return
     */
    private double calculateCarbonEmission(double distance) {

        double radius = 6371e3;   //radius of earth;



        double FuelUsed;

        final int seatsOccupancy = 333;   //number of passengers;

        final double Co2OfOneGramFuel = 3.15;  //in gram

        final int CruisingSpeed = 910;   //km per hour

        FuelUsed = distance * 12 / 1250  ;//fuel in tonns

        double FuelPerPassenger = (FuelUsed / (distance*seatsOccupancy))*1000000;   //fuel use per passenger per km

        double Co2PerPassengerPerKm = FuelPerPassenger*Co2OfOneGramFuel;  //co2 emissions per passenger km in gram

        double Co2Hour = (Co2PerPassengerPerKm*CruisingSpeed)/1000;   //how much Co2 genate per hour in kg

        double flytime = distance / CruisingSpeed;  // in hour

        double finalCo2 =  flytime*Co2Hour;

        return finalCo2;  // in kg
    }
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

    @Before
    public void setUp() throws FileNotFoundException, FileSystemException {
        storage = new Storage();
        loader = new Loader(storage);
        loader.loadFile("../seng202_project/src/test/java/TestFiles/airports.csv", "Airport");


        path1 = new ArrayList<>();

        path2   = new ArrayList<>();

        path1.add(route1.getSourceAirport());
        path1.add(route1.getDestinationAirport());

        path2.add(route2.getSourceAirport());
        path2.add(route2.getDestinationAirport());
        System.out.println(path1);
        System.out.println(path2);

        findCoordinate();
        analyser = new FlightAnalyser(route1, route2, storage);
    }

    @Test
    public void isDistancePath1Correct(){
        double path1Distance = analyser.getTotalDistancePath1();
        assertEquals(distance1, path1Distance, 0.0);
    }

    @Test
    public void isDistancePath2Correct(){
        double path2Distance = analyser.getTotalDistancePath2();

        assertEquals(distance2, path2Distance, 0.0);
    }

    @Test
    public void isEmissionPath1Correct(){
        assertEquals(calculateCarbonEmission(distance1), analyser.getPath1Emission(), 0.0);

    }
    @Test
    public void isEmissionPath2Correct(){
        assertEquals(calculateCarbonEmission(distance2), analyser.getPath2Emission(), 0.0);

    }
}

package controller.analysis;

import model.data.Airport;
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
    FlightAnalyser analyser;
    List<Airport> airport;
    private final double radius = 6371e3;
    double distance1;
    double distance2;

    /**
     *
     */
    @Test
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
        double path1Distance1 = calculatedistance(listOfAirportPath1.get(0).getLatitude(), listOfAirportPath1.get(0).getLongitude(), listOfAirportPath1.get(1).getLatitude(), listOfAirportPath1.get(1).getLongitude());
        double path1Distance2 = calculatedistance(listOfAirportPath1.get(1).getLatitude(), listOfAirportPath1.get(1).getLongitude(), listOfAirportPath1.get(2).getLatitude(), listOfAirportPath1.get(2).getLongitude());


        double path2Distance1 = calculatedistance(listOfAirportPath2.get(0).getLatitude(), listOfAirportPath2.get(0).getLongitude(), listOfAirportPath2.get(1).getLatitude(), listOfAirportPath2.get(1).getLongitude());
        double path2Distance2 = calculatedistance(listOfAirportPath2.get(1).getLatitude(), listOfAirportPath2.get(1).getLongitude(), listOfAirportPath2.get(2).getLatitude(), listOfAirportPath2.get(2).getLongitude());
        distance1 = path1Distance1 + path1Distance2;
        distance2 = path2Distance1 + path2Distance2;

        FlightAnalyser analyser = new FlightAnalyser(path1, path2, storage);
        assertEquals(path1Distance1+path1Distance2, analyser.getTotalDistancePath1(), 0.0);
        assertEquals(path2Distance1+path2Distance2, analyser.getTotalDistancePath2(), 0.0);
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

        path1 = new ArrayList();

        path2   = new ArrayList<>();

        path1.add("AER");
        path1.add("KZN");
        path1.add("ASF");

        path2.add("NBC");
        path2.add("DME");
        path2.add("KZN");

        analyser = new FlightAnalyser(path1, path2, storage);
    }

    @Test
    public void isDistancePath1Correct(){
        double path1Distance = analyser.getTotalDistancePath1();
        assertEquals(2547.25576865941, path1Distance, 0.0);
      }

    @Test
    public void isDistancePath2Correct(){
        double path2Distance = analyser.getTotalDistancePath2();
 ;
        assertEquals(1608.4319442023736, path2Distance, 0.0);
    }

    @Test
    public void isEmissionPath1Correct(){
        double path1Emission = analyser.getPath1Emission();
        findCoordinate();
        assertEquals(calculateCarbonEmission(distance1), analyser.getPath1Emission(), 0.0);

    }
    @Test
    public void isEmissionPath2Correct(){
        double path1Emission = analyser.getPath1Emission();
        findCoordinate();
        assertEquals(calculateCarbonEmission(distance2), analyser.getPath2Emission(), 0.0);

    }
}

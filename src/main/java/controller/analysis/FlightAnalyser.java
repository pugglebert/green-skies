package controller.analysis;

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


        public FlightAnalyser(double Latitude1, double Longitude1, double Latitude2, double Longitude2, double airlaneDistance) {
            this.LatitudeExample1 = Latitude1;
            this.LongitudeExample1 = Longitude1;
            this.LatitudeExample2 = Latitude2;
            this.LongitudeExample2 = Longitude2;
            this.distance = airlaneDistance;



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

        public double calculateCarbonEmission(double distance){
            FuelUsed = 59.6;  //fuel in tonns

            double FuelPerPassenger = (FuelUsed / (distance*seatsOccupancy))*1000000;   //fuel use per passenger per km

            double Co2PerPassengerPerKm = FuelPerPassenger*Co2OfOneGramFuel;  //co2 emissions per passenger km in gram

            double Co2Hour = (Co2PerPassengerPerKm*CruisingSpeed)/1000;   //how much Co2 genate per hour in kg

            double flytime = distance / CruisingSpeed;  // in hour

            double finalCo2 =  flytime*Co2Hour;

            return finalCo2;  // in kg

        }

        public static void main(String[] args) {
            double Lati1 =40.689202777778;
            double Long1 =-74.044219444444;
            double Lati2 = 38.889069444444;
            double Long2 = -77.034502777778;
            int disatnce = 5556;
            FlightAnalyser f1 = new FlightAnalyser(Lati1, Long1, Lati2, Long2, disatnce);
            System.out.println(f1.calculateCarbonEmission(disatnce));

        }











}

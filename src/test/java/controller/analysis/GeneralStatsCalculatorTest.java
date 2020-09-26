//package controller.analysis;
//
//import model.data.*;
//import model.loader.Loader;
//import org.junit.Before;
//import org.junit.Test;
//import java.io.FileNotFoundException;
//import java.nio.file.FileSystemException;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.*;
//
//import static org.junit.Assert.*;
//
///**
// * Unit test for ReportGenrator class.
// *
// * @author Hayley Krippner
// * @since 16/09/2020
// * @version 1.0
// */
//public class GeneralStatsCalculatorTest {
//
//  private Storage storage;
//  private Loader loader;
//  private GeneralStatsCalculator generalStatsCalculator;
//
//  @Before
//  public void setUp() {
//    storage = new Storage();
//    loader = new Loader(storage);
//    generalStatsCalculator = new GeneralStatsCalculator();
//    try {
//      loader.loadFile("../seng202_project/src/test/java/TestFiles/GenerateReportTest.csv", "Route");
//    } catch (FileSystemException | FileNotFoundException | SQLException e) {
//      e.printStackTrace();
//    }
//  }
//
//  // --------------------------------------- Testing for updateTotalEmissions
//
//  /**
//   * Verify that when updateTotalEmissions is called with a route with a large amount of carbon
//   * emissions that the total emissions is updated accordingly.The carbon emissions are starting at
//   * 0.0 g.
//   */
//  @Test
//  public void updateTotalEmissionsLargeEmissionsInitiallyZeroTest() {
//    generalStatsCalculator.setTotalCarbonEmissions(0.0);
//    Route testRoute = new Route("2W", 410, "SVX", 2975, "OVC", 4078, "", 0, "CR2".split(" "));
//    testRoute.setEmissions(20000000.50);
//    testRoute.setTimesTaken(1);
//    generalStatsCalculator.updateTotalEmissions(testRoute);
//    assertEquals(20000000.50, generalStatsCalculator.getTotalCarbonEmissions(), 0.0001);
//  }
//
//  /**
//   * Verify that when updateTotalEmissions is called with a route with a small amount of carbon
//   * emissions that the total emissions is updated accordingly.The carbon emissions are starting at
//   * 0.0 g.
//   */
//  @Test
//  public void updateTotalEmissionsSmallEmissionsInitiallyZeroTest() {
//    generalStatsCalculator.setTotalCarbonEmissions(0.0);
//    Route testRoute = new Route("2W", 410, "SVX", 2975, "OVC", 4078, "", 0, "CR2".split(" "));
//    testRoute.setEmissions(70000.0);
//    testRoute.setTimesTaken(1);
//    generalStatsCalculator.updateTotalEmissions(testRoute);
//    assertEquals(70000.0, generalStatsCalculator.getTotalCarbonEmissions(), 0.0001);
//  }
//
//  /**
//   * Verify that when updateTotalEmissions is called with a route with a zero carbon emissions that
//   * the total emissions is updated accordingly.The carbon emissions are starting at large amount.
//   */
//  @Test
//  public void updateTotalEmissionsZeroEmissionsInitiallyZeroTest() {
//    generalStatsCalculator.setTotalCarbonEmissions(0.0);
//    Route testRoute = new Route("2W", 410, "SVX", 2975, "OVC", 4078, "", 0, "CR2".split(" "));
//    testRoute.setEmissions(0.0);
//    testRoute.setTimesTaken(1);
//    generalStatsCalculator.updateTotalEmissions(testRoute);
//    assertEquals(0.0, generalStatsCalculator.getTotalCarbonEmissions(), 0.0001);
//  }
//
//  /**
//   * Verify that when updateTotalEmissions is called with a route with a large amount of carbon
//   * emissions that the total emissions is updated accordingly.The carbon emissions are starting at
//   * large amount.
//   */
//  @Test
//  public void updateTotalEmissionsLargeEmissionsInitiallyLargeTest() {
//    generalStatsCalculator.setTotalCarbonEmissions(80000000000.10);
//    Route testRoute = new Route("2W", 410, "SVX", 2975, "OVC", 4078, "", 0, "CR2".split(" "));
//    testRoute.setEmissions(1700000.20);
//    testRoute.setTimesTaken(1);
//    generalStatsCalculator.updateTotalEmissions(testRoute);
//    assertEquals(80001700000.30, generalStatsCalculator.getTotalCarbonEmissions(), 0.0001);
//  }
//
//  /**
//   * Verify that when updateTotalEmissions is called with a route with a small amount of carbon
//   * emissions that the total emissions is updated accordingly.The carbon emissions are starting at
//   * large amount.
//   */
//  @Test
//  public void updateTotalEmissionsSmallEmissionsInitiallyLargeTest() {
//    generalStatsCalculator.setTotalCarbonEmissions(90000000000.10);
//    Route testRoute = new Route("2W", 410, "SVX", 2975, "OVC", 4078, "", 0, "CR2".split(" "));
//    testRoute.setEmissions(4500.08);
//    testRoute.setTimesTaken(1);
//    generalStatsCalculator.updateTotalEmissions(testRoute);
//    assertEquals(90000004500.18, generalStatsCalculator.getTotalCarbonEmissions(), 0.0001);
//  }
//
//  /**
//   * Verify that when updateTotalEmissions is called with a route with a zero carbon emissions that
//   * the total emissions is updated accordingly.The carbon emissions are starting at large amount.
//   */
//  @Test
//  public void updateTotalEmissionsZeroEmissionsInitiallyLargeTest() {
//    generalStatsCalculator.setTotalCarbonEmissions(345231863432.98);
//    Route testRoute = new Route("2W", 410, "SVX", 2975, "OVC", 4078, "", 0, "CR2".split(" "));
//    testRoute.setEmissions(0.0);
//    testRoute.setTimesTaken(1);
//    generalStatsCalculator.updateTotalEmissions(testRoute);
//    assertEquals(345231863432.98, generalStatsCalculator.getTotalCarbonEmissions(), 0.0001);
//  }
//
//  //  /**
//  //   * Verify that when updateTotalEmissions is called with a route with a large amount of carbon
//  //   * emissions, which has been taken multipe times, that the total emissions is updated
//  //   * accordingly.The carbon emissions are starting at 0.0 g.
//  //   */
//  //  @Test
//  //  public void updateTotalEmissionsLargeEmissionsZeroTakenTest() {
//  //    reportGenerator.setTotalCarbonEmissions(0.0);
//  //    Route testRoute = new Route("2W", 410, "SVX", 2975, "OVC", 4078, "", 0, "CR2".split(" "));
//  //    testRoute.setEmissions(20000000.50);
//  //    testRoute.setTimesTaken(0);
//  //    reportGenerator.updateTotalEmissions(testRoute);
//  //    assertEquals(0.0, reportGenerator.getTotalCarbonEmissions(), 0.0001);
//  //  }
//
//  //  /**
//  //   * Verify that when updateTotalEmissions is called with a route with a small amount of carbon
//  //   * emissions, which has been taken zero times, that the total emissions is not updated. The
//  // carbon
//  //   * emissions are starting at 0.0 g.
//  //   */
//  //  @Test
//  //  public void updateTotalEmissionsSmallEmissionsManyTakenTest() {
//  //    reportGenerator.setTotalCarbonEmissions(0.0);
//  //    Route testRoute = new Route("2W", 410, "SVX", 2975, "OVC", 4078, "", 0, "CR2".split(" "));
//  //    testRoute.setEmissions(8760.0);
//  //    testRoute.setTimesTaken(1000);
//  //    reportGenerator.updateTotalEmissions(testRoute);
//  //    assertEquals(8760000.0, reportGenerator.getTotalCarbonEmissions(), 0.0001);
//  //  }
//
//  // ------------------------------------- Testing for updateTotalDistance
//
//  /**
//   * Verify that when updateTotalDistance is called with a route that is of large distance that the
//   * total distance is updated accordingly.The total distance is started as 0.0 km.
//   */
//  @Test
//  public void updateTotalDistanceLargeDistanceInitiallyZeroTest() {
//    generalStatsCalculator.setTotalDistanceTravelled(0.0);
//    Route testRoute = new Route("7K", 392, "PKL", 3920, "MDC", 2523, "", 2, "PDS".split(" "));
//    testRoute.setDistance(4157987.41);
//    generalStatsCalculator.updateTotalDistance(testRoute);
//    assertEquals(4157987.41, generalStatsCalculator.getTotalDistanceTravelled(), 0.0001);
//  }
//
//  /**
//   * Verify that when updateTotalDistance is called with a route that is of small distance that the
//   * total * distance is updated accordingly.The total distance is started as 0.0 km.
//   */
//  @Test
//  public void updateTotalDistanceSmallDistanceInitiallyZeroTest() {
//    generalStatsCalculator.setTotalDistanceTravelled(0.0);
//    Route testRoute = new Route("7K", 392, "PKL", 3920, "MDC", 2523, "", 2, "PDS".split(" "));
//    testRoute.setDistance(3902.79);
//    generalStatsCalculator.updateTotalDistance(testRoute);
//    assertEquals(3902.79, generalStatsCalculator.getTotalDistanceTravelled(), 0.0001);
//  }
//
//  /**
//   * Verify that when updateTotalDistance is called with a route with zero distance that the total
//   * emissions is updated accordingly. The total distance is started as 0.0km.
//   */
//  @Test
//  public void updateTotalDistanceZeroDistanceInitiallyZeroTest() {
//    generalStatsCalculator.setTotalCarbonEmissions(0.0);
//    Route testRoute = new Route("7K", 392, "PKL", 3920, "MDC", 2523, "", 2, "PDS".split(" "));
//    testRoute.setDistance(0.0);
//    generalStatsCalculator.updateTotalDistance(testRoute);
//    assertEquals(0.0, generalStatsCalculator.getTotalDistanceTravelled(), 0.0001);
//  }
//
//  /**
//   * Verify that when updateTotalDistance is called with a route that is of large distance that the
//   * total distance is updated accordingly. The total distance is started at a large distance in km.
//   */
//  @Test
//  public void updateTotalDistanceLargeDistanceInitiallyLargeTest() {
//    generalStatsCalculator.setTotalDistanceTravelled(58900000000000.02);
//    Route testRoute = new Route("7K", 392, "PKL", 3920, "MDC", 2523, "", 2, "PDS".split(" "));
//    testRoute.setDistance(46372.91);
//    generalStatsCalculator.updateTotalDistance(testRoute);
//    assertEquals(58900000046372.93, generalStatsCalculator.getTotalDistanceTravelled(), 0.0001);
//  }
//
//  /**
//   * Verify that when updateTotalDistance is called with a route that is of small distance that the
//   * total distance is updated accordingly. The total distance is started at a large distance in km.
//   */
//  @Test
//  public void updateTotalDistanceSmallDistanceInitiallyLargeTest() {
//    generalStatsCalculator.setTotalDistanceTravelled(67554440900000.07);
//    Route testRoute = new Route("7K", 392, "PKL", 3920, "MDC", 2523, "", 2, "PDS".split(" "));
//    testRoute.setDistance(4157.41);
//    generalStatsCalculator.updateTotalDistance(testRoute);
//    assertEquals(67554440904157.48, generalStatsCalculator.getTotalDistanceTravelled(), 0.0001);
//  }
//
//  /**
//   * Verify that when updateTotalDistance is called with a route that is of zero distance that the
//   * total distance is updated accordingly. The total distance is started at a large distance in km.
//   */
//  @Test
//  public void updateTotalDistanceZeroDistanceInitiallyLargeTest() {
//    generalStatsCalculator.setTotalDistanceTravelled(345231863432.98);
//    Route testRoute = new Route("7K", 392, "PKL", 3920, "MDC", 2523, "", 2, "PDS".split(" "));
//    testRoute.setDistance(0.0);
//    generalStatsCalculator.updateTotalDistance(testRoute);
//    assertEquals(345231863432.98, generalStatsCalculator.getTotalDistanceTravelled(), 0.0001);
//  }
//
//  // --------------------------------- Testing for updateMostEmissionsRoute
//
//  /**
//   * Verify that when updateMostEmissionsRoute is called with a route and there are no routes in the
//   * history, then the route with the most emissions is the route that was added.
//   */
//  @Test
//  public void updateMostEmissionsRouteFirstEntryTest() {
//    ArrayList<Route> expectedResults = new ArrayList<>();
//    Route testRoute = new Route("2H", 1654, "IKT", 2937, "ODO", 8944, "", 0, "AN4".split(" "));
//    expectedResults.add(testRoute);
//    testRoute.setEmissions(10000);
//    generalStatsCalculator.updateMostEmissionsRoute(testRoute);
//    assertEquals(expectedResults, generalStatsCalculator.getMostEmissionsRoutes());
//  }
//
//  /**
//   * Verify that when updateMostEmissionsRoute is called with a route and it has more emissions,
//   * then the most emissions route in the history is set to the added route.
//   */
//  @Test
//  public void updateMostEmissionsRouteAddMoreEmissionsEntryTest() {
//    ArrayList<Route> expectedResults = new ArrayList<>();
//    Route testRouteMoreEmissions =
//        new Route("2H", 1654, "GKA", 2937, "UAK", 8944, "", 0, "AN4".split(" "));
//    Route testRouteLessEmissions =
//        new Route("2B", 5336, "BGGH", 4253, "BIAR", 6436, "", 4, "NH7".split(" "));
//    testRouteMoreEmissions.setEmissions(12600);
//    testRouteLessEmissions.setEmissions(163);
//    expectedResults.add(testRouteMoreEmissions);
//    generalStatsCalculator.updateMostEmissionsRoute(testRouteLessEmissions);
//    generalStatsCalculator.updateMostEmissionsRoute(testRouteMoreEmissions);
//    assertEquals(expectedResults, generalStatsCalculator.getMostEmissionsRoutes());
//  }
//
//  /**
//   * Verify that when updateMostEmissionsRoute is called with a route and it has less emissions,
//   * then the most emissions route in the history is remains the same.
//   */
//  @Test
//  public void updateMostEmissionsRouteAddLessEmissionsEntryTest() {
//    ArrayList<Route> expectedResults = new ArrayList<>();
//    Route testRouteMoreEmissions =
//        new Route("2H", 1654, "GKA", 2937, "UAK", 8944, "", 0, "AN4".split(" "));
//    Route testRouteLessEmissions =
//        new Route("2B", 5336, "BGGH", 4253, "BIAR", 6436, "", 4, "NH7".split(" "));
//    testRouteMoreEmissions.setEmissions(12600);
//    testRouteLessEmissions.setEmissions(163);
//    expectedResults.add(testRouteMoreEmissions);
//    generalStatsCalculator.updateMostEmissionsRoute(testRouteMoreEmissions);
//    generalStatsCalculator.updateMostEmissionsRoute(testRouteLessEmissions);
//    assertEquals(expectedResults, generalStatsCalculator.getMostEmissionsRoutes());
//  }
//
//  /**
//   * Verify that when updateMostEmissionsRoute is called with a route and it is already in the most
//   * emissions routes, then the most emissions route in the history remains the same.
//   */
//  @Test
//  public void updateMostEmissionsRouteAddSameEmissionsSameRouteEntryTest() {
//    ArrayList<Route> expectedResults = new ArrayList<>();
//    Route testRouteEmissions =
//        new Route("2H", 1654, "GKA", 2937, "UAK", 8944, "", 0, "AN4".split(" "));
//    testRouteEmissions.setEmissions(20000);
//    expectedResults.add(testRouteEmissions);
//    generalStatsCalculator.updateMostEmissionsRoute(testRouteEmissions);
//    generalStatsCalculator.updateMostEmissionsRoute(testRouteEmissions);
//    assertEquals(expectedResults, generalStatsCalculator.getMostEmissionsRoutes());
//  }
//
//  /**
//   * Verify that when updateMostEmissionsRoute is called with a route and it has the same most
//   * emissions but isn't in the most emissions routes array, then the route is added to the most
//   * emissions route array.
//   */
//  @Test
//  public void updateMostEmissionsRouteAddSameEmissionsDiffRouteEntryTest() {
//    ArrayList<Route> expectedResults = new ArrayList<>();
//    Route testRouteEmissions =
//        new Route("2H", 1654, "GKA", 2937, "UAK", 8944, "", 0, "AN4".split(" "));
//    Route testRouteDiffEmissions =
//        new Route("2B", 5336, "BGGH", 4253, "BIAR", 6436, "", 4, "NH7".split(" "));
//    testRouteEmissions.setEmissions(20000); // 12600 km
//    testRouteDiffEmissions.setEmissions(20000); // 12600 km
//    expectedResults.add(testRouteEmissions);
//    expectedResults.add(testRouteDiffEmissions);
//    generalStatsCalculator.updateMostEmissionsRoute(testRouteEmissions);
//    generalStatsCalculator.updateMostEmissionsRoute(testRouteDiffEmissions);
//    assertEquals(expectedResults, generalStatsCalculator.getMostEmissionsRoutes());
//  }
//
//  /**
//   * Verify that when updateMostEmissionsRoute is called with a route and it has NaN emissions then
//   * the most emissions route in the history is not changed to this added route. This test also uses
//   * the FlightAnalyser class to check it works as expected here.
//   */
//  @Test
//  public void updateMostEmissionsRouteAddNaNEmissionsEntryUseFlighAnalyserTest() {
//    ArrayList<Route> expectedResults = new ArrayList<>();
//    List<DataType> providedAirports = new ArrayList<>();
//    providedAirports.add(
//        new Airport(
//            2937,
//            "Goroka",
//            "Goroka",
//            "Papua New Guinea",
//            "GKA",
//            "AYGA",
//            -6.081689,
//            145.391881,
//            5282,
//            10,
//            "U",
//            "Pacific/Port_Moresby"));
//    providedAirports.add(
//        new Airport(
//            8944,
//            "Narsarsuaq",
//            "Narssarssuaq",
//            "Greenland",
//            "UAK",
//            "BGBW",
//            61.160517,
//            -45.425978,
//            112,
//            -3,
//            "E",
//            "America/Godthab"));
//    providedAirports.add(
//        new Airport(
//            4253,
//            "Nuuk",
//            "Godthaab",
//            "Greenland",
//            "GOH",
//            "BGGH",
//            64.190922,
//            -51.678064,
//            283,
//            -3,
//            "E",
//            "America/Godthab"));
//    providedAirports.add(
//        new Airport(
//            6436,
//            "Akureyri",
//            "Akureyri",
//            "Iceland",
//            "AEY",
//            "BIAR",
//            65.659994,
//            -18.072703,
//            6,
//            0,
//            "N",
//            "Atlantic/Reykjavik"));
//
//    storage.setData(providedAirports, "Airport", "airporttest.csv");
//    Route testRouteEmissions =
//        new Route("2H", 1654, "GKA", 2937, "UAK", 8944, "", 0, "AN4".split(" "));
//    Route testRouteNaNEmissions =
//        new Route("2B", 5336, "BGGH", 4253, "BIAR", 6436, "", 4, "NH7".split(" "));
//    FlightAnalyser flightAnalyser =
//        new FlightAnalyser(testRouteNaNEmissions, testRouteEmissions, storage);
//    testRouteEmissions.setEmissions(flightAnalyser.getPath2Emission()); // 12600 km
//    testRouteNaNEmissions.setEmissions(flightAnalyser.getPath1Emission()); // NaN
//    expectedResults.add(testRouteEmissions);
//    generalStatsCalculator.updateMostEmissionsRoute(testRouteEmissions);
//    generalStatsCalculator.updateMostEmissionsRoute(testRouteNaNEmissions);
//    assertEquals(expectedResults, generalStatsCalculator.getMostEmissionsRoutes());
//  }
//
//  /**
//   * Verify that when updateMostEmissionsRoute is called with a route that has NaN emissions and
//   * then with a route that has emissions that the NaN route gets ignored and the most emissions
//   * route is the second route (not NaN emissions).
//   */
//  @Test
//  public void updateMostEmissionsRouteMoreEmissionsEntryTest() throws SQLException {
//    ArrayList<Route> expectedResults = new ArrayList<>();
//    List<DataType> providedAirports = new ArrayList<>();
//    providedAirports.add(
//        new Airport(
//            2937,
//            "Goroka",
//            "Goroka",
//            "Papua New Guinea",
//            "GKA",
//            "AYGA",
//            -6.081689,
//            145.391881,
//            5282,
//            10,
//            "U",
//            "Pacific/Port_Moresby"));
//    providedAirports.add(
//        new Airport(
//            8944,
//            "Narsarsuaq",
//            "Narssarssuaq",
//            "Greenland",
//            "UAK",
//            "BGBW",
//            61.160517,
//            -45.425978,
//            112,
//            -3,
//            "E",
//            "America/Godthab"));
//    providedAirports.add(
//        new Airport(
//            4253,
//            "Nuuk",
//            "Godthaab",
//            "Greenland",
//            "GOH",
//            "BGGH",
//            64.190922,
//            -51.678064,
//            283,
//            -3,
//            "E",
//            "America/Godthab"));
//    providedAirports.add(
//        new Airport(
//            6436,
//            "Akureyri",
//            "Akureyri",
//            "Iceland",
//            "AEY",
//            "BIAR",
//            65.659994,
//            -18.072703,
//            6,
//            0,
//            "N",
//            "Atlantic/Reykjavik"));
//
//    storage.setData(providedAirports, "Airport", "airporttest.csv");
//    Route testRouteEmissions =
//        new Route("2H", 1654, "GKA", 2937, "UAK", 8944, "", 0, "AN4".split(" "));
//    Route testRouteNaNEmissions =
//        new Route("2B", 5336, "BGGH", 4253, "BIAR", 6436, "", 4, "NH7".split(" "));
//    FlightAnalyser flightAnalyser =
//        new FlightAnalyser(testRouteNaNEmissions, testRouteEmissions, storage);
//    testRouteEmissions.setEmissions(flightAnalyser.getPath2Emission()); // 12600 km
//    testRouteNaNEmissions.setEmissions(flightAnalyser.getPath1Emission()); // NaN
//    expectedResults.add(testRouteEmissions);
//    generalStatsCalculator.updateMostEmissionsRoute(testRouteNaNEmissions);
//    generalStatsCalculator.updateMostEmissionsRoute(testRouteEmissions);
//    assertEquals(expectedResults, generalStatsCalculator.getMostEmissionsRoutes());
//  }
//
//  // --------------------------------- Testing for updateLeastEmissionsRoute
//
//  /**
//   * Verify that when updateLeastEmissionsRoute is called with a route and there are no routes in
//   * the history, then the route with the least emissions is the route that was added.
//   */
//  @Test
//  public void updateLeastEmissionsRouteFirstEntryTest() {
//    ArrayList<Route> expectedResults = new ArrayList<>();
//    Route testRoute = new Route("2H", 1654, "IKT", 2937, "ODO", 8944, "", 0, "AN4".split(" "));
//    expectedResults.add(testRoute);
//    testRoute.setEmissions(3425353);
//    generalStatsCalculator.updateLeastEmissionsRoute(testRoute);
//    assertEquals(expectedResults, generalStatsCalculator.getLeastEmissionsRoutes());
//  }
//
//  /**
//   * Verify that when updateLeastEmissionsRoute is called with a route and it has more emissions,
//   * then the less emissions route in the history is not changed.
//   */
//  @Test
//  public void updateLeastEmissionsRouteAddMoreEmissionsEntryTest() {
//    ArrayList<Route> expectedResults = new ArrayList<>();
//    Route testRouteMoreEmissions =
//        new Route("2H", 1654, "GKA", 2937, "UAK", 8944, "", 0, "AN4".split(" "));
//    Route testRouteLessEmissions =
//        new Route("2B", 5336, "BGGH", 4253, "BIAR", 6436, "", 4, "NH7".split(" "));
//    testRouteMoreEmissions.setEmissions(12600);
//    testRouteLessEmissions.setEmissions(163);
//    expectedResults.add(testRouteLessEmissions);
//    generalStatsCalculator.updateLeastEmissionsRoute(testRouteLessEmissions);
//    generalStatsCalculator.updateLeastEmissionsRoute(testRouteMoreEmissions);
//    assertEquals(expectedResults, generalStatsCalculator.getLeastEmissionsRoutes());
//  }
//
//  /**
//   * Verify that when updateLeastEmissionsRoute is called with a route and it has less emissions,
//   * then the less emissions route in the history is updated to the added route.
//   */
//  @Test
//  public void updateLeastEmissionsRouteAddLessEmissionsEntryTest() {
//    ArrayList<Route> expectedResults = new ArrayList<>();
//    Route testRouteMoreEmissions =
//        new Route("2H", 1654, "GKA", 2937, "UAK", 8944, "", 0, "AN4".split(" "));
//    Route testRouteLessEmissions =
//        new Route("2B", 5336, "BGGH", 4253, "BIAR", 6436, "", 4, "NH7".split(" "));
//    testRouteMoreEmissions.setEmissions(12600);
//    testRouteLessEmissions.setEmissions(163);
//    expectedResults.add(testRouteLessEmissions);
//    generalStatsCalculator.updateLeastEmissionsRoute(testRouteMoreEmissions);
//    generalStatsCalculator.updateLeastEmissionsRoute(testRouteLessEmissions);
//    assertEquals(expectedResults, generalStatsCalculator.getLeastEmissionsRoutes());
//  }
//
//  /**
//   * Verify that when updateLeastEmissionsRoute is called with a route and it is already in the
//   * least emissions routes, then the least emissions route in the history remains the same.
//   */
//  @Test
//  public void updateLeastEmissionsRouteAddSameEmissionsSameRouteEntryTest() {
//    ArrayList<Route> expectedResults = new ArrayList<>();
//    Route testRouteEmissions =
//        new Route("2H", 1654, "GKA", 2937, "UAK", 8944, "", 0, "AN4".split(" "));
//    testRouteEmissions.setEmissions(20000);
//    expectedResults.add(testRouteEmissions);
//    generalStatsCalculator.updateLeastEmissionsRoute(testRouteEmissions);
//    generalStatsCalculator.updateLeastEmissionsRoute(testRouteEmissions);
//    assertEquals(expectedResults, generalStatsCalculator.getLeastEmissionsRoutes());
//  }
//
//  /**
//   * Verify that when updateLeastEmissionsRoute is called with a route and it has the same most
//   * emissions but isn't in the most emissions routes array, then the route is added to the most
//   * emissions route array.
//   */
//  @Test
//  public void updateLeastEmissionsRouteAddSameEmissionsDiffRouteEntryTest() {
//    ArrayList<Route> expectedResults = new ArrayList<>();
//    Route testRouteEmissions =
//        new Route("2H", 1654, "GKA", 2937, "UAK", 8944, "", 0, "AN4".split(" "));
//    Route testRouteDiffEmissions =
//        new Route("2B", 5336, "BGGH", 4253, "BIAR", 6436, "", 4, "NH7".split(" "));
//    testRouteEmissions.setEmissions(20000);
//    testRouteDiffEmissions.setEmissions(20000);
//    expectedResults.add(testRouteEmissions);
//    expectedResults.add(testRouteDiffEmissions);
//    generalStatsCalculator.updateLeastEmissionsRoute(testRouteEmissions);
//    generalStatsCalculator.updateLeastEmissionsRoute(testRouteDiffEmissions);
//    assertEquals(expectedResults, generalStatsCalculator.getLeastEmissionsRoutes());
//  }
//
//  /**
//   * Verify that when updateLeastEmissionsRoute is called with a route and it has NaN emissions then
//   * the most emissions route in the history is not changed to this added route. This test also uses
//   * the FlightAnalyser class to check it works as expected here.
//   */
//  @Test
//  public void updateLeastEmissionsRouteAddNaNEmissionsEntryUseFlighAnalyserTest()
//      throws SQLException {
//    ArrayList<Route> expectedResults = new ArrayList<>();
//    List<DataType> providedAirports = new ArrayList<>();
//    providedAirports.add(
//        new Airport(
//            2937,
//            "Goroka",
//            "Goroka",
//            "Papua New Guinea",
//            "GKA",
//            "AYGA",
//            -6.081689,
//            145.391881,
//            5282,
//            10,
//            "U",
//            "Pacific/Port_Moresby"));
//    providedAirports.add(
//        new Airport(
//            8944,
//            "Narsarsuaq",
//            "Narssarssuaq",
//            "Greenland",
//            "UAK",
//            "BGBW",
//            61.160517,
//            -45.425978,
//            112,
//            -3,
//            "E",
//            "America/Godthab"));
//    providedAirports.add(
//        new Airport(
//            4253,
//            "Nuuk",
//            "Godthaab",
//            "Greenland",
//            "GOH",
//            "BGGH",
//            64.190922,
//            -51.678064,
//            283,
//            -3,
//            "E",
//            "America/Godthab"));
//    providedAirports.add(
//        new Airport(
//            6436,
//            "Akureyri",
//            "Akureyri",
//            "Iceland",
//            "AEY",
//            "BIAR",
//            65.659994,
//            -18.072703,
//            6,
//            0,
//            "N",
//            "Atlantic/Reykjavik"));
//
//    storage.setData(providedAirports, "Airport", "airporttest.csv");
//    Route testRouteNaNEmissions =
//        new Route("2H", 1654, "GKA", 2937, "UAK", 8944, "", 0, "AN4".split(" "));
//    Route testRouteEmissions =
//        new Route("2B", 5336, "BGGH", 4253, "BIAR", 6436, "", 4, "NH7".split(" "));
//    FlightAnalyser flightAnalyser =
//        new FlightAnalyser(testRouteNaNEmissions, testRouteEmissions, storage);
//    testRouteEmissions.setEmissions(flightAnalyser.getPath2Emission()); // 12600 km
//    testRouteNaNEmissions.setEmissions(flightAnalyser.getPath1Emission()); // NaN
//    expectedResults.add(testRouteEmissions);
//    generalStatsCalculator.updateLeastEmissionsRoute(testRouteEmissions);
//    generalStatsCalculator.updateLeastEmissionsRoute(testRouteNaNEmissions);
//    assertEquals(expectedResults, generalStatsCalculator.getLeastEmissionsRoutes());
//  }
//
//  /**
//   * Verify that when updateLeastEmissionsRoute is called with a route that has NaN emissions and
//   * then with a route that has emissions that the NaN route gets ignored and the most emissions
//   * route is the second route (not NaN emissions).
//   */
//  @Test
//  public void updateLeastEmissionsRouteMoreEmissionsUseFlighAnalyserEntryTest()
//      throws SQLException {
//    ArrayList<Route> expectedResults = new ArrayList<>();
//    List<DataType> providedAirports = new ArrayList<>();
//    providedAirports.add(
//        new Airport(
//            2937,
//            "Goroka",
//            "Goroka",
//            "Papua New Guinea",
//            "GKA",
//            "AYGA",
//            -6.081689,
//            145.391881,
//            5282,
//            10,
//            "U",
//            "Pacific/Port_Moresby"));
//    providedAirports.add(
//        new Airport(
//            8944,
//            "Narsarsuaq",
//            "Narssarssuaq",
//            "Greenland",
//            "UAK",
//            "BGBW",
//            61.160517,
//            -45.425978,
//            112,
//            -3,
//            "E",
//            "America/Godthab"));
//    providedAirports.add(
//        new Airport(
//            4253,
//            "Nuuk",
//            "Godthaab",
//            "Greenland",
//            "GOH",
//            "BGGH",
//            64.190922,
//            -51.678064,
//            283,
//            -3,
//            "E",
//            "America/Godthab"));
//    providedAirports.add(
//        new Airport(
//            6436,
//            "Akureyri",
//            "Akureyri",
//            "Iceland",
//            "AEY",
//            "BIAR",
//            65.659994,
//            -18.072703,
//            6,
//            0,
//            "N",
//            "Atlantic/Reykjavik"));
//
//    storage.setData(providedAirports, "Airport", "airporttest.csv");
//    Route testRouteNaNEmissions =
//        new Route("2H", 1654, "GKA", 2937, "UAK", 8944, "", 0, "AN4".split(" "));
//    Route testRouteEmissions =
//        new Route("2B", 5336, "BGGH", 4253, "BIAR", 6436, "", 4, "NH7".split(" "));
//    FlightAnalyser flightAnalyser =
//        new FlightAnalyser(testRouteNaNEmissions, testRouteEmissions, storage);
//    testRouteEmissions.setEmissions(flightAnalyser.getPath2Emission()); // 12600 km
//    testRouteNaNEmissions.setEmissions(flightAnalyser.getPath1Emission()); // NaN
//    expectedResults.add(testRouteEmissions);
//    generalStatsCalculator.updateLeastEmissionsRoute(testRouteNaNEmissions);
//    generalStatsCalculator.updateLeastEmissionsRoute(testRouteEmissions);
//    assertEquals(expectedResults, generalStatsCalculator.getLeastEmissionsRoutes());
//  }
//
//  // --------------------------------- Testing for updateMostDistanceRoute
//
//  /**
//   * Verify that when updateMostDistanceRoute is called with a route and there are no routes in the
//   * history, then the route of the most distance is the route that was added.
//   */
//  @Test
//  public void updateMostDistanceRouteFirstEntryTest() {
//    ArrayList<Route> expectedResults = new ArrayList<>();
//    Route testRoute = new Route("2H", 1654, "IKT", 2937, "ODO", 8944, "", 0, "AN4".split(" "));
//    expectedResults.add(testRoute);
//    testRoute.setDistance(12333333);
//    generalStatsCalculator.updateMostDistanceRoute(testRoute);
//    assertEquals(expectedResults, generalStatsCalculator.getMostDistanceRoutes());
//  }
//
//  /**
//   * Verify that when updateMostDistanceRoute is called with a route and it is of more distance,
//   * then the more emissions route in the history is changed to this route.
//   */
//  @Test
//  public void updateMostDistanceRouteAddMoreDistanceEntryTest() {
//    ArrayList<Route> expectedResults = new ArrayList<>();
//    Route testRouteMoreDistance =
//        new Route("2H", 1654, "GKA", 2937, "UAK", 8944, "", 0, "AN4".split(" "));
//    Route testRouteLessDistance =
//        new Route("2B", 5336, "BGGH", 4253, "BIAR", 6436, "", 4, "NH7".split(" "));
//    testRouteMoreDistance.setDistance(29394389);
//    testRouteLessDistance.setDistance(309328);
//    expectedResults.add(testRouteMoreDistance);
//    generalStatsCalculator.updateMostDistanceRoute(testRouteLessDistance);
//    generalStatsCalculator.updateMostDistanceRoute(testRouteMoreDistance);
//    assertEquals(expectedResults, generalStatsCalculator.getMostDistanceRoutes());
//  }
//
//  /**
//   * Verify that when updateMostDistanceRoute is called with a route and it is of less distance,
//   * then the most distance route is not changed.
//   */
//  @Test
//  public void updateMostDistanceRouteAddLessDistanceEntryTest() {
//    ArrayList<Route> expectedResults = new ArrayList<>();
//    Route testRouteMoreDistance =
//        new Route("2H", 1654, "GKA", 2937, "UAK", 8944, "", 0, "AN4".split(" "));
//    Route testRouteLessDistance =
//        new Route("2B", 5336, "BGGH", 4253, "BIAR", 6436, "", 4, "NH7".split(" "));
//    testRouteMoreDistance.setDistance(43243224);
//    testRouteLessDistance.setDistance(3232223);
//    expectedResults.add(testRouteMoreDistance);
//    generalStatsCalculator.updateMostDistanceRoute(testRouteMoreDistance);
//    generalStatsCalculator.updateMostDistanceRoute(testRouteLessDistance);
//    assertEquals(expectedResults, generalStatsCalculator.getMostDistanceRoutes());
//  }
//
//  /**
//   * Verify that when updateMostDistanceRoute is called with a route and it is already in the most
//   * distance routes, then the most distance routes in the history remains the same.
//   */
//  @Test
//  public void updateMostDistanceRouteAddSameDistanceSameRouteEntryTest() {
//    ArrayList<Route> expectedResults = new ArrayList<>();
//    Route testRouteDistance =
//        new Route("2H", 1654, "GKA", 2937, "UAK", 8944, "", 0, "AN4".split(" "));
//    testRouteDistance.setDistance(700080);
//    expectedResults.add(testRouteDistance);
//    generalStatsCalculator.updateMostDistanceRoute(testRouteDistance);
//    generalStatsCalculator.updateMostDistanceRoute(testRouteDistance);
//    assertEquals(expectedResults, generalStatsCalculator.getMostDistanceRoutes());
//  }
//
//  /**
//   * Verify that when updateMostDistanceRoute is called with a route and it has the same most
//   * distance but isn't in the most distance routes array, then the route is added to the most
//   * distance route array.
//   */
//  @Test
//  public void updateMostDistanceRouteAddSameEmissionsDiffRouteEntryTest() {
//    ArrayList<Route> expectedResults = new ArrayList<>();
//    Route testRouteDistance =
//        new Route("2H", 1654, "GKA", 2937, "UAK", 8944, "", 0, "AN4".split(" "));
//    Route testRouteDiffDistance =
//        new Route("2B", 5336, "BGGH", 4253, "BIAR", 6436, "", 4, "NH7".split(" "));
//    testRouteDistance.setDistance(20000);
//    testRouteDiffDistance.setDistance(20000);
//    expectedResults.add(testRouteDistance);
//    expectedResults.add(testRouteDiffDistance);
//    generalStatsCalculator.updateMostDistanceRoute(testRouteDistance);
//    generalStatsCalculator.updateMostDistanceRoute(testRouteDiffDistance);
//    assertEquals(expectedResults, generalStatsCalculator.getMostDistanceRoutes());
//  }
//
//  // --------------------------------- Testing for updateLeastDistanceRoute
//
//  /**
//   * Verify that when updateLeastDistanceRoute is called with a route and there are no routes in the
//   * history, then the route of the least distance is the route that was added.
//   */
//  @Test
//  public void updateLeastDistanceRouteFirstEntryTest() {
//    ArrayList<Route> expectedResults = new ArrayList<>();
//    Route testRoute = new Route("2H", 1654, "IKT", 2937, "ODO", 8944, "", 0, "AN4".split(" "));
//    expectedResults.add(testRoute);
//    testRoute.setDistance(2432828);
//    generalStatsCalculator.updateLeastDistanceRoute(testRoute);
//    assertEquals(expectedResults, generalStatsCalculator.getLeastDistanceRoutes());
//  }
//
//  /**
//   * Verify that when updateLeastDistanceRoute is called with a route and it is of less distance,
//   * then the less emissions route in the history is changed to this route.
//   */
//  @Test
//  public void updateLeastDistanceRouteAddLessDistanceEntryTest() {
//    ArrayList<Route> expectedResults = new ArrayList<>();
//    Route testRouteMoreDistance =
//        new Route("2H", 1654, "GKA", 2937, "UAK", 8944, "", 0, "AN4".split(" "));
//    Route testRouteLessDistance =
//        new Route("2B", 5336, "BGGH", 4253, "BIAR", 6436, "", 4, "NH7".split(" "));
//    testRouteMoreDistance.setDistance(29394389);
//    testRouteLessDistance.setDistance(309328);
//    expectedResults.add(testRouteLessDistance);
//    generalStatsCalculator.updateLeastDistanceRoute(testRouteMoreDistance);
//    generalStatsCalculator.updateLeastDistanceRoute(testRouteLessDistance);
//    assertEquals(expectedResults, generalStatsCalculator.getLeastDistanceRoutes());
//  }
//
//  /**
//   * Verify that when updateLeastDistanceRoute is called with a route and it is of more distance,
//   * then the most less route is not changed.
//   */
//  @Test
//  public void updateLessDistanceRouteAddMoreDistanceEntryTest() {
//    ArrayList<Route> expectedResults = new ArrayList<>();
//    Route testRouteMoreDistance =
//        new Route("2H", 1654, "GKA", 2937, "UAK", 8944, "", 0, "AN4".split(" "));
//    Route testRouteLessDistance =
//        new Route("2B", 5336, "BGGH", 4253, "BIAR", 6436, "", 4, "NH7".split(" "));
//    testRouteMoreDistance.setDistance(43243224);
//    testRouteLessDistance.setDistance(3232223);
//    expectedResults.add(testRouteLessDistance);
//    generalStatsCalculator.updateLeastDistanceRoute(testRouteLessDistance);
//    generalStatsCalculator.updateLeastDistanceRoute(testRouteMoreDistance);
//    assertEquals(expectedResults, generalStatsCalculator.getLeastDistanceRoutes());
//  }
//
//  /**
//   * Verify that when updateLeastDistanceRoute is called with a route and it is already in the least
//   * distance routes, then the least distance routes in the history remains the same.
//   */
//  @Test
//  public void updateLeastDistanceRouteAddSameDistanceSameRouteEntryTest() {
//    ArrayList<Route> expectedResults = new ArrayList<>();
//    Route testRouteDistance =
//        new Route("2H", 1654, "GKA", 2937, "UAK", 8944, "", 0, "AN4".split(" "));
//    testRouteDistance.setDistance(34727229);
//    expectedResults.add(testRouteDistance);
//    generalStatsCalculator.updateLeastDistanceRoute(testRouteDistance);
//    generalStatsCalculator.updateLeastDistanceRoute(testRouteDistance);
//    assertEquals(expectedResults, generalStatsCalculator.getLeastDistanceRoutes());
//  }
//
//  /**
//   * Verify that when updateLeastDistanceRoute is called with a route and it has the same most
//   * distance but isn't in the most distance routes array, then the route is added to the most
//   * distance route array.
//   */
//  @Test
//  public void updateLeastDistanceRouteAddSameDistanceDiffRouteEntryTest() {
//    ArrayList<Route> expectedResults = new ArrayList<>();
//    Route testRouteDistance =
//        new Route("2H", 1654, "GKA", 2937, "UAK", 8944, "", 0, "AN4".split(" "));
//    Route testRouteDiffDistance =
//        new Route("2B", 5336, "BGGH", 4253, "BIAR", 6436, "", 4, "NH7".split(" "));
//    testRouteDistance.setDistance(20000);
//    testRouteDiffDistance.setDistance(20000);
//    expectedResults.add(testRouteDistance);
//    expectedResults.add(testRouteDiffDistance);
//    generalStatsCalculator.updateLeastDistanceRoute(testRouteDistance);
//    generalStatsCalculator.updateLeastDistanceRoute(testRouteDiffDistance);
//    assertEquals(expectedResults, generalStatsCalculator.getLeastDistanceRoutes());
//  }
//
//  // --------------------------------- Testing for updateMostTravelledMostRoute
//
//  /**
//   * Verify that when updateMostTravelledRoute is called when there is one route in the flight
//   * history, then the most travelled route is this single route.
//   */
//  @Test
//  public void updateMostTravelledRouteOneTest() {
//    ArrayList<Route> expectedResults = new ArrayList<>();
//    ArrayList<Route> testRoutes = new ArrayList<>();
//    Route testRoute = new Route("2H", 1654, "GKA", 2937, "UAK", 8944, "", 0, "AN4".split(" "));
//    testRoute.setTimesTaken(5);
//    expectedResults.add(testRoute);
//    testRoutes.add(testRoute);
//    generalStatsCalculator.updateMostTravelledRoute(testRoutes);
//    assertEquals(expectedResults, generalStatsCalculator.getMostTravelledRoute());
//  }
//
//  /**
//   * Verify that when updateMostTravelledRoute is called when there are two routes in the flight
//   * history and one is more travelled than the other, then the most travelled route is the only
//   * route in the mostTravelled Route array.
//   */
//  @Test
//  public void updateMostTravelledMostRouteTwoTest() {
//    ArrayList<Route> expectedResults = new ArrayList<>();
//    ArrayList<Route> testRoutes = new ArrayList<>();
//    Route testRouteMost = new Route("2H", 1654, "GKA", 2937, "UAK", 8944, "", 0, "AN4".split(" "));
//    Route testRouteLess =
//        new Route("2B", 5336, "BGGH", 4253, "BIAR", 6436, "", 4, "NH7".split(" "));
//    testRouteLess.setTimesTaken(5);
//    testRouteMost.setTimesTaken(9);
//    expectedResults.add(testRouteMost);
//    testRoutes.add(testRouteMost);
//    testRoutes.add(testRouteLess);
//    generalStatsCalculator.updateMostTravelledRoute(testRoutes);
//    assertEquals(expectedResults, generalStatsCalculator.getMostTravelledRoute());
//  }
//
//  /**
//   * Verify that when updateMostTravelledRoute is called when there are multiple routes in history
//   * i.e. 10 and one of them is of the most travelled, then that route is the only route added to
//   * the most travelled routes array.
//   */
//  @Test
//  public void updateMostTravelledMostRouteTenDiffOneMostTest() {
//    ArrayList<Route> expectedResults = new ArrayList<>();
//    ArrayList<Route> testRoutes = new ArrayList<>();
//    Route testRouteMost = new Route("2H", 1654, "GKA", 2937, "UAK", 8944, "", 0, "AN4".split(" "));
//    Route testRoute2 = new Route("2B", 5336, "BGGH", 4253, "BIAR", 6436, "", 4, "NH7".split(" "));
//    Route testRoute3 = new Route("2C", 5336, "BGGH", 4253, "BIAR", 6436, "", 4, "NH7".split(" "));
//    Route testRoute4 = new Route("2D", 2777, "GBRF", 2242, "BIAR", 6436, "", 3, "NH7".split(" "));
//    Route testRoute5 = new Route("2E", 1744, "GFRG", 2727, "BIAR", 6436, "", 1, "NH7".split(" "));
//    Route testRoute6 = new Route("2F", 2424, "SWVR", 5858, "BIAR", 6436, "", 0, "NH7".split(" "));
//    Route testRoute7 = new Route("2G", 2775, "SDD", 7557, "BIAR", 6436, "", 3, "NH7".split(" "));
//    Route testRoute8 = new Route("2H", 9898, "VSV", 5578, "BIAR", 6436, "", 4, "NH7".split(" "));
//    Route testRoute9 = new Route("2I", 2782, "SVDE", 5257, "BIAR", 6436, "", 4, "NH7".split(" "));
//    testRouteMost.setTimesTaken(10);
//    testRoute2.setTimesTaken(2);
//    testRoute3.setTimesTaken(3);
//    testRoute4.setTimesTaken(4);
//    testRoute5.setTimesTaken(5);
//    testRoute6.setTimesTaken(6);
//    testRoute7.setTimesTaken(7);
//    testRoute8.setTimesTaken(8);
//    testRoute9.setTimesTaken(9);
//    expectedResults.add(testRouteMost);
//    testRoutes.add(testRoute2);
//    testRoutes.add(testRoute3);
//    testRoutes.add(testRoute4);
//    testRoutes.add(testRoute5);
//    testRoutes.add(testRoute6);
//    testRoutes.add(testRoute7);
//    testRoutes.add(testRoute8);
//    testRoutes.add(testRoute9);
//    testRoutes.add(testRouteMost);
//    generalStatsCalculator.updateMostTravelledRoute(testRoutes);
//    assertEquals(expectedResults, generalStatsCalculator.getMostTravelledRoute());
//  }
//
//  /**
//   * Verify that when updateMostTravelledRoute is called when there are multiple routes in history
//   * i.e. 10 and more than one of them e.g. 3 is of the most travelled, then those routes are the
//   * only routes added to the most travelled routes array.
//   */
//  @Test
//  public void updateMostTravelledMostRouteTenDiffThreeMostTest() {
//    ArrayList<Route> expectedResults = new ArrayList<>();
//    ArrayList<Route> testRoutes = new ArrayList<>();
//    Route testRoute1 = new Route("2A", 1654, "GKA", 2937, "UAK", 8944, "", 0, "AN4".split(" "));
//    Route testRoute2 = new Route("2B", 5336, "BGGH", 4253, "BIAR", 6436, "", 4, "NH7".split(" "));
//    Route testRoute3 = new Route("2C", 5336, "BGGH", 4253, "BIAR", 6436, "", 4, "NH7".split(" "));
//    Route testRoute4 = new Route("2D", 2777, "GBRF", 2242, "BIAR", 6436, "", 3, "NH7".split(" "));
//    Route testRoute5 = new Route("2E", 1744, "GFRG", 2727, "BIAR", 6436, "", 1, "NH7".split(" "));
//    Route testRoute6 = new Route("2F", 2424, "SWVR", 5858, "BIAR", 6436, "", 0, "NH7".split(" "));
//    Route testRoute7 = new Route("2G", 2775, "SDD", 7557, "BIAR", 6436, "", 3, "NH7".split(" "));
//    Route testRoute8 = new Route("2H", 9898, "VSV", 5578, "BIAR", 6436, "", 4, "NH7".split(" "));
//    Route testRoute9 = new Route("2I", 2782, "SVDE", 5257, "BIAR", 6436, "", 4, "NH7".split(" "));
//    testRoute1.setTimesTaken(8);
//    testRoute2.setTimesTaken(2);
//    testRoute3.setTimesTaken(9);
//    testRoute4.setTimesTaken(4);
//    testRoute5.setTimesTaken(9);
//    testRoute6.setTimesTaken(6);
//    testRoute7.setTimesTaken(7);
//    testRoute8.setTimesTaken(8);
//    testRoute9.setTimesTaken(9);
//    expectedResults.add(testRoute9);
//    expectedResults.add(testRoute5);
//    expectedResults.add(testRoute3);
//    testRoutes.add(testRoute1);
//    testRoutes.add(testRoute2);
//    testRoutes.add(testRoute3);
//    testRoutes.add(testRoute4);
//    testRoutes.add(testRoute5);
//    testRoutes.add(testRoute6);
//    testRoutes.add(testRoute7);
//    testRoutes.add(testRoute8);
//    testRoutes.add(testRoute9);
//    generalStatsCalculator.updateMostTravelledRoute(testRoutes);
//    assertEquals(expectedResults, generalStatsCalculator.getMostTravelledRoute());
//  }
//
//  /**
//   * Verify that when updateMostTravelledRoute is called when there are no routes in history that
//   * there are no routes in the most travelled routes array.
//   */
//  @Test
//  public void updateMostTravelledRouteNoRoutesTest() {
//    ArrayList<Route> expectedResults = new ArrayList<>();
//    ArrayList<Route> testRoutes = new ArrayList<>();
//    generalStatsCalculator.updateMostTravelledRoute(testRoutes);
//    assertEquals(expectedResults, generalStatsCalculator.getMostTravelledRoute());
//  }
//
//  // --------------------------------- Testing for updateLeastTravelledMostRoute
//
//  /**
//   * Verify that when updateMostTravelledRoute is called when there is one route in the flight
//   * history, then the least travelled route is this single route.
//   */
//  @Test
//  public void updateLeastTravelledMostRouteOneTest() {
//    ArrayList<Route> expectedResults = new ArrayList<>();
//    ArrayList<Route> testRoutes = new ArrayList<>();
//    Route testRoute = new Route("2H", 1654, "GKA", 2937, "UAK", 8944, "", 0, "AN4".split(" "));
//    testRoute.setTimesTaken(5);
//    expectedResults.add(testRoute);
//    testRoutes.add(testRoute);
//    generalStatsCalculator.updateLeastTravelledRoute(testRoutes);
//    assertEquals(expectedResults, generalStatsCalculator.getLeastTravelledRoute());
//  }
//
//  /**
//   * Verify that when updateLeastTravelledRoute is called when there are two routes in the flight
//   * history and one is less travelled than the other, then the least travelled route is the only
//   * route in the least travelled routes array.
//   */
//  @Test
//  public void updateLeastTravelledMostRouteTwoTest() {
//    ArrayList<Route> expectedResults = new ArrayList<>();
//    ArrayList<Route> testRoutes = new ArrayList<>();
//    Route testRouteMost = new Route("2H", 1654, "GKA", 2937, "UAK", 8944, "", 0, "AN4".split(" "));
//    Route testRouteLess =
//        new Route("2B", 5336, "BGGH", 4253, "BIAR", 6436, "", 4, "NH7".split(" "));
//    testRouteLess.setTimesTaken(86);
//    testRouteMost.setTimesTaken(233);
//    expectedResults.add(testRouteLess);
//    testRoutes.add(testRouteMost);
//    testRoutes.add(testRouteLess);
//    generalStatsCalculator.updateLeastTravelledRoute(testRoutes);
//    assertEquals(expectedResults, generalStatsCalculator.getLeastTravelledRoute());
//  }
//
//  /**
//   * Verify that when updateMostTravelledRoute is called when there are multiple routes in history
//   * i.e. 10 and one of them is of the least travelled, then that route is the only route added to
//   * the least travelled routes array.
//   */
//  @Test
//  public void updateLeastTravelledMostRouteTenDiffOneMostTest() {
//    ArrayList<Route> expectedResults = new ArrayList<>();
//    ArrayList<Route> testRoutes = new ArrayList<>();
//    Route testRouteLeast = new Route("2H", 1654, "GKA", 2937, "UAK", 8944, "", 0, "AN4".split(" "));
//    Route testRoute2 = new Route("2B", 5336, "BGGH", 4253, "BIAR", 6436, "", 4, "NH7".split(" "));
//    Route testRoute3 = new Route("2C", 5336, "BGGH", 4253, "BIAR", 6436, "", 4, "NH7".split(" "));
//    Route testRoute4 = new Route("2D", 2777, "GBRF", 2242, "BIAR", 6436, "", 3, "NH7".split(" "));
//    Route testRoute5 = new Route("2E", 1744, "GFRG", 2727, "BIAR", 6436, "", 1, "NH7".split(" "));
//    Route testRoute6 = new Route("2F", 2424, "SWVR", 5858, "BIAR", 6436, "", 0, "NH7".split(" "));
//    Route testRoute7 = new Route("2G", 2775, "SDD", 7557, "BIAR", 6436, "", 3, "NH7".split(" "));
//    Route testRoute8 = new Route("2H", 9898, "VSV", 5578, "BIAR", 6436, "", 4, "NH7".split(" "));
//    Route testRoute9 = new Route("2I", 2782, "SVDE", 5257, "BIAR", 6436, "", 4, "NH7".split(" "));
//    testRouteLeast.setTimesTaken(1);
//    testRoute2.setTimesTaken(2);
//    testRoute3.setTimesTaken(3);
//    testRoute4.setTimesTaken(4);
//    testRoute5.setTimesTaken(5);
//    testRoute6.setTimesTaken(6);
//    testRoute7.setTimesTaken(7);
//    testRoute8.setTimesTaken(8);
//    testRoute9.setTimesTaken(9);
//    expectedResults.add(testRouteLeast);
//    testRoutes.add(testRoute2);
//    testRoutes.add(testRoute3);
//    testRoutes.add(testRoute4);
//    testRoutes.add(testRoute5);
//    testRoutes.add(testRoute6);
//    testRoutes.add(testRoute7);
//    testRoutes.add(testRoute8);
//    testRoutes.add(testRoute9);
//    testRoutes.add(testRouteLeast);
//    generalStatsCalculator.updateLeastTravelledRoute(testRoutes);
//    assertEquals(expectedResults, generalStatsCalculator.getLeastTravelledRoute());
//  }
//
//  /**
//   * Verify that when updateLeastTravelledRoute is called when there are multiple routes in history
//   * i.e. 10 and more than one of them e.g. 3 is of the least travelled, then those routes are the
//   * only routes added to the least travelled routes array.
//   */
//  @Test
//  public void updateLeastTravelledMostRouteTenDiffThreeMostTest() {
//    ArrayList<Route> expectedResults = new ArrayList<>();
//    ArrayList<Route> testRoutes = new ArrayList<>();
//    Route testRoute1 = new Route("2A", 1654, "GKA", 2937, "UAK", 8944, "", 0, "AN4".split(" "));
//    Route testRoute2 = new Route("2B", 5336, "BGGH", 4253, "BIAR", 6436, "", 4, "NH7".split(" "));
//    Route testRoute3 = new Route("2C", 5336, "BGGH", 4253, "BIAR", 6436, "", 4, "NH7".split(" "));
//    Route testRoute4 = new Route("2D", 2777, "GBRF", 2242, "BIAR", 6436, "", 3, "NH7".split(" "));
//    Route testRoute5 = new Route("2E", 1744, "GFRG", 2727, "BIAR", 6436, "", 1, "NH7".split(" "));
//    Route testRoute6 = new Route("2F", 2424, "SWVR", 5858, "BIAR", 6436, "", 0, "NH7".split(" "));
//    Route testRoute7 = new Route("2G", 2775, "SDD", 7557, "BIAR", 6436, "", 3, "NH7".split(" "));
//    Route testRoute8 = new Route("2H", 9898, "VSV", 5578, "BIAR", 6436, "", 4, "NH7".split(" "));
//    Route testRoute9 = new Route("2I", 2782, "SVDE", 5257, "BIAR", 6436, "", 4, "NH7".split(" "));
//    testRoute1.setTimesTaken(8);
//    testRoute2.setTimesTaken(2);
//    testRoute3.setTimesTaken(9);
//    testRoute4.setTimesTaken(2);
//    testRoute5.setTimesTaken(9);
//    testRoute6.setTimesTaken(6);
//    testRoute7.setTimesTaken(7);
//    testRoute8.setTimesTaken(8);
//    testRoute9.setTimesTaken(9);
//    expectedResults.add(testRoute4);
//    expectedResults.add(testRoute2);
//    testRoutes.add(testRoute1);
//    testRoutes.add(testRoute2);
//    testRoutes.add(testRoute3);
//    testRoutes.add(testRoute4);
//    testRoutes.add(testRoute5);
//    testRoutes.add(testRoute6);
//    testRoutes.add(testRoute7);
//    testRoutes.add(testRoute8);
//    testRoutes.add(testRoute9);
//    generalStatsCalculator.updateLeastTravelledRoute(testRoutes);
//    assertEquals(expectedResults, generalStatsCalculator.getLeastTravelledRoute());
//  }
//
//  /**
//   * Verify that when updateLeastTravelledRoute is called when there are no routes in history that
//   * there are no routes in the least travelled routes array.
//   */
//  @Test
//  public void updateLeastTravelledRouteNoRoutesTest() {
//    ArrayList<Route> expectedResults = new ArrayList<>();
//    ArrayList<Route> testRoutes = new ArrayList<>();
//    generalStatsCalculator.updateLeastTravelledRoute(testRoutes);
//    assertEquals(expectedResults, generalStatsCalculator.getLeastTravelledRoute());
//  }
//
//  // --------------------------------- Testing for updateMostVisitedSrcAirport
//
//  /**
//   * Verify that when updateMostVisitedSrcAiport is called when there is one source aiport entry,
//   * then the most visit source airport is this entry
//   */
//  @Test
//  public void updateMostVisitedSrcAirportOneTest() {
//    ArrayList<String> expectedResults = new ArrayList<>();
//    String testString = "Wellington Aiport";
//    expectedResults.add(testString);
//    HashMap<String, Integer> testSrcAirports = new HashMap<>();
//    testSrcAirports.put("Wellington Aiport", 100);
//    generalStatsCalculator.updateMostVisitedSrcAirports(testSrcAirports);
//    assertEquals(expectedResults, generalStatsCalculator.getMostVisitedSrcAirports());
//  }
//
//  /**
//   * Verify that when updateMostVisitedSrcAiport is called when there are two source aiport entries,
//   * where one is most visited than the other, then the most visit source airport is the one that is
//   * most visted out of the two entries.
//   */
//  @Test
//  public void updateMostVisitedSrcAirportTwoTest() {
//    ArrayList<String> expectedResults = new ArrayList<>();
//    expectedResults.add("Wellington Aiport");
//    HashMap<String, Integer> testSrcAirports = new HashMap<>();
//    testSrcAirports.put("Wellington Aiport", 100);
//    testSrcAirports.put("Auckland Aiport", 84);
//    generalStatsCalculator.updateMostVisitedSrcAirports(testSrcAirports);
//    assertEquals(expectedResults, generalStatsCalculator.getMostVisitedSrcAirports());
//  }
//
//  /**
//   * Verify that when updateMostVisitedSrcAiport is called when there are multiple source airports
//   * in history i.e. 9 and one of them is of the most visited source airports, then this airport is
//   * the only airport added to the most visited source airports array.
//   */
//  @Test
//  public void updateMostVisitedSrcAirportTenDiffOneMostTest() {
//    ArrayList<String> expectedResults = new ArrayList<>();
//    expectedResults.add("Christchurch Aiport");
//    HashMap<String, Integer> testSrcAirports = new HashMap<>();
//    testSrcAirports.put("Wellington Aiport", 100);
//    testSrcAirports.put("Auckland Aiport", 84);
//    testSrcAirports.put("Christchurch Aiport", 147);
//    testSrcAirports.put("Queenstown Aiport", 44);
//    testSrcAirports.put("Nelson Aiport", 44);
//    testSrcAirports.put("Hamilton Aiport", 14);
//    testSrcAirports.put("Rotorua Aiport", 57);
//    testSrcAirports.put("Hawke's Bay Aiport", 45);
//    testSrcAirports.put("Palmerston North Aiport", 4);
//    generalStatsCalculator.updateMostVisitedSrcAirports(testSrcAirports);
//    assertEquals(expectedResults, generalStatsCalculator.getMostVisitedSrcAirports());
//  }
//
//  /**
//   * Verify that when updateMostVisitedSrcAiport is called when there are multiple source airports
//   * in history i.e. 9 and more than one of them e.g. 3 are of the most visited source airports,
//   * then those airports are the only airports added to the most visited source airports array.
//   */
//  @Test
//  public void updateMostVisitedSrcAirportTenDiffThreeMostTest() {
//    ArrayList<String> expectedResults = new ArrayList<>();
//    expectedResults.add("Wellington Aiport");
//    expectedResults.add("Hamilton Aiport");
//    expectedResults.add("Auckland Aiport");
//    HashMap<String, Integer> testSrcAirports = new HashMap<>();
//    testSrcAirports.put("Wellington Aiport", 251);
//    testSrcAirports.put("Auckland Aiport", 251);
//    testSrcAirports.put("Christchurch Aiport", 147);
//    testSrcAirports.put("Queenstown Aiport", 44);
//    testSrcAirports.put("Nelson Aiport", 44);
//    testSrcAirports.put("Hamilton Aiport", 251);
//    testSrcAirports.put("Rotorua Aiport", 57);
//    testSrcAirports.put("Hawke's Bay Aiport", 45);
//    testSrcAirports.put("Palmerston North Aiport", 4);
//    generalStatsCalculator.updateMostVisitedSrcAirports(testSrcAirports);
//    assertEquals(expectedResults, generalStatsCalculator.getMostVisitedSrcAirports());
//  }
//
//  /**
//   * Verify that when updateMostVisitedSrcAiport is called when there are no source aiport entries,
//   * then the most visited source airport array should be empty.
//   */
//  @Test
//  public void updateMostVisitedSrcAirportNoRoutesTest() {
//    ArrayList<String> expectedResults = new ArrayList<>();
//    HashMap<String, Integer> testSrcAirports = new HashMap<>();
//    generalStatsCalculator.updateMostVisitedSrcAirports(testSrcAirports);
//    assertEquals(expectedResults, generalStatsCalculator.getMostVisitedSrcAirports());
//  }
//
//  // --------------------------------- Testing for updateMostVisitedDestAirport
//
//  /**
//   * Verify that when updateMostVisitedDestAiport is called when there is one destionation aiport
//   * entry, then the most visit destination airport is this entry.
//   */
//  @Test
//  public void updateMostVisitedDestAirportOneTest() {
//    ArrayList<String> expectedResults = new ArrayList<>();
//    String testString = "Wellington Aiport";
//    expectedResults.add(testString);
//    HashMap<String, Integer> testDestAirports = new HashMap<>();
//    testDestAirports.put("Wellington Aiport", 100);
//    generalStatsCalculator.updateMostVisitedDestAirports(testDestAirports);
//    assertEquals(expectedResults, generalStatsCalculator.getMostVisitedDestAirports());
//  }
//
//  /**
//   * Verify that when updateMostVisitedDestAiport is called when there are two destination aiport
//   * entries, where one is most visited than the other, then the most visit destination airport is
//   * the one that is most visted out of the two entries.
//   */
//  @Test
//  public void updateMostVisitedDestAirportTwoTest() {
//    ArrayList<String> expectedResults = new ArrayList<>();
//    expectedResults.add("Wellington Aiport");
//    HashMap<String, Integer> testDestAirports = new HashMap<>();
//    testDestAirports.put("Wellington Aiport", 100);
//    testDestAirports.put("Auckland Aiport", 84);
//    generalStatsCalculator.updateMostVisitedDestAirports(testDestAirports);
//    assertEquals(expectedResults, generalStatsCalculator.getMostVisitedDestAirports());
//  }
//
//  /**
//   * Verify that when updateMostVisitedDestAiport is called when there are multiple destination
//   * airports in history i.e. 9 and one of them is of the most visited destination airports, then
//   * this airport is the only airport added to the most visited destination airports array.
//   */
//  @Test
//  public void updateMostVisitedDestAirportTenTest() {
//    ArrayList<String> expectedResults = new ArrayList<>();
//    expectedResults.add("Christchurch Aiport");
//    HashMap<String, Integer> testDestAirports = new HashMap<>();
//    testDestAirports.put("Wellington Aiport", 100);
//    testDestAirports.put("Auckland Aiport", 84);
//    testDestAirports.put("Christchurch Aiport", 147);
//    testDestAirports.put("Queenstown Aiport", 44);
//    testDestAirports.put("Nelson Aiport", 44);
//    testDestAirports.put("Hamilton Aiport", 14);
//    testDestAirports.put("Rotorua Aiport", 57);
//    testDestAirports.put("Hawke's Bay Aiport", 45);
//    testDestAirports.put("Palmerston North Aiport", 4);
//    generalStatsCalculator.updateMostVisitedDestAirports(testDestAirports);
//    assertEquals(expectedResults, generalStatsCalculator.getMostVisitedDestAirports());
//  }
//
//  /**
//   * Verify that when updateMostVisitedDestAiport is called when there are multiple destination
//   * airports in history i.e. 9 and more than one of them e.g. 3 are of the most visited destination
//   * airports, then those airports are the only airports added to the most visited destination
//   * airports array.
//   */
//  @Test
//  public void updateMostVisitedDestAirportTenDiffThreeMostTest() {
//    ArrayList<String> expectedResults = new ArrayList<>();
//    expectedResults.add("Wellington Aiport");
//    expectedResults.add("Hamilton Aiport");
//    expectedResults.add("Auckland Aiport");
//    HashMap<String, Integer> testSrcAirports = new HashMap<>();
//    testSrcAirports.put("Wellington Aiport", 251);
//    testSrcAirports.put("Auckland Aiport", 251);
//    testSrcAirports.put("Christchurch Aiport", 147);
//    testSrcAirports.put("Queenstown Aiport", 44);
//    testSrcAirports.put("Nelson Aiport", 44);
//    testSrcAirports.put("Hamilton Aiport", 251);
//    testSrcAirports.put("Rotorua Aiport", 57);
//    testSrcAirports.put("Hawke's Bay Aiport", 45);
//    testSrcAirports.put("Palmerston North Aiport", 4);
//    generalStatsCalculator.updateMostVisitedDestAirports(testSrcAirports);
//    assertEquals(expectedResults, generalStatsCalculator.getMostVisitedDestAirports());
//  }
//
//  /**
//   * Verify that when updateMostVisitedDestAiport is called when there are no destination aiport
//   * entries, then the most visited destination airport array should be empty.
//   */
//  @Test
//  public void updateMostVisitedDestAirportNoRoutesTest() {
//    ArrayList<String> expectedResults = new ArrayList<>();
//    HashMap<String, Integer> testSrcAirports = new HashMap<>();
//    generalStatsCalculator.updateMostVisitedDestAirports(testSrcAirports);
//    assertEquals(expectedResults, generalStatsCalculator.getMostVisitedDestAirports());
//  }
//
//  // --------------------------------- Testing for calculateCO2ReductionNeeded
//
//  //  @Test
//  //  public void calculateCO2ReductionNeededSmallTest() {
//  //    reportGenerator.setCarbonEmissionsGoal(200000);
//  //    reportGenerator.setTotalCarbonEmissions(2000);
//  //    int expectedReduction = 198000;
//  //    assertEquals(expectedReduction, reportGenerator.getHowMuchToReduceCO2By(), 0.00001);
//  //
//  //  }
//
//  /** */
//  @Test
//  public void calculateCO2ReductionNeededLargeTest() {}
//
//  /** */
//  @Test
//  public void calculateCO2ReductionNeededNegTest() {}
//
//  // --------------------------------- Testing for calculateOffsetTrees
//
//  /** */
//  @Test
//  public void calculateOffsetTreesSmallTest() {}
//
//  /** */
//  @Test
//  public void calculateOffsetTreesLargeTest() {}
//
//  /** */
//  @Test
//  public void calculateOffsetTreesNegTest() {}
//}

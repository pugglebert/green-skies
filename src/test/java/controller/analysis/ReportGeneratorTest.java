package controller.analysis;

import model.data.*;
import model.loader.Loader;
import org.junit.Before;
import org.junit.Test;
import java.io.FileNotFoundException;
import java.nio.file.FileSystemException;
import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Unit test for ReportGenrator class.
 *
 * @author Hayley Krippner
 * @since 15/09/2020
 * @version 1.0
 */
public class ReportGeneratorTest {

  private Storage storage;
  private Loader loader;
  private ReportGenerator reportGenerator;

  @Before
  public void setUp() {
    storage = new Storage();
    loader = new Loader(storage);
    reportGenerator = new ReportGenerator();
    try {
      loader.loadFile("../seng202_project/src/test/java/TestFiles/GenerateReportTest.csv", "Route");
    } catch (FileSystemException
        | FileNotFoundException
        | SQLException
        | ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  // ------------------------------------------- Testing for updateTotalEmissions
  // ---------------------------------------

  /**
   * Verify that when updateTotalEmissions is called with a route with a large amount of carbon
   * emissions that the total emissions is updated accordingly.The carbon emissions are starting at
   * 0.0 g.
   */
  @Test
  public void updateTotalEmissionsLargeEmissionsInitiallyZeroTest() {
    reportGenerator.setTotalCarbonEmissions(0.0);
    Route testRoute = new Route("2W", 410, "SVX", 2975, "OVC", 4078, "", 0, "CR2".split(" "));
    testRoute.setEmissions(20000000.50);
    testRoute.setTimesTaken(1);
    reportGenerator.updateTotalEmissions(testRoute);
    assertEquals(20000000.50, reportGenerator.getTotalCarbonEmissions(), 0.0001);
  }

  /**
   * Verify that when updateTotalEmissions is called with a route with a small amount of carbon
   * emissions that the total emissions is updated accordingly.The carbon emissions are starting at
   * 0.0 g.
   */
  @Test
  public void updateTotalEmissionsSmallEmissionsInitiallyZeroTest() {
    reportGenerator.setTotalCarbonEmissions(0.0);
    Route testRoute = new Route("2W", 410, "SVX", 2975, "OVC", 4078, "", 0, "CR2".split(" "));
    testRoute.setEmissions(70000.0);
    testRoute.setTimesTaken(1);
    reportGenerator.updateTotalEmissions(testRoute);
    assertEquals(70000.0, reportGenerator.getTotalCarbonEmissions(), 0.0001);
  }

  /**
   * Verify that when updateTotalEmissions is called with a route with a zero carbon emissions that
   * the total emissions is updated accordingly.The carbon emissions are starting at large amount.
   */
  @Test
  public void updateTotalEmissionsZeroEmissionsInitiallyZeroTest() {
    reportGenerator.setTotalCarbonEmissions(0.0);
    Route testRoute = new Route("2W", 410, "SVX", 2975, "OVC", 4078, "", 0, "CR2".split(" "));
    testRoute.setEmissions(0.0);
    testRoute.setTimesTaken(1);
    reportGenerator.updateTotalEmissions(testRoute);
    assertEquals(0.0, reportGenerator.getTotalCarbonEmissions(), 0.0001);
  }

  /**
   * Verify that when updateTotalEmissions is called with a route with a large amount of carbon
   * emissions that the total emissions is updated accordingly.The carbon emissions are starting at
   * large amount.
   */
  @Test
  public void updateTotalEmissionsLargeEmissionsInitiallyLargeTest() {
    reportGenerator.setTotalCarbonEmissions(80000000000.10);
    Route testRoute = new Route("2W", 410, "SVX", 2975, "OVC", 4078, "", 0, "CR2".split(" "));
    testRoute.setEmissions(1700000.20);
    testRoute.setTimesTaken(1);
    reportGenerator.updateTotalEmissions(testRoute);
    assertEquals(80001700000.30, reportGenerator.getTotalCarbonEmissions(), 0.0001);
  }

  /**
   * Verify that when updateTotalEmissions is called with a route with a small amount of carbon
   * emissions that the total emissions is updated accordingly.The carbon emissions are starting at
   * large amount.
   */
  @Test
  public void updateTotalEmissionsSmallEmissionsInitiallyLargeTest() {
    reportGenerator.setTotalCarbonEmissions(90000000000.10);
    Route testRoute = new Route("2W", 410, "SVX", 2975, "OVC", 4078, "", 0, "CR2".split(" "));
    testRoute.setEmissions(4500.08);
    testRoute.setTimesTaken(1);
    reportGenerator.updateTotalEmissions(testRoute);
    assertEquals(90000004500.18, reportGenerator.getTotalCarbonEmissions(), 0.0001);
  }

  /**
   * Verify that when updateTotalEmissions is called with a route with a zero carbon emissions that
   * the total emissions is updated accordingly.The carbon emissions are starting at large amount.
   */
  @Test
  public void updateTotalEmissionsZeroEmissionsInitiallyLargeTest() {
    reportGenerator.setTotalCarbonEmissions(345231863432.98);
    Route testRoute = new Route("2W", 410, "SVX", 2975, "OVC", 4078, "", 0, "CR2".split(" "));
    testRoute.setEmissions(0.0);
    testRoute.setTimesTaken(1);
    reportGenerator.updateTotalEmissions(testRoute);
    assertEquals(345231863432.98, reportGenerator.getTotalCarbonEmissions(), 0.0001);
  }

  /**
   * Verify that when updateTotalEmissions is called with a route with a large amount of carbon
   * emissions, which has been taken multipe times, that the total emissions is updated
   * accordingly.The carbon emissions are starting at 0.0 g.
   */
  @Test
  public void updateTotalEmissionsLargeEmissionsZeroTakenTest() {
    reportGenerator.setTotalCarbonEmissions(0.0);
    Route testRoute = new Route("2W", 410, "SVX", 2975, "OVC", 4078, "", 0, "CR2".split(" "));
    testRoute.setEmissions(20000000.50);
    testRoute.setTimesTaken(0);
    reportGenerator.updateTotalEmissions(testRoute);
    assertEquals(0.0, reportGenerator.getTotalCarbonEmissions(), 0.0001);
  }

  /**
   * Verify that when updateTotalEmissions is called with a route with a small amount of carbon
   * emissions, which has been taken zero times, that the total emissions is not updated. The carbon
   * emissions are starting at 0.0 g.
   */
  @Test
  public void updateTotalEmissionsSmallEmissionsManyTakenTest() {
    reportGenerator.setTotalCarbonEmissions(0.0);
    Route testRoute = new Route("2W", 410, "SVX", 2975, "OVC", 4078, "", 0, "CR2".split(" "));
    testRoute.setEmissions(8760.0);
    testRoute.setTimesTaken(1000);
    reportGenerator.updateTotalEmissions(testRoute);
    assertEquals(8760000.0, reportGenerator.getTotalCarbonEmissions(), 0.0001);
  }

  // ------------------------------------------- Testing for updateTotalDistance
  // ---------------------------------------

  /**
   * Verify that when updateTotalDistance is called with a route that is of large distance that the
   * total distance is updated accordingly.The total distance is started as 0.0 km.
   */
  @Test
  public void updateTotalDistanceLargeDistanceInitiallyZeroTest() {
    reportGenerator.setTotalDistanceTravelled(0.0);
    Route testRoute = new Route("7K", 392, "PKL", 3920, "MDC", 2523, "", 2, "PDS".split(" "));
    testRoute.setDistance(4157987.41);
    reportGenerator.updateTotalDistance(testRoute);
    assertEquals(4157987.41, reportGenerator.getTotalDistanceTravelled(), 0.0001);
  }

  /**
   * Verify that when updateTotalDistance is called with a route that is of small distance that the
   * total * distance is updated accordingly.The total distance is started as 0.0 km.
   */
  @Test
  public void updateTotalDistanceSmallDistanceInitiallyZeroTest() {
    reportGenerator.setTotalDistanceTravelled(0.0);
    Route testRoute = new Route("7K", 392, "PKL", 3920, "MDC", 2523, "", 2, "PDS".split(" "));
    testRoute.setDistance(3902.79);
    reportGenerator.updateTotalDistance(testRoute);
    assertEquals(3902.79, reportGenerator.getTotalDistanceTravelled(), 0.0001);
  }

  /**
   * Verify that when updateTotalDistance is called with a route with zero distance that the total
   * emissions is updated accordingly. The total distance is started as 0.0km.
   */
  @Test
  public void updateTotalDistanceZeroDistanceInitiallyZeroTest() {
    reportGenerator.setTotalCarbonEmissions(0.0);
    Route testRoute = new Route("7K", 392, "PKL", 3920, "MDC", 2523, "", 2, "PDS".split(" "));
    testRoute.setDistance(0.0);
    reportGenerator.updateTotalDistance(testRoute);
    assertEquals(0.0, reportGenerator.getTotalDistanceTravelled(), 0.0001);
  }

  /**
   * Verify that when updateTotalDistance is called with a route that is of large distance that the
   * total distance is updated accordingly. The total distance is started at a large distance in km.
   */
  @Test
  public void updateTotalDistanceLargeDistanceInitiallyLargeTest() {
    reportGenerator.setTotalDistanceTravelled(58900000000000.02);
    Route testRoute = new Route("7K", 392, "PKL", 3920, "MDC", 2523, "", 2, "PDS".split(" "));
    testRoute.setDistance(46372.91);
    reportGenerator.updateTotalDistance(testRoute);
    assertEquals(58900000046372.93, reportGenerator.getTotalDistanceTravelled(), 0.0001);
  }

  /**
   * Verify that when updateTotalDistance is called with a route that is of small distance that the
   * total distance is updated accordingly. The total distance is started at a large distance in km.
   */
  @Test
  public void updateTotalDistanceSmallDistanceInitiallyLargeTest() {
    reportGenerator.setTotalDistanceTravelled(67554440900000.07);
    Route testRoute = new Route("7K", 392, "PKL", 3920, "MDC", 2523, "", 2, "PDS".split(" "));
    testRoute.setDistance(4157.41);
    reportGenerator.updateTotalDistance(testRoute);
    assertEquals(67554440904157.48, reportGenerator.getTotalDistanceTravelled(), 0.0001);
  }

  /**
   * Verify that when updateTotalDistance is called with a route that is of zero distance that the
   * total distance is updated accordingly. The total distance is started at a large distance in km.
   */
  @Test
  public void updateTotalDistanceZeroDistanceInitiallyLargeTest() {
    reportGenerator.setTotalDistanceTravelled(345231863432.98);
    Route testRoute = new Route("7K", 392, "PKL", 3920, "MDC", 2523, "", 2, "PDS".split(" "));
    testRoute.setDistance(0.0);
    reportGenerator.updateTotalDistance(testRoute);
    assertEquals(345231863432.98, reportGenerator.getTotalDistanceTravelled(), 0.0001);
  }

  // --------------------------------- Testing for updateMostEmissionsRoute
  // ---------------------------------

  /**
   * Verify that when updateMostEmissionsRoute is called with a route and there are no routes in the
   * history, then the route with the most emissions is the route that was added.
   */
  @Test
  public void updateMostEmissionsRouteFirstEntryTest() {
    ArrayList<Route> expectedResults = new ArrayList<>();
    Route testRoute = new Route("2H", 1654, "IKT", 2937, "ODO", 8944, "", 0, "AN4".split(" "));
    expectedResults.add(testRoute);
    reportGenerator.updateMostEmissionsRoute(testRoute);
    assertEquals(expectedResults, reportGenerator.getMostEmissionsRoutes());
  }

  //TODO: rebug this! The carbon emissions of for airline 2B are NaN
  /**
   * Verify that when updateMostEmissionsRoute is called with a route and it has more emissions than
   * the current most emissions route, then the most emissions route in the history is updated to
   * this route.
   */
  @Test
  public void updateMostEmissionsRouteMoreEmissionsEntryTest()
      throws SQLException, ClassNotFoundException {
    ArrayList<Route> expectedResults = new ArrayList<>();
    List<DataType> providedAirports = new ArrayList<>();
    providedAirports.add(
        new Airport(
            2937,
            "Goroka",
            "Goroka",
            "Papua New Guinea",
            "GKA",
            "AYGA",
            -6.081689,
            145.391881,
            5282,
            10,
            "U",
            "Pacific/Port_Moresby"));
    providedAirports.add(
        new Airport(
            8944,
            "Narsarsuaq",
            "Narssarssuaq",
            "Greenland",
            "UAK",
            "BGBW",
            61.160517,
            -45.425978,
            112,
            -3,
            "E",
            "America/Godthab"));
    providedAirports.add(
        new Airport(
            4253,
            "Nuuk",
            "Godthaab",
            "Greenland",
            "GOH",
            "BGGH",
            64.190922,
            -51.678064,
            283,
            -3,
            "E",
            "America/Godthab"));
    providedAirports.add(
        new Airport(
            6436,
            "Akureyri",
            "Akureyri",
            "Iceland",
            "AEY",
            "BIAR",
            65.659994,
            -18.072703,
            6,
            0,
            "N",
            "Atlantic/Reykjavik"));

    storage.setData(providedAirports, "Airport");
    Route testRouteMoreEmissions =
        new Route("2H", 1654, "GKA", 2937, "UAK", 8944, "", 0, "AN4".split(" "));
    Route testRouteLessEmissions =
        new Route("2B", 5336, "BGGH", 4253, "BIAR", 6436, "", 4, "NH7".split(" "));
    FlightAnalyser flightAnalyser =
        new FlightAnalyser(testRouteLessEmissions, testRouteMoreEmissions, storage);
    testRouteMoreEmissions.setEmissions(flightAnalyser.getPath2Emission()); // 12600 km
    testRouteLessEmissions.setEmissions(flightAnalyser.getPath1Emission()); // 163 km
    expectedResults.add(testRouteMoreEmissions);
    reportGenerator.updateMostEmissionsRoute(testRouteLessEmissions);
    reportGenerator.updateMostEmissionsRoute(testRouteMoreEmissions);
    assertEquals(expectedResults, reportGenerator.getMostEmissionsRoutes());
  }

  //TODO: check this! The carbon emissions of for airline 2B are NaN
  /**
   * Verify that when updateMostEmissionsRoute is called with a route and it has less emissions than
   * the current most emissions route, then the most emissions route in the history is not changed to this added route.
   */
  @Test
  public void updateMostEmissionsRouteLessEmissionsEntryTest()
          throws SQLException, ClassNotFoundException {
    ArrayList<Route> expectedResults = new ArrayList<>();
    List<DataType> providedAirports = new ArrayList<>();
    providedAirports.add(
            new Airport(
                    2937,
                    "Goroka",
                    "Goroka",
                    "Papua New Guinea",
                    "GKA",
                    "AYGA",
                    -6.081689,
                    145.391881,
                    5282,
                    10,
                    "U",
                    "Pacific/Port_Moresby"));
    providedAirports.add(
            new Airport(
                    8944,
                    "Narsarsuaq",
                    "Narssarssuaq",
                    "Greenland",
                    "UAK",
                    "BGBW",
                    61.160517,
                    -45.425978,
                    112,
                    -3,
                    "E",
                    "America/Godthab"));
    providedAirports.add(
            new Airport(
                    4253,
                    "Nuuk",
                    "Godthaab",
                    "Greenland",
                    "GOH",
                    "BGGH",
                    64.190922,
                    -51.678064,
                    283,
                    -3,
                    "E",
                    "America/Godthab"));
    providedAirports.add(
            new Airport(
                    6436,
                    "Akureyri",
                    "Akureyri",
                    "Iceland",
                    "AEY",
                    "BIAR",
                    65.659994,
                    -18.072703,
                    6,
                    0,
                    "N",
                    "Atlantic/Reykjavik"));

    storage.setData(providedAirports, "Airport");
    Route testRouteMoreEmissions =
            new Route("2H", 1654, "GKA", 2937, "UAK", 8944, "", 0, "AN4".split(" "));
    Route testRouteLessEmissions =
            new Route("2B", 5336, "BGGH", 4253, "BIAR", 6436, "", 4, "NH7".split(" "));
    FlightAnalyser flightAnalyser =
            new FlightAnalyser(testRouteLessEmissions, testRouteMoreEmissions, storage);
    testRouteMoreEmissions.setEmissions(flightAnalyser.getPath2Emission()); // 12600 km
    testRouteLessEmissions.setEmissions(flightAnalyser.getPath1Emission()); // 163 km
    expectedResults.add(testRouteMoreEmissions);
    reportGenerator.updateMostEmissionsRoute(testRouteMoreEmissions);
    reportGenerator.updateMostEmissionsRoute(testRouteLessEmissions);
    assertEquals(expectedResults, reportGenerator.getMostEmissionsRoutes());
  }

  // --------------------------------- Testing for updateLeastEmissionsRoute
  // ---------------------------------

  /**
   * Verify that when updateLeastEmissionsRoute is called with a route and there are no routes in
   * the history, then the route with the least emissions is the route that was added.
   */
  @Test
  public void updateLeastEmissionsRouteFirstEntryTest() {
    ArrayList<Route> expectedResults = new ArrayList<>();
    Route testRoute = new Route("2H", 1654, "IKT", 2937, "ODO", 8944, "", 0, "AN4".split(" "));
    expectedResults.add(testRoute);
    reportGenerator.updateLeastEmissionsRoute(testRoute);
    assertEquals(expectedResults, reportGenerator.getLeastEmissionsRoutes());
  }

  // --------------------------------- Testing for updateMostDistanceRoute
  // ---------------------------------

  /**
   * Verify that when updateMostDistanceRoute is called with a route and there are no routes in the
   * history, then the route of the most distance is the route that was added.
   */
  @Test
  public void updateMostDistanceRouteFirstEntryTest() {
    ArrayList<Route> expectedResults = new ArrayList<>();
    Route testRoute = new Route("2H", 1654, "IKT", 2937, "ODO", 8944, "", 0, "AN4".split(" "));
    expectedResults.add(testRoute);
    reportGenerator.updateMostDistanceRoute(testRoute);
    assertEquals(expectedResults, reportGenerator.getMostDistanceRoutes());
  }

  // --------------------------------- Testing for updateLeastDistanceRoute
  // ---------------------------------

  /**
   * Verify that when updateLeastDistanceRoute is called with a route and there are no routes in the
   * history, then the route of the least distance is the route that was added.
   */
  @Test
  public void updateLeastDistanceRouteFirstEntryTest() {
    ArrayList<Route> expectedResults = new ArrayList<>();
    Route testRoute = new Route("2H", 1654, "IKT", 2937, "ODO", 8944, "", 0, "AN4".split(" "));
    expectedResults.add(testRoute);
    reportGenerator.updateLeastDistanceRoute(testRoute);
    assertEquals(expectedResults, reportGenerator.getLeastDistanceRoutes());
  }
}

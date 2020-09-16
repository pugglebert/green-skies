package controller.analysis;

import model.data.*;
import model.loader.Loader;
import org.junit.Before;
import org.junit.Test;
import java.io.FileNotFoundException;
import java.nio.file.FileSystemException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Unit test for ReportGenrator class.
 *
 * @author Hayley Krippner
 * @since 16/09/2020
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

  // --------------------------------------- Testing for updateTotalEmissions

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

  // ------------------------------------- Testing for updateTotalDistance

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

  /**
   * Verify that when updateMostEmissionsRoute is called with a route and there are no routes in the
   * history, then the route with the most emissions is the route that was added.
   */
  @Test
  public void updateMostEmissionsRouteFirstEntryTest() {
    ArrayList<Route> expectedResults = new ArrayList<>();
    Route testRoute = new Route("2H", 1654, "IKT", 2937, "ODO", 8944, "", 0, "AN4".split(" "));
    expectedResults.add(testRoute);
    testRoute.setEmissions(10000);
    reportGenerator.updateMostEmissionsRoute(testRoute);
    assertEquals(expectedResults, reportGenerator.getMostEmissionsRoutes());
  }

  /**
   * Verify that when updateMostEmissionsRoute is called with a route and it has more emissions,
   * then the most emissions route in the history is set to the added route.
   */
  @Test
  public void updateMostEmissionsRouteAddMoreEmissionsEntryTest() {
    ArrayList<Route> expectedResults = new ArrayList<>();
    Route testRouteMoreEmissions =
        new Route("2H", 1654, "GKA", 2937, "UAK", 8944, "", 0, "AN4".split(" "));
    Route testRouteLessEmissions =
        new Route("2B", 5336, "BGGH", 4253, "BIAR", 6436, "", 4, "NH7".split(" "));
    testRouteMoreEmissions.setEmissions(12600);
    testRouteLessEmissions.setEmissions(163);
    expectedResults.add(testRouteMoreEmissions);
    reportGenerator.updateMostEmissionsRoute(testRouteLessEmissions);
    reportGenerator.updateMostEmissionsRoute(testRouteMoreEmissions);
    assertEquals(expectedResults, reportGenerator.getMostEmissionsRoutes());
  }

  /**
   * Verify that when updateMostEmissionsRoute is called with a route and it has less emissions,
   * then the most emissions route in the history is remains the same.
   */
  @Test
  public void updateMostEmissionsRouteAddLessEmissionsEntryTest() {
    ArrayList<Route> expectedResults = new ArrayList<>();
    Route testRouteMoreEmissions =
        new Route("2H", 1654, "GKA", 2937, "UAK", 8944, "", 0, "AN4".split(" "));
    Route testRouteLessEmissions =
        new Route("2B", 5336, "BGGH", 4253, "BIAR", 6436, "", 4, "NH7".split(" "));
    testRouteMoreEmissions.setEmissions(12600);
    testRouteLessEmissions.setEmissions(163);
    expectedResults.add(testRouteMoreEmissions);
    reportGenerator.updateMostEmissionsRoute(testRouteMoreEmissions);
    reportGenerator.updateMostEmissionsRoute(testRouteLessEmissions);
    assertEquals(expectedResults, reportGenerator.getMostEmissionsRoutes());
  }

  /**
   * Verify that when updateMostEmissionsRoute is called with a route and it is already in the most
   * emissions routes, then the most emissions route in the history remains the same.
   */
  @Test
  public void updateMostEmissionsRouteAddSameEmissionsSameRouteEntryTest() {
    ArrayList<Route> expectedResults = new ArrayList<>();
    Route testRouteEmissions =
        new Route("2H", 1654, "GKA", 2937, "UAK", 8944, "", 0, "AN4".split(" "));
    testRouteEmissions.setEmissions(20000);
    expectedResults.add(testRouteEmissions);
    reportGenerator.updateMostEmissionsRoute(testRouteEmissions);
    reportGenerator.updateMostEmissionsRoute(testRouteEmissions);
    assertEquals(expectedResults, reportGenerator.getMostEmissionsRoutes());
  }

  /**
   * Verify that when updateMostEmissionsRoute is called with a route and it has the same most
   * emissions but isn't in the most emissions routes array, then the route is added to the most
   * emissions route array.
   */
  @Test
  public void updateMostEmissionsRouteAddSameEmissionsDiffRouteEntryTest() {
    ArrayList<Route> expectedResults = new ArrayList<>();
    Route testRouteEmissions =
        new Route("2H", 1654, "GKA", 2937, "UAK", 8944, "", 0, "AN4".split(" "));
    Route testRouteDiffEmissions =
        new Route("2B", 5336, "BGGH", 4253, "BIAR", 6436, "", 4, "NH7".split(" "));
    testRouteEmissions.setEmissions(20000); // 12600 km
    testRouteDiffEmissions.setEmissions(20000); // 12600 km
    expectedResults.add(testRouteEmissions);
    expectedResults.add(testRouteDiffEmissions);
    reportGenerator.updateMostEmissionsRoute(testRouteEmissions);
    reportGenerator.updateMostEmissionsRoute(testRouteDiffEmissions);
    assertEquals(expectedResults, reportGenerator.getMostEmissionsRoutes());
  }

  /**
   * Verify that when updateMostEmissionsRoute is called with a route and it has NaN emissions then
   * the most emissions route in the history is not changed to this added route. This test also uses
   * the FlightAnalyser class to check it works as expected here.
   */
  @Test
  public void updateMostEmissionsRouteAddNaNEmissionsEntryUseFlighAnalyserTest()
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
    Route testRouteEmissions =
        new Route("2H", 1654, "GKA", 2937, "UAK", 8944, "", 0, "AN4".split(" "));
    Route testRouteNaNEmissions =
        new Route("2B", 5336, "BGGH", 4253, "BIAR", 6436, "", 4, "NH7".split(" "));
    FlightAnalyser flightAnalyser =
        new FlightAnalyser(testRouteNaNEmissions, testRouteEmissions, storage);
    testRouteEmissions.setEmissions(flightAnalyser.getPath2Emission()); // 12600 km
    testRouteNaNEmissions.setEmissions(flightAnalyser.getPath1Emission()); // NaN
    expectedResults.add(testRouteEmissions);
    reportGenerator.updateMostEmissionsRoute(testRouteEmissions);
    reportGenerator.updateMostEmissionsRoute(testRouteNaNEmissions);
    assertEquals(expectedResults, reportGenerator.getMostEmissionsRoutes());
  }

  /**
   * Verify that when updateMostEmissionsRoute is called with a route that has NaN emissions and
   * then with a route that has emissions that the NaN route gets ignored and the most emissions
   * route is the second route (not NaN emissions).
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
    Route testRouteEmissions =
        new Route("2H", 1654, "GKA", 2937, "UAK", 8944, "", 0, "AN4".split(" "));
    Route testRouteNaNEmissions =
        new Route("2B", 5336, "BGGH", 4253, "BIAR", 6436, "", 4, "NH7".split(" "));
    FlightAnalyser flightAnalyser =
        new FlightAnalyser(testRouteNaNEmissions, testRouteEmissions, storage);
    testRouteEmissions.setEmissions(flightAnalyser.getPath2Emission()); // 12600 km
    testRouteNaNEmissions.setEmissions(flightAnalyser.getPath1Emission()); // NaN
    expectedResults.add(testRouteEmissions);
    reportGenerator.updateMostEmissionsRoute(testRouteNaNEmissions);
    reportGenerator.updateMostEmissionsRoute(testRouteEmissions);
    assertEquals(expectedResults, reportGenerator.getMostEmissionsRoutes());
  }

  // --------------------------------- Testing for updateLeastEmissionsRoute

  /**
   * Verify that when updateLeastEmissionsRoute is called with a route and there are no routes in
   * the history, then the route with the least emissions is the route that was added.
   */
  @Test
  public void updateLeastEmissionsRouteFirstEntryTest() {
    ArrayList<Route> expectedResults = new ArrayList<>();
    Route testRoute = new Route("2H", 1654, "IKT", 2937, "ODO", 8944, "", 0, "AN4".split(" "));
    expectedResults.add(testRoute);
    testRoute.setEmissions(3425353);
    reportGenerator.updateLeastEmissionsRoute(testRoute);
    assertEquals(expectedResults, reportGenerator.getLeastEmissionsRoutes());
  }

  /**
   * Verify that when updateLeastEmissionsRoute is called with a route and it has more emissions,
   * then the less emissions route in the history is not changed.
   */
  @Test
  public void updateLeastEmissionsRouteAddMoreEmissionsEntryTest() {
    ArrayList<Route> expectedResults = new ArrayList<>();
    Route testRouteMoreEmissions =
        new Route("2H", 1654, "GKA", 2937, "UAK", 8944, "", 0, "AN4".split(" "));
    Route testRouteLessEmissions =
        new Route("2B", 5336, "BGGH", 4253, "BIAR", 6436, "", 4, "NH7".split(" "));
    testRouteMoreEmissions.setEmissions(12600);
    testRouteLessEmissions.setEmissions(163);
    expectedResults.add(testRouteLessEmissions);
    reportGenerator.updateLeastEmissionsRoute(testRouteLessEmissions);
    reportGenerator.updateLeastEmissionsRoute(testRouteMoreEmissions);
    assertEquals(expectedResults, reportGenerator.getLeastEmissionsRoutes());
  }

  /**
   * Verify that when updateLeastEmissionsRoute is called with a route and it has less emissions,
   * then the less emissions route in the history is updated to the added route.
   */
  @Test
  public void updateLeastEmissionsRouteAddLessEmissionsEntryTest() {
    ArrayList<Route> expectedResults = new ArrayList<>();
    Route testRouteMoreEmissions =
        new Route("2H", 1654, "GKA", 2937, "UAK", 8944, "", 0, "AN4".split(" "));
    Route testRouteLessEmissions =
        new Route("2B", 5336, "BGGH", 4253, "BIAR", 6436, "", 4, "NH7".split(" "));
    testRouteMoreEmissions.setEmissions(12600);
    testRouteLessEmissions.setEmissions(163);
    expectedResults.add(testRouteLessEmissions);
    reportGenerator.updateLeastEmissionsRoute(testRouteMoreEmissions);
    reportGenerator.updateLeastEmissionsRoute(testRouteLessEmissions);
    assertEquals(expectedResults, reportGenerator.getLeastEmissionsRoutes());
  }

  /**
   * Verify that when updateLeastEmissionsRoute is called with a route and it is already in the
   * least emissions routes, then the least emissions route in the history remains the same.
   */
  @Test
  public void updateLeastEmissionsRouteAddSameEmissionsSameRouteEntryTest() {
    ArrayList<Route> expectedResults = new ArrayList<>();
    Route testRouteEmissions =
        new Route("2H", 1654, "GKA", 2937, "UAK", 8944, "", 0, "AN4".split(" "));
    testRouteEmissions.setEmissions(20000);
    expectedResults.add(testRouteEmissions);
    reportGenerator.updateLeastEmissionsRoute(testRouteEmissions);
    reportGenerator.updateLeastEmissionsRoute(testRouteEmissions);
    assertEquals(expectedResults, reportGenerator.getLeastEmissionsRoutes());
  }

  /**
   * Verify that when updateLeastEmissionsRoute is called with a route and it has the same most
   * emissions but isn't in the most emissions routes array, then the route is added to the most
   * emissions route array.
   */
  @Test
  public void updateLeastEmissionsRouteAddSameEmissionsDiffRouteEntryTest() {
    ArrayList<Route> expectedResults = new ArrayList<>();
    Route testRouteEmissions =
        new Route("2H", 1654, "GKA", 2937, "UAK", 8944, "", 0, "AN4".split(" "));
    Route testRouteDiffEmissions =
        new Route("2B", 5336, "BGGH", 4253, "BIAR", 6436, "", 4, "NH7".split(" "));
    testRouteEmissions.setEmissions(20000);
    testRouteDiffEmissions.setEmissions(20000);
    expectedResults.add(testRouteEmissions);
    expectedResults.add(testRouteDiffEmissions);
    reportGenerator.updateLeastEmissionsRoute(testRouteEmissions);
    reportGenerator.updateLeastEmissionsRoute(testRouteDiffEmissions);
    assertEquals(expectedResults, reportGenerator.getLeastEmissionsRoutes());
  }

  /**
   * Verify that when updateLeastEmissionsRoute is called with a route and it has NaN emissions then
   * the most emissions route in the history is not changed to this added route. This test also uses
   * the FlightAnalyser class to check it works as expected here.
   */
  @Test
  public void updateLeastEmissionsRouteAddNaNEmissionsEntryUseFlighAnalyserTest()
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
    Route testRouteEmissions =
        new Route("2H", 1654, "GKA", 2937, "UAK", 8944, "", 0, "AN4".split(" "));
    Route testRouteNaNEmissions =
        new Route("2B", 5336, "BGGH", 4253, "BIAR", 6436, "", 4, "NH7".split(" "));
    FlightAnalyser flightAnalyser =
        new FlightAnalyser(testRouteNaNEmissions, testRouteEmissions, storage);
    testRouteEmissions.setEmissions(flightAnalyser.getPath2Emission()); // 12600 km
    testRouteNaNEmissions.setEmissions(flightAnalyser.getPath1Emission()); // NaN
    expectedResults.add(testRouteEmissions);
    reportGenerator.updateLeastEmissionsRoute(testRouteEmissions);
    reportGenerator.updateLeastEmissionsRoute(testRouteNaNEmissions);
    assertEquals(expectedResults, reportGenerator.getLeastEmissionsRoutes());
  }

  /**
   * Verify that when updateLeastEmissionsRoute is called with a route that has NaN emissions and
   * then with a route that has emissions that the NaN route gets ignored and the most emissions
   * route is the second route (not NaN emissions).
   */
  @Test
  public void updateLeastEmissionsRouteMoreEmissionsUseFlighAnalyserEntryTest()
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
    Route testRouteEmissions =
        new Route("2H", 1654, "GKA", 2937, "UAK", 8944, "", 0, "AN4".split(" "));
    Route testRouteNaNEmissions =
        new Route("2B", 5336, "BGGH", 4253, "BIAR", 6436, "", 4, "NH7".split(" "));
    FlightAnalyser flightAnalyser =
        new FlightAnalyser(testRouteNaNEmissions, testRouteEmissions, storage);
    testRouteEmissions.setEmissions(flightAnalyser.getPath2Emission()); // 12600 km
    testRouteNaNEmissions.setEmissions(flightAnalyser.getPath1Emission()); // NaN
    expectedResults.add(testRouteEmissions);
    reportGenerator.updateLeastEmissionsRoute(testRouteNaNEmissions);
    reportGenerator.updateLeastEmissionsRoute(testRouteEmissions);
    assertEquals(expectedResults, reportGenerator.getLeastEmissionsRoutes());
  }

  // --------------------------------- Testing for updateMostDistanceRoute

  /**
   * Verify that when updateMostDistanceRoute is called with a route and there are no routes in the
   * history, then the route of the most distance is the route that was added.
   */
  @Test
  public void updateMostDistanceRouteFirstEntryTest() {
    ArrayList<Route> expectedResults = new ArrayList<>();
    Route testRoute = new Route("2H", 1654, "IKT", 2937, "ODO", 8944, "", 0, "AN4".split(" "));
    expectedResults.add(testRoute);
    testRoute.setDistance(12333333);
    reportGenerator.updateMostDistanceRoute(testRoute);
    assertEquals(expectedResults, reportGenerator.getMostDistanceRoutes());
  }

  /**
   * Verify that when updateMostDistanceRoute is called with a route and it is of more distance,
   * then the more emissions route in the history is changed to this route.
   */
  @Test
  public void updateMostDistanceRouteAddMoreDistanceEntryTest() {
    ArrayList<Route> expectedResults = new ArrayList<>();
    Route testRouteMoreDistance =
        new Route("2H", 1654, "GKA", 2937, "UAK", 8944, "", 0, "AN4".split(" "));
    Route testRouteLessDistance =
        new Route("2B", 5336, "BGGH", 4253, "BIAR", 6436, "", 4, "NH7".split(" "));
    testRouteMoreDistance.setDistance(29394389);
    testRouteLessDistance.setDistance(309328);
    expectedResults.add(testRouteMoreDistance);
    reportGenerator.updateMostDistanceRoute(testRouteLessDistance);
    reportGenerator.updateMostDistanceRoute(testRouteMoreDistance);
    assertEquals(expectedResults, reportGenerator.getMostDistanceRoutes());
  }

  /**
   * Verify that when updateMostDistanceRoute is called with a route and it is of less distance,
   * then the most distance route is not changed.
   */
  @Test
  public void updateMostDistanceRouteAddLessDistanceEntryTest() {
    ArrayList<Route> expectedResults = new ArrayList<>();
    Route testRouteMoreDistance =
        new Route("2H", 1654, "GKA", 2937, "UAK", 8944, "", 0, "AN4".split(" "));
    Route testRouteLessDistance =
        new Route("2B", 5336, "BGGH", 4253, "BIAR", 6436, "", 4, "NH7".split(" "));
    testRouteMoreDistance.setDistance(43243224);
    testRouteLessDistance.setDistance(3232223);
    expectedResults.add(testRouteMoreDistance);
    reportGenerator.updateMostDistanceRoute(testRouteMoreDistance);
    reportGenerator.updateMostDistanceRoute(testRouteLessDistance);
    assertEquals(expectedResults, reportGenerator.getMostDistanceRoutes());
  }

  /**
   * Verify that when updateMostDistanceRoute is called with a route and it is already in the most
   * distance routes, then the most distance routes in the history remains the same.
   */
  @Test
  public void updateMostDistanceRouteAddSameDistanceSameRouteEntryTest() {
    ArrayList<Route> expectedResults = new ArrayList<>();
    Route testRouteDistance =
        new Route("2H", 1654, "GKA", 2937, "UAK", 8944, "", 0, "AN4".split(" "));
    testRouteDistance.setDistance(700080);
    expectedResults.add(testRouteDistance);
    reportGenerator.updateMostDistanceRoute(testRouteDistance);
    reportGenerator.updateMostDistanceRoute(testRouteDistance);
    assertEquals(expectedResults, reportGenerator.getMostDistanceRoutes());
  }

  /**
   * Verify that when updateMostDistanceRoute is called with a route and it has the same most
   * distance but isn't in the most distance routes array, then the route is added to the most
   * distance route array.
   */
  @Test
  public void updateMostDistanceRouteAddSameEmissionsDiffRouteEntryTest() {
    ArrayList<Route> expectedResults = new ArrayList<>();
    Route testRouteDistance =
        new Route("2H", 1654, "GKA", 2937, "UAK", 8944, "", 0, "AN4".split(" "));
    Route testRouteDiffDistance =
        new Route("2B", 5336, "BGGH", 4253, "BIAR", 6436, "", 4, "NH7".split(" "));
    testRouteDistance.setDistance(20000);
    testRouteDiffDistance.setDistance(20000);
    expectedResults.add(testRouteDistance);
    expectedResults.add(testRouteDiffDistance);
    reportGenerator.updateMostDistanceRoute(testRouteDistance);
    reportGenerator.updateMostDistanceRoute(testRouteDiffDistance);
    assertEquals(expectedResults, reportGenerator.getMostDistanceRoutes());
  }

  // --------------------------------- Testing for updateLeastDistanceRoute

  /**
   * Verify that when updateLeastDistanceRoute is called with a route and there are no routes in the
   * history, then the route of the least distance is the route that was added.
   */
  @Test
  public void updateLeastDistanceRouteFirstEntryTest() {
    ArrayList<Route> expectedResults = new ArrayList<>();
    Route testRoute = new Route("2H", 1654, "IKT", 2937, "ODO", 8944, "", 0, "AN4".split(" "));
    expectedResults.add(testRoute);
    testRoute.setDistance(2432828);
    reportGenerator.updateLeastDistanceRoute(testRoute);
    assertEquals(expectedResults, reportGenerator.getLeastDistanceRoutes());
  }

  /**
   * Verify that when updateLeastDistanceRoute is called with a route and it is of less distance,
   * then the less emissions route in the history is changed to this route.
   */
  @Test
  public void updateLeastDistanceRouteAddLessDistanceEntryTest() {
    ArrayList<Route> expectedResults = new ArrayList<>();
    Route testRouteMoreDistance =
        new Route("2H", 1654, "GKA", 2937, "UAK", 8944, "", 0, "AN4".split(" "));
    Route testRouteLessDistance =
        new Route("2B", 5336, "BGGH", 4253, "BIAR", 6436, "", 4, "NH7".split(" "));
    testRouteMoreDistance.setDistance(29394389);
    testRouteLessDistance.setDistance(309328);
    expectedResults.add(testRouteLessDistance);
    reportGenerator.updateLeastDistanceRoute(testRouteMoreDistance);
    reportGenerator.updateLeastDistanceRoute(testRouteLessDistance);
    assertEquals(expectedResults, reportGenerator.getLeastDistanceRoutes());
  }

  /**
   * Verify that when updateLeastDistanceRoute is called with a route and it is of more distance,
   * then the most less route is not changed.
   */
  @Test
  public void updateLessDistanceRouteAddMoreDistanceEntryTest() {
    ArrayList<Route> expectedResults = new ArrayList<>();
    Route testRouteMoreDistance =
        new Route("2H", 1654, "GKA", 2937, "UAK", 8944, "", 0, "AN4".split(" "));
    Route testRouteLessDistance =
        new Route("2B", 5336, "BGGH", 4253, "BIAR", 6436, "", 4, "NH7".split(" "));
    testRouteMoreDistance.setDistance(43243224);
    testRouteLessDistance.setDistance(3232223);
    expectedResults.add(testRouteLessDistance);
    reportGenerator.updateLeastDistanceRoute(testRouteLessDistance);
    reportGenerator.updateLeastDistanceRoute(testRouteMoreDistance);
    assertEquals(expectedResults, reportGenerator.getLeastDistanceRoutes());
  }

  /**
   * Verify that when updateLeastDistanceRoute is called with a route and it is already in the least
   * distance routes, then the least distance routes in the history remains the same.
   */
  @Test
  public void updateLeastDistanceRouteAddSameDistanceSameRouteEntryTest() {
    ArrayList<Route> expectedResults = new ArrayList<>();
    Route testRouteDistance =
        new Route("2H", 1654, "GKA", 2937, "UAK", 8944, "", 0, "AN4".split(" "));
    testRouteDistance.setDistance(34727229);
    expectedResults.add(testRouteDistance);
    reportGenerator.updateLeastDistanceRoute(testRouteDistance);
    reportGenerator.updateLeastDistanceRoute(testRouteDistance);
    assertEquals(expectedResults, reportGenerator.getLeastDistanceRoutes());
  }

  /**
   * Verify that when updateLeastDistanceRoute is called with a route and it has the same most
   * distance but isn't in the most distance routes array, then the route is added to the most
   * distance route array.
   */
  @Test
  public void updateLeastDistanceRouteAddSameDistanceDiffRouteEntryTest() {
    ArrayList<Route> expectedResults = new ArrayList<>();
    Route testRouteDistance =
        new Route("2H", 1654, "GKA", 2937, "UAK", 8944, "", 0, "AN4".split(" "));
    Route testRouteDiffDistance =
        new Route("2B", 5336, "BGGH", 4253, "BIAR", 6436, "", 4, "NH7".split(" "));
    testRouteDistance.setDistance(20000);
    testRouteDiffDistance.setDistance(20000);
    expectedResults.add(testRouteDistance);
    expectedResults.add(testRouteDiffDistance);
    reportGenerator.updateLeastDistanceRoute(testRouteDistance);
    reportGenerator.updateLeastDistanceRoute(testRouteDiffDistance);
    assertEquals(expectedResults, reportGenerator.getLeastDistanceRoutes());
  }
}

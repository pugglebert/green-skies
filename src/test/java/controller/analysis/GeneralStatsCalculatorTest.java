package controller.analysis;

import model.data.*;
import model.loader.Loader;
import org.junit.Before;
import org.junit.Test;
import java.io.FileNotFoundException;
import java.nio.file.FileSystemException;
import java.sql.SQLException;

import static org.junit.Assert.*;

/**
 * Unit test for ReportGenrator class.
 *
 * @author Hayley Krippner
 * @since 16/09/2020
 * @version 1.0
 */
public class GeneralStatsCalculatorTest {

  private Storage storage;
  private Loader loader;
  private GeneralStatsCalculator generalStatsCalculator;

  @Before
  public void setUp() {
    storage = new Storage();
    loader = new Loader(storage);
    generalStatsCalculator = new GeneralStatsCalculator();
    try {
      loader.loadFile("../seng202_project/src/test/java/TestFiles/GenerateReportTest.csv", "Route");
    } catch (FileSystemException | FileNotFoundException | SQLException e) {
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
    generalStatsCalculator.setTotalCarbonEmissions(0.0);
    Route testRoute = new Route("2W", 410, "SVX", 2975, "OVC", 4078, "", 0, "CR2".split(" "));
    testRoute.setEmissions(20000000.50);
    testRoute.setTimesTaken(1);
    generalStatsCalculator.updateTotalEmissions(testRoute);
    assertEquals(20000000.50, generalStatsCalculator.getTotalCarbonEmissions(), 0.0001);
  }

  /**
   * Verify that when updateTotalEmissions is called with a route with a small amount of carbon
   * emissions that the total emissions is updated accordingly.The carbon emissions are starting at
   * 0.0 g.
   */
  @Test
  public void updateTotalEmissionsSmallEmissionsInitiallyZeroTest() {
    generalStatsCalculator.setTotalCarbonEmissions(0.0);
    Route testRoute = new Route("2W", 410, "SVX", 2975, "OVC", 4078, "", 0, "CR2".split(" "));
    testRoute.setEmissions(70000.0);
    testRoute.setTimesTaken(1);
    generalStatsCalculator.updateTotalEmissions(testRoute);
    assertEquals(70000.0, generalStatsCalculator.getTotalCarbonEmissions(), 0.0001);
  }

  /**
   * Verify that when updateTotalEmissions is called with a route with a zero carbon emissions that
   * the total emissions is updated accordingly.The carbon emissions are starting at large amount.
   */
  @Test
  public void updateTotalEmissionsZeroEmissionsInitiallyZeroTest() {
    generalStatsCalculator.setTotalCarbonEmissions(0.0);
    Route testRoute = new Route("2W", 410, "SVX", 2975, "OVC", 4078, "", 0, "CR2".split(" "));
    testRoute.setEmissions(0.0);
    testRoute.setTimesTaken(1);
    generalStatsCalculator.updateTotalEmissions(testRoute);
    assertEquals(0.0, generalStatsCalculator.getTotalCarbonEmissions(), 0.0001);
  }

  /**
   * Verify that when updateTotalEmissions is called with a route with a large amount of carbon
   * emissions that the total emissions is updated accordingly.The carbon emissions are starting at
   * large amount.
   */
  @Test
  public void updateTotalEmissionsLargeEmissionsInitiallyLargeTest() {
    generalStatsCalculator.setTotalCarbonEmissions(80000000000.10);
    Route testRoute = new Route("2W", 410, "SVX", 2975, "OVC", 4078, "", 0, "CR2".split(" "));
    testRoute.setEmissions(1700000.20);
    testRoute.setTimesTaken(1);
    generalStatsCalculator.updateTotalEmissions(testRoute);
    assertEquals(80001700000.30, generalStatsCalculator.getTotalCarbonEmissions(), 0.0001);
  }

  /**
   * Verify that when updateTotalEmissions is called with a route with a small amount of carbon
   * emissions that the total emissions is updated accordingly.The carbon emissions are starting at
   * large amount.
   */
  @Test
  public void updateTotalEmissionsSmallEmissionsInitiallyLargeTest() {
    generalStatsCalculator.setTotalCarbonEmissions(90000000000.10);
    Route testRoute = new Route("2W", 410, "SVX", 2975, "OVC", 4078, "", 0, "CR2".split(" "));
    testRoute.setEmissions(4500.08);
    testRoute.setTimesTaken(1);
    generalStatsCalculator.updateTotalEmissions(testRoute);
    assertEquals(90000004500.18, generalStatsCalculator.getTotalCarbonEmissions(), 0.0001);
  }

  /**
   * Verify that when updateTotalEmissions is called with a route with a zero carbon emissions that
   * the total emissions is updated accordingly.The carbon emissions are starting at large amount.
   */
  @Test
  public void updateTotalEmissionsZeroEmissionsInitiallyLargeTest() {
    generalStatsCalculator.setTotalCarbonEmissions(345231863432.98);
    Route testRoute = new Route("2W", 410, "SVX", 2975, "OVC", 4078, "", 0, "CR2".split(" "));
    testRoute.setEmissions(0.0);
    testRoute.setTimesTaken(1);
    generalStatsCalculator.updateTotalEmissions(testRoute);
    assertEquals(345231863432.98, generalStatsCalculator.getTotalCarbonEmissions(), 0.0001);
  }

    /**
     * Verify that when updateTotalEmissions is called with a route with a large amount of carbon
     * emissions, which has been taken multipe times, that the total emissions is updated
     * accordingly.The carbon emissions are starting at 0.0 g.
     */
    @Test
    public void updateTotalEmissionsLargeEmissionsZeroTakenTest() {
      generalStatsCalculator.setTotalCarbonEmissions(0.0);
      Route testRoute = new Route("2W", 410, "SVX", 2975, "OVC", 4078, "", 0, "CR2".split(" "));
      testRoute.setEmissions(20000000.50);
      testRoute.setTimesTaken(0);
      generalStatsCalculator.updateTotalEmissions(testRoute);
      assertEquals(0.0, generalStatsCalculator.getTotalCarbonEmissions(), 0.0001);
    }

    /**
     * Verify that when updateTotalEmissions is called with a route with a small amount of carbon
     * emissions, which has been taken zero times, that the total emissions is not updated. The
   carbon
     * emissions are starting at 0.0 g.
     */
    @Test
    public void updateTotalEmissionsSmallEmissionsManyTakenTest() {
      generalStatsCalculator.setTotalCarbonEmissions(0.0);
      Route testRoute = new Route("2W", 410, "SVX", 2975, "OVC", 4078, "", 0, "CR2".split(" "));
      testRoute.setEmissions(8760.0);
      testRoute.setTimesTaken(1000);
      generalStatsCalculator.updateTotalEmissions(testRoute);
      assertEquals(8760000.0, generalStatsCalculator.getTotalCarbonEmissions(), 0.0001);
    }

  // ------------------------------------- Testing for updateTotalDistance

  //TODO fix me! HK 26/09/2020 --> Gives 0.0 instead of 4157987.41
  /**
   * Verify that when updateTotalDistance is called with a route that is of large distance that the
   * total distance is updated accordingly.The total distance is started as 0.0 km.
   */
  @Test
  public void updateTotalDistanceLargeDistanceInitiallyZeroTest() {
    generalStatsCalculator.setTotalDistanceTravelled(0.0);
    Route testRoute = new Route("7K", 392, "PKL", 3920, "MDC", 2523, "", 2, "PDS".split(" "));
    testRoute.setDistance(4157987.41);
    generalStatsCalculator.updateTotalDistance(testRoute);
    assertEquals(4157987.41, generalStatsCalculator.getTotalDistanceTravelled(), 0.0001);
  }

  //TODO fix me! HK 26/09/2020 --> Gives 0.0 instead of 3902.79
  /**
   * Verify that when updateTotalDistance is called with a route that is of small distance that the
   * total * distance is updated accordingly.The total distance is started as 0.0 km.
   */
  @Test
  public void updateTotalDistanceSmallDistanceInitiallyZeroTest() {
    generalStatsCalculator.setTotalDistanceTravelled(0.0);
    Route testRoute = new Route("7K", 392, "PKL", 3920, "MDC", 2523, "", 2, "PDS".split(" "));
    testRoute.setDistance(3902.79);
    generalStatsCalculator.updateTotalDistance(testRoute);
    assertEquals(3902.79, generalStatsCalculator.getTotalDistanceTravelled(), 0.0001);
  }

  /**
   * Verify that when updateTotalDistance is called with a route with zero distance that the total
   * emissions is updated accordingly. The total distance is started as 0.0km.
   */
  @Test
  public void updateTotalDistanceZeroDistanceInitiallyZeroTest() {
    generalStatsCalculator.setTotalCarbonEmissions(0.0);
    Route testRoute = new Route("7K", 392, "PKL", 3920, "MDC", 2523, "", 2, "PDS".split(" "));
    testRoute.setDistance(0.0);
    generalStatsCalculator.updateTotalDistance(testRoute);
    assertEquals(0.0, generalStatsCalculator.getTotalDistanceTravelled(), 0.0001);
  }

  //TODO fix me! HK 26/09/2020 --> Gives 5.890000004637293E13 instead of 5.890000000000002E13
  /**
   * Verify that when updateTotalDistance is called with a route that is of large distance that the
   * total distance is updated accordingly. The total distance is started at a large distance in km.
   */
  @Test
  public void updateTotalDistanceLargeDistanceInitiallyLargeTest() {
    generalStatsCalculator.setTotalDistanceTravelled(58900000000000.02);
    Route testRoute = new Route("7K", 392, "PKL", 3920, "MDC", 2523, "", 2, "PDS".split(" "));
    testRoute.setDistance(46372.91);
    generalStatsCalculator.updateTotalDistance(testRoute);
    assertEquals(58900000046372.93, generalStatsCalculator.getTotalDistanceTravelled(), 0.000000001);
  }

  //TODO fix me! HK 26/09/2020 --> Gives 6.755444090000007E13 instead of 6.755444090415748E13
  /**
   * Verify that when updateTotalDistance is called with a route that is of small distance that the
   * total distance is updated accordingly. The total distance is started at a large distance in km.
   */
  @Test
  public void updateTotalDistanceSmallDistanceInitiallyLargeTest() {
    generalStatsCalculator.setTotalDistanceTravelled(67554440900000.07);
    Route testRoute = new Route("7K", 392, "PKL", 3920, "MDC", 2523, "", 2, "PDS".split(" "));
    testRoute.setDistance(4157.41);
    generalStatsCalculator.updateTotalDistance(testRoute);
    assertEquals(67554440904157.48, generalStatsCalculator.getTotalDistanceTravelled(), 0.0001);
  }

  /**
   * Verify that when updateTotalDistance is called with a route that is of zero distance that the
   * total distance is updated accordingly. The total distance is started at a large distance in km.
   */
  @Test
  public void updateTotalDistanceZeroDistanceInitiallyLargeTest() {
    generalStatsCalculator.setTotalDistanceTravelled(345231863432.98);
    Route testRoute = new Route("7K", 392, "PKL", 3920, "MDC", 2523, "", 2, "PDS".split(" "));
    testRoute.setDistance(0.0);
    generalStatsCalculator.updateTotalDistance(testRoute);
    assertEquals(345231863432.98, generalStatsCalculator.getTotalDistanceTravelled(), 0.0001);
  }

  //TODO write tests for these and rerun coverage

  // --------------------------------- calculateEmissionsPerYear() tests

  // --------------------------------- calculateReductionPercentage() tests

  // --------------------------------- getCurrentYear() tests

  // --------------------------------- calculateRemainingCO2InYear() tests

  // --------------------------------- Testing for calculateOffsetTrees

  /** */
  @Test
  public void calculateOffsetTreesSmallTest() {}

  /** */
  @Test
  public void calculateOffsetTreesLargeTest() {}

  /** */
  @Test
  public void calculateOffsetTreesNegTest() {}

  // --------------------------------- createCarbonEmissionsComment()



  // --------------------------------- Testing for calculateCO2ReductionNeeded


}

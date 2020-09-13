package controller.analysis;

import model.data.Route;
import model.data.Storage;
import model.loader.Loader;
import org.junit.Before;
import org.junit.Test;
import java.io.FileNotFoundException;
import java.nio.file.FileSystemException;
import java.sql.SQLException;

import static org.junit.Assert.*;

/**
 * Unit test for ReportGenrator class.
 * @author Hayley Krippner
 * @since 13/09/2020
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
    } catch (FileSystemException | FileNotFoundException | SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  //------------------------------------------- Testing for updateTotalEmissions ---------------------------------------

  /**
   * Verify that when updateTotalEmissions is called with a route with a large amount of carbon emissions that the total
   * emissions is updated accordingly.The carbon emissions are starting at 0.0 g.
   */
  @Test
  public void updateTotalEmissionsLargeEmissionsInitiallyZeroTest() {
    reportGenerator.setTotalCarbonEmissions(0.0);
    Route testRoute = new Route("2W",410,"SVX",2975,"OVC",
            4078,"",0,"CR2".split(" "));
    testRoute.setEmissions(20000000.50);
    testRoute.setTimesTaken(1);
    reportGenerator.updateTotalEmissions(testRoute);
    assertEquals(20000000.50, reportGenerator.getTotalCarbonEmissions(), 0.0001);
  }

  /**
   * Verify that when updateTotalEmissions is called with a route with a small amount of carbon emissions that the total
   * emissions is updated accordingly.The carbon emissions are starting at 0.0 g.
   */
  @Test
  public void updateTotalEmissionsSmallEmissionsInitiallyZeroTest() {
    reportGenerator.setTotalCarbonEmissions(0.0);
    Route testRoute = new Route("2W",410,"SVX",2975,"OVC",
            4078,"",0,"CR2".split(" "));
    testRoute.setEmissions(70000.0);
    testRoute.setTimesTaken(1);
    reportGenerator.updateTotalEmissions(testRoute);
    assertEquals(70000.0, reportGenerator.getTotalCarbonEmissions(), 0.0001);
  }

  /**
   * Verify that when updateTotalEmissions is called with a route with a zero carbon emissions that the total
   * emissions is updated accordingly.The carbon emissions are starting at large amount.
   */
  @Test
  public void updateTotalEmissionsZeroEmissionsInitiallyZeroTest() {
    reportGenerator.setTotalCarbonEmissions(0.0);
    Route testRoute = new Route("2W",410,"SVX",2975,"OVC",
            4078,"",0,"CR2".split(" "));
    testRoute.setEmissions(0.0);
    testRoute.setTimesTaken(1);
    reportGenerator.updateTotalEmissions(testRoute);
    assertEquals(0.0, reportGenerator.getTotalCarbonEmissions(), 0.0001);
  }

  /**
   * Verify that when updateTotalEmissions is called with a route with a large amount of carbon emissions that the total
   * emissions is updated accordingly.The carbon emissions are starting at large amount.
   */
  @Test
  public void updateTotalEmissionsLargeEmissionsInitiallyLargeTest() {
    reportGenerator.setTotalCarbonEmissions(80000000000.10);
    Route testRoute = new Route("2W",410,"SVX",2975,"OVC",
            4078,"",0,"CR2".split(" "));
    testRoute.setEmissions(1700000.20);
    testRoute.setTimesTaken(1);
    reportGenerator.updateTotalEmissions(testRoute);
    assertEquals(80001700000.30, reportGenerator.getTotalCarbonEmissions(), 0.0001);
  }

  /**
   * Verify that when updateTotalEmissions is called with a route with a small amount of carbon emissions that the total
   * emissions is updated accordingly.The carbon emissions are starting at large amount.
   */
  @Test
  public void updateTotalEmissionsSmallEmissionsInitiallyLargeTest() {
    reportGenerator.setTotalCarbonEmissions(90000000000.10);
    Route testRoute = new Route("2W",410,"SVX",2975,"OVC",
            4078,"",0,"CR2".split(" "));
    testRoute.setEmissions(4500.08);
    testRoute.setTimesTaken(1);
    reportGenerator.updateTotalEmissions(testRoute);
    assertEquals(90000004500.18, reportGenerator.getTotalCarbonEmissions(), 0.0001);
  }

  /**
   * Verify that when updateTotalEmissions is called with a route with a zero carbon emissions that the total
   * emissions is updated accordingly.The carbon emissions are starting at large amount.
   */
  @Test
  public void updateTotalEmissionsZeroEmissionsInitiallyLargeTest() {
    reportGenerator.setTotalCarbonEmissions(345231863432.98);
    Route testRoute = new Route("2W",410,"SVX",2975,"OVC",
            4078,"",0,"CR2".split(" "));
    testRoute.setEmissions(0.0);
    testRoute.setTimesTaken(1);
    reportGenerator.updateTotalEmissions(testRoute);
    assertEquals(345231863432.98, reportGenerator.getTotalCarbonEmissions(), 0.0001);
  }

  /**
   * Verify that when updateTotalEmissions is called with a route with a large amount of carbon emissions, which has been
   * taken multipe times, that the total emissions is updated accordingly.The carbon emissions are starting at 0.0 g.
   */
  @Test
  public void updateTotalEmissionsLargeEmissionsZeroTakenTest() {
    reportGenerator.setTotalCarbonEmissions(0.0);
    Route testRoute = new Route("2W",410,"SVX",2975,"OVC",
            4078,"",0,"CR2".split(" "));
    testRoute.setEmissions(20000000.50);
    testRoute.setTimesTaken(0);
    reportGenerator.updateTotalEmissions(testRoute);
    assertEquals(0.0, reportGenerator.getTotalCarbonEmissions(), 0.0001);
  }

  /**
   * Verify that when updateTotalEmissions is called with a route with a small amount of carbon emissions, which has
   * been taken zero times, that the total emissions is not updated. The carbon emissions are starting at 0.0 g.
   */
  @Test
  public void updateTotalEmissionsSmallEmissionsManyTakenTest() {
    reportGenerator.setTotalCarbonEmissions(0.0);
    Route testRoute = new Route("2W",410,"SVX",2975,"OVC",
            4078,"",0,"CR2".split(" "));
    testRoute.setEmissions(8760.0);
    testRoute.setTimesTaken(1000);
    reportGenerator.updateTotalEmissions(testRoute);
    assertEquals(8760000.0, reportGenerator.getTotalCarbonEmissions(), 0.0001);
  }

  //------------------------------------------- Testing for updateTotalDistance ---------------------------------------

  /**
   * Verify that when updateTotalDistance is called with a route that is of large distance that the total
   * distance is updated accordingly.The total distance is started as 0.0 km.
   */
  @Test
  public void updateTotalDistanceLargeDistanceInitiallyZeroTest() {
    reportGenerator.setTotalDistanceTravelled(0.0);
    Route testRoute = new Route("7K",392,"PKL",3920,"MDC",
            2523,"",2,"PDS".split(" "));
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
    Route testRoute = new Route("7K",392,"PKL",3920,"MDC",
            2523,"",2,"PDS".split(" "));
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
    Route testRoute = new Route("7K",392,"PKL",3920,"MDC",
            2523,"",2,"PDS".split(" "));
    testRoute.setDistance(0.0);
    reportGenerator.updateTotalDistance(testRoute);
    assertEquals(0.0, reportGenerator.getTotalDistanceTravelled(), 0.0001);
  }

  /**
   * Verify that when updateTotalDistance is called with a route that is of large
   * distance that the total distance is updated accordingly. The total distance is started at a large distance in km.
   */
  @Test
  public void updateTotalDistanceLargeDistanceInitiallyLargeTest() {
    reportGenerator.setTotalDistanceTravelled(58900000000000.02);
    Route testRoute = new Route("7K",392,"PKL",3920,"MDC",
            2523,"",2,"PDS".split(" "));
    testRoute.setDistance(46372.91);
    reportGenerator.updateTotalDistance(testRoute);
    assertEquals(58900000046372.93, reportGenerator.getTotalDistanceTravelled(), 0.0001);
  }

  /**
   * Verify that when updateTotalDistance is called with a route that is of small
   * distance that the total distance is updated accordingly. The total distance is started at a large distance in km.
   */
  @Test
  public void updateTotalDistanceSmallDistanceInitiallyLargeTest() {
    reportGenerator.setTotalDistanceTravelled(67554440900000.07);
    Route testRoute = new Route("7K",392,"PKL",3920,"MDC",
            2523,"",2,"PDS".split(" "));
    testRoute.setDistance(4157.41);
    reportGenerator.updateTotalDistance(testRoute);
    assertEquals(67554440904157.48, reportGenerator.getTotalDistanceTravelled(), 0.0001);
  }

  /**
   * Verify that when updateTotalDistance is called with a route that is of zero
   * distance that the total distance is updated accordingly. The total distance is started at a large distance in km.
   */
  @Test
  public void updateTotalDistanceZeroDistanceInitiallyLargeTest() {
    reportGenerator.setTotalDistanceTravelled(345231863432.98);
    Route testRoute = new Route("7K",392,"PKL",3920,"MDC",
            2523,"",2,"PDS".split(" "));
    testRoute.setDistance(0.0);
    reportGenerator.updateTotalDistance(testRoute);
    assertEquals(345231863432.98, reportGenerator.getTotalDistanceTravelled(), 0.0001);
  }

}
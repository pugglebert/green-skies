package controller.analysis;

import model.data.Route;
import model.data.Storage;
import model.loader.Loader;
import org.junit.Before;
import org.junit.Test;
import java.io.FileNotFoundException;
import java.nio.file.FileSystemException;

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
    } catch (FileSystemException | FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  //----------------------- Testing for Generating the Results for the Flight History Report ---------------------------

  /**
   * Verify that when updateTotalEmissions is called with a route with a large amount of carbon emissions that the total
   * emissions is update accordingly.The carbon emissions are starting at 0.0 g.
   */
  @Test
  public void updateTotalEmissionsLargeEmissionsInitallyZeroTest() {
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
   * emissions is update accordingly.The carbon emissions are starting at 0.0 g.
   */
  @Test
  public void updateTotalEmissionsSmallEmissionsInitallyZeroTest() {
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
   * emissions is update accordingly.The carbon emissions are starting at large amount.
   */
  @Test
  public void updateTotalEmissionsZeroEmissionsInitallyZeroTest() {
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
   * emissions is update accordingly.The carbon emissions are starting at large amount.
   */
  @Test
  public void updateTotalEmissionsLargeEmissionsInitallyLargeTest() {
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
   * emissions is update accordingly.The carbon emissions are starting at large amount.
   */
  @Test
  public void updateTotalEmissionsSmallEmissionsInitallyLargeTest() {
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
   * emissions is update accordingly.The carbon emissions are starting at large amount.
   */
  @Test
  public void updateTotalEmissionsZeroEmissionsInitallyLargeTest() {
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
   * taken multipe times, that the total emissions is update accordingly.The carbon emissions are starting at 0.0 g.
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

}
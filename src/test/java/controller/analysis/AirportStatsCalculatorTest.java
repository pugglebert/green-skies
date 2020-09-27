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
import java.util.*;

import static org.junit.Assert.*;

/**
 * Unit test for AirportStatsCalculator class.
 *
 * @author Hayley Krippner
 * @since 26/09/2020
 * @version 1.0
 */
public class AirportStatsCalculatorTest {

  private Storage storage;
  private Loader loader;
  private AirportStatsCalculator airportStatsCalculator;

  @Before
  public void setUp() {
    storage = new Storage();
    loader = new Loader(storage);
    airportStatsCalculator = new AirportStatsCalculator();
    try {
      loader.loadFile("../seng202_project/src/test/java/TestFiles/GenerateReportTest.csv", "Route");
    } catch (FileSystemException | FileNotFoundException | SQLException e) {
      e.printStackTrace();
    }
  }

  // --------------------------------- Testing for updateMostVisitedSrcAirport

  /**
   * Verify that when updateMostVisitedSrcAiport is called when there is one source aiport entry,
   * then the most visited source airport is this entry
   */
  @Test
  public void updateMostVisitedSrcAirportOneTest() {
    ArrayList<String> expectedResults = new ArrayList<>();
    String testString = "Wellington Aiport";
    expectedResults.add(testString);
    HashMap<String, Integer> testSrcAirports = new HashMap<>();
    testSrcAirports.put("Wellington Aiport", 100);
    airportStatsCalculator.updateMostVisitedSrcAirports(testSrcAirports);
    assertEquals(expectedResults, airportStatsCalculator.getMostVisitedSrcAirports());
  }

  /**
   * Verify that when updateMostVisitedSrcAiport is called when there are two source aiport entries,
   * where one is most visited than the other, then the most visit source airport is the one that is
   * most visted out of the two entries.
   */
  @Test
  public void updateMostVisitedSrcAirportTwoTest() {
    ArrayList<String> expectedResults = new ArrayList<>();
    expectedResults.add("Wellington Aiport");
    HashMap<String, Integer> testSrcAirports = new HashMap<>();
    testSrcAirports.put("Wellington Aiport", 100);
    testSrcAirports.put("Auckland Aiport", 84);
    airportStatsCalculator.updateMostVisitedSrcAirports(testSrcAirports);
    assertEquals(expectedResults, airportStatsCalculator.getMostVisitedSrcAirports());
  }

  /**
   * Verify that when updateMostVisitedSrcAiport is called when there are multiple source airports
   * in history i.e. 9 and one of them is of the most visited source airports, then this airport is
   * the only airport added to the most visited source airports array.
   */
  @Test
  public void updateMostVisitedSrcAirportTenDiffOneMostTest() {
    ArrayList<String> expectedResults = new ArrayList<>();
    expectedResults.add("Christchurch Aiport");
    HashMap<String, Integer> testSrcAirports = new HashMap<>();
    testSrcAirports.put("Wellington Aiport", 100);
    testSrcAirports.put("Auckland Aiport", 84);
    testSrcAirports.put("Christchurch Aiport", 147);
    testSrcAirports.put("Queenstown Aiport", 44);
    testSrcAirports.put("Nelson Aiport", 44);
    testSrcAirports.put("Hamilton Aiport", 14);
    testSrcAirports.put("Rotorua Aiport", 57);
    testSrcAirports.put("Hawke's Bay Aiport", 45);
    testSrcAirports.put("Palmerston North Aiport", 4);
    airportStatsCalculator.updateMostVisitedSrcAirports(testSrcAirports);
    assertEquals(expectedResults, airportStatsCalculator.getMostVisitedSrcAirports());
  }

  /**
   * Verify that when updateMostVisitedSrcAiport is called when there are multiple source airports
   * in history i.e. 9 and more than one of them e.g. 3 are of the most visited source airports,
   * then those airports are the only airports added to the most visited source airports array.
   */
  @Test
  public void updateMostVisitedSrcAirportTenDiffThreeMostTest() {
    ArrayList<String> expectedResults = new ArrayList<>();
    expectedResults.add("Wellington Aiport");
    expectedResults.add("Hamilton Aiport");
    expectedResults.add("Auckland Aiport");
    HashMap<String, Integer> testSrcAirports = new HashMap<>();
    testSrcAirports.put("Wellington Aiport", 251);
    testSrcAirports.put("Auckland Aiport", 251);
    testSrcAirports.put("Christchurch Aiport", 147);
    testSrcAirports.put("Queenstown Aiport", 44);
    testSrcAirports.put("Nelson Aiport", 44);
    testSrcAirports.put("Hamilton Aiport", 251);
    testSrcAirports.put("Rotorua Aiport", 57);
    testSrcAirports.put("Hawke's Bay Aiport", 45);
    testSrcAirports.put("Palmerston North Aiport", 4);
    airportStatsCalculator.updateMostVisitedSrcAirports(testSrcAirports);
    assertEquals(expectedResults, airportStatsCalculator.getMostVisitedSrcAirports());
  }

  /**
   * Verify that when updateMostVisitedSrcAiport is called when there are no source aiport entries,
   * then the most visited source airport array should be empty.
   */
  @Test
  public void updateMostVisitedSrcAirportNoRoutesTest() {
    ArrayList<String> expectedResults = new ArrayList<>();
    HashMap<String, Integer> testSrcAirports = new HashMap<>();
    airportStatsCalculator.updateMostVisitedSrcAirports(testSrcAirports);
    assertEquals(expectedResults, airportStatsCalculator.getMostVisitedSrcAirports());
  }

  // --------------------------------- Testing for updateMostVisitedDestAirport

  /**
   * Verify that when updateMostVisitedDestAiport is called when there is one destionation aiport
   * entry, then the most visit destination airport is this entry.
   */
  @Test
  public void updateMostVisitedDestAirportOneTest() {
    ArrayList<String> expectedResults = new ArrayList<>();
    String testString = "Wellington Aiport";
    expectedResults.add(testString);
    HashMap<String, Integer> testDestAirports = new HashMap<>();
    testDestAirports.put("Wellington Aiport", 100);
    airportStatsCalculator.updateMostVisitedDestAirports(testDestAirports);
    assertEquals(expectedResults, airportStatsCalculator.getMostVisitedDestAirports());
  }

  /**
   * Verify that when updateMostVisitedDestAiport is called when there are two destination aiport
   * entries, where one is most visited than the other, then the most visit destination airport is
   * the one that is most visted out of the two entries.
   */
  @Test
  public void updateMostVisitedDestAirportTwoTest() {
    ArrayList<String> expectedResults = new ArrayList<>();
    expectedResults.add("Wellington Aiport");
    HashMap<String, Integer> testDestAirports = new HashMap<>();
    testDestAirports.put("Wellington Aiport", 100);
    testDestAirports.put("Auckland Aiport", 84);
    airportStatsCalculator.updateMostVisitedDestAirports(testDestAirports);
    assertEquals(expectedResults, airportStatsCalculator.getMostVisitedDestAirports());
  }

  /**
   * Verify that when updateMostVisitedDestAiport is called when there are multiple destination
   * airports in history i.e. 9 and one of them is of the most visited destination airports, then
   * this airport is the only airport added to the most visited destination airports array.
   */
  @Test
  public void updateMostVisitedDestAirportTenTest() {
    ArrayList<String> expectedResults = new ArrayList<>();
    expectedResults.add("Christchurch Aiport");
    HashMap<String, Integer> testDestAirports = new HashMap<>();
    testDestAirports.put("Wellington Aiport", 100);
    testDestAirports.put("Auckland Aiport", 84);
    testDestAirports.put("Christchurch Aiport", 147);
    testDestAirports.put("Queenstown Aiport", 44);
    testDestAirports.put("Nelson Aiport", 44);
    testDestAirports.put("Hamilton Aiport", 14);
    testDestAirports.put("Rotorua Aiport", 57);
    testDestAirports.put("Hawke's Bay Aiport", 45);
    testDestAirports.put("Palmerston North Aiport", 4);
    airportStatsCalculator.updateMostVisitedDestAirports(testDestAirports);
    assertEquals(expectedResults, airportStatsCalculator.getMostVisitedDestAirports());
  }

  /**
   * Verify that when updateMostVisitedDestAiport is called when there are multiple destination
   * airports in history i.e. 9 and more than one of them e.g. 3 are of the most visited destination
   * airports, then those airports are the only airports added to the most visited destination
   * airports array.
   */
  @Test
  public void updateMostVisitedDestAirportTenDiffThreeMostTest() {
    ArrayList<String> expectedResults = new ArrayList<>();
    expectedResults.add("Wellington Aiport");
    expectedResults.add("Hamilton Aiport");
    expectedResults.add("Auckland Aiport");
    HashMap<String, Integer> testSrcAirports = new HashMap<>();
    testSrcAirports.put("Wellington Aiport", 251);
    testSrcAirports.put("Auckland Aiport", 251);
    testSrcAirports.put("Christchurch Aiport", 147);
    testSrcAirports.put("Queenstown Aiport", 44);
    testSrcAirports.put("Nelson Aiport", 44);
    testSrcAirports.put("Hamilton Aiport", 251);
    testSrcAirports.put("Rotorua Aiport", 57);
    testSrcAirports.put("Hawke's Bay Aiport", 45);
    testSrcAirports.put("Palmerston North Aiport", 4);
    airportStatsCalculator.updateMostVisitedDestAirports(testSrcAirports);
    assertEquals(expectedResults, airportStatsCalculator.getMostVisitedDestAirports());
  }

  /**
   * Verify that when updateMostVisitedDestAiport is called when there are no destination aiport
   * entries, then the most visited destination airport array should be empty.
   */
  @Test
  public void updateMostVisitedDestAirportNoRoutesTest() {
    ArrayList<String> expectedResults = new ArrayList<>();
    HashMap<String, Integer> testSrcAirports = new HashMap<>();
    airportStatsCalculator.updateMostVisitedDestAirports(testSrcAirports);
    assertEquals(expectedResults, airportStatsCalculator.getMostVisitedDestAirports());
  }

  // TODO write tests for these methods and rerun coverage once completed! HK 26/09/2020
  // --------------------------------- resetAirportArrays() tests

  /**
   * Verify that when resetRoutesArrays is called when there are no values in the arrays, then they
   * remain empty.
   */
  @Test
  public void resetAirportsArraysEmptyTest() {
    ArrayList<String> expectedMostVisitedDestAirports = new ArrayList<>();
    ArrayList<String> expectedMostVisitedSrcAirports = new ArrayList<>();
    ArrayList<String> expectedLeastVisitedSrcAirports = new ArrayList<>();
    ArrayList<String> expectedLeastVisitedDestAirports = new ArrayList<>();

    airportStatsCalculator.resetAirportArrays();

    assertEquals(
        expectedMostVisitedDestAirports, airportStatsCalculator.getMostVisitedDestAirports());
    assertEquals(
        expectedMostVisitedSrcAirports, airportStatsCalculator.getMostVisitedSrcAirports());
    assertEquals(
        expectedLeastVisitedSrcAirports, airportStatsCalculator.getLeastVisitedSrcAirports());
    assertEquals(
        expectedLeastVisitedDestAirports, airportStatsCalculator.getLeastVisitedDestAirports());
  }

  /**
   * Verify that when resetRoutesArrays is called when there is a single value in each array, that
   * the arrays become empty.
   */
  @Test
  public void resetAirportsArraysOneValueTest() {
    ArrayList<String> testMostVisitedDestAirports = new ArrayList<>();
    ArrayList<String> testMostVisitedSrcAirports = new ArrayList<>();
    ArrayList<String> testLeastVisitedSrcAirports = new ArrayList<>();
    ArrayList<String> testLeastVisitedDestAirports = new ArrayList<>();

    testMostVisitedDestAirports.add("GKA");
    testMostVisitedSrcAirports.add("BGG");
    testLeastVisitedSrcAirports.add("BHL");
    testLeastVisitedDestAirports.add("GBR");

    ArrayList<String> expectedMostVisitedDestAirports = new ArrayList<>();
    ArrayList<String> expectedMostVisitedSrcAirports = new ArrayList<>();
    ArrayList<String> expectedLeastVisitedSrcAirports = new ArrayList<>();
    ArrayList<String> expectedLeastVisitedDestAirports = new ArrayList<>();

    airportStatsCalculator.resetAirportArrays();

    assertEquals(
        expectedMostVisitedDestAirports, airportStatsCalculator.getMostVisitedDestAirports());
    assertEquals(
        expectedMostVisitedSrcAirports, airportStatsCalculator.getMostVisitedSrcAirports());
    assertEquals(
        expectedLeastVisitedSrcAirports, airportStatsCalculator.getLeastVisitedSrcAirports());
    assertEquals(
        expectedLeastVisitedDestAirports, airportStatsCalculator.getLeastVisitedDestAirports());
  }

  /**
   * Verify that when resetRoutesArrays is called when there is are multiple values in each array,
   * that the arrays become empty.
   */
  @Test
  public void resetAirportsArraysMultiValuesTest() {
    ArrayList<String> testMostVisitedDestAirports = new ArrayList<>();
    ArrayList<String> testMostVisitedSrcAirports = new ArrayList<>();
    ArrayList<String> testLeastVisitedSrcAirports = new ArrayList<>();
    ArrayList<String> testLeastVisitedDestAirports = new ArrayList<>();

    testMostVisitedDestAirports.add("GKA");
    testMostVisitedSrcAirports.add("BGG");
    testLeastVisitedSrcAirports.add("BHL");
    testLeastVisitedDestAirports.add("GBR");
    testMostVisitedDestAirports.add("AUR");
    testMostVisitedSrcAirports.add("HEP");
    testLeastVisitedSrcAirports.add("MAA");
    testLeastVisitedDestAirports.add("ETY");
    testMostVisitedDestAirports.add("UED");
    testMostVisitedSrcAirports.add("DEA");
    testLeastVisitedSrcAirports.add("PVE");
    testLeastVisitedDestAirports.add("LAC");

    ArrayList<String> expectedMostVisitedDestAirports = new ArrayList<>();
    ArrayList<String> expectedMostVisitedSrcAirports = new ArrayList<>();
    ArrayList<String> expectedLeastVisitedSrcAirports = new ArrayList<>();
    ArrayList<String> expectedLeastVisitedDestAirports = new ArrayList<>();

    airportStatsCalculator.resetAirportArrays();

    assertEquals(
        expectedMostVisitedDestAirports, airportStatsCalculator.getMostVisitedDestAirports());
    assertEquals(
        expectedMostVisitedSrcAirports, airportStatsCalculator.getMostVisitedSrcAirports());
    assertEquals(
        expectedLeastVisitedSrcAirports, airportStatsCalculator.getLeastVisitedSrcAirports());
    assertEquals(
        expectedLeastVisitedDestAirports, airportStatsCalculator.getLeastVisitedDestAirports());
  }

  /**
   * Verify that when resetRoutesArrays is called when there is a mixture of values in each array,
   * that the arrays become empty.
   */
  @Test
  public void resetAirportsArraysVaryingNumOfValuesTest() {
    ArrayList<String> testMostVisitedDestAirports = new ArrayList<>();
    ArrayList<String> testMostVisitedSrcAirports = new ArrayList<>();
    ArrayList<String> testLeastVisitedSrcAirports = new ArrayList<>();
    ArrayList<String> testLeastVisitedDestAirports = new ArrayList<>();

    testMostVisitedDestAirports.add("GKA");
    testLeastVisitedSrcAirports.add("BHL");
    testLeastVisitedDestAirports.add("GBR");
    testMostVisitedDestAirports.add("AUR");
    testMostVisitedSrcAirports.add("HEP");
    testLeastVisitedSrcAirports.add("MAA");
    testMostVisitedDestAirports.add("UED");
    testMostVisitedSrcAirports.add("DEA");
    testLeastVisitedSrcAirports.add("PVE");
    testLeastVisitedDestAirports.add("LAC");

    ArrayList<String> expectedMostVisitedDestAirports = new ArrayList<>();
    ArrayList<String> expectedMostVisitedSrcAirports = new ArrayList<>();
    ArrayList<String> expectedLeastVisitedSrcAirports = new ArrayList<>();
    ArrayList<String> expectedLeastVisitedDestAirports = new ArrayList<>();

    airportStatsCalculator.resetAirportArrays();

    assertEquals(
        expectedMostVisitedDestAirports, airportStatsCalculator.getMostVisitedDestAirports());
    assertEquals(
        expectedMostVisitedSrcAirports, airportStatsCalculator.getMostVisitedSrcAirports());
    assertEquals(
        expectedLeastVisitedSrcAirports, airportStatsCalculator.getLeastVisitedSrcAirports());
    assertEquals(
        expectedLeastVisitedDestAirports, airportStatsCalculator.getLeastVisitedDestAirports());
  }

  // TODO write these tests! 26/09/2020 HK
  // ------------------------------------ Testing for updateLeastVisitedDestAirports()

  /**
   * Verify that when updateLeastVisitedSrcAiport is called when there is one source aiport entry,
   * then the least visited source airport is this entry
   */
  @Test
  public void updateLeastVisitedSrcAirportOneTest() {
    ArrayList<String> expectedResults = new ArrayList<>();
    String testString = "Wellington Aiport";
    expectedResults.add(testString);
    HashMap<String, Integer> testSrcAirports = new HashMap<>();
    testSrcAirports.put("Wellington Aiport", 100);
    airportStatsCalculator.updateLeastVisitedSrcAirports(testSrcAirports);
    assertEquals(expectedResults, airportStatsCalculator.getLeastVisitedSrcAirports());
  }

  /**
   * Verify that when updateLeastVisitedSrcAiport is called when there are two source aiport
   * entries, where one is leasst visited than the other, then the least visit source airport is the
   * one that is least visted out of the two entries.
   */
  @Test
  public void updateLeastVisitedSrcAirportTwoTest() {
    ArrayList<String> expectedResults = new ArrayList<>();
    expectedResults.add("Wellington Aiport");
    HashMap<String, Integer> testSrcAirports = new HashMap<>();
    testSrcAirports.put("Wellington Aiport", 84);
    testSrcAirports.put("Auckland Aiport", 100);
    airportStatsCalculator.updateLeastVisitedSrcAirports(testSrcAirports);
    assertEquals(expectedResults, airportStatsCalculator.getLeastVisitedSrcAirports());
  }

  /**
   * Verify that when updateLeastVisitedSrcAiport is called when there are multiple source airports
   * in history i.e. 9 and one of them is of the least visited source airports, then this airport is
   * the only airport added to the least visited source airports array.
   */
  @Test
  public void updateLeastVisitedSrcAirportTenDiffOneMostTest() {
    ArrayList<String> expectedResults = new ArrayList<>();
    expectedResults.add("Christchurch Aiport");
    HashMap<String, Integer> testSrcAirports = new HashMap<>();
    testSrcAirports.put("Wellington Aiport", 100);
    testSrcAirports.put("Auckland Aiport", 84);
    testSrcAirports.put("Christchurch Aiport", 2);
    testSrcAirports.put("Queenstown Aiport", 44);
    testSrcAirports.put("Nelson Aiport", 44);
    testSrcAirports.put("Hamilton Aiport", 14);
    testSrcAirports.put("Rotorua Aiport", 57);
    testSrcAirports.put("Hawke's Bay Aiport", 45);
    testSrcAirports.put("Palmerston North Aiport", 4);
    airportStatsCalculator.updateLeastVisitedSrcAirports(testSrcAirports);
    assertEquals(expectedResults, airportStatsCalculator.getLeastVisitedSrcAirports());
  }

  /**
   * Verify that when updateLeastVisitedSrcAiport is called when there are multiple source airports
   * in history i.e. 9 and more than one of them e.g. 3 are of the least visited source airports,
   * then those airports are the only airports added to the least visited source airports array.
   */
  @Test
  public void updateLeastVisitedSrcAirportTenDiffThreeMostTest() {
    ArrayList<String> expectedResults = new ArrayList<>();
    expectedResults.add("Wellington Aiport");
    expectedResults.add("Hamilton Aiport");
    expectedResults.add("Auckland Aiport");
    HashMap<String, Integer> testSrcAirports = new HashMap<>();
    testSrcAirports.put("Wellington Aiport", 2);
    testSrcAirports.put("Auckland Aiport", 2);
    testSrcAirports.put("Christchurch Aiport", 147);
    testSrcAirports.put("Queenstown Aiport", 44);
    testSrcAirports.put("Nelson Aiport", 44);
    testSrcAirports.put("Hamilton Aiport", 2);
    testSrcAirports.put("Rotorua Aiport", 57);
    testSrcAirports.put("Hawke's Bay Aiport", 45);
    testSrcAirports.put("Palmerston North Aiport", 4);
    airportStatsCalculator.updateLeastVisitedSrcAirports(testSrcAirports);
    assertEquals(expectedResults, airportStatsCalculator.getLeastVisitedSrcAirports());
  }

  /**
   * Verify that when updateLeastVisitedSrcAiport is called when there are no source aiport entries,
   * then the least visited source airport array should be empty.
   */
  @Test
  public void updateLeastVisitedSrcAirportNoRoutesTest() {
    ArrayList<String> expectedResults = new ArrayList<>();
    HashMap<String, Integer> testSrcAirports = new HashMap<>();
    airportStatsCalculator.updateLeastVisitedSrcAirports(testSrcAirports);
    assertEquals(expectedResults, airportStatsCalculator.getLeastVisitedSrcAirports());
  }

  // ------------------------------------ updateLeastVisitedSrcAirports(HashMap<String, Integer>
  // srcAirportCounts) tests

}

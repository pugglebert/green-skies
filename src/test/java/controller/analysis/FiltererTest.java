package controller.analysis;

import model.data.Airline;
import model.data.Airport;
import model.data.Route;
import model.data.Storage;
import model.loader.Loader;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.nio.file.FileSystemException;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * Unit test for Filterer class.
 * @author Hayley Krippner
 * @since 29/08/2020
 * @version 1.0
 */
public class FiltererTest {

  private Storage storage;
  private Loader loader;
  private Filterer filterer;

  //----------------------------------------- Testing for filtering Airports -------------------------------------------

  @Before
  public void setUp() {
    storage = new Storage();
    loader = new Loader(storage);
    filterer = new Filterer();
    try {
      loader.loadFile("../seng202_project/src/test/java/TestFiles/FiltererAirportsTest.csv", "Airport");
    } catch (FileSystemException | FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  /**
   * Verify that when filterAirports is called with a filter term that matches one entry in the file, an arrayList
   * containing just that entry is returned.
   */
  @Test
  public void filterAirportsOneTermOneRecordTest() {
    ArrayList<Airport> expectedResults = new ArrayList<>();
    HashMap<String, String> testFilterTerms = new HashMap<>();
    testFilterTerms.put("Name", "Goroka");
    expectedResults.add(new Airport(1,"Goroka","Goroka","Papua New Guinea","GKA",
            "AYGA",-6.081689,145.391881,5282,10,"U",
            "Pacific/Port_Moresby"));
    ArrayList<Airport> results = filterer.filterAirports(testFilterTerms, storage.getAirports());
    assertArrayEquals(expectedResults.toArray(), results.toArray());
  }

  /**
   * Verify that when filterAirports is called with a name and country filter terms that matches one entry in the file,
   * an arrayList containing just that entry is returned.
   */
  @Test
  public void filterAirportsTwoTermsOneRecordTest() {
    ArrayList<Airport> expectedResults = new ArrayList<>();
    HashMap<String, String> testFilterTerms = new HashMap<>();
    testFilterTerms.put("Name", "Goroka");
    testFilterTerms.put("Country", "Papua New Guinea");
    expectedResults.add(new Airport(1,"Goroka","Goroka","Papua New Guinea","GKA",
            "AYGA",-6.081689,145.391881,5282,10,"U",
            "Pacific/Port_Moresby"));
    ArrayList<Airport> results = filterer.filterAirports(testFilterTerms, storage.getAirports());
    assertArrayEquals(expectedResults.toArray(), results.toArray());
  }

  /**
   * Verify that when filterAirports is called with a name, country and IATA filter terms that matches one entry in the
   * file an arrayList containing just that entry is returned.
   */
  @Test
  public void filterAirportsThreeTermsOneRecordTest() {
    ArrayList<Airport> expectedResults = new ArrayList<>();
    HashMap<String, String> testFilterTerms = new HashMap<>();
    testFilterTerms.put("Name", "Goroka");
    testFilterTerms.put("Country", "Papua New Guinea");
    testFilterTerms.put("IATA", "GKA");
    expectedResults.add(new Airport(1,"Goroka","Goroka","Papua New Guinea","GKA",
            "AYGA",-6.081689,145.391881,5282,10,"U",
            "Pacific/Port_Moresby"));
    ArrayList<Airport> results = filterer.filterAirports(testFilterTerms, storage.getAirports());
    assertArrayEquals(expectedResults.toArray(), results.toArray());
  }


  /**
   * Verify that when filterAirports is called with a filter term that matches multiple entries in the file, an
   * arrayList containing all of those entries is returned. This tests data which does not contain a subset of the
   * filter terms i.e. all terms appear in each matching record.
   */
  @Test
  public void filterAirportsOneTermManyRecordsTest() {
    ArrayList<Airport> expectedResults = new ArrayList<>();
    HashMap<String, String> testFilterTerms = new HashMap<>();
    testFilterTerms.put("Country", "Greenland");
    expectedResults.add(new Airport(7,"Narsarsuaq","Narssarssuaq","Greenland","UAK",
            "BGBW",61.160517,-45.425978,112,-3,"E",
            "America/Godthab"));
    expectedResults.add(new Airport(8,"Nuuk","Godthaab","Greenland","GOH","BGGH",
            64.190922,-51.678064,283,-3,"E","America/Godthab"));
    ArrayList<Airport> results = filterer.filterAirports(testFilterTerms, storage.getAirports());
    assertArrayEquals(expectedResults.toArray(), results.toArray());
  }


  /**
   * Verify that when filterAirports is called with two filter terms that match multiple entries in the file, an
   * ArrayList containing all of those entries is returned. This tests data which does not contain a subset of the
   * filter terms i.e. all terms appear in each matching record.
   */
  @Test
  public void filterAirportsTwoTermsManyRecordsTest() {
    ArrayList<Airport> expectedResults = new ArrayList<>();
    HashMap<String, String> testFilterTerms = new HashMap<>();
    testFilterTerms.put("Name", "Akureyri");
    testFilterTerms.put("Country", "Iceland");
    expectedResults.add(new Airport(11,"Akureyri","Akureyri","Iceland","AEY",
            "BIAR",65.659994,-18.072703,6,0,"N",
            "Atlantic/Reykjavik"));
    expectedResults.add(new Airport(12,"Akureyri","Egilsstadir","Iceland","EGS",
            "BIEG",65.283333,-14.401389,76,0,"N",
            "Atlantic/Reykjavik"));
    expectedResults.add(new Airport(13,"Akureyri","Hofn","Iceland","HFN","BIHN",
            64.295556,-15.227222,24,0,"N","Atlantic/Reykjavik"));
    expectedResults.add(new Airport(14,"Akureyri","Husavik","Iceland","HZK","BIHU",
            65.952328,-17.425978,48,0,"N","Atlantic/Reykjavik"));
    ArrayList<Airport> results = filterer.filterAirports(testFilterTerms, storage.getAirports());
    assertArrayEquals(expectedResults.toArray(), results.toArray());
  }

  /**
   * Verify that when filterAirports is called with three filter terms that matches multiple entries in the file, an arrayList
   * containing all of those entries is returned. This tests data which does not contain a subset of the filter terms
   * i.e. all terms appear in each matching record.
   */
  @Test
  public void filterAirportsThreeTermsManyRecordsTest() {
    ArrayList<Airport> expectedResults = new ArrayList<>();
    HashMap<String, String> testFilterTerms = new HashMap<>();
    testFilterTerms.put("Name", "Sault Ste Marie");
    testFilterTerms.put("Country", "Canada");
    testFilterTerms.put("IATA", "YAM");
    expectedResults.add(new Airport(21,"Sault Ste Marie","Sault Sainte Marie","Canada",
            "YAM","CYAM",46.485001,-84.509445,630,-5,"A",
            "America/Toronto"));
    expectedResults.add(new Airport(22,"Sault Ste Marie","Winnipeg","Canada","YAM",
            "CYAV",50.056389,-97.0325,760,-6,"A",
            "America/Winnipeg"));
    ArrayList<Airport> results = filterer.filterAirports(testFilterTerms, storage.getAirports());
    assertArrayEquals(expectedResults.toArray(), results.toArray());
  }

  /**
   * Verify that when filterAirports is called with two filter terms and there are two records in the data storage where
   * the first record's name attribute matches the filter's name and the second record's country matches the filter's
   * country, then no records are returned and a RuntimeException exception gets thrown.
   */
  @Test
  public void filterAirportsTwoTermsNoRecordsTest() {
      try {
      HashMap<String, String> testFilterTerms = new HashMap<>();
      testFilterTerms.put("Name", "Santa Maria Pub Cpt G Allan Hancock Airport");
      testFilterTerms.put("Country", "USA");
      filterer.filterAirports(testFilterTerms, storage.getAirports());
      fail();
      } catch (RuntimeException e) {
        assertTrue(true);
      }
}
    /**
     * Verify that when filterAirports is called with two filter terms and there is only one record in
     * the data storage where the first record's name attribute matches the filter's name and the
     * second record's country matches the filter's country, then just this record is returned.
     */
    @Test
    public void filterAirportsTwoTermsTwoIncorrectOneRecordTest() {
        ArrayList<Airport> expectedResults = new ArrayList<>();
        HashMap<String, String> testFilterTerms = new HashMap<>();
        testFilterTerms.put("Name", "Mangaia Island Airport");
        testFilterTerms.put("Country", "Cook Islands");
        ArrayList<Airport> results = filterer.filterAirports(testFilterTerms, storage.getAirports());
        expectedResults.add(new Airport(5864,"Mangaia Island Airport","Mangaia Island",
                "Cook Islands","MGS","NCMG",-21.8956, -157.905,45,
                -10,"U","Pacific/Rarotonga"));
        assertArrayEquals(expectedResults.toArray(), results.toArray());
  }
}


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

//@TODO: test for successful single filter term
//@TODO: test for successful two filter terms
//@TODO: test for successful three filter terms
//@TODO: for all the above, test with files where there is one record, two records and non-successful records in between
//@TODO: test for two filter terms where one record has first and another record has the second and niether are added
//@TODO: test for three filter terms where one record has first, another record has the second and another record has
// the none are added
//@TODO: test for single filter term and nothing returned
//@TODO: test for two filter terms and nothing returned
//@TODO: test for three filter term and nothing returned

//TODO:@Repeat for all each data type.


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
   * Verify that when filterAirports is called with a name, country and IATA filter terms that matches one entry in the file,
   * an arrayList containing just that entry is returned.
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
   * Verify that when filterAirports is called with a filter term that matches multiple entries in the file, an arrayList
   * containing all of those entries is returned. This tests data which does not contain a subset of the filter terms
   * i.e. all terms appear in each matching record.
   */
  @Test
  public void filterAirportsOneMatchManyRecordsTest() {
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
   * Verify that when filterAirports is called with two filter terms that matches multiple entries in the file, an arrayList
   * containing all of those entries is returned. This tests data which does not contain a subset of the filter terms
   * i.e. all terms appear in each matching record.
   */
  @Test
  public void filterAirportsTwoMatchManyRecordsTest() {
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
  public void filterAirportsThreeMatchManyRecordsTest() {
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

//TODO: up to here. Need to debug as this test failed!
  /**
   * Verify that when filterAirports is called with two filter terms and there are two records in the data storage
   * where the first record's name attribute matches the filter's name and the second record's country matches the
   * filter's country,
   */
  @Test
  public void filterAirportsTwoMatchNoRecordsTest() {
    ArrayList<Airport> expectedResults = new ArrayList<>();
    HashMap<String, String> testFilterTerms = new HashMap<>();
    testFilterTerms.put("Name", "Manihiki Island Airport");
    testFilterTerms.put("Country", "Cook Islands");
    ArrayList<Airport> results = filterer.filterAirports(testFilterTerms, storage.getAirports());
    assertArrayEquals(expectedResults.toArray(), results.toArray());
  }



//
//  /**
//   * Verify that when searchAirports is called with a search term that matches one entry in the file, an arrayList
//   * containing just that entry is returned.
//   * Test File: #1
//   */
//  @Test
//  public void filterAirportsTwoDataTypesNoMatchesTest() {
//    ArrayList<Airport> expectedResults = new ArrayList<>();
//    HashMap<String, String> testFilterTerms = new HashMap<>();
//    testFilterTerms.put("Name", "Wellington");
//    testFilterTerms.put("Country", "England");
//    expectedResults.add(new Airport(1,"Wellington","Goroka","Papua New Guinea","GKA",
//            "AYGA",-6.081689,145.391881,5282,10,"U",
//            "Pacific/Port_Moresby"));
//    expectedResults.add(new Airport(2,"Madang","Madang","England","MAG","AYMD",
//            -5.207083,145.7887,20,10,"U","Pacific/Port_Moresby"));
//    ArrayList<Airport> results = filterer.filterAirports(testFilterTerms, storage.getAirports());
//    assertArrayEquals(expectedResults.toArray(), results.toArray());
//  }



//  /**
//   * Verify that when filterAirports is called with a two filter terms that have no intersecting records in the file,
//   * a RuntimeException is thrown.
//   */
//  @Test
//  public void filterAirportsNoMatchTwoValuesTest() {
//    try {
//        HashMap<String, String> testFilterTerms = new HashMap<>();
//        testFilterTerms.put("Name", "Wellington");
//        testFilterTerms.put("Country", "England");
//        filterer.filterAirports(testFilterTerms, storage.getAirports());
//        fail();
//    } catch (RuntimeException e) {
//      assertTrue(true);
//    }
//  }





//  /**
//   * Verify that when searchAirports is called with a search term that matches one entry in the file, an arrayList
//   * containing just that entry is returned.
//   */
//  @Test
//  public void searchAirportsOneMatchTest() {
//    ArrayList<Airport> expectedResults = new ArrayList<>();
//    expectedResults.add(new Airport(6,"Wewak Intl","Wewak","Papua New Guinea","WWK","AYWK",-3.583828,143.669186,19,10,"U","Pacific/Port_Moresby"));
//    ArrayList<Airport> results = Searcher.searchAirports("WWK", "IATA", storage.getAirports());
//    assertArrayEquals(expectedResults.toArray(), results.toArray());
//  }
//
//  /**
//   * Verify that when searchAirports is called with a search term that matches multiple entries in the file, an arrayList
//   * containing all those entries in the same order they appear in the file is returned.
//   */
//  @Test
//  public void searchAirportsMultipleMatchTest() {
//    ArrayList<Airport> expectedResults = new ArrayList<>();
//    expectedResults.add(new Airport(7,"Narsarsuaq","Narssarssuaq","Greenland","UAK","BGBW",61.160517,-45.425978,112,-3,"E","America/Godthab"));
//    expectedResults.add(new Airport(8,"Nuuk","Godthaab","Greenland","GOH","BGGH",64.190922,-51.678064,283,-3,"E","America/Godthab"));
//    expectedResults.add(new Airport(9,"Sondre Stromfjord","Sondrestrom","Greenland","SFJ","BGSF",67.016969,-50.689325,165,-3,"E","America/Godthab"));
//    expectedResults.add(new Airport(10,"Thule Air Base","Thule","Greenland","THU","BGTL",76.531203,-68.703161,251,-4,"E","America/Thule"));
//    ArrayList<Airport> results = Searcher.searchAirports("Greenland", "Country", storage.getAirports());
//    assertArrayEquals(expectedResults.toArray(), results.toArray());
//  }
//
//  /**
//   * Verify that when an illegal search type is passed into searchAirports it raises an IllegalArgumentException.
//   */
//  @Test
//  public void searchAirportsIllegalTypeTest() {
//    try {
//      Searcher.searchAirports("Greenland", "Airline", storage.getAirports());
//      fail();
//    } catch (IllegalArgumentException e) {
//      assertTrue(true);
//    }
//  }
//
//  /**
//   * Verify that searchAirports can return the correct entry when that entry is towards the end of a file over 8,000
//   * lines long.
//   */
//  @Test
//  public void searchAirportsLongFileTest(){
//    storage = new Storage();
//    loader = new Loader(storage);
//    try {
//      loader.loadFile("../seng202_project/src/test/java/TestFiles/airports.csv", "Airport");
//    } catch (Exception e) {
//      System.out.println(e.getMessage());
//      fail();
//    }
//    ArrayList<Airport> expectedResults = new ArrayList<>();
//    expectedResults.add(new Airport(9428,"Bessemer","Bessemer","United States","EKY","KEKY",33.1876,-86.5558,700,-6,"A","America/Chicago"));
//    ArrayList<Airport> results = Searcher.searchAirports("Bessemer", "Name", storage.getAirports());
//    assertArrayEquals(expectedResults.toArray(), results.toArray());
//  }
//
//  /**
//   * Verify that when searchAirports is called with a searchTerm which matches a term in the file but is in a different,
//   * an arrayList containing the matching entry is returned.
//   */
//  @Test
//  public void searchAirportsCaseSensitiveTest() {
//    ArrayList<Airport> expectedResults = new ArrayList<>();
//    expectedResults.add(new Airport(6,"Wewak Intl","Wewak","Papua New Guinea","WWK","AYWK",-3.583828,143.669186,19,10,"U","Pacific/Port_Moresby"));
//    ArrayList<Airport> results = Searcher.searchAirports("WEWAK INTL", "Name", storage.getAirports());
//    assertArrayEquals(expectedResults.toArray(), results.toArray());
//  }





//  /**
//   * Verify that when searchRoutes is called with a search term that matches no terms in the file, a RuntimeException
//   * is thrown.
//   */
//  @Test
//  public void searchRoutesNoMatchTest() {
//    try {
//      Searcher.searchRoutes("ABCDEFGHIJKLMNOPQRSTUVWXYZ", "Airline", storage.getRoutes());
//      fail();
//    } catch (RuntimeException e) {
//      assertTrue(true);
//    }
//  }
//
//  /**
//   * Verify that when searchRoutes is called with a search term that matches one entry in the file, an arrayList
//   * containing just that entry is returned.
//   */
//  @Test
//  public void searchRoutesOneMatchTest() {
//    ArrayList<Route> expectedResults = new ArrayList<>();
//    expectedResults.add(new Route("2B",410,"GYD",2922,"NBC",6969,"",0,"CR2".split(" ")));
//    ArrayList<Route> results = Searcher.searchRoutes("GYD", "Source", storage.getRoutes());
//    assertArrayEquals(expectedResults.toArray(), results.toArray());
//  }
//
//  /**
//   * Verify that when searchRoutes is called with a search term that matches multiple entries in the file, an arrayList
//   * containing all those entries in the same order they appear in the file is returned.
//   */
//  @Test
//  public void searchRoutesMultipleMatchTest() {
//    ArrayList<Route> expectedResults = new ArrayList<>();
//    expectedResults.add(new Route("2B",410,"DME",4029,"NBC",6969,"",0,"CR2".split(" ")));
//    expectedResults.add(new Route("2B",410,"GYD",2922,"NBC",6969,"",0,"CR2".split(" ")));
//    expectedResults.add(new Route("2B",410,"LED",2948,"NBC",6969,"",0,"CR2".split(" ")));
//    expectedResults.add(new Route("2B",410,"SVX",2975,"NBC",6969,"",0,"CR2".split(" ")));
//    ArrayList<Route> results = Searcher.searchRoutes("NBC", "Destination", storage.getRoutes());
//    assertArrayEquals(expectedResults.toArray(), results.toArray());
//  }
//
//  /**
//   * Verify that when an illegal search type is passed into searchRoutes it raises an IllegalArgumentException.
//   */
//  @Test
//  public void searchRoutesIllegalTypeTest() {
//    try {
//      Searcher.searchRoutes("NBC", "Country", storage.getRoutes());
//      fail();
//    } catch (IllegalArgumentException e) {
//      assertTrue(true);
//    }
//  }
//
//  /**
//   * Verify that searchRoutes can return the correct entry when that entry is towards the end of a file over 10,000
//   * lines long.
//   */
//  @Test
//  public void searchRoutesLongFileTest(){
//    storage = new Storage();
//    loader = new Loader(storage);
//    try {
//      loader.loadFile("../seng202_project/src/test/java/TestFiles/routes.csv", "Route");
//    } catch (Exception e) {
//      System.out.println(e.getMessage());
//      fail();
//    }
//    ArrayList<Route> expectedResults = new ArrayList<>();
//    expectedResults.add(new Route("YO",16150,"HME",238,"LGW",502,"Y",0,"320".split(" ")));
//    expectedResults.add(new Route("YO",16150,"LGW",502,"HME",238,"Y",0,"320".split(" ")));
//    expectedResults.add(new Route("YO",16150,"MCM",4264,"NCE",1354,"",0,"NDE".split(" ")));
//    expectedResults.add(new Route("YO",16150,"NCE",1354,"MCM",4264,"",0,"NDE".split(" ")));
//    ArrayList<Route> results = Searcher.searchRoutes("YO", "Airline", storage.getRoutes());
//    assertArrayEquals(expectedResults.toArray(), results.toArray());
//  }
//
//  /**
//   * Verify that when searchRoutes is called with a searchTerm which matches a term in the file but is in a different,
//   * an arrayList containing the matching entry is returned.
//   */
//  @Test
//  public void searchRoutesCaseSensitiveTest() {
//    ArrayList<Route> expectedResults = new ArrayList<>();
//    expectedResults.add(new Route("2B",410,"GYD",2922,"NBC",6969,"",0,"CR2".split(" ")));
//    ArrayList<Route> results = Searcher.searchRoutes("gyd", "Source", storage.getRoutes());
//    assertArrayEquals(expectedResults.toArray(), results.toArray());
//  }


//  /**
//   * Verify that when searchAirlines is called with a search term that matches no terms in the file, a RuntimeException
//   * is thrown.
//   */
//  @Test
//  public void searchAirlinesNoMatchTest() {
//    try {
//      Searcher.searchAirlines("ABCDEFGHIJKLMNOPQRSTUVWXYZ", "IATA", storage.getAirlines());
//      fail();
//    } catch (RuntimeException e) {
//      assertTrue(true);
//    }
//  }
//
//  /**
//   * Verify that when searchAirlines is called with a search term that matches one entry in the file, an arrayList
//   * containing just that entry is returned.
//   */
//  @Test
//  public void searchAirlinesOneMatchTest() {
//    ArrayList<Airline> expectedResults = new ArrayList<>();
//    expectedResults.add(new Airline(29,"Askari Aviation","\\N","4K","AAS","AL-AAS","Pakistan",true));
//    ArrayList<Airline> results = Searcher.searchAirlines("Askari Aviation", "Name", storage.getAirlines());
//    assertArrayEquals(expectedResults.toArray(), results.toArray());
//  }
//
//  /**
//   * Verify that when searchAirlines is called with a search term that matches multiple entries in the file, an arrayList
//   * containing all those entries in the same order they appear in the file is returned.
//   */
//  @Test
//  public void searchAirlinesMultipleMatchTest() {
//    ArrayList<Airline> expectedResults = new ArrayList<>();
//    expectedResults.add(new Airline(5,"213 Flight Unit","\\N","","TFU","","Russia",false));
//    expectedResults.add(new Airline(6,"223 Flight Unit State Airline","\\N","","CHD","CHKALOVSK-AVIA","Russia",false));
//    expectedResults.add(new Airline(7,"224th Flight Unit","\\N","","TTF","CARGO UNIT","Russia",false));
//    expectedResults.add(new Airline(41,"Abakan-Avia","\\N","","ABG","ABAKAN-AVIA","Russia",false));
//    ArrayList<Airline> results = Searcher.searchAirlines("Russia", "Country", storage.getAirlines());
//    assertArrayEquals(expectedResults.toArray(), results.toArray());
//  }
//
//  /**
//   * Verify that when an illegal search type is passed into searchAirlines it raises an IllegalArgumentException.
//   */
//  @Test
//  public void searchAirlinesIllegalTypeTest() {
//    try {
//      Searcher.searchAirlines("Russia", "Airport", storage.getAirlines());
//      fail();
//    } catch (IllegalArgumentException e) {
//      assertTrue(true);
//    }
//  }
//
//  /**
//   * Verify that searchAirlines can return the correct entry when that entry is towards the end of a file over 6,000
//   * lines long.
//   */
//  @Test
//  public void searchAirlinesLongFileTest(){
//    storage = new Storage();
//    loader = new Loader(storage);
//    try {
//      loader.loadFile("../seng202_project/src/test/java/TestFiles/airlines.csv", "Airline");
//    } catch (Exception e) {
//      System.out.println(e.getMessage());
//      fail();
//    }
//    ArrayList<Airline> expectedResults = new ArrayList<>();
//    expectedResults.add(new Airline(21179,"Thai Vietjet Air","","","TVJ","THAIVIET JET","Thailand",true));
//    ArrayList<Airline> results = Searcher.searchAirlines("TVJ", "ICAO", storage.getAirlines());
//    assertArrayEquals(expectedResults.toArray(), results.toArray());
//  }
//
//  /**
//   * Verify that when searchAirlines is called with a searchTerm which matches a term in the file but is in a different,
//   * an arrayList containing the matching entry is returned.
//   */
//  @Test
//  public void searchAirlinesCaseSensitiveTest() {
//    ArrayList<Airline> expectedResults = new ArrayList<>();
//    expectedResults.add(new Airline(29,"Askari Aviation","\\N","4K","AAS","AL-AAS","Pakistan",true));
//    ArrayList<Airline> results = Searcher.searchAirlines("askari aviation", "Name", storage.getAirlines());
//    assertArrayEquals(expectedResults.toArray(), results.toArray());
//  }

}

package controller.analysis;

import model.data.Route;
import model.data.Storage;
import model.loader.Loader;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.nio.file.FileSystemException;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Unit test for Searcher class.
 * @author Ella Johnson
 * @since 26/08/2020
 * @version 1.0
 */
public class SearcherTest {

  private Storage storage;
  private Loader loader;

  @Before
  public void setUp() {
    storage = new Storage();
    loader = new Loader(storage);
    try {
      loader.loadFile("../seng202_project/src/test/java/TestFiles/SearcherRoutesTest.csv", "Route");
    } catch (FileSystemException | FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  /**
   * Verify that when searchRoutes is called with a search term that matches no terms in the file, a RuntimeException
   * is thrown.
   */
  @Test
  public void searchRoutesNoMatchTest() {
    try {
      Searcher.searchRoutes("ABCDEFGHIJKLMNOPQRSTUVWXYZ", "Airline", storage.getRoutes());
      fail();
    } catch (RuntimeException e) {
      assertTrue(true);
    }
  }

  /**
   * Verify that when searchRoutes is called with a search term that matches one entry in the file, an arrayList
   * containing just that entry is returned.
   */
  @Test
  public void searchRoutesOneMatchTest() {
    ArrayList<Route> expectedResults = new ArrayList<>();
    expectedResults.add(new Route("2B",410,"GYD",2922,"NBC",6969,"",0,"CR2".split(" ")));
    ArrayList<Route> results = Searcher.searchRoutes("GYD", "Source", storage.getRoutes());
    assertArrayEquals(expectedResults.toArray(), results.toArray());
  }

  /**
   * Verify that when searchRoutes is called with a search term that matches multiple entries in the file, an arrayList
   * containing all those entries in the same order they appear in the file is returned.
   */
  @Test
  public void searchRoutesMultipleMatchTest() {
    ArrayList<Route> expectedResults = new ArrayList<>();
    expectedResults.add(new Route("2B",410,"DME",4029,"NBC",6969,"",0,"CR2".split(" ")));
    expectedResults.add(new Route("2B",410,"GYD",2922,"NBC",6969,"",0,"CR2".split(" ")));
    expectedResults.add(new Route("2B",410,"LED",2948,"NBC",6969,"",0,"CR2".split(" ")));
    expectedResults.add(new Route("2B",410,"SVX",2975,"NBC",6969,"",0,"CR2".split(" ")));
    ArrayList<Route> results = Searcher.searchRoutes("NBC", "Destination", storage.getRoutes());
    assertArrayEquals(expectedResults.toArray(), results.toArray());
  }

  /**
   * Verify that when an illegal search type is passed into searchRoutes it raises an IllegalArgumentException.
   */
  @Test
  public void searchRoutesIllegalTypeTest() {
    try {
      Searcher.searchRoutes("NBC", "Country", storage.getRoutes());
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }
  }

  /**
   * Verify that searchRoutes can return the correct entry when that entry is towards the end of a file over 10,000
   * lines long.
   */
  @Test
  public void searchRoutesLongFileTest(){
    storage = new Storage();
    loader = new Loader(storage);
    try {
      loader.loadFile("../seng202_project/src/test/java/TestFiles/routes.csv", "Route");
    } catch (Exception e) {
      System.out.println(e.getMessage());
      fail();
    }
    ArrayList<Route> expectedResults = new ArrayList<>();
    expectedResults.add(new Route("YO",16150,"HME",238,"LGW",502,"Y",0,"320".split(" ")));
    expectedResults.add(new Route("YO",16150,"LGW",502,"HME",238,"Y",0,"320".split(" ")));
    expectedResults.add(new Route("YO",16150,"MCM",4264,"NCE",1354,"",0,"NDE".split(" ")));
    expectedResults.add(new Route("YO",16150,"NCE",1354,"MCM",4264,"",0,"NDE".split(" ")));
    ArrayList<Route> results = Searcher.searchRoutes("YO", "Airline", storage.getRoutes());
    assertArrayEquals(expectedResults.toArray(), results.toArray());
  }



}
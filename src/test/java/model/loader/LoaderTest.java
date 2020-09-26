package model.loader;

import model.data.Airport;
import model.data.DataType;
import model.data.Route;
import model.data.Storage;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.nio.file.FileSystemException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Unit tests for the Loader class
 * @author Ella Johnson
 * @since 2020-09-08
 */
public class LoaderTest {

  private Loader loader;
  private Storage storage;
  private ArrayList<String> testLines;

  /**
   * Set up before each test case.
   */
  @Before
  public void setUp() {
    storage = new Storage();
    loader = new Loader(storage);
    testLines = new ArrayList<>();

    testLines.add(
        "1,\"Goroka Airport\",\"Goroka\",\"Papua New Guinea\",\"GKA\",\"AYGA\",-6.081689834590001,145.391998291,5282,10,\"U\",\"Pacific/Port_Moresby\",\"airport\",\"OurAirports\"");
    testLines.add(
        "2,\"Madang Airport\",\"Madang\",\"Papua New Guinea\",\"MAG\",\"AYMD\",-5.20707988739,145.789001465,20,10,\"U\",\"Pacific/Port_Moresby\",\"airport\",\"OurAirports\"");
    testLines.add(
        "3,\"Mount Hagen Kagamuga Airport\",\"Mount Hagen\",\"Papua New Guinea\",\"HGU\",\"AYMH\",-5.826789855957031,144.29600524902344,5388,10,\"U\",\"Pacific/Port_Moresby\",\"airport\",\"OurAirports\"");
  }

  @Test
  /**
   * Test that checkFileType throws an exception when called with a filename with an invalid
   * extension
   */
  public void testCheckFileTypeInvalidFileName() {
    try {
      loader.checkFileType("../seng202_project/src/test/java/TestFile/badFile.jpg");
      fail();
    } catch (Exception e) {
    }
  }

  @Test
  /**
   * Test that checkFileType doesn't throw an exception when called with a filename with a valid
   * extension
   */
  public void testCheckFileTypeValidFileName() {
    try {
      loader.checkFileType("../seng202_project/src/test/java/TestFiles/goodFile.csv");
    } catch (Exception e) {
      fail();
    }
  }

  @Test
  /** Test that checkFileType throws an exception when called with a file with no extension */
  public void testCheckFileTypeNoExtension() {
    try {
      loader.checkFileType("../seng202_project/src/test/java/TestFiles/airportsTest");
      fail();
    } catch (Exception e) {
    }
  }

  /**
   * Test that getFileName returns the correct filename for a file path containing forward slashes.
   */
  @Test
  public void testGetFileNameForwardSlash() {
    String filename = loader.getFileName("C:/Users/ellaj/Documents/GreenSkies/seng202_project/src/test/java/TestFiles/airlines.csv");
    assertEquals("airlines.csv", filename);
  }

  /**
   * Test that getFileName returns the correct filename for a filepath contiaining back slashes.
   */
  @Test
  public void testGetFileNameBackSlash() {
    String filename = loader.getFileName("C:\\Users\\ellaj\\Documents\\GreenSkies\\seng202_project\\src\\test\\java\\TestFiles\\airlines.csv");
    assertEquals("airlines.csv", filename);
  }

  /**
   * Test that getFileName returns the correct filename for a file path containing no slashes.
   */
  @Test
  public void testGetFileNameNoSlash() {
    String filename = loader.getFileName("airlines.csv");
    assertEquals("airlines.csv", filename);
  }

  /**
   * Test that checkDuplicateFileName raises an error when another file with the same name has been uploaded.
   */
  @Test
  public void testCheckDuplicateFileNameDuplicateUploaded() throws FileNotFoundException, FileSystemException, SQLException {
    loader.loadFile("C:\\Users\\ellaj\\Documents\\GreenSkies\\seng202_project\\src\\test\\java\\TestFiles\\airlines.csv", "Airline");
    try {
      loader.checkDuplicateFileName("airlines.csv");
      fail();
    } catch (RuntimeException e) {
      assertTrue(true);
    }
  }

  /**
   * Test that checkDuplicateFileName does not raise an exception when another file with the same name has not been uploaded.
   */
  @Test
  public void testCheckDuplicatFileNameDuplicateNotUploaded() throws FileNotFoundException, FileSystemException, SQLException {
    loader.loadFile("C:\\Users\\ellaj\\Documents\\GreenSkies\\seng202_project\\src\\test\\java\\TestFiles\\airlines.csv", "Airline");
    try {
      loader.checkDuplicateFileName("airports.csv");
      assertTrue(true);
    } catch (RuntimeException e) {
      fail();
    }
  }

  /**
   * Test that checkDuplicateFileName does not raise an exception when another file with the same name has been uploaded and then deleted.
   */
  @Test
  public void testCheckDuplicateFileNameDuplicateDeleted() throws FileNotFoundException, FileSystemException, SQLException {
    loader.loadFile("C:\\Users\\ellaj\\Documents\\GreenSkies\\seng202_project\\src\\test\\java\\TestFiles\\airlines.csv", "Airline");
    storage.getAirlineFileList().remove("airlines.csv");
    try {
      loader.checkDuplicateFileName("airlines.csv");
      assertTrue(true);
    } catch (RuntimeException e) {
      fail();
    }
  }

  @Test
  /** Test that openFile instantiates the Parser class with an ArrayList of Lines matching the contents of the file */
  public void testOpenFileValidFile() {
    ArrayList<String> actualLines = new ArrayList<String>();

    try {
      actualLines = loader.openFile("../seng202_project/src/test/java/TestFiles/airportsTest.csv");
    } catch (Exception e) {
      fail();
    }

    assertArrayEquals(testLines.toArray(), actualLines.toArray());
  }

  @Test
  /** Test that openFile throws an exception when a file cannot be found */
  public void testOpenFileNotFound() {
    try {
      ArrayList<String> lines =
          loader.openFile("..seng202_project/src/test/java/TestFiles/doesntExist.csv");
      fail();
    } catch (Exception e) {
    }
  }

  @Test
  /**
   * Test that constructParser instantiates a parser of the correct datatype when called with a
   * valid datatype
   */
  public void testConstructParserValid() {
    Parser testParser = loader.constructParser("Airport", testLines, false);
    assertTrue(testParser instanceof AirportParser);
  }

  @Test
  /** Test that constructParser throws an exception when called with an invalid datatype */
  public void testConstructParserInvalid() {
    try {
      loader.constructParser("plane", testLines, false);
      fail();
    } catch (IllegalArgumentException e) {
    }
  }

  @Test
  /**
   * Test that loadFile returns correct error message if called with an empty string for the
   * filename parameter
   */
  public void testLoadFileEmptyFilename() {
    try {
      String message = loader.loadFile("", "airport");
      fail();
    } catch (Exception e) {
      assertEquals("Filename cannot be empty.", e.getMessage());
    }
  }

  @Test
  /**
   * Test that loadFile returns correct error message if called with an empty string for the
   * datatype parameter
   */
  public void testLoadFileEmptyDatatype() {
    try {
      String message =
          loader.loadFile("../seng202_project/src/test/java/TestFiles/airportsTest.csv", "");
    } catch (Exception e) {
      assertEquals("Datatype cannot be empty.", e.getMessage());
    }
  }

  @Test
  /**
   * Test that a expected data is stored in Storage when loadFile is called with valid input for
   * filename and datatype
   */
  public void testLoadFileValid() {
    List<DataType> testRoutes = new ArrayList<>();
    testRoutes.add(new Route("2B", 410, "AER", 2965, "KZN", 2990, "", 0, "CR2".split(" ")));
    testRoutes.add(new Route("2B", 410, "ASF", 2966, "KZN", 2990, "", 0, "CR2".split(" ")));
    testRoutes.add(new Route("2B", 410, "ASF", 2966, "MRV", 2962, "", 0, "CR2".split(" ")));

    try {
      loader.loadFile("../seng202_project/src/test/java/TestFiles/routesTest.csv", "Route");
    } catch (Exception e) {
      fail();
    }

    assertArrayEquals(testRoutes.toArray(), storage.getRoutes().toArray());
  }
}

package model.loader;

import model.data.Storage;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.FileSystemException;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Unit tests for the Loader class
 * @author Ella Johnson
 */
public class LoaderTest {

    private Loader loader;
    private Storage storage;
    private ArrayList<String> testLines;

    @Before
    public void setUp() {
        storage = new Storage();
        loader = new Loader(storage);
        testLines = new ArrayList<>();

        testLines.add("1,\"Goroka Airport\",\"Goroka\",\"Papua New Guinea\",\"GKA\",\"AYGA\",-6.081689834590001,145.391998291,5282,10,\"U\",\"Pacific/Port_Moresby\",\"airport\",\"OurAirports\"");
        testLines.add("2,\"Madang Airport\",\"Madang\",\"Papua New Guinea\",\"MAG\",\"AYMD\",-5.20707988739,145.789001465,20,10,\"U\",\"Pacific/Port_Moresby\",\"airport\",\"OurAirports\"");
        testLines.add("3,\"Mount Hagen Kagamuga Airport\",\"Mount Hagen\",\"Papua New Guinea\",\"HGU\",\"AYMH\",-5.826789855957031,144.29600524902344,5388,10,\"U\",\"Pacific/Port_Moresby\",\"airport\",\"OurAirports\"");
    }

    @Test
    /** Test that checkFileType throws an exception when called with a filename with an invalid extension */
    public void testCheckFileTypeInvalidFileName() {
        try {
            loader.checkFileType("../seng202_project/src/test/java/TestFile/badFile.jpg");
            fail();
        } catch (Exception e) {
        }
    }

    @Test
    /** Test that checkFileType doesn't throw an exception when called with a filename with a valid extension */
    public void testCheckFileTypeValidFileName() {
        try {
            loader.checkFileType("../seng202_project/src/test/java/TestFiles/goodFile.csv");
        } catch (Exception e) {
            System.out.println(e.getMessage());
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

    @Test
    /* Test that openFile instantiates the Parser class with an ArrayList of Lines matching the contents of the file */
    public void testOpenFileValidFile() {
    ArrayList<String> actualLines = new ArrayList<String>();

        try {
            actualLines = loader.openFile("../seng202_project/src/test/java/TestFiles/airportsTest.csv");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail();
        }

        assertArrayEquals(testLines.toArray(), actualLines.toArray());

    }

    @Test
    /** Test that openFile throws an exception when a file cannot be found */
    public void testOpenFileNotFound() {
        try {
            ArrayList<String> lines = loader.openFile("..seng202_project/src/test/java/TestFiles/doesntExist.csv");
            fail();
        } catch (Exception e) {
        }
    }

    @Test
    /** Test that constructParser instantiates a parser of the correct datatype when called with a valid datatype */
    public void testConstructParserValid() {
        Parser testParser = loader.constructParser("airport", testLines);
        assertTrue(testParser instanceof AirportParser);
    }

    @Test
    /** Test that constructParser throws an exception when called with an invalid datatype */
    public void testConstructParserInvalid() {
        try {
            loader.constructParser("plane", testLines);
            fail();
        } catch (IllegalArgumentException e) {
        }
    }

    //TODO rewrite these tests so that they work without the getParser method
    //TODO additional tests for adding data to storage
//    @Test
//    /** Test that no parser is constructed if loadFile is called with an empty string for the filename parameter */
//    public void testLoadFileEmptyFilename() {
//        loader.loadFile("", "airport");
//        assertNull(loader.getParser());
//    }

//    @Test
//    /** Test that no parser is constructed if loadFile is called with an empty string for the datatype parameter */
//    public void testLoadFileEmptyDatatype() {
//        loader.loadFile("../seng202_project/src/test/java/TestFiles/airportsTest.csv", "");
//        assertNull(loader.getParser());
//    }

//    @Test
//    /** Test that a parser is constructed when loadFile is called with valid input for filename and datatype*/
//    public void testLoadFileValid() {

//        try {
//            loader.loadFile("../seng202_project/src/test/java/TestFiles/airportsTest.csv", "airport");
//        } catch (Exception e) {
//            System.out.println();
//        }
//        assertNotNull(loader.getParser());
//     }

}
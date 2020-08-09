package model.loader;

import org.junit.Before;
import org.junit.Test;

import java.nio.file.FileSystemException;

import static org.junit.Assert.*;

/**
 * Unit tests for the Loader class
 * @Author Ella Johnson
 */
public class LoaderTest {

    private Loader loader;

    @Before
    public void setUp() {
        loader = new Loader();
    }

    @Test
    /** Test that checkFileType throws an exception when called with a filename with an invalid extension */
    public void testCheckFileTypeInvalidFileName() {
        try {
            loader.checkFileType("badFile.jpg");
            fail();
        } catch (Exception e) {
        }
    }

    @Test
    /** Test that checkFileType doesn't throw an exception when called with a filename with a valid extension */
    public void testCheckFileTypeValidFileName() {
        try {
            loader.checkFileType("goodFile.csv");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    /** Test that checkFileType throws an exception when called with a file with no extension */
    public void testCheckFileTypeNoExtension() {
        try {
            loader.checkFileType("noExtension");
            fail();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    /** Test that openFile instantiates the Parser class with an ArrayList of Lines matching the contents of the file */
    public void testOpenFileValidFile() {

    }

    @Test
    /** Test that openFile throws an exception when a file cannot be found */
    public void testOpenFileNotFound() {

    }

    @Test
    /** Test that constructParser instantiates a parser of the correct datatype when called with a valid datatype */
    public void testConstructParserValid() {

    }

    @Test
    /** Test that constructParser throws an exception when called with an invalid datatype */
    public void testConstructParserInvalid() {

    }

    @Test
    /** Test that no parser is constructed if loadFile is called with an empty string for the filename parameter */
    public void testLoadFileEmptyFilename() {

    }

    @Test
    /** Test that no parser is constructed if loadFile is called with an empty string for the datatype parameter */
    public void testLoadFileEmptyDatatype() {

    }

    @Test
    /** Test that a parser is constructed when loadFile is called with valid input for filename and datatype */
    public void testLoadFileValid() {

    }

}
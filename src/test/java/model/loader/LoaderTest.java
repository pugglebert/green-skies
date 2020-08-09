package model.loader;

import org.junit.Before;
import org.junit.Test;

import java.nio.file.FileSystemException;

import static org.junit.Assert.*;

public class LoaderTest {

    private Loader loader;

    @Before
    public void setUp() {
        loader = new Loader();
    }

    @Test
    /** Test that openFile instantiates the Parser class with an ArrayList of Lines matching the contents of the file */
    public void testOpenFile() {

    }

    @Test
    /** Test that checkFileType throws an exception when called with a filename with an invalid extension */
    public void testCheckFileTypeInvalidFileName() {
        try {
            loader.checkFileType("badFile.jpg");
            fail();
        } catch (FileSystemException e) {
        }
    }

    @Test
    /** Test that checkFileType doesn't throw an exception when called with a filename with a valid extension */
    public void testCheckFileTypeValidFileName() {
        try {
            loader.checkFileType("goodFile.csv");
        } catch (FileSystemException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }


}
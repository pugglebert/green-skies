package controller.guiController.dataview;

import model.data.Storage;
import model.loader.Loader;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.nio.file.FileSystemException;
import java.sql.SQLException;

public class FlightHistoryControllerTest {

    @Before
    public void setUp() {
        Storage storage = new Storage();
        Loader loader = new Loader(storage);
        try {
            loader.loadFile("../seng202_project/src/test/java/TestFiles/GenerateReportTest.csv", "Route");
        } catch (FileSystemException | FileNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateReportStatsDeletionSingleRoute() {
    }
}
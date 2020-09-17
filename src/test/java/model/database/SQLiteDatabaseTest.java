package model.database;

import model.data.Airport;
import model.data.Route;
import model.data.Storage;
import model.loader.AirportParser;
import model.loader.Loader;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.nio.file.FileSystemException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Test cases for database.
 * @author Lambert
 * @since 18/09/2020
 * @version 1.0
 */

public class SQLiteDatabaseTest {
    private Storage storage = new Storage();
    private Loader loader = new Loader(storage);
    private SQLiteDatabase database = new SQLiteDatabase();
    private Statement state;
    private PreparedStatement prep;
    private ResultSet res;
    private static Connection con;

    @Before
    public void setUp() throws FileSystemException, SQLException, ClassNotFoundException {
        try{
            loader.loadFile("../seng202_project/src/test/java/TestFiles/singleairport.csv", "Airport");
            loader.loadFile("../seng202_project/src/test/java/TestFiles/singleairline.csv", "Airline");
            loader.loadFile("../seng202_project/src/test/java/TestFiles/singleRoute.csv", "Route");
            con = database.getCon();
        } catch (FileNotFoundException e) {
        }
    }

    @Test
    public void isTableAirportReseted() throws SQLException {
        database.initialiseTable("airports");
        state = con.createStatement();
        res = state.executeQuery(
                        "SELECT name FROM sqlite_master WHERE type='table' AND name='airports'");
        assertTrue(res.next());
    }

    @Test
    public void isTableAirlinesReseted() throws SQLException {
        database.initialiseTable("airlines");
        state = con.createStatement();
        res = state.executeQuery(
                "SELECT name FROM sqlite_master WHERE type='table' AND name='airlines'");
        assertTrue(res.next());
    }

    @Test
    public void isTableRoutesReseted() throws SQLException {
        database.initialiseTable("routes");
        state = con.createStatement();
        res = state.executeQuery(
                "SELECT name FROM sqlite_master WHERE type='table' AND name='routes'");
        assertTrue(res.next());
    }

    @Test
    public void isAutoCommiteClosed() throws SQLException {
        database.closeAutoCommite();
        database.initialiseTable("routes");
        state = con.createStatement();
        res = state.executeQuery("select * from 'routes'");
        assertTrue(res.next());
    }
}


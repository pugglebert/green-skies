package model.database;

import model.data.*;
import model.loader.Loader;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.nio.file.FileSystemException;
import java.sql.*;

import static org.junit.Assert.*;

/**
 * Test cases for database.
 *
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

  /**
   *Setup storage and database with one row in each table and list in storage.
   */
  @Before
  public void setUp() throws FileSystemException, SQLException, ClassNotFoundException {
    try {
      loader.loadFile("../seng202_project/src/test/java/TestFiles/singleairport.csv", "Airport");
      loader.loadFile("../seng202_project/src/test/java/TestFiles/singleairline.csv", "Airline");
      loader.loadFile("../seng202_project/src/test/java/TestFiles/singleRoute.csv", "Route");
      con = database.getCon();
    } catch (FileNotFoundException e) {
    }
  }

  /**
   * Check if initialiseTable method resets airport table
   */
  @Test
  public void isTableAirportReseted() throws SQLException {
    database.initialiseTable("airports");
    state = con.createStatement();
    res =
        state.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='airports'");
    assertTrue(res.next());

    res.close();
    state.close();

  }

  /**
   * Check if initialiseTable method resets ainlines table.
   */
  @Test
  public void isTableAirlinesReseted() throws SQLException {
    database.initialiseTable("airlines");
    state = con.createStatement();
    res =
        state.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='airlines'");
    assertTrue(res.next());

    res.close();
    state.close();

  }

  /**
   * Check if initialiseTable method resets routes table.
   */
  @Test
  public void isTableRoutesReseted() throws SQLException {
    database.initialiseTable("routes");
    state = con.createStatement();
    res = state.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='routes'");
    assertTrue(res.next());

    res.close();
    state.close();

  }

  /**
   * Check if Auto commite feature for database will be set off by closeAutoCommite method.
   */
  @Test
  public void isAutoCommiteClosed() throws SQLException {
    database.closeAutoCommite();
    database.initialiseTable("routes");
    state = con.createStatement();
    res = state.executeQuery("select * from 'routes'");
    assertFalse(res.next());

    res.close();
    state.close();

  }

  /**
   * Check if buildAirportsTable will create a airports table if there is no airports table.
   */
  @Test
  public void isAirportsTableBuilt() throws Exception {
    database.initialiseTable("airport");
    state = con.createStatement();
    state.executeUpdate("drop table airports");
    state = con.createStatement();
    res =
        state.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='airports'");
    try {
      if (!res.next()) {
        database.buildAirportsTable();
        res =
            state.executeQuery(
                "SELECT name FROM sqlite_master WHERE type='table' AND name='airports'");

        assertTrue(res.next());
        res.close();
        state.close();

      }
    } catch (Exception e) {
      throw new Exception("Table not drop successfully.");
    } finally{

    }
  }

  /**
   * Check if buildAirportsTable will create a routes table if there is no routes table.
   */
  @Test
  public void isRoutesTableBuilt() throws Exception {
    database.initialiseTable("routes");
    con.close();
    database.buildConnection();
    con = database.getCon();
    state = con.createStatement();
    state.executeUpdate("drop table routes");
    state = con.createStatement();
    res = state.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='routes'");
    try {
      if (!res.next()) {
        database.buildRoutesTable();
        res =
            state.executeQuery(
                "SELECT name FROM sqlite_master WHERE type='table' AND name='routes'");

        assertTrue(res.next());
        res.close();
        state.close();
      }
    } catch (Exception e) {
      throw new Exception("Table not drop successfully.");
    } finally{

    }
  }

  /**
   * Check if buildAirportsTable will create a airlines table if there is no airlines table.
   */
  @Test
  public void isAirlinesTableBuilt() throws Exception {
    database.initialiseTable("airlines");
    state = con.createStatement();
    state.executeUpdate("drop table airlines");
    state = con.createStatement();
    res =
        state.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='airlines'");
    try {
      if (!res.next()) {
        database.buildAirlinesTable();
        res =
            state.executeQuery(
                "SELECT name FROM sqlite_master WHERE type='table' AND name='airlines'");

        assertTrue(res.next());
        res.close();
        state.close();
      }
    } catch (Exception e) {
      throw new Exception("Table not drop successfully.");
    } finally{

    }
  }

  /**
   * Check if addAirport method will insert given data into databases.
   */
  @Test
  public void isAirportAdded() throws SQLException {
    Airport airport =
        new Airport(14 , "Husavik", "Husavik", "Iceland", "HZK", "BIHU", 65.952328, -17.425978, 48, 0, "N", "Atlantic/Reykjavik");
    assertEquals(airport, storage.getAirports().get(0));
  }

  /**
   * Check if addRoute method will insert given data into databases.
   */
  @Test
  public void isRouteAdded() throws SQLException {
    String[] equipment = new String[]{"CR2"};
    Route route =
            new Route("2B" , 410, "EGO", 6156, "KZN", 2990, "", 0, equipment);
    assertEquals(route, storage.getRoutes().get(0));
  }

  /**
   * Check if addAirline method will insert given data into databases.
   */
  @Test
  public void isAirlineAdded() throws SQLException {
    Airline airline =
            new Airline(6, "223 Flight Unit State Airline", "\\N", "", "CHD", "CHKALOVSK-AVIA", "Russia", false);
    assertEquals(airline, storage.getAirlines().get(0));
  }

  /**
   * Check if initialiseStorage method will exist airport data from database into airport data list of storage.
   */
  @Test
  public void isStorageAirportInited() throws Exception {
    storage.resetAirportsList();
    if(storage.getAirports().size() == 0){
      database.initialiseStorage(storage);
      assertEquals(storage.getAirports().size(), 1);
    } else {
      throw new Exception ("Airports table not reset successfully.");
    }
  }

  /**
   * Check if initialiseStorage method will exist airline data from database into airlines data list of storage.
   */
  @Test
  public void isStorageAirlineInited() throws Exception {
    storage.resetAirlinesList();
    if(storage.getAirlines().size() == 0){
      database.initialiseStorage(storage);
      assertEquals(storage.getAirlines().size(), 1);
    } else {
      throw new Exception ("Airline table not reset successfully.");
    }
  }

  /**
   * Check if initialiseStorage method will exist route data from database into routes data list of storage.
   */
  @Test
  public void isStorageRouteInited() throws Exception {
    storage.resetRoutesList();
    if(storage.getRoutes().size() == 0){
      database.initialiseStorage(storage);
      assertEquals(storage.getRoutes().size(), 1);
    } else {
      throw new Exception ("Route table not reset successfully.");
    }
  }
}

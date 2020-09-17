package model.database;

import model.data.*;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

/**
 * Class to store presistent data in database.
 *
 * @author Lambert
 * @since 2020-09-18
 * @version 1.2
 */
public class SQLiteDatabase {
  /** database connection */
  private static Connection con;

  private Statement builtTable;

  private Statement state;

  private PreparedStatement prep;

  private ResultSet res;

  /**
   * This method create connection to local database, and will create one if there is no database.
   */
  protected void getConnection() {
    try {
      // sqlite driver
      Class.forName("org.sqlite.JDBC");
      // database path, if it's new database, it will be created in the project folder
      con = DriverManager.getConnection("jdbc:sqlite:app.sqlite");
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, e);
    }
  }

  /**
   * This method returns connection as an object.
   */
  public Connection getCon(){
    return con;
  }

  /**
   * This method closes auto-commit feature for database to store all query in one transection, it
   * will help to significantly improve insertion speed.
   */
  public void closeAutoCommite() {
    try {
      con.setAutoCommit(false);
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, e);
    }
  }

  /** This method manully starts commite for database. */
  public void startCommite() {
    try {
      con.commit();
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, e);
    } finally{
      try{
//      con.close();
      } catch (Exception e){
        JOptionPane.showMessageDialog(null, e);
      }
    }
  }

  /** This method builds airports table with airport attributes as colunms in dastabase. */
  protected void buildAirportsTable() {
    if (con == null) {
      // get connection
      getConnection();
    }

    try {
      builtTable = con.createStatement();
      builtTable.executeUpdate(
          "create table airports(airport_id integer,"
              + "name varchar(60),"
              + "city varchar(60),"
              + "country varchar(60),"
              + "IATA varchar(5),"
              + "ICAO varchar(5),"
              + "lat double(4, 6),"
              + "lon double(4, 6),"
              + "alt integer,"
              + "timezone float,"
              + "DST varchar(60),"
              + "DBTimezone varchar(60),"
              + "primary key (airport_id))");
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, e);
    } finally {
      try {
        builtTable.close();
      } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e);
      }
    }
  }

  /** This method builds routes table with routes attributes as colunms in dastabase. */
  protected void buildRoutesTable() {
    if (con == null) {
      // get connection
      getConnection();
    }

    try {
      builtTable = con.createStatement();
      builtTable.executeUpdate(
          "create table routes(route_id integer,"
              + "airlineName varchar(60),"
              + "airlineID integer,"
              + "sourceAirport varchar(60),"
              + "sourceAirportID integer,"
              + "destinationAirport varchar(60),"
              + "destinationAirportID integer,"
              + "codeShare varchar(60),"
              + "numOfStops integer,"
              + "equipment varchar(256),"
              + "emissions double(100, 10),"
              + "distance double(100, 10),"
              + "timesTaken integer,"
              + "primary key (route_id))");
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, e);
    } finally {
      try {
        builtTable.close();
      } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e);
      }
    }
  }

  /** This method builds airlines table with airline attributes as colunms in dastabase. */
  protected void buildAirlinesTable() {
    if (con == null) {
      // get connection
      getConnection();
    }

    try {
      builtTable = con.createStatement();
      builtTable.executeUpdate(
          "create table airlines(airline_id integer,"
              + "airlineName varchar(60),"
              + "alias varchar(60),"
              + "IATA varchar(5),"
              + "ICAO varchar(5),"
              + "callsign varchar(60),"
              + "country varchar(60),"
              + "activeStatus boolean,"
              + " primary key (airline_id))");
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, e);
    } finally {
      try {
        builtTable.close();
      } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e);
      }
    }
  }

  /**
   * This method insert all attributes of an airport object into database.
   *
   * @param airport Object of airport contains information of airport as attributes.
   */
  public void addAirports(Airport airport) {
    if (con == null) {
      // get connection
      getConnection();
    }

    try {
      prep = con.prepareStatement("insert into airports values(?,?,?,?,?,?,?,?,?,?,?,?);");
      prep.setInt(1, airport.getAirportID());
      prep.setString(2, airport.getName());
      prep.setString(3, airport.getCity());
      prep.setString(4, airport.getCountry());
      prep.setString(5, airport.getIATA());
      prep.setString(6, airport.getICAO());
      prep.setDouble(7, airport.getLatitude());
      prep.setDouble(8, airport.getLongitude());
      prep.setInt(9, airport.getAltitude());
      prep.setFloat(10, airport.getTimezone());
      prep.setString(11, airport.getDST());
      prep.setString(12, airport.getDataBaseTimeZone());
      prep.execute();
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, e);
    } finally {
      try {
        prep.close();
      } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e);
      }
    }
  }

  /**
   * This method insert all attributes of an route object into database.
   *
   * @param route Object of route contains information of route as attributes.
   */
  public void addRoutes(Route route) {
    if (con == null) {
      // get connection
      getConnection();
    }

    try {
      prep = con.prepareStatement("insert into routes values(?,?,?,?,?,?,?,?,?,?,?,?,?);");
      prep.setString(2, route.getAirlineName());
      prep.setInt(3, route.getAirlineID());
      prep.setString(4, route.getSourceAirport());
      prep.setInt(5, route.getSourceAirportID());
      prep.setString(6, route.getDestinationAirport());
      prep.setInt(7, route.getDestinationAirportID());
      prep.setString(8, route.getCodeShare());
      prep.setInt(9, route.getNumOfStops());

      String[] equipments = route.getEquipment();
      String stringEquipment = "";
      if (equipments != null) {
        for (String equipment : equipments) {
          stringEquipment = stringEquipment + " " + equipment;
        }
      }

      prep.setString(10, stringEquipment);
      prep.setDouble(11, route.getEmissions());
      prep.setDouble(12, route.getDistance());
      prep.setInt(13, route.getTimesTaken());
      prep.execute();
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, e);
    } finally {
      try {
        prep.close();
      } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e);
      }
    }
  }

  /**
   * This method insert all attributes of an airline object into database.
   *
   * @param airline Object of airline contains information of airline as attributes.
   */
  public void addAirlines(Airline airline) {
    if (con == null) {
      // get connections
      getConnection();
    }

    try {
      prep = con.prepareStatement("insert into airlines values(?,?,?,?,?,?,?,?);");
      prep.setInt(1, airline.getAirlineID());
      prep.setString(2, airline.getName());
      prep.setString(3, airline.getAirlineAlias());
      prep.setString(4, airline.getIATA());
      prep.setString(5, airline.getICAO());
      prep.setString(6, airline.getCallsign());
      prep.setString(7, airline.getCountry());
      prep.setBoolean(8, airline.getActiveStatus());
      prep.execute();
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, e);
    } finally {
      try {
        prep.close();
      } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e);
      }
    }
  }

  /**
   * This method initialise table according to provided tabletype, it deletes table and recreates
   * it.
   *
   * @param tableType Three types of table conresponding to airport, route and airline.
   */
  public void initialiseTable(String tableType) {
    if (con == null) {
      // get connection
      getConnection();
    }

    if (tableType.equals("airports")) {
      try {
        state = con.createStatement();
        res =
            state.executeQuery(
                "SELECT name FROM sqlite_master WHERE type='table' AND name='airports'");
        if (res.next()) {
          state = con.createStatement();
          state.executeUpdate("Delete from 'airports'");
        } else {
          buildAirportsTable();
        }
      } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e);
      } finally {
        try {
          res.close();
          state.close();
        } catch (Exception e) {
          JOptionPane.showMessageDialog(null, e);
        }
      }
    } else if (tableType.equals("routes")) {
      try {
        state = con.createStatement();
        res =
            state.executeQuery(
                "SELECT name FROM sqlite_master WHERE type='table' AND name='routes'");
        if (res.next()) {
          state = con.createStatement();
          state.executeUpdate("Delete from 'routes'");
        } else {
          buildRoutesTable();
        }
      } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e);
      } finally {
        try {
          res.close();
          state.close();
        } catch (Exception e) {
          JOptionPane.showMessageDialog(null, e);
        }
      }
    } else if (tableType.equals("airlines")) {
      try {
        state = con.createStatement();
        res =
            state.executeQuery(
                "SELECT name FROM sqlite_master WHERE type='table' AND name='airlines'");
        if (res.next()) {
          state = con.createStatement();
          state.executeUpdate("Delete from 'airlines'");
        } else {
          buildAirlinesTable();
        }
      } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e);
      } finally {
        try {
          res.close();
          state.close();
        } catch (Exception e) {
          JOptionPane.showMessageDialog(null, e);
        }
      }
    }
  }

  /**
   * This method initialise storage with data from database.
   *
   * @param storage
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  public void initialiseStorage(Storage storage) {
    if (con == null) {
      // get connections
      getConnection();
    }
    try {
      state = con.createStatement();
      res =
          state.executeQuery(
              "SELECT name FROM sqlite_master WHERE type='table' AND name='airports'");

      if (res.next()) {
        state = con.createStatement();
        res = state.executeQuery("select * from 'airports'");
        ArrayList<DataType> airports = new ArrayList<>();
        while (res.next()) {
          int airportId = res.getInt("airport_id");
          String name = res.getString("name");
          String city = res.getString("city");
          String country = res.getString("country");
          String IATA = res.getString("IATA");
          String ICAO = res.getString("ICAO");
          double lat = res.getDouble("lat");
          double lon = res.getDouble("lon");
          int alt = res.getInt("alt");
          float timezone = res.getFloat("timezone");
          String DST = res.getString("DST");
          String DBTimezone = res.getString("DBTimezone");
          Airport airport =
              new Airport(
                  airportId,
                  name,
                  city,
                  country,
                  IATA,
                  ICAO,
                  lat,
                  lon,
                  alt,
                  timezone,
                  DST,
                  DBTimezone);
          airports.add(airport);
        }
        storage.setData(airports, "Airport");
      }

      state = con.createStatement();
      res =
          state.executeQuery(
              "SELECT name FROM sqlite_master WHERE type='table' AND name='airlines'");

      if (res.next()) {
        state = con.createStatement();
        res = state.executeQuery("select * from 'airlines'");
        ArrayList<DataType> airlines = new ArrayList<>();
        while (res.next()) {
          int airlineId = res.getInt("airline_id");
          String airlineName = res.getString("airlineName");
          String alias = res.getString("alias");
          String IATA = res.getString("IATA");
          String ICAO = res.getString("ICAO");
          String callsign = res.getString("callsign");
          String country = res.getString("country");
          Boolean activeStatus = res.getBoolean("activeStatus");
          Airline airline =
              new Airline(
                  airlineId, airlineName, alias, IATA, ICAO, callsign, country, activeStatus);
          airlines.add(airline);
        }
        storage.setData(airlines, "Airline");
      }

      state = con.createStatement();
      res =
          state.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='routes'");

      if (res.next()) {
        state = con.createStatement();
        res = state.executeQuery("select * from 'routes'");
        ArrayList<DataType> routes = new ArrayList<>();
        while (res.next()) {
          //                int route_id = routesRow.getInt("route_id");
          String airlineName = res.getString("airlineName");
          int airlineID = res.getInt("airlineID");
          String sourceAirport = res.getString("sourceAirport");
          int sourceAirportID = res.getInt("sourceAirportID");
          String destinationAirport = res.getString("destinationAirport");
          int destinationAirportID = res.getInt("destinationAirportID");
          String codeShare = res.getString("codeShare");
          int numOfStops = res.getInt("numOfStops");

          String equipment = res.getString("equipment");
          //                System.out.println(equipment);
          String[] equipmentArray;
          if (equipment != null) {
            equipmentArray = equipment.split(" ");
          } else {
            equipmentArray = null;
          }

          double emissions = res.getDouble("emissions");
          double distance = res.getDouble("distance");
          int timesTaken = res.getInt("timesTaken");

          Route route =
              new Route(
                  airlineName,
                  airlineID,
                  sourceAirport,
                  sourceAirportID,
                  destinationAirport,
                  destinationAirportID,
                  codeShare,
                  numOfStops,
                  equipmentArray);
          route.setEmissions(emissions);
          route.setTimesTaken(timesTaken);
          route.setDistance(distance);
          routes.add(route);
        }
        storage.setData(routes, "Route");
      }
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, e);
    } finally {
      try {
        res.close();
        state.close();
//        con.close();
      } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e);
      }
    }
  }
}

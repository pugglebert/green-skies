package model.database;

import model.data.*;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

/**
 * Class to store presistent data in database.
 *
 * @author Lambert
 * @since 18/09/2020
 * @version 1.2
 */
public class SQLiteDatabase {
  /** database connection. */
  private static Connection con;

  /** statement for building tables. */
  private Statement builtTable;

  /** Vairable that contains statement for database. */
  private Statement state;

  /** Variable that contains statement for prepare statement database. */
  private PreparedStatement prep;

  /** Variable that contains result fetched form database. */
  private ResultSet res;

  /** Variable for table name that is going to be created in database. */
  private String tableName;

  /**
   * This method create connection to local database, and will create one if there is no database.
   */
  protected void buildConnection() {

    try {
      // sqlite driver
      Class.forName("org.sqlite.JDBC");
      // database path, if it's new database, it will be created in the project folder
      con = DriverManager.getConnection("jdbc:sqlite:app.sqlite");
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, e);
    }
  }

  /** This method is getter for connection returns connection as an object. */
  public Connection getCon() {
    return con;
  }

  /**
   * This method closes auto-commit feature for database to store all query in one transection, it
   * will help to significantly improve insertion speed.
   */
  public void closeAutoCommite() {
    if (con == null) {
      // get connection
      buildConnection();
    }
    try {
      con.setAutoCommit(false);
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, e);
    }
  }

  /** This method manully starts commite for database. */
  public void startCommite() {
    if (con == null) {
      // get connection
      buildConnection();
    }
    try {
      con.commit();
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, e);
    }
//    finally {
//      try {
//      } catch (Exception e) {
//        JOptionPane.showMessageDialog(null, e);
//      }
//    }
  }

  /**
   * This method sets table name that is going to be created in database.
   * @param fileName Provided file name.
   */
  public void setTableName(String fileName){
    this.tableName = "'" + fileName.split("\\.")[0] + "'";
  }

  /** This method builds airports table with airport attributes as colunms in dastabase. */
  protected void buildAirportsTable() {
    if (con == null) {
      // get connection
      buildConnection();
    }

    try {
      builtTable = con.createStatement();
      builtTable.executeUpdate(
          "create table "+ tableName + "(airport_id integer,"
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
      startCommite();
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
      buildConnection();
    }

    try {
      builtTable = con.createStatement();
      builtTable.executeUpdate(
          "create table " + tableName + "(route_id integer,"
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
      startCommite();
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

  /** This method builds airlines table with given name and with airline attributes as colunms in dastabase. */
  protected void buildAirlinesTable() {
    if (con == null) {
      // get connection
      buildConnection();
    }

    try {
      builtTable = con.createStatement();
      builtTable.executeUpdate(
          "create table" + tableName +"(airline_id integer,"
              + "airlineName varchar(60),"
              + "alias varchar(60),"
              + "IATA varchar(5),"
              + "ICAO varchar(5),"
              + "callsign varchar(60),"
              + "country varchar(60),"
              + "activeStatus boolean,"
              + " primary key (airline_id))");
      startCommite();
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
      buildConnection();
    }

    try {
      prep = con.prepareStatement("insert into " + tableName + " values(?,?,?,?,?,?,?,?,?,?,?,?);");
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
      buildConnection();
    }

    try {
      prep = con.prepareStatement("insert into " + tableName +" values(?,?,?,?,?,?,?,?,?,?,?,?,?);");
      prep.setString(2, route.getAirlineName());
      prep.setInt(3, route.getAirlineID());
      prep.setString(4, route.getSourceAirport());
      prep.setInt(5, route.getSourceAirportID());
      prep.setString(6, route.getDestinationAirport());
      prep.setInt(7, route.getDestinationAirportID());
      prep.setString(8, route.getCodeShare());
      prep.setInt(9, route.getNumOfStops());

      String[] equipments = route.getEquipment();
      StringBuilder stringEquipment = new StringBuilder();
      if (equipments != null) {
        for (String equipment : equipments) {
          stringEquipment.append(" ").append(equipment);
        }
      }

      prep.setString(10, stringEquipment.toString());
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
      buildConnection();
    }

    try {
      prep = con.prepareStatement("insert into" + tableName + "values(?,?,?,?,?,?,?,?);");
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
      buildConnection();
    }
    switch (tableType) {
      case "Airport":
        try {
          state = con.createStatement();
          res =
              state.executeQuery(
                  "SELECT name FROM sqlite_master WHERE type='table' AND name=" + tableName);
          if (res.next()) {
            state = con.createStatement();
            state.executeUpdate("Delete from " + tableName);
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
        break;
      case "Route":
        try {
          state = con.createStatement();
          res =
              state.executeQuery(
                  "SELECT name FROM sqlite_master WHERE type='table' AND name=" + tableName);
          if (res.next()) {
            state = con.createStatement();
            state.executeUpdate("Delete from " + tableName);
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
        break;
      case "Airline":
        try {
          state = con.createStatement();
          res =
              state.executeQuery(
                      "SELECT name FROM sqlite_master WHERE type='table' AND name=" + tableName);
          if (res.next()) {
            state = con.createStatement();
            state.executeUpdate("Delete from " + tableName);
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
        break;
    }
  }

  /**
   * This method initialise storage with data from database.
   *
   * @param storage The storage used.
   * @throws ClassNotFoundException This throws a ClassNotFoundException.
   */
  public void initialiseStorage(Storage storage) {
    if (con == null) {
      // get connections
      buildConnection();
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
        storage.setData(airports, "Airport", null);
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

    try {
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
        storage.setData(airlines, "Airline", null);
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

    try {
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
          String[] equipmentArray;
          if (equipment != null) {
            equipmentArray = equipment.split(" ");
          } else {
            equipmentArray = null;
          }

          double emissions = res.getDouble("emissions");
          double distance = res.getDouble("distance");
          int timesTaken = res.getInt("timesTaken");

          assert equipmentArray != null;
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
        storage.setData(routes, "Route", null);
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

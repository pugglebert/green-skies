package model.database;

import javafx.beans.binding.StringExpression;
import model.data.*;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;

public class SQLiteDatabase {
    private static Connection con;
//    private static boolean hasData = false;

    private void getConnection() throws ClassNotFoundException, SQLException {
        // sqlite driver
        Class.forName("org.sqlite.JDBC");
        // database path, if it's new database, it will be created in the project folder
        con = DriverManager.getConnection("jdbc:sqlite:app.sqlite");
    }
    public void closeAutoCommite() throws SQLException {
        con.setAutoCommit(false);
    }

    public void startCommite() throws SQLException {
        con.commit();
    }

    private void buildAirportsTable() throws SQLException {
            Statement builtTable = con.createStatement();
            builtTable.executeUpdate("create table airports(airport_id integer," + "name varchar(60)," + "city varchar(60),"
                    + "country varchar(60)," + "IATA varchar(5)," + "ICAO varchar(5),"
                    + "lat double(4, 6)," + "lon double(4, 6)," + "alt integer," + "timezone float," + "DST varchar(60),"
                    + "DBTimezone varchar(60)," + "primary key (airport_id))");

    }

    private void buildRoutesTable() throws SQLException{
        Statement builtTable = con.createStatement();
        builtTable.executeUpdate("create table routes(route_id integer," + "airlineName varchar(60)," + "airlineID integer,"
                + "sourceAirport varchar(60)," + "sourceAirportID integer," + "destinationAirport varchar(60),"
                + "destinationAirportID integer," + "codeShare varchar(60)," + "numOfStops integer," + "equipment varchar(256)," + "emissions double(100, 10),"
                + "distance double(100, 10)," + "timesTaken integer," + "primary key (route_id))");
    }

    private void buildAirlinesTable() throws SQLException{
        Statement builtTable = con.createStatement();
        builtTable.executeUpdate("create table airlines(airline_id integer,"  +  "airlineName varchar(60)," + "alias varchar(60),"
                + "IATA varchar(5)," + "ICAO varchar(5)," + "callsign varchar(60)," + "country varchar(60)," + "activeStatus boolean," + " primary key (airline_id))");
    }

    public void addAirports(Airport airport) throws ClassNotFoundException, SQLException {
        if(con == null) {
            // get connection
            getConnection();
        }

        PreparedStatement prep = con.prepareStatement("insert into airports values(?,?,?,?,?,?,?,?,?,?,?,?);");
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
    }

    public void addRoutes(Route route) throws ClassNotFoundException, SQLException {
        if(con == null) {
            // get connection
            getConnection();
        }

        PreparedStatement prep = con.prepareStatement("insert into routes values(?,?,?,?,?,?,?,?,?,?,?,?,?);");
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
        if(equipments != null) {
            for (String equipment : equipments) {
                stringEquipment = stringEquipment + " " + equipment;
            }
        }

        prep.setString(10, stringEquipment);
        prep.setDouble(11, route.getEmissions());
        prep.setDouble(12, route.getDistance());
        prep.setInt(13, route.getTimesTake());
        prep.execute();
    }

    public void addAirlines(Airline airline) throws ClassNotFoundException, SQLException {
        if(con == null) {
            // get connections
            getConnection();
        }

        PreparedStatement prep = con.prepareStatement("insert into airlines values(?,?,?,?,?,?,?,?);");
        prep.setInt(1, airline.getAirlineID());
        prep.setString(2, airline.getName());
        prep.setString(3, airline.getAirlineAlias());
        prep.setString(4, airline.getIATA());
        prep.setString(5, airline.getICAO());
        prep.setString(6, airline.getCallsign());
        prep.setString(7, airline.getCountry());
        prep.setBoolean(8, airline.getActiveStatus());
        prep.execute();
    }

    public void dropTable(String tableType) throws ClassNotFoundException, SQLException{
        if(con == null) {
            // get connection
            getConnection();
        }
        if(tableType.equals("airports")){
            Statement state = con.createStatement();
            ResultSet res = state.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='airports'");
            if(res.next()){
                Statement dropState = con.createStatement();
                dropState.executeUpdate("Delete from 'airports'");
            } else {
                buildAirportsTable();
            }
        } else if(tableType.equals("routes")){
            Statement state = con.createStatement();
            ResultSet res = state.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='routes'");
            if(res.next()){
                Statement dropState = con.createStatement();
                dropState.executeUpdate("Delete from 'routes'");
            } else {
                buildRoutesTable();
            }
        } else if(tableType.equals("airlines")){
            Statement state = con.createStatement();
            ResultSet res = state.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='airlines'");
            if(res.next()){
                Statement dropState = con.createStatement();
                dropState.executeUpdate("Delete from 'airlines'");
            } else {
                buildAirlinesTable();
            }
        }
    }

    public void initialiseStorage(Storage storage) throws SQLException, ClassNotFoundException {
        if(con == null) {
            // get connections
            getConnection();
        }

        Statement state = con.createStatement();
        ResultSet hasAirportTable = state.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='airports'");

        if(hasAirportTable.next()){
            Statement airportsTable = con.createStatement();
            ResultSet airportRow = airportsTable.executeQuery("select * from 'airports'");
            ArrayList<DataType> airports = new ArrayList<>();
            while(airportRow.next()){
                int airportId = airportRow.getInt("airport_id");
                String name = airportRow.getString("name");
                String city = airportRow.getString("city");
                String country = airportRow.getString("country");
                String IATA = airportRow.getString("IATA");
                String ICAO = airportRow.getString("ICAO");
                double lat = airportRow.getDouble("lat");
                double lon = airportRow.getDouble("lon");
                int alt = airportRow.getInt("alt");
                float timezone = airportRow.getFloat("timezone");
                String DST = airportRow.getString("DST");
                String DBTimezone = airportRow.getString("DBTimezone");
                Airport airport = new Airport(airportId, name, city, country, IATA, ICAO, lat, lon, alt, timezone, DST, DBTimezone);
                airports.add(airport);
            }
             storage.setData(airports, "Airport");
        }

        state = con.createStatement();
        ResultSet hasAirlinesTable = state.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='airlines'");

        if(hasAirlinesTable.next()){
            Statement airlinesTable = con.createStatement();
            ResultSet airlineRow = airlinesTable.executeQuery("select * from 'airlines'");
            ArrayList<DataType> airlines = new ArrayList<>();
            while(airlineRow.next()){
                int airlineId = airlineRow.getInt("airline_id");
                String airlineName = airlineRow.getString("airlineName");
                String alias = airlineRow.getString("alias");
                String IATA = airlineRow.getString("IATA");
                String ICAO = airlineRow.getString("ICAO");
                String callsign = airlineRow.getString("callsign");
                String country = airlineRow.getString("country");
                Boolean activeStatus = airlineRow.getBoolean("activeStatus");
                Airline airline = new Airline(airlineId, airlineName, alias, IATA, ICAO, callsign, country, activeStatus);
                airlines.add(airline);
            }
            storage.setData(airlines, "Airline");
        }

        state = con.createStatement();
        ResultSet hasRouteTable = state.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='routes'");

        if(hasRouteTable.next()){
            Statement routesTable = con.createStatement();
            ResultSet routesRow = routesTable.executeQuery("select * from 'routes'");
            ArrayList<DataType> routes = new ArrayList<>();
            while(routesRow.next()){
//                int route_id = routesRow.getInt("route_id");
                String airlineName = routesRow.getString("airlineName");
                int airlineID = routesRow.getInt("airlineID");
                String sourceAirport = routesRow.getString("sourceAirport");
                int sourceAirportID = routesRow.getInt("sourceAirportID");
                String destinationAirport = routesRow.getString("destinationAirport");
                int destinationAirportID = routesRow.getInt("destinationAirportID");
                String codeShare = routesRow.getString("codeShare");
                int numOfStops = routesRow.getInt("numOfStops");

                String equipment = routesRow.getString("equipment");
                System.out.println(equipment);
                String[] equipmentArray;
                if(equipment != null) {
                    equipmentArray = equipment.split(" ");
                } else {
                    equipmentArray = null;
                }

                double emissions = routesRow.getDouble("emissions");
                double distance = routesRow.getDouble("distance");
                int timesTaken = routesRow.getInt("timesTaken");

                Route route = new Route(airlineName, airlineID, sourceAirport, sourceAirportID, destinationAirport, destinationAirportID, codeShare, numOfStops, equipmentArray);
                route.setEmissions(emissions);
                route.setTimesTaken(timesTaken);
                route.setDistance(distance);
                routes.add(route);
            }
            storage.setData(routes, "Route");
        }
    }
}

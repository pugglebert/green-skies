package controller.main;

import model.data.Route;
import model.data.Storage;
import model.database.SQLiteTest;
import model.loader.Loader;

import java.sql.ResultSet;

/**
 * The controller class which contains the controls for the main.
 * @author Hayley Krippner, Ella Johnson.
 * @version 1.0
 * @since 04/09/20
 */
public class Main {

    private static Storage storage = new Storage();
    private static Loader loader = new Loader(storage);

    public static Storage getStorage() {
        return storage;
    }

    public static Loader getLoader() {
        return loader;
    }

    public static void main(String[] args) {
//        Route route1 = new Route("a", 123, "abc", 123, "abc", 123, "ABC", 0, null);
//        Route route2 = new Route("b", 124, "hkk", 144, "kkn", 654, "SSH", 0, null);
//        Route route3 = new Route("c", 125, "czg", 187, "whi", 739, "PPO", 0, null);
//        Route route4 = new Route("d", 126, "wsk", 216, "ggu", 679, "JGF", 0, null);
//        route1.setDistance(1234);
//        route1.setEmissions(56);
//        route2.setDistance(7899);
//        route2.setEmissions(160);
//        route3.setDistance(3999);
//        route3.setEmissions(43);
//        route4.setDistance(13999);
//        route4.setEmissions(315);
//        storage.history.add(route1);
//        storage.history.add(route2);
//        storage.history.add(route3);
//        storage.history.add(route4);
//        SQLiteTest test = new SQLiteTest();
//        ResultSet rs;
//
//        try {
//            rs = test.displayUsers();
//            test.addUser("abc", "tom");
//
//            while (rs.next()) {
//                System.out.println(rs.getString("fname") + " " + rs.getString("lname"));
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


        GreenSkiesApplication.main(args);
    }
}

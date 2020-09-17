package controller.main;

import controller.analysis.Filterer;
import controller.analysis.ReportGenerator;
import model.data.Storage;
import model.loader.Loader;
import java.sql.SQLException;

// TODO: check all method comments start with "This method ..."

/**
 * The controller class which contains the controls for the main.
 *
 * @author Hayley Krippner, Ella Johnson.
 * @version 1.0
 * @since 04/09/20
 */
public class Main {
  // TODO: write comments for these attributes

  private static Storage storage = new Storage();
  private static Loader loader = new Loader(storage);
  private static Filterer filterer = new Filterer();
  private static ReportGenerator reportGenerator = new ReportGenerator();
  // TODO: write comment for this method

  public static Storage getStorage() {
    return storage;
  }
  // TODO: write comment for this method

  public static Loader getLoader() {
    return loader;
  }
  // TODO: write comment for this method

  public static Filterer getFilterer() {
    return filterer;
  }
  // TODO: write comment for this method

  public static ReportGenerator getReportGenerator() {
    return reportGenerator;
  }

  // TODO: write comment for this method
  // TODO can the comment things be removed? HK 16/09/2020
  public static void main(String[] args) throws SQLException, ClassNotFoundException {
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

    //            while (rs.next()) {
    //                System.out.println(rs.getString("fname") + " " + rs.getString("lname"));
    //            }
    //
    //        } catch (Exception e) {
    //            e.printStackTrace();
    //        }

    //        storage.initialiseStorage();
    GreenSkiesApplication.main(args);
  }
}

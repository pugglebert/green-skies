package controller.main;

import controller.analysis.Filterer;
import controller.analysis.ReportGenerator;
import model.data.Storage;
import model.loader.Loader;

import java.sql.SQLException;

/**
 * The controller class which contains the controls for the main.
 *
 * @author Hayley Krippner, Ella Johnson.
 * @version 1.0
 * @since 04/09/20
 */
public class Main {
  /** The storage for the application. */
  private static Storage storage = new Storage();
  /** The data loader for the the application. */
  private static Loader loader = new Loader(storage);
  /** The filter for the application. */
  private static Filterer filterer = new Filterer();
  /** The report generator for the application. */
  private static ReportGenerator reportGenerator = new ReportGenerator();

  public static Storage getStorage() {
    return storage;
  }

  public static Loader getLoader() {
    return loader;
  }

  public static Filterer getFilterer() {
    return filterer;
  }

  public static ReportGenerator getReportGenerator() {
    return reportGenerator;
  }

  /**
   * This method is the main which initilizes the storage and starts the application.
   *
   * @param args The arguments passed in.
   * @throws SQLException This throws an SQLException.
   * @throws ClassNotFoundException This throws a ClassNotFoundException.
   */
  public static void main(String[] args) throws SQLException, ClassNotFoundException {

//    storage.initialiseStorage();
    GreenSkiesApplication.main(args);
  }
}

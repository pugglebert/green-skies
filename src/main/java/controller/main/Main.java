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

    storage.initialiseStorage();
    GreenSkiesApplication.main(args);
  }
}

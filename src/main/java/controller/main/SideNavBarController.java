package controller.main;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.data.Storage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Superclass for all data view pages. All such pages contain a table of data, tools for searching
 * and filtering that data, and a navigation bar which can be used to navigate to other pages.
 *
 * @author Ella Johnson, Hayley Krippner, Enyang Zhang, ZhengJingRui He.
 * @version 1.0
 * @since 04/09/20
 */
public abstract class SideNavBarController implements Initializable {

  @FXML protected Button btnUpload;
  @FXML protected Button btnRouteDataView;
  @FXML protected Button btnAirportDataView;
  @FXML protected Button btnAirlineDataView;
  @FXML protected Button btnFlightHistory;
  @FXML protected Button btnCarbonEmissionsReport;
  @FXML protected Button btnMapOfAirports;
  @FXML protected Button btnGraphs;
  @FXML protected Button btnAnalyseFlight;

  /** The search types. */
  protected ObservableList<String> searchTypes;
  /** The storage for the application. */
  protected final Storage storage = Main.getStorage();

  /**
   * This method initializes the class.
   *
   * @param url The provided url.
   * @param rb The provided resource bundle.
   */
  public abstract void initialize(URL url, ResourceBundle rb);

  /**
   * This method closes the current page and opens the Upload Data page.
   *
   * @throws IOException
   */
  public void toUploadData() throws IOException {
    Stage newStage = GreenSkiesApplication.getPrimaryStage();
    Parent root;
    try {
      root = FXMLLoader.load(getClass().getResource("/controller/main/upload.fxml"));
    } catch (NullPointerException e) {
      root = FXMLLoader.load(getClass().getResource("../upload.fxml"));
    }
    Scene scene = new Scene(root);
    newStage.setScene(scene);
  }

  /**
   * This method closes the current page and opens the View Route Data page.
   *
   * @throws IOException
   */
  public void toRouteDataView() throws IOException {
    Stage newStage = GreenSkiesApplication.getPrimaryStage();
    Parent root;
    try {
      root = FXMLLoader.load(getClass().getResource("/controller/main/viewRouteData.fxml"));
    } catch (NullPointerException e) {
      root = FXMLLoader.load(getClass().getResource("../viewRouteData.fxml"));
    }
    Scene scene = new Scene(root);
    newStage.setScene(scene);
  }

  /**
   * This method closes the current page and opens the View Airport Data page.
   *
   * @throws IOException
   */
  public void toAirportDataView() throws IOException {
    Stage newStage = GreenSkiesApplication.getPrimaryStage();
    Parent root;
    try {
      root = FXMLLoader.load(getClass().getResource("/controller/main/viewAirportData.fxml"));

    } catch (NullPointerException e) {
      root = FXMLLoader.load(getClass().getResource("../viewAirportData.fxml"));
    }
    // open the View Airport Data page
    Scene scene = new Scene(root);
    newStage.setScene(scene);
  }

  /**
   * This method closes the current page and opens the View Airline Data page.
   *
   * @throws IOException
   */
  public void toAirlineDataView() throws IOException {
    Stage newStage = GreenSkiesApplication.getPrimaryStage();
    Parent root;
    try {
      root = FXMLLoader.load(getClass().getResource("/controller/main/viewAirlineData.fxml"));
    } catch (NullPointerException e) {
      root = FXMLLoader.load(getClass().getResource("../viewAirlineData.fxml"));
    }
    Scene scene = new Scene(root);
    newStage.setScene(scene);
  }

  /**
   * This method closes the current page and opens the Flight History page.
   *
   * @throws IOException
   */
  public void toFlightHistory() throws IOException {
    Stage newStage = GreenSkiesApplication.getPrimaryStage();
    Parent root;
    try {
      root = FXMLLoader.load(getClass().getResource("/controller/main/flightHistory.fxml"));
    } catch (NullPointerException e) {
      root = FXMLLoader.load(getClass().getResource("../flightHistory.fxml"));
    }
    Scene scene = new Scene(root);
    newStage.setScene(scene);
  }

  /**
   * This method closes the current page and opens the Carbon Emissions Report page.
   *
   * @throws IOException
   */
  public void toCarbonEmissionsReport() throws IOException {
    Stage newStage = GreenSkiesApplication.getPrimaryStage();
    Parent root;
    try {
      root = FXMLLoader.load(getClass().getResource("/controller/main/carbonEmissionsReport.fxml"));
      System.out.println(getClass());
    } catch (NullPointerException e) {
      root = FXMLLoader.load(getClass().getResource("../carbonEmissionsReport.fxml"));
    }
    Scene scene = new Scene(root);
    newStage.setScene(scene);
  }

  /**
   * This method closes the current page and opens the Map of Airports page.
   *
   * @throws IOException
   */
  public void toMapOfAirports() throws IOException {
    Stage newStage = GreenSkiesApplication.getPrimaryStage();
    Parent root;
    try {
      root = FXMLLoader.load(getClass().getResource("/controller/main/mapOfRoutes.fxml"));
    } catch (NullPointerException e) {
      root = FXMLLoader.load(getClass().getResource("../mapOfRoutes.fxml"));
    }
    Scene scene = new Scene(root);
    newStage.setScene(scene);
  }

  /**
   * This method closes the current page and opens the Graphs page.
   *
   * @throws IOException
   */
  public void toGraphs() throws IOException {
    Stage newStage = GreenSkiesApplication.getPrimaryStage();
    Parent root;
    try {
      root = FXMLLoader.load(getClass().getResource("/controller/main/graphs.fxml"));
    } catch (NullPointerException e) {
      root = FXMLLoader.load(getClass().getResource("../graphs.fxml"));
    }
    Scene scene = new Scene(root);
    newStage.setScene(scene);
  }

  /**
   * This method closes the current page and opens the Flight Analyser page.
   *
   * @throws IOException
   */
  public void toAnalyseFlight() throws IOException {
    Stage newStage = GreenSkiesApplication.getPrimaryStage();
    Parent root;
    try {
      root = FXMLLoader.load(getClass().getResource("/controller/main/analyse.fxml"));
    } catch (NullPointerException e) {
      root = FXMLLoader.load(getClass().getResource("../analyse.fxml"));
    }
    Scene scene = new Scene(root);
    newStage.setScene(scene);
  }
}

package controller.main;

import controller.analysis.Searcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.data.Route;
import model.data.Storage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * The controller class which contains the controls for the airline data view.
 * @author Hayley Krippner, Nathan Huynh
 * @version 1.0
 * @since 04/09/20
 */
public class FlightHistoryController extends SideNavBarController {

  //Configure the TableView.
  @FXML
  private TableView<Route> tableView;
  @FXML
  private TableColumn<Route, Boolean> addColumn;
  @FXML
  private TableColumn<Route, String> airlineNameColumn;
  @FXML
  private TableColumn<Route, Integer> airlineIDColumn;
  @FXML
  private TableColumn<Route, String> sourceAirportColumn;
  @FXML
  private TableColumn<Route, Integer> sourceAirportIDColumn;
  @FXML
  private TableColumn<Route, String> destinationAirportColumn;
  @FXML
  private TableColumn<Route, Integer> destinationAirportIDColumn;
  @FXML
  private TableColumn<Route, String> codeShareColumn;
  @FXML
  private TableColumn<Route, Integer> numOfStopsColumn;
  @FXML
  private TableColumn<Route, String[]> equipmentColumn;
  @FXML
  private Button btnUpload;
  @FXML
  private Button btnRouteDataView;
  @FXML
  private Button btnAirportDataView;
  @FXML
  private Button btnAirlineDataView;
  @FXML
  private Button btnFlightHistory;
  @FXML
  private ChoiceBox<String> searchTypeSelection;
  @FXML
  private TextField searchBar;
  @FXML
  private Button searchButton;
  @FXML
  private Label errorText;

  private final ObservableList<String> searchTypes = FXCollections.observableArrayList("Airline", "Source", "Destination");
  private Storage storage = Main.getStorage();

  /**
   * Initializes the controller class.
   * @param url The URL used.
   * @param rb The resource bundle used.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    //Set up the columns in the TableView.
    addColumn.setCellValueFactory(new PropertyValueFactory<>("select"));
    airlineNameColumn.setCellValueFactory(new PropertyValueFactory<>("airlineName"));
    airlineIDColumn.setCellValueFactory(new PropertyValueFactory<>("airlineID"));
    sourceAirportColumn.setCellValueFactory(new PropertyValueFactory<>("sourceAirport"));
    sourceAirportIDColumn.setCellValueFactory(new PropertyValueFactory<>("sourceAirportID"));
    destinationAirportColumn.setCellValueFactory(new PropertyValueFactory<>("destinationAirport"));
    destinationAirportIDColumn.setCellValueFactory(new PropertyValueFactory<>("destinationAirportID"));
    codeShareColumn.setCellValueFactory(new PropertyValueFactory<>("codeShare"));
    numOfStopsColumn.setCellValueFactory(new PropertyValueFactory<>("numOfStops"));
    equipmentColumn.setCellValueFactory(new PropertyValueFactory<>("equipment"));

    //Load data by taking the Route ArrayList and converting it to an ObservableArrayList.
//    System.out.println(Main.getStorage().getHistory());
    ObservableList<Route> routes = FXCollections.observableList(Main.getStorage().getHistory());
//    System.out.println(Main.getStorage().getHistory());
    tableView.setItems(routes);
//    System.out.println(Main.getStorage().getHistory());

    //Set choice box to list of potential search types
    searchTypeSelection.setItems(searchTypes);
  }

  //TODO: remove the code below once the super class SideNavBarController works
//  /**
//   * This method closes the View Route Data page and opens the Upload Data page.
//   * @throws IOException
//   */
//  public void toUploadData() throws IOException {
//    Stage stage = (Stage) btnUpload.getScene().getWindow();
//    stage.close();
//    Stage newStage = new Stage();
//    Parent root = FXMLLoader.load(getClass().getResource("upload.fxml")); //open the Upload Data page
//    Scene scene = new Scene(root);
//    newStage.setScene(scene);
//    newStage.show();
//  }
//
//  /**
//   * This method closes the View Route Data page and opens the View Route Data page.
//   * @throws IOException
//   */
//  public void toRouteDataView() throws IOException {
//    Stage stage = (Stage) btnRouteDataView.getScene().getWindow();
//    stage.close();
//    Stage newStage = new Stage();
//    Parent root = FXMLLoader.load(getClass().getResource("viewRouteData.fxml")); //open the View Route Data page
//    Scene scene = new Scene(root);
//    newStage.setScene(scene);
//    newStage.show();
//  }
//
//  /**
//   * This method closes the View Route Data page and opens the View Airport Data page.
//   * @throws IOException
//   */
//  public void toAirportDataView() throws IOException {
//    Stage stage = (Stage) btnAirportDataView.getScene().getWindow();
//    stage.close();
//    Stage newStage = new Stage();
//    Parent root = FXMLLoader.load(getClass().getResource("viewAirportData.fxml")); //open the View Airport Data page
//    Scene scene = new Scene(root);
//    newStage.setScene(scene);
//    newStage.show();
//  }
//
//  /**
//   * This method closes the View Route Data page and opens the View Airline Data page.
//   * @throws IOException
//   */
//  public void toAirlineDataView() throws IOException {
//    Stage stage = (Stage) btnAirlineDataView.getScene().getWindow();
//    stage.close();
//    Stage newStage = new Stage();
//    Parent root = FXMLLoader.load(getClass().getResource("viewAirlineData.fxml")); //open the View Airline Data page
//    Scene scene = new Scene(root);
//    newStage.setScene(scene);
//    newStage.show();
//  }
//
//  /**
//   * This method closes the Upload Data page and opens the View Airline Data page.
//   * @throws IOException
//   */
//  public void toFlightHistory() throws IOException {
//    Stage stage = (Stage) btnFlightHistory.getScene().getWindow();
//    stage.close();
//    Stage newStage = new Stage();
//    Parent root = FXMLLoader.load(getClass().getResource("flightHistory.fxml")); //open the View Airline Data page
//    Scene scene = new Scene(root);
//    newStage.setScene(scene);
//    newStage.show();
//  }
//
//  /**
//   * Checks users search for errors and displays an error message if any are present. If no errors
//   * are present, calls searchRoutes method from searcher class and upldates table to display
//   * results of search.
//   */
  public void search() {
    String searchType = searchTypeSelection.getValue();
    String searchTerm = searchBar.getText();
    if (searchType == null) {
      errorText.setText("Select a search type to proceed.");
      errorText.setVisible(true);
    } else if (searchTerm == null) {
      errorText.setText("Select a search type to proceed.");
      errorText.setVisible(true);
    } else {
      try {
        ArrayList<Route> results = Searcher.searchRoutes(searchTerm, searchType, storage.getRoutes());
        tableView.setItems(FXCollections.observableList(results));
        errorText.setVisible(false);
      } catch (RuntimeException e) {
        errorText.setText(e.getMessage());
        errorText.setVisible(true);
      }
    }

  }
}

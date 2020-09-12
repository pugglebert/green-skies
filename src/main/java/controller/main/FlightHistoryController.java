package controller.main;

import controller.analysis.Filterer;
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
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

/**
 * The controller class which contains the controls for the airline data view.
 * @author Hayley Krippner, Nathan Huynh
 * @version 1.0
 * @since 04/09/20
 */
public class FlightHistoryController extends DataViewController {

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
  @FXML
  private ChoiceBox<String> airlineSelection;
  @FXML
  private ChoiceBox<String> sourceSelection;
  @FXML
  private ChoiceBox<String> destinationSelection;


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

  /**
   * Searches history for routes which match the search type and term and displays them in the table view.
   * @param searchTerm String to match attributes to.
   * @param searchType String representing attribute to check for matches.
   */
  @Override
  public void searchByDataType(String searchTerm, String searchType) {
    ArrayList<Route> results = Searcher.searchRoutes(searchTerm, searchType, storage.getHistory());
    tableView.setItems(FXCollections.observableList(results));
  }

  /**
   * Clear search bar and display all history in table view.
   */
  @Override
  public void clearSearch() {
    searchBar.setText(null);
    tableView.setItems(FXCollections.observableList(storage.getHistory()));
  }

  public void filterOptions() throws IOException {
  }

}

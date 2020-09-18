package controller.main.dataview;

import controller.analysis.Searcher;
import controller.main.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.data.Route;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.ResourceBundle;

// TODO: check all method comments start with "This method ..."

/**
 * The controller class which contains the controls for the airline data view.
 *
 * @author Hayley Krippner, Nathan Huynh, He Zhengjingrui, ELla Johnson
 * @version 1.0
 * @since 2020-09-04
 */
public class FlightHistoryController extends DataViewController {

  // TODO: what is with the commented columns? HK 16/09/2020
  @FXML private TableView<Route> tableView;
  @FXML private TableColumn<Route, String> airlineNameColumn;
  @FXML private TableColumn<Route, String> sourceAirportColumn;
  @FXML private TableColumn<Route, String> destinationAirportColumn;
  @FXML private TableColumn<Route, String> codeShareColumn;
  @FXML private TableColumn<Route, Integer> numOfStopsColumn;
  @FXML private TableColumn<Route, String> equipmentColumn;
  @FXML private ChoiceBox<String> searchTypeSelection;
  @FXML private TextField searchBar;
  @FXML private ChoiceBox<String> RankSelection;

  /** The types of search which can be performed on history. */
  private final ObservableList<String> searchTypes =
      FXCollections.observableArrayList("Airline", "Source", "Destination");
  private final ObservableList<String> RankTypes =
      FXCollections.observableArrayList("Emission", "Distance");

  /**
   * This method initializes the controller class.
   *
   * @param url The URL used.
   * @param rb The resource bundle used.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    // TODO: what is with the commented columns? HK 16/09/2020

    //    addColumn.setCellValueFactory(new PropertyValueFactory<>("select"));
    airlineNameColumn.setCellValueFactory(new PropertyValueFactory<>("airlineName"));
    //    airlineIDColumn.setCellValueFactory(new PropertyValueFactory<>("airlineID"));
    sourceAirportColumn.setCellValueFactory(new PropertyValueFactory<>("sourceAirport"));
    //    sourceAirportIDColumn.setCellValueFactory(new PropertyValueFactory<>("sourceAirportID"));
    destinationAirportColumn.setCellValueFactory(new PropertyValueFactory<>("destinationAirport"));
    //    destinationAirportIDColumn.setCellValueFactory(new
    // PropertyValueFactory<>("destinationAirportID"));
    codeShareColumn.setCellValueFactory(new PropertyValueFactory<>("codeShare"));
    numOfStopsColumn.setCellValueFactory(new PropertyValueFactory<>("numOfStops"));
    equipmentColumn.setCellValueFactory(new PropertyValueFactory<>("firstEquipment"));

    // TODO: what is with the commented things? HK 16/09/2020
    // Load data by taking the Route ArrayList and converting it to an ObservableArrayList.
    //    System.out.println(Main.getStorage().getHistory());
    ObservableList<Route> routes = FXCollections.observableList(Main.getStorage().getHistory());
    //    System.out.println(Main.getStorage().getHistory());
    tableView.setItems(routes);
    //    System.out.println(Main.getStorage().getHistory());

    // Set choice box to list of potential search types
    searchTypeSelection.setItems(searchTypes);
    RankSelection.setItems(RankTypes);
  }

  /**
   * This method earches history for routes which match the search type and term and displays them in the table
   * view.
   *
   * @param searchTerm String to match attributes to.
   * @param searchType String representing attribute to check for matches.
   */
  @Override
  public void searchByDataType(String searchTerm, String searchType) {
    ArrayList<Route> results = Searcher.searchRoutes(searchTerm, searchType, storage.getHistory());
    tableView.setItems(FXCollections.observableList(results));
  }

  /** This method clears search bar and displays all history in table view. */
  @Override
  public void clearSearch() {
    searchBar.setText(null);
    tableView.setItems(FXCollections.observableList(storage.getHistory()));
  }

  /**
   * This method launches the filter pop up box. If filtering is successful displays filtered history in
   * tableview.
   *
   * @throws IOException if fxml file cannot be opened.
   */
  public void filterOptions() throws IOException {
    HistoryFilterPopUpController filterPopUp = new HistoryFilterPopUpController();
    filterer.setFilterSuccess(false);
    filterPopUp.display();
    if (filterer.getFilterSuccess()) {
      tableView.setItems(FXCollections.observableList(filterer.getFilteredRoutes()));
    }
  }

  /**
   * This method ranks the routes by the attribute selected by the user.
   */
  @FXML
  public void rank() {
    if (RankSelection.getSelectionModel().getSelectedItem().equals("Distance")) {
//      System.out.println(1);
      Collections.sort(
          storage.getHistory(),
          new Comparator<Route>() {
            @Override
            public int compare(Route route1, Route route2) {
              return Double.compare(route1.getDistance(), route2.getDistance());
            }
          });
      for (Route i : storage.getHistory()) {
//        System.out.println(i);
      }
    } else {
      Collections.sort(
          storage.getHistory(),
          new Comparator<Route>() {
            @Override
            public int compare(Route route1, Route route2) {
              return Double.compare(route1.getEmissions(), route2.getEmissions());
            }
          });
    }
  }
}

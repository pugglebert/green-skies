package controller.main;

import controller.analysis.FlightAnalyser;
import controller.analysis.ReportGenerator;
import controller.analysis.Searcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.data.Route;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * The controller class which contains the controls for the route data view.
 *
 * @author Hayley Krippner, Ella Johnson
 * @version 1.0
 * @since 2020-09-04
 */
public class RouteDataViewController extends DataViewController {

  // Configure the TableView.
  @FXML private TableView<Route> tableView;
  @FXML private TableColumn<Route, Boolean> addColumn;
  @FXML private TableColumn<Route, String> airlineNameColumn;
  @FXML private TableColumn<Route, String> sourceAirportColumn;
  @FXML private TableColumn<Route, String> destinationAirportColumn;
  @FXML private TableColumn<Route, String> codeShareColumn;
  @FXML private TableColumn<Route, Integer> numOfStopsColumn;
  @FXML private TableColumn<Route, String> equipmentColumn;

  /** The types of search which can be performed on routes. */
  private final ObservableList<String> searchTypes =
      FXCollections.observableArrayList("Airline", "Source", "Destination");

  /** Class to generate reports on history. */
  private ReportGenerator reportGenerator;

  /** Pop up to launch when adding route to history. */
  private final RouteAddToHistoryPopUpController addPopUp = new RouteAddToHistoryPopUpController();

  /**
   * Initializes the controller class. The checkboxes are added to each record.
   *
   * @param url The URL used.
   * @param rb The resource bundle used.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {

    this.reportGenerator = Main.getReportGenerator();
    // Set up the columns in the TableView.
    addColumn.setCellValueFactory(new PropertyValueFactory<>("select"));
    airlineNameColumn.setCellValueFactory(new PropertyValueFactory<>("airlineName"));
    sourceAirportColumn.setCellValueFactory(new PropertyValueFactory<>("sourceAirport"));
    destinationAirportColumn.setCellValueFactory(new PropertyValueFactory<>("destinationAirport"));
    codeShareColumn.setCellValueFactory(new PropertyValueFactory<>("codeShare"));
    numOfStopsColumn.setCellValueFactory(new PropertyValueFactory<>("numOfStops"));
    equipmentColumn.setCellValueFactory(new PropertyValueFactory<>("firstEquipment"));

    for (Route route : storage.getRoutes()) {
      route.initCheckBox();
    }
    ObservableList<Route> routes = FXCollections.observableList(storage.getRoutes());
    tableView.setItems(routes);
    searchTypeSelection.setItems(searchTypes); // Setup choice boxes
  }

  /**
   * This method calls searchRoutes method from searcher class and upldates table to display results of search.
   */
  public void searchByDataType(String searchTerm, String searchType) {
    ArrayList<Route> results = Searcher.searchRoutes(searchTerm, searchType, storage.getRoutes());
    tableView.setItems(FXCollections.observableList(results));
  }

  /**
   * This method adds the selected routes to the flight history. The total distance, total
   * emissions, least distance, most distance , least emissions , most emissions , least travelled
   * and most travelled route are updated.
   */
  public void addDataToHistory() throws IOException {
    if (!Main.getStorage().getTempRoutes().isEmpty()) {
      Main.getStorage().getTempRoutes().clear();
    }

    for (Route route : Main.getStorage().getRoutes()) {
      if (route.getSelect().isSelected()) {
        Main.getStorage().getTempRoutes().add(route);

        FlightAnalyser flightAnalyser = new FlightAnalyser(route, storage);
        route.setEmissions(flightAnalyser.getPath1Emission());
        route.setDistance(flightAnalyser.getTotalDistancePath1());
        reportGenerator.updateTotalDistance(route);
        reportGenerator.updateTotalEmissions(route);

        storage.addToHistorySrcAirports(
            route.getSourceAirport(),
            1); // TODO:change to route.getTimesTaken() once Nathan has implemented counter
        storage.addToHistoryDestAirports(
            route.getDestinationAirport(),
            1); // TODO:change to route.getTimesTaken() once Nathan has implemented counter

        // reportGenerator.updateLeastDistanceRoute(route);
        // reportGenerator.updateMostDistanceRoute(route);
        // reportGenerator.updateMostEmissionsRoute(route);
        // reportGenerator.updateLeastEmissionsRoute(route);
      }
    }
    addPopUp.display();
    //        Main.getStorage().getHistory().addAll(temp);
    // reportGenerator.updateLeastTravelledRoute(Main.getStorage().getHistory()); //TODO:
    // considering doing when click on history page to reduce time wasted.
    // reportGenerator.updateMostTravelledRoute(Main.getStorage().getHistory()); //TODO: considering
    // doing when click on history page to reduce time wasted.
    reportGenerator.updateMostVisitedSrcAirports(
        storage.getHistorySrcAirports()); // TODO: uncommment once implemented HashMap
    reportGenerator.updateMostVisitedSrcAirports(
        storage.getHistoryDestAirports()); // TODO: uncommment once implemented HashMap
  }

  /** This method clears the search bar and displays all routes in table view. */
  @Override
  public void clearSearch() {
    searchBar.setText(null);
    tableView.setItems(FXCollections.observableList(storage.getRoutes()));
  }

  /**
   * This method launches the filter pop up box. If filtering is successful displays filtered routes in tableview.
   *
   * @throws IOException If fxml file cannot be launched.
   */
  public void filterOptions() throws IOException {
    RouteFilterPopUpController filterPopUp = new RouteFilterPopUpController();
    filterer.setFilterSuccess(false);
    filterPopUp.display();
    if (filterer.getFilterSuccess()) {
      tableView.setItems(FXCollections.observableList(filterer.getFilteredRoutes()));
    }
  }
}

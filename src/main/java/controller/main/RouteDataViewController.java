package controller.main;

import controller.analysis.FlightAnalyser;
import controller.analysis.ReportGenerator;
import controller.analysis.Searcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.data.Route;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * The controller class which contains the controls for the route data view.
 *
 * @author Hayley Krippner, Ella Johnson
 * @version 1.0
 * @since 2020-09-04
 */
public class RouteDataViewController extends DataViewController {

  @FXML
  private TableView<Route> tableView;
  @FXML
  private TableColumn<Route, Boolean> addColumn;
  @FXML
  private TableColumn<Route, String> airlineNameColumn;
  @FXML
  private TableColumn<Route, String> sourceAirportColumn;
  @FXML
  private TableColumn<Route, String> destinationAirportColumn;
  @FXML
  private TableColumn<Route, String> codeShareColumn;
  @FXML
  private TableColumn<Route, Integer> numOfStopsColumn;
  @FXML
  private TableColumn<Route, String> equipmentColumn;
  @FXML
  private Button AddToHistoryButton;
  @FXML
  private Button btnRemove;

  /**
   * The types of search which can be performed on routes.
   */
  private final ObservableList<String> searchTypes =
          FXCollections.observableArrayList("Airline", "Source", "Destination");

  /**
   * Class to generate reports on history.
   */
  private ReportGenerator reportGenerator;

  /**
   * Pop up to launch when adding route to history.
   */
  private final RouteAddToHistoryPopUpController addPopUp = new RouteAddToHistoryPopUpController();

  private ObservableList<Route> routes;
  /**
   * Initializes the controller class. The checkboxes are added to each record.
   *
   * @param url The URL used.
   * @param rb The resource bundle used.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {

    this.reportGenerator = Main.getReportGenerator();
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
    routes = FXCollections.observableList(storage.getRoutes());
    tableView.setItems(routes);
    searchTypeSelection.setItems(searchTypes); // Setup choice boxes
  }

  /**
   * This method calls searchRoutes method from searcher class and upldates table to display results
   * of search.
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
      Main.getStorage().getTempRoutes().clear(); //TODO: ask Nathan if this should be here. Does it need to be cleared every time a new route is added? HK, 23/09/2020
    }

    for (Route route : Main.getStorage().getRoutes()) {
      if (route.getSelect().isSelected()) {
        Main.getStorage().getTempRoutes().add(route);
        FlightAnalyser flightAnalyser = new FlightAnalyser(route, storage);
        route.setEmissions(flightAnalyser.getPath1Emission());
        route.setDistance(flightAnalyser.getTotalDistancePath1());
        reportGenerator.updateTotalDistance(route);
        reportGenerator.updateTotalEmissions(route);
        route.setTimesTaken(1);
        storage.addToHistorySrcAirports(route.getSourceAirport());
        storage.addToHistoryDestAirports(route.getDestinationAirport());
        reportGenerator.updateLeastDistanceRoute(route);
        reportGenerator.updateMostDistanceRoute(route);
        reportGenerator.updateMostEmissionsRoute(route);
        reportGenerator.updateLeastEmissionsRoute(route);
      }
    }

    addPopUp.display();
//    reportGenerator.updateLeastTravelledRoute(Main.getStorage().getHistory());
//    reportGenerator.updateMostTravelledRoute(Main.getStorage().getHistory());
    reportGenerator.updateLeastTravelledRoute(storage.getHistory());
    reportGenerator.updateMostTravelledRoute(storage.getHistory());
    reportGenerator.updateMostVisitedSrcAirports(storage.getHistorySrcAirports());
    reportGenerator.updateLeastVisitedSrcAirports(storage.getHistorySrcAirports());
    reportGenerator.updateMostVisitedDestAirports(storage.getHistoryDestAirports());
    reportGenerator.updateLeastVisitedDestAirports(storage.getHistoryDestAirports());

  }

  /** This method clears the search bar and displays all routes in table view. */
  @Override
  public void clearSearch() {
    searchBar.setText(null);
    tableView.setItems(FXCollections.observableList(storage.getRoutes()));
  }

  /**
   * This method launches the filter pop up box. If filtering is successful displays filtered routes
   * in tableview.
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

  public void removeSelected() {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirm remove");
    alert.setHeaderText(null);
    alert.setContentText("Are you sure?");
    Optional<ButtonType> result = alert.showAndWait();
    if (result.isPresent() && result.get() == ButtonType.OK) {
      routes.removeIf(route -> route.getSelect().isSelected());

      }
    }

  }


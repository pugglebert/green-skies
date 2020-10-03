package controller.guiController.dataview;

import controller.analysis.FlightAnalyser;
import controller.analysis.GeneralStatsCalculator;
import controller.analysis.RouteStatsCalculator;
import controller.analysis.Searcher;
import controller.guiController.AlertPopUp;
import controller.guiController.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import model.data.Airport;
import model.data.Route;
import model.database.SQLiteDatabase;

import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * The controller class which contains the controls for the history data view.
 *
 * @author Hayley Krippner, Nathan Huynh, He Zhengjingrui, ELla Johnson, Lambert
 * @version 1.0
 * @since 03/10/2020
 */
public class FlightHistoryController extends DataViewController {
  ;
  @FXML private TableView<Route> tableView;
  @FXML private TableColumn<Route, Boolean> addColumn;
  @FXML private TableColumn<Route, String> airlineNameColumn;
  @FXML private TableColumn<Route, String> sourceAirportColumn;
  @FXML private TableColumn<Route, String> destinationAirportColumn;
  @FXML private TableColumn<Route, String> codeShareColumn;
  @FXML private TableColumn<Route, Integer> numOfStopsColumn;
  @FXML private TableColumn<Route, String> equipmentColumn;
  @FXML private TableColumn<Route, Integer> timesTakenColumn;
  @FXML private TableColumn<Route, String> distanceColumn;
  @FXML private TableColumn<Route, String> emissionsColumn;
  @FXML private ChoiceBox<String> searchTypeSelection;
  @FXML private TextField searchBar;
  @FXML private ChoiceBox<String> RankSelection;

  /** The database object. */
  private SQLiteDatabase database = new SQLiteDatabase();
  /** The types of search which can be performed on history. */
  private final ObservableList<String> searchTypes =
      FXCollections.observableArrayList("Airline", "Source", "Destination");
  /** The GeneralStatsCalculator to generate reports about flight history. */
  private final GeneralStatsCalculator generalStatsCalculator = Main.getGeneralStatsCalculator();
  /** The RouteStatsCalculator to generate route stats for the reports about flight history. */
  private final RouteStatsCalculator routeStatsCalculator = Main.getRouteStatsCalculator();

  ObservableList<Route> routes;

  /**
   * This method initializes the controller class.
   *
   * @param url The URL used.
   * @param rb The resource bundle used.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {

    addColumn.setCellValueFactory(new PropertyValueFactory<>("select"));
    airlineNameColumn.setCellValueFactory(new PropertyValueFactory<>("airlineName"));
    sourceAirportColumn.setCellValueFactory(new PropertyValueFactory<>("sourceAirport"));
    destinationAirportColumn.setCellValueFactory(new PropertyValueFactory<>("destinationAirport"));
    codeShareColumn.setCellValueFactory(new PropertyValueFactory<>("codeShare"));
    numOfStopsColumn.setCellValueFactory(new PropertyValueFactory<>("numOfStops"));
    equipmentColumn.setCellValueFactory(new PropertyValueFactory<>("firstEquipment"));

    timesTakenColumn.setCellValueFactory(new PropertyValueFactory<>("timesTaken"));
    timesTakenColumn.setCellFactory(
        TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    timesTakenColumn.setOnEditCommit(
        routeIntegerCellEditEvent -> {
          Route routeChanged =
              routeIntegerCellEditEvent
                  .getTableView()
                  .getItems()
                  .get(routeIntegerCellEditEvent.getTablePosition().getRow());
          routeChanged.setTimesTaken(routeIntegerCellEditEvent.getNewValue());
        });

    distanceColumn.setCellValueFactory(new PropertyValueFactory<>("distanceDisplayString"));
    emissionsColumn.setCellValueFactory(new PropertyValueFactory<>("emissionsDisplayString"));

    for (Route route : storage.getHistory()) {
      route.initCheckBox();
    }

    routes = FXCollections.observableList(Main.getStorage().getHistory());
    tableView.setItems(routes);
    tableView.setEditable(true);

    searchTypeSelection.setItems(searchTypes);
  }

  /**
   * This method searches history for routes which match the search type and term and displays them
   * in the table view.
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
    errorText.setVisible(false);
    searchBar.setText(null);
    tableView.setItems(FXCollections.observableList(storage.getHistory()));
  }

  /**
   * This method launches the filter pop up box. If filtering is successful displays filtered
   * history in tableview.
   *
   * @throws IOException if fxml file cannot be opened.
   */
  public void filterOptions() throws IOException {
    errorText.setVisible(false);
    HistoryFilterPopUpController filterPopUp = new HistoryFilterPopUpController();
    filterer.setFilterSuccess(false);
    filterPopUp.display();
    if (filterer.getFilterSuccess()) {
      tableView.setItems(FXCollections.observableList(filterer.getFilteredRoutes()));
    }
  }

  /** This method ranks the routes by the attribute selected by the user. */
  @FXML
  public void rank() {
    if (RankSelection.getSelectionModel().getSelectedItem().equals("Distance")) {
      storage.getHistory().sort(Comparator.comparingDouble(Route::getDistance));
    } else {
      storage.getHistory().sort(Comparator.comparingDouble(Route::getEmissions));
    }
  }

  // todo write document for this method or delete this function seens it havent been used//
  public void makeBarChart() {

    if (!Main.getStorage().getTempRoutes().isEmpty()) {
      Main.getStorage().getTempRoutes().clear();
    }

    for (Route route : Main.getStorage().getRoutes()) {
      if (route.getSelect().isSelected()) {
        Main.getStorage().getTempRoutes().add(route);
      }
    }
  }

  /** remove all the selection from checkboxes which have been selected */
  public void removeSelected() {
    errorText.setVisible(false);
    if (getAnySelected()) {
      Optional<ButtonType> result = AlertPopUp.showDeleteAlert("flight record(s)");
      if (result.isPresent() && result.get() == ButtonType.OK) {
        for (Route route : routes) {
          if (route.getSelect().isSelected()) {
            updateReportStatsDeletionSingleRoute(route); //TODO test this! May need to put after next line HK 12:46pm 2/10
            routes.remove(route);
          }
        }
        database.updateHistoryTable(storage.getHistory());
      }
    } else {
      errorText.setText("No routes selected.");
      errorText.setVisible(true);
    }
  }

  /**
   * Check if at least one entry has been selected.
   *
   * @return true if any have been selected or false otherwise.
   */
  public boolean getAnySelected() {
    boolean selected = false;
    for (Route route : routes) {
      if (route.getSelect().isSelected()) {
        selected = true;
        break;
      }
    }
    return selected;
  }

  // todo write comment for this function
  public void updateReportStatsDeletionSingleRoute(Route route) {

    FlightAnalyser flightAnalyser = new FlightAnalyser(route, storage);
    route.setEmissions(flightAnalyser.getPath1Emission());
    route.setDistance(flightAnalyser.getTotalDistancePath1());
    generalStatsCalculator.updateTotalDistanceRemoval(route);
    generalStatsCalculator.updateTotalEmissionsRemoval(route);
    storage.removeFromHistorySrcAirports(route.getSourceAirport()); //TODO test this works
    storage.removeFromHistoryDestAirports(route.getDestinationAirport()); //TODO test this works
    //routeStatsCalculator.updateLeastDistanceRoute(route); //TODO test these methods actually remove the route.
    //routeStatsCalculator.updateMostDistanceRoute(route); //TODO test these methods actually remove the route.
    routeStatsCalculator.updateMostEmissionsRouteRemoval(route, storage.getHistory()); //TODO test these methods actually remove the route.
    //routeStatsCalculator.updateLeastEmissionsRoute(route); //TODO test these methods actually remove the route.
  }

  /** This method select the route that user chooses and put it in storage for google map to use. */
  public void selectRoute() {
    HashMap<Integer, ArrayList<Airport>> mapAirport = Main.getStorage().getMapAirport();
    mapAirport.clear();
    int pathNum = 0;
    for (Route route : this.routes) {
      if (route.getSelect().isSelected()) {
        ArrayList<Airport> sourDestAirport = new ArrayList<>();
        for (Airport airport : Main.getStorage().getAirports()) {
          if (airport.getIATA().equals(route.getSourceAirport())
              || airport.getICAO().equals(route.getSourceAirport())) {
            sourDestAirport.add(airport);
          }
          if (airport.getIATA().equals(route.getDestinationAirport())
              || airport.getICAO().equals(route.getDestinationAirport())) {
            sourDestAirport.add(airport);
          }
        }
        mapAirport.put(pathNum, sourDestAirport);
        pathNum+=1;
      }
          if (mapAirport.size() > 1) {
            AlertPopUp.showGoogleMapAlert();
            mapAirport.clear();
          }
    }
  }
}

package controller.guiController.dataview;

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
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * The controller class which contains the controls for the airline data view.
 *
 * @author Hayley Krippner, Nathan Huynh, He Zhengjingrui, ELla Johnson
 * @version 1.0
 * @since 04/09/2020
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
  private ArrayList<Airport> airports = new ArrayList<>();


  /**
   * The types of search which can be performed on history.
   */
  private final ObservableList<String> searchTypes =
      FXCollections.observableArrayList("Airline", "Source", "Destination");

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
   * This method earches history for routes which match the search type and term and displays them
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
        routes.removeIf(route -> route.getSelect().isSelected());
        database.updateHistoryTable(storage.getHistory());
      }
    } else {
      errorText.setText("No routes selected.");
      errorText.setVisible(true);
    }
  }

  /** select all checkboxes */
  public void selectAll() {
    for (Route route : Main.getStorage().getHistory()) {
      route.getSelect().setSelected(true);
    }
  }

  /** deselect all checkboxes */
  public void deselectAll() {
    for (Route route : Main.getStorage().getHistory()) {
      route.getSelect().setSelected(false);
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

  public void selectFlightHistory() {
    errorText.setVisible(false);
      Main.getStorage().MapAirport = new ArrayList<>();
      Optional<ButtonType> result = AlertPopUp.showDeleteAlert("flight record(s)");

      if (result.isPresent() && result.get() == ButtonType.OK) {

        for(Route route: this.routes){

          if(route.getSelect().isSelected()){

              for(Airport airport: Main.getStorage().getAirports()){


                if(airport.getIATA().equals(route.getSourceAirport()) || airport.getICAO().equals(route.getSourceAirport()) || airport.getIATA().equals(route.getDestinationAirport()) || airport.getICAO().equals(route.getDestinationAirport())){

                  Main.getStorage().MapAirport.add(airport);

                }
              }


            }
          }
      }


  }
}

package controller.main;

import controller.analysis.Filterer;
import controller.analysis.Searcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.data.Airline;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * The controller class which contains the controls for the airline data view.
 *
 * @author Hayley Krippner
 * @version 1.0
 * @since 04/09/20
 */
public class AirlineDataViewController extends DataViewController {

  // Configure the TableView.
  @FXML private TableView<Airline> tableView;
  @FXML private TableColumn<Airline, Integer> airlineIDColumn;
  @FXML private TableColumn<Airline, String> airlineNameColumn;
  @FXML private TableColumn<Airline, String> airlineAliasColumn;
  @FXML private TableColumn<Airline, String> airlineIATAColumn;
  @FXML private TableColumn<Airline, String> ICAOColumn;
  @FXML private TableColumn<Airline, String> callsignColumn;
  @FXML private TableColumn<Airline, String> countryColumn;
  @FXML private TableColumn<Airline, Boolean> activeStatusColumn;
  @FXML private ChoiceBox<String> countrySelection;
  @FXML private ChoiceBox<String> activeSelection;
  @FXML private Button filterButton;

  private final ObservableList<String> searchTypes =
      FXCollections.observableArrayList("Name", "Country", "IATA", "ICAO");
  private final ObservableList<String> countries = FXCollections.observableArrayList("Any");
  private final ObservableList<String> activeStatuses =
      FXCollections.observableArrayList("Any", "True", "False");

  /**
   * Initializes the controller class.
   *
   * @param url The URL used.
   * @param rb The resource bundle used.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    // Set up the columns in the TableView.
    airlineIDColumn.setCellValueFactory(new PropertyValueFactory<>("airlineID"));
    airlineNameColumn.setCellValueFactory(new PropertyValueFactory<>("airlineName"));
    airlineAliasColumn.setCellValueFactory(new PropertyValueFactory<>("airlineAlias"));
    airlineIATAColumn.setCellValueFactory(new PropertyValueFactory<>("airlineIATA"));
    ICAOColumn.setCellValueFactory(new PropertyValueFactory<>("ICAO"));
    callsignColumn.setCellValueFactory(new PropertyValueFactory<>("callsign"));
    countryColumn.setCellValueFactory(new PropertyValueFactory<>("country"));
    activeStatusColumn.setCellValueFactory(new PropertyValueFactory<>("activeStatus"));

    // Load data by taking the Airline ArrayList and converting it to an ObservableArrayList.
    ObservableList<Airline> airlines = FXCollections.observableList(storage.getAirlines());
    tableView.setItems(airlines);

    // Setup choice boxes
    searchTypeSelection.setItems(searchTypes);
    countrySelection.setItems(countries);
    activeSelection.setItems(activeStatuses);

    // Add filter selection boxes to HashMap with filter type as key
    filterSelectionBoxes.put("Country", countrySelection);
    filterSelectionBoxes.put("Active status", activeSelection);
  }

  /**
   * Calls searchAirlines method from searcher class and upldates table to display results of
   * search.
   */
  public void searchByDataType(String searchTerm, String searchType) {
    ArrayList<Airline> results =
        Searcher.searchAirlines(searchTerm, searchType, storage.getAirlines());
    tableView.setItems(FXCollections.observableList(results));
  }

  public void filterByDataType(HashMap<String, String> filterTerms) {
    Filterer filterer = new Filterer();
    ArrayList<Airline> results = filterer.filterAirlines(filterTerms, storage.getAirlines());
    tableView.setItems(FXCollections.observableList(results));
  }
}
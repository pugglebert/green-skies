package controller.main;

import controller.analysis.Searcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.data.Airline;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

// TODO: check all method comments start with "This method ..."
/**
 * The controller class which contains the controls for the airline data view.
 *
 * @author Hayley Krippner, Ella Johnson
 * @version 1.0
 * @since 04/09/20
 */
public class AirlineDataViewController extends DataViewController {

  @FXML private TableView<Airline> tableView;
  @FXML private TableColumn<Airline, Integer> airlineIDColumn;
  @FXML private TableColumn<Airline, String> airlineNameColumn;
  @FXML private TableColumn<Airline, String> airlineAliasColumn;
  @FXML private TableColumn<Airline, String> airlineIATAColumn;
  @FXML private TableColumn<Airline, String> ICAOColumn;
  @FXML private TableColumn<Airline, String> callsignColumn;
  @FXML private TableColumn<Airline, String> countryColumn;
  @FXML private TableColumn<Airline, Boolean> activeStatusColumn;

  // TODO: write comments for these attributes
  private final ObservableList<String> searchTypes =
      FXCollections.observableArrayList("Name", "Country", "IATA", "ICAO");
  // TODO: write comments for these attributes
  private final ObservableList<String> activeStatuses =
      FXCollections.observableArrayList("True", "False");
  // TODO: write comments for these attributes
  private AirlineFilterPopUpController filterPopUp;

  /**
   * Initializes the controller class.
   *
   * @param url The URL used.
   * @param rb The resource bundle used.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    airlineIDColumn.setCellValueFactory(new PropertyValueFactory<>("airlineID"));
    airlineNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    airlineAliasColumn.setCellValueFactory(new PropertyValueFactory<>("airlineAlias"));
    airlineIATAColumn.setCellValueFactory(new PropertyValueFactory<>("IATA"));
    ICAOColumn.setCellValueFactory(new PropertyValueFactory<>("ICAO"));
    callsignColumn.setCellValueFactory(new PropertyValueFactory<>("callsign"));
    countryColumn.setCellValueFactory(new PropertyValueFactory<>("country"));
    activeStatusColumn.setCellValueFactory(new PropertyValueFactory<>("activeStatus"));

    ObservableList<Airline> airlines = FXCollections.observableList(storage.getAirlines());
    tableView.setItems(airlines);

    // Setup choice boxes
    searchTypeSelection.setItems(searchTypes);
    filterPopUpFilename = "airlineFilterPopUp.fxml";
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

  /** Clear search bar and display all airlines in table view. */
  @Override
  public void clearSearch() {
    searchBar.setText(null);
    tableView.setItems(FXCollections.observableList(storage.getAirlines()));
  }

  /**
   * Display the filter pop up box. If filtering is successful displays the filtered airline data in
   * the tableview when the pop up is closed.
   *
   * @throws IOException If AirlineFilterPopUpController fxml file cannot be opened.
   */
  public void filterOptions() throws IOException {
    AirlineFilterPopUpController filterPopUp = new AirlineFilterPopUpController();
    filterer.setFilterSuccess(false);
    filterPopUp.display();
    if (filterer.getFilterSuccess()) {
      tableView.setItems(FXCollections.observableList(filterer.getFilteredAirlines()));
    }
  }
}

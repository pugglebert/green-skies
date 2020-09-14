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
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.data.Airline;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

/**
 * The controller class which contains the controls for the airline data view.
 *
 * @author Hayley Krippner, Ella Johnson
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


  private final ObservableList<String> searchTypes =
      FXCollections.observableArrayList("Name", "Country", "IATA", "ICAO");
  private final ObservableList<String> activeStatuses =
      FXCollections.observableArrayList("True", "False");
  private AirlineFilterPopUpController filterPopUp;
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
    airlineNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    airlineAliasColumn.setCellValueFactory(new PropertyValueFactory<>("airlineAlias"));
    airlineIATAColumn.setCellValueFactory(new PropertyValueFactory<>("IATA"));
    ICAOColumn.setCellValueFactory(new PropertyValueFactory<>("ICAO"));
    callsignColumn.setCellValueFactory(new PropertyValueFactory<>("callsign"));
    countryColumn.setCellValueFactory(new PropertyValueFactory<>("country"));
    activeStatusColumn.setCellValueFactory(new PropertyValueFactory<>("activeStatus"));

    // Load data by taking the Airline ArrayList and converting it to an ObservableArrayList.
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

  /**
   * Clear search bar and display all airlines in table view.
   */
  @Override
  public void clearSearch() {
    searchBar.setText(null);
    tableView.setItems(FXCollections.observableList(storage.getAirlines()));
  }

  /**
   * Display the filter pop up box. If filtering is successful displays the filtered airline data in the tableview when
   * the pop up is closed.
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
package controller.main;

import controller.analysis.Searcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.data.Airline;
import model.data.Route;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * The controller class which contains the controls for the airline data view.
 *
 * @author Hayley Krippner, Ella Johnson
 * @version 1.0
 * @since 04/09/2020
 */
public class AirlineDataViewController extends DataViewController {


  @FXML
  private TableView<Airline> tableView;
  @FXML
  private TableColumn<Route, Boolean> addColumn;
  @FXML
  private TableColumn<Airline, Integer> airlineIDColumn;
  @FXML private TableColumn<Airline, String> airlineNameColumn;
  @FXML private TableColumn<Airline, String> airlineAliasColumn;
  @FXML private TableColumn<Airline, String> airlineIATAColumn;
  @FXML private TableColumn<Airline, String> ICAOColumn;
  @FXML private TableColumn<Airline, String> callsignColumn;
  @FXML private TableColumn<Airline, String> countryColumn;
  @FXML private TableColumn<Airline, Boolean> activeStatusColumn;

  /** Initialize the strings in the searchTypes list. */
  private final ObservableList<String> searchTypes =
      FXCollections.observableArrayList("Name", "Country", "IATA", "ICAO");

  ObservableList<Airline> airlines;
  /**
   * This method initializes the controller class.
   *
   * @param url The URL used.
   * @param rb The resource bundle used.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    addColumn.setCellValueFactory(new PropertyValueFactory<>("select"));
    airlineIDColumn.setCellValueFactory(new PropertyValueFactory<>("airlineID"));
    airlineNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    airlineAliasColumn.setCellValueFactory(new PropertyValueFactory<>("airlineAlias"));
    airlineIATAColumn.setCellValueFactory(new PropertyValueFactory<>("IATA"));
    ICAOColumn.setCellValueFactory(new PropertyValueFactory<>("ICAO"));
    callsignColumn.setCellValueFactory(new PropertyValueFactory<>("callsign"));
    countryColumn.setCellValueFactory(new PropertyValueFactory<>("country"));
    activeStatusColumn.setCellValueFactory(new PropertyValueFactory<>("activeStatus"));

    if (storage.getAirlines() != null) {
      for (Airline airline : storage.getAirlines()) {
        airline.initCheckBox();
      }
      airlines = FXCollections.observableList(storage.getAirlines());
      tableView.setItems(airlines);
      searchTypeSelection.setItems(searchTypes); // Setup choice boxes
    }
  }
  /**
   * This method calls searchAirlines method from searcher class and upldates table to display
   * results of search.
   *
   * @param searchTerm The term to search for matches to.
   * @param searchType The type of attribute to check for matches.
   */
  public void searchByDataType(String searchTerm, String searchType) {
    ArrayList<Airline> results =
        Searcher.searchAirlines(searchTerm, searchType, storage.getAirlines());
    tableView.setItems(FXCollections.observableList(results));
  }

  /** This method clears search bar and display all airlines in table view. */
  @Override
  public void clearSearch() {
    searchBar.setText(null);
    tableView.setItems(FXCollections.observableList(storage.getAirlines()));
  }

  /**
   * This method displays the filter pop up box. If filtering is successful displays the filtered
   * airline data in the tableview when the pop up is closed.
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

  public void removeSelected() {
    Optional<ButtonType> result = AlertPopUp.showDeleteAlert("Airline");
    if (result.isPresent() && result.get() == ButtonType.OK) {
      airlines.removeIf(airline -> airline.getSelect().isSelected());
    }
  }
}

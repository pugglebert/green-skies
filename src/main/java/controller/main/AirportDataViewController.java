package controller.main;

import controller.analysis.Filterer;
import controller.analysis.Searcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.data.Airline;
import model.data.Airport;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

/**
 * The controller class which contains the controls for the airport data view.
 * @author Hayley Krippner
 * @version 1.0
 * @since 04/09/20
 */
public class AirportDataViewController extends DataViewController {

    //Configure the TableView.
    @FXML
    private TableView<Airport> tableView;
    @FXML
    private TableColumn<Airport, Integer> airportIDColumn;
    @FXML
    private TableColumn<Airport, String> nameColumn;
    @FXML
    private TableColumn<Airport, String> cityColumn;
    @FXML
    private TableColumn<Airport, String> countryColumn;
    @FXML
    private TableColumn<Airport, String> IATAColumn;
    @FXML
    private TableColumn<Airport, String> ICAOColumn;
    @FXML
    private TableColumn<Airport, Double> latitudeColumn;
    @FXML
    private TableColumn<Airport, Double> longitudeColumn;
    @FXML
    private TableColumn<Airport, Integer> altitudeColumn;
    @FXML
    private TableColumn<Airport, Float> timezoneColumn;
    @FXML
    private TableColumn<Airport, String> DSTColumn;
    @FXML
    private TableColumn<Airport, String> dataBaseTimeZoneColumn;
    @FXML
    private ChoiceBox<String> countrySelection;
    @FXML
    private ChoiceBox<String> citySelection;

    private final ObservableList<String> searchTypes = FXCollections.observableArrayList("Name", "Country", "IATA", "ICAO");
    private ObservableList<String> countries;
    private ObservableList<String> cities;

    /**
     * Initializes the controller class.
     * @param url The URL used.
     * @param rb The resource bundle used.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Set up the columns in the TableView.
        airportIDColumn.setCellValueFactory(new PropertyValueFactory<>("airportID"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        cityColumn.setCellValueFactory(new PropertyValueFactory<>("city"));
        countryColumn.setCellValueFactory(new PropertyValueFactory<>("country"));
        IATAColumn.setCellValueFactory(new PropertyValueFactory<>("IATA"));
        ICAOColumn.setCellValueFactory(new PropertyValueFactory<>("ICAO"));
        latitudeColumn.setCellValueFactory(new PropertyValueFactory<>("latitude"));
        longitudeColumn.setCellValueFactory(new PropertyValueFactory<>("longitude"));
        altitudeColumn.setCellValueFactory(new PropertyValueFactory<>("altitude"));
        timezoneColumn.setCellValueFactory(new PropertyValueFactory<>("timezone"));
        DSTColumn.setCellValueFactory(new PropertyValueFactory<>("DST"));
        dataBaseTimeZoneColumn.setCellValueFactory(new PropertyValueFactory<>("dataBaseTimeZone"));

        //Load data by taking the Airport ArrayList and converting it to an ObservableArrayList.
        ObservableList<Airport> airports = FXCollections.observableList(storage.getAirports());
        tableView.setItems(airports);

        //Setup choice boxes
        searchTypeSelection.setItems(searchTypes);
        List<String> tempCountries = storage.getAirportCountries();
        tempCountries.add("Any");
        countries = FXCollections.observableArrayList(tempCountries);
        countrySelection.setItems(countries);
        List<String> tempCities = storage.getAirportCities();
        tempCities.add("Any");
        cities = FXCollections.observableArrayList(tempCities);
        citySelection.setItems(cities);

        //Add filter choice boxes to hashmap with filter type as key
        filterSelectionBoxes.put("Country", countrySelection);
        filterSelectionBoxes.put("City", citySelection);

    }

    /**
     * Calls searchAirports method from searcher class and upldates table to display
     * results of search.
     */
    public void searchByDataType(String searchTerm, String searchType) {
        ArrayList<Airport> results = Searcher.searchAirports(searchTerm, searchType, storage.getAirports());
        tableView.setItems(FXCollections.observableList(results));
    }

    /**
     * Calls filterAirports method of Filterer class and sets table to display results.
     * @param filterTerms A hashmap where the key is the filter type and the value is the term
     *                    the filter should match.
     */
    public void filterByDataType(HashMap<String, String> filterTerms) {
        ArrayList<Airport> results = Filterer.filterAirports(filterTerms, storage.getAirports());
        tableView.setItems(FXCollections.observableList(results));
    }

}
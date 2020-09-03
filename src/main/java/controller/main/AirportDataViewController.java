package controller.main;

import controller.analysis.Searcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.data.Airline;
import model.data.Airport;
import model.data.Storage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * The controller class which contains the controls for the airport data view.
 * @author Hayley Krippner
 * @version 1.0
 * @since 2020-08-26
 */
public class AirportDataViewController extends DataViewController{

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

    private final ObservableList<String> searchTypes = FXCollections.observableArrayList("Name", "Country", "IATA", "ICAO");

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

        //Set choice box to list of potential search types
        searchTypeSelection.setItems(searchTypes);

    }

    /**
     * Calls searchAirports method from searcher class and upldates table to display
     * results of search.
     */
    public void searchByDataType(String searchTerm, String searchType) {
        ArrayList<Airport> results = Searcher.searchAirports(searchTerm, searchType, storage.getAirports());
        tableView.setItems(FXCollections.observableList(results));
    }

}
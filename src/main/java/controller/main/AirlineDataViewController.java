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
 * The controller class which contains the controls for the airline data view.
 * @author Hayley Krippner
 * @version 1.0
 * @since 2020-08-26
 */
public class AirlineDataViewController extends DataViewController{

    //Configure the TableView.
    @FXML
    private TableView<Airline> tableView;
    @FXML
    private TableColumn<Airline, Integer> airlineIDColumn;
    @FXML
    private TableColumn<Airline, String> airlineNameColumn;
    @FXML
    private TableColumn<Airline, String> airlineAliasColumn;
    @FXML
    private TableColumn<Airline, String> airlineIATAColumn;
    @FXML
    private TableColumn<Airline, String> ICAOColumn;
    @FXML
    private TableColumn<Airline, String> callsignColumn;
    @FXML
    private TableColumn<Airline, String> countryColumn;
    @FXML
    private TableColumn<Airline, Boolean> activeStatusColumn;

    private final ObservableList<String> searchTypes = FXCollections.observableArrayList("Name", "Country", "IATA", "ICAO");

    /**
     * Initializes the controller class.
     * @param url The URL used.
     * @param rb The resource bundle used.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Set up the columns in the TableView.
        airlineIDColumn.setCellValueFactory(new PropertyValueFactory<>("airlineID"));
        airlineNameColumn.setCellValueFactory(new PropertyValueFactory<>("airlineName"));
        airlineAliasColumn.setCellValueFactory(new PropertyValueFactory<>("airlineAlias"));
        airlineIATAColumn.setCellValueFactory(new PropertyValueFactory<>("airlineIATA"));
        ICAOColumn.setCellValueFactory(new PropertyValueFactory<>("ICAO"));
        callsignColumn.setCellValueFactory(new PropertyValueFactory<>("callsign"));
        countryColumn.setCellValueFactory(new PropertyValueFactory<>("country"));
        activeStatusColumn.setCellValueFactory(new PropertyValueFactory<>("activeStatus"));

        //Load data by taking the Airline ArrayList and converting it to an ObservableArrayList.
        ObservableList<Airline> airlines = FXCollections.observableList(storage.getAirlines());
        tableView.setItems(airlines);

        //Set choice box to list of potential search types
        searchTypeSelection.setItems(searchTypes);
    }

    /**
     * Calls searchAirlines method from searcher class and upldates table to display
     * results of search.
     */
    public void searchByDataType(String searchTerm, String searchType) {
        ArrayList<Airline> results = Searcher.searchAirlines(searchTerm, searchType, storage.getAirlines());
        tableView.setItems(FXCollections.observableList(results));
    }

}
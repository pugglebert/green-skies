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
import javafx.stage.Stage;
import model.data.Route;
import model.loader.FlightHistory;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

/**
 * The controller class which contains the controls for the route data view.
 * @author Hayley Krippner
 * @version 1.0
 * @since 2020-08-26
 */
public class RouteDataViewController extends DataViewController {


    //Configure the TableView.
    @FXML
    private TableView<Route> tableView;
    @FXML
    private TableColumn<Route, Boolean> addColumn;
    @FXML
    private TableColumn<Route, String> airlineNameColumn;
    @FXML
    private TableColumn<Route, Integer> airlineIDColumn;
    @FXML
    private TableColumn<Route, String> sourceAirportColumn;
    @FXML
    private TableColumn<Route, Integer> sourceAirportIDColumn;
    @FXML
    private TableColumn<Route, String> destinationAirportColumn;
    @FXML
    private TableColumn<Route, Integer> destinationAirportIDColumn;
    @FXML
    private TableColumn<Route, String> codeShareColumn;
    @FXML
    private TableColumn<Route, Integer> numOfStopsColumn;
    @FXML
    private TableColumn<Route, String[]> equipmentColumn;
    @FXML
    private Button btnFlightHistory;
    @FXML
    private ChoiceBox<String> airlineSelection;
    @FXML
    private ChoiceBox<String> sourceSelection;
    @FXML
    private ChoiceBox<String> destinationSelection;

    private final ObservableList<String> searchTypes = FXCollections.observableArrayList("Airline", "Source", "Destination");
    private ObservableList<String> airlines;
    private ObservableList<String> sources;
    private ObservableList<String> destinations;


    public RouteDataViewController() {
    }

    /**
     * Initializes the controller class.
     * @param url The URL used.
     * @param rb The resource bundle used.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Set up the columns in the TableView.
        addColumn.setCellValueFactory(new PropertyValueFactory<>("select"));
        airlineNameColumn.setCellValueFactory(new PropertyValueFactory<>("airlineName"));
        airlineIDColumn.setCellValueFactory(new PropertyValueFactory<>("airlineID"));
        sourceAirportColumn.setCellValueFactory(new PropertyValueFactory<>("sourceAirport"));
        sourceAirportIDColumn.setCellValueFactory(new PropertyValueFactory<>("sourceAirportID"));
        destinationAirportColumn.setCellValueFactory(new PropertyValueFactory<>("destinationAirport"));
        destinationAirportIDColumn.setCellValueFactory(new PropertyValueFactory<>("destinationAirportID"));
        codeShareColumn.setCellValueFactory(new PropertyValueFactory<>("codeShare"));
        numOfStopsColumn.setCellValueFactory(new PropertyValueFactory<>("numOfStops"));
        equipmentColumn.setCellValueFactory(new PropertyValueFactory<>("equipment"));

        //Load data by taking the Route ArrayList and converting it to an ObservableArrayList.
        for (Route route: storage.getRoutes()){
            route.initCheckBox();
        }
        ObservableList<Route> routes = FXCollections.observableList(storage.getRoutes());


        tableView.setItems(routes);

        //Setup choice boxes
        searchTypeSelection.setItems(searchTypes);
        List<String> tempAirlines = storage.getRouteAirlines();
        tempAirlines.add("Any");
        airlines = FXCollections.observableArrayList(tempAirlines);
        airlineSelection.setItems(airlines);
        List<String> tempSources = storage.getRouteSources();
        tempSources.add("Any");
        sources = FXCollections.observableArrayList(tempSources);
        sourceSelection.setItems(sources);
        List<String> tempDestinations = storage.getRouteDestinations();
        tempDestinations.add("Any");
        destinations = FXCollections.observableArrayList(tempDestinations);
        destinationSelection.setItems(destinations);

        //Add choice boxes to hashmap with filter type as key
        filterSelectionBoxes.put("Airline", airlineSelection);
        filterSelectionBoxes.put("Source", sourceSelection);
        filterSelectionBoxes.put("Destination", destinationSelection);
    }

    /**
     * This method closes the Upload Data page and opens the View Airline Data page.
     * @throws IOException
     */
    public void toFlightHistory() throws IOException {
        Stage stage = (Stage) btnFlightHistory.getScene().getWindow();
        stage.close();
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("flightHistory.fxml")); //open the View Airline Data page
        Scene scene = new Scene(root);
        newStage.setScene(scene);
        newStage.show();
    }

    /**
     * Calls searchRoutes method from searcher class and upldates table to display
     * results of search.
     */
    public void searchByDataType(String searchTerm, String searchType) {
        ArrayList<Route> results = Searcher.searchRoutes(searchTerm, searchType, storage.getRoutes());
        tableView.setItems(FXCollections.observableList(results));
    }

    private FlightHistory buffer;
    private ObservableList<Route> routes;


    //TODO: remove this code.

    /*add checkbox to mark which route user wanted to add*/
//    public void addCheckboxAndDisplay() {
//        buffer = new FlightHistory(this.fileDir);
//        for (Route route : buffer.getBuffer()) {
//            route.initCheckBox();
//        }
//        routes = FXCollections.observableList(buffer.getBuffer());
//        FlightTable.setItems(routes);
//    }

    public void addDataToHistory() { //TODO slow on entire data list (maybe add listener to checkbox)
        List<Route> temp = new ArrayList<Route>();
        for (Route route : Main.getStorage().getRoutes()){
            if (route.getSelect().isSelected()){
                temp.add(route);

            }
        }

        Main.getStorage().getHistory().addAll(temp);

//TODO: remove this code.
        //parent.updateTable();

        //TODo pass data back
//        Stage stage = (Stage) AddButton.getScene().getWindow();
//        stage.close();

    }

    /**
     * Calls filterRoutes method of Filterer class and sets table to display results.
     * @param filterTerms A hashmap where the key is the type of filter and the value is the
     *                    term the filter should match.
     */
    public void filterByDataType(HashMap<String, String> filterTerms) {
        ArrayList<Route> results = Filterer.filterRoutes(filterTerms, storage.getRoutes());
        tableView.setItems(FXCollections.observableList(results));
    }

    /**
     * Clear filter choices and display all routes in table view.
     */
    @Override
    public void clearFilter() {
        for (ChoiceBox<String> filterBox : filterSelectionBoxes.values()) {
            filterBox.setValue(null);
        }
        tableView.setItems(FXCollections.observableList(storage.getRoutes()));
    }

    /**
     * Clear search bar and display all routes in table view.
     */
    @Override
    public void clearSearch() {
        searchBar.setText(null);
        tableView.setItems(FXCollections.observableList(storage.getRoutes()));
    }
}
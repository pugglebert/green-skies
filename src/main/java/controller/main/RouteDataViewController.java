package controller.main;

import controller.analysis.Searcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.data.Route;
import model.loader.FlightHistory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
    public Button AddToHistoryButton;

    private final ObservableList<String> searchTypes = FXCollections.observableArrayList("Airline", "Source", "Destination");

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

    public void addDataToHistory() throws IOException { //TODO slow on entire data list (maybe add listener to checkbox)
//        List<Route> temp = new ArrayList<Route>();
//        for (Route route : Main.getStorage().getRoutes()){
//            if (route.getSelect().isSelected()){
//                temp.add(route);
//
//            }
//        }
//
//        Main.getStorage().getHistory().addAll(temp);
        RouteAddToHistoryPopUpController popUp = new RouteAddToHistoryPopUpController();
        popUp.display(this);
//TODO: remove this code.
        //parent.updateTable();

        //TODo pass data back
//        Stage stage = (Stage) AddButton.getScene().getWindow();
//        stage.close();

    }

    /**
     * Clear search bar and display all routes in table view.
     */
    @Override
    public void clearSearch() {
        searchBar.setText(null);
        tableView.setItems(FXCollections.observableList(storage.getRoutes()));
    }

    /**
     * Launch the filter pop up box. If filtering is successful displays filtered routes in tableview.
     * @throws IOException
     */
    public void filterOptions() throws IOException {
        RouteFilterPopUpController filterPopUp = new RouteFilterPopUpController();
        filterer.setFilterSuccess(false);
        filterPopUp.display();
        if (filterer.getFilterSuccess()) {
            tableView.setItems(FXCollections.observableList(filterer.getFilteredRoutes()));
        }
    }

}
package controller.main;

import controller.analysis.ReportGenerator;
import controller.analysis.Searcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.data.Route;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
//    @FXML
//    private TableColumn<Route, Integer> airlineIDColumn;
    @FXML
    private TableColumn<Route, String> sourceAirportColumn;
//    @FXML
//    private TableColumn<Route, Integer> sourceAirportIDColumn;
    @FXML
    private TableColumn<Route, String> destinationAirportColumn;
//    @FXML
//    private TableColumn<Route, Integer> destinationAirportIDColumn;
    @FXML
    private TableColumn<Route, String> codeShareColumn;
    @FXML
    private TableColumn<Route, Integer> numOfStopsColumn;
    @FXML
    private TableColumn<Route, String[]> equipmentColumn;
    @FXML
    private Button btnFlightHistory;
    @FXML
    private Button AddToHistoryButton;
    private final ObservableList<String> searchTypes = FXCollections.observableArrayList("Airline", "Source", "Destination");
    private ReportGenerator reportGenerator;

    public RouteDataViewController() {
    }

    /**
     * Initializes the controller class. The checkboxes are added to each record.
     * @param url The URL used.
     * @param rb The resource bundle used.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        this.reportGenerator = Main.getReportGenerator();

        //Set up the columns in the TableView.
        addColumn.setCellValueFactory(new PropertyValueFactory<>("select"));
        airlineNameColumn.setCellValueFactory(new PropertyValueFactory<>("airlineName"));
//        airlineIDColumn.setCellValueFactory(new PropertyValueFactory<>("airlineID"));
        sourceAirportColumn.setCellValueFactory(new PropertyValueFactory<>("sourceAirport"));
//        sourceAirportIDColumn.setCellValueFactory(new PropertyValueFactory<>("sourceAirportID"));
        destinationAirportColumn.setCellValueFactory(new PropertyValueFactory<>("destinationAirport"));
//        destinationAirportIDColumn.setCellValueFactory(new PropertyValueFactory<>("destinationAirportID"));
        codeShareColumn.setCellValueFactory(new PropertyValueFactory<>("codeShare"));
        numOfStopsColumn.setCellValueFactory(new PropertyValueFactory<>("numOfStops"));
        equipmentColumn.setCellValueFactory(new PropertyValueFactory<>("equipment"));

        //Load data by taking the Route ArrayList and converting it to an ObservableArrayList.
        for (Route route: storage.getRoutes()){
            route.initCheckBox();
        }
        ObservableList<Route> routes = FXCollections.observableList(storage.getRoutes());
        tableView.setItems(routes);
        searchTypeSelection.setItems(searchTypes); //Setup choice boxes
    }

    /**
     * Calls searchRoutes method from searcher class and upldates table to display
     * results of search.
     */
    public void searchByDataType(String searchTerm, String searchType) {
        ArrayList<Route> results = Searcher.searchRoutes(searchTerm, searchType, storage.getRoutes());
        tableView.setItems(FXCollections.observableList(results));
    }

    //TODO: can these be removed? 13/09/2020 HK
//    private FlightHistory buffer;
//    private ObservableList<Route> routes;

    /**
     * This method adds the selected routes to the flight history. The total distance, total emissions, least distance,
     * most distance , least emissions , most emissions , least travelled  and most travelled route are updated.
     */
    public void addDataToHistory() {
        List<Route> temp = new ArrayList<Route>();
        for (Route route : Main.getStorage().getRoutes()) {
          if (route.getSelect().isSelected()) {
            temp.add(route);
            reportGenerator.updateTotalDistance(route);
            reportGenerator.updateTotalEmissions(route);
            //reportGenerator.updateLeastDistanceRoute(route);
            //reportGenerator.updateMostDistanceRoute(route);
            //reportGenerator.updateMostEmissionsRoute(route);
            //reportGenerator.updateLeastEmissionsRoute(route);
          }
        }

        Main.getStorage().getHistory().addAll(temp);
        //reportGenerator.updateLeastTravelledRoute(Main.getStorage().getHistory()); //TODO: considering doing when click on history page to reduce time wasted.
        //reportGenerator.updateMostTravelledRoute(Main.getStorage().getHistory()); //TODO: considering doing when click on history page to reduce time wasted.
        //reportGenerator.updateMostVisitedSrcAirports(); //TODO: uncommment once implemented HashMap
        //reportGenerator.updateMostVisitedSrcAirports(); //TODO: uncommment once implemented HashMap
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
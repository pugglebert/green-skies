package controller.main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import model.data.Route;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

//@TODO: should RouteDataViewController implement an inteface Initializable?

/**
 * The controller class which contains the controls for the route data view.
 * @author Hayley Krippner
 * @version 1.0
 * @since 2020-08-19
 */
public class RouteDataViewController implements Initializable {

    //configure the table
    @FXML
    private TableView<Route> tableView;
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


//    public RouteDataViewController(){
//        getRoutes()
//    }

    /**
 * Initializes the controller class.
 */
//@Override
public void initialize(URL url, ResourceBundle rb) {
    //set up the columns in the table
    airlineNameColumn.setCellValueFactory(new PropertyValueFactory<Route, String>("airlineName"));
    airlineIDColumn.setCellValueFactory(new PropertyValueFactory<Route, Integer>("airlineID"));
    sourceAirportColumn.setCellValueFactory(new PropertyValueFactory<Route, String>("sourceAirport"));
    sourceAirportIDColumn.setCellValueFactory(new PropertyValueFactory<Route, Integer>("sourceAirportID"));
    destinationAirportColumn.setCellValueFactory(new PropertyValueFactory<Route, String>("destinationAirport"));
    destinationAirportIDColumn.setCellValueFactory(new PropertyValueFactory<Route, Integer>("destinationAirportID"));
    codeShareColumn.setCellValueFactory(new PropertyValueFactory<Route, String>("codeShare"));
    numOfStopsColumn.setCellValueFactory(new PropertyValueFactory<Route, Integer>("numOfStops"));
    equipmentColumn.setCellValueFactory(new PropertyValueFactory<Route, String[]>("equipment"));

    //load data
    tableView.setItems(getRoutes());
}

    /**
     * This method will return an ObservableList of Route objects
     */
    public ObservableList<Route>  getRoutes()
    {
        ObservableList<Route> routes = FXCollections.observableArrayList();
        String[] test = {"abvc"};
        routes.add(new Route("A", 1234, "B", 4567, "C", 8910, "a", 2, test)); //@TODO: need to provide specific values into Route()
//        routes.add(new Route()); //@TODO: need to provide specific values into Route()
//        routes.add(new Route()); //@TODO: need to provide specific values into Route()

        return routes;
    }

//    public static void main(String[] args) {
//        launch(args);
//    }
}

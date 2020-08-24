package controller.main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.data.DataType;
import model.data.Route;
import model.data.Storage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
    @FXML
    private Button backButton;

    private Storage storage;

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

    // load data by taking the route hashset and converting it to an ArrayList to convert it to
    // ObservableArrayList.
    // TODO: 23/08/20 Change datatype in Storage class into ArrayList
    //List<Route> list = new ArrayList<Route>((HashSet) Storage.getRoutes());
    // TODO: 23/08/20 Find a cleaner way to convert list to observableList
    //ObservableList<Route> routes = FXCollections.observableArrayList();
    ObservableList<Route> routes = FXCollections.observableList(storage.getRoutes());
    tableView.setItems(routes);
}

    /**
     * This method will return an ObservableList of Route objects
     */
//    public ObservableList<Route>  getRoutes()
//    {
//        ObservableList<Route> routes = FXCollections.observableArrayList();
//        String[] test = {"abvc"};
//        routes.add(new Route("A", 1234, "B", 4567, "C", 8910, "a", 2, test)); //@TODO: need to provide specific values into Route()
//        routes.add(new Route()); //@TODO: need to provide specific values into Route()
//        routes.add(new Route()); //@TODO: need to provide specific values into Route()

//        return routes;
//    }

//    public static void main(String[] args) {
//        launch(args);
//    }
    //take user back to the welcome screen in case of wanting to see info screen
    public void toRouteDataView() throws IOException {
        // TODO: 23/08/20 how to close it with routeData as a button instead of menuitem

        Stage stage = (Stage) backButton.getScene().getWindow();   //get current window
        stage.close();  // close current window
        Stage stage1 = new Stage(); // create new stage
        Parent root = FXMLLoader.load(getClass().getResource("viewRouteData.fxml")); //reopen welcome.fxml
        Scene scene = new Scene(root);   //add thing to scene
        stage1.setScene(scene);
        stage1.show();
    }

    //take user back to the welcome screen in case of wanting to see info screen
    public void toAirportDataView() throws IOException {
        // TODO: 23/08/20 how to close it with routeData as a button instead of menuitem

        Stage stage = (Stage) backButton.getScene().getWindow();   //get current window
        stage.close();  // close current window
        Stage stage1 = new Stage(); // create new stage
        Parent root = FXMLLoader.load(getClass().getResource("viewAirportData.fxml")); //reopen welcome.fxml
        Scene scene = new Scene(root);   //add thing to scene
        stage1.setScene(scene);
        stage1.show();
    }

    //take user back to the welcome screen in case of wanting to see info screen
    public void toAirlineDataView() throws IOException {
        // TODO: 23/08/20 how to close it with routeData as a button instead of menuitem

        Stage stage = (Stage) backButton.getScene().getWindow();   //get current window
        stage.close();  // close current window
        Stage stage1 = new Stage(); // create new stage
        Parent root = FXMLLoader.load(getClass().getResource("viewAirlineData.fxml")); //reopen welcome.fxml
        Scene scene = new Scene(root);   //add thing to scene
        stage1.setScene(scene);
        stage1.show();
    }
}

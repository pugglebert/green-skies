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
import model.data.Airport;
import model.data.Route;
import model.data.Storage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;

//@TODO: should AirlineDataViewController implement an inteface Initializable?

/**
 * The controller class which contains the controls for the airport data view.
 * @author Hayley Krippner
 * @version 1.0
 * @since 2020-08-19
 */
public class AirportDataViewController implements Initializable {

    //configure the table
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
    private TableColumn<Airport, Float> latitudeColumn;
    @FXML
    private TableColumn<Airport, Float> longitudeColumn;
    @FXML
    private TableColumn<Airport, Integer> altitudeColumn;
    @FXML
    private TableColumn<Airport, Float> timezoneColumn;
    @FXML
    private TableColumn<Airport, String> DSTColumn;
    @FXML
    private TableColumn<Airport, String> dataBaseTimeZoneColumn;
    @FXML
    private Button backButton;

    /**
     * Initializes the controller class.
     */
//@Override
    public void initialize(URL url, ResourceBundle rb) {
        //set up the columns in the table
        airportIDColumn.setCellValueFactory(new PropertyValueFactory<Airport, Integer>("airportID"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Airport, String>("name"));
        cityColumn.setCellValueFactory(new PropertyValueFactory<Airport, String>("city"));
        countryColumn.setCellValueFactory(new PropertyValueFactory<Airport, String>("country"));
        IATAColumn.setCellValueFactory(new PropertyValueFactory<Airport, String>("IATA"));
        ICAOColumn.setCellValueFactory(new PropertyValueFactory<Airport, String>("ICAO"));
        latitudeColumn.setCellValueFactory(new PropertyValueFactory<Airport, Float>("latitude"));
        longitudeColumn.setCellValueFactory(new PropertyValueFactory<Airport, Float>("longitude"));
        altitudeColumn.setCellValueFactory(new PropertyValueFactory<Airport, Integer>("altitude"));
        timezoneColumn.setCellValueFactory(new PropertyValueFactory<Airport, Float>("timezone"));
        DSTColumn.setCellValueFactory(new PropertyValueFactory<Airport, String>("DST"));
        dataBaseTimeZoneColumn.setCellValueFactory(new PropertyValueFactory<Airport, String>("dataBaseTimeZone"));

//        // load data by taking the route hashset and converting it to an ArrayList to convert it to
//        // ObservableArrayList.
//        // TODO: 23/08/20 Change datatype in Storage class into ArrayList
//        List<Airport> list = new ArrayList<Airport>((HashSet) Storage.getAirports());
//        // TODO: 23/08/20 Find a cleaner way to convert list to observableList
//        ObservableList<Airport> airports = FXCollections.observableArrayList();
//        for (Airport entry: list){
//            airports.add(entry);
//        }
//        tableView.setItems((ObservableList) airports);
    }

//
    /**
     * This method will return an ObservableList of Airport objects
     */
//    public ObservableList<Airport>  getAirport()
//    {
//        ObservableList<Airport> airports = FXCollections.observableArrayList();
//        airports.add(new Airport()); //@TODO: need to provide specific values into Airport()
//        airports.add(new Airport()); //@TODO: need to provide specific values into Airport()
//        airports.add(new Airport()); //@TODO: need to provide specific values into Airport()

//        return airports;
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
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
import javafx.stage.Stage;
import model.data.Airline;
import model.data.Airport;
import model.data.Storage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
    private Button btnUpload;
    @FXML
    private Button btnRouteDataView;
    @FXML
    private Button btnAirportDataView;
    @FXML
    private Button btnAirlineDataView;

    private Storage storage = Main.getStorage();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //set up the columns in the table
        airportIDColumn.setCellValueFactory(new PropertyValueFactory<Airport, Integer>("airportID"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Airport, String>("name"));
        cityColumn.setCellValueFactory(new PropertyValueFactory<Airport, String>("city"));
        countryColumn.setCellValueFactory(new PropertyValueFactory<Airport, String>("country"));
        IATAColumn.setCellValueFactory(new PropertyValueFactory<Airport, String>("IATA"));
        ICAOColumn.setCellValueFactory(new PropertyValueFactory<Airport, String>("ICAO"));
        latitudeColumn.setCellValueFactory(new PropertyValueFactory<Airport, Double>("latitude"));
        longitudeColumn.setCellValueFactory(new PropertyValueFactory<Airport, Double>("longitude"));
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

        // load data by taking the Airline hashset and converting it to an ArrayList to convert it to
        // ObservableArrayList.
        // TODO: 23/08/20 Change datatype in Storage class into ArrayList
        // List<Airline> list = new ArrayList<Airline>((HashSet) Storage.getAirlines());
        // TODO: 23/08/20 Find a cleaner way to convert list to observableList
        ObservableList<Airport> airports = FXCollections.observableList(storage.getAirports());
        tableView.setItems(airports);
    }

    //take user back to the upload screen
    public void toUploadData() throws IOException {
        Stage stage = (Stage) btnUpload.getScene().getWindow();   //get current window
        stage.close();  // close current window
        Stage stage1 = new Stage(); // create new stage
        Parent root = FXMLLoader.load(getClass().getResource("upload.fxml")); //reopen welcome.fxml
        Scene scene = new Scene(root);   //add thing to scene
        stage1.setScene(scene);
        stage1.show();
    }

    //take user back to the route data view
    public void toRouteDataView() throws IOException {
        Stage stage = (Stage) btnRouteDataView.getScene().getWindow();   //get current window
        stage.close();  // close current window
        Stage stage1 = new Stage(); // create new stage
        Parent root = FXMLLoader.load(getClass().getResource("viewRouteData.fxml")); //reopen welcome.fxml
        Scene scene = new Scene(root);   //add thing to scene
        stage1.setScene(scene);
        stage1.show();
    }

    //take user back to the airport data view
    public void toAirportDataView() throws IOException {
        Stage stage = (Stage) btnAirportDataView.getScene().getWindow();   //get current window
        stage.close();  // close current window
        Stage stage1 = new Stage(); // create new stage
        Parent root = FXMLLoader.load(getClass().getResource("viewAirportData.fxml")); //reopen welcome.fxml
        Scene scene = new Scene(root);   //add thing to scene
        stage1.setScene(scene);
        stage1.show();
    }

    //take user back to the airline data view screen
    public void toAirlineDataView() throws IOException {
        Stage stage = (Stage) btnAirlineDataView.getScene().getWindow();   //get current window
        stage.close();  // close current window
        Stage stage1 = new Stage(); // create new stage
        Parent root = FXMLLoader.load(getClass().getResource("viewAirlineData.fxml")); //reopen welcome.fxml
        Scene scene = new Scene(root);   //add thing to scene
        stage1.setScene(scene);
        stage1.show();
    }
}
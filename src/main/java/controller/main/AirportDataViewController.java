package controller.main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import model.data.Airport;
import java.net.URL;
import java.util.ResourceBundle;

//@TODO: should AirlineDataViewController implement an inteface Initializable?

/**
 * The controller class which contains the controls for the airport data view.
 * @author Hayley Krippner
 * @version 1.0
 * @since 2020-08-19
 */
public class AirportDataViewController {

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

//
//    //load data
//    tableView.setItems(getAirports());
    }
//
//    /**
//     * This method will return an ObservableList of Airport objects
//     */
//    public ObservableList<Airport>  getAirport()
//    {
//        ObservableList<Airport> airports = FXCollections.observableArrayList();
//        routes.add(new Airport()); //@TODO: need to provide specific values into Airport()
//        routes.add(new Airport()); //@TODO: need to provide specific values into Airport()
//        routes.add(new Airport()); //@TODO: need to provide specific values into Airport()
//
//        return airports;
//    }
}
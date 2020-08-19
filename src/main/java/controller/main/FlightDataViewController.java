package controller.main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import model.data.Flight;
import java.net.URL;
import java.util.ResourceBundle;

//@TODO: should FlightDataViewController implement an inteface Initializable?

/**
 * The controller class which contains the controls for the flight data view.
 * @author Hayley Krippner
 * @version 1.0
 * @since 2020-08-19
 */
public class FlightDataViewController {

    //configure the table
    @FXML
    private TableView<Flight> tableView;
    @FXML
    private TableColumn<Flight, String> flightIDColumn;
    @FXML
    private TableColumn<Flight, Flight> typeColumn;
    @FXML
    private TableColumn<Flight, String> airportsViaColumn;
    @FXML
    private TableColumn<Flight, Integer> altitudeColumn;
    @FXML
    private TableColumn<Flight, String> positionColumn;
    @FXML
    private TableColumn<Flight, Integer> distanceColumn;
    @FXML
    private TableColumn<Flight, String> flightNameColumn;

/**
 * Initializes the controller class.
 */
//@Override
public void initialize(URL url, ResourceBundle rb) {
    //set up the columns in the table
    flightIDColumn.setCellValueFactory(new PropertyValueFactory<Flight, String>("flightID"));
    typeColumn.setCellValueFactory(new PropertyValueFactory<Flight, Flight>("type"));
    airportsViaColumn.setCellValueFactory(new PropertyValueFactory<Flight, String>("airportsVia"));
    altitudeColumn.setCellValueFactory(new PropertyValueFactory<Flight, Integer>("altitude"));
    positionColumn.setCellValueFactory(new PropertyValueFactory<Flight, String>("position"));
    distanceColumn.setCellValueFactory(new PropertyValueFactory<Flight, Integer>("distance"));
    flightNameColumn.setCellValueFactory(new PropertyValueFactory<Flight, String>("flightName"));

    //load data
//    tableView.setItems(getFlight());
}
//
//    /**
//     * This method will return an ObservableList of Flight objects
//     */
//    public ObservableList<Flight>  getFlights()
//    {
//        ObservableList<Flight> flights = FXCollections.observableArrayList();
//        routes.add(new Flight()); //@TODO: need to provide specific values into Flight()
//        routes.add(new Flight()); //@TODO: need to provide specific values into Flight()
//        routes.add(new Flight()); //@TODO: need to provide specific values into Flight()
//
//        return flights;
//    }
}
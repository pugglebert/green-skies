package controller.main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import model.data.Airline;
import java.net.URL;
import java.util.ResourceBundle;

//@TODO: should AirlineDataViewController implement an inteface Initializable?

/**
 * The controller class which contains the controls for the airline data view.
 * @author Hayley Krippner
 * @version 1.0
 * @since 2020-08-19
 */
public class AirlineDataViewController {

    //configure the table
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

//    private final int airlineID;
//    private final String name;
//    private final String alias;
//    private final String IATA;
//    private final String ICAO;
//    private final String callsign;
//    private final String country;
//    private final Boolean activeStatus;
//

    /**
     * Initializes the controller class.
     */
//@Override
    public void initialize(URL url, ResourceBundle rb) {

        //set up the columns in the table
        airlineIDColumn.setCellValueFactory(new PropertyValueFactory<Airline, Integer>("airlineID"));
        airlineNameColumn.setCellValueFactory(new PropertyValueFactory<Airline, String>("airlineName"));
        airlineAliasColumn.setCellValueFactory(new PropertyValueFactory<Airline, String>("airlineAlias"));
        airlineIATAColumn.setCellValueFactory(new PropertyValueFactory<Airline, String>("airlineIATA"));
        ICAOColumn.setCellValueFactory(new PropertyValueFactory<Airline, String>("ICAO"));
        callsignColumn.setCellValueFactory(new PropertyValueFactory<Airline, String>("callsign"));
        countryColumn.setCellValueFactory(new PropertyValueFactory<Airline, String>("country"));
        activeStatusColumn.setCellValueFactory(new PropertyValueFactory<Airline, Boolean>("activeStatus"));

        //load data
//    tableView.setItems(getRoutes());
    }
//
//    /**
//     * This method will return an ObservableList of Airline objects
//     */
//    public ObservableList<Airline>  getAirlines()
//    {
//        ObservableList<Airline> airlines = FXCollections.observableArrayList();
//        routes.add(new Airline()); //@TODO: need to provide specific values into Route()
//        routes.add(new Airline()); //@TODO: need to provide specific values into Route()
//        routes.add(new Airline()); //@TODO: need to provide specific values into Route()
//
//        return airlines;
//    }
}
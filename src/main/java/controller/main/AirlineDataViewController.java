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
import model.data.Airline;
import model.data.Airline;
import model.data.Storage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;

//@TODO: should AirlineDataViewController implement an inteface Initializable?

/**
 * The controller class which contains the controls for the airline data view.
 * @author Hayley Krippner
 * @version 1.0
 * @since 2020-08-19
 */
public class AirlineDataViewController implements Initializable {

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
    @FXML
    private Button backButton;

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

        // load data by taking the Airline hashset and converting it to an ArrayList to convert it to
        // ObservableArrayList.
        // TODO: 23/08/20 Change datatype in Storage class into ArrayList
        List<Airline> list = new ArrayList<Airline>((HashSet) Storage.getAirlines());
        // TODO: 23/08/20 Find a cleaner way to convert list to observableList
        ObservableList<Airline> airlines = FXCollections.observableArrayList();
        for (Airline entry: list){
            airlines.add(entry);
        }
        tableView.setItems((ObservableList) airlines);
    }
//
//    /**
//     * This method will return an ObservableList of Airline objects
//     */
//    public ObservableList<Airline>  getAirlines()
//    {
//        ObservableList<Airline> airlines = FXCollections.observableArrayList();
//        Airlines.add(new Airline()); //@TODO: need to provide specific values into Airline()
//        Airlines.add(new Airline()); //@TODO: need to provide specific values into Airline()
//        Airlines.add(new Airline()); //@TODO: need to provide specific values into Airline()
//
//        return airlines;
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
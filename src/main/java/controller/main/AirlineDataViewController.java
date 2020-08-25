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
import model.data.Storage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * The controller class which contains the controls for the airline data view.
 * @author Hayley Krippner
 * @version 1.0
 * @since 2020-08-26
 */
public class AirlineDataViewController implements Initializable {

    //Configure the TableView.
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
     * @param url The URL used.
     * @param rb The resource bundle used.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Set up the columns in the TableView.
        airlineIDColumn.setCellValueFactory(new PropertyValueFactory<>("airlineID"));
        airlineNameColumn.setCellValueFactory(new PropertyValueFactory<>("airlineName"));
        airlineAliasColumn.setCellValueFactory(new PropertyValueFactory<>("airlineAlias"));
        airlineIATAColumn.setCellValueFactory(new PropertyValueFactory<>("airlineIATA"));
        ICAOColumn.setCellValueFactory(new PropertyValueFactory<>("ICAO"));
        callsignColumn.setCellValueFactory(new PropertyValueFactory<>("callsign"));
        countryColumn.setCellValueFactory(new PropertyValueFactory<>("country"));
        activeStatusColumn.setCellValueFactory(new PropertyValueFactory<>("activeStatus"));

        //Load data by taking the Airline ArrayList and converting it to an ObservableArrayList.
        ObservableList<Airline> airlines = FXCollections.observableList(storage.getAirlines());
        tableView.setItems(airlines);
    }

    /**
     * This method closes the View Airline Data page and opens the Upload Data page.
     * @throws IOException
     */
    public void toUploadData() throws IOException {
        Stage stage = (Stage) btnUpload.getScene().getWindow();
        stage.close();
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("upload.fxml")); //open the Upload Data page
        Scene scene = new Scene(root);
        newStage.setScene(scene);
        newStage.show();
    }

    /**
     * This method closes the View Airline Data page and opens the View Route Data page.
     * @throws IOException
     */
    public void toRouteDataView() throws IOException {
        Stage stage = (Stage) btnRouteDataView.getScene().getWindow();
        stage.close();
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("viewRouteData.fxml")); //open the View Route Data page
        Scene scene = new Scene(root);
        newStage.setScene(scene);
        newStage.show();
    }

    /**
     * This method closes the View Airline Data page and opens the View Airport Data page.
     * @throws IOException
     */
    public void toAirportDataView() throws IOException {
        Stage stage = (Stage) btnAirportDataView.getScene().getWindow();
        stage.close();
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("viewAirportData.fxml")); //open the View Airport Data page
        Scene scene = new Scene(root);
        newStage.setScene(scene);
        newStage.show();
    }

    /**
     * This method closes the View Airline Data page and opens the View Airline Data page.
     * @throws IOException
     */
    public void toAirlineDataView() throws IOException {
        Stage stage = (Stage) btnAirlineDataView.getScene().getWindow();
        stage.close();
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("viewAirlineData.fxml")); //open the View Airline Data page
        Scene scene = new Scene(root);
        newStage.setScene(scene);
        newStage.show();
    }

}
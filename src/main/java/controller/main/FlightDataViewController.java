package controller.main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.data.Route;
import model.loader.FlightHistory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FlightDataViewController implements Initializable {


    @FXML
    public TableView<Route> FlightTable;
    @FXML
    public TableColumn<Route, Boolean> addColumn;
    @FXML
    public TableColumn<Route, String> airlineNameColumn;
    @FXML
    public TableColumn<Route, Integer> airlineIDColumn;
    @FXML
    public TableColumn<Route, String> sourceAirportColumn;
    @FXML
    public TableColumn<Route, Integer> sourceAirportIDColumn;
    @FXML
    public TableColumn<Route, String> destinationAirportColumn;
    @FXML
    public TableColumn<Route, Integer> destinationAirportIDColumn;
    @FXML
    public TableColumn<Route, String> codeShareColumn;
    @FXML
    public TableColumn<Route, Integer> numOfStopsColumn;
    @FXML
    public TableColumn<Route, String[]> equipmentColumn;
    @FXML
    public Button btnUpload;
    @FXML
    public Button btnRouteDataView;
    @FXML
    public Button btnAirportDataView;
    @FXML
    public Button btnAirlineDataView;
    @FXML
    public Button addTest;


    //TODO delete this
    public void main() throws Exception {
        FlightHistory test = new FlightHistory();
        ObservableList<Route> routes = FXCollections.observableList(test.buffer);
        FlightTable.setItems(routes);
        System.out.println("added");
    }


    public void initialize(URL url, ResourceBundle rb) {
        addColumn.setCellValueFactory(new PropertyValueFactory<>("select"));
        airlineNameColumn.setCellValueFactory(new PropertyValueFactory<>("airlineName"));
        airlineIDColumn.setCellValueFactory(new PropertyValueFactory<>("airlineID"));
        sourceAirportColumn.setCellValueFactory(new PropertyValueFactory<>("sourceAirport"));
        sourceAirportIDColumn.setCellValueFactory(new PropertyValueFactory<>("sourceAirportID"));
        destinationAirportColumn.setCellValueFactory(new PropertyValueFactory<>("destinationAirport"));
        destinationAirportIDColumn.setCellValueFactory(new PropertyValueFactory<>("destinationAirportID"));
        codeShareColumn.setCellValueFactory(new PropertyValueFactory<>("codeShare"));
        numOfStopsColumn.setCellValueFactory(new PropertyValueFactory<>("numOfStops"));
        equipmentColumn.setCellValueFactory(new PropertyValueFactory<>("equipment"));

    }

    /**
     * This method closes the View Airline Data page and opens the Upload Data page.
     *
     * @throws IOException
     */

    public void toUploadData() throws IOException {


    }

    /**
     * This method closes the View Airline Data page and opens the View Route Data page.
     *
     * @throws IOException
     */
    public void toRouteDataView() throws IOException {

    }

    /**
     * This method closes the View Airline Data page and opens the View Airport Data page.
     *
     * @throws IOException
     */
    public void toAirportDataView() throws IOException {

    }

    /**
     * This method closes the View Airline Data page and opens the View Airline Data page.
     *
     * @throws IOException
     */
    public void toAirlineDataView() throws IOException {

    }

}


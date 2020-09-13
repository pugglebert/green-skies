package controller.main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.data.Route;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RouteAddToHistoryPopUpController implements Initializable {


    @FXML
    private TableView<Route> tableView;
    @FXML
    public TableColumn<Route, String> passengers;
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
    public Button confirmBtn;
    @FXML
    public Button cancelBtn;

    private RouteDataViewController caller;
    private ArrayList<Route> testRoutes;


    public void initialize(URL url, ResourceBundle rb) {
        passengers.setCellValueFactory(new PropertyValueFactory<>("timesTaken"));
        airlineNameColumn.setCellValueFactory(new PropertyValueFactory<>("airlineName"));
        airlineIDColumn.setCellValueFactory(new PropertyValueFactory<>("airlineID"));
        sourceAirportColumn.setCellValueFactory(new PropertyValueFactory<>("sourceAirport"));
        sourceAirportIDColumn.setCellValueFactory(new PropertyValueFactory<>("sourceAirportID"));
        destinationAirportColumn.setCellValueFactory(new PropertyValueFactory<>("destinationAirport"));
        destinationAirportIDColumn.setCellValueFactory(new PropertyValueFactory<>("destinationAirportID"));
        codeShareColumn.setCellValueFactory(new PropertyValueFactory<>("codeShare"));
        numOfStopsColumn.setCellValueFactory(new PropertyValueFactory<>("numOfStops"));
        equipmentColumn.setCellValueFactory(new PropertyValueFactory<>("equipment"));


//        List<Route> testRoutes = new ArrayList<>();
        testRoutes = new ArrayList<>();
        testRoutes.add(new Route("FM", 4609, "CTU", 3395, "SHA",
                3391, "", 0, "757 737 738".split(" ")));
        testRoutes.add(new Route("MH", 3378, "MYY", 3266, "BTU",
                3262, "Y", 0, "AT7".split(" ")));
        ObservableList<Route> routes = FXCollections.observableList(testRoutes);
        tableView.setItems(routes);
        tableView.setEditable(true);
        passengers.setCellFactory(TextFieldTableCell.forTableColumn());

    }

    public void display(RouteDataViewController caller) throws IOException {
        final Stage popUp = new Stage();
        this.caller = caller;
        popUp.initModality(Modality.APPLICATION_MODAL);
        Parent root = FXMLLoader.load(getClass().getResource("routeAddToHistoryPopUp.fxml"));
        Scene scene = new Scene(root);
        popUp.setScene(scene);
        popUp.show();

    }


    public void confirm() {
        for (Route route : testRoutes) {
            System.out.println(route.getTimesTake());
        }

    }
}



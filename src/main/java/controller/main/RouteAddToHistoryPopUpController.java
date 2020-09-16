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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.data.Route;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
// TODO: check all method comments start with "This method ..."

/**
 * The controller class which contains the controls for the route data view.
 *
 * @author Hayley Krippner
 * @version 1.0
 * @since 15/09/2020
 */
public class RouteAddToHistoryPopUpController implements Initializable {

  @FXML private TableView<Route> tableView;
  @FXML private TableColumn<Route, Integer> passengerNumber;
  @FXML private TableColumn<Route, String> airlineNameColumn;
  @FXML private TableColumn<Route, String> sourceAirportColumn;
  @FXML private TableColumn<Route, String> destinationAirportColumn;
  @FXML private TableColumn<Route, String> codeShareColumn;
  @FXML private TableColumn<Route, Integer> numOfStopsColumn;
  @FXML private TableColumn<Route, String> equipmentColumn;
  @FXML private Button confirmBtn;
  @FXML private Button cancelBtn;
  @FXML private AnchorPane btnChangePassenger;
  // TODO: write comments for these attributes

  private ArrayList<Route> tempRoute;
  private RouteDataViewController caller;
  // TODO: write comment for this method

  public void display() throws IOException {
    setTable();
    Parent root = FXMLLoader.load(getClass().getResource("routeAddToHistoryPopUp.fxml"));
    Stage addPopUp = new Stage();
    addPopUp.initModality(Modality.APPLICATION_MODAL);
    addPopUp.setScene(new Scene(root));
    addPopUp.show();
  }

  /**
   * Initializes the controller class. The checkboxes are added to each record.
   *
   * @param url The URL used.
   * @param rb The resource bundle used.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {

    // Set up the columns in the TableView.

  }

  //    protected void setTableView() {
  //        ObservableList<Route> routes = FXCollections.observableList(tempRoute);
  //        tableView.setItems(routes);
  //    }

  public void confirm() {} // TODO: write comment for this method

  public void setCaller(RouteDataViewController caller) {
    this.caller = caller;
  }
  // TODO: write comment for this method

  public ArrayList<Route> getTempRoute() {
    return this.tempRoute;
  }
  // TODO: write comment for this method

  public void setTable() {
    passengerNumber.setCellValueFactory(new PropertyValueFactory<>("timesTaken"));
    airlineNameColumn.setCellValueFactory(new PropertyValueFactory<>("airlineName"));
    sourceAirportColumn.setCellValueFactory(new PropertyValueFactory<>("sourceAirport"));
    destinationAirportColumn.setCellValueFactory(new PropertyValueFactory<>("destinationAirport"));
    codeShareColumn.setCellValueFactory(new PropertyValueFactory<>("codeShare"));
    numOfStopsColumn.setCellValueFactory(new PropertyValueFactory<>("numOfStops"));
    equipmentColumn.setCellValueFactory(new PropertyValueFactory<>("firstEquipment"));
    ObservableList<Route> routes = FXCollections.observableList(tempRoute);
    tableView.setItems(routes);
  }
}

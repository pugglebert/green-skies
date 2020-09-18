package controller.main.dataview;

import controller.analysis.FlightAnalyser;
import controller.analysis.ReportGenerator;
import controller.analysis.Searcher;
import controller.main.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
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

  // Configure the TableView.
  @FXML private TableView<Route> tableView;
  @FXML private TableColumn<Route, Integer> passengerNumber;
  @FXML private TableColumn<Route, String> airlineNameColumn;
  @FXML private TableColumn<Route, String> sourceAirportColumn;
  @FXML private TableColumn<Route, String> destinationAirportColumn;
  @FXML private TableColumn<Route, String> codeShareColumn;
  @FXML private TableColumn<Route, Integer> numOfStopsColumn;
  @FXML private TableColumn<Route, String> equipmentColumn;
  @FXML private Button confirmBtn;
  // TODO: write comments for these attributes

  // TODO: write comment for this method

  public void display() throws IOException {

    Parent root = FXMLLoader.load(getClass().getResource("../routeAddToHistoryPopUp.fxml"));
    Stage addPopUp = new Stage();
    addPopUp.setTitle("Add To History ");
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
    // below is initialize the cell of number of passenger, when double click the cell will be turned to textfield
    passengerNumber.setCellValueFactory(new PropertyValueFactory<>("timesTaken"));
    passengerNumber.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    passengerNumber.setOnEditCommit(
            new EventHandler<>() {
              @Override
              public void handle(TableColumn.CellEditEvent<Route, Integer> routeIntegerCellEditEvent) {
                Route routeChanged =
                        routeIntegerCellEditEvent
                                .getTableView()
                                .getItems()
                                .get(routeIntegerCellEditEvent.getTablePosition().getRow());
                routeChanged.setTimesTaken(routeIntegerCellEditEvent.getNewValue());
              }
            });
    airlineNameColumn.setCellValueFactory(new PropertyValueFactory<>("airlineName"));
    sourceAirportColumn.setCellValueFactory(new PropertyValueFactory<>("sourceAirport"));
    destinationAirportColumn.setCellValueFactory(new PropertyValueFactory<>("destinationAirport"));
    codeShareColumn.setCellValueFactory(new PropertyValueFactory<>("codeShare"));
    numOfStopsColumn.setCellValueFactory(new PropertyValueFactory<>("numOfStops"));
    equipmentColumn.setCellValueFactory(new PropertyValueFactory<>("firstEquipment"));

    ObservableList<Route> tempRoute =
        FXCollections.observableArrayList(Main.getStorage().getTempRoutes());
    tableView.setEditable(true);
    tableView.setItems(tempRoute);


  }

  public void confirm() {
    for (Route route : Main.getStorage().getTempRoutes()) {
      if (route.getTimesTaken() < 0) { // Have not edit number of passenger => invalid history
        continue; // drop
      } else {
        int index = Main.getStorage().getHistory().indexOf(route);
        if (index != -1) { // Route have been added to histoy
          Main.getStorage().getHistory().get(index).setTimesTaken(route.getTimesTaken());
        } else { // Route have been added to history + have been set number of passenger
          Main.getStorage().getHistory().add(route);
        }
      }
    }
    Stage stage = (Stage) confirmBtn.getScene().getWindow();
    stage.close();
  }
}

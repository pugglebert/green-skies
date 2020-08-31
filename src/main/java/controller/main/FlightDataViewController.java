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
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.data.Route;
import model.loader.FlightHistory;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FlightDataViewController implements Initializable {

  @FXML
  public AnchorPane anchorPane;
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
  public Button comfirmButton;
  @FXML
  public Button browseButton;


  private FlightHistory buffer;
  private ObservableList<Route> routes;
  private String fileDir;


  public void browseFile() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Open Route data");
    Stage stage = (Stage) anchorPane.getScene().getWindow(); // TODO browser button that open local file should not be able to refocus back to the window that opened it, need to cancel first
    File selectedFile = fileChooser.showOpenDialog(stage);
    String filedir = selectedFile.toString();
    if (filedir != null) {
      try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("viewFlightDataAddScreen.fxml"));


        Parent root1 = fxmlLoader.load();
        RouteAddDialogController controller = fxmlLoader.getController();
        controller.setFileDir(filedir);
        controller.addCheckboxAndDisplay();
        controller.setParent(this);
        Stage childStage = new Stage();
        childStage.initModality(Modality.WINDOW_MODAL);          // LOCK
        childStage.initOwner(anchorPane.getScene().getWindow()); // FOCUS
        childStage.setScene(new Scene(root1));
        childStage.show();


      } catch (Exception e) {
        System.out.println("can not load new window");
        e.printStackTrace();
      }
    }
  }

  public void initialize(URL url, ResourceBundle rb) {
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

  public void updateTable() {
    ObservableList<Route> routes = FXCollections.observableList(Main.getStorage().getRoutes());
    FlightTable.setItems(routes);
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

  /*remove route and add to existing route database*/

}

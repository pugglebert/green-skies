package controller.main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.data.Route;
import model.data.Storage;
import model.loader.FlightHistory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SurpriseTool2 implements Initializable {

  @FXML
  public Button AddButton;
  @FXML
  public TableView FlightTable;
  @FXML
  public TableColumn addColumn;
  @FXML
  public TableColumn airlineNameColumn;
  @FXML
  public TableColumn airlineIDColumn;
  @FXML
  public TableColumn sourceAirportColumn;
  @FXML
  public TableColumn sourceAirportIDColumn;
  @FXML
  public TableColumn destinationAirportIDColumn;
  @FXML
  public TableColumn destinationAirportColumn;
  @FXML
  public TableColumn codeShareColumn;
  @FXML
  public TableColumn numOfStopsColumn;
  @FXML
  public TableColumn equipmentColumn;

  private FlightHistory buffer;
  private ObservableList<Route> routes;
  private String fileDir;

  public void setParent(SurpriseTool parent) {
    this.parent = parent;
  }

  private SurpriseTool parent;

  public void setFileDir(String fileDir) {
    this.fileDir = fileDir;
  }

  /*add checkbox to mark which route user wanted to add*/
  public void addCheckboxAndDisplay() {
    buffer = new FlightHistory(this.fileDir);
    for (Route route : buffer.getBuffer()) {
      route.initCheckBox();
    }
    routes = FXCollections.observableList(buffer.getBuffer());
    FlightTable.setItems(routes);
  }

  public void confirm() { //TODO slow on entire data list (maybe add listener to checkbox)
    buffer.removeUnchecked();

    Storage storage = Main.getStorage();

    storage.addToHistory((ArrayList<Route>) buffer.getBuffer());

    //parent.updateTable();

    //TODo pass data back
    Stage stage = (Stage) AddButton.getScene().getWindow();
    stage.close();

  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {

    addColumn.setCellValueFactory(new PropertyValueFactory<>("select"));
    airlineNameColumn.setCellValueFactory(new PropertyValueFactory<>("airlineName"));
    airlineIDColumn.setCellValueFactory(new PropertyValueFactory<>("airlineID"));
    sourceAirportColumn.setCellValueFactory(new PropertyValueFactory<>("sourceAirport"));
    sourceAirportIDColumn.setCellValueFactory(new PropertyValueFactory<>("sourceAirportID"));
    destinationAirportColumn.setCellValueFactory(new PropertyValueFactory<>("destinationAirport"));
    destinationAirportIDColumn.setCellValueFactory(
            new PropertyValueFactory<>("destinationAirportID"));
    codeShareColumn.setCellValueFactory(new PropertyValueFactory<>("codeShare"));
    numOfStopsColumn.setCellValueFactory(new PropertyValueFactory<>("numOfStops"));
    equipmentColumn.setCellValueFactory(new PropertyValueFactory<>("equipment"));
  }
}

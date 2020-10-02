package controller.guiController.upload;

import controller.guiController.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.data.DataType;
import model.data.Route;
import model.database.SQLiteDatabase;
import model.loader.Loader;

import java.util.List;

/**
 * The controller class which contains the controls for data entry of a single Route.
 *
 * @author Grace Hanlon
 * @version 1.0
 * @since 12/09/20
 */
public class RouteSingleEntryController {

  /** Initialize a loader object */
  private final Loader loader = Main.getLoader();

  @FXML TextField airlineNameField;
  @FXML TextField airlineIDField;
  @FXML TextField numStopsField;
  @FXML TextField srcairportField;
  @FXML TextField destairportField;
  @FXML TextField codeshareField;
  @FXML TextField srcairportIDField;
  @FXML TextField destairportIDField;
  @FXML TextField equipmentField;
  @FXML Button cancelButton;
  @FXML Button addEntryButton;

  /** This method closes window when the 'Cancel' button is pushed. */
  public void closeWindow() {
    Stage stage = (Stage) cancelButton.getScene().getWindow();
    stage.close();
  }

  /** This method loads the data entered for a route as a singular line. */
  public void addEntry() {
    String entryString = makeRouteString();

    try {
      String message = loader.loadLine(entryString, "Route");

      //Add single entry to specified table.
      List<DataType> data = loader.getParser().getData();
      String fileName = loader.getLineFileName("Route");

      SQLiteDatabase database = new SQLiteDatabase();
      database.setTableName(fileName);
      while (data.remove(null));
      database.addRoutes((Route) data.get(data.size()-1));
      database.startCommite();

      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Confirm data entry upload");
      alert.setHeaderText(null);
      alert.setContentText(message);
      alert.showAndWait();

      Stage stage = (Stage) addEntryButton.getScene().getWindow();
      stage.close();

    } catch (Exception e) {
      Alert ErrorAlert = new Alert(Alert.AlertType.NONE);
      ErrorAlert.setAlertType(Alert.AlertType.ERROR);
      ErrorAlert.setContentText(e.getMessage());
      ErrorAlert.show();
    }
  }

  /**
   * This method compiles a string from the the entered data for a route.
   *
   * @return airlineString.
   */
  public String makeRouteString() {
    String routeLine = "";

    String airlineName = airlineNameField.getText() + ",";
    routeLine += airlineName;

    String airlineID = airlineIDField.getText() + ",";
    routeLine += airlineID;

    String srcairport = srcairportField.getText() + ",";
    routeLine += srcairport;

    String srcairportID = srcairportIDField.getText() + ",";
    routeLine += srcairportID;

    String destairport = destairportField.getText() + ",";
    routeLine += destairport;

    String destairportID = destairportIDField.getText() + ",";
    routeLine += destairportID;

    String codeShare = codeshareField.getText() + ",";
    routeLine += codeShare;

    String numStops = numStopsField.getText() + ",";
    routeLine += numStops;

    String equipment = equipmentField.getText();
    routeLine += equipment;

    return routeLine;
  }

  /**
   * This method launches alert giving user information on expected format of airline entry.
   */
  @FXML
  public void routeHelp() {
    Alert ErrorAlert = new Alert(Alert.AlertType.NONE);
    ErrorAlert.setAlertType(Alert.AlertType.INFORMATION);
    ErrorAlert.setContentText("Expected format of route entry:\n" +
            "Airline name: a number followed by a capital letter e.g. 2B\n" +
            "Airline ID: '\\N' if Airline ID is unknown, an integer otherwise\n" +
            "Number of Stops: an integer\n" +
            "Source Airport: a combination of three capital letters e.g. 'AER'\n" +
            "Destination Airport: a combination of three capital letters e.g. 'AER'\n" +
            "Code Share: 'Y' or blank if unknown\n" +
            "Source Airport ID: a four digit number, otherwise '\\N' if unknown\n" +
            "Destination Airport ID: a four digit number, otherwise '\\N' if unknown\n" +
            "Equipment: a combination of three letters or numbers\n");
    ErrorAlert.show();
  }

}

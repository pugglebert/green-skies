package controller.guiController.upload;

import controller.guiController.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.data.Airline;
import model.data.DataType;
import model.database.SQLiteDatabase;
import model.loader.Loader;

import java.util.List;

/**
 * The controller class which contains the controls for data entry of a single Airline.
 *
 * @author Grace Hanlon
 * @version 1.0
 * @since 12/09/20
 */
public class AirlineSingleEntryController {
  /** Initialize a loader object */
  private final Loader loader = Main.getLoader();

  @FXML TextField nameField;
  @FXML TextField airlineIDField;
  @FXML TextField aliasField;
  @FXML TextField iataField;
  @FXML TextField icaoField;
  @FXML TextField callsignField;
  @FXML TextField countryField;
  @FXML CheckBox activeCheck;
  @FXML Button cancelButton;
  @FXML Button addButton;

  /** This method closes window when the 'Cancel' button is pushed. */
  public void closeWindow() {
    Stage stage = (Stage) cancelButton.getScene().getWindow();
    stage.close();
  }

  /**
   * This method presents the user with a confirmation box before uploading a file
   * @param message
   */
  public void showConfirmDialog(String message) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Confirm data entry upload");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
  }


  /** This method loads the data entered for an airline as a singular line. */
  public void addEntry() {
    String entryString = makeAirlineString();

    try {
      String message = loader.loadLine(entryString, "Airline");

      //Add single entry to specified table.
      List<DataType> data = loader.getParser().getData();
      String fileName = loader.getLineFileName("Airline");

      SQLiteDatabase database = new SQLiteDatabase();
      database.setTableName(fileName);
      while (data.remove(null));
      database.addAirlines((Airline) data.get(data.size()-1));
      database.startCommite();

      showConfirmDialog(message);

      Stage stage = (Stage) addButton.getScene().getWindow();
      stage.close();

    } catch (Exception e) {
      Alert ErrorAlert = new Alert(Alert.AlertType.NONE);
      ErrorAlert.setAlertType(Alert.AlertType.ERROR);
      ErrorAlert.setContentText(e.getMessage());
      ErrorAlert.show();
    }
  }

  /**
   * This method compiles a string from the the entered data for an airline.
   *
   * @return airlineString.
   */
  public String makeAirlineString() {
    String airlineString = "";

    String airlineID = airlineIDField.getText();
    airlineString += airlineID + ",";

    String name = nameField.getText();
    airlineString += name + ",";

    String alias = aliasField.getText();
    airlineString += alias + ",";

    String itat = iataField.getText();
    airlineString += itat + ",";

    String icao = icaoField.getText();
    airlineString += icao + ",";

    String callsign = callsignField.getText();
    airlineString += callsign + ",";

    String country = countryField.getText();
    airlineString += country + ",";

    if (activeCheck.isSelected()) {
      airlineString += "\"Y\"";
    } else {
      airlineString += "\"N\"";
    }

    return airlineString;
  }

  /**
   * This method launches alert giving user information on expected format of airline entry.
   */
  public void airlineHelp() {
    Alert ErrorAlert = new Alert(Alert.AlertType.NONE);
    ErrorAlert.setAlertType(Alert.AlertType.INFORMATION);
    ErrorAlert.setContentText("Expected format of airline entry:\n" +
            "Airline name: a combination of letters and spaces\n" +
            "Airline ID: a positive integer\n" +
            "Alias: '\\N' if the airline has no alias, or a combination of letters and spaces\n" +
            "IATA: '\\N' if IATA is unknown, two capital letters or numbers otherwise\n" +
            "ICAO: '\\N' or N/A if ICAO is unknown, three capital letters otherwise\n" +
            "Callsign: a combination of capital letters and spaces, or blank if unknown\n" +
            "Country: a combination of letters and spaces\n" +
            "Active status: check if active, leave unchecked if inactive");
    ErrorAlert.show();
  }
}

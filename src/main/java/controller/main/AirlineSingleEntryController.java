package controller.main;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.loader.Loader;


/**
 * The controller class which contains the controls for data entry of a single Airline.
 *
 * @author Grace Hanlon
 * @version 1.0
 * @since 12/09/20
 */
public class AirlineSingleEntryController {
  /**
   * Initialize a loader attribute
   */
  private final Loader loader = Main.getLoader();

  @FXML TextField nameField;
  @FXML TextField airlineIDField;
  @FXML TextField aliasField;
  @FXML TextField itatField;
  @FXML TextField icaoField;
  @FXML TextField callsignField;
  @FXML TextField countryField;
  @FXML CheckBox activeCheck;
  @FXML Button cancelButton;
  @FXML Button addButton;

  /**
   * This method closes window when the 'Cancel' button is pushed.
   */
  public void closeWindow() {
    Stage stage = (Stage) cancelButton.getScene().getWindow();
    stage.close();
  }

  /**
   * This method loads the data entered for an airline as a singular line.
   */
  public void addEntry() {
    String entryString = makeAirlineString();

    try {
      String message = loader.loadLine(entryString, "Airline");
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Confirm data entry upload");
      alert.setHeaderText(null);
      alert.setContentText(message);
      alert.showAndWait();

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
   * @return airlineString.
   */
  public String makeAirlineString() {
    String airlineString = new String();

    String airlineID = airlineIDField.getText();
    airlineString += airlineID + ",";

    String name = nameField.getText();
    airlineString += name + ",";

    String alias = aliasField.getText();
    airlineString += alias + ",";

    String itat = itatField.getText();
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
}

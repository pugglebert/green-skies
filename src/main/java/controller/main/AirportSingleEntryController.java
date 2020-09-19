package controller.main;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.loader.Loader;

/**
 * The controller class which contains the controls for data entry of a single Airport.
 *
 * @author Grace Hanlon
 * @version 1.0
 * @since 12/09/20
 */
public class AirportSingleEntryController {

  /** Initialize a loader object */
  private final Loader loader = Main.getLoader();

  @FXML TextField nameFld;
  @FXML TextField cityFld;
  @FXML TextField countryFld;
  @FXML TextField iataFld;
  @FXML TextField icaoFld;
  @FXML TextField timezoneFld;
  @FXML TextField latitudeFld;
  @FXML TextField longitudeFld;
  @FXML TextField altitudeFld;
  @FXML TextField dstFld;
  @FXML TextField dbtimezoneFld;
  @FXML TextField airportidFld;
  @FXML Button cancelButton;
  @FXML Button addButton;

  /** This method closes window when the 'Cancel' button is pushed. */
  public void closeWindow() {
    Stage stage = (Stage) cancelButton.getScene().getWindow();
    stage.close();
  }

  /** This method loads the data entered for an aiport as a singular line. */
  public void addEntry() {
    String entryString = makeAirportString();

    try {
      String message = loader.loadLine(entryString, "Airport");
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
   * This method compiles a string from the the entered data for an airport.
   *
   * @return airlineString.
   */
  public String makeAirportString() {
    String airportString = "";

    String airportID = airportidFld.getText();
    airportString += airportID + ",";

    String name = nameFld.getText();
    airportString += name + ",";

    String city = cityFld.getText();
    airportString += city + ",";

    String country = countryFld.getText();
    airportString += country + ",";

    String iata = iataFld.getText();
    airportString += iata + ",";

    String icao = icaoFld.getText();
    airportString += icao + ",";

    String latitude = latitudeFld.getText();
    airportString += latitude + ",";

    String longitude = longitudeFld.getText();
    airportString += longitude + ",";

    String altitude = altitudeFld.getText();
    airportString += altitude + ",";

    String timezone = timezoneFld.getText();
    airportString += timezone + ",";

    String DST = dstFld.getText();
    airportString += DST + ",";

    String DBtimezone = dbtimezoneFld.getText();
    airportString += DBtimezone;

    return airportString;
  }
}

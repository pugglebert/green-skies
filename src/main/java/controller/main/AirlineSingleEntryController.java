package controller.main;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * The controller class which contains the controls for data entry of a single Airline.
 * @author Grace Hanlon
 * @version 1.0
 * @since 12/09/20
 */

public class AirlineSingleEntryController {

    @FXML
    TextField nameField;
    @FXML
    TextField airlineIDField;
    @FXML
    TextField aliasField;
    @FXML
    TextField itatField;
    @FXML
    TextField icaoField;
    @FXML
    TextField callsignField;
    @FXML
    TextField countryField;
    @FXML
    CheckBox activeCheck;
    @FXML
    Button cancelButton;

    /**
     * Closes window when the 'Cancel' button is pushed
     */
    public void closeWindow() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Compiles a string from the the entered data for an airline
     * @return airlineString
     */
    public String makeAirlineString() {
        String airlineString = new String();

        String airlineID = airlineIDField.getText();
        airlineString += airlineID;

        String name = nameField.getText();
        airlineString += name;

        String alias = aliasField.getText();
        airlineString += alias;

        String itat = itatField.getText();
        airlineString += itat;

        String icao = icaoField.getText();
        airlineString += icao;

        String callsign = callsignField.getText();
        airlineString += callsign;

        String country = countryField.getText();
        airlineString += country;

        Boolean active = false;
        if (activeCheck.isSelected()) {
            active = true;
        }
        String activity = String.valueOf(active);
        airlineString += activity;

        return airlineString;
    }



















}

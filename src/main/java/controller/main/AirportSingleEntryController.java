package controller.main;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * The controller class which contains the controls for data entry of a single Airport.
 * @author Grace Hanlon
 * @version 1.0
 * @since 12/09/20
 */


public class AirportSingleEntryController {

    @FXML
    TextField nameFld;
    @FXML
    TextField cityFld;
    @FXML
    TextField countryFld;
    @FXML
    TextField iataFld;
    @FXML
    TextField icaoFld;
    @FXML
    TextField timezoneFld;
    @FXML
    TextField latitudeFld;
    @FXML
    TextField longitudeFld;
    @FXML
    TextField altitudeFld;
    @FXML
    TextField dstFld;
    @FXML
    TextField dbtimezoneFld;
    @FXML
    TextField airportidFld;
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
     * Compiles a string from the the entered data for an airport
     * @return airportString
     */
    public String makeAirportString() {
        String airportString = new String();

        String airportID = airportidFld.getText();
        airportString.concat(airportID);

        String name = nameFld.getText();
        airportString.concat(name);

        String city = cityFld.getText();
        airportString.concat(city);

        String country = countryFld.getText();
        airportString.concat(country);

        String iata = iataFld.getText();
        airportString.concat(iata);

        String icao = icaoFld.getText();
        airportString.concat(icao);

        String latitude = latitudeFld.getText();
        airportString.concat(latitude);

        String longitude = longitudeFld.getText();
        airportString.concat(longitude);

        String altitude = altitudeFld.getText();
        airportString.concat(altitude);

        String timezone = timezoneFld.getText();
        airportString.concat(timezone);

        String DST = dstFld.getText();
        airportString.concat(DST);

        String DBtimezone = dbtimezoneFld.getText();
        airportString.concat(DBtimezone);

        return airportString;
    }








}

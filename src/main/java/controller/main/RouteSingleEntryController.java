package controller.main;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.data.Storage;
import model.loader.Loader;

/**
 * The controller class which contains the controls for data entry of a single Route.
 * @author Grace Hanlon
 * @version 1.0
 * @since 12/09/20
 */


public class RouteSingleEntryController {

    private final Storage storage = Main.getStorage();
    private final Loader loader = Main.getLoader();

    @FXML
    TextField airlineNameField;
    @FXML
    TextField airlineIDField;
    @FXML
    TextField numStopsField;
    @FXML
    TextField srcairportField;
    @FXML
    TextField destairportField;
    @FXML
    TextField codeshareField;
    @FXML
    TextField srcairportIDField;
    @FXML
    TextField destairportIDField;
    @FXML
    TextField equipmentField;
    @FXML
    Button cancelButton;
    @FXML
    Button addEntryButton;

    /**
     * Closes window when the 'Cancel' button is pushed
     */
    public void closeWindow() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Loads the data entered for a single route as a singular line
     */
    public void addEntry () {
        String entryString = makeRouteString();

        try {
            String message = loader.loadLine(entryString, "Route");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Confirm data entry upload");
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();

            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.close();
        }
        catch (Exception e){
            Alert ErrorAlert = new Alert(Alert.AlertType.NONE);
            ErrorAlert.setAlertType(Alert.AlertType.ERROR);
            ErrorAlert.setContentText(e.getMessage());
            ErrorAlert.show();
        }

    }

    /**
     * Compiles a string from the the entered data for a route
     * @return routeString
     */
    public String makeRouteString() {
        String routeLine = new String();

        String airlineName = airlineNameField.getText() + ", ";
        routeLine.concat(airlineName);

        String airlineID = airlineIDField.getText() + ", ";
        routeLine.concat(airlineID);

        String srcairport = srcairportField.getText() + ", ";
        routeLine.concat(srcairport);

        String srcairportID = srcairportIDField.getText() + ", ";
        routeLine.concat(srcairportID);

        String destairport = destairportField.getText() + ", ";
        routeLine.concat(destairport);

        String destairportID = destairportIDField.getText() + ", ";
        routeLine.concat(destairportID);

        String codeShare = codeshareField.getText() + ", ";
        routeLine.concat(codeShare);

        String numStops = numStopsField.getText() + ", ";
        routeLine.concat(numStops);

        String equipment = equipmentField.getText() + ", ";
        routeLine.concat(equipment);

        return routeLine;
    }



}

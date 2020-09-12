package controller.main;

import controller.analysis.Filterer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.data.Airport;
import model.data.Storage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class AirportFilterPopUpController implements Initializable {

    @FXML
    private TextField countryField;
    @FXML
    private TextField cityField;
    @FXML
    private CheckBox countryCheckBox;
    @FXML
    private CheckBox cityCheckBox;
    @FXML
    private Label errorText;

    private AirportDataViewController caller;
    private ArrayList<Airport> airports;
    private Storage storage = Main.getStorage();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void display(AirportDataViewController caller) throws IOException {
        final Stage filterPopUp = new Stage();
        this.caller = caller;
        filterPopUp.initModality(Modality.APPLICATION_MODAL);
        Parent root = FXMLLoader.load(getClass().getResource("airportFilterPopUp.fxml")); //open the Upload Data page
        Scene scene = new Scene(root);
        filterPopUp.setScene(scene);
        filterPopUp.show();
    }

    public void applyFilters() {
        errorText.setVisible(false);
        HashMap<String, String> filterTerms = new HashMap();
        if (countryCheckBox.isSelected()) {
            filterTerms.put("Country", countryField.getText());
        }
        if (cityCheckBox.isSelected()) {
            filterTerms.put("Active status", cityField.getText());
        }
        if (!filterTerms.isEmpty()) {
            airports = Filterer.filterAirports(filterTerms, storage.getAirports());
            caller.setAirports(airports);
            /*} catch (RuntimeException e) {
                if (e.getMessage() == null) {
                    errorText.setText("Something went wrong.");
                } else {
                    errorText.setText(e.getMessage());
                }
                errorText.setVisible(true);
            }*/
        } else {
            errorText.setText("Select at least one filter option.");
            errorText.setVisible(true);
        }
    }
}

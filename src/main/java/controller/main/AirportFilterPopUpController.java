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

/**
 * Pop up window in which user can select filters to apply to the list of airports.
 * @author Ella Johnson
 * @version 1.0
 * @since 12/09/2020
 */
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

    /**
     * The class form which the AirportFilterPopUpController was instantiated.
     */
    private AirportDataViewController caller;
    /**
     * An ArrayList of filtered Airports, initialized to the value stored in storage.
     */
    private ArrayList<Airport> airports;
    private Storage storage = Main.getStorage();

    /**
     * Initialize airports to the list of Airports stored in storage.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        airports = (ArrayList<Airport>) storage.getAirports();
    }

    /**
     * Display the filter pop up window until close called.
     * @param caller the class calling this method.
     * @return an ArrayList of airports, which have been filtered if a filter was applied.
     * @throws IOException
     */
    public void display(AirportDataViewController caller) throws IOException {
        final Stage filterPopUp = new Stage();
        this.caller = caller;
        filterPopUp.initModality(Modality.APPLICATION_MODAL);
        Parent root = FXMLLoader.load(getClass().getResource("airportFilterPopUp.fxml")); //open the Upload Data page
        Scene scene = new Scene(root);
        filterPopUp.setScene(scene);
        filterPopUp.show();
    }

    /**
     * Get the filter types and terms from the textfields. Call Filterer.filterAirports with the given filter types and
     * terms in a hashmap, and assign the results of the filter to the airport attribute.
     */
    public void applyFilters() {
        errorText.setVisible(false);
        HashMap<String, String> filterTerms = new HashMap<>();
        if (countryCheckBox.isSelected()) {
            filterTerms.put("Country", countryField.getText());
        }
        if (cityCheckBox.isSelected()) {
            filterTerms.put("Active status", cityField.getText());
        }
        if (!filterTerms.isEmpty()) {
            try {
                airports = Filterer.filterAirports(filterTerms, storage.getAirports());
                // System.out.println(String.format("Results length = %d", airports.size()));
                caller.setAirports(airports);
            //caller.setAirports(airports);
            } catch (RuntimeException e) {
                if (e.getMessage() == null) {
                    errorText.setText("Something went wrong.");
                } else {
                    errorText.setText(e.getMessage());
                }
                errorText.setVisible(true);
            }
        } else {
            errorText.setText("Select at least one filter option.");
            errorText.setVisible(true);
        }
    }
}

package controller.main;

import controller.analysis.Filterer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.data.Airline;
import model.data.Storage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

/**
 * A pop up window in which the user can select filters to apply to the list of Routes.
 * @author Ella Johnson
 * @version 1.0
 * @since 12/09/2020
 */
public class AirlineFilterPopUpController implements Initializable {

    @FXML
    private Button filterButton;
    @FXML
    private CheckBox countryCheckBox;
    @FXML
    private CheckBox activeCheckBox;
    @FXML
    private TextField countryField;
    @FXML
    private ChoiceBox<String> activeSelection;
    @FXML
    private Label errorText;

    /**
     * The filtered airlines. Initialized to the List of Airlines stored in Storage.
     */
    private ArrayList<Airline> airlines;
    private final Storage storage = Main.getStorage();
    /**
     * The two options for the active status of an airline.
     */
    private final ObservableList<String> activeOptions = FXCollections.observableArrayList("True", "False");
    /**
     * The class from which the AirlineFilterPopUpController was instantiated.
     */
    private AirlineDataViewController caller;

    /**
     * Initialize airlines to the List stored in Storage and set activeSelection to give the options "True" or "False".
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        airlines = (ArrayList) storage.getAirlines();
        activeSelection.setItems(activeOptions);
        activeSelection.setValue("True");
    }

    /**
     * Display the filter pop up window.
     * @param caller The class from which this method has been called.
     * @throws IOException
     */
    public void display(AirlineDataViewController caller) throws IOException {
        final Stage filterPopUp = new Stage();
        this.caller = caller;
        filterPopUp.initModality(Modality.APPLICATION_MODAL);
        Parent root = FXMLLoader.load(getClass().getResource("airlineFilterPopUp.fxml")); //open the Upload Data page
        Scene scene = new Scene(root);
        filterPopUp.setScene(scene);
        filterPopUp.show();
    }

    /**
     * Get the filter type and terms that have been applied by the user and pass them into the filter method. Sets the
     * airlines displayed in the caller's tableview to be the result of this filter.
     */
    public void applyFilters() {
        errorText.setVisible(false);
        HashMap<String, String> filterTerms = new HashMap<>();
        if (countryCheckBox.isSelected()) {
            filterTerms.put("Country", countryField.getText());
        }
        if (activeCheckBox.isSelected()) {
            filterTerms.put("Active status", activeSelection.getValue());
        }
        if (!filterTerms.isEmpty()) {
            airlines = Filterer.filterAirlines(filterTerms, storage.getAirlines());
            caller.setAirlines(airlines);
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

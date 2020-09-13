package controller.main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * Pop up scene on which users can apply filters for the route table.
 * @author Ella Johnson
 * @version 1.0
 * @since 12/09/2020
 */
public class RouteFilterPopUpController extends FilterPopUpController {

    @FXML
    private CheckBox airlineCheckBox;
    @FXML
    private CheckBox sourceCheckBox;
    @FXML
    private CheckBox destinationCheckBox;
    @FXML
    private TextField airlineField;
    @FXML
    private TextField sourceField;
    @FXML
    private TextField destinationField;

    /**
     * Display the filter pop up window.
     * @throws IOException if fmxl file cannot be opened.
     */
    @Override
    public void display() throws IOException {
        final Stage filterPopUp = new Stage();
        filterPopUp.initModality(Modality.APPLICATION_MODAL);
        Parent root = FXMLLoader.load(getClass().getResource("routeFilterPopUp.fxml"));
        Scene scene = new Scene(root);
        filterPopUp.setScene(scene);
        filterPopUp.showAndWait();
    }

    /**
     * Initialize the fxml filename.
     * @param url Not used.
     * @param resourceBundle Not used.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    /**
     * Gets the filter terms the user has entered from the text fields and puts them into a HashMap with the filter type
     * as the key and the filter term as the value.
     * @return A HashMap with filter types as keys and filter terms as values.
     */
    public HashMap<String, String> getFilterTerms() {
        HashMap<String, String> filterTerms = new HashMap<>();
        if (airlineCheckBox.isSelected()) {
            filterTerms.put("Airline", airlineField.getText());
        }
        if (sourceCheckBox.isSelected()) {
            filterTerms.put("Source", sourceField.getText());
        }
        if (destinationCheckBox.isSelected()) {
            filterTerms.put("Destination", destinationField.getText());
        }
        return filterTerms;
    }

    /**
     * Call the filterRoutes method of filterer with the given HashMap of filter types and terms.
     * @param filterTerms A HashMap with filter type as the key and filter term as the value.
     */
    @Override
    public void filterByDataType(HashMap<String, String> filterTerms) {
        filterer.filterRoutes(filterTerms, storage.getRoutes());
    }

}

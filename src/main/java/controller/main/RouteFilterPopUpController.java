package controller.main;

import controller.analysis.Filterer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.data.Route;
import model.data.Storage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
    @FXML
    private Label errorText;

    /**
     * Initialize the fxml filename.
     * @param url Not used.
     * @param resourceBundle Not used.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fxmlFilename = "routeFilterPopUp.fxml";
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

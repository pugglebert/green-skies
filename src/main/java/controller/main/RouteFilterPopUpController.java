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
public class RouteFilterPopUpController implements Initializable {

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
     * A list of filtered routes. Initialized to the routes stored in storage.
     */
    private ArrayList<Route> routes;
    private final Storage storage = Main.getStorage();
    /**
     * The class that instatiated the RouteFilterPopUpController.
     */
    private RouteDataViewController caller;

    /**
     * Initalize the routes to the list stored in the Storage class.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        routes = (ArrayList<Route>) storage.getRoutes();
    }

    /**
     * Create and display a stage for the filter pop up.
     * @param caller the class which instantiated the RouteFilterPopUpController
     * @throws IOException
     */
    public void display(RouteDataViewController caller) throws IOException {
        final Stage filterPopUp = new Stage();
        this.caller = caller;
        filterPopUp.initModality(Modality.APPLICATION_MODAL);
        Parent root = FXMLLoader.load(getClass().getResource("routeFilterPopUp.fxml")); //open the Upload Data page
        Scene scene = new Scene(root);
        filterPopUp.setScene(scene);
        filterPopUp.show();
    }

    /**
     * Get the filter terms and types from the user and call Filterer.filterRoutes with a hashmap of these terms and types.
     * Call setRoutes in the RouteDataViewController to display the results of filtering.
     */
    public void applyFilters() {
        errorText.setVisible(false);
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
        if (!filterTerms.isEmpty()) {
            routes = Filterer.filterRoutes(filterTerms, storage.getRoutes());
            caller.setRoutes(routes);
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

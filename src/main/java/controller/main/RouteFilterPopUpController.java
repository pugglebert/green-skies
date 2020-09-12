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
import model.data.Route;
import model.data.Storage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class RouteFilterPopUpController implements Initializable {

    @FXML
    private Button filterButton;
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

    private ArrayList<Route> routes;
    private final Storage storage = Main.getStorage();
    private RouteDataViewController caller;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        routes = (ArrayList<Route>) storage.getRoutes();
    }

    public void display(RouteDataViewController caller) throws IOException {
        final Stage filterPopUp = new Stage();
        this.caller = caller;
        filterPopUp.initModality(Modality.APPLICATION_MODAL);
        Parent root = FXMLLoader.load(getClass().getResource("routeFilterPopUp.fxml")); //open the Upload Data page
        Scene scene = new Scene(root);
        filterPopUp.setScene(scene);
        filterPopUp.show();
    }

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

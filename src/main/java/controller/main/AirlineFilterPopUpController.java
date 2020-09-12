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

    private ArrayList<Airline> airlines;
    private Storage storage = Main.getStorage();
    private final ObservableList<String> activeOptions = FXCollections.observableArrayList("True", "False");
    private AirlineDataViewController caller;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        airlines = (ArrayList) storage.getAirlines();
        activeSelection.setItems(activeOptions);
        activeSelection.setValue("True");
    }

    public void display(AirlineDataViewController caller) throws IOException {
        final Stage filterPopUp = new Stage();
        filterPopUp.initModality(Modality.APPLICATION_MODAL);
        Parent root = FXMLLoader.load(getClass().getResource("airlineFilterPopUp.fxml")); //open the Upload Data page
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
        if (activeCheckBox.isSelected()) {
            filterTerms.put("Active status", activeSelection.getValue());
        }
        if (!filterTerms.isEmpty()) {
            try {
                airlines = Filterer.filterAirlines(filterTerms, storage.getAirlines());
                caller.setAirlines(airlines);
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

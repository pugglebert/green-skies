package controller.main;

import controller.analysis.Filterer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.data.Storage;

import java.io.IOException;
import java.util.HashMap;

/**
 * Parent class of the filter pop up window classes for each datatype.
 * @author Ella Johnson
 * @version 1.0
 * @since 12/09/20202
 */
public abstract class FilterPopUpController implements Initializable {

    @FXML
    private Label errorText;

    protected Filterer filterer = Main.getFilterer();
    protected Storage storage = Main.getStorage();
    protected String fxmlFilename;

    public abstract HashMap<String, String> getFilterTerms();
    public abstract void filterByDataType(HashMap<String, String> filterTerms);

    /**
     * Display the filter pop up window.
     * @throws IOException if fmxl file cannot be opened.
     */
    public void display() throws IOException {
        final Stage filterPopUp = new Stage();
        filterPopUp.initModality(Modality.APPLICATION_MODAL);
        Parent root = FXMLLoader.load(getClass().getResource("airportFilterPopUp.fxml")); //open the Upload Data page
        Scene scene = new Scene(root);
        filterPopUp.setScene(scene);
        filterPopUp.showAndWait();
    }

    /**
     * Call getFilterTerms to get a hashmap of the filter types and terms. Call filterByDataType with the given filter
     * types and terms in a hashmap, and set the filterSuccess attribute of the filterer to true. Display an error message
     * if any step goes wrong.
     */
    public void applyFilters() {
        errorText.setVisible(false);
        HashMap<String, String> filterTerms = getFilterTerms();
        if (!filterTerms.isEmpty()) {
            try {
                filterByDataType(filterTerms);
                filterer.setFilterSuccess(true);
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

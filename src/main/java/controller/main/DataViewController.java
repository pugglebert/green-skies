package controller.main;

import controller.analysis.Filterer;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.data.Storage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * Superclass for all data view pages. All such pages contain a table of data, tools for searching
 * and filtering that data, and a navigation bar which can be used to navigate to other pages.
 * @author Ella Johnson, Hayley Krippner.
 * @since 04/09/20
 * @version 1.0
 */
public abstract class DataViewController extends SideNavBarController {

    @FXML
    protected Button btnUpload;
    @FXML
    protected Button btnRouteDataView;
    @FXML
    protected Button btnAirportDataView;
    @FXML
    protected Button btnAirlineDataView;
    @FXML
    protected ChoiceBox<String> searchTypeSelection;
    @FXML
    protected TextField searchBar;
    @FXML
    protected Button searchButton;
    @FXML
    protected Label errorText;
    @FXML
    protected Button filterButton;
    @FXML
    protected Label filterErrorText;

    protected ObservableList<String> searchTypes;
    protected HashMap<String, ChoiceBox<String>> filterSelectionBoxes = new HashMap<>();
    protected final Storage storage = Main.getStorage();

    protected DataViewController() {
    }

    public abstract void initialize(URL url, ResourceBundle rb);
    public abstract void searchByDataType(String searchTerm, String searchType);
    public abstract void filterByDataType(HashMap<String, String> filterTerms);

    /**
     * Checks users search for errors and displays an error message if any are present. If no errors
     * are present, calls searchByDataType method in subclass.
     */
    public void search() {
        String searchType = searchTypeSelection.getValue();
        String searchTerm = searchBar.getText();
        if (searchType == null) {
            errorText.setText("Select a search type to proceed.");
            errorText.setVisible(true);
        } else if (searchTerm == null) {
            errorText.setText("Enter a search term to proceed.");
            errorText.setVisible(true);
        } else {
            try {
                searchByDataType(searchTerm, searchType);
                errorText.setVisible(false);
            } catch (RuntimeException e) {
                errorText.setText(e.getMessage());
                errorText.setVisible(true);
            }
        }

    }

    /**
     * Gets filter type and term to match from choiceboxes, then calls filterByDataType if no errors occur or displays
     * an error message if there is an error.
     */
    public void applyFilters() {
        HashMap<String, String> filterTerms = new HashMap();
        for (String filterType : filterSelectionBoxes.keySet()) {
            ChoiceBox<String> filterTerm = filterSelectionBoxes.get(filterType);
            if (!filterTerm.getValue().equals("Any")){
                filterTerms.put(filterType, filterTerm.getValue());
            }
        }
        try {
            filterByDataType(filterTerms);
        } catch (RuntimeException e) {
            filterErrorText.setText(e.getMessage());
            filterErrorText.setVisible(true);
        }
    }

    public void filterOptions() {
        //@TODO
    }

}



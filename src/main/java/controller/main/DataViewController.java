package controller.main;

import controller.analysis.Searcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.data.Route;
import model.data.Storage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Superclass for all data view pages. All such pages contain a table of data, tools for searching
 * and filtering that data, and a navigation bar which can be used to navigate to other pages.
 * @author Ella Johnson.
 * @since 04/09/20
 * @version 1.0
 */
public abstract class DataViewController implements Initializable {

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

    protected ObservableList<String> searchTypes;
    protected final Storage storage = Main.getStorage();

    public abstract void initialize(URL url, ResourceBundle rb);
    public abstract void searchByDataType(String searchTerm, String searchType);

    /**
     * This method closes the current page and opens the Upload Data page.
     * @throws IOException
     */
    public void toUploadData() throws IOException {
        Stage stage = (Stage) btnUpload.getScene().getWindow();
        stage.close();
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("upload.fxml")); //open the Upload Data page
        Scene scene = new Scene(root);
        newStage.setScene(scene);
        newStage.show();
    }


    /**
     * This method closes the current page and opens the View Route Data page.
     * @throws IOException
     */
    public void toRouteDataView() throws IOException {
        Stage stage = (Stage) btnRouteDataView.getScene().getWindow();
        stage.close();
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("viewRouteData.fxml")); //open the View Route Data page
        Scene scene = new Scene(root);
        newStage.setScene(scene);
        newStage.show();
    }

    /**
     * This method closes the current page and opens the View Airport Data page.
     * @throws IOException
     */
    public void toAirportDataView() throws IOException {
        Stage stage = (Stage) btnAirportDataView.getScene().getWindow();
        stage.close();
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("viewAirportData.fxml")); //open the View Airport Data page
        Scene scene = new Scene(root);
        newStage.setScene(scene);
        newStage.show();
    }

    /**
     * This method closes the current page and opens the View Airline Data page.
     * @throws IOException
     */
    public void toAirlineDataView() throws IOException {
        Stage stage = (Stage) btnAirlineDataView.getScene().getWindow();
        stage.close();
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("viewAirlineData.fxml")); //open the View Airline Data page
        Scene scene = new Scene(root);
        newStage.setScene(scene);
        newStage.show();
    }

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

}



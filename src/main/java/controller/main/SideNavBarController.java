package controller.main;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.data.Storage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Superclass for all data view pages. All such pages contain a table of data, tools for searching
 * and filtering that data, and a navigation bar which can be used to navigate to other pages.
 * @author Ella Johnson, Hayley Krippner, Enyang Zhang, ZhengJingRui He.
 * @since 04/09/20
 * @version 1.0
 */
public abstract class SideNavBarController implements Initializable {

    @FXML
    protected Button btnUpload;
    @FXML
    protected Button btnRouteDataView;
    @FXML
    protected Button btnAirportDataView;
    @FXML
    protected Button btnAirlineDataView;
    @FXML
    protected Button btnFlightHistory;
    @FXML
    protected Button btnCarbonEmissionsReport;
    @FXML
    protected Button btnMapOfAirports;
    @FXML
    protected Button btnGraphs;
    @FXML
    protected Button btnAnalyseFlight;

    protected ObservableList<String> searchTypes;
    protected final Storage storage = Main.getStorage();

    public abstract void initialize(URL url, ResourceBundle rb);

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
        newStage.setMaximized(true);
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
        newStage.setMaximized(true);
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
        newStage.setMaximized(true);
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
        newStage.setMaximized(true);
        newStage.show();
    }

    /**
     * This method closes the current page and opens the Flight History page.
     * @throws IOException
     */
    public void toFlightHistory() throws IOException {
        Stage stage = (Stage) btnFlightHistory.getScene().getWindow();
        stage.close();
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("flightHistory.fxml")); //open the View Airline Data page
        Scene scene = new Scene(root);
        newStage.setScene(scene);
        newStage.setMaximized(true);
        newStage.show();
    }

    /**
     * This method closes the current page and opens the Carbon Emissions Report page.
     * @throws IOException
     */
    public void toCarbonEmissionsReport() throws IOException {
        Stage stage = (Stage) btnCarbonEmissionsReport.getScene().getWindow();
        stage.close();
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("carbonEmissionsReport.fxml")); //open the View Airline Data page
        Scene scene = new Scene(root);
        newStage.setScene(scene);
        newStage.setMaximized(true);
        newStage.show();
    }

    /**
     * This method closes the current page and opens the Map of Airports page.
     * @throws IOException
     */
    public void toMapOfAirports() throws IOException {
        Stage stage = (Stage) btnMapOfAirports.getScene().getWindow();
        stage.close();
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("mapOfAirports.fxml")); //open the View Airline Data page
        Scene scene = new Scene(root);
        newStage.setScene(scene);
        newStage.setMaximized(true);
        newStage.show();
    }

    /**
     * This method closes the current page and opens the Graphs page.
     * @throws IOException
     */
    public void toGraphs() throws IOException {
        Stage stage = (Stage) btnGraphs.getScene().getWindow();
        stage.close();
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("graphs.fxml")); //open the View Airline Data page
        Scene scene = new Scene(root);
        newStage.setScene(scene);
        newStage.setMaximized(true);
        newStage.show();
    }

    /**
     * Opens analyse screen
     * @throws IOException
     */
    public void toAnalyseFlight() throws IOException {
        Stage stage = (Stage)btnAnalyseFlight.getScene().getWindow();
        stage.close();
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("analyse.fxml"));
        Scene scene = new Scene(root);
        newStage.setScene(scene);
        newStage.setMaximized(true);
        newStage.show();
    }

}




package controller.main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.data.DataType;
import model.data.Route;
import model.data.Storage;
import model.loader.Loader;
import java.io.File;
import java.io.IOException;

/**
 * The controller class which contains the controls for the upload data view.
 * @author Grace Hanlon, Hayley Krippner
 * @version 1.0
 * @since 2020-08-26
 */
public class UploadController {

    //Initialize storage space for selected file.
    private final Storage storage = Main.getStorage();
    private final Loader loader = Main.getLoader();

    ObservableList<String> dataTypeList = FXCollections.observableArrayList("Airport", "Route", "Airline");

    @FXML
    private ChoiceBox dataTypeSelect;
    @FXML
    private ListView fileView;
    @FXML
    private Text fileErrorText;
    @FXML
    private Button nextButton;
    @FXML
    private Button backButton;
    @FXML
    private Button btnUpload;
    @FXML
    private Button btnRouteDataView;
    @FXML
    private Button btnAirportDataView;
    @FXML
    private Button btnAirlineDataView;
    @FXML
    private Button btnFlightHistory;

    /**
     * This method adds the data types from dataTypeList to the dataTypeSelect list.
     */
    public void initialize(){
        dataTypeSelect.getItems().addAll(dataTypeList);
    }

    /**
     * This method opens the user's file browser when the 'browse' button is clicked.
     */
    public void browseFiles() {

        FileChooser fileChooser = new FileChooser(); //opens a file local file browser
        File selectedFile = fileChooser.showOpenDialog(null);

        String fileType = dataTypeSelect.getValue().toString();
        String stringFile = selectedFile.toString();

        if (selectedFile != null){  // check a file has been selected
            try{
            String resultString = loader.loadFile(stringFile, fileType);
            fileErrorText.setText(resultString);
            fileErrorText.setVisible(true);
            for (DataType line: storage.getRoutes()){
                Route test = (Route) line;
                System.out.println(test.getAirlineID());
                }
            }
            catch (Exception e){
                //@TODO Display error message
            }
            fileView.getItems().add(selectedFile.getName());
            nextButton.setVisible(true); //'Next' button pops up if a valid file has been selected
        } else{
            fileErrorText.setVisible(true);
        }
    }

    /**
     * This method closes the Upload Data page and opens the Welcome page.
     * @throws IOException
     */
    public void backToWelcome() throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("welcome.fxml")); //open the Welcome page
        Scene scene = new Scene(root);
        newStage.setScene(scene);
        newStage.show();
    }

    /**
     * This method closes the Upload Data page and opens the Upload Data page.
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
     * This method closes the Upload Data page and opens the View Route Data page.
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
     * This method closes the Upload Data page and opens the View Airport Data page.
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
     * This method closes the Upload Data page and opens the View Airline Data page.
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
     * This method closes the Upload Data page and opens the View Airline Data page.
     * @throws IOException
     */
    public void toFlightHistory() throws IOException {
        Stage stage = (Stage) btnFlightHistory.getScene().getWindow();
        stage.close();
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("flightHistory.fxml")); //open the View Airline Data page
        Scene scene = new Scene(root);
        newStage.setScene(scene);
        newStage.show();
    }
}

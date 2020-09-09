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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystemException;
import java.util.ResourceBundle;

/**
 * The controller class which contains the controls for the upload data view.
 * @author Grace Hanlon, Hayley Krippner
 * @version 1.0
 * @since 2020-08-26
 */
public class UploadController extends SideNavBarController {

    //Initialize storage space for selected file.
    private final Storage storage = Main.getStorage();
    private final Loader loader = Main.getLoader();

    //Iniitialize the list of poosible data types to be added to the ChoiceBox 'dataTypeSelect'
    ObservableList<String> dataTypeList = FXCollections.observableArrayList("Airport", "Route", "Airline");

    @FXML
    private ChoiceBox dataTypeSelect;
    @FXML
    private ListView fileView;
    @FXML
    private Text fileAcceptedText;
    @FXML
    private Button nextButton;
    @FXML
    private Button backButton;

    /**
     * This method adds the data types from dataTypeList to the dataTypeSelect list.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){
        dataTypeSelect.getItems().addAll(dataTypeList);
    }

    /**
     * This method opens the user's file browser when the 'browse' button is clicked.
     */
    public void browseFiles() throws FileNotFoundException, FileSystemException {

        FileChooser fileChooser = new FileChooser(); //opens a file local file browser
        File selectedFile = fileChooser.showOpenDialog(null);

        String fileType = dataTypeSelect.getValue().toString();
        String stringFile = selectedFile.toString();

        Alert a = new Alert(Alert.AlertType.NONE);

        try{
            //try loadFile returns a String (fileAccceptedText) when the file is accepted, with the number of rejected lines
            String resultString = loader.loadFile(stringFile, fileType);
            fileAcceptedText.setText(resultString);
            fileAcceptedText.setVisible(true);
            fileView.getItems().add(selectedFile.getName());
            nextButton.setVisible(true); //'Next' button pops up if a valid file has been selected

        for (DataType line: storage.getRoutes()){
            Route test = (Route) line;
            System.out.println(test.getAirlineID());
            }
        }
        //catches errors in uploading file and alerts user by displaying the error message in an error box
        catch (Exception e){
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText(e.getMessage());
            a.show();
        }
//
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
    
}

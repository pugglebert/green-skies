package controller.main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.data.DataType;
import model.data.Route;
import model.data.Storage;
import model.loader.Loader;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
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
    public void browseFiles() {

        if((dataTypeSelect.getValue()) == null){
            Alert ErrorAlert = new Alert(Alert.AlertType.NONE);
            ErrorAlert.setAlertType(Alert.AlertType.ERROR);
            ErrorAlert.setContentText("You must select the type of data that you are going to add");
            ErrorAlert.show();

        } else {

          FileChooser fileChooser = new FileChooser(); // opens a file local file browser
          File selectedFile = fileChooser.showOpenDialog(null);

          String fileType = dataTypeSelect.getValue().toString();
          String stringFile = selectedFile.toString();

          try {
            // loadFile returns a String when the file is accepted, with the number of reject lines, this string
            // pops up in the ConfirmALert message

            String resultString = loader.checkFile(stringFile, fileType);
            Alert ConfirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            ConfirmAlert.setContentText(resultString);
            ConfirmAlert.setHeaderText("Confirm file upload");
            ButtonType yesButton = new ButtonType("Yes");
            ButtonType cancelButton = new ButtonType("Cancel");
            ConfirmAlert.getButtonTypes().setAll(yesButton, cancelButton);

            Optional<ButtonType> result = ConfirmAlert.showAndWait();
            if (result.get() == yesButton) {
                loader.loadFile(stringFile, fileType);
                fileView.getItems().add(selectedFile.getName());
                ConfirmAlert.close();

            //if user wishes to cancel the file chosne to upload they push cancel and no data is uploaded
            } else if (result.get() == cancelButton) {
                ConfirmAlert.close();
            }
          }
          // catches errors in uploading file and alerts user by displaying the error message in an
          // error box
          catch (Exception e) {
            Alert ErrorAlert = new Alert(Alert.AlertType.NONE);
            ErrorAlert.setAlertType(Alert.AlertType.ERROR);
            ErrorAlert.setContentText(e.getMessage());
            ErrorAlert.show();
          }

          for (DataType line : storage.getRoutes()) {
            Route test = (Route) line;
            System.out.println(test.getAirlineID());
          }
            }
//
    }

    public void addSingle() {

        if((dataTypeSelect.getValue()) == null){
            Alert ErrorAlert = new Alert(Alert.AlertType.NONE);
            ErrorAlert.setAlertType(Alert.AlertType.ERROR);
            ErrorAlert.setContentText("You must select the type of data that you are going to add");
            ErrorAlert.show();

        } else {



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
        newStage.setMaximized(true);
        newStage.show();
    }

}

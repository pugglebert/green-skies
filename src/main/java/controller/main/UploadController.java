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

// TODO: check all method comments start with "This method ..."

/**
 * The controller class which contains the controls for the upload data view.
 *
 * @author Grace Hanlon, Lambert
 * @version 1.1
 * @since 2020-09-18
 */
public class UploadController extends SideNavBarController {

  private final Storage storage = Main.getStorage();
  private final Loader loader = Main.getLoader();
  @FXML public ListView fileView;
  // Iniitialize the list of poosible data types to be added to the ChoiceBox 'dataTypeSelect'
  ObservableList<String> dataTypeList =
      FXCollections.observableArrayList("Airport", "Route", "Airline");
  @FXML private ChoiceBox dataTypeSelect;
  @FXML private Button backButton;

  /**
   * This method adds the data types from dataTypeList to the dataTypeSelect list. Must have
   * implementation of this method as superclass implements the Initializable interface.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    dataTypeSelect.getItems().addAll(dataTypeList);
  }

  /**
   * This method returns a boolan value based on whether the user has selected a data type or not
   */
  public boolean checkDataTypeSelected() {

    if ((dataTypeSelect.getValue()) == null) {
      Alert ErrorAlert = new Alert(Alert.AlertType.NONE);
      ErrorAlert.setAlertType(Alert.AlertType.ERROR);
      ErrorAlert.setContentText("You must select the type of data that you are going to add");
      ErrorAlert.show();
      return false;
    } else {
      return true;
    }
  }

  /** This method opens the user's file browser when the 'browse' button is clicked. */
  public void browseFiles() {

    if (checkDataTypeSelected()) {

      FileChooser fileChooser = new FileChooser(); // opens a file local file browser
      File selectedFile = fileChooser.showOpenDialog(null);

      try {
        String fileType = dataTypeSelect.getValue().toString();
        String stringFile;
        stringFile = selectedFile.toString();
        // loadFile returns a String when the file is accepted, with the number of reject lines,
        // this string
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
          switch (fileType) {
            case "Airport":
              storage.resetAirportsList();
              break;
            case "Airline":
              storage.resetAirlinesList();
              break;
            case "Route":
              storage.resetRoutesList();
              break;
          }
          loader.loadFile(stringFile, fileType);
          fileView.getItems().add(selectedFile.getName());
          ConfirmAlert.close();

          // only load data if it is not erroneous
          for (DataType line : storage.getRoutes()) {
            Route test = (Route) line;
          }
          // if user wishes to cancel the file chosne to upload they push cancel and no data is
          // uploaded
        } else if (result.get() == cancelButton) {
          ConfirmAlert.close();
        }
      }
      // catches errors in uploading file and alerts user by displaying the error message in an
      // error box
      catch (Exception e) {
        if (e.getMessage() != null) {
          Alert ErrorAlert = new Alert(Alert.AlertType.NONE);
          ErrorAlert.setAlertType(Alert.AlertType.ERROR);
          ErrorAlert.setContentText(e.getMessage());
          ErrorAlert.show();
        }
      }
    }
    //
  }

  /**
   * This method opens a screen for manual data entry of a single item when the 'Add single entry'
   * button is clicked.
   *
   * @throws IOException This throws an IOException.
   */
  public void addSingle() throws IOException {

    // Checks to see that the user has chosen a data type
    if (checkDataTypeSelected()) {
      String fileType = dataTypeSelect.getValue().toString();
      // checks which data type was chosen by the user so the correct single data entry window is
      // opened
      switch (fileType) {
        case "Airport":
          {
            Stage newStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("../airportSingleEntry.fxml"));
            Scene scene = new Scene(root);
            newStage.setScene(scene);
            newStage.show();
            break;
          }
        case "Airline":
          {
            Stage newStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("../airlineSingleEntry.fxml"));
            Scene scene = new Scene(root);
            newStage.setScene(scene);
            newStage.show();
            break;
          }
        case "Route":
          {
            Stage newStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("../routeSingleEntry.fxml"));
            Scene scene = new Scene(root);
            newStage.setScene(scene);
            newStage.show();
            break;
          }
      }
    }
  }

  /**
   * This method closes the Upload Data page and opens the Welcome page.
   *
   * @throws IOException This throws an IOException.
   */
  public void backToWelcome() throws IOException {
    Stage stage = (Stage) backButton.getScene().getWindow();
    stage.close();
    Stage newStage = new Stage();
    Parent root =
        FXMLLoader.load(getClass().getResource("welcome.fxml")); // open the Welcome page
    Scene scene = new Scene(root);
    newStage.setScene(scene);
    newStage.setMaximized(true);
    newStage.show();
  }
}

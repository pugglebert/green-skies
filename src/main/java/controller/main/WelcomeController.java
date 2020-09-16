package controller.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;
// TODO: check all method comments start with "This method ..."

/**
 * The Controller class which contains the controls for the Welcome page, the first page shown to
 * users.
 *
 * @author Grace Hanlon, Hayley Krippner
 * @version 1.0
 * @since 2020-08-26
 */
public class WelcomeController {
  // TODO: write comments for these attributes

  public Button continueButton;
  public Button InfoButton;
  // TODO: write comment for this method

  // Method to handle the event for continuing to the main screen of the application.
  public void continueToMainScreen(ActionEvent event) throws IOException {
    Stage stage = (Stage) continueButton.getScene().getWindow();
    stage.close();
    Stage newStage = new Stage();
    Parent root =
        FXMLLoader.load(getClass().getResource("upload.fxml")); // open the Upload Data page
    Scene scene = new Scene(root);
    newStage.setScene(scene);
    newStage.setMaximized(true);
    newStage.show();
  }
  // TODO: write comment for this method

  // Method to handle the event for continuing to the info screen of the application.
  public void toInfoScreen(ActionEvent event) throws IOException {
    Stage stage = (Stage) continueButton.getScene().getWindow();
    stage.close();
    Stage newStage = new Stage();
    Parent root = FXMLLoader.load(getClass().getResource("help.fxml"));
    Scene scene = new Scene(root);
    newStage.setScene(scene);
    newStage.setMaximized(true);
    newStage.show();
  }
}

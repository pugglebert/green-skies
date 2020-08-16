package controller.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class WelcomeController {

    //Method to handle the event for continuing to the main screen of the application.
    public void continueToMainScreen() throws IOException {
        Stage stage = new Stage(); // create a stage for scene
        Parent root = FXMLLoader.load(getClass().getResource("upload.fxml")); // load upload.fxml
        Scene scene = new Scene(root, 1024, 640);  // apply stuff wanna show to scene
        stage.setResizable(false);
        stage.setScene(scene);  // set up scene
        stage.show(); // time for performing
        }


    //Method to handle the event for continuing to the info screen of the application.
    public void toInfoScreen(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("help.fxml")); // load help.fxml
        Scene scene = new Scene(root, 1024, 640);  // apply stuff wanna show to scene
        stage.setResizable(false);
        stage.setScene(scene);  // set up scene
        stage.show(); // time for performing

    }
}

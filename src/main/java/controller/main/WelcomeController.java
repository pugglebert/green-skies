package controller.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * TODO: write method comment
 */
public class WelcomeController {

    //Method to handle the event for continuing to the main screen of the application.
    public void continueToMainScreen(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("upload.fxml")); // load upload.fxml
        Scene scene = new Scene(root, 1024, 640);  // apply stuff wanna show to scene
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);  // set up scene
        window.show(); // time for performing

    }

    //Method to handle the event for continuing to the info screen of the application.
    public void toInfoScreen(ActionEvent event) throws IOException {
        // TODO change to "help.fxml" this is debug
        Parent root = FXMLLoader.load(getClass().getResource("help.fxml")); // load help.fxml
        Scene scene = new Scene(root, 1024, 640);  // apply stuff wanna show to scene
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);  // set up scene
        window.show(); // time for performing

    }

}

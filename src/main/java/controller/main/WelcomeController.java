package controller.main;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class WelcomeController {

    //Method to handle the event for continuing to the main screen of the application.
    public void continueToMainScreen() {

    }

    //Method to handle the event for continuing to the info screen of the application.
    public void toInfoScreen() throws IOException {
        Stage stage = new Stage(); // create a stage for scene

        Parent root = FXMLLoader.load(getClass().getResource("help.fxml")); // load help.fxml

        Scene scene = new Scene(root);  // apply stuff wanna show to scene

        stage.setScene(scene);  // set up scene

        stage.show(); // time for performing

    }
}

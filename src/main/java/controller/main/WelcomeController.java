package controller.main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * The Controller class which contains the controls for the Welcome page, the first page shown to users.
 * @author Grace Hanlon, Hayley Krippner
 * @version 1.0
 * @since 2020-08-26
 */
public class WelcomeController {

    public Button continueButton;
    public Button InfoButton;

    //Method to handle the event for continuing to the main screen of the application.
    public void continueToMainScreen(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("upload.fxml")); // load upload.fxml
        Scene scene = new Scene(root/*, 1024, 640*/);  // apply stuff wanna show to scene
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);  // set up scene
        window.setMaximized(true);
        window.show(); // time for performing

    }

    //Method to handle the event for continuing to the info screen of the application.
    public void toInfoScreen(ActionEvent event) throws IOException {
        // TODO change to "help.fxml" this is debug
        Parent root1 = FXMLLoader.load(getClass().getResource("help.fxml")); // load help.fxml
        Scene scene = new Scene(root1/*, 1024, 640*/);  // apply stuff wanna show to scene
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);  // set up scene
        window.setMaximized(true);
        window.show(); // time for performing

    }



}

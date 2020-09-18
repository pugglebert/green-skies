package controller.main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
// TODO: check all method comments start with "This method ..."

/**
 * The controller class which contains the controls for the help page on the welcome page.
 *
 * @author ZhengJingRui He.
 * @version 1.0
 * @since 04/09/20
 */
public class HelpController {

    @FXML
    public Button BackButton;

    /**
     * Method in order to return back to the welcome page
     *
     * @throws IOException
     */

    public void BackToPreviousWindow() throws IOException {
        Stage stage = (Stage) BackButton.getScene().getWindow(); // get current window
        stage.close(); // close current window
        Stage stage1 = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("welcome.fxml"));
        Scene scene = new Scene(root);
        stage1.setScene(scene);
        stage1.setMaximized(true);
        stage1.show();
    }
}

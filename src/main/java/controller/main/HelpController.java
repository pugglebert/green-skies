package controller.main;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HelpController {

    @FXML
    public Button BackButton;

    public void BackToPreviousWindow()
    {
        Stage stage = (Stage) BackButton.getScene().getWindow();   //get current window
        stage.close();  // close current window
    }
}

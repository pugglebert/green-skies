package controller.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class HelpController {

    @FXML
    public Button BackButton;

    public void BackToPreviousWindow() throws IOException
    {
        Stage stage = (Stage) BackButton.getScene().getWindow();   //get current window
        stage.close();  // close current window
        Stage stage1 = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("welcome.fxml"));
        Scene scene = new Scene(root);
        stage1.setScene(scene);
        stage1.show();








    }
}

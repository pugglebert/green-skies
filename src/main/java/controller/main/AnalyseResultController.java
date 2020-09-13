package controller.main;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import controller.main.AnalyseController;

public class AnalyseResultController {

    @FXML
    private Button BackButton;

    @FXML
    public TextField DistanceRoute1;

    @FXML
    public TextField DistanceRoute2;

    @FXML
    public TextField EmissionRoute1;

    @FXML
    public TextField EmissionRoute2;


    @FXML
    private void  GoBackToAnalyse(){
        Stage stage = (Stage) BackButton.getScene().getWindow();   //get current window
        stage.close();  // close current window
    }


}

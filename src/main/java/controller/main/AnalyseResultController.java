package controller.main;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.data.Storage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * The controller class which contains the controls for the AnalyseResult screen, this screen shows the user
 * the calcuted results for distance and emissions of two routes entered into the analyse screen
 * @version 1.0
 * @since 15/09/20
 */

public class AnalyseResultController implements Initializable {

    @FXML
    private Button BackButton;

    @FXML
    public Text DistanceRoute1;

    @FXML
    public Text DistanceRoute2;

    @FXML
    public Text EmissionRoute1;

    @FXML
    public Text EmissionRoute2;

    /**
     * Must have implementation of this method as superclass implements the Initializable interface.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Storage storage = Main.getStorage();
        String DistanceRoute1Text = String.valueOf(storage.getAnalyseDistanceResult().get(0));
        DistanceRoute1.setText(DistanceRoute1Text);
        String DistanceRoute2Text = String.valueOf(storage.getAnalyseDistanceResult().get(1));
        DistanceRoute2.setText(DistanceRoute1Text);
        String EmissionRoute1Text = String.valueOf(storage.getAnalyseEmissionResult().get(0));
        EmissionRoute1.setText(EmissionRoute1Text);
        String EmissionRoute2Text = String.valueOf(storage.getAnalyseEmissionResult().get(1));
        EmissionRoute2.setText(EmissionRoute2Text);

    }


    public void GoBackToAnalyse() {
        Stage stage = (Stage) BackButton.getScene().getWindow();   //get current window
        stage.close();  // close current window
    }
}
    

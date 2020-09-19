package controller.main;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.data.Storage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * The controller class which contains the controls for the analyseResult page.
 *
 * @author ZhengJingRui He
 * @version 1.0
 * @since 13/09/20
 */
public class AnalyseResultController implements Initializable {

  @FXML private Button BackButton;
  @FXML public Text DistanceRoute1;
  @FXML public Text DistanceRoute2;
  @FXML public Text EmissionRoute1;
  @FXML public Text EmissionRoute2;
  @FXML public Text DistanceDifference;
  @FXML public Text EmissionDifference;

  /**
   * This method is required for Initializable interface show distance and emissions of each route
   *
   * @param url The provided url.
   * @param resourceBundle The provided resoure bundle.
   */
  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    Storage storage = Main.getStorage();
    String DistanceRoute1Text = String.valueOf(storage.getAnalyseDistanceResult().get(0));

    DistanceRoute1.setText(DistanceRoute1Text);
    String DistanceRoute2Text = String.valueOf(storage.getAnalyseDistanceResult().get(1));

    DistanceRoute2.setText(DistanceRoute2Text);
    String EmissionRoute1Text = String.valueOf(storage.getAnalyseEmissionResult().get(0));

    EmissionRoute1.setText(EmissionRoute1Text);
    String EmissionRoute2Text = String.valueOf(storage.getAnalyseEmissionResult().get(1));

    EmissionRoute2.setText(EmissionRoute2Text);
    String DistanceDifferenceText =
        String.valueOf(
            Math.abs(
                storage.getAnalyseDistanceResult().get(0)
                    - storage.getAnalyseDistanceResult().get(1)));
    DistanceDifference.setText(DistanceDifferenceText);
    String EmissionDifferenceText =
        String.valueOf(
            Math.abs(
                storage.getAnalyseEmissionResult().get(0)
                    - storage.getAnalyseEmissionResult().get(1)));
    EmissionDifference.setText(EmissionDifferenceText);
  }

  /**
   * This method is to return back to the AnalysePage.
   *
   * @throws IOException
   */
  public void GoBackToAnalyse() throws IOException {

    Stage stage = (Stage) BackButton.getScene().getWindow();
    stage.close();
  }
}

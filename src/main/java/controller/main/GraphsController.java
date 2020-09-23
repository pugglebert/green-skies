package controller.main;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * The controller class which contains the controls for the graphs page.
 *
 * @author Hayley Krippner
 * @version 1.0
 * @since 04/09/20
 */
public class GraphsController extends SideNavBarController {

  /**
   * This method is the initializer for this class.
   *
   * @param url The provided resoure bundle.
   * @param resourceBundle The resoure bundle.
   */
  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {}

  public void toBarChart() throws IOException {
    Stage newStage = new Stage();
    Parent root = FXMLLoader.load(getClass().getResource("barChart.fxml"));
    Scene scene = new Scene(root);
    newStage.setScene(scene);
    newStage.show();
  }

}

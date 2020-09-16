package controller.main;

import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import java.net.URL;
import java.util.ResourceBundle;
// TODO: check all method comments start with "This method ..."

/**
 * The controller class which contains the controls for the map of airports page.
 *
 * @author Lambert
 * @version 1.2
 * @since 14/09/20
 */
public class MapOfAirportsController extends SideNavBarController {

  @FXML private WebView mapView;
  @FXML private WebEngine mapEngine;
  // TODO: write comment for this method

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    mapEngine = mapView.getEngine();
    mapEngine.load(
        MapOfAirportsController.class
            .getResource("/controller/main/googleMap.html")
            .toExternalForm());
  }
}

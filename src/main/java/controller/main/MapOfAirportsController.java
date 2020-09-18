package controller.main;

import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import java.net.URL;
import java.util.ResourceBundle;


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


  /**
   * This method initializes the controller class.
   * @param url
   * @param resourceBundle
   */
  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    mapEngine = mapView.getEngine();
    mapEngine.load(
        MapOfAirportsController.class
            .getResource("/controller/main/googleMap.html")
            .toExternalForm());
  }
}

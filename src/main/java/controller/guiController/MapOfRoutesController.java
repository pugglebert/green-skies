package controller.guiController;

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
public class MapOfRoutesController extends SideNavBarController {

  @FXML private WebView mapView;
  @FXML private WebEngine mapEngine;

  /**
   * This method initializes the controller class.
   *
   * @param url The provided url.
   * @param resourceBundle The proided resource bundle.
   */
  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    mapEngine = mapView.getEngine();
    mapEngine.load(
        MapOfRoutesController.class.getResource("/view/googleMap.html").toExternalForm());
  }
}

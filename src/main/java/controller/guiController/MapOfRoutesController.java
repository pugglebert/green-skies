package controller.guiController;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import model.data.Airport;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
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
  @FXML protected Label errorText;

  /** The airport that user selected to display in google map. */
  private HashMap<Integer, ArrayList<Airport>> mapAirport = new HashMap<>();

  /**
   * This method initializes the controller class.
   *
   * @param url The provided url.
   * @param resourceBundle The proided resource bundle.
   */
  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    iniMap();
    mapAirport = Main.getStorage().getMapAirport();
  }

  /**
   * This method initilises the google map.
   */
  public void iniMap() {
    mapEngine = mapView.getEngine();
    mapEngine.load(MapOfRoutesController.class.getResource("/view/googleMap.html").toExternalForm());
  }

  /**
   * This method calls script to display the route on map.
   */
  public void displayRoute() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("[");
    mapAirport.get(0).forEach(
          pos ->
              stringBuilder.append(
                  String.format("{lat: %f, lng: %f}, ", pos.getLatitude(), pos.getLongitude())));
      stringBuilder.append("]");
      String scriptToExecute = "displayRoute(" + stringBuilder.toString() + ");";
      if (mapEngine.isJavaScriptEnabled()) {
        mapEngine.executeScript(scriptToExecute);
      } else {
        errorText.setText("Please wait until google map loaded.");
        errorText.setVisible(true);
      }


    }


  public void showMap() {
    displayRoute();
  }
}

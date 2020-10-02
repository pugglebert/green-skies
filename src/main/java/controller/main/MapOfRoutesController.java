package controller.main;

import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import model.data.Airport;

import java.net.URL;
import java.util.ArrayList;
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
  private ArrayList<Airport> mapAirport = new ArrayList<>();
  /**
   * This method initializes the controller class.
   *
   * @param url The provided url.
   * @param resourceBundle The proided resource bundle.
   */
  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    iniMap();
    mapAirport = Main.getStorage().MapAirport;
  }

  public void iniMap() {
    mapEngine = mapView.getEngine();
    mapEngine.load(MapOfRoutesController.class.getResource("/view/googleMap.html").toExternalForm());
  }

  public void displayRoute() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("[");
    mapAirport.forEach(pos -> stringBuilder.append(
            String.format("{lat: %f, lng: %f}, ",pos.getLatitude(), pos.getLongitude())));
    stringBuilder.append("]");
    String scriptToExecute = "displayRoute(" + stringBuilder.toString() + ");";
    System.out.println(scriptToExecute);
    mapEngine.executeScript(scriptToExecute);

  }
  //

  public void showMap() {
    displayRoute();
  }
}

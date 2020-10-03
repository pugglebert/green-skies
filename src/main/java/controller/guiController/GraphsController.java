package controller.guiController;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.text.Text;
import model.data.Route;

import java.net.URL;
import java.util.*;

/**
 * The controller class which contains the controls for the graphs page.
 *
 * @author Grace Hanlon
 * @version 1.0
 * @since 04/09/20
 */
public class GraphsController extends SideNavBarController {

  @FXML
  NumberAxis yAxis;
  @FXML
  CategoryAxis xAxis;
  @FXML
  BarChart<String, Number> barChart;
  @FXML
  Text warningText;

  List<Route> routes;
  private double NaN;


  /**
   * This method is the initializer for this class. Displays graph of routes from history
   * @param url The provided resoure bundle.
   * @param resourceBundle The resoure bundle.
   */
  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    /**
     * Initialise the series to which route details will be added and then added from here to the
     * graph
     */
    XYChart.Series<String, Number> data = new XYChart.Series();
    routes = storage.getHistory();
    /**Check if flights exist in the users history*/
    if (routes.size() == 0) {
      warningText.setVisible(true);
    } else {
      /**If there are less than 10 fights in history, no need to sort them*/
      if (routes.size() <= 10) {
        for (Route route : routes) {
          String axisString = createAxisString(route);
          double emissions = route.getEmissions();
          data.getData().add(new XYChart.Data(axisString, emissions));
        }
      } else {
        /**If there are more than 10 flights in history, they need to be sorted*/
        ArrayList<Route> sortedByEmissions = new ArrayList<Route>(routes);
        Collections.sort(sortedByEmissions, new Comparator<Route>() {
          @Override
          public int compare(Route o1, Route o2) {
            return Double.valueOf(o2.getEmissions()).compareTo(o1.getEmissions());
          }
        });

        int i = 0;
        int limit = 10;
        while (i < limit) {
          if (!Double.isNaN(sortedByEmissions.get(i).getEmissions())) {
            String axisString = createAxisString(sortedByEmissions.get(i));
            double emissions = sortedByEmissions.get(i).getEmissions();
            data.getData().add(new XYChart.Data(axisString, emissions));
            i += 1;
          } else {
            limit += 1;
            i += 1;
          }
        }
      }
      barChart.getData().add(data);
    }
  }

  /** This method creates the string to be placed on the x axis for each route*/
  public String createAxisString(Route route) {
    String dest = route.getDestinationAirport();
    String src = route.getSourceAirport();
    double distance = route.getDistance();
    String axisString = String.format("%s - %s\n(%.0f km)", src, dest, distance);
    return axisString;
  }

}
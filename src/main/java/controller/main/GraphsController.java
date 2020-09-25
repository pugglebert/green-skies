package controller.main;

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
   * This method is the initializer for this class.
   *
   * @param url The provided resoure bundle.
   * @param resourceBundle The resoure bundle.
   */
  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {

    XYChart.Series<String, Number> data = new XYChart.Series();

    routes = storage.getHistory();

    if (routes.size() == 0) {
      warningText.setVisible(true);
    } else {

      if (routes.size() <= 10) {
        for (Route route : routes) {
          String dest = route.getDestinationAirport();
          String src = route.getSourceAirport();
          String axisString = String.format("%s - %s", src, dest);
          double emissions = route.getEmissions();
          data.getData().add(new XYChart.Data(axisString, emissions));
        }
      } else {
        ArrayList<Route> sortedByEmissions = new ArrayList<Route>(routes);
        Collections.sort(
            sortedByEmissions,
            new Comparator<Route>() {
              @Override
              public int compare(Route o1, Route o2) {
                return Double.valueOf(o2.getEmissions()).compareTo(o1.getEmissions());
              }
            });
        int i = 0;
        int limit = 10;
        while(i<limit) {
          if (sortedByEmissions.get(i).getEmissions() != NaN) { //issue, graph wont display if there is NaN for emissions value
            String dest = sortedByEmissions.get(i).getDestinationAirport();
            String src = sortedByEmissions.get(i).getSourceAirport();
            String axisString = String.format("%s - %s", src, dest);
            double emissions = sortedByEmissions.get(i).getEmissions();
            data.getData().add(new XYChart.Data(axisString, emissions));
            i += 1;
            System.out.println(emissions);
          } else {
            limit += 1;
            i += 1;
          }
        }


      }

      barChart.getData().add(data);
    }
    }




}

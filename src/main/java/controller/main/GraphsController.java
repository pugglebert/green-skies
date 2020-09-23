package controller.main;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

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


  @FXML
  NumberAxis yAxis;
  @FXML
  CategoryAxis xAxis;
  @FXML
  BarChart<String, Number> barChart;

  /**
   * This method is the initializer for this class.
   *
   * @param url The provided resoure bundle.
   * @param resourceBundle The resoure bundle.
   */
  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    XYChart.Series<String, Number> data = new XYChart.Series();

    //provide data EXAMPLES
    data.getData().add(new XYChart.Data("Route A", 100));
    data.getData().add(new XYChart.Data("Route b", 111));
    data.getData().add(new XYChart.Data("Route c", 50));

    barChart.getData().add(data);
  }

//  public void toBarChart() throws IOException {
//    Stage newStage = new Stage();
//    Parent root = FXMLLoader.load(getClass().getResource("barChart.fxml"));
//    Scene scene = new Scene(root);
//    newStage.setScene(scene);
//    newStage.show();
//  }


}

package controller.main;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class BarChartController implements Initializable {

    @FXML
    BorderPane borderPane;
    @FXML
    NumberAxis yAxis;
    @FXML
    CategoryAxis xAxis;
    @FXML
    BarChart<String, Number> barChart;


    @Override
    public void initialize(URL url, ResourceBundle resources) {
        //xAxis.setLabel("Route");
        //yAxis.setLabel("Carbon Emissions");

        XYChart.Series<String, Number> data = new XYChart.Series();

        //provide data EXAMPLES
        data.getData().add(new XYChart.Data("Route A", 100));
        data.getData().add(new XYChart.Data("Route b", 111));
        data.getData().add(new XYChart.Data("Route c", 50));

        barChart.getData().add(data);
    }



}

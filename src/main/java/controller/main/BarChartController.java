package controller.main;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.BorderPane;

public class BarChartController {

    @FXML
    BorderPane borderPane;

    public void showChart() {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Route");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Carbon Emissions");

        BarChart barChart = new BarChart(xAxis, yAxis);

        XYChart.Series data = new XYChart.Series();
        data.setName("Routes Taken");

        //provide data
        data.getData().add(new XYChart.Data("Route A", 100));
        data.getData().add(new XYChart.Data("Route b", 111));
        data.getData().add(new XYChart.Data("Route c", 50));

        barChart.getData().add(data);

        borderPane.setCenter(barChart);
    }


}

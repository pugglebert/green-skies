package controller.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class BarChartController implements Initializable {

    @FXML
    private BorderPane borderPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void handleClose(ActionEvent e) {

    }

    @FXML
    private void handleShowBarChart(ActionEvent e) {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Route");

        CategoryAxis yAxis = new CategoryAxis();
        yAxis.setLabel("Carbon Emissions");

        BarChart barChart = new BarChart(xAxis, yAxis);

        XYChart.Series data = new XYChart.Series();
        data.setName("Products sold");

        //provide data
        data.getData().add(new XYChart.Data("Route A", 100));
        data.getData().add(new XYChart.Data("Route b", 111));
        data.getData().add(new XYChart.Data("Route c", 50));

        barChart.getData().add(data);

        //add barChart to borderPane
        borderPane.setCenter(barChart);
    }


    @FXML
    private void handleUpdateData(ActionEvent e) {
    }

}

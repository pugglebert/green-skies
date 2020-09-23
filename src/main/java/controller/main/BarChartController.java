package controller.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class BarChartController extends SideNavBarController {

    /**
     * This method is the initializer for this class.
     *
     * @param url The provided resoure bundle.
     * @param resourceBundle The resoure bundle.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

    @FXML
    private BorderPane borderPane;


    @FXML
    private void handleClose(ActionEvent e) {

    }

    @FXML
    private void ShowBarChart(ActionEvent e) {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Route");

        NumberAxis yAxis = new NumberAxis();
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

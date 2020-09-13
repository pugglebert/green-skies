package controller.main;

import controller.analysis.FlightAnalyser;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import model.data.Airport;
import model.data.Route;

import static javafx.application.Application.launch;
//import org.controlsfx.control.textfield.TextFields;

public class AnalyseController extends SideNavBarController implements Initializable  {
    @FXML
    private TextField pathSource1;
    @FXML
    private TextField pathDestination1;

    @FXML
    private TextField pathSource2;

    @FXML
    private TextField pathDestination2;

    private HashSet<String> airports = new HashSet<>();

    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("analyse.fxml"));
        primaryStage.setTitle("Welcome");
        primaryStage.setScene(new Scene(root, 1024, 640));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(Main.getStorage().getAirports());
        for(Airport airport: Main.getStorage().getAirports()) {
            airports.add(airport.getIATA());

        }
        System.out.println(airports);
//        TextFields.bindAutoCompletion(pathSource1, airports);
//        TextFields.bindAutoCompletion(pathDestination1, airports);
//        TextFields.bindAutoCompletion(pathSource2, airports);
//        TextFields.bindAutoCompletion(pathDestination2, airports);
    }


    @FXML
    private void analyse() {

        ArrayList<String> path1 = new ArrayList<>();
        ArrayList<String> path2 = new ArrayList<>();
        path1.add((String)pathSource1.getText());
        path1.add((String)pathDestination1.getText());
        path2.add((String)pathSource2.getText());
        path2.add((String)pathDestination2.getText());

        Route route1 = new Route(null, 0, (String)pathSource1.getText(), 0, (String)pathDestination1.getText(), 0, null, 0, null);
        Route route2 = new Route(null, 0, (String)pathSource2.getText(), 0, (String)pathDestination2.getText(), 0, null, 0, null);
        FlightAnalyser analyser = new FlightAnalyser(route1, route2, Main.getStorage());
        System.out.println(analyser.getTotalDistancePath1());
        System.out.println(analyser.getTotalDistancePath2());
        System.out.println(analyser.getPath1Emission());
        System.out.println(analyser.getPath2Emission());

    }
}
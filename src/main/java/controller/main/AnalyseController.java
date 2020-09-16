package controller.main;

import controller.analysis.FlightAnalyser;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import model.data.Airport;
import model.data.Route;
import static javafx.application.Application.launch;
import org.controlsfx.control.textfield.TextFields;

// TODO: write comment for this class
public class AnalyseController extends SideNavBarController implements Initializable {

  @FXML private TextField pathSource1;

  @FXML private TextField pathDestination1;
  @FXML private TextField pathSource2;
  @FXML private TextField pathDestination2;

  // TODO: write comments for these attributes
  private HashSet<String> airports = new HashSet<>();

  // TODO: check all method comments start with "This method ..."

  public void start(Stage primaryStage) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("analyse.fxml"));
    primaryStage.setTitle("Welcome");
    primaryStage.setScene(new Scene(root, 1024, 640));
    primaryStage.show();
  }

  // TODO: check all method comments start with "This method ..."

  public static void main(String[] args) {
    launch(args);
  }
  // TODO: check all method comments start with "This method ..."

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    //        System.out.println(Main.getStorage().getAirports());
    for (Airport airport : Main.getStorage().getAirports()) {
      airports.add(airport.getIATA());
    }
    //        System.out.println(airports);
    TextFields.bindAutoCompletion(pathSource1, airports);
    TextFields.bindAutoCompletion(pathDestination1, airports);
    TextFields.bindAutoCompletion(pathSource2, airports);
    TextFields.bindAutoCompletion(pathDestination2, airports);
  }

  // TODO: check all method comments start with "This method ..."

  @FXML
  private void analyse() throws IOException {

    ArrayList<String> path1 = new ArrayList<>();
    ArrayList<String> path2 = new ArrayList<>();
    path1.add((String) pathSource1.getText());
    path1.add((String) pathDestination1.getText());
    path2.add((String) pathSource2.getText());
    path2.add((String) pathDestination2.getText());

    Route route1 =
        new Route(
            null,
            0,
            (String) pathSource1.getText(),
            0,
            (String) pathDestination1.getText(),
            0,
            null,
            0,
            null);
    Route route2 =
        new Route(
            null,
            0,
            (String) pathSource2.getText(),
            0,
            (String) pathDestination2.getText(),
            0,
            null,
            0,
            null);
    FlightAnalyser analyser = new FlightAnalyser(route1, route2, Main.getStorage());

    System.out.println(analyser.getTotalDistancePath1());
    System.out.println(analyser.getTotalDistancePath2());
    System.out.println(analyser.getPath1Emission());
    System.out.println(analyser.getPath2Emission());

    storage.setAnalyseDistanceResult(analyser.getTotalDistancePath1());
    storage.setAnalyseDistanceResult(analyser.getTotalDistancePath2());
    storage.setAnalyseEmissionResult(analyser.getPath1Emission());
    storage.setAnalyseEmissionResult(analyser.getPath2Emission());

    Stage stage1 = new Stage();
    Parent root = FXMLLoader.load(getClass().getResource("analyseResult.fxml"));
    Scene scene = new Scene(root);
    stage1.setScene(scene);
    stage1.setMaximized(true);
    stage1.show();
  }
}

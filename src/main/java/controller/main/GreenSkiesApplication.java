package controller.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * The controller class which contains the controls for starting the application.
 *
 * @author Hayley Krippner
 * @version 1.0
 * @since 04/09/20
 */
public class GreenSkiesApplication extends Application {

  /**
   * This method starts the application's GUI
   *
   * @param primaryStage The primary stage used.
   * @throws IOException This throws an IOException.
   */
  @Override
  public void start(Stage primaryStage) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("welcome.fxml"));
    primaryStage.setTitle("Welcome");
    primaryStage.setScene(new Scene(root));
    primaryStage.show();
  }

  /**
   * This method is where the application is called from.
   *
   * @param args The passed in arguements.
   */
  public static void main(String[] args) {
    launch(args);
  }
}

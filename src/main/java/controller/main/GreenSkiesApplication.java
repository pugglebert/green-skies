package controller.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

// TODO: check all method comments start with "This method ..."

/**
 * The controller class which contains the controls for starting the application.
 *
 * @author Hayley Krippner
 * @version 1.0
 * @since 04/09/20
 */
public class GreenSkiesApplication extends Application {
  // TODO: write comment for this method

  /** @param primaryStage, the primary stage used. */
  @Override
  public void start(Stage primaryStage) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("welcome.fxml"));
    primaryStage.setTitle("Welcome");
    primaryStage.setScene(new Scene(root, 1024, 640));
    primaryStage.setMaximized(true);
    primaryStage.show();
  }
  // TODO: write comment for this method

  public static void main(String[] args) {
    launch(args);
  }
}

package controller.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

//TODO: update comment. What is this used for? (HK 4/9/2020)
/**
 *
 * @author jwright
 */
public class NavigationController extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("viewRouteData.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * TODO: comment method
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}

package controller.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GreenSkiesApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("viewRouteData.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * TODO: uncomment to run welcome page (later)
     * @param args
     */
//    @Override
//    public void start(Stage primaryStage) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("welcome.fxml"));
//        primaryStage.setTitle("Welcome");
//        primaryStage.setScene(new Scene(root, 1024, 640));
//        primaryStage.show();
//    }



//
//@Override public void start(Stage primaryStage) {
//    primaryStage.setTitle("Login");primaryStage.show();
//    GridPane gridPane = new GridPane();
//    gridPane.setAlignment(Pos.CENTER);
//    gridPane.setHgap(10);
//    gridPane.setVgap(10);
//    gridPane.setPadding(new Insets(25, 25, 25, 25));
//
//    Text sceneTitle = new Text("Welcome!");
//    sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
//    gridPane.add(sceneTitle, 0, 0, 2, 1); // Col 0, Row 0,ColSpan 2, RowSpan 1
//    Label username = new Label("Username:");
//    gridPane.add(username, 0, 1); // Col 0, Row 1
//    TextField usernameTextField = new TextField();
//    gridPane.add(usernameTextField, 1, 1); // Col 1, Row 1
//
//    Label password = new Label("Password:");
//    gridPane.add(password, 0, 2); // Col 0, Row 2
//    PasswordField passwordField = new PasswordField();
//    gridPane.add(passwordField, 1, 2); // Col 1, Row
//    //gridPane.setGridLinesVisible(true);
//
//    Button button = new Button("Sign in");
//    HBox hboxButton = new HBox(10);
//    hboxButton.setAlignment(Pos.BOTTOM_RIGHT);
//    hboxButton.getChildren().add(button);
//    gridPane.add(hboxButton, 1, 4);
//
//    Text actionTarget = new Text();
//    gridPane.add(actionTarget, 1, 6);
//
//    button.setOnAction(new EventHandler<ActionEvent>() {
//        @Override
//        public void handle(ActionEvent e) {
//            actionTarget.setFill(Color.FIREBRICK);
//            actionTarget.setText("Sign in button pressed.");
//        }
//    });
//
//    Scene scene = new Scene(gridPane, 400, 250);
//    primaryStage.setScene(scene);
//}

    public static void main(String[] args) {
        launch(args);
    }
}

package controller.main;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class UploadController extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("upload.fxml"));
        primaryStage.setTitle("Welcome");
        primaryStage.setScene(new Scene(root, 1024, 640));
        primaryStage.show();
    }

    ObservableList<String> dataTypeList = FXCollections.
            observableArrayList("Airport", "Route", "Airline");


    @FXML
    private ChoiceBox dataTypeSelect;
    @FXML
    private ListView fileView;
    @FXML
    private Text fileErrorText;
    @FXML
    private Button nextButton;
    @FXML
    public void initialize(){
        dataTypeSelect.getItems().addAll(dataTypeList);
    }

    public void browseFiles() {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null){
            fileView.getItems().add(selectedFile.getName());
            nextButton.setVisible(true);
        } else{
            fileErrorText.setVisible(true);

        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}

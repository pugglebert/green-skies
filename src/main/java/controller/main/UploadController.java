package controller.main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.io.File;

public class UploadController  {

    //Initialise the list of possible data Types for the user to choose from
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

    //Browse home files when 'Browse' button pushed
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


}

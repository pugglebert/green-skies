package controller.main;

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
import model.data.Storage;
import model.loader.Loader;

import java.io.File;
import java.io.IOException;

public class UploadController {

    //Initialize storage space for selected file
    public final Storage storage = new Storage();
    public final Loader loader = new Loader(storage);

    ObservableList<String> dataTypeList = FXCollections.
            observableArrayList("Airport", "route", "airline");

    @FXML
    private ChoiceBox dataTypeSelect;
    @FXML
    private ListView fileView;
    @FXML
    private Text fileErrorText;
    @FXML
    private Button nextButton;


//    public UploadController(Main main){
//        this.main = main;
//    }

    public void initialize(){
        dataTypeSelect.getItems().addAll(dataTypeList);
    }


    //opens file browser when button pushed
    public void browseFiles() {

        FileChooser fileChooser = new FileChooser(); //opens a file local file browser
        File selectedFile = fileChooser.showOpenDialog(null);

        String fileType = dataTypeSelect.getValue().toString();
        String stringFile = selectedFile.toString();

        if (selectedFile != null){  // check a file has been selected
            try{
            String resultString = loader.loadFile(stringFile, fileType);
            fileErrorText.setText(resultString);
            fileErrorText.setVisible(true);

            }
            catch (Exception e){
                //@TODO Display error message
            }
            fileView.getItems().add(selectedFile.getName());
            nextButton.setVisible(true); //'Next' button pops up if a valid file has been selected
        } else{
            fileErrorText.setVisible(true);

        }
    }


    //take user back to the welcome screen in case of wanting to see info screen
    public void backToWelcome() throws IOException {
        Stage stage = new Stage(); // create a stage for scene
        Parent root = FXMLLoader.load(getClass().getResource("welcome.fxml")); // load welcome.fxml
        Scene scene = new Scene(root, 1024, 640);  // apply stuff wanna show to scene
        stage.setResizable(false);
        stage.setScene(scene);  // set up scene
        stage.show(); // time for performing
    }


}

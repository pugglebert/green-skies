package controller.main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.data.DataType;
import model.data.Route;
import model.data.Storage;
import model.loader.Loader;
import java.io.File;
import java.io.IOException;

public class UploadController {

    //Initialize storage space for selected file
    private final Storage storage = Main.getStorage();
    private final Loader loader = Main.getLoader();

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
    private Button backButton;
    @FXML
    private Button btnUpload;
    @FXML
    private Button btnRouteDataView;
    @FXML
    private Button btnAirportDataView;
    @FXML
    private Button btnAirlineDataView;


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
            for (DataType line: storage.getRoutes()){
                Route test = (Route) line;
                System.out.println(test.getAirlineID());
                }

            }
            catch (Exception e){
                //@TODO Display error message
            }
            fileView.getItems().add(selectedFile.getName());
            nextButton.setVisible(true); //'Next' button pops up if a valid file has been selected

            //Create the view raw data controllers

        } else{
            fileErrorText.setVisible(true);

        }
    }

    //take user back to the welcome screen in case of wanting to see info screen
    public void backToWelcome() throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();   //get current window
        stage.close();  // close current window
        Stage stage1 = new Stage();  //create new stage
        Parent root = FXMLLoader.load(getClass().getResource("welcome.fxml")); //reopen welcome.fxml
        Scene scene = new Scene(root);   //add thing to scene
        stage1.setScene(scene);
        stage1.show();
    }

    //take user back to the upload screen
    public void toUploadData() throws IOException {
        Stage stage = (Stage) btnUpload.getScene().getWindow();   //get current window
        stage.close();  // close current window
        Stage stage1 = new Stage(); // create new stage
        Parent root = FXMLLoader.load(getClass().getResource("upload.fxml")); //reopen welcome.fxml
        Scene scene = new Scene(root);   //add thing to scene
        stage1.setScene(scene);
        stage1.show();
    }

    //take user back to the route data view
    public void toRouteDataView() throws IOException {
        Stage stage = (Stage) btnRouteDataView.getScene().getWindow();   //get current window
        stage.close();  // close current window
        Stage stage1 = new Stage(); // create new stage
        Parent root = FXMLLoader.load(getClass().getResource("viewRouteData.fxml")); //reopen welcome.fxml
        Scene scene = new Scene(root);   //add thing to scene
        stage1.setScene(scene);
        stage1.show();
    }

    //take user back to the airport data view
    public void toAirportDataView() throws IOException {
        Stage stage = (Stage) btnAirportDataView.getScene().getWindow();   //get current window
        stage.close();  // close current window
        Stage stage1 = new Stage(); // create new stage
        Parent root = FXMLLoader.load(getClass().getResource("viewAirportData.fxml")); //open viewAirportData.fxml
        Scene scene = new Scene(root);   //add thing to scene
        stage1.setScene(scene);
        stage1.show();
    }

    //take user back to the airline data view screen
    public void toAirlineDataView() throws IOException {
        Stage stage = (Stage) btnAirlineDataView.getScene().getWindow();   //get current window
        stage.close();  // close current window
        Stage stage1 = new Stage(); // create new stage
        Parent root = FXMLLoader.load(getClass().getResource("viewAirlineData.fxml")); //reopen welcome.fxml
        Scene scene = new Scene(root);   //add thing to scene
        stage1.setScene(scene);
        stage1.show();
    }

}

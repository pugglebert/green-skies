package controller.main;

import controller.analysis.ReportGenerator;
import controller.main.SideNavBarController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * The controller class which contains the controls for the carbon emissions report page.
 * @author Hayley Krippner
 * @version 1.0
 * @since 14/09/20
 */
public class CarbonEmissionsReportController extends SideNavBarController {

    @FXML
    private TextField displayTotalEmissionsField;
    @FXML
    private TextField displayTotalDistanceTravelledField;
    @FXML
    private TextField displayMostEmissionsRouteField;
    @FXML
    private TextField displayLeastEmissionsRouteField;
    @FXML
    private TextField displayMostDistanceRouteField;
    @FXML
    private TextField displayLeastDistanceRouteField;
    @FXML
    private TextField displayMostVisitedSourceAirportField;
    @FXML
    private TextField displayLeastVisitedSourceAirportField;
    @FXML
    private TextField displayMostVisitedDestinationAirportField;
    @FXML
    private TextField displayLeastVisitedDestinationAirportField;
    @FXML
    private TextField displayCarbonEmissionGoalField;
    @FXML
    private TextField displaycarbonEmissionGoalDurationField;
    @FXML
    private TextField displayTreeOffsetField;
    @FXML
    private Button generateReportDataButton;
    @FXML
    private Button clearReportDataButton;
    @FXML
    private TextField carbonEmissionGoalDurationField;
    @FXML
    private TextField carbonEmissionGoalField;
    private ReportGenerator reportGenerator;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.reportGenerator = Main.getReportGenerator(); //TODO update this so that is
    }

    //TODO: the inputs need to be validated and a text box needs to be displayed for invalid input 13/09/2020 HK.

    /**
     * This method displays the carbon emissions report information based on the inputted goal, duration and current
     * flight history records.
     * @param event
     * @throws IOException
     */
    @FXML
    private void generateReportData(ActionEvent event) throws IOException {
        String carbonEmissionGoalValue = carbonEmissionGoalField.getText();
        displayCarbonEmissionGoalField.setText(carbonEmissionGoalValue);
        String carbonEmissionGoalDurationValue = carbonEmissionGoalDurationField.getText();
        displaycarbonEmissionGoalDurationField.setText(carbonEmissionGoalDurationValue);
        displayTotalEmissionsField.setText(Double.toString(reportGenerator.getTotalCarbonEmissions()));
        displayTotalDistanceTravelledField.setText(Double.toString(reportGenerator.getTotalDistanceTravelled()));
//        displayMostEmissionsRoute.setText(""); //TODO: implement this as table or popup with table
//        displayLeastEmissionsRoute.setText(""); //TODO: implement this as table or popup with table
//        displayMostDistanceRoute.setText(""); //TODO: implement this as table or popup with table
//        displayLeastDistanceRoute.setText(""); //TODO: implement this as table or popup with table
        displayMostVisitedSourceAirportField.setText(""); //TODO: implement this as table or popup with table
        displayLeastVisitedSourceAirportField.setText(""); //TODO: implement this as table or popup with table
        displayMostVisitedDestinationAirportField.setText(""); //TODO: implement this as table or popup with table
        displayLeastVisitedDestinationAirportField.setText(""); //TODO: implement this as table or popup with table
        displayTreeOffsetField.setText(""); //TODO implement once have tree calcution implemented
    }

    /**
     * This method clears all fields in the report generator page to be empty.
     * @param event
     * @throws IOException
     */
    @FXML
    private void clearReportData(ActionEvent event) throws IOException {
        displayCarbonEmissionGoalField.setText("");
        carbonEmissionGoalField.setText("");
        displaycarbonEmissionGoalDurationField.setText("");
        carbonEmissionGoalDurationField.setText("");
        displayTotalEmissionsField.setText("");
        displayTotalDistanceTravelledField.setText("");
        displayMostEmissionsRouteField.setText(""); //TODO: implement this as table or popup with table
        displayMostEmissionsRouteField.setText(""); //TODO: implement this as table or popup with table
        displayLeastEmissionsRouteField.setText(""); //TODO: implement this as table or popup with table
        displayMostDistanceRouteField.setText(""); //TODO: implement this as table or popup with table
        displayLeastEmissionsRouteField.setText(""); //TODO: implement this as table or popup with table
        displayLeastDistanceRouteField.setText(""); //TODO: implement this as table or popup with table
        displayMostVisitedSourceAirportField.setText(""); //TODO: implement this as table or popup with table
        displayLeastVisitedSourceAirportField.setText(""); //TODO: implement this as table or popup with table
        displayMostVisitedDestinationAirportField.setText(""); //TODO: implement this as table or popup with table
        displayLeastVisitedDestinationAirportField.setText(""); //TODO: implement this as table or popup with table
        displayTreeOffsetField.setText("");
    }

}

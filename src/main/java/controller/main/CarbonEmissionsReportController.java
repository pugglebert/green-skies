package controller.main;

import controller.main.SideNavBarController;
import javafx.fxml.FXML;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * The controller class which contains the controls for the carbon emissions report page.
 * @author Hayley Krippner
 * @version 1.0
 * @since 04/09/20
 */
public class CarbonEmissionsReportController extends SideNavBarController {

    @FXML
    private TextField displayTotalEmissions;

    @FXML
    private TextField displayTotalDistanceTravelled;

    @FXML
    private TextField displayMostEmissionsRoute;

    @FXML
    private TextField displayLeastEmissionsRoute;

    @FXML
    private TextField displayMostDistanceRoute;

    @FXML
    private TextField displayLeastDistanceRoute;

    @FXML
    private TextField displayMostVisitedSourceAirport;

    @FXML
    private TextField displayLeastVisitedSourceAirport;

    @FXML
    private TextField displayMostVisitedDestinationAirport;

    @FXML
    private TextField displayLeastVisitedDestinationAirport;

    @FXML
    private TextField displayCarbonEmissionsGoal;

    @FXML
    private TextField displayGoalDuration;

    @FXML
    private TextField displayTreeOffset;

    @FXML
    private Button generateReportDataButton;

    @FXML
    private Button clearReportDataButton;

    @FXML
    private TextField carbonEmissionGoalDurationField;

    @FXML
    private TextField carbonEmissionGoalField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayCarbonEmissionsGoal.setText("");
        carbonEmissionGoalDurationField.setText("");
    }

    //TODO: the inputs need to be validated and a text box needs to be displayed for invalid input 13/09/2020 HK.

    @FXML
    private void generateReportData(ActionEvent event) throws IOException {
        String carbonEmissionGoalValue = carbonEmissionGoalField.getText();
        displayCarbonEmissionsGoal.setText(carbonEmissionGoalValue);
        String carbonEmissionGoalDurationValue = carbonEmissionGoalDurationField.getText();
        carbonEmissionGoalDurationField.setText(carbonEmissionGoalDurationValue);
//
//        displayTotalEmissions.setText("");
//        displayTotalDistanceTravelled.setText("");
//        displayMostEmissionsRoute.setText("");
//        displayMostEmissionsRoute.setText("");
//        displayLeastEmissionsRoute.setText("");
//        displayMostDistanceRoute.setText("");
//        displayLeastEmissionsRoute.setText("");
//        displayLeastDistanceRoute.setText("");
//        displayMostVisitedSourceAirport.setText("");
//        displayLeastVisitedSourceAirport.setText("");
//        displayMostVisitedDestinationAirport.setText("");
//        displayLeastVisitedDestinationAirport.setText("");
//        displayTreeOffset.setText("");
    }

    @FXML
    private void clearReportData(javafx.event.ActionEvent event) throws IOException {
//        displayCarbonEmissionsGoal.setText("");
//        carbonEmissionGoalField.setText("");
//        displayGoalDuration.setText("");
//        carbonEmissionGoalDurationField.setText("");
//        displayTotalEmissions.setText("");
//        displayTotalDistanceTravelled.setText("");
//        displayMostEmissionsRoute.setText("");
//        displayMostEmissionsRoute.setText("");
//        displayLeastEmissionsRoute.setText("");
//        displayMostDistanceRoute.setText("");
//        displayLeastEmissionsRoute.setText("");
//        displayLeastDistanceRoute.setText("");
//        displayMostVisitedSourceAirport.setText("");
//        displayLeastVisitedSourceAirport.setText("");
//        displayMostVisitedDestinationAirport.setText("");
//        displayLeastVisitedDestinationAirport.setText("");
//        displayTreeOffset.setText("");
    }
//
//    public void generateReportData(javafx.event.ActionEvent actionEvent) throws IOException{
//        String carbonEmissionGoalValue = carbonEmissionGoalField.getText();
//        displayCarbonEmissionsGoal.setText(carbonEmissionGoalValue);
//        String carbonEmissionGoalDurationValue = carbonEmissionGoalDurationField.getText();
//        carbonEmissionGoalDurationField.setText(carbonEmissionGoalDurationValue);
//
//        displayTotalEmissions.setText("");
//        displayTotalDistanceTravelled.setText("");
//        displayMostEmissionsRoute.setText("");
//        displayMostEmissionsRoute.setText("");
//        displayLeastEmissionsRoute.setText("");
//        displayMostDistanceRoute.setText("");
//        displayLeastEmissionsRoute.setText("");
//        displayLeastDistanceRoute.setText("");
//        displayMostVisitedSourceAirport.setText("");
//        displayLeastVisitedSourceAirport.setText("");
//        displayMostVisitedDestinationAirport.setText("");
//        displayLeastVisitedDestinationAirport.setText("");
//        displayTreeOffset.setText("");
//    }
}

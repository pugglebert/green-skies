package controller.main;

import controller.analysis.ReportGenerator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.data.Route;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * The controller class which contains the controls for the carbon emissions report page.
 *
 * @author Hayley Krippner
 * @version 1.0
 * @since 19/09/20
 */
public class CarbonEmissionsReportController extends SideNavBarController {

  @FXML private TextField displayTotalEmissionsField;
  @FXML private TextField displayTotalDistanceTravelledField;
  @FXML private TextField displayMostEmissionsRouteField;
  @FXML private TextField displayLeastEmissionsRouteField;
  @FXML private TextField displayMostDistanceRouteField;
  @FXML private TextField displayLeastDistanceRouteField;
  @FXML private TextField displayMostVisitedSourceAirportField;
  @FXML private TextField displayLeastVisitedSourceAirportField;
  @FXML private TextField displayMostVisitedDestinationAirportField;
  @FXML private TextField displayLeastVisitedDestinationAirportField;
  @FXML private TextField displayCarbonEmissionGoalField;
  @FXML private TextField displaycarbonEmissionGoalDurationField;
  @FXML private TextField displayTreeOffsetField;
  @FXML private Button generateReportDataButton;
  @FXML private Button clearReportDataButton;
  @FXML private TextField carbonEmissionGoalDurationField;
  @FXML private TextField carbonEmissionGoalField;
  private ReportGenerator reportGenerator;
  private String MostEmissionsRouteString;
  private String LeastEmissionsRouteString;
  private String MostDistanceRouteString;
  private String LeastDistanceRouteString;
  private String MostVisitedSourceAirport;
  private String LeastVisitedSourceAirport;
  private String MostVisitedDestAirport;
  private String LeastVisitedDestAirport;

  /**
   * This method is the initializer for this class.
   *
   * @param url The provided url.
   * @param resourceBundle The provided resource bundle.
   */
  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    this.reportGenerator = Main.getReportGenerator();
  }

  /**
   * This method displays the carbon emissions report information based on the inputted goal,
   * duration and current flight history records.
   *
   * @param event The event to handle.
   * @throws IOException
   */
  @FXML
  private void generateReportData(ActionEvent event) throws IOException {
    setUpData();

    String carbonEmissionGoalValue = carbonEmissionGoalField.getText();
    displayCarbonEmissionGoalField.setText(carbonEmissionGoalValue);
    String carbonEmissionGoalDurationValue = carbonEmissionGoalDurationField.getText();
    displaycarbonEmissionGoalDurationField.setText(carbonEmissionGoalDurationValue);
    displayTotalEmissionsField.setText(Double.toString(reportGenerator.getTotalCarbonEmissions()));
    displayTotalDistanceTravelledField.setText(Double.toString(reportGenerator.getTotalDistanceTravelled()));
    displayMostEmissionsRouteField.setText(MostEmissionsRouteString);
    displayLeastEmissionsRouteField.setText(LeastEmissionsRouteString);
    displayMostDistanceRouteField.setText(MostDistanceRouteString);
    displayLeastDistanceRouteField.setText(LeastDistanceRouteString);
    displayMostVisitedSourceAirportField.setText(MostVisitedSourceAirport);
    displayLeastVisitedSourceAirportField.setText("To be implemented!");
    displayMostVisitedDestinationAirportField.setText("To be implemented!");
    displayLeastVisitedDestinationAirportField.setText("To be implemented!");
    displayTreeOffsetField.setText("To be implemented!");

    reportGenerator.resetReportGenerator();

  }

  /**
   * This method clears all fields in the report generator page to be empty.
   *
   * @param event The event to handle.
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
    displayMostEmissionsRouteField.setText("");
    displayMostEmissionsRouteField.setText("");
    displayLeastEmissionsRouteField.setText("");
    displayMostDistanceRouteField.setText("");
    displayLeastEmissionsRouteField.setText("");
    displayLeastDistanceRouteField.setText("");
    displayMostVisitedSourceAirportField.setText("");
    displayLeastVisitedSourceAirportField.setText("");
    displayMostVisitedDestinationAirportField.setText("");
    displayLeastVisitedDestinationAirportField.setText("");
    displayTreeOffsetField.setText("");

    reportGenerator.resetReportGenerator();
  }

  public void setUpData() {

    this.MostEmissionsRouteString = RoutesArrayToString(MostEmissionsRouteString, reportGenerator.getMostEmissionsRoutes());
    this.LeastEmissionsRouteString = RoutesArrayToString(LeastEmissionsRouteString, reportGenerator.getLeastEmissionsRoutes());
    this.MostDistanceRouteString = RoutesArrayToString(MostDistanceRouteString, reportGenerator.getMostDistanceRoutes());
    this.LeastDistanceRouteString = RoutesArrayToString(LeastDistanceRouteString, reportGenerator.getLeastDistanceRoutes());
    this.MostVisitedSourceAirport = CombineAirportsToOneString(MostVisitedSourceAirport, reportGenerator.getMostVisitedSrcAirports());
    this.LeastVisitedSourceAirport = CombineAirportsToOneString(LeastVisitedSourceAirport, reportGenerator.getLeastVisitedSrcAirports());
    this.MostVisitedDestAirport = CombineAirportsToOneString(MostVisitedDestAirport, reportGenerator.getMostVisitedDestAirports());
    this.LeastVisitedDestAirport = CombineAirportsToOneString(LeastVisitedDestAirport, reportGenerator.getLeastVisitedDestAirports());

  }

  /**
   * This methods takes an arrary containing either routes with most or least emissions or most or least distance and
   * produces a string of its routes' AirlineIDs.
   * @param resultString Where the content in the provided array with be added to.
   * @param arrayToConvert Either the most or least emissions or most or least distance array that needs to be
   *                                converted to a String.
   * @return resultString The string of the array's routes' AirlineIDs.
   */
  public String RoutesArrayToString(String resultString, ArrayList<Route> arrayToConvert) {
    for (int i = 0; i < arrayToConvert.size(); i++) {
      if (arrayToConvert.get(i).getAirlineID() >= 0)
        if (i == arrayToConvert.size() - 1) {
          resultString += arrayToConvert.get(i).getAirlineID();
        } else {
          resultString += arrayToConvert.get(i).getAirlineID() + " , ";
        }
    }
  return resultString;
  }

  // TODO fix comments
  /**
   * This methods takes an array containing either routes with most or least emissions or most or
   * least distance and produces a string of its routes' AirlineIDs.
   *
   * @param resultString Where the content in the provided array with be added to.
   * @param airportArrayToConvert Either the most or least emissions or most or least distance
   *     array that needs to be converted to a String.
   * @return resultString The string of the array's routes' AirlineIDs.
   */
  public String CombineAirportsToOneString(String resultString, ArrayList<String> airportArrayToConvert) {
    for (int i = 0; i < airportArrayToConvert.size(); i++) {
      if (i == airportArrayToConvert.size() - 1) {
        resultString += airportArrayToConvert.get(i);
      } else {
        resultString += airportArrayToConvert.get(i) + " , ";
      }
    }
    return resultString;
    }
}

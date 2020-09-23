package controller.main;

import controller.analysis.ReportGenerator;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.data.Route;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
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
  @FXML private TextField carbonEmissionGoalDurationField;
  @FXML private TextField carbonEmissionGoalField;
  /** This reportGenerator for the application. */
  private ReportGenerator reportGenerator;
  /** A string of the most emission routes. */
  private String MostEmissionsRouteString = "";
  /** A string of the least emission routes. */
  private String LeastEmissionsRouteString;
  /** A string of the most distance routes. */
  private String MostDistanceRouteString;
  /** A string of the least distance routes. */
  private String LeastDistanceRouteString;
  /** A string of the most visited source airports. */
  private String MostVisitedSourceAirport;
  /** A string of the least visited source airports. */
  private String LeastVisitedSourceAirport;
  /** A string of the most visited destination airports. */
  private String MostVisitedDestAirport;
  /** A string of the least visited destination airports. */
  private String LeastVisitedDestAirport;

  public CarbonEmissionsReportController() {}

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
   */
  @FXML
  private void generateReportData() {
    setUpData();

    String carbonEmissionGoalValue = carbonEmissionGoalField.getText();
    displayCarbonEmissionGoalField.setText(carbonEmissionGoalValue);
    String carbonEmissionGoalDurationValue = carbonEmissionGoalDurationField.getText();
    displaycarbonEmissionGoalDurationField.setText(carbonEmissionGoalDurationValue);
    displayTotalEmissionsField.setText(Double.toString(reportGenerator.getTotalCarbonEmissions()));
    displayTotalDistanceTravelledField.setText(
        Double.toString(reportGenerator.getTotalDistanceTravelled()));
    displayMostEmissionsRouteField.setText(MostEmissionsRouteString);
    displayLeastEmissionsRouteField.setText(LeastEmissionsRouteString);
    displayMostDistanceRouteField.setText(MostDistanceRouteString);
    displayLeastDistanceRouteField.setText(LeastDistanceRouteString);
    displayMostVisitedSourceAirportField.setText(MostVisitedSourceAirport);
    displayLeastVisitedSourceAirportField.setText(LeastVisitedSourceAirport);
    displayMostVisitedDestinationAirportField.setText(MostVisitedDestAirport);
    displayLeastVisitedDestinationAirportField.setText(LeastVisitedDestAirport);
    displayTreeOffsetField.setText("To be implemented!");

    //reportGenerator.resetReportGenerator();
  }

  /** This method clears all fields in the report generator page to be empty. */
  @FXML
  private void clearReportData() {
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

    //reportGenerator.resetReportGenerator();
  }

  public void setUpData() {
    System.out.println(MostEmissionsRouteString);
    this.MostEmissionsRouteString =
        RoutesArrayToString(reportGenerator.getMostEmissionsRoutes());
    this.LeastEmissionsRouteString =
        RoutesArrayToString(reportGenerator.getLeastEmissionsRoutes());
    this.MostDistanceRouteString =
        RoutesArrayToString(reportGenerator.getMostDistanceRoutes());
    this.LeastDistanceRouteString =
        RoutesArrayToString(reportGenerator.getLeastDistanceRoutes());
    this.MostVisitedSourceAirport =
        CombineAirportsToOneString(
            reportGenerator.getMostVisitedSrcAirports());
    this.LeastVisitedSourceAirport =
        CombineAirportsToOneString(
            reportGenerator.getLeastVisitedSrcAirports());
    this.MostVisitedDestAirport =
        CombineAirportsToOneString(
            reportGenerator.getMostVisitedDestAirports());
    this.LeastVisitedDestAirport =
        CombineAirportsToOneString(
            reportGenerator.getLeastVisitedDestAirports());
  }

  /**
   * This methods takes an arrary containing either routes with most or least emissions or most or
   * least distance and produces a string of its routes' AirlineIDs.
   *
   * @param arrayToConvert Either the most or least emissions or most or least distance array that
   *     needs to be converted to a String.
   * @return resultString The string of the array's routes' AirlineIDs.
   */
  public String RoutesArrayToString(ArrayList<Route> arrayToConvert) {
    String resultString = "";
    StringBuilder resultStringBuilder = new StringBuilder(resultString);
    for (int i = 0; i < arrayToConvert.size(); i++) {
      if (arrayToConvert.get(i).getAirlineID() >= 0)
        if (i == arrayToConvert.size() - 1) {
          resultStringBuilder.append(arrayToConvert.get(i).getAirlineID());
        } else {
          resultStringBuilder.append(arrayToConvert.get(i).getAirlineID()).append(", ");
        }
    }
    resultString = resultStringBuilder.toString();
    return resultString;
  }

  /**
   * This methods takes an array containing either source or destination airports that are most or
   * least visited and produces a string of its routes' AirlineIDs.
   *
   * @param airportArrayToConvert Either the most or least visited source or destination airports
   *     array that needs to be converted to a String.
   * @return resultString The string of the array's airport names.
   */
  public String CombineAirportsToOneString(
      ArrayList<String> airportArrayToConvert) {
    String resultString = "";
    StringBuilder resultStringBuilder = new StringBuilder(resultString);
    for (int i = 0; i < airportArrayToConvert.size(); i++) {
      if (i == airportArrayToConvert.size() - 1) {
        resultStringBuilder.append(airportArrayToConvert.get(i));
      } else {
        resultStringBuilder.append(airportArrayToConvert.get(i)).append(", ");
      }
    }
    resultString = resultStringBuilder.toString();
    return resultString;
  }
}

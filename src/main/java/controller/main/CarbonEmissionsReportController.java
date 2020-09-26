package controller.main;

import controller.analysis.ReportGenerator;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.data.Route;

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
  @FXML private TextField carbonEmissionGoalField;
  @FXML private TextField displayStatusCommentField;

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
  private String MostVisitedSourceAirportString;
  /** A string of the least visited source airports. */
  private String LeastVisitedSourceAirportString;
  /** A string of the most visited destination airports. */
  private String MostVisitedDestAirportString;
  /** A string of the least visited destination airports. */
  private String LeastVisitedDestAirportString;
  /**
   * A string of the number of trees required to offset the provided amount of carbon emissions.
   */
  private String numOfTreesString;


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
    reportGenerator.updateTravelledAndVisited();
    setUpData();

    String carbonEmissionGoalValue = carbonEmissionGoalField.getText();
    displayCarbonEmissionGoalField.setText(carbonEmissionGoalValue);
    //TODO: fix this line below! It's creating issues and crashing the app.
    reportGenerator.setCarbonEmissionsGoal(Double.parseDouble(carbonEmissionGoalValue)); //TODO write test for handling different errors

    displayTotalEmissionsField.setText(String.format("%.2f",reportGenerator.getTotalCarbonEmissions()));
    displayTotalDistanceTravelledField.setText(String.format("%.2f",reportGenerator.getTotalDistanceTravelled()));
    displayMostEmissionsRouteField.setText(MostEmissionsRouteString);
    displayLeastEmissionsRouteField.setText(LeastEmissionsRouteString);
    displayMostDistanceRouteField.setText(MostDistanceRouteString);
    displayLeastDistanceRouteField.setText(LeastDistanceRouteString);
    displayMostVisitedSourceAirportField.setText(MostVisitedSourceAirportString);
    displayLeastVisitedSourceAirportField.setText(LeastVisitedSourceAirportString);
    displayMostVisitedDestinationAirportField.setText(MostVisitedDestAirportString);
    displayLeastVisitedDestinationAirportField.setText(LeastVisitedDestAirportString);

    displayTreeOffsetField.setText(numOfTreesString);
    displayStatusCommentField.setText(reportGenerator.getCarbonEmissionsComment());

    reportGenerator.resetReportGenerator();
  }

  /** This method clears all fields in the report generator page to be empty. */
  @FXML
  private void clearReportData() {
    displayCarbonEmissionGoalField.setText("");
    carbonEmissionGoalField.setText("");
    displaycarbonEmissionGoalDurationField.setText("");
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
    reportGenerator.calculateOffsetTrees();
    System.out.println(MostEmissionsRouteString);
    this.MostEmissionsRouteString =
        RoutesArrayToString(reportGenerator.getMostEmissionsRoutes());
    this.LeastEmissionsRouteString =
        RoutesArrayToString(reportGenerator.getLeastEmissionsRoutes());
    this.MostDistanceRouteString =
        RoutesArrayToString(reportGenerator.getMostDistanceRoutes());
    this.LeastDistanceRouteString =
        RoutesArrayToString(reportGenerator.getLeastDistanceRoutes());
    this.MostVisitedSourceAirportString =
        CombineAirportsToOneString(
            reportGenerator.getMostVisitedSrcAirports());
    this.LeastVisitedSourceAirportString =
        CombineAirportsToOneString(
            reportGenerator.getLeastVisitedSrcAirports());
    this.MostVisitedDestAirportString =
        CombineAirportsToOneString(
            reportGenerator.getMostVisitedDestAirports());
    this.LeastVisitedDestAirportString =
        CombineAirportsToOneString(
            reportGenerator.getLeastVisitedDestAirports());
    numOfTreesToString(reportGenerator.getTreesToGrow());
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

  /**
   * This method converts the number of trees to offset emissions to a string that has been ceiled.
   * @param trees The number of trees to offset as a double.
   */
  public void numOfTreesToString(Double trees) {
    this.numOfTreesString = String.format("%.0f", Math.ceil(trees));
  }
}

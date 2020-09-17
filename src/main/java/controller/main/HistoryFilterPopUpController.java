package controller.main;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

// TODO: check all method comments start with "This method ..."

// TODO: write comment for this class

public class HistoryFilterPopUpController extends FilterPopUpController {

  @FXML private CheckBox airlineCheckBox;
  @FXML private CheckBox sourceCheckBox;
  @FXML private CheckBox destinationCheckBox;
  @FXML private TextField airlineField;
  @FXML private TextField sourceField;
  @FXML private TextField destinationField;

  /**
   * Must have implementation of this method as superclass implements the Initializable interface.
   *
   * @param url Not used.
   * @param resourceBundle Not used.
   */
  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {}

  /**
   * Gets the filter terms the user has entered from the text fields and puts them into a HashMap
   * with the filter type as the key and the filter term as the value.
   *
   * @return A HashMap with filter types as keys and filter terms as values.
   */
  public HashMap<String, String> getFilterTerms() {
    HashMap<String, String> filterTerms = new HashMap<>();
    if (airlineCheckBox.isSelected()) {
      filterTerms.put("Airline", airlineField.getText());
    }
    if (sourceCheckBox.isSelected()) {
      filterTerms.put("Source", sourceField.getText());
    }
    if (destinationCheckBox.isSelected()) {
      filterTerms.put("Destination", destinationField.getText());
    }
    return filterTerms;
  }

  /**
   * Call the filterRoutes method of filterer with the given HashMap of filter types and terms and
   * the history stored in storage.
   *
   * @param filterTerms A HashMap with filter type as the key and filter term as the value.
   */
  @Override
  public void filterByDataType(HashMap<String, String> filterTerms) {
    filterer.filterRoutes(filterTerms, storage.getHistory());
  }

  /** @return The fxml filename for the history filter pop up. */
  @Override
  public String getFXMLFilename() {
    return "historyFilterPopUp.fxml";
  }
}

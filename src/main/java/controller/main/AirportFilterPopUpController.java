package controller.main;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

// TODO: check all method comments start with "This method ..."

/**
 * Pop up window in which user can select filters to apply to the list of airports.
 *
 * @author Ella Johnson
 * @version 1.0
 * @since 12/09/2020
 */
public class AirportFilterPopUpController extends FilterPopUpController {

  @FXML private TextField countryField;
  @FXML private TextField cityField;
  @FXML private CheckBox countryCheckBox;
  @FXML private CheckBox cityCheckBox;

  /**
   * Must have implementation of this method as superclass implements the Initializable interface.
   *
   * @param url Not used.
   * @param resourceBundle Not used.
   */
  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {}

  /**
   * Get the country and active status which the user has inputed and insert them into a hashmap
   * with the filter type as the key.
   *
   * @return a hashmap with filter type as the key and filter term as the value.
   */
  @Override
  public HashMap<String, String> getFilterTerms() {
    HashMap<String, String> filterTerms = new HashMap<>();
    if (countryCheckBox.isSelected()) {
      filterTerms.put("Country", countryField.getText());
    }
    if (cityCheckBox.isSelected()) {
      filterTerms.put("Active status", cityField.getText());
    }
    return filterTerms;
  }

  /**
   * Call the filterAirports method of the Filterer class with the given hashmap of filter types and
   * term.
   *
   * @param filterTerms a hashmap to pass into the filterAirports method.
   */
  public void filterByDataType(HashMap<String, String> filterTerms) {
    filterer.filterAirports(filterTerms, storage.getAirports());
  }

  // TODO: rewrite comment for this method
  /** @return The fxml filename for the airport filter pop up controller. */
  @Override
  public String getFXMLFilename() {
    return "airportFilterPopUp.fxml";
  }
}

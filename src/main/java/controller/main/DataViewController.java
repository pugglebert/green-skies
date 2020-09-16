package controller.main;

import controller.analysis.Filterer;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.data.Storage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

// TODO: check all method comments start with "This method ..."

/**
 * Superclass for all data view pages. All such pages contain a table of data, tools for searching
 * and filtering that data, and a navigation bar which can be used to navigate to other pages.
 *
 * @author Ella Johnson, Hayley Krippner.
 * @since 04/09/20
 * @version 1.0
 */
public abstract class DataViewController extends SideNavBarController {

  @FXML protected Button btnUpload;
  @FXML protected Button btnRouteDataView;
  @FXML protected Button btnAirportDataView;
  @FXML protected Button btnAirlineDataView;
  @FXML protected ChoiceBox<String> searchTypeSelection;
  @FXML protected TextField searchBar;
  @FXML protected Button searchButton;
  @FXML protected Label errorText;
  @FXML protected Button clearSearchButton;
  @FXML protected Button filterOptionsButton;
  // TODO: write comments for these attributes
  protected String filterPopUpFilename;
  protected ObservableList<String> searchTypes;
  protected final Storage storage = Main.getStorage();
  protected final Filterer filterer = Main.getFilterer();

  // TODO: write comment for this method

  protected DataViewController() {}

  // TODO: write comment for these method
  public abstract void initialize(URL url, ResourceBundle rb);

  public abstract void searchByDataType(String searchTerm, String searchType);

  public abstract void clearSearch();

  public abstract void filterOptions() throws IOException;

  /**
   * Checks users search for errors and displays an error message if any are present. If no errors
   * are present, calls searchByDataType method in subclass.
   */
  public void search() {
    String searchType = searchTypeSelection.getValue();
    String searchTerm = searchBar.getText();
    if (searchType == null) {
      errorText.setText("Select a search type to proceed.");
      errorText.setVisible(true);
    } else if (searchTerm == null) {
      errorText.setText("Enter a search term to proceed.");
      errorText.setVisible(true);
    } else {
      try {
        searchByDataType(searchTerm, searchType);
        errorText.setVisible(false);
      } catch (RuntimeException e) {
        errorText.setText(e.getMessage());
        errorText.setVisible(true);
      }
    }
  }
}

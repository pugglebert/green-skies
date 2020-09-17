package controller.main;

import controller.analysis.Filterer;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.data.Storage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Superclass for all data view pages. All such pages contain a table of data, tools for searching
 * and filtering that data, and a navigation bar which can be used to navigate to other pages.
 *
 * @author Ella Johnson, Hayley Krippner.
 * @since 2020-09-04
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

  /** Name of the fxml file associated with this controller. */
  protected String filterPopUpFilename;

  /** The types of searches which can be chosen from for a particular datatype. */
  protected ObservableList<String> searchTypes;

  /** Class to get storage data from. */
  protected final Storage storage = Main.getStorage();

  /** Class to get filtered data from. */
  protected final Filterer filterer = Main.getFilterer();

  /**
   * This method is inherited from Initializable interface.
   * @param url Not used.
   * @param rb Not used.
   */
  public abstract void initialize(URL url, ResourceBundle rb);

  /**
   * This method searches for datatypes matching the search term and type.
   * @param searchTerm The term to search for e.g. "Christchurch"
   * @param searchType The type of search e.g. "Country"
   */
  public abstract void searchByDataType(String searchTerm, String searchType);

  /**
   * This method resets the table view and clears the search bar.
   */
  public abstract void clearSearch();

  /**
   * This method opens the filter pop up.
   * @throws IOException If loading of fxml file fails.
   */
  public abstract void filterOptions() throws IOException;

  /**
   * This method checks user's search for errors and displays an error message if any are present. If no errors
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

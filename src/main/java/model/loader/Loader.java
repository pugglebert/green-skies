package model.loader;

import model.data.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.FileSystemException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static com.google.common.io.Files.getFileExtension;

/**
 * The Loader class checks that a file has the expected extension, opens that file and creates an
 * instance of the Parser class to parse the file. It then stores the processed data in the Storage
 * class.
 *
 * @author Ella Johnson
 * @version 1.0
 * @since 09/08/2020
 */
public class Loader {

  /** Expected file extension */
  private final ArrayList<String> supportedExtensions;
  /** The storage used in the application. */
  private final Storage storage;

  /** This constructor adds extensions for supported fileTypes to supportedExtensions. */
  public Loader(Storage storage) {
    this.storage = storage;
    supportedExtensions = new ArrayList<>();
    supportedExtensions.add("csv");
    supportedExtensions.add("txt");
    supportedExtensions.add("dat");
  }

  /**
   * This method checks if file extension matches supported file types.
   *
   * @param fileName Name of file to be checked.
   * @throws FileSystemException If file extension does not match one of supported formats.
   * @throws IllegalArgumentException If file has no extension.
   */
  protected void checkFileType(String fileName)
      throws FileSystemException, IllegalArgumentException {
    String extension = getFileExtension(fileName);
    if (extension.isEmpty()) {
      throw new IllegalArgumentException("Address does not contain file extension.");
    }
    if (!supportedExtensions.contains(extension)) {
      throw new FileSystemException(
          "Unsupported file type", extension, "Only CSV files can be processed.");
    }
  }

  /**
   * This method opens file, reads each line and appends it to an ArrayList.
   *
   * @param fileName Name of file to read from.
   * @return An ArrayList of a String for each line in the file.
   */
  protected ArrayList<String> openFile(String fileName) throws FileNotFoundException {

    ArrayList<String> lines = new ArrayList<>();
    File file = new File(fileName);
    Scanner scanner;

    scanner = new Scanner(file);

    while (scanner.hasNextLine()) {
      lines.add(scanner.nextLine());
    }

    scanner.close();

    return lines;
  }

  /**
   * This method constructs a parser of the relevant type.
   *
   * @param dataType The type of data to be procesed.
   * @param lines An ArrayList of Strings of data to be processed by the parser.
   * @throws IllegalArgumentException Thrown if datatype is not one of airline, airport, flight or
   *     route.
   */
  protected Parser constructParser(String dataType, ArrayList<String> lines)
      throws IllegalArgumentException {

    Parser parser;

    switch (dataType) {
      case "Airport":
        List<Airport> existingAirports = storage.getAirports();
        parser = new AirportParser(lines, existingAirports);
        break;
      case "Airline":
        List<Airline> exisitingAirlines = storage.getAirlines();
        parser = new AirlineParser(lines, exisitingAirlines);
        break;
      case "Route":
        List<Route> existingRoutes = storage.getRoutes();
        parser = new RouteParser(lines, existingRoutes);
        break;
      default:
        throw new IllegalArgumentException("Datatype must be one of: airline, airport, route.");
    }

    return parser;
  }

  /**
   * This method is the same as the loadFile method except it does not upload any data. Checks if
   * filename and datatype fields are empty. If they aren't, processes file by calling
   * checkFileType, openFile and constructParser. If an error occurs while trying to open the file,
   * returns a message about the error. Otherwise, returns message abount number of rejected lines
   * from file.
   *
   * @param fileName Name of the file to be opened.
   * @param dataType The type of data in the file (one of airport, airline, or route).
   * @return Error information string.
   */
  public String checkFile(String fileName, String dataType)
      throws FileSystemException, FileNotFoundException {

    if (fileName.isEmpty()) {
      throw new RuntimeException("Filename cannot be empty.");
    } else if (dataType.isEmpty()) {
      throw new RuntimeException("Datatype cannot be empty.");
    }

    checkFileType(fileName);
    ArrayList<String> lines;
    lines = openFile(fileName);

    Parser parser = constructParser(dataType, lines);

    return parser.getErrorMessage();
  }

  /**
   * This method checks if filename and datatype fields are empty. If they aren't, processes file by
   * calling checkFileType, openFile and constructParser. If an error occurs while trying to open
   * the file, returns a message about the error. Otherwise, returns message abount number of
   * rejected lines from file.
   *
   * @param fileName Name of the file to be opened.
   * @param dataType The type of data in the file (one of airport, airline, or route).
   * @return Error information string.
   */
  public String loadFile(String fileName, String dataType)
      throws FileSystemException, FileNotFoundException, SQLException {

    if (fileName.isEmpty()) {
      throw new RuntimeException("Filename cannot be empty.");
    } else if (dataType.isEmpty()) {
      throw new RuntimeException("Datatype cannot be empty.");
    }

    checkFileType(fileName);
    ArrayList<String> lines;
    lines = openFile(fileName);

    Parser parser = constructParser(dataType, lines);
    List<DataType> data = parser.getData();
    storage.setData(data, dataType);

    return parser.getErrorMessage();
  }

  /**
   * This method parses a single line and returns a message about whether any errors occured when
   * processing that line.
   *
   * @param entryString The entry to be parsed.
   * @param dataType Type of entry, must be one of Airline, Airport or Route.
   * @return errorMessageString
   */
  public String loadLine(String entryString, String dataType) throws SQLException {

    ArrayList<String> line = new ArrayList<>();
    line.add(entryString);

    Parser parser;

    parser = constructParser(dataType, line);

    List<DataType> data = parser.getData();
    storage.setData(data, dataType);
    return parser.getErrorMessage();
  }
}

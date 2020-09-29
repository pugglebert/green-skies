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
 * @since 2020-08-09
 */
public class Loader {

  /** Expected file extension */
  private final ArrayList<String> supportedExtensions;
  /** Filenames used by application. Cannot be used as names of user files. */
  private final ArrayList<String> reservedFilenames;
  /** The storage used in the application. */
  private final Storage storage;
  /** The parser constructed for single entry. */
  private Parser parser;

  /** This constructor adds extensions for supported fileTypes to supportedExtensions. */
  public Loader(Storage storage) {
    this.storage = storage;
    supportedExtensions = new ArrayList<>();
    supportedExtensions.add("csv");
    reservedFilenames = new ArrayList<>();
    reservedFilenames.add("singleEntryAirlines.csv");
    reservedFilenames.add("singleEntryAirports.csv");
    reservedFilenames.add("singleEntryRoutes.csv");
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
   * This method gets the name of the file from its full path.
   * @param filePath full path of the file on the computer.
   * @return the last part of the file path (after the last slash).
   */
  protected String getFileName(String filePath) {
    String fileName;
    if (filePath.contains("/")) {
      fileName = filePath.substring(filePath.lastIndexOf("/")+1);
    } else {
      fileName = filePath.substring(filePath.lastIndexOf("\\")+1);
    }
    return fileName;
  }

  /**
   * This method checks if file is already uploaded to the system.
   *
   * @param fileName Name of file to be checked.
   * @throws RuntimeException If filename is already in the system.
   */
  protected void checkDuplicateFileName(String fileName) {
    boolean duplicate = false;
    if (storage.getAirportFileNames().contains(fileName)) {
      duplicate = true;
    } else if (storage.getAirlineFileNames().contains(fileName)) {
      duplicate = true;
    } else if (storage.getRouteFileNames().contains(fileName)) {
      duplicate = true;
    }
    if (duplicate) {
      throw new RuntimeException(String.format("There is already a file with the name %s in the system", fileName));
    }
    if (reservedFilenames.contains(fileName)) {
      throw new RuntimeException(String.format("Filename %s is used by the system. User files cannot have this name", fileName));
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
  //Changed to public for database using.
  public Parser constructParser(String dataType, ArrayList<String> lines, boolean appendToExisting)
      throws IllegalArgumentException {

    Parser parser;

    switch (dataType) {
      case "Airport":
        List<Airport> existingAirports = new ArrayList<>();
        if (appendToExisting) {
          existingAirports = storage.getAirports();
        }
        parser = new AirportParser(lines, existingAirports);
        break;
      case "Airline":
        List<Airline> existingAirlines = new ArrayList<>();
        if (appendToExisting) {
          existingAirlines = storage.getAirlines();
        }
        parser = new AirlineParser(lines, existingAirlines);
        break;
      case "Route":
        List<Route> existingRoutes = new ArrayList<>();
        if (appendToExisting) {
          existingRoutes = storage.getRoutes();
        }
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
    checkDuplicateFileName(fileName);
    ArrayList<String> lines;
    lines = openFile(fileName);

    Parser parser = constructParser(dataType, lines, false);

    return parser.getErrorMessage(true);
  }

  /**
   * This method checks if filename and datatype fields are empty. If they aren't, processes file by
   * calling checkFileType, getFileName, checkDuplicateFileName, openFile and constructParser. Then
   * adds the resulting data to that stored in the storage class. If an error occurs while trying to
   * open the file, returns a message about the error. Otherwise, returns message abount number of
   * rejected lines from file.
   *
   * @param filePath Path of the file to be opened.
   * @param dataType The type of data in the file (one of airport, airline, or route).
   * @return Error information string.
   */
  public String loadFile(String filePath, String dataType)
      throws FileSystemException, FileNotFoundException, SQLException {

    if (filePath.isEmpty()) {
      throw new RuntimeException("Filename cannot be empty.");
    } else if (dataType.isEmpty()) {
      throw new RuntimeException("Datatype cannot be empty.");
    }

    checkFileType(filePath);

    String fileName = getFileName(filePath);
    checkDuplicateFileName(fileName);
    ArrayList<String> lines;
    lines = openFile(filePath);

    Parser parser = constructParser(dataType, lines, false);
    List<DataType> data = parser.getData();
    storage.setData(data, dataType, fileName);

    return parser.getErrorMessage(true);
  }

  /**
   * Returns the current filename for that datatype, or if current filename is null returns the name of the single
   * entry file.
   * @return The filename to use when adding the file to storage.
   */
  public String getLineFileName(String dataType) {
    String fileName;
    switch (dataType) {
      case "Airline":
        if (storage.getCurrentAirlineFile() == null) {
          fileName = "singleEntryAirlines.csv";
        } else {
          fileName = storage.getCurrentAirlineFile();
        }
        break;
      case "Airport":
        if (storage.getCurrentAirportFile() == null) {
          fileName = "singleEntryAirports.csv";
        } else {
          fileName = storage.getCurrentAirportFile();
        }
        break;
      case "Route":
        if (storage.getCurrentRouteFile() == null) {
          fileName = "singleEntryRoutes.csv";
        } else {
          fileName = storage.getCurrentRouteFile();
        }
        break;
      default:
        throw new IllegalArgumentException("Datatype must be one of: Airline, Airport, Route");
    }
    return fileName;
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

    String fileName = getLineFileName(dataType);

    ArrayList<String> line = new ArrayList<>();
    line.add(entryString);

    parser = constructParser(dataType, line, true);

    List<DataType> data = parser.getData();
    storage.setData(data, dataType, fileName);
    return String.format("%s successfully uploaded", dataType);
  }

  /**
   * This method returns the parser constructed for single entry.
   * @return parser that constructed for single entry.
   */
  public Parser getParser(){
    return (this.parser);
  }
}

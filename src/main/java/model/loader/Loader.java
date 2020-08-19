package model.loader;

import model.data.DataType;
import model.data.Storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.FileSystemException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

import static com.google.common.io.Files.getFileExtension;

/**
 * The Loader class checks that a file has the expected extension, opens that file and creates an instance of the
 * Parser class to parse the file
 *
 * @author Ella Johnson
 * @version 1.0
 * @since 2020-08-09
 */
public class Loader {


    /** Expected file extension*/
    private final ArrayList<String> supportedExtensions;
    private Storage storage;

    /** Adds extensions for supported fileTypes to supportedExtensions. */
    public Loader(Storage storage) {
        this.storage = storage;
        supportedExtensions = new ArrayList<>();
        supportedExtensions.add("csv");
        supportedExtensions.add("txt");
        supportedExtensions.add("dat");
    }

    /**
     * Checks if file extension matches supported file types.
     * @param fileName Name of file to be checked.
     * @throws FileSystemException If file extension does not match one of supported formats
     * @throws IllegalArgumentException If file has no extension
     */
    protected void checkFileType(String fileName) throws FileSystemException, IllegalArgumentException {
        String extension = getFileExtension(fileName);
        if (extension.isEmpty()) {
            throw new IllegalArgumentException("Address does not contain file extension.");
        } if (! supportedExtensions.contains(extension)) {
            throw new FileSystemException("Unsupported file type", extension, "Only CSV files can be processed.");
        }
    }

    /**
     * Opens file, reads each line and appends it to an ArrayList.
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
     * Constructs a parser of the relevant type.
     * @param dataType The type of data to be procesed.
     * @param lines An ArrayList of Strings of data to be processed by the parser.
     * @throws IllegalArgumentException Thrown if datatype is not one of airline, airport, flight or route.
     */
    protected Parser constructParser(String dataType, ArrayList<String> lines) throws IllegalArgumentException{

        Parser parser;

        switch (dataType) {
            case "airport" :
                try {
                    parser = new AirportParser(lines);
                } catch (RuntimeException e) {
                    throw e;
                }
                break;
            case "airline" :
                try {
                    parser = new AirlineParser(lines);
                } catch (RuntimeException e) {
                    throw e;
                }
                break;
            case "route" :
                try {
                    parser = new RouteParser(lines);
                } catch (RuntimeException e) {
                    throw e;
                }
                break;
            default:
                throw new IllegalArgumentException("Datatype must be one of: airline, airport, route.");
        }

        return parser;
    }

    /** Checks if filename and datatype fields are empty. If they aren't, processes file by calling checkFileType,
     * openFile and constructParser. If an error occurs while trying to open the file, returns a message about the
     * error. Otherwise, returns message abount number of rejected lines from file.
     * @param fileName Name of the file to be opened.
     * @param dataType The type of data in the file (one of airport, airline, flight or route).
     * @return Error information string.
     */
    public String loadFile(String fileName, String dataType) {

        if (fileName.isEmpty()) {
            return "Filename cannot be empty.";
        } else if (dataType.isEmpty()) {
            return "Datatype cannot be empty.";
        }

        try {
            checkFileType(fileName);
        } catch (FileSystemException | IllegalArgumentException e) {
            return e.getMessage();
        }

        ArrayList<String> lines;

        try {
            lines = openFile(fileName);
        } catch (FileNotFoundException e) {
            return e.getMessage();
        }

        Parser parser;

        try {
            parser = constructParser(dataType, lines);
        } catch (RuntimeException e) {
            return e.getMessage();
        }

        Set<DataType> data = parser.getData();
        storage.setData(data, dataType);

        return parser.getErrorMessage();
    }
}
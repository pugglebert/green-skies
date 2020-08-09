package model.loader;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.FileSystemException;
import java.util.ArrayList;
import java.util.Scanner;

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
    private ArrayList<String> supportedExtensions;
    private Parser parser;

    /** Adds extensions for supported fileTypes to supportedExtensions. */
    public Loader() {
        supportedExtensions = new ArrayList<String>();
        supportedExtensions.add("csv");
        supportedExtensions.add("txt");
        supportedExtensions.add("dat");
    }

    protected Parser getParser() {
        return parser;
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

        ArrayList<String> lines = new ArrayList<String>();
        File file = new File(fileName);
        Scanner scanner = null;

        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw e;
        }

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
    protected void constructParser(String dataType, ArrayList lines) throws IllegalArgumentException{

        switch (dataType) {
            case "airport" :
                parser = new AirportParser(lines);
                break;
            case "airline" :
                parser = new AirlineParser(lines);
                break;
            case "route" :
                parser = new RouteParser(lines);
                break;
            case "flight" :
                parser = new FlightParser(lines);
                break;
            default :
                throw new IllegalArgumentException("Datatype must be one of: airline, airport, flight, route.");
        }

    }

    /** Checks if filename and datatype fields are empty. If they aren't, processes file by calling checkFileType,
     * openFile and constructParser.
     * @param fileName Name of the file to be opened.
     * @param dataType The type of data in the file (one of airport, airline, flight or route).
     */
    public void loadFile(String fileName, String dataType) {

        if (fileName.isEmpty()) {
            IllegalArgumentException e = new IllegalArgumentException("Filename cannot be empty.");
            System.out.println(e.getMessage());
            return;
        } else if (dataType.isEmpty()) {
            IllegalArgumentException e = new IllegalArgumentException("Datatype cannot be empty.");
            System.out.println(e.getMessage());
            return;
        }

        try {
            checkFileType(fileName);
        } catch (FileSystemException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        ArrayList<String> lines;

        try {
            lines = openFile(fileName);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }

        constructParser(dataType, lines);
    }
}
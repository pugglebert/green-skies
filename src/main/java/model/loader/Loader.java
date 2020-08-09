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

    /** Calls checkFileType. Opens file. Seperates lines in file into ArrayList of Strings. Instantiates Parser.
     * @param fileName name of the file to be opened
     */
    public void loadFile(String fileName, String dataType) {

        try {
            checkFileType(fileName);
        } catch (FileSystemException e) {
            System.out.println(e.getMessage()); //Change later, just for debugging
        }

        ArrayList<String> lines = openFile(fileName);

        constructParser(dataType, lines);
    }

    /**
     * Checks if file extension is csv.
     * @param fileName Name of file to be checked.
     * @throws FileSystemException
     */
    public void checkFileType(String fileName) throws FileSystemException {
        String extension = getFileExtension(fileName);
        if (! supportedExtensions.contains(extension)) {
            throw new FileSystemException("Unsupported file type", extension, "Only CSV files can be processed.");
        }
    }

    public ArrayList<String> openFile(String fileName) {

        ArrayList<String> lines = new ArrayList<String>();
        File file = new File(fileName);
        Scanner scanner = null;

        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (scanner.hasNextLine()); {
            lines.add(scanner.nextLine());
        }

        return lines;
    }

    public void constructParser(String dataType, ArrayList lines) {
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
        }
    }
}

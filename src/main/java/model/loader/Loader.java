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
    private String csvExtension = "csv";
    private ArrayList<String> lines;
    private Parser parser;

    public Loader() {

    }

    /** Calls checkFileType. Opens file. Seperates lines in file into ArrayList of Strings. Instantiates Parser.
     * @param fileName name of the file to be opened
     */
    public void openFile(String fileName, String dataType) {

        try {
            checkFileType(fileName);
        } catch (FileSystemException e) {
            System.out.println(e.getMessage()); //Change later, just for debugging
        }

        lines = new ArrayList<String>();
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

        parser = new Parser(lines, dataType);
    }

    /**
     * Checks if file extension is csv.
     * @param fileName Name of file to be checked.
     * @throws FileSystemException
     */
    public void checkFileType(String fileName) throws FileSystemException {
        String extension = getFileExtension(fileName);
        if (extension != csvExtension) {
            throw new FileSystemException("Unsupported file type. Only CSV files can be processed.");
        }
    }
}

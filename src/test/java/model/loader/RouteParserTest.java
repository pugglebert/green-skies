package model.loader;

import model.data.DataType;
import model.data.Route;
import model.data.Storage;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Test cases for routePaser.
 * @author Ella
 */
public class RouteParserTest {

  private RouteParser routeParser;
  private ArrayList<String> lines;
  private ArrayList<Route> existingLines;

  @Before
  public void setup() {
    Loader loader = new Loader(new Storage());
    existingLines = new ArrayList<Route>();
    try {

      lines = loader.openFile("../seng202_project/src/test/java/TestFiles/routesTest.csv");
      routeParser = new RouteParser(lines, existingLines);
    } catch (FileNotFoundException e) {
      fail();
    }
  }

  /** Verify that isAirportValid returns true for string containing 4 capital letters */
  @Test
  public void isAirportValidFourLetterTest() {
    assertTrue(routeParser.isAirportValid("ABCD"));
  }

  /** Verify that isAirportValid returns true for string containing 4 capital letters */
  @Test
  public void isAirportValidThreeLetterTest() {
    assertTrue(routeParser.isAirportValid("CBA"));
  }

  /** Verify that isAirportValid returns false when length of airport code is not 3 or 4 */
  @Test
  public void isAirportValidWrongLengthTest() {
    assertFalse(routeParser.isAirportValid("ABCDEFG"));
  }

  /** Verify that isAirportValid returns false when aiport code contains lower case letter */
  @Test
  public void isAirportValidLowerCaseTest() {
    assertFalse(routeParser.isAirportValid("ABc"));
  }

  /** Verify that isAirportValid returns false when airport code contains a number */
  @Test
  public void isAirportValidNumberTest() {
    assertFalse(routeParser.isAirportValid("1ABC"));
  }

  /** Verify that isAirportValid returns false for the empty string */
  @Test
  public void isAirportValidEmptyTest() {
    assertFalse(routeParser.isAirportValid(""));
  }

  /** Verify that isAirportIDValid returns true when tested with a numeric string of length 4 */
  @Test
  public void isAirportIDValidLengthFiveTest() {
    assertTrue(routeParser.isAirportIDValid("12345"));
  }

  /**
   * Verify that isAirportIDValid returns true when tested with a numeric string of lenght less than
   * 4
   */
  @Test
  public void isAirportIDValidLessThanFiveTest() {
    assertTrue(routeParser.isAirportIDValid("9"));
  }

  /** Verify that isAirportIDValid returns false when tested with the null string \N */
  @Test
  public void isAirportIDValidNullTest() {
    assertFalse(routeParser.isAirportIDValid("\\N"));
  }

  /**
   * Verify that isAirportIDValid returns false when tested with a string of length greater than
   * four
   */
  @Test
  public void isAirportIDValidGreaterThanFiveTest() {
    assertFalse(routeParser.isAirportIDValid("593256"));
  }
  /**
   * Verify that isAirportIDValid returns false when tested with a string containing non-numeric
   * characters
   */
  @Test
  public void isAirportIDValidNonNumericTest() {
    assertFalse(routeParser.isAirportIDValid("123A"));
  }

  /** Verify that isAirportIDValid returns false when tested with the empty string */
  @Test
  public void isAirportIDValidEmptyTest() {
    assertFalse(routeParser.isAirportIDValid(""));
  }

  /** Verify that isAirlineValid returns true for alphanumeric string of length 3 */
  @Test
  public void isAirlineValidLengthThreeTest() {
    assertTrue(routeParser.isAirlineValid("ABC"));
  }

  /** Verify that isAirlineValid returns true for alphanumeric string of length 2 */
  @Test
  public void isAirlineValidLengthTwoTest() {
    assertTrue(routeParser.isAirlineValid("71"));
  }

  /** Verify that isAirlineValid returns false for alphanumeric string of incorrect length */
  @Test
  public void isAirlineValidLengthFiveTest() {
    assertFalse(routeParser.isAirlineValid("49193"));
  }

  /** Verify that isAirlineValid returns false for empty string */
  @Test
  public void isAirlineValidEmptyTest() {
    assertFalse(routeParser.isAirlineValid(""));
  }

  /** Verify that isAirlineValid returns false for string containing special character */
  @Test
  public void isAirlineValidSpecialCharTest() {
    assertFalse(routeParser.isAirlineValid("A_B"));
  }

  /** Verify that isAirlineIDValid returns true when tested with a numeric string of length 5 */
  @Test
  public void isAirlineIDValidLengthFourTest() {
    assertTrue(routeParser.isAirlineIDValid("12345"));
  }

  /**
   * Verify that isAirlineIDValid returns true when tested with a numeric string of lenght less than
   * 5
   */
  @Test
  public void isAirlineIDValidLessThanFiveTest() {
    assertTrue(routeParser.isAirlineIDValid("9"));
  }

  /**
   * Verify that isAirlineIDValid returns false when tested with a string of length greater than
   * five
   */
  @Test
  public void isAirlineIDValidGreaterThanFourTest() {
    assertFalse(routeParser.isAirlineIDValid("532561"));
  }

  /**
   * Verify that isAirlineIDValid returns false when tested with a string containing non-numeric
   * characters
   */
  @Test
  public void isAirlineIDValidNonNumericTest() {
    assertFalse(routeParser.isAirlineIDValid("123A"));
  }

  /** Verify that isAirlineIDValid returns false when tested with the empty string */
  @Test
  public void isAirlineIDValidEmptyTest() {
    assertFalse(routeParser.isAirlineIDValid(""));
  }

  /** Verify that isCodeshareValid returns true when tested with the empty string */
  @Test
  public void isCodeShareValidEmptyTest() {
    assertTrue(routeParser.isCodeshareValid(""));
  }

  /** Verify that isCodeshareValid returns true when tested with the string "Y" */
  @Test
  public void isCodeShareValidYTest() {
    assertTrue(routeParser.isCodeshareValid("Y"));
  }

  /**
   * Verify that isCodeshareValid returns false when tested with a string containing only whitespace
   */
  @Test
  public void isCodeShareValidWhitespaceTest() {
    assertFalse(routeParser.isCodeshareValid(" "));
  }

  /**
   * Verify that isCodeshareValid returns false when tested with a single character which is not Y
   */
  @Test
  public void isCodeShareValidOneCharTest() {
    assertFalse(routeParser.isCodeshareValid("P"));
  }

  /** Test that isStopsValid returns true when tested with sting of a number less than 10 */
  @Test
  public void isStopsValidLessThanTenTest() {
    assertTrue(routeParser.isStopsValid("3"));
  }

  /** Test that isStopsValid returns true when tested with the string "0" */
  @Test
  public void isStopsValidZeroTest() {
    assertTrue(routeParser.isStopsValid("0"));
  }

  /** Test that isStopsValid returns false when teseted with a string of a two digit number */
  @Test
  public void isStopsValidTwoDigitTest() {
    assertFalse(routeParser.isStopsValid("10"));
  }

  /**
   * Test that isStopsValid returns false when tested with a string containing a non-digital
   * character
   */
  @Test
  public void isStopsValidNonDigitalTest() {
    assertFalse(routeParser.isStopsValid("-"));
  }

  /** Verify that isStopsValid returns false when tested with the empty string */
  @Test
  public void isStopsValidEmptyTest() {
    assertFalse(routeParser.isStopsValid(""));
  }

  /** Verify that isEquipentValid returns true for a single valid plane code */
  @Test
  public void isEquipmentValidSingleTest() {
    assertTrue(routeParser.isEquipmentValid("A30"));
  }

  /** Verify that isEquipmentValid returns true for multiple valid plane codes */
  @Test
  public void isEquipmentValidMultipleTest() {
    assertTrue(routeParser.isEquipmentValid("763 757 76W"));
  }

  /**
   * Verify that isEquipmentValid returns false when one of the plane codes is greater than 3
   * characters
   */
  @Test
  public void isEquipmentValidGreaterThanThreeTest() {
    assertFalse(routeParser.isEquipmentValid("763 757 76W5"));
  }

  /**
   * Verify that isEquipmentValid returns false when one of the plane codes is less than 3
   * characters
   */
  @Test
  public void isEquipmentValidLessThanThreeTest() {
    assertFalse(routeParser.isEquipmentValid("76 757 76W"));
  }

  /**
   * Verify that isEquipmentValid returns false when one of the plane codes contains a non
   * alphanumeric character
   */
  @Test
  public void isEquipmentValidNonAlphaNumericTest() {
    assertFalse(routeParser.isEquipmentValid("763 75//7 76W"));
  }

  /** Verify that isEquipmentValid returns false for the empty string */
  @Test
  public void isEquipmentValidEmptyTest() {
    assertFalse(routeParser.isEquipmentValid(""));
  }

  /** Verify that when parseLine is called with a valid route, that route is added to routes */
  @Test
  public void parseRouteValidLineTest() {
    ArrayList<String> testLines = new ArrayList<>();
    testLines.add("2B,410,KZN,2990,DME,4029,,0,CR2");
    RouteParser testParser = new RouteParser(testLines, existingLines);
    testParser.parseLine("2B,410,AER,2965,KZN,2990,,0,CR2");
    Route existingRoute = new Route("2B",410,"KZN",2990,"DME",4029,"",0,"CR2".split(" "));
    Route addedRoute = new Route("2B", 410, "AER", 2965, "KZN", 2990, "", 0, "CR2".split(","));
    Route[] expectedArray = new Route[2];
    expectedArray[0] = existingRoute;
    expectedArray[1] = addedRoute;
    assertArrayEquals(expectedArray, testParser.getData().toArray());
  }

  /** Verify that when parseLine is called with a valid route, that error counter is not updated */
  @Test
  public void parseRouteValidLineErrorTest() {
    ArrayList<String> testLines = new ArrayList<>();
    testLines.add("2B,410,KZN,2990,DME,4029,,0,CR2");
    RouteParser testParser = new RouteParser(testLines, existingLines);
    testParser.parseLine("2B,410,AER,2965,KZN,2990,,0,CR2");
    assertEquals("File uploaded with 0 invalid lines rejected\n", testParser.getErrorMessage());
  }

  /** Test that invalid line is not added to routes */
  @Test
  public void parseRouteInvalidLineTest() {
    ArrayList<String> testLines = new ArrayList<>();
    testLines.add("2B,410,KZN,2990,DME,4029,,0,CR2");
    RouteParser testParser = new RouteParser(testLines, existingLines);
    testParser.parseLine("2B,410A,AER,2965,KZN,2990,,0,CR2");
    ArrayList<Route> expected = new ArrayList<>();
    expected.add(new Route("2B",410,"KZN",2990,"DME",4029,"",0,"CR2".split(" ")));
    assertArrayEquals(expected.toArray(), testParser.getData().toArray());
  }

  /** Test that error counter is updated when invalid line is parser. */
  @Test
  public void parseRouteInvalidLineErrorTest() {
    ArrayList<String> testLines = new ArrayList<>();
    testLines.add("2B,410,KZN,2990,DME,4029,,0,CR2");
    RouteParser testParser = new RouteParser(testLines, existingLines);
    testParser.parseLine("2B,410A,AER,2965,KZN,2990,,0,CR2");
    assertEquals(
        "File uploaded with 1 invalid lines rejected\n"
            + "Error [2] Invalid airline ID: 1 occurances\n",
        testParser.getErrorMessage());
  }

  /** Verify that no exception is thrown when 100 or fewer errors have been counted. */
  @Test
  public void dataParseLowErrorsTest() {
    for (int i = 0; i < 197; i++) {
      routeParser.errorCounter(0);
    }
    try {
      routeParser.dataParser();
    } catch (RuntimeException e) {
      fail();
    }
  }

  /**
   * Verify that an exception is thrown by dataParser method when over 100 errors have been counted.
   */
  @Test
  public void dataParseHighErrorsTest() {
    for (int i = 0; i < 201; i++) {
      routeParser.errorCounter(0);
    }
    try {
      routeParser.dataParser();
      fail();
    } catch (RuntimeException ignored) {
    }
  }

  /** Verify that changeNulls changes \N strings to zero. */
  @Test
  public void changeNullsNullTest() {
    String[] testString = {"MI", "4750", "HYD", "\\N", "SIN", "3316", "", "0", "320 738"};
    routeParser.changeNulls(testString);
    assertEquals("0", testString[3]);
  }

  /** Verify that changeNulls doesn't change non-null strings. */
  @Test
  public void changeNullsNonNullTest() {
    String[] testString = {"MI", "4750", "HYD", "43", "SIN", "3316", "", "0", "320 738"};
    String[] testStringCopy = Arrays.copyOf(testString, 9);
    routeParser.changeNulls(testString);
    assertArrayEquals(testStringCopy, testString);
  }

  /** Verify that the validator method returns true for a valid line. */
  @Test
  public void validatorValidLineTest() {
    String[] testString = {"MI", "4750", "HYD", "\\N", "SIN", "3316", "", "0", "320 738"};
    assertTrue(routeParser.validater(testString));
  }

  /**
   * Verify that the validator method returns false for a line which doesn't pass one of the isValid
   * tests.
   */
  @Test
  public void validatorInvalidLineTest() {
    String[] testString = {"MI", "475000", "HYD", "\\N", "SIN", "3316", "", "0", "320 738"};
    assertFalse(routeParser.validater(testString));
  }

  /**
   * Verify that error message is updated for a line which doesn't pass one of the isValid tests.
   */
  @Test
  public void validatorInvalidLineErrorTest() {
    String[] testString = {"MI", "475000", "HYD", "\\N", "SIN", "3316", "", "0", "320 738"};
    routeParser.validater(testString);
    assertEquals(
        "File uploaded with 1 invalid lines rejected\n"
            + "Error [2] Invalid airline ID: 1 occurances\n",
        routeParser.getErrorMessage());
  }

  /** Verify that validater returns false for a line with the wrong number of parameters. */
  @Test
  public void validatorWrongParamsLineTest() {
    String[] testString = {"MI", "475000", "HYD", "\\N", "SIN", "3316", "", "0", "320 738", ""};
    assertFalse(routeParser.validater(testString));
  }

  /** Verify that the correct error code is produced when attempting to add a duplicate route */
  @Test
  public void duplicateErrorMessageTest() throws FileNotFoundException {
    Loader loader = new Loader(new Storage());
    ArrayList<String> duplicateLines =
        loader.openFile("../seng202_project/src/test/java/TestFiles/duplicateRouteTest.csv");
    RouteParser duplicateParser = new RouteParser(duplicateLines, existingLines);
    assertEquals(
        "File uploaded with 1 invalid lines rejected\nError [10] Duplicate route: 1 occurances\n",
        duplicateParser.getErrorMessage());
  }

  /**
   * Verify that the route is only added to the data once when attempting to add a duplicate route
   */
  @Test
  public void duplicateNotAddedTest() throws FileNotFoundException {
    Loader loader = new Loader(new Storage());
    ArrayList<String> duplicateLines =
        loader.openFile("../seng202_project/src/test/java/TestFiles/duplicateRouteTest.csv");
    RouteParser duplicateParser = new RouteParser(duplicateLines, existingLines);
    assertEquals(1, duplicateParser.getData().size());
  }
}

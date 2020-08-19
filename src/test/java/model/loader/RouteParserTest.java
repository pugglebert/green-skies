package model.loader;

import model.data.DataType;
import model.data.Route;
import model.data.Storage;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class RouteParserTest {

    private RouteParser routeParser;
    private ArrayList<String> lines;

    @Before
    public void setup() {
        Loader loader = new Loader(new Storage());
        try {
            lines = loader.openFile("../seng202_project/src/test/java/TestFiles/routesTest.csv");
            routeParser = new RouteParser(lines);
        } catch (FileNotFoundException e) {
            fail();
        }
    }

    @Test
    /*Verify that isAirportValid returns true for string containing 4 capital letters*/
    public void isAirportValidFourLetterTest() {
        assertTrue(routeParser.isAirportValid("ABCD"));
    }

    @Test
    /*Verify that isAirportValid returns true for string containing 4 capital letters*/
    public void isAirportValidThreeLetterTest() {
        assertTrue(routeParser.isAirportValid("CBA"));
    }

    @Test
    /*Verify that isAirportValid returns false when length of airport code is not 3 or 4*/
    public void isAirportValidWrongLengthTest() {
        assertFalse(routeParser.isAirportValid("ABCDEFG"));
    }

    @Test
    /*Verify that isAirportValid returns false when aiport code contains lower case letter*/
    public void isAirportValidLowerCaseTest() {
        assertFalse(routeParser.isAirportValid("ABc"));
    }

    @Test
    /*Verify that isAirportValid returns false when airport code contains a number*/
    public void isAirportValidNumberTest() {
        assertFalse(routeParser.isAirportValid("1ABC"));
    }

    @Test
    /*Verify that isAirportValid returns false for the empty string*/
    public void isAirportValidEmptyTest() {
        assertFalse(routeParser.isAirportValid(""));
    }

    @Test
    /* Verify that isAirportIDValid returns true when tested with a numeric string of length 4 */
    public void isAirportIDValidLengthFourTest() {
        assertTrue(routeParser.isAirportIDValid("1234"));
    }

    @Test
    /*Verify that isAirportIDValid returns true when tested with a numeric string of lenght less than 4 */
    public void isAirportIDValidLessThanFourTest() {
        assertTrue(routeParser.isAirportIDValid("9"));
    }

    @Test
    /*Verify that isAirportIDValid returns true when tested with the null string \N */
    public void isAirportIDValidNullTest() {
        assertTrue(routeParser.isAirportIDValid("\\N"));
    }

    @Test
    /*Verify that isAirportIDValid returns false when tested with a string of length greater than four*/
    public void isAirportIDValidGreaterThanFourTest() {
        assertFalse(routeParser.isAirportIDValid("53256"));
    }

    @Test
    /*Verify that isAirportIDValid returns false when tested with a string containing non-numeric characters */
    public void isAirportIDValidNonNumericTest() {
        assertFalse(routeParser.isAirportIDValid("123A"));
    }

    @Test
    /*Verify that isAirportIDValid returns false when tested with the empty string */
    public void isAirportIDValidEmptyTest() {
        assertFalse(routeParser.isAirportIDValid(""));
    }

    @Test
    /*Verify that isAirlineValid returns true for alphanumeric string of length 3 */
    public void isAirlineValidLengthThreeTest() {
        assertTrue(routeParser.isAirlineValid("ABC"));
    }

    @Test
    /*Verify that isAirlineValid returns true for alphanumeric string of length 2 */
    public void isAirlineValidLengthTwoTest() {
        assertTrue(routeParser.isAirlineValid("71"));
    }

    @Test
    /*Verify that isAirlineValid returns false for alphanumeric string of incorrect length */
    public void isAirlineValidLengthFiveTest() {
        assertFalse(routeParser.isAirlineValid("49193"));
    }

    @Test
    /*Verify that isAirlineValid returns false for empty string */
    public void isAirlineValidEmptyTest() {
        assertFalse(routeParser.isAirlineValid(""));
    }

    @Test
    /*Verify that isAirlineValid returns false for string containing special character */
    public void isAirlineValidSpecialCharTest() {
        assertFalse(routeParser.isAirlineValid("A_B"));
    }

    @Test
    /* Verify that isAirlineIDValid returns true when tested with a numeric string of length 5 */
    public void isAirlineIDValidLengthFourTest() {
        assertTrue(routeParser.isAirlineIDValid("12345"));
    }

    @Test
    /*Verify that isAirlineIDValid returns true when tested with a numeric string of lenght less than 5 */
    public void isAirlineIDValidLessThanFiveTest() {
        assertTrue(routeParser.isAirlineIDValid("9"));
    }

    @Test
    /*Verify that isAirlineIDValid returns false when tested with a string of length greater than five*/
    public void isAirlineIDValidGreaterThanFourTest() {
        assertFalse(routeParser.isAirlineIDValid("532561"));
    }

    @Test
    /*Verify that isAirlineIDValid returns false when tested with a string containing non-numeric characters */
    public void isAirlineIDValidNonNumericTest() {
        assertFalse(routeParser.isAirlineIDValid("123A"));
    }

    @Test
    /*Verify that isAirlineIDValid returns false when tested with the empty string */
    public void isAirlineIDValidEmptyTest() {
        assertFalse(routeParser.isAirlineIDValid(""));
    }

    @Test
    /* Verify that isCodeshareValid returns true when tested with the empty string */
    public void isCodeShareValidEmptyTest() {
        assertTrue(routeParser.isCodeshareValid(""));
    }

    @Test
    /* Verify that isCodeshareValid returns true when tested with the string "Y" */
    public void isCodeShareValidYTest() {
        assertTrue(routeParser.isCodeshareValid("Y"));
    }

    @Test
    /* Verify that isCodeshareValid returns false when tested with a string containing only whitespace */
    public void isCodeShareValidWhitespaceTest() {
        assertFalse(routeParser.isCodeshareValid(" "));
    }

    @Test
    /* Verify that isCodeshareValid returns false when tested with a single character which is not Y */
    public void isCodeShareValidOneCharTest() {
        assertFalse(routeParser.isCodeshareValid("P"));
    }

    @Test
    /* Test that isStopsValid returns true when tested with sting of a number less than 10 */
    public void isStopsValidLessThanTenTest() {
        assertTrue(routeParser.isStopsValid("3"));
    }

    @Test
    /* Test that isStopsValid returns true when tested with the string "0" */
    public void isStopsValidZeroTest() {
        assertTrue(routeParser.isStopsValid("0"));
    }

    @Test
    /* Test that isStopsValid returns false when teseted with a string of a two digit number */
    public void isStopsValidTwoDigitTest() {
        assertFalse(routeParser.isStopsValid("10"));
    }

    @Test
    /* Test that isStopsValid returns false when tested with a string containing a non-digital character */
    public void isStopsValidNonDigitalTest() {
        assertFalse(routeParser.isStopsValid("-"));
    }

    @Test
    /*Verify that isStopsValid returns false when tested with the empty string*/
    public void isStopsValidEmptyTest() {
        assertFalse(routeParser.isStopsValid(""));
    }

    @Test
    /*Verify that isEquipentValid returns true for a single valid plane code*/
    public void isEquipmentValidSingleTest() {
        assertTrue(routeParser.isEquipmentValid("A30"));
    }

    @Test
    /*Verify that isEquipmentValid returns true for multiple valid plane codes */
    public void isEquipmentValidMultipleTest() {
        assertTrue(routeParser.isEquipmentValid("763 757 76W"));
    }

    @Test
    /*Verify that isEquipmentValid returns false when one of the plane codes is greater than 3 characters */
    public void isEquipmentValidGreaterThanThreeTest() {
        assertFalse(routeParser.isEquipmentValid("763 757 76W5"));
    }

    @Test
    /*Verify that isEquipmentValid returns false when one of the plane codes is less than 3 characters */
    public void isEquipmentValidLessThanThreeTest() {
        assertFalse(routeParser.isEquipmentValid("76 757 76W"));
    }

    @Test
    /*Verify that isEquipmentValid returns false when one of the plane codes contains a non alphanumeric character */
    public void isEquipmentValidNonAlphaNumericTest() {
        assertFalse(routeParser.isEquipmentValid("763 75//7 76W"));
    }

    @Test
    /*Verify that isEquipmentValid returns false for the empty string */
    public void isEquipmentValidEmptyTest() {
        assertFalse(routeParser.isEquipmentValid(""));
    }

    @Test
    /*Verify that when parseLine is called with a valid route, that route is added to routes */
    public void parseRouteValidLineTest() {
        ArrayList<String> testLines = new ArrayList<String>();
        RouteParser testParser = new RouteParser(testLines);
        testParser.parseLine("2B,410,AER,2965,KZN,2990,,0,CR2");
        Route expectedRoute = new Route("2B",410,"AER",2965,"KZN",
                2990,"",0, "CR2".split(","));
        assertEquals(1, testParser.getData().size());
        Route actualRoute = (Route) testParser.getData().toArray()[0];
        assertTrue((expectedRoute.equals(actualRoute)));
    }



}
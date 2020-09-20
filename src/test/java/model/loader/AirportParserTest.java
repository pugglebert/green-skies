package model.loader;

import model.data.Airport;
import model.data.Storage;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * This class tests airportParser.
 * @author Lambert
 * @since 20/09/2020
 */
public class AirportParserTest {
    private AirportParser airportParser;

    /**
     * This method sets up evironment before each test case.
     */
    @Before
    public void setUp() {
        Loader loader = new Loader(new Storage());
        List<Airport> existingLines = new ArrayList<Airport>();
        try {
            ArrayList<String> lines =
                    loader.openFile("../seng202_project/src/test/java/TestFiles/airportsTest.csv");
            airportParser = new AirportParser(lines, existingLines);
        } catch (FileNotFoundException e) {
        }
    }

    /**
     * Test if the airportId is valid with a new airport id number that is not exists in storage.
     */
    @Test
    public void isAirportIdValidNoDupId() {
        assertTrue(airportParser.isIdValid("4"));
    }

    /**
     * Test if method reject airport with exists id.
     */
    @Test
    public void isAirportIdValidDupId() {
        assertTrue(airportParser.isIdValid("3"));
    }

    /**
     * Test if method accept valid airport name without space.
     */
    @Test
    public void isNameValidWithoutSpace() {
        assertTrue(airportParser.isNameValid("ab"));
    }

    /**
     * Test if method accept valid airport name with space.
     */
    @Test
    public void isNameValidWithSpace() {
        assertTrue(airportParser.isNameValid("a b"));
    }

    /**
     * Test if method accept valid airport city without space.
     */
    @Test
    public void isCityValidWithoutSpace() {
        assertTrue(airportParser.isCityValid("ab"));
    }

    /**
     * Test if method accept valid airport city with space.
     */
    @Test
    public void isCityWithSpace() {
        assertTrue(airportParser.isCityValid("a b"));
    }

    /**
     * Test if method accept valid airport city with space.
     */
    @Test
    public void isCityInvalid() {
        assertFalse(airportParser.isCityValid("a%b"));
    }

    /**
     * Test if method accept valid country without space.
     */
    @Test
    public void isCountryValidWithoutSpace() {
        assertTrue(airportParser.isCountryValid("ab"));
    }

    /** Test if method reject invalid coutry name. */
    @Test
    public void isCountryValidInvalidName() {
        assertFalse(airportParser.isCountryValid("4a"));
    }

    /** Test if method accept valid country name with space. */
    @Test
    public void isCountryWithSpace() {
        assertTrue(airportParser.isCountryValid("a b"));
    }

  /** Test if method reject invalid country name with space. */
  @Test
  public void isCountryInvalid() {
    assertFalse(airportParser.isCountryValid("a%b"));
  }

    /** Test if method accept null as IATA code. */
    @Test
    public void isIATAValidNull() {
        assertTrue(airportParser.isIATAValid("null"));
    }

    /** Test if method accept IATA code that is unknown. */
    @Test
    public void isIATAValidUnknow() {
        assertTrue(airportParser.isIATAValid("unknown"));
    }

    /** Test if method reject unexpected formation of code. */
    @Test
    public void isIATAValidNotFormated() {
        assertFalse(airportParser.isIATAValid("abcd"));
    }

    /** Test if method reject IATA code with space. */
    @Test
    public void isIATAValidWithSpace() {
        assertFalse(airportParser.isIATAValid("a b"));
    }

    /** Test if method accept IATA code with correct fomation. */
    @Test
    public void isIATAValidValid() {
        assertTrue(airportParser.isIATAValid("ABC"));
    }

    /** Test if method reject IATA code that is null. */
    @Test
    public void isICAOValidNull() {
        assertTrue(airportParser.isICAOValid("null"));
    }

    /** Test if method accept ICAO code that is unknown */
    @Test
    public void isICAOValidUnknow() {
        assertTrue(airportParser.isICAOValid("unknown"));
    }

    /** Test if method reject ICAO code with incorrect fomation. */
    @Test
    public void isICAOValidNotFormated() {
        assertFalse(airportParser.isICAOValid("abc"));
    }

    /** Test if method reject invalid ICAO code with space. */
    @Test
    public void isICAOValidWithSpace() {
        assertFalse(airportParser.isICAOValid("a b"));
    }

    /** Test if method accpet ICAO code with space. */
    @Test
    public void isICAOValidValid() {
        assertTrue(airportParser.isICAOValid("ABCD"));
    }

    /** Test if method accpet correct latitude. */
    @Test
    public void isLatValidCorrect() {
        assertTrue(airportParser.isLatValid("-6.081689834590001"));
    }

    /** Test if method reject invalid latitude. */
    @Test
    public void isLatValidWrong() {
        assertFalse(airportParser.isLatValid("-6.081689834590001ab"));
    }

    /** Test if method accpet correct longitude. */
    @Test
    public void isLonValidCorrect() {
        assertTrue(airportParser.isLonValid("145.391998291"));
    }

    /** Test if method reject invalid longitude. */
    @Test
    public void isLonValidWrong() {
        assertFalse(airportParser.isLonValid("145.391998291ab"));
    }

    /** Test if method accpet correct altitude. */
    @Test
    public void isAltValidCorrect() {
        assertTrue(airportParser.isAltValid("145"));
    }

    /** Test if method reject invalid altitude. */
    @Test
    public void isAltValidWrong() {
        assertFalse(airportParser.isAltValid("145.391998291ab"));
    }

    /** Test if method accpet correct timezome. */
    @Test
    public void isTZValidCorrect() {
        assertTrue(airportParser.isTZValid("5.5"));
    }

    /** Test if method reject invalid timezome. */
    @Test
    public void isTZValidWrong1() {
        assertFalse(airportParser.isTZValid("5a"));
    }

    /** Test if method reject invalid timezome number. */
    @Test
    public void isTZValidWrong2() {
        assertFalse(airportParser.isTZValid("15"));
    }

    /** Test if method reject invalid timezome number. */
    @Test
    public void isTZValidWrong3() {
        assertFalse(airportParser.isTZValid("-13"));
    }

    /** Test if method accpet correct timezome. */
    @Test
    public void isTZValidCorrect2() {
        assertTrue(airportParser.isTZValid("-12"));
    }

    /** Test if method accpet correct timezome. */
    @Test
    public void isTZValidCorrect3() {
        assertTrue(airportParser.isTZValid("14"));
    }

    /** Test if method accpet correct DST. */
    @Test
    public void isDSTValidCorrect() {
        assertTrue(airportParser.isDSTValid("E"));
    }

    /** Test if method reject invalid DST. */
    @Test
    public void isDSTValidWrong1() {
        assertFalse(airportParser.isDSTValid("14"));
    }

    /** Test if method reject invalid DST. */
    @Test
    public void isDSTValidWrong2() {
        assertFalse(airportParser.isDSTValid("B"));
    }

    /** Test if method accpet correct databaseTimezone. */
    @Test
    public void isDBValidCorrect() {
        assertTrue(airportParser.isDBTZValid("a/b"));
    }

    /** Test if method rejcet incorrect databaseTimezone. */
    @Test
    public void isDBValidWrong2() {
        assertFalse(airportParser.isDBTZValid("a1"));
    }
}
